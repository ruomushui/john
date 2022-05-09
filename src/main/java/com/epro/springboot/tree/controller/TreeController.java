package com.epro.springboot.tree.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.epro.springboot.tree.service.TreeService;
import com.epro.springboot.tree.vo.TreeVO;
import com.epro.springboot.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 树形 前端控制器
 * </p>
 *
 * @author zy
 * @since 2022-04-29
 */
@RestController
@RequestMapping("/tree")
public class TreeController {

    @Autowired
    TreeService treeService;
    @GetMapping("/{id}")
    public R<List<TreeVO>> getUser(@PathVariable("id") Long id){
        return R.ok(treeService.getTreeVO(id));
    }
}

