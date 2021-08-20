package cn.huaming.fastjson;

import cn.huaming.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fastjson")
@Slf4j
public class TestFilterFieldController {

    @RequestMapping(value = "/test")
    @ResponseBody
    @ScProperty(includes ="name")
    public Result go(){
        return go2();
    }

    @RequestMapping(value = "/test2")
    @ResponseBody
    @ScProperty(includes ="pwd")
    public Result go2(){
        User user = new User();
        user.setName("2");
        user.setPwd("2");
        return new Result("0000","success",user);
    }

}