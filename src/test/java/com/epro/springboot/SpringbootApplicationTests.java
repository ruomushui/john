package com.epro.springboot;

import com.epro.springboot.user.entity.User;
import com.epro.springboot.user.mapper.UserMapper;
import com.epro.springboot.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
        List<User> list = new ArrayList<>();

        for (int i = 6; i < 10; i++) {
            User user = new User();
            user.setName("张三丰");
            user.setId((long) i);
            list.add(user);
        }
        userService.updateBatchById(list);
    }

}
