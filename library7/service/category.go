package service

import (
	"errors"
	"library7/model/entity"
	"library7/repository"
)

// 获取所有图书分类
func GetAllCategory() ([]*entity.Category, error) {
	return repository.GetAllCategory()
}

// 添加分类
func AddCategory(cate *entity.Category) (*entity.Category, error) {
	//分类名称不能为空
	if cate.Name == "" {
		return nil, errors.New("分类名称不能为空")
	}

	// 检查分类名是否已存在
	categoryList, err := repository.GetAllCategory()
	if err != nil {
		return nil, err
	}
	for _, item := range categoryList {
		if item.Name == cate.Name {
			return nil, errors.New("该分类名称已存在")
		}
	}

	err = repository.AddCategory(cate)
	if err != nil {
		return nil, err
	}
	return cate, nil
}

// 更新分类
func UpdateCategory(cate *entity.Category) (*entity.Category, error) {
	//ID不能为空
	if cate.ID == "" {
		return nil, errors.New("分类ID不能为空")
	}

	//名称不能为空
	if cate.Name == "" {
		return nil, errors.New("分类名称不能为空")
	}

	//判断分类是否存在
	all, err := repository.GetAllCategory()
	if err != nil {
		return nil, err
	}
	exist := false
	for _, v := range all {
		if v.ID == cate.ID {
			exist = true
			break
		}
	}
	if !exist {
		return nil, errors.New("分类不存在，无法更新")
	}

	err = repository.UpdateCategory(cate)
	if err != nil {
		return nil, err
	}
	return cate, nil
}

// 删除分类
func DeleteCategory(id string) error {
	//ID不能为空
	if id == "" {
		return errors.New("分类ID不能为空")
	}

	//检查分类是否存在
	cateList, err := repository.GetAllCategory()
	if err != nil {
		return err
	}
	exists := false
	for _, item := range cateList {
		if item.ID == id {
			exists = true
			break
		}
	}
	if !exists {
		return errors.New("该分类不存在，无法删除")
	}

	return repository.DeleteCategory(id)
}
