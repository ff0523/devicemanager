package com.zg.devicemanager.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.sql.Timestamp;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wlf
 * @since 2021-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("DEVICE_INFO")
public class DeviceInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(value = "ID",type = IdType.AUTO)
    private String id;

    /**
     * 设备id
     */
    @TableField("DEVICE_ID")
    private String deviceId;

    /**
     * 设备编号
     */
    @TableField("DEVICE_CODE")
    private String deviceCode;

    /**
     * 设备名称
     */
    @TableField("DEVICE_NAME")
    private String deviceName;

    /**
     * 设备状态,0:不可用,1:可用
     */
    @TableField("DEVICE_STATUS")
    private Integer deviceStatus;

    /**
     * 创建时间
     */
    @TableField(value = "ADD_TIME",fill = FieldFill.INSERT)
    private LocalDateTime addTime;

    /**
     * 创建用户
     */
    @TableField("ADD_USER")
    private String addUser;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 更新用户
     */
    @TableField("UPDATE_USER")
    private String updateUser;

    /**
     * 是否删除
     */
    @TableField("STATUS")
    private String status;
}
