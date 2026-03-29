package repository

import (
	"library7/config"
	"library7/model/entity"
)

// 获取所有图书分类列表
func GetAllCategory() ([]*entity.Category, error) {
	var list []*entity.Category
	// 查询 category 表中所有数据
	//存入 list 集合
	err := config.Connect().Table("category").Find(&list).Error
	if err != nil {
		return nil, err
	}
	return list, nil
}

// 添加一个新的图书分类
func AddCategory(cate *entity.Category) error {
	// 将分类信息插入 category 表
	err := config.Connect().Table("category").Create(cate).Error
	if err != nil {
		return err
	}
	return nil
}

// 根据分类ID 更新分类名称
func UpdateCategory(cate *entity.Category) error {
	return config.Connect().Table("category").Where("id = ?", cate.ID).Update("name", cate.Name).Error
}

// 根据分类ID 删除一个分类
func DeleteCategory(id string) error {
	return config.Connect().Table("category").Where("id = ?", id).Delete(nil).Error
}
