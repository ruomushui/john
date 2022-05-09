package com.epro.springboot.user.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.epro.springboot.user.entity.User;
import com.epro.springboot.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(value = "user", tags = "用户管理")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    public R<User> getUser(@PathVariable("id") Long id){
        return R.ok(userService.getById(id));
    }

    @GetMapping("/page/{current}/{size}")
    public R<Page<User>> getUser(@PathVariable("current") Long current,@PathVariable("size") Long size){
        Page<User> page = new Page(current,size);
        return R.ok(userService.page(page, Wrappers.query()));
    }
}
