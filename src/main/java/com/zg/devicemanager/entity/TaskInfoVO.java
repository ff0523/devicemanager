package com.zg.devicemanager.entity;

import com.diboot.core.binding.annotation.BindEntity;
import com.diboot.core.binding.annotation.BindField;

public class TaskInfoVO extends TaskInfo{
    @BindField(entity = DeviceInfo.class, field = "DEVICE_CODE", condition = "this.DEVICE_ID = DEVICE_INFO.DEVICE_ID")
    private String deviceCode;

    @BindField(entity = DeviceInfo.class, field = "DEVICE_NAME", condition = "this.DEVICE_ID = DEVICE_INFO.DEVICE_ID")
    private String deviceName;
}
