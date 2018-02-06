package com.yy.bjtours.modules.server.web;

import com.yy.bjtours.common.persistence.Page;
import com.yy.bjtours.common.web.BaseController;
import com.yy.bjtours.modules.server.entity.PosServiceInfo;
import com.yy.bjtours.modules.server.service.PosServiceInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统服务Controller
 * Created by user on 2016/1/12.
 */
@Controller
@RequestMapping(value = "${adminPath}/iposService")
public class PosServiceInfoController extends BaseController {
    @Resource
    private PosServiceInfoService posServiceInfoService;

    /**
     * 查询服务配置信息
     *
     * @param posServiceInfo
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"", "list"})
    public String findServiceList(PosServiceInfo posServiceInfo, Model model, HttpServletRequest request, HttpServletResponse response) {
        Page<PosServiceInfo> queryPage = new Page<PosServiceInfo>(request, response);
        Page<PosServiceInfo> page = posServiceInfoService.findPage(queryPage, posServiceInfo);
        model.addAttribute("page", page);
        return "/modules/server/iposServiceInfoList";
    }

    /**
     * 添加系统服务页面跳转
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "addFrom")
    public String iposServiceAddFrom(HttpServletRequest request, HttpServletResponse response) {
        return "/modules/server/iposServiceAddForm";
    }

    /**
     * 查看服务页面跳转
     *
     * @param id
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "viewFrom/{id}")
    public String iposServiceViewFrom(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        PosServiceInfo serviceInfo = posServiceInfoService.get(id);
        if (serviceInfo == null) {
            logger.info("##iposServiceViewFrom 查询服务信息不存在");
            addMessage(redirectAttributes, "服务信息不存在");
            return "redirect:" + adminPath + "/iposService/list";
        }
        model.addAttribute("posServiceInfo", serviceInfo);
        return "/modules/server/iposServiceViewForm";
    }
    @RequiresPermissions("ipos:sysserver:add")
    @RequestMapping(value = "saveService")
    public String saveServiceInfo(PosServiceInfo posServiceInfo, RedirectAttributes redirectAttributes) {
        try {
            posServiceInfo.setIsNewRecord(true);
            posServiceInfoService.save(posServiceInfo);
            addMessage(redirectAttributes, "服务保存成功！");
        } catch (Exception e) {
            logger.error("##saveServiceInfo 系统服务保存系统异常，异常信息：{}", e);
            addMessage(redirectAttributes, "服务保存系统异常！");
        }
        return "redirect:" + adminPath + "/iposService/list";
    }

    @RequiresPermissions("ipos:sysserver:modify")
    @RequestMapping(value = "modifyService")
    public String modifyServiceInfo(PosServiceInfo posServiceInfo, RedirectAttributes redirectAttributes) {
        try {
            posServiceInfoService.save(posServiceInfo);
            addMessage(redirectAttributes, "服务更新成功！");
        } catch (Exception e) {
            logger.error("##saveServiceInfo 系统服务更新系统异常，异常信息：{}", e);
            addMessage(redirectAttributes, "服务更新系统异常！");
        }
        return "redirect:" + adminPath + "/iposService/list";
    }
}
