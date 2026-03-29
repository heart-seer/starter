package controller

import (
	"fmt"
	"library7/model/entity"
	"library7/service"
	"library7/utils"
	"net/http"

	"github.com/gin-gonic/gin"
)

func UserLogin(a *gin.Context) {
	var user entity.User
	err := a.ShouldBindJSON(&user)
	if err != nil {
		fmt.Println("参数解析失败:", err)
		utils.Response(a, 400, "参数解析失败", nil)
		return
	}

	if user.Username == "" {
		utils.Response(a, 400, "用户名不能为空", nil)
		return
	}
	if user.Password == "" {
		utils.Response(a, 400, "密码不能为空", nil)
		return
	}

	token, err := service.UserLogin(user)
	if err != nil {
		fmt.Println("登录业务失败:", err)
		utils.Response(a, 400, err.Error(), nil)
		return
	}
	fmt.Println("登录成功，生成 token:", token)
	utils.Response(a, 200, "登录成功", map[string]interface{}{
		"token": token,
	})
}

// 注册
func UserRegister(c *gin.Context) {
	var user entity.User
	if err := c.ShouldBindJSON(&user); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"msg": "参数错误"})
		return
	}

	err := service.UserRegister(user)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"msg": err.Error()})
		return
	}

	c.JSON(http.StatusOK, gin.H{"msg": "注册成功"})
}
