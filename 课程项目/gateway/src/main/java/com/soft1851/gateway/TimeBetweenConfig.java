package com.soft1851.gateway;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/10/9
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeBetweenConfig {
    private LocalTime start;
    private LocalTime end;
}
