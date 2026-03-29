package utils

import "github.com/gin-gonic/gin"

type ResponseData struct {
	Code    int         `json:"code"`
	Message string      `json:"message"`
	Data    interface{} `json:"data"`
}

func Response(ctx *gin.Context, code int, message string, data interface{}) {
	println("Response called: code=", code, "message=", message)
	ctx.JSON(code, ResponseData{
		Code:    code,
		Message: message,
		Data:    data,
	})
}
