package repository

import (
	"errors"
	"library7/config"
	"library7/model/entity"

	"gorm.io/gorm"
)

// 获取所有图书列表
func GetAllBooks() ([]*entity.Book, error) {
	var list []*entity.Book
	// 从 book 表查询所有数据，存入 list
	err := config.Connect().Table("book").Find(&list).Error
	if err != nil {
		return nil, err
	}
	return list, nil
}

// 获取热门图书
func GetHotBooks() ([]*entity.Book, error) {
	var list []*entity.Book
	//更具borrow_count 从大到小排序，查询所有图书
	err := config.Connect().Table("book").Order("borrow_count desc").Find(&list).Error
	if err != nil {
		return nil, err
	}
	return list, nil
}

// 根据图书ID查询单本图书
func GetBookByID(id string) (*entity.Book, error) {
	var book entity.Book
	err := config.Connect().Table("book").Where("id = ?", id).First(&book).Error

	if err == gorm.ErrRecordNotFound {
		return nil, errors.New("图书不存在")
	}
	return &book, err
}

// 添加一本新图书到数据库
func AddBook(book *entity.Book) error {
	err := config.Connect().Table("book").Create(book).Error
	if err != nil {
		return err
	}
	return nil
}

// 根据图书ID更新图书信息
func UpdateBook(book *entity.Book) error {
	return config.Connect().
		Table("book").
		Where("id = ?", book.ID).
		Updates(book).Error
}

// 根据图书ID删除图书
func DeleteBook(id string) error {
	// 根据 id 删除 book 表中的记录
	return config.Connect().
		Table("book").
		Where("id = ?", id).
		Delete(nil).Error
}

// 根据分类查询该分类下的所有图书
// 模糊查询
func GetBooksByCategory(cid string) ([]*entity.Book, error) {
	var list []*entity.Book
	err := config.Connect().Table("book").Where("category_ids like ?", "%"+cid+"%").Find(&list).Error
	if err != nil {
		return nil, err
	}
	return list, nil
}
func SetBookCategory(bookId string, categoryId string) error {
	return config.Connect().
		Table("book").
		Where("id = ?", bookId).
		Update("category_id", categoryId).Error
}
