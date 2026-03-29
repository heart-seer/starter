package service

import (
	"errors"
	"library7/model/entity"
	"library7/repository"
	"library7/util"
	"regexp"
	"strings"
	"time"
)

func UserLogin(user entity.User) (string, error) {
	// 1. 非空校验
	if strings.TrimSpace(user.Username) == "" {
		return "", errors.New("用户名不能为空")
	}
	if strings.TrimSpace(user.Password) == "" {
		return "", errors.New("密码不能为空")
	}

	// 2. 查询用户
	dbUser, err := repository.UserLogin(user.Username)
	if err != nil {
		return "", errors.New("用户不存在")
	}

	// 3. 已经错3次，直接拒绝登录
	if dbUser.LoginAttempts >= 3 {
		return "", errors.New("密码连续错误3次，账号已锁定")
	}

	// 4. 密码错误
	if dbUser.Password != user.Password {
		dbUser.LoginAttempts++ // 错误次数+1
		repository.UpdateUserLoginInfo(dbUser)

		if dbUser.LoginAttempts >= 3 {
			return "", errors.New("密码连续错误3次，账号已锁定")
		}
		return "", errors.New("密码错误")
	}

	// 5. 登录成功，重置错误次数
	dbUser.LoginAttempts = 0
	repository.UpdateUserLoginInfo(dbUser)

	// 6. 生成token
	token, err := util.GenerateJWT(dbUser.ID)
	if err != nil {
		return "", err
	}

	return token, nil
}

// 注册
func UserRegister(user entity.User) error {
	// 1. 用户名非空
	if user.Username == "" {
		return errors.New("用户名不能为空")
	}

	//2.密码非空
	if user.Password == "" {
		return errors.New("密码不能为空")
	}

	//6-10 位
	if len(user.Password) < 6 || len(user.Password) > 10 {
		return errors.New("密码长度必须6-10位")
	}
	//必须 字母+数字
	hasLetter := regexp.MustCompile(`[a-zA-Z]`).MatchString(user.Password)
	hasDigit := regexp.MustCompile(`[0-9]`).MatchString(user.Password)
	if !hasLetter || !hasDigit {
		return errors.New("密码必须包含字母+数字")
	}

	// 3.用户名不能重复
	_, err := repository.UserLogin(user.Username)
	if err == nil {
		return errors.New("用户名已存在")
	}

	user.ID = time.Now().Format("20060102150405")
	user.LoginAttempts = 0

	return repository.UserRegister(&user)
}
