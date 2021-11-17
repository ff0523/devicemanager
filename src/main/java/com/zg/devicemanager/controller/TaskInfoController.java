package com.zg.devicemanager.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zg.devicemanager.common.Result;
import com.zg.devicemanager.entity.TaskInfo;
import com.zg.devicemanager.entity.TaskInfoVO;
import com.zg.devicemanager.service.ITaskInfoService;
import com.zg.devicemanager.utils.WrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-10-13
 */
@Controller
@RequestMapping("/task")
public class TaskInfoController extends BaseController<ITaskInfoService, TaskInfo> {
    @Autowired
    ITaskInfoService iService;

    @RequestMapping("/init")
    public String init(HttpServletRequest request, Model model) {
        return "/html/task.html";
    }

//    @PostMapping({"/listByPage"})
//    @ResponseBody
//    public String listByPage(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
//        String start = request.getParameter("start");
//        String length = request.getParameter("length");
//        String searchParam = request.getParameter("data");
//        QueryWrapper queryWrapper = WrapperUtil.parseWhereSql(searchParam);
//        int page = (Integer.parseInt(start) / Integer.parseInt(length)) + 1;
//
//        PageHelper.startPage(page, Integer.parseInt(length));
//        List<TaskInfo> list = iService.list(queryWrapper);
//        PageInfo<TaskInfo> pageInfo = new PageInfo<>(list);
//        return JSONObject.toJSONString(Result.success(list));
//    }
}
