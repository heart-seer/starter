//package com.example.studentmanager.mapper;
//
//import com.example.studentmanager.model.entity.UserEntity;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import java.util.List;
//
//@Mapper
//public interface UserMapper {
//
////    UserEntity selectByUsername(@Param("username") String username);
//UserEntity selectByUsername(String username);
//
//    List<UserEntity> selectAll();
//
//    UserEntity selectById(@Param("userId") Integer userId);
//
//    int insert(UserEntity user);
//
//    int update(UserEntity user);
//    int deleteByUsername(@Param("username") String username);
//}
package com.example.studentmanager.mapper;

import com.example.studentmanager.model.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    UserEntity selectByUsername(String username);

    List<UserEntity> selectAll();

    UserEntity selectById(@Param("userId") Integer userId);

    int insert(UserEntity user);

    int update(UserEntity user);

    int deleteByUsername(@Param("username") String username);
    // 更新登录错误次数
    void updateLoginFailCount(UserEntity user);
}