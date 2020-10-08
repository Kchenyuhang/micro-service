package com.soft1851.user.service.impl;

import com.soft1851.user.dao.BonusEventLogMapper;
import com.soft1851.user.domain.entity.BonusEventLog;
import com.soft1851.user.service.BonusEventLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/10/7
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BonusEventLogServiceImpl implements BonusEventLogService {
    private final BonusEventLogMapper bonusEventLogMapper;

    @Override
    public void insertLog(Integer userId) {
//        BonusEventLog log = BonusEventLog.builder()
//                .userId(userId)
//                .value().build();
    }
}
