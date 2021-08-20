package cn.huaming.sentinel;//package cn.huaming.sentinel;
//
//import cn.huaming.entity.User;
//import cn.huaming.fastjson.Result;
//import cn.huaming.fastjson.ScProperty;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/sentinel")
//@Slf4j
//public class TestController {
//
//    @Autowired
//    private TestService testService;
//
//    @RequestMapping(value = "/test")
//    @ResponseBody
//    @ScProperty(includes ="name")
//    public String go(){
//        return testService.hello(1000);
//    }
//
//
//
//}