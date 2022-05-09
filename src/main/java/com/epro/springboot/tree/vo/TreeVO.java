package com.epro.springboot.tree.vo;

import com.epro.springboot.util.TreeNode;
import lombok.Data;

@Data
public class TreeVO extends TreeNode<TreeVO> {
    private String name;
}
