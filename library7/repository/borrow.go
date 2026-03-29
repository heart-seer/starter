package repository

import (
	"library7/config"
	"library7/model/entity"
)

// 借阅
func CreateBorrowRecord(record *entity.BorrowRecord) error {
	// 往 borrow_record 表插入一条借阅记录
	err := config.Connect().Table("borrow_record").Create(record).Error
	if err != nil {
		return err
	}
	return nil
}

// 幻术＋更新记录
// 参数：记录ID、归还时间、状态、逾期天数、罚金
func ReturnBook(recordID string, returnTime int64, status int, days int, fine int) error {
	// 根据 recordID 更新：归还时间、状态、逾期天数、罚金
	return config.Connect().Table("borrow_record").Where("id = ?", recordID).Updates(map[string]interface{}{
		"return_time": returnTime,
		// 归还时间
		"status": status,
		// 状态：0未归还 / 1已归还
		"overdue_days": days,
		// 逾期天数
		"fine": fine,
		// 逾期罚金
	}).Error
}

// 根据用户ID 查询该用户的所有借阅记录
func GetMyRecords(userID string) ([]*entity.BorrowRecord, error) {
	var list []*entity.BorrowRecord
	//查询
	err := config.Connect().Table("borrow_record").Where("user_id = ?", userID).Find(&list).Error
	if err != nil {
		return nil, err
	}
	return list, nil
}

// 管理员查询-》所有借阅记录
func GetAllRecords() ([]*entity.BorrowRecord, error) {
	var list []*entity.BorrowRecord
	// 查询 borrow_record 表所有数据
	err := config.Connect().Table("borrow_record").Find(&list).Error
	if err != nil {
		return nil, err
	}
	return list, nil
}

// 根据借阅记录ID 查询单条借阅详情
func GetRecordByID(recordID string) (*entity.BorrowRecord, error) {
	var record entity.BorrowRecord
	err := config.Connect().Table("borrow_record").Where("id = ?", recordID).First(&record).Error
	return &record, err
}
