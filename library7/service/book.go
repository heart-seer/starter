package service

import (
	"errors"
	"library7/model/entity"
	"library7/repository"
	"unicode/utf8"
)

// 获取所有图书
func GetAllBooks() ([]*entity.Book, error) {
	return repository.GetAllBooks()
}

// 获取热门图书
func GetHotBooks() ([]*entity.Book, error) {
	return repository.GetHotBooks()
}

// 添加图书
func AddBook(book *entity.Book) (*entity.Book, error) {
	//书名长度不能少于2个字符
	if utf8.RuneCountInString(book.Title) < 2 {
		return nil, errors.New("书名过短")
	}

	//库存不能为负数
	if book.Stock < 0 {
		return nil, errors.New("库存不能为负数")
	}
	// 调用数据库添加图书
	err := repository.AddBook(book)
	if err != nil {
		return nil, err
	}
	return book, nil
}

// 修改图书信息
func UpdateBook(book *entity.Book) (*entity.Book, error) {
	// 必要：判断图书是否存在
	_, err := repository.GetBookByID(book.ID)
	if err != nil {
		return nil, errors.New("图书不存在")
	}

	err = repository.UpdateBook(book)
	if err != nil {
		return nil, err
	}
	return book, nil
}

// 删除图书
func DeleteBook(id string) (*entity.Book, error) {
	// 先根据ID查询图书是否存在
	book, err := repository.GetBookByID(id)
	if err != nil {
		return nil, err
	}

	err = repository.DeleteBook(id)
	if err != nil {
		return nil, err
	}
	return book, nil
}

// 根据分类ID查询该分类下的所有图书
func GetBooksByCategory(cid string) ([]*entity.Book, error) {
	return repository.GetBooksByCategory(cid)
}
func SetBookCategory(bookId string, categoryId string) error {
	return repository.SetBookCategory(bookId, categoryId)
}
