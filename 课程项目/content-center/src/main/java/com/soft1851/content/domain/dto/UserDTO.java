package com.soft1851.content.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("接收用户中心发送的User信息DTO")
public class UserDTO {
    @ApiModelProperty(name = "id", value = "用户id")
    private Integer id;

    @ApiModelProperty(name = "wxId", value = "微信Id")
    private String wxId;

    @ApiModelProperty(name = "wxNickname", value = "微信昵称")
    private String wxNickname;

    /**
     * 角色
     */
    @ApiModelProperty(name = "roles", value = "角色")
    private String roles;

    /**
     * 头像地址
     */
    @ApiModelProperty(name = "avatarUrl", value = "头像地址")
    private String avatarUrl;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "用户创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(name = "updateTime", value = "用户信息更新时间")
    private Date updateTime;

    /**
     * 积分
     */
    @ApiModelProperty(name = "bonus", value = "积分")
    private Integer bonus;
}
