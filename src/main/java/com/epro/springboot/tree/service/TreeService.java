package com.epro.springboot.tree.service;

import com.epro.springboot.tree.entity.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.epro.springboot.tree.vo.TreeVO;

import java.util.List;

/**
 * <p>
 * 树形 服务类
 * </p>
 *
 * @author zy
 * @since 2022-04-29
 */
public interface TreeService extends IService<Tree> {

    List<TreeVO> getTreeVO(Long id);
}
