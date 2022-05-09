package com.epro.springboot.tree.mapper;

import com.epro.springboot.tree.entity.Tree;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 树形 Mapper 接口
 * </p>
 *
 * @author zy
 * @since 2022-04-29
 */
@Mapper
public interface TreeMapper extends BaseMapper<Tree> {

}
