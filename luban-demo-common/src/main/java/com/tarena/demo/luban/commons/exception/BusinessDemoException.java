package com.tarena.demo.luban.commons.exception;


import com.tarena.demo.luban.commons.restful.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessDemoException extends RuntimeException {

    private ResponseCode responseCode;

    public BusinessDemoException(ResponseCode responseCode, String message) {
        super(message);
        setResponseCode(responseCode);
    }

}
