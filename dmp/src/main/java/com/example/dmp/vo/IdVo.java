package com.example.dmp.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author renqy
 * @version V1.0
 * @ClassName IdVo
 * @Description
 * @date 2019/3/12 9:32
 * @since V1.0
 */
@Data
@ApiModel("参数Vo")
public class IdVo {

    @NotEmpty(message = "参数不能为空")
    @ApiModelProperty(value = "参数",example = "123456789",required = true)
    private String id;

    public IdVo() {
    }

    public IdVo(String str) {
        this.id = str;
    }
}
