package entity

type Book struct {
	ID          string `json:"id"`
	Title       string `json:"title"`
	Author      string `json:"author"`
	PublishDate string `json:"publish"`
	Stock       int    `json:"count"`
	BorrowCount int    `json:"borrow_count"`
	CategoryId  string `json:"categoryId"`
}
