package cn.huaming.service.impl;

import cn.huaming.entity.User;
import cn.huaming.repo.UserRepository;
import cn.huaming.service.IUserService;
import com.alibaba.fastjson.JSON;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceB userServiceB;

    @Override
    @Transactional
    public String testUser(String name) {
        User testUser = new User();
        return "success";
    }

    @Override
    @Transactional
    public String createUser(String name) {
        User user = new User();
        user.setName("a");
        user.setAccount("a");
        user.setPwd("a");
        userRepository.save(user);
//        try {
//            createChild("b");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        return "serviceA";
    }

    @Override
    @Transactional
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
