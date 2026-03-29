package entity

type User struct {
	ID       string `json:"id"`
	Username string `json:"username"`
	Password string `json:"password"`
	//记录错误次数
	LoginAttempts int `json:"login_attempts"`
}
