package config

import (
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
)

var connect *gorm.DB

// 连接数据库
func ConnectDB() {
	dsn := "root:mySQL070620.Zwx@tcp(127.0.0.1:3306)/library_system?charset=utf8mb4&parseTime=True&loc=Local"

	open, err := gorm.Open(mysql.Open(dsn), &gorm.Config{})

	if err != nil {
		return
	}
	connect = open
}

// 将connect返回出去始终存在
func Connect() *gorm.DB {
	return connect
}
