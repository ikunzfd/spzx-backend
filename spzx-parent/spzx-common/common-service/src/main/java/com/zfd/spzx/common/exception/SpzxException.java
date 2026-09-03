package com.zfd.spzx.common.exception;

import com.zfd.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

@Data
public class SpzxException extends RuntimeException {

    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public SpzxException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
}
