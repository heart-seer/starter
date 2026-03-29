package controller

import (
	"library7/model/entity"
	"library7/service"
	"library7/utils"
	"time"

	"github.com/gin-gonic/gin"
)

func GetAllCategory(a *gin.Context) {
	list, err := service.GetAllCategory()
	if err != nil {
		utils.Response(a, 400, "查询失败", nil)
		return
	}
	utils.Response(a, 200, "查询成功", list)
}

func AddCategory(a *gin.Context) {
	var cate entity.Category
	err := a.ShouldBindJSON(&cate)
	if err != nil {
		utils.Response(a, 400, "参数错误", nil)
		return
	}

	if cate.Name == "" {
		utils.Response(a, 400, "分类名不能为空", nil)
		return
	}
	cate.ID = time.Now().Format("20060102150405")
	_, err = service.AddCategory(&cate)
	if err != nil {
		utils.Response(a, 400, "添加失败", nil)
		return
	}
	utils.Response(a, 200, "添加成功", cate)
}

func UpdateCategory(a *gin.Context) {
	var cate entity.Category
	err := a.ShouldBindJSON(&cate)
	if err != nil {
		utils.Response(a, 400, "参数错误", nil)
		return
	}

	_, err = service.UpdateCategory(&cate)
	if err != nil {
		utils.Response(a, 400, "更新失败", nil)
		return
	}
	utils.Response(a, 200, "更新成功", cate)
}

func DeleteCategory(a *gin.Context) {
	id := a.Query("id")
	err := service.DeleteCategory(id)
	if err != nil {
		utils.Response(a, 400, "删除失败", nil)
		return
	}
	utils.Response(a, 200, "删除成功", nil)
}
