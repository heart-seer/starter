package middleware

import (
	"library7/util"
	"library7/utils"
	"strings"

	"github.com/gin-gonic/gin"
)

//验证 JWT Token 中间件

func TokenAuthMiddleware() gin.HandlerFunc {
	return func(c *gin.Context) {
		tokenString := c.GetHeader("Authorization")
		if tokenString == "" {
			utils.Response(c, 401, "请求未携带 Token", nil)
			c.Abort()
			return
		}

		token := strings.TrimPrefix(tokenString, "Bearer ")
		claims, err := util.ParseToken(token)
		if err != nil {
			utils.Response(c, 401, "Token 无效", nil)
			c.Abort()
			return
		}

		//c.Set("userID", claims.UserID)
		c.Set("userID", claims.UserName)
		c.Next()
	}
}
