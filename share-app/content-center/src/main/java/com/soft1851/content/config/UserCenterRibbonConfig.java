package com.soft1851.content.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbonconfig.RibbonConfig;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/25
 */
@Configuration
//@RibbonClient(name = "user-center", configuration = RibbonConfig.class)
//@RibbonClients(defaultConfiguration = RibbonConfig.class)
public class UserCenterRibbonConfig {
}
