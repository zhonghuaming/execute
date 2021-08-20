package cn.huaming.swaager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(tags = "测试接口", description = "提供用户相关的 Rest API")
public class TestController {

    @ApiOperation(value = "ts", notes = "test notes")
    @RequestMapping(value = "/ts", method = RequestMethod.POST)
    public TestDTO save() {
        return new TestDTO();
    }
}