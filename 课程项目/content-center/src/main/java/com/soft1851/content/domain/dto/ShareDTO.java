package com.soft1851.content.domain.dto;

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
public class ShareDTO {

    private Integer id;

    private Integer userId;

    private String title;

    private Date createTime;

    private Date updateTime;

    private Integer isOriginal;

    private String author;

    private String cover;

    private String summary;

    private Integer price;

    private String downloadUrl;

    private Integer showFlag;

    private Integer buyCount;

    private String auditStatus;

    private String reason;

    private String wxNickname;
}
