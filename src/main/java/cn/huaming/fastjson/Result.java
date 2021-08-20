package cn.huaming.fastjson;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Result<T> {
    private String errcode;
    private String errmsg;
    @ApiModelProperty(value = "数据对象")
    private T data;

    public Result(String errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public Result(String errcode, String errmsg, T data) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.data = data;
    }

}
