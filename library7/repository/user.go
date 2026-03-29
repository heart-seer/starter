package repository

import (
	"library7/config"
	"library7/model/entity"
)

// 用户登录：根据用户名查询用户信息
func UserLogin(username string) (*entity.User, error) {
	var user entity.User

	err := config.Connect().Table("user").Where("username = ?", username).First(&user).Error

	//查询出错
	if err != nil {
		return nil, err
	}

	//成功
	return &user, nil
}

func UpdateUserLoginInfo(user *entity.User) error {
	return config.Connect().
		Table("user").
		Where("id = ?", user.ID).
		Update("login_attempts", user.LoginAttempts).Error
}

// 注册
func UserRegister(user *entity.User) error {
	return config.Connect().Table("user").Create(user).Error
}
