package com.jy73.basic.service;

import com.jy73.basic.response.CommonResult;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public enum CommonResponse {
        SUCCESS("성공하였습니다.");

        String msg;

        CommonResponse(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }

    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        result.setSuccess(true);
        result.setMsg(CommonResponse.SUCCESS.getMsg());
        return result;
    }

    public CommonResult getFailResult(String msg) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }
}
