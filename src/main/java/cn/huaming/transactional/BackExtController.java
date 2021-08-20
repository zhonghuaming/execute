package cn.huaming.transactional;

import cn.huaming.service.impl.UserService;
import cn.huaming.service.impl.UserServiceB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/user")
@Slf4j
public class BackExtController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceB userServiceB;

    @RequestMapping(value = "/a1")
    @ResponseBody
    public String go(){
        String e = userService.createUser("e");
        log.trace(e);
        log.error("Er");
//        String b = userServiceB.createChild("b");
        return e;
    }

}