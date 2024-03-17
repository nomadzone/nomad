package com.nomad.main.dto;

import com.nomad.main.validation.OnCreate;
import com.nomad.main.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class UserDto {
    @NotNull(groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long id;

    @NotNull
    private String openId;

    @NotNull
    private String unionId;

    private String nickname;

    @NotNull
    private String signature;

    private Boolean gender;

    @NotNull
    private Long phone;

    private String email;

    private String description;
}
