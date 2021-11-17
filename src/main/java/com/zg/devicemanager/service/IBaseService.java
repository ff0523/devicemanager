package com.zg.devicemanager.service;

import com.baomidou.mybatisplus.extension.service.IService;

public interface IBaseService<T> extends IService<T> {

    default int insert(T entity) {
        return this.getBaseMapper().insert(entity);
    }
}
