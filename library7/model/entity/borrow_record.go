package entity

type BorrowRecord struct {
	ID          string `json:"id"`
	UserID      string `json:"user_id"`
	BookID      string `json:"book_id"`
	BorrowTime  int64  `json:"borrow_time"`
	ReturnTime  int64  `json:"return_time"`
	Status      int    `json:"status"` //1=借阅中 2=已归还 3=逾期
	OverdueDays int    `json:"overdue_days"`
	Fine        int    `json:"fine"`
}
