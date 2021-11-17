package com.zg.devicemanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zg.devicemanager.entity.DeviceInfo;
import com.zg.devicemanager.mapper.DeviceInfoMapper;
import com.zg.devicemanager.service.IDeviceInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wlf
 * @since 2021-10-13
 */
@Service
public class DeviceInfoServiceImpl extends ServiceImpl<DeviceInfoMapper, DeviceInfo> implements IDeviceInfoService {

}
