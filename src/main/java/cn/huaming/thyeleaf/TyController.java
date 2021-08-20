package cn.huaming.thyeleaf;

import cn.huaming.entity.User;
import cn.huaming.fastjson.Result;
import cn.huaming.fastjson.ScProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
public class TyController {

    @GetMapping("/my/index")
    public String go(){
        return "index";
    }
}