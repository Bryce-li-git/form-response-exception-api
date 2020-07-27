package com.bryceli.api.controller;

import com.bryceli.api.entity.User;
import com.bryceli.api.vo.ResultVO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
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
    public ResultVO<User> getUser() {
        User user = new User();
        user.setId(1L);
        user.setAccount("12345678");
        user.setPassword("12345678");
        user.setEmail("123@qq.com");
        return new ResultVO<>(user);
    }

}
