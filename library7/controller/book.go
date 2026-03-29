package controller

import (
	"library7/model/entity"
	"library7/service"
	"library7/utils"
	"time"

	"github.com/gin-gonic/gin"
)

func GetAllBooks(a *gin.Context) {
	list, err := service.GetAllBooks()
	if err != nil {
		utils.Response(a, 400, "查询失败", nil)
		return
	}
	utils.Response(a, 200, "查询成功", list)
}

func GetHotBooks(a *gin.Context) {
	list, err := service.GetHotBooks()
	if err != nil {
		utils.Response(a, 400, "查询失败", nil)
		return
	}
	utils.Response(a, 200, "查询成功", list)
}

func AddBook(a *gin.Context) {
	var book entity.Book
	err := a.ShouldBindJSON(&book)
	if err != nil {
		utils.Response(a, 400, "参数解析失败", nil)
		return
	}

	if book.Title == "" {
		utils.Response(a, 400, "书名不能为空", nil)
		return
	}

	book.ID = time.Now().Format("20060102150405")
	_, err = service.AddBook(&book)
	if err != nil {
		utils.Response(a, 400, err.Error(), nil)
		return
	}
	utils.Response(a, 200, "添加成功", book)
}

func UpdateBook(a *gin.Context) {
	var book entity.Book
	err := a.ShouldBindJSON(&book)
	if err != nil {
		utils.Response(a, 400, "参数错误", nil)
		return
	}

	if book.ID == "" {
		utils.Response(a, 400, "图书ID不能为空", nil)
		return
	}

	_, err = service.UpdateBook(&book)
	if err != nil {
		utils.Response(a, 400, "更新失败", nil)
		return
	}
	utils.Response(a, 200, "更新成功", book)
}

func DeleteBook(a *gin.Context) {
	id := a.Query("id")
	if id == "" {
		utils.Response(a, 400, "id不能为空", nil)
		return
	}

	_, err := service.DeleteBook(id)
	if err != nil {
		utils.Response(a, 400, err.Error(), nil)
		return
	}
	utils.Response(a, 200, "删除成功", nil)
}

func GetBooksByCategory(a *gin.Context) {
	cid := a.Query("cid")
	list, err := service.GetBooksByCategory(cid)
	if err != nil {
		utils.Response(a, 400, "查询失败", nil)
		return
	}
	utils.Response(a, 200, "查询成功", list)
}
func SetBookCategory(a *gin.Context) {
	var req struct {
		BookId     string `json:"bookId"`
		CategoryId string `json:"categoryId"`
	}

	// 解析参数
	if err := a.ShouldBindJSON(&req); err != nil {
		utils.Response(a, 400, "参数错误", nil)
		return
	}

	// 校验不能为空
	if req.BookId == "" || req.CategoryId == "" {
		utils.Response(a, 400, "图书ID和分类ID不能为空", nil)
		return
	}

	// 调用 service
	err := service.SetBookCategory(req.BookId, req.CategoryId)
	if err != nil {
		utils.Response(a, 400, "设置失败", nil)
		return
	}

	utils.Response(a, 200, "图书分类设置成功", nil)
}
