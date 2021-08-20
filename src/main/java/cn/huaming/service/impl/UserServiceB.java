package cn.huaming.service.impl;

import cn.huaming.entity.User;
import cn.huaming.repo.UserRepository;
import cn.huaming.service.IUserServiceB;
import com.alibaba.fastjson.JSON;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceB implements IUserServiceB {


    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Transactional
    public String createChild(String name) {
        Optional<User> byId = userRepository.findById(1L);
        if(byId.isPresent()){
            User user = byId.get();
            System.out.println(JSON.toJSONString(user));
        }
        User user = new User();
        user.setName("b");
        user.setAccount("b");
        user.setPwd("b");
        userRepository.save(user);
        if(1==1){
            throw new RuntimeException("xxx");
        }
        return "serviceB" ;
    }
}
