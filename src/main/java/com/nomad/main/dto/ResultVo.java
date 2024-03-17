package com.nomad.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo {

    public static final String SUCCEED = "succeed";

    public static ResultVo success(Object data) {
        return ResultVo.builder()
                .code(0)
                .msg(ResultVo.SUCCEED)
                .data(data)
                .build();
    }

    private int code;
    private String msg;
    private Object data;

}
