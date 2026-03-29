package service

import (
	"errors"
	"library7/model/entity"
	"library7/repository"
	"time"
)

// 借阅图书
func BorrowBook(userID, bookID string) error {
	// 1.用户ID不能为空
	if userID == "" {
		return errors.New("用户ID不能为空")
	}
	// 图书ID不能为空
	if bookID == "" {
		return errors.New("图书ID不能为空")
	}

	// 2.查询图书是否存在
	book, err := repository.GetBookByID(bookID)
	if err != nil {
		return err
	}

	// 3.库存不足无法借阅
	if book.Stock < 1 {
		return errors.New("库存不足")
	}

	// 5.扣库存、增加借阅次数
	book.Stock--
	book.BorrowCount++
	err = repository.UpdateBook(book)
	if err != nil {
		return errors.New("更新图书信息失败")
	}

	// 6.构造借阅记录
	record := &entity.BorrowRecord{
		ID:         time.Now().Format("20060102150405"),
		UserID:     userID,
		BookID:     bookID,
		BorrowTime: time.Now().Unix(),
		Status:     1, // 1=未归还
	}

	// 7.插入借阅记录
	return repository.CreateBorrowRecord(record)
}

// 归还图书
func ReturnBook(recordID string) error {
	// 1.记录ID不能为空
	if recordID == "" {
		return errors.New("借阅记录ID不能为空")
	}

	// 2.查询借阅记录是否存在
	record, err := repository.GetRecordByID(recordID)
	if err != nil {
		return errors.New("借阅记录不存在")
	}

	// 3.只有未归还的图书才能还
	if record.Status != 1 {
		return errors.New("该图书已归还或异常，无法再次归还")
	}

	// 4.计算借阅时长、逾期、罚金
	now := time.Now().Unix()
	days := (now - record.BorrowTime) / 86400
	status := 2 // 2=正常归还
	fine := 0

	// 5.超过30天算逾期，产生罚金
	if days > 30 {
		status = 3 // 3=逾期归还
		fine = int(days-30) * 10
	}

	// 6.归还图书 → 库存+1
	book, err := repository.GetBookByID(record.BookID)
	if err != nil {
		return errors.New("图书信息异常")
	}
	book.Stock++
	err = repository.UpdateBook(book)
	if err != nil {
		return errors.New("恢复图书库存失败")
	}

	// 7.更新归还记录
	return repository.ReturnBook(recordID, now, status, int(days), fine)
}

// 获取当前用户的所有借阅记录
func GetMyRecords(userID string) ([]*entity.BorrowRecord, error) {
	if userID == "" {
		return nil, errors.New("用户ID不能为空")
	}
	return repository.GetMyRecords(userID)
}

// 管理员获取所有借阅记录
func GetAllRecords() ([]*entity.BorrowRecord, error) {
	return repository.GetAllRecords()
}
