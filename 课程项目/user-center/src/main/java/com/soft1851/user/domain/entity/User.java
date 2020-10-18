package com.soft1851.user.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@ApiModel("用户实体类")
public class User {

    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(name = "id", value = "主键id")
    private Integer id;

    @Column(name = "wx_id")
    @ApiModelProperty(name = "wxId", value = "微信Id")
    private String wxId;

    @Column(name = "wx_nickname")
    @ApiModelProperty(name = "wxNickname", value = "微信昵称")
    private String wxNickname;

    @Column(name = "roles")
    @ApiModelProperty(name = "roles", value = "角色")
    private String roles;

    @Column(name = "avatar_url")
    @ApiModelProperty(name = "avatarUrl", value = "头像地址")
    private String avatarUrl;

    @Column(name = "create_time")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    @Column(name = "update_time")
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date updateTime;

    @Column(name = "bonus")
    @ApiModelProperty(name = "bonus", value = "积分")
    private Integer bonus;
}
