package com.zg.devicemanager.utils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.util.StringUtil;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

public class WrapperUtil {
    @Data
    public static class ParamEntity implements Serializable {
        private static final long serialVersionUID = -5099378457111419832L;
        /**
         * 数据库字段名
         */
        private String column;
        /**
         * 字段值
         */
        private String value;
        /**
         * 连接类型，如llike,equals,gt,ge,lt,le
         */
        private String type;
    }

    public static QueryWrapper parseWhereSql(String params){
        QueryWrapper queryWrapper = new QueryWrapper();
        if(StringUtil.isNotEmpty(params)){

            List<ParamEntity> paramList = JSON.parseArray(params,ParamEntity.class);
            if(paramList.size() > 0){
                for(ParamEntity param : paramList){
                    switch (param.getType()){
                        case "eq": {//=
                            if(StringUtil.isNotEmpty(param.getValue()) && StringUtil.isNotEmpty(param.getColumn())){
                                queryWrapper.eq(param.getColumn(),param.getValue());
                            }
                            break;
                        }
                        case "ne": {//!=
                            if(StringUtil.isNotEmpty(param.getValue()) && StringUtil.isNotEmpty(param.getColumn())){
                                queryWrapper.ne(param.getColumn(),param.getValue());
                            }
                            break;
                        }
                        case "like": {//like
                            if(StringUtil.isNotEmpty(param.getValue()) && StringUtil.isNotEmpty(param.getColumn())){
                                queryWrapper.like(param.getColumn(),param.getValue());
                            }
                            break;
                        }
                        case "likeLeft": {//like '%abc'
                            if(StringUtil.isNotEmpty(param.getValue()) && StringUtil.isNotEmpty(param.getColumn())){
                                queryWrapper.likeLeft(param.getColumn(),param.getValue());
                            }
                            break;
                        }
                        case "likeRight": {//like 'abc%'
                            if(StringUtil.isNotEmpty(param.getValue()) && StringUtil.isNotEmpty(param.getColumn())){
                                queryWrapper.likeRight(param.getColumn(),param.getValue());
                            }
                            break;
                        }
                        case "notLike": {//not like
                            if(StringUtil.isNotEmpty(param.getValue()) && StringUtil.isNotEmpty(param.getColumn())){
                                queryWrapper.notLike(param.getColumn(),param.getValue());
                            }
                            break;
                        }
                        case "gt": {//>
                            if(StringUtil.isNotEmpty(param.getValue()) && StringUtil.isNotEmpty(param.getColumn())){
                                queryWrapper.gt(param.getColumn(),param.getValue());
                            }
                            break;
                        }
                        case "lt": {//>=
                            if(StringUtil.isNotEmpty(param.getValue()) && StringUtil.isNotEmpty(param.getColumn())){
                                queryWrapper.lt(param.getColumn(),param.getValue());
                            }
                            break;
                        }
                        case "ge": {//<
                            if(StringUtil.isNotEmpty(param.getValue()) && StringUtil.isNotEmpty(param.getColumn())){
                                queryWrapper.ge(param.getColumn(),param.getValue());
                            }
                            break;
                        }
                        case "le": {//<=
                            if(StringUtil.isNotEmpty(param.getValue()) && StringUtil.isNotEmpty(param.getColumn())){
                                queryWrapper.le(param.getColumn(),param.getValue());
                            }
                            break;
                        }
                        case "orderByAsc": {//order by param asc,param is a list
                            if(StringUtil.isNotEmpty(param.getColumn())){
                                queryWrapper.orderByAsc(param.getColumn());
                            }
                            break;
                        }
                        case "orderByDesc": {//order by param desc,param is a list
                            if(StringUtil.isNotEmpty(param.getColumn())){
                                queryWrapper.orderByDesc(param.getColumn());
                            }
                            break;
                        }
                    }
                }
            }
        }
        return queryWrapper;
    }
}
