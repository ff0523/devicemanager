package com.zg.devicemanager.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Timestamp;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.diboot.core.binding.annotation.BindEntity;
import com.diboot.core.binding.annotation.BindField;
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
@TableName("TASK_INFO")
public class TaskInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableField("ID")
    private String id;
    /**
     * 试验编号
     */
    @TableField("EXPERIMENT_CODE")
    private String experimentCode;

    /**
     * 设备编号
     */
    @TableField("DEVICE_ID")
    private String deviceID;

    /**
     * 任务状态,0:未开始,1:进行中,2:已结束
     */
    @TableField("TASK_STATUS")
    private Integer taskStatus;

    /**
     * 任务开始时间
     */
    @TableField("BEGIN_TIME")
    private Timestamp beginTime;

    /**
     * 任务进行时间
     */
    @TableField("CURR_TIME")
    private Timestamp currTime;

    /**
     * 任务试验人员
     */
    @TableField("TASK_EXECUTOR")
    private String taskExecutor;

    /**
     * 下一任务id
     */
    @TableField("NEXT_TASK_ID")
    private Integer nextTaskId;

    /**
     * 创建时间
     */
    @TableField("ADD_TIME")
    private Timestamp addTime;

    /**
     * 创建用户
     */
    @TableField("ADD_USER")
    private String addUser;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private Timestamp updateTime;

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
