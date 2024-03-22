package com.nomad.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo<T> {

    public static final String SUCCEED = "succeed";
    public static final String FAILED = "failed";

    /**
     * succeed result
     * @param data
     * @return
     */
    public static ResultVo success(Object data) {
        return ResultVo.builder()
                .code(0)
                .msg(ResultVo.SUCCEED)
                .data(data)
                .build();
    }

    /**
     * failed result
     * @param message
     * @return
     */
    public static ResultVo failed(String message) {
        ResultVo<Object> build = ResultVo.builder()
                .code(500)
                .msg(ResultVo.FAILED)
                .build();
        if(message != null) {
            build.setMsg(message);
        }
        return build;
    }

    private int code;
    private String msg;
    private T data;

}
