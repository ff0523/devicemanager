package com.zg.devicemanager.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zg.devicemanager.common.Result;
import com.zg.devicemanager.service.IBaseService;
import com.zg.devicemanager.utils.WrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class BaseController<S extends IBaseService,E> {
    @Autowired
    private S iBaseService;

    @PostMapping({"/save"})
    public boolean save(E entity){
        return iBaseService.save(entity);
    }

    @PostMapping({"/saveBatch"})
    public boolean saveBatch(List<E> entityList){
        return iBaseService.saveBatch(entityList);
    }

    @PostMapping({"/saveBatchByCount"})
    public boolean saveBatchByCount(List<E> entityList,int batchSize){
        return iBaseService.saveBatch(entityList,batchSize);
    }

    @PostMapping({"/saveOrUpdateBatch"})
    public boolean saveOrUpdateBatch(List<E> entityList){
        return iBaseService.saveOrUpdateBatch(entityList);
    }

    @PostMapping({"/saveOrUpdateBatchByCount"})
    public boolean saveOrUpdateBatchByCount(List<E> entityList,int batchSize){
        return iBaseService.saveOrUpdateBatch(entityList,batchSize);
    }

    @PostMapping({"/removeById"})
    public boolean removeById(String id){
        return iBaseService.removeById(id);
    }

    @PostMapping({"/removeByMap"})
    public boolean removeByMap(Map<String, Object> columnMap){
        return iBaseService.removeByMap(columnMap);
    }

    @PostMapping({"/removeByIds"})
    public boolean removeByIds(List<String> ids){
        return iBaseService.removeByIds(ids);
    }

    @PostMapping({"/updateById"})
    public boolean updateById(E entity){
        return iBaseService.updateById(entity);
    }

    @PostMapping({"/updateBatchById"})
    public boolean updateBatchById(List<E> entitys){
        return iBaseService.updateBatchById(entitys);
    }

    @PostMapping({"/updateBatchByIdAndCount"})
    public boolean updateBatchByIdAndCount(List<E> entitys, int batchSize){
        return iBaseService.updateBatchById(entitys,batchSize);
    }

    @PostMapping({"/saveOrUpdate"})
    public boolean saveOrUpdate(E entity) throws Exception {
        return iBaseService.saveOrUpdate(entity);
    }

    @PostMapping({"/getById"})
    public E getById(String id) throws Exception {
        return (E) iBaseService.getById(id);
    }

    @PostMapping({"/listByIds"})
    public List<E> listByIds(List<String> ids) throws Exception {
        return iBaseService.listByIds(ids);
    }

    @PostMapping({"/listByMap"})
    public List<E> listByMap(Map<String, Object> columnMap) throws Exception {
        return iBaseService.listByMap(columnMap);
    }

    @PostMapping({"/list"})
    @ResponseBody
    public String list() {
        List<E> list = iBaseService.list();
        return JSONObject.toJSONString(Result.success(list));
    }

    @PostMapping({"/listByPage"})
    @ResponseBody
    public String listByPage(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String searchParam = request.getParameter("data");
        QueryWrapper queryWrapper = WrapperUtil.parseWhereSql(searchParam);
        int page = (Integer.parseInt(start) / Integer.parseInt(length)) + 1;

        PageHelper.startPage(page, Integer.parseInt(length));
        List<E> list = iBaseService.list(queryWrapper);
        PageInfo<E> pageInfo = new PageInfo<>(list);
        long drow = Long.parseLong(request.getParameter("draw"));//页码
        long iTotalDisplayRecords = pageInfo.getTotal();//总条数
        return JSONObject.toJSONString(Result.success(list,drow,iTotalDisplayRecords));
    }

    @GetMapping({"/listMaps"})
    public List<Map<String, Object>> listMaps() {
        return iBaseService.listMaps();
    }

    @GetMapping({"/listObjs"})
    public Object listObjs() {
        return iBaseService.listObjs();
    }

    @GetMapping({"/count"})
    public int count() {
        return iBaseService.count();
    }
}
