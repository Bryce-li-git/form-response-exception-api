package com.bryceli.api.controller;

import com.bryceli.api.annotation.NotResponseBody;
import com.bryceli.api.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Bryce
 * @date 2020/7/27 12:28
 * @description 测试用控制器
 * @Version 1.0
 */
@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("/adduser")
    public String addUser(@RequestBody @Valid User user){
        return "UserService.add() success";
    }

    @GetMapping("/getUser")
    //加了这个注解的方法，我们就不做数据统一响应处理，返回类型是啥就是返回的啥
    @NotResponseBody
    public User getUser() {
        User user = new User();
        user.setId(1L);
        user.setAccount("12345678");
        user.setPassword("12345678");
        user.setEmail("123@qq.com");
        // 注意哦，这里是直接返回的User类型，并没有用ResultVO进行包装
        return user;
    }

}
