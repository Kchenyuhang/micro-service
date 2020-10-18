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
 * @Date 2020/10/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("投稿传参DTO")
public class ShareRequestDTO {
    @ApiModelProperty(name = "userId",value = "投稿用户id")
    private Integer userId;

    @ApiModelProperty(name = "title",value = "标题")
    private String title;

    @ApiModelProperty(name = "isOriginal",value = "是否原创：false否，true是")
    private Boolean isOriginal;

    @ApiModelProperty(name = "author",value = "作者")
    private String author;

    @ApiModelProperty(name = "summary",value = "描述")
    private String summary;

    @ApiModelProperty(name = "price",value = "兑换所需积分")
    private Integer price;

    @ApiModelProperty(name = "downloadUrl",value = "下载地址")
    private String downloadUrl;

}
