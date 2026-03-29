package main

import (
	"library7/config"
	"library7/router"
	"log"

	"github.com/gin-gonic/gin"
)

func main() {
	config.ConnectDB()
	r := gin.Default()
	router.Router(r)

	err := r.Run(":8079")
	if err != nil {
		log.Fatalf("启动失败：%v", err)
	}
}
