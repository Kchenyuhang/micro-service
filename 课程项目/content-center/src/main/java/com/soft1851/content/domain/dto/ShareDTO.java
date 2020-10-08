package com.soft1851.content.domain.dto;

import com.soft1851.content.domain.entity.Share;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("分享详情，带发布人昵称")
public class ShareDTO {

    private Integer id;

    private Integer userId;

    private String title;

    private Date createTime;

    private Date updateTime;

    private Boolean isOriginal;

    private String author;

    private String cover;

    private String summary;

    private Integer price;

    private String downloadUrl;

    private Boolean showFlag;

    private Integer buyCount;

    private String auditStatus;

    private String reason;

//    @ApiModelProperty(name = "share", value = "分享资源信息")
//    private Share share;

    @ApiModelProperty(name = "nickName", value = "发布人昵称")
    private String wxNickName;
}
