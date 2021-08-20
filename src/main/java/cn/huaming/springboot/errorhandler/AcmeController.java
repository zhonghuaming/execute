package cn.huaming.springboot.errorhandler;

import cn.huaming.service.impl.UserService;
import cn.huaming.service.impl.UserServiceB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acme")
@Slf4j
public class AcmeController {

    @RequestMapping(value = "/test")
    @ResponseBody
    public String go(){
        throw new RuntimeException();
    }

}