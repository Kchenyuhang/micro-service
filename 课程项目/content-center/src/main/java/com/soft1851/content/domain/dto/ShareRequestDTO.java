package com.soft1851.content.domain.dto;

import io.swagger.annotations.ApiModel;
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
@ApiModel("投稿")
public class ShareRequestDTO {
    private Integer userId;

    private String title;

    private Boolean isOriginal;

    private String author;

    private String summary;

    private Integer price;

    private String downloadUrl;

}
