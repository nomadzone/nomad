package com.nomad.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 城市
 * </p>
 *
 * @author alex
 * @since 2024-03-20
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "destination", autoResultMap = true)
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Destination implements Serializable {

    private static final long serialVersionUID = 1710912032660L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id" , description  = "id主键")
    private Long id;

    @TableField(value = "name")
    @Schema(name = "name" , description = "城市名称")
    private String name;

    @TableField(value = "province")
    @Schema(name = "province" , description = "省份")
    private String province;

    @TableField(value = "temperature_min")
    @Schema(name = "temperatureMin" , description = "最低温度")
    private BigDecimal temperatureMin;

    @TableField(value = "temperature_max")
    @Schema(name = "temperatureMax" , description = "最高温度")
    private BigDecimal temperatureMax;

    @TableField(value = "costliving_min")
    @Schema(name = "costlivingMin" , description = "月最低生活成本")
    private BigDecimal costlivingMin;

    @TableField(value = "costliving_max")
    @Schema(name = "costlivingMax" , description = "月最高生活成本")
    private BigDecimal costlivingMax;

    @TableField(value = "description")
    @Schema(name = "description" , description = "描述")
    private String description;

    @TableField(value = "cover")
    @Schema(name = "cover" , description = "封面图片")
    private String cover;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "created_at")
    @Schema(name = "createdAt" , description = "创建时间")
    private Date createdAt;

    @TableField(value = "created_by")
    @Schema(name = "createdBy" , description = "创建人")
    private Long createdBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "updated_at")
    @Schema(name = "updatedAt" , description = "修改时间")
    private Date updatedAt;

    @TableField(value = "updated_by")
    @Schema(name = "updatedBy" , description = "修改人")
    private Long updatedBy;

    @TableField(value = "del_flag")
    @Schema(name = "delFlag" , description = "删除标识")
    private String delFlag;


}
