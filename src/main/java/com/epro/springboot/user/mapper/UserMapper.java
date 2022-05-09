package com.epro.springboot.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.epro.springboot.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//使用mybatis-plus增强接口
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}


