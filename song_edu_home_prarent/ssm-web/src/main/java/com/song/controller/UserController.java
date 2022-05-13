package com.song.controller;

import com.github.pagehelper.PageInfo;
import com.song.domain.*;
import com.song.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页获取用户数据&条件查询用户数据
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO) {

        try {
            PageInfo allUserByPage = userService.findAllUserByPage(userVO);
            return new ResponseResult(true, 200, "响应成功", allUserByPage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用户状态设置
     * ENABLE能登录，DISABLE不能登录
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam Integer id, @RequestParam String status) {
        try {
            if ("ENABLE".equalsIgnoreCase(status)) {
                status = "DISABLE";
            } else {
                status = "ENABLE";
            }
            userService.updateUserStatus(id, status);
            return new ResponseResult(true, 200, "响应成功", status);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用户登录
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) {
        try {
            User user1 = userService.login(user);
            if (user1 != null) {
                //保存access_token
                Map<String, Object> map = new HashMap<>();
                String access_token = UUID.randomUUID().toString();
                map.put("access_token", access_token);
                map.put("user_id", user1.getId());

                HttpSession session = request.getSession();
                session.setAttribute("user_id",user1.getId());
                session.setAttribute("access_token",access_token);

                return new ResponseResult(true, 200, "响应成功", map);
            } else {
                return new ResponseResult(true, 400, "用户名密码错误", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用户注册
     */
    @RequestMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        try {
            userService.register(user);
            return new ResponseResult(true, 200, "响应成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除用户
     */
    @RequestMapping("/deleteUser")
    public ResponseResult deleteUser(User user) {
        try {
            userService.deleteUser(user);
            return new ResponseResult(true, 200, "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据ID查询用户当前角色
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(@RequestParam Integer id) {
        try {
            List<Role> userRelationRoleById = userService.findUserRelationRoleById(id);
            return new ResponseResult(true, 200, "删除成功", userRelationRoleById);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 为用户分配角色信息
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserRoleVO userRoleVO) {
        try {
            userService.userContextRole(userRoleVO);
            return new ResponseResult(true, 200, "响应成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户权限
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {
        try {
            //获取请求头中的 token
            String header_token = request.getHeader("Authorization");

            //获取session中的access_token
            String session_token = (String) request.getSession().getAttribute("access_token");

            //判断 
            if (header_token.equals(session_token)) {
                int user_id = (Integer) request.getSession().getAttribute("user_id");
                ResponseResult result = userService.getUserPermissions(user_id);
                return result;
            } else {
                ResponseResult result = new ResponseResult(false, 400, "获取失败", "");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
