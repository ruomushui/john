package com.epro.springboot.tree.service.impl;

import com.epro.springboot.tree.entity.Tree;
import com.epro.springboot.tree.mapper.TreeMapper;
import com.epro.springboot.tree.service.TreeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.epro.springboot.tree.vo.TreeVO;
import com.epro.springboot.util.TreeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 树形 服务实现类
 * </p>
 *
 * @author zy
 * @since 2022-04-29
 */
@Service
public class TreeServiceImpl extends ServiceImpl<TreeMapper, Tree> implements TreeService {

    @Override
    public List<TreeVO> getTreeVO(Long id) {
        List<Tree> trees = list();
        List<TreeVO> vos = trees.stream().map(tree -> {
            TreeVO vo = new TreeVO();
            BeanUtils.copyProperties(tree,vo);
            return vo;
        }
            ).collect(Collectors.toList());
        return TreeUtil.build(vos,null);
    }
}
