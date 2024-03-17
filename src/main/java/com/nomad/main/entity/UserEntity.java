package com.nomad.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user", autoResultMap = true)
public class UserEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String openId;
    private String unionId;

    private String nickname;
    private String signature;
    private Boolean gender;
    private Long phone;
    private String email;
    private String description;

    private Long createAt;
    private Long updateAt;
}
