package router

import (
	"library7/controller"
	"library7/middleware"

	"github.com/gin-gonic/gin"
)

func Router(a *gin.Engine) {
	a.POST("/api/user/login", controller.UserLogin)
	a.POST("/api/user/register", controller.UserRegister)

	a.GET("/api/book/list", controller.GetAllBooks)
	a.GET("/api/book/hot", controller.GetHotBooks)
	a.GET("/api/book/category", controller.GetBooksByCategory)
	a.GET("/api/category/list", controller.GetAllCategory)

	auth := a.Group("/api")
	auth.Use(middleware.TokenAuthMiddleware())
	{
		auth.POST("/book/add", controller.AddBook)
		auth.POST("/book/update", controller.UpdateBook)
		auth.GET("/book/delete", controller.DeleteBook)

		auth.POST("/category/add", controller.AddCategory)
		auth.POST("/category/update", controller.UpdateCategory)
		auth.GET("/category/delete", controller.DeleteCategory)
		auth.POST("/book/set-category", controller.SetBookCategory)

		auth.GET("/borrow", controller.BorrowBook)
		auth.GET("/return", controller.ReturnBook)
		auth.GET("/borrow/my", controller.GetMyRecords)
		auth.GET("/borrow/all", controller.GetAllRecords)
	}
}
