//package com.example.studentmanager.service;
//
//import com.example.studentmanager.mapper.UserMapper;
//import com.example.studentmanager.model.entity.Result;
//import com.example.studentmanager.model.entity.UserEntity;
//import com.example.studentmanager.model.dto.user.UserDTO;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService {
//
//    private final UserMapper userMapper;
//
//    public UserService(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }
//
//    public Result getUserByUsername(String username) {
//        if (username == null || username.trim().isEmpty()) {
//            return Result.error("用户名不能为空！");
//        }
//
//        UserEntity user = userMapper.selectByUsername(username);
//
//        if (user == null) {
//            return Result.error("没找到这个用户名的用户！");
//        } else {
//            return Result.success(user);
//        }
//    }
//
//    public Result getAllUsers() {
//        List<UserEntity> userList = userMapper.selectAll();
//        if (userList.isEmpty()) {
//            return Result.error("暂无用户数据");
//        }
//        return Result.success(userList);
//    }
//
//    public Result getUserById(Integer userId) {
//        if (userId == null || userId <= 0) {
//            return Result.error("用户ID不能为空且必须大于0");
//        }
//
//        UserEntity user = userMapper.selectById(userId);
//        if (user == null) {
//            return Result.error("未找到该用户");
//        }
//        return Result.success(user);
//    }
//
//    public Result register(UserDTO dto) {
//        if (dto == null) return Result.error("参数不能为空");
//
//        String username = dto.getUsername();
//        String password = dto.getPassword();
//        String role = dto.getRole();
//
//        if (username == null || password == null || role == null) {
//            return Result.error("账号、密码、角色不能为空");
//        }
//
//        UserEntity existed = userMapper.selectByUsername(username);
//        if (existed != null) {
//            return Result.error("账号已注册，直接登录");
//        }
//
//        UserEntity user = new UserEntity(username, password, role);
//
//        int rows = userMapper.insert(user);
//        return rows > 0 ? Result.success("注册成功") : Result.error("注册失败");
//    }
//
//    public Result updateUser(Integer userId, UserDTO dto) {
//        if (userId == null || userId <= 0) {
//            return Result.error("用户ID不能为空且必须大于0");
//        }
//        if (dto == null) return Result.error("更新参数不能为空");
//
//        UserEntity existed = userMapper.selectById(userId);
//        if (existed == null) {
//            return Result.error("该用户不存在，无法更新");
//        }
//
//        UserEntity user = new UserEntity(userId, dto.getUsername(), dto.getPassword(), dto.getRole());
//
//        int rows = userMapper.update(user);
//        return rows > 0 ? Result.success("更新成功") : Result.error("更新失败");
//    }
//
//    public Result deleteUser(String username) {
//        if (username == null || username.trim().isEmpty()) {
//            return Result.error("用户名不能为空");
//        }
//
//        UserEntity existed = userMapper.selectByUsername(username);
//        if (existed == null) {
//            return Result.error("该用户不存在，无法删除");
//        }
//
//        int rows = userMapper.deleteByUsername(username);
//        return rows > 0 ? Result.success("删除成功") : Result.error("删除失败");
//    }
//
//    public Result login(String username, String password) {
//        if (username == null || password == null) {
//            return Result.error("账号或密码不能为空");
//        }
//
//        UserEntity user = userMapper.selectByUsername(username);
//        if (user == null) {
//            return Result.error("账号未注册");
//        }
//
//        if (!password.equals(user.getPassword())) {
//            return Result.error("密码错误");
//        }
//
//        return Result.success("登录成功");
//    }
//}
package com.example.studentmanager.service;

import com.example.studentmanager.mapper.UserMapper;
import com.example.studentmanager.model.dto.user.UserDTO;
import com.example.studentmanager.model.dto.user.UserRegisterDTO;
import com.example.studentmanager.model.entity.Result;
import com.example.studentmanager.model.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //通过用户名查询用户
    public Result getUserByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return Result.error("用户名不能为空！");
        }

        UserEntity user = userMapper.selectByUsername(username);

        if (user == null) {
            return Result.error("没找到这个用户名的用户！");
        }

        return Result.success(user);
    }

    //查询所有用户
    public Result getAllUsers() {
        List<UserEntity> userList = userMapper.selectAll();
        if (userList.isEmpty()) {
            return Result.error("暂无用户数据");
        }
        return Result.success(userList);
    }

    //通过ID查询用户
    public Result getUserById(Integer userId) {
        if (userId == null || userId <= 0) {
            return Result.error("用户ID不能为空且必须大于0");
        }

        UserEntity user = userMapper.selectById(userId);

        if (user == null) {
            return Result.error("未找到该用户");
        }

        return Result.success(user);
    }

    //注册
    public Result register(UserRegisterDTO dto) {
        //非空校验
        if (dto == null) {
            return Result.error("参数不能为空");
        }

        String username = dto.getUsername();
        String password = dto.getPassword();
        String role = dto.getRole();

        if (username == null || password == null || role == null) {
            return Result.error("账号、密码、角色不能为空");
        }

        if (username.isEmpty() || username.trim().isEmpty()) {
            return Result.error("用户名不能为空");
        }

        //用户名重复校验
        UserEntity existed = userMapper.selectByUsername(username);
        if (existed != null) {
            return Result.error("用户名已被注册，请更换其他用户名"); // 优化提示语，更清晰
        }

        //密码:字母+数字，6-10位
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$";
        if (!password.matches(passwordRegex)) {
            return Result.error("密码必须包含字母+数字，且长度在6-10位之间");
        }

        //创建用户
        UserEntity user = new UserEntity(dto);
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        //插入数据库
        int rows = userMapper.insert(user);

        return rows > 0 ? Result.success("注册成功") : Result.error("注册失败");
    }
    //更新
    public Result updateUser(Integer userId, UserDTO dto) {
        //非空校验
        if (userId == null || userId <= 0) {
            return Result.error("用户ID不能为空且必须大于0");
        }

        if (dto == null) {
            return Result.error("更新参数不能为空");
        }

        String username = dto.getUsername().trim();
        String password = dto.getPassword();
        String role = dto.getRole();

        if (username.isEmpty() || password == null || role == null) {
            return Result.error("账号、密码、角色不能为空");
        }

        //校验用户是否存在
        UserEntity existed = userMapper.selectById(userId);
        if (existed == null) {
            return Result.error("用户不存在，更新失败");
        }

        //重复校验
        UserEntity existUser = userMapper.selectByUsername(username);
        if (existUser != null && !existUser.getId().equals(userId)) {
            return Result.error("用户名已被占用，请更换");
        }

        //密码校验：字母+数字，6-10位
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$";
        if (!password.matches(regex)) {
            return Result.error("密码必须包含字母+数字，长度6-10位");
        }

        //更新
        UserEntity user = new UserEntity(userId, username, password, role);
        int rows = userMapper.update(user);

        return rows > 0 ? Result.success("更新成功") : Result.error("更新失败");
    }

    //删除
    public Result deleteUser(String username) {
        if (username == null || username.trim().isEmpty()) {
            return Result.error("用户名不能为空");
        }

        UserEntity existed = userMapper.selectByUsername(username);
        if (existed == null) {
            return Result.error("该用户不存在，无法删除");
        }

        int rows = userMapper.deleteByUsername(username);
        return rows > 0 ? Result.success("删除成功") : Result.error("删除失败");
    }

    //登录
    public Result login(String username, String password) {
        //非空校验
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            return Result.error("账号或密码不能为空");
        }

        username = username.trim();

        //查询用户
        UserEntity user = userMapper.selectByUsername(username);
        if (user == null) {
            return Result.error("账号未注册");
        }

        //登录失败次数
        if (user.getLoginFailCount() >= 3) {
            return Result.error("密码错误次数过多，账号已锁定，请联系管理员");
        }

        //密码判断
        if (!password.equals(user.getPassword())) {
            user.setLoginFailCount(user.getLoginFailCount() + 1);
            userMapper.updateLoginFailCount(user);

            return Result.error("密码错误，已错误 " + user.getLoginFailCount() + " 次，3次将锁定");
        }

        //登录成功 重置错误次数
        user.setLoginFailCount(0);
        userMapper.updateLoginFailCount(user);

        return Result.success("登录成功");
    }
}