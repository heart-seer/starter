package util

import (
	"time"

	"github.com/dgrijalva/jwt-go"
)

// JWT 密钥（应该更复杂并保存在环境变量中）
var jwtKey = []byte("your-secret-key")

// 声明 Token 的结构体

type Claims struct {
	UserName string `json:"user_name"`
	jwt.StandardClaims
}

//

func GenerateJWT(userName string) (string, error) {
	// 设置 Token 过期时间为 1小时
	expirationTime := time.Now().Add(1 * time.Hour)
	claims := &Claims{
		UserName: userName,
		StandardClaims: jwt.StandardClaims{
			ExpiresAt: expirationTime.Unix(),
			Issuer:    "your-app-name",
		},
	}

	// 创建 token
	token := jwt.NewWithClaims(jwt.SigningMethodHS256, claims)

	// 使用密钥签名 Token
	tokenString, err := token.SignedString(jwtKey)
	if err != nil {
		return "", err
	}
	return tokenString, nil
}

// 解析 JWT Token

func ParseToken(tokenString string) (*Claims, error) {
	// 解析 token
	token, err := jwt.ParseWithClaims(tokenString, &Claims{}, func(token *jwt.Token) (interface{}, error) {
		return jwtKey, nil
	})

	if err != nil {
		return nil, err
	}

	// 验证 Token 的合法性并返回 Claims
	if claims, ok := token.Claims.(*Claims); ok && token.Valid {
		return claims, nil
	}
	return nil, err
}
