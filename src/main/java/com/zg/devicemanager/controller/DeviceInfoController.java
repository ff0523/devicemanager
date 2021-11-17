package com.zg.devicemanager.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zg.devicemanager.common.Result;
import com.zg.devicemanager.entity.DeviceInfo;
import com.zg.devicemanager.service.IDeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-10-13
 */
@Controller
@RequestMapping("/device")
public class DeviceInfoController extends BaseController<IDeviceInfoService, DeviceInfo> {
    @Autowired
    IDeviceInfoService iDeviceInfoService;

    @RequestMapping("/init")
    public String init(HttpServletRequest request, Model model) {
        return "/html/device.html";
    }

    @PostMapping({"/insert"})
    @ResponseBody
    public String insert(HttpServletRequest request, Model model){
        String data = request.getParameter("data");
        DeviceInfo entity = JSON.parseObject(data,new TypeReference<DeviceInfo>(){});
        int id = iDeviceInfoService.insert(entity);
        return JSONObject.toJSONString(Result.success(null));
    }
}
