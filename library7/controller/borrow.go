package controller

import (
	"github.com/gin-gonic/gin"
	"library7/service"
	"library7/utils"
)

func BorrowBook(a *gin.Context) {
	userID, _ := a.Get("userID")
	bookID := a.Query("book_id")

	err := service.BorrowBook(userID.(string), bookID)
	if err != nil {
		utils.Response(a, 400, err.Error(), nil)
		return
	}
	utils.Response(a, 200, "借阅成功", nil)
}

func ReturnBook(a *gin.Context) {
	recordID := a.Query("record_id")
	err := service.ReturnBook(recordID)
	if err != nil {
		utils.Response(a, 400, err.Error(), nil)
		return
	}
	utils.Response(a, 200, "归还成功", nil)
}

func GetMyRecords(a *gin.Context) {
	userID, _ := a.Get("userID")
	list, err := service.GetMyRecords(userID.(string))
	if err != nil {
		utils.Response(a, 400, "查询失败", nil)
		return
	}
	utils.Response(a, 200, "查询成功", list)
}

func GetAllRecords(a *gin.Context) {
	list, err := service.GetAllRecords()
	if err != nil {
		utils.Response(a, 400, "查询失败", nil)
		return
	}
	utils.Response(a, 200, "查询成功", list)
}
