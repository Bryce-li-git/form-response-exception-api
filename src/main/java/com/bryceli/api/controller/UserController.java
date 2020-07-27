package com.bryceli.api.controller;

import com.bryceli.api.entity.User;
import com.bryceli.api.vo.ResultVO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/getuser")
    public String addUser(@RequestBody @Valid User user, BindingResult bindingResult){
        for(ObjectError objectError:bindingResult.getAllErrors()){
            return objectError.getDefaultMessage();
        }
        return "UserService.add() success";
    }

}
