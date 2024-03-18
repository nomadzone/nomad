package com.nomad.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-17
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user", autoResultMap = true)
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Posts implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String title;

    private String content;

    private String resources;

    private Long createdAt;

    private Long updatedAt;

    private Float latitude;

    private Float longitude;

    private String locationName;

    private String imageUrl;


}
