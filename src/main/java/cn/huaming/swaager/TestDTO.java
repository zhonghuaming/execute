package cn.huaming.swaager;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "TestDTO", description = "测试DTO")
public class TestDTO {


    @ApiModelProperty(value = "测试")
    private String testString;


}
