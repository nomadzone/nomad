package com.nomad.main.dto;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PostsVO implements Serializable {

    private static final long serialVersionUID=1L;


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

    private String imageNames;

    /**
     * 点赞数
     */
    private Integer likes;


}
