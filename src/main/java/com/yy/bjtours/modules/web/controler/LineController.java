package com.yy.bjtours.modules.web.controler;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.yy.bjtours.common.config.Global;
import com.yy.bjtours.common.persistence.Page;
import com.yy.bjtours.common.utils.StringUtils;
import com.yy.bjtours.common.web.BaseController;
import com.yy.bjtours.modules.web.dto.TAdditionDto;
import com.yy.bjtours.modules.web.dto.TLineAdditionDto;
import com.yy.bjtours.modules.web.dto.TLineDto;
import com.yy.bjtours.modules.web.dto.TLinePicDto;
import com.yy.bjtours.modules.web.dto.TLinePriceDto;
import com.yy.bjtours.modules.web.dto.TPicDto;
import com.yy.bjtours.modules.web.service.AdditionService;
import com.yy.bjtours.modules.web.service.LineAdditionService;
import com.yy.bjtours.modules.web.service.LinePicService;
import com.yy.bjtours.modules.web.service.LineService;
import com.yy.bjtours.modules.web.service.PicService;
import com.yy.bjtours.modules.web.service.PriceService;

/**
 * @author: yy
 * @date: 2018年1月4日 下午3:32:26
 * @desc:
 */
@Controller
@RequestMapping("${adminPath}/line")
public class LineController extends BaseController {
    @Resource
    private LineService lineService;
    @Resource
    private PicService picService;
    @Resource
    private LinePicService linePicService;
    @Resource
    private PriceService priceService;
    @Resource
    private AdditionService additionService;
    @Resource
    private LineAdditionService lineAdditionService;
    
    /**
     * 线路列表页面
     */
    @RequestMapping(value = {"", "lineList"})
    public String listLine(TLineDto lineDto, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<TLineDto> page = new Page<TLineDto>(request, response);
        lineDto.setPage(page);
        Page<TLineDto> newpage = lineService.findPage(page, lineDto);
        model.addAttribute("page", newpage);
        return "/modules/web/LineList";
    }


    /**
     * 线路详情
     */
    @RequestMapping(value = "/form/{lineNo}")
    public String form(@PathVariable Long lineNo, Model model) {
    	TLineDto lineDto = new TLineDto();
    	lineDto.setLineNo(lineNo.intValue());
    	lineDto = lineService.queryLine(lineDto);
    	model.addAttribute("lineDto",lineDto);
        return "/modules/web/LineForm";
    }

    /**
     * 更新路线信息
     */
    @RequestMapping(value = "updateLine", method = RequestMethod.POST)
    public String updateLine(TLineDto lineDto, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
    	String lineNo = "";
    	if(lineDto != null){
    		lineNo = String.valueOf(lineDto.getLineNo());
    	}
        try {
        	lineService.updateLine(lineDto);
            addMessage(redirectAttributes, "更新路线信息成功");
        } catch (Exception e) {
            logger.error("##updateLine 更新路线信息系统异常,异常信息：{}", e);
            addMessage(redirectAttributes, "更新路线信息失败");
        }
        if(StringUtils.isBlank(lineNo)){
        	return "redirect:" + adminPath + "/line";
        }else{
        	return "redirect:" + adminPath + "/line/form/" + lineNo;
        }
    }
    
    /**
     * 更新路线图片信息
     */
    @RequestMapping(value = "updateLinePic/{lineNo}", method = RequestMethod.POST)
    public String updateLinePic(@PathVariable String lineNo, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    	TLineDto lineDto = new TLineDto();
    	lineDto.setLineNo(Integer.valueOf(lineNo));
    	lineDto = lineService.queryLine(lineDto);
    	
    	if(lineDto == null){
    		logger.error("没有找到路线");
    		addMessage(redirectAttributes, "没有找到对应路线！");
    		return "redirect:" + adminPath +"/line/lineList";
    	}
    	
    	String[] picNos = request.getParameterValues("picNo");
    	if(picNos == null){
    		logger.error("没有勾选图片");
    		addMessage(redirectAttributes, "没有勾选图片！");
    		return "redirect:" + adminPath +"/line/lineList";
    	}
    	
    	TLinePicDto tlpd = new TLinePicDto();
    	tlpd.setLineNo(Integer.valueOf(lineNo));
    	linePicService.delete(tlpd);
    	
    	for(String picNoStr : picNos){
    		tlpd = new TLinePicDto();
    		tlpd.setLineNo(Integer.valueOf(lineNo));
    		tlpd.setPicNo(Integer.valueOf(picNoStr));
    		linePicService.save(tlpd);
    		
    		String isMain = request.getParameter("isMain"+picNoStr);
    		TPicDto tpd = new TPicDto();
    		tpd.setPicNo(Integer.valueOf(picNoStr));
    		tpd.setIsMain(Integer.valueOf(isMain));
    		picService.updatePic(tpd);
    	}
    	addMessage(redirectAttributes, "更新路线图片成功");
    	return "redirect:" + adminPath + "/line/pic/" + lineNo;
    }

    /**
     * 路线图片编辑
     */
    @RequestMapping(value = "/pic/{lineNo}")
    public String pic(@PathVariable Long lineNo, Model model) {
    	String urlFront = Global.getConfig("pic.front.url");
    	List<TPicDto> allPicList  = picService.queryPicList(urlFront);
    	List<TPicDto> LinePicList = picService.queryPicLineByLineNo(lineNo);
    	
    	TLineDto lineDto = new TLineDto();
    	lineDto.setLineNo(lineNo.intValue());
    	lineDto = lineService.queryLine(lineDto);
    	
    	model.addAttribute("lineNo",lineDto.getLineNo());
    	model.addAttribute("lineName",lineDto.getLineName());
    	model.addAttribute("allPicList",allPicList);
    	model.addAttribute("LinePicList",LinePicList);
        return "/modules/web/LineImg";
    }
    
	@RequestMapping(value = "importPic", method=RequestMethod.POST)
    @ResponseBody
    public Object importPic(HttpServletRequest request, @RequestParam(value = "myFile", required = true) MultipartFile myFile,@RequestParam(required = true) String lineNo){
    	JSONObject json = new JSONObject();
    	BufferedInputStream bis = null;
    	FileOutputStream out = null;
    	String fileName = "";
    	try {
    		fileName = myFile.getOriginalFilename();
    		if(!fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg")){
    			json.put("result", "导入文件格式异常！");
				logger.warn("导入文件格式异常！");
                return json;
    		}
    		logger.info(fileName+"::"+myFile.getSize());
    		
    		File file = new File("/home/images/"+lineNo+"/"+fileName);
		    if(!file.exists()){
		    	file.getParentFile().mkdirs();
		    	file.createNewFile();
		    }
		    
		    bis = new BufferedInputStream(myFile.getInputStream());
		    out = new FileOutputStream(file);  
		    byte[] buffer = new byte[4096];
		    int readLength = 0;
		    while ((readLength=bis.read(buffer)) > 0) {
		    	out.write(buffer, 0, readLength);
		    }
		    out.flush();
		    json.put("result", "success");
		} catch (Exception e) {
			logger.error("导入图片系统异常，异常信息：" + e);
			json.put("result", "导入图片系统异常");
		} finally {
			try {
            	if(out != null){
            		out.close();
            	}
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            try {
            	if(bis != null){
            		bis.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	long picNo = picService.getNewNo();
    	TPicDto tpd = new TPicDto();
    	tpd.setPicNo((int)picNo);
    	tpd.setPicName(fileName);
    	picService.save(tpd);
    	TLinePicDto tlpd = new TLinePicDto();
    	tlpd.setLineNo(Integer.valueOf(lineNo));
    	tlpd.setPicNo((int)picNo);
    	linePicService.save(tlpd);
    	return json;
    }
    
    /**
     * 路线价格编辑
     */
    @RequestMapping(value = "/price/{lineNo}")
    public String price(@PathVariable Long lineNo, Model model) {
    	TLinePriceDto tlpd = new TLinePriceDto();
    	tlpd.setLineNo(lineNo.intValue());
    	tlpd = priceService.get(tlpd);
    	
    	TLineDto lineDto = new TLineDto();
    	lineDto.setLineNo(lineNo.intValue());
    	lineDto = lineService.queryLine(lineDto);
    	
    	model.addAttribute("lineNo", lineNo);
    	model.addAttribute("lineName",lineDto.getLineName());
    	model.addAttribute("tLinePriceDto", tlpd==null?new TLinePriceDto():tlpd);
    	return "/modules/web/LinePrice";
    }
    
    /**
     * 路线图片编辑
     */
    @RequestMapping(value = "/addition/{lineNo}")
    public String addition(@PathVariable Long lineNo, Model model) {
    	TAdditionDto tad = new TAdditionDto();
    	List<TAdditionDto> allAddList  = additionService.findList(tad);
    	List<TAdditionDto> lineAddList = additionService.queryAdditionByLineNo(lineNo);
    	
    	TLineDto lineDto = new TLineDto();
    	lineDto.setLineNo(lineNo.intValue());
    	lineDto = lineService.queryLine(lineDto);
    	
    	model.addAttribute("lineNo",lineDto.getLineNo());
    	model.addAttribute("lineName",lineDto.getLineName());
    	model.addAttribute("allAddList",allAddList);
    	model.addAttribute("lineAddList",lineAddList);
        return "/modules/web/LineAdd";
    }
    
    /**
     * 更新路线价格信息
     */
    @RequestMapping(value = "updateLinePrice", method = RequestMethod.POST)
    public String updateLinePrice(TLinePriceDto tLinePriceDto, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    	if(tLinePriceDto != null && tLinePriceDto.getOneP() != null){
    		priceService.updateLinePrice(tLinePriceDto);
    		addMessage(redirectAttributes, "更新路线价格成功");
    	}else{
    		addMessage(redirectAttributes, "至少要填写单人价格");
    	}
    	return "redirect:" + adminPath + "/line/price/" + tLinePriceDto.getLineNo();
    }

    /**
     * 更新路线附加项信息
     */
    @RequestMapping(value = "updateLineAdd/{lineNo}", method = RequestMethod.POST)
    public String updateLineAdd(@PathVariable String lineNo, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    	TLineDto lineDto = new TLineDto();
    	lineDto.setLineNo(Integer.valueOf(lineNo));
    	lineDto = lineService.queryLine(lineDto);
    	
    	if(lineDto == null){
    		logger.error("没有找到路线");
    		addMessage(redirectAttributes, "没有找到对应路线！");
    		return "redirect:" + adminPath +"/line/lineList";
    	}
    	
    	String[] addNos = request.getParameterValues("addNo");
    	if(addNos == null){
    		logger.error("没有勾选附加项");
    		addMessage(redirectAttributes, "没有勾选附加项！");
    		return "redirect:" + adminPath +"/line/lineList";
    	}
    	
    	TLineAdditionDto tlad = new TLineAdditionDto();
    	tlad.setLineNo(Integer.valueOf(lineNo));
    	lineAdditionService.delete(tlad);
    	
    	for(String addNoStr : addNos){
    		tlad = new TLineAdditionDto();
    		tlad.setLineNo(Integer.valueOf(lineNo));
    		tlad.setAddNo(Integer.valueOf(addNoStr));
    		lineAdditionService.save(tlad);
    		
    		String addValue = request.getParameter("addValue"+addNoStr);
    		TAdditionDto tad = new TAdditionDto();
    		tad.setAddNo(Integer.valueOf(addNoStr));
    		tad.setAddValue(addValue);
    		
    		additionService.updatePic(tad);
    	}
    	addMessage(redirectAttributes, "更新路线附加项成功");
    	return "redirect:" + adminPath + "/line/addition/" + lineNo;
    }
    /**
    * 跳转新增附加项页面
    *
    */
    @RequestMapping("/ForwardAddAddition")
    public String addEditBankCard(HttpServletRequest request, HttpServletResponse response, Model model) {
    	long addNo = additionService.getNewNo();
    	model.addAttribute("addNo", addNo);
    	return "/modules/web/addAdditionInfo";
    }
    
    /**
     * 保存附加项信息
     */
    @ResponseBody
    @RequestMapping("/saveAddition")
    public String saveAddition(TAdditionDto tAdditionDto, HttpServletRequest request, HttpServletResponse response) {
        String message = "";
        try {
        	additionService.save(tAdditionDto);
            message = "保存附加项信息成功";
        } catch (Exception e) {
            logger.error("##saveAddition 保存结算卡附加项信息系统异常,异常信息：{}", e);
            message = "保存附加项信息失败";
        }
        return message;
    }
    
    
    /**
     * 更新路线价格信息
     */ 
    @RequestMapping("/addition/del/{lineNo}/{addNo}")
    public String delAddition(@PathVariable String lineNo, @PathVariable int addNo, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    	TAdditionDto tad = new TAdditionDto();
    	tad.setAddNo(addNo);
    	additionService.delete(tad);
    	addMessage(redirectAttributes, "删除附加项成功");
    	return "redirect:" + adminPath + "/line/addition/" + lineNo;
    }
    
    @RequestMapping("/rediectLineBlankInfo")
    public String rediectLineBlankInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
    	long lineNo = lineService.getNewNo();
    	model.addAttribute("lineNo", lineNo);
        return "/modules/web/LineBlankInfo";
    }
    
    /**
     * 保存商户信息
     */
    @RequestMapping(value = ("/saveLine"), method = RequestMethod.POST)
    public String saveLine(TLineDto lineDto, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
        	lineService.save(lineDto);
            addMessage(redirectAttributes, "保存路线信息成功");
        } catch (Exception e) {
            logger.error("##saveLine 保存路线信息系统异常,异常信息：{}", e);
            addMessage(redirectAttributes, "保存路线信息失败");
        }
        return "redirect:" + adminPath + "/line/lineList";
    }

    /**
     * 跳转结算卡信息页面%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
     *//*
    @RequestMapping(value = "/clearCardInfo/{merchantCode}")
    public String clearCardInfo(@PathVariable String merchantCode, PosMerchantBo posMerchantBo, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        PosMerchant merchant = lineService.getPosMerchant(merchantCode);
        if (merchant == null) {
            logger.info("###dredgeService 查询商户信息不存在，商户编号：{}", merchantCode);
            addMessage(redirectAttributes, "商户信息不存在");
            return "redirect:" + adminPath + "/iposMerchant";
        }
        PosSettlementInfo posSettlementInfo = new PosSettlementInfo();
        posSettlementInfo.setMerchantCode(merchantCode);
        Page<PosSettlementInfo> page = posSettlementInfoService.findPage(new Page<PosSettlementInfo>(request, response), posSettlementInfo);
        model.addAttribute("mId", merchant.getId());
        model.addAttribute("merchantName", merchant.getMerchantName());
        model.addAttribute("merchantCode", merchant.getMerchantCode());
        model.addAttribute("page", page);

        return "/modules/merchant/clearCardInfo";
    }

    *//**
     * 查询结算卡支付类型
     *//*
    @RequestMapping(value = "finSettlementInfo")
    public String finSettlementInfo(PosMerchantBo posMerchantBo, HttpServletRequest request, HttpServletResponse response, Model model) {
        String merchantCode = request.getParameter("merchantCode");
        Page<PosSettlementInfo> page = new Page<PosSettlementInfo>(request, response);
        PosSettlementInfo posSettlementInfo = posMerchantBo.getSettlement();
        posSettlementInfo.setMerchantCode(merchantCode);
        posSettlementInfo.setPage(page);
        Page<PosSettlementInfo> orderPage = posSettlementInfoService.findPage(page, posSettlementInfo);
        model.addAttribute("page", orderPage);
        model.addAttribute("merchantName", posMerchantBo.getMerchant().getMerchantName());
        model.addAttribute("merchantCode", posMerchantBo.getMerchant().getMerchantCode());
        return "/modules/merchant/clearCardInfo";
    }


    *//**
     * 保存结算卡信息
     *//*
    @ResponseBody
    @RequestMapping("/saveSettlement")
    public String saveSettlement(PosSettlementInfo posSettlementInfo, HttpServletRequest request, HttpServletResponse response) {
        String message = "";
        try {
            posSettlementInfoService.updateDelFlag(posSettlementInfo);
            message = "保存结算卡信息成功";
        } catch (Exception e) {
            logger.error("##PosMerchantController.saveSettlement保存结算卡信息系统异常,异常信息：{}", e);
            message = "保存结算卡信息失败";
        }
        return message;
    }

    *//**
     * 跳转 新增/申请商户页面
     *//*
    @RequestMapping("/rediectLineBlankInfo")
    public String rediectLineBlankInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/modules/web/LineBlankInfo";
    }

    *//**
     * 新增商户 3---> 跳转结算/资质页面============================================================
     *//*
    @RequestMapping(value = "/clearAptitude")
    public String clearAptitude(HttpServletRequest request, HttpServletResponse response, PosMerchantBo posMerchantBo, Model model) {
        String merchantPayCode = request.getParameter("merchantPayCode");
        model.addAttribute("merchantPayCode", merchantPayCode);
        return "/modules/merchant/iposJieSuan";
    }

    *//**
     * 跳转提交页面
     *//*
    @RequestMapping(value = "/submitMerchant")
    public String submitMerchant(PosMerchantBo posMerchantBo, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response, Model model) {
        String merchantPayCode = request.getParameter("merchantPayCode");
        model.addAttribute("merchantPayCode", merchantPayCode);
        return "/modules/merchant/iposSubmit";
    }

    *//**
     * 提交页面保存商户信息跳
     *//*
    @RequestMapping(value = ("/saveMerchantToService"), method = RequestMethod.POST)
    public String saveMerchantToService(HttpServletRequest request, Model model, PosMerchantBo posMerchantBo, RedirectAttributes redirectAttributes) {
        try {
            String merchantPayCode = request.getParameter("merchantPayCode");
            if (StringUtils.isEmpty(merchantPayCode)) {
                addMessage(redirectAttributes, "商户开通业务服务编码不能为空");
                return "redirect:" + adminPath + "/iposMerchant/listMerchant";
            }
            Map<String, String> uploadResult = uploadFileService.upliadFiles(request);
            PosAttachment posAttachment = posMerchantBo.getAttach();
            if (posAttachment == null) {
                posAttachment = new PosAttachment();
            }
            BeanUtils.copyProperties(posAttachment, uploadResult);
            posMerchantBo.setAttach(posAttachment);
            PosMerchant merchant = posMerchantBo.getMerchant();
            Long count = lineService.verifyMerchantName(merchant);
            if (count != 0) {
                addMessage(redirectAttributes, "商户信息已存在");
                return "redirect:" + adminPath + "/iposMerchant/listMerchant";
            }
            lineService.saveMechant(posMerchantBo, Constants.POS_TYPE_MERCHANT);
            addMessage(redirectAttributes, "保存商户信息成功");
            String mId = posMerchantBo.getMerchant().getId();
            String merchantCode = merchant.getMerchantCode();

            PosMerchantBizBo posMerchantBizBo = new PosMerchantBizBo();
            posMerchantBizBo.setPosPayMerchantCode(merchantPayCode);
            posMerchantBizBo.setStatus(1);
            posMerchantBizBo.setmId(Long.parseLong(mId));
            String[] bizCodes = {"TECPOST+1"};
            posMerchantBizBo.setBizCodes(bizCodes);
            posMerchantBizBo.setWechatClearType(1);
            posMerchantBizService.openMerchantBiz(posMerchantBizBo);
            posMerchantBizService.openDefaultMerchantBiz(merchantCode, merchantPayCode, null);
            //删除开通服务缓存
            redisServiceFacade.deleteValue(Constants.REDIS_APP_NAME + "OpenBizs." + mId);
            logger.info("##saveMerchantToService， 删除开通服务缓存");
            model.addAttribute("mId", mId);
            model.addAttribute("merchantCode", merchantCode);
            model.addAttribute("merchantName", merchant.getMerchantName());
        } catch (Exception e) {
            logger.error("##PosMerchantController.saveMerchant 保存商户信息系统异常,异常信息：{}", e);
            addMessage(redirectAttributes, "保存商户信息失败");
        }
        return "/modules/merchant/ipsoAddTerminal";
    }

    *//**
     * 保存设备
     *//*
    @RequestMapping(value = "saveTerminals")
    public String saveTerminals(PosTerminalInfoBo posTerminalInfoBo, RedirectAttributes redirectAttributes) {
        try {
            posTerminalInfoService.saveTerminals(posTerminalInfoBo);
            addMessage(redirectAttributes, "保存设备信息成功！");
        } catch (Exception e) {
            logger.error("##saveTerminals 保存商户设备信息系统异常,异常信息：{}", e);
            addMessage(redirectAttributes, "保存设备信息失败！");
        }

        return "redirect:" + adminPath + "/iposMerchant/listMerchant";
    }

    *//**
     * 验证商户是否存在
     *//*
    @RequestMapping("/validateMerchant")
    public void validateMerchant(PosMerchantBo posMerchantBo, HttpServletRequest request, HttpServletResponse response) {
        String retStr = "failure";
        PrintWriter out = null;
        try {
            Long count = lineService.verifyMerchantName(posMerchantBo.getMerchant());
            if (count == 0) {
                retStr = "success";
            }
        } catch (Exception e) {
            logger.error("##validateMerchant异常", e);
            retStr = "failure";
        } finally {
            try {
                out = response.getWriter();
                out.write(retStr);
                out.flush();
                out.close();
            } catch (IOException e) {
                logger.error("验证商户是否存在系统异常", e);
            }
        }
    }

    *//**
     * 添加获取 代理商编号 和 名称
     *//*
    @ResponseBody
    @RequestMapping(value = "getAgenCodeList")
    public List<PosMerchant> getAgenCodeList(HttpServletRequest request, HttpServletResponse response) {
        return lineService.getAgenCodeList();
    }

    *//**
     * 根据商户code获取商户名称
     *//*
    @ResponseBody
    @RequestMapping("/getMerchantName")
    public String getMerchantName(HttpServletRequest request, String type, HttpServletResponse response) {
        String merchantCode = request.getParameter("merchantCode");
        return lineService.getMerchantName(merchantCode);

    }

    *//**
     * 跳转操作员商户列表
     *//*
    @RequestMapping("/findAccountNumberManage")
    public String findAccountNumberManage(PosMerchantVO posMerchantVO, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PosMerchantVO> page = new Page<PosMerchantVO>(request, response);
        posMerchantVO.setPage(page);
        Page<PosMerchantVO> newpage = posMerchantVOService.findPage(page, posMerchantVO);
        model.addAttribute("page", newpage);
        return "/modules/merchant/iposAccountNumberManage";
    }

    *//**
     * 操作员详情列表
     *//*
    @RequestMapping(value = "/iposAccountNumberForm/{mId}")
    public String iposAccountNumberForm(@PathVariable Long mId, Model model) {
        PosMerchantBo posMerchantBo = lineService.getMerchantBo(mId);
        model.addAttribute("posMerchantBo", posMerchantBo);
        return "/modules/merchant/iposAccountNumberForm";
    }

    *//**
     * @param mId
     * @return
     * @brief 跳转创建分店页面
     * @details
     * @author - 2016年6月4日 zs
     *//*
    @RequestMapping("/iposCreateSub/{mId}")
    public String iposCreateSub(@PathVariable Long mId, Model model, RedirectAttributes redirectAttributes) {
        PosMerchantBo merchentBo = lineService.getMerchantBo(mId);
        if (merchentBo == null) {
            logger.info("查询商户信息异常，mId{}错误" + mId);
            addMessage(redirectAttributes, "获取商户信息异常！");
            return "redirect:" + adminPath + "/iposMerchant/listMerchant";
        }
        PosMerchant merchant = merchentBo.getMerchant();
        PosMerchantBiz merchantBiz = merchentBo.getPosMerchantBiz();
        PosLegalPersonInfo legal = merchentBo.getLegal();
        PosBankCard bankCard = merchentBo.getBankCard();
        PosAttachment attach = merchentBo.getAttach();
        model.addAttribute("merchant", merchant);
        model.addAttribute("legal", legal);
        model.addAttribute("bankCard", bankCard);
        model.addAttribute("attach", attach);
        model.addAttribute("merchantBiz", merchantBiz);
        return "/modules/merchant/iposCreateSub";
    }

    *//**
     * @brief 跳转增加总店商户列表
     * @details
     * @author - 2016年11月29日 zs
     *//*
    @RequestMapping("/addHqMerchantList")
    public String addHqMerchant(HttpServletRequest request, PosMerchantVO posMerchantVo, HttpServletResponse response, Model model) {
        List<PosMerchant> page = lineService.getHQMerchant(posMerchantVo);
        model.addAttribute("page", page);
        return "/modules/merchant/addHqMerchantList";
    }

    *//**
     * @brief 跳转批量开通商户服务页面
     * @details
     * @author - 2016年11月29日 zs
     *//*
    @RequestMapping("/goMerServiceOpenJsp")
    public String goMerServiceOpenJsp(HttpServletResponse response, Model model) {

        return "/modules/merchant/iposMerchantServiceOpen";
    }
    *//**
     * 根据时间段(时间跨度7天)导出商户信息
     * @param posMerchant 商户信息
     * @param response
     * @param redirectAttributes
     * @return null
     *//*
    @RequestMapping(value = "exportMerchantExcel")
    public String exportMerchantExcel(PosMerchantVO posMerchantVo, HttpServletResponse response,
                              RedirectAttributes redirectAttributes) {
        try {
            String beginDate = posMerchantVo.getBeginDate();
            String endDate = posMerchantVo.getEndDate();

            if (!validateDate(beginDate, endDate)) {
                logger.info("导出商户信息异常，时间范围超出7天");
                addMessage(redirectAttributes, "导出商户时间范围超过7天，请修改！");
                return "redirect:" + adminPath + "/iposMerchant/listMerchant";
            } else {//大于0或者==
            	response.setContentType("application/vnd.ms-excel");
                List<PosMerchantVO> merchantList = posMerchantVOService.queryExportMerchantList(posMerchantVo);
                if (merchantList.size() == 0) {
                    logger.info("导出交易查询异常，查询结果为空");
                    addMessage(redirectAttributes, "导出数据为空，请核实！");
                    return "redirect:" + adminPath + "/iposMerchant/listMerchant";
                }
                ExportExcel exportExcel = new ExportExcel("商户信息", headers1);
                for (int i = 0, length = merchantList.size(); i < length; i++) {
                    Row row = exportExcel.addRow();//增加一行
                    PosMerchantVO settlement = merchantList.get(i);
                    exportExcel.addCell(row, 0, settlement.getMerchantCode());
                    exportExcel.addCell(row, 1, settlement.getMerchantName());
                }
                exportExcel.write(response, "商户信息" + beginDate + "至" + endDate + ".xls");
            }
        } catch (Exception exportExcel) {
            logger.error("导出数据异常，异常类型" + exportExcel);
            addMessage(redirectAttributes, "导出数据异常！");
            return "redirect:" + adminPath + "/iposMerchant/list";
        }
        return null;
    }
    *//**
     * 日期验证
     * @param beginDate 开始时间
     * @param endDate	结束时间
     * @return boolean
     * @throws ParseException 
     *//*
    private static boolean validateDate(String beginDate, String endDate) throws ParseException{
    	if (StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate)) {
            return false;
        }
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(format.parse(beginDate));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(format.parse(endDate));
        endCalendar.add(Calendar.DATE, -7);
        return beginCalendar.compareTo(endCalendar) > 0;
    }
    
    *//**
     * 停用商户
     *//*
    @RequestMapping(value = "/stopMerchant/{mId}")
    @RequiresPermissions("ipos:merchant:stop")
    public String stopMerchant(@PathVariable Long mId, Model model,RedirectAttributes redirectAttributes) {
        try {
        		if(null==mId){
        			addMessage(redirectAttributes, "参数非法");
        			return "/modules/merchant/iposMerchantList";
        		}
        		PosMerchant posMerchant=new PosMerchant();
        		posMerchant.setId(Long.toString(mId));
        		//设置商户状态
        		posMerchant.setDelFlag("1");
            lineService.stopMerchant(posMerchant);
            addMessage(redirectAttributes, "停用商戶成功");
        } catch (Exception e) {
            logger.error("##PosMerchantController.updateMerchant 停用商户异常,异常信息：{}", e);
            addMessage(redirectAttributes, "停用商戶失败");
        }
        return "redirect:" + adminPath + "/iposMerchant/listMerchant";
    }
    
    *//**
     * 启用商户
     *//*
    @RequestMapping(value = "/startMerchant/{mId}")
    @RequiresPermissions("ipos:merchant:stop")
    public String startMerchant(@PathVariable Long mId, Model model,RedirectAttributes redirectAttributes) {
        try {
        		if(null==mId){
        			addMessage(redirectAttributes, "参数非法");
        			return "/modules/merchant/iposMerchantList";
        		}
        		PosMerchant posMerchant=new PosMerchant();
        		posMerchant.setId(Long.toString(mId));
        		//设置商户状态
        		posMerchant.setDelFlag("0");
            lineService.stopMerchant(posMerchant);
            addMessage(redirectAttributes, "启用商戶成功");
        } catch (Exception e) {
            logger.error("##PosMerchantController.updateMerchant 启用商户异常,异常信息：{}", e);
            addMessage(redirectAttributes, "启用商戶失败");
        }
        return "redirect:" + adminPath + "/iposMerchant/listMerchant";
    }
    
    *//**
     * 下载发票商户批量导入模板
     * @param request
     * @return
     * @throws IOException
     *//*
    @RequestMapping(value = "downloadTemplate")
    public ResponseEntity<byte[]> downloadTemplate(HttpServletRequest request) throws IOException {
        String fileName = "importInvoiceMerchant_template.xlsx";
        String dfileName = "商户发票服务开通导入模板.xlsx";
        return DownloadUtils.download(request,fileName, dfileName);
    }
    
    @RequestMapping(value = "downloadErrMCode", method=RequestMethod.POST)
    @ResponseBody
    public Object downloadErrMCode(@RequestBody String errListStr, HttpServletResponse response){
    	String[] errmArray = errListStr.substring(errListStr.indexOf("=")+1).split("%2C");
    	List<String> errList = new ArrayList<String>();
    	for(String errstr : errmArray){
    		errList.add(errstr);
    	}
		response.setContentType("application/vnd.ms-excel");
		String[] headers = {"导入电子发票商户失败商户编号"};
		ExportExcel export = new ExportExcel("", headers);
		for (int i = 0, length = errList.size(); i < length; i++) {
			Row row = export.addRow();
			String mCode = (String) errList.get(i);
			export.addCell(row, 0, mCode);
		}
		try {
			export.write(response, "导入电子发票商户失败数据.xlsx");
		} catch (IOException e1) {
			logger.error("输出电子发票商户信息异常！", e1);
		}
		return null;
    }
    
    *//**
     * 批量导入发票商户
     * @param request
     * @param myFile
     * @return
     *//*
    @RequestMapping(value = "importInvoiceMerchant", method=RequestMethod.POST)
    @ResponseBody
    public Object importInvoiceMerchant(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "myFile", required = true) MultipartFile myFile){
    	logger.info("####导入发票商户信息！");
    	JSONObject json = new JSONObject();
    	List<String> errList = new ArrayList<String>();
    	try {
    		System.out.println(myFile.getName()+"::"+myFile.getSize());
			ImportExcel excel = new ImportExcel(myFile, 1, 0);
			List<String> mList = excel.getDataTypeList(String.class);
			if(CollectionUtils.isEmpty(mList)){
				json.put("result", "导入发票商户的文件为空！请检查或重新下载模板填写！");
				logger.warn("导入发票商户的文件为空！");
			}
			List<String> haveList = lineService.queryExistM(mList);
			for(String outMcode : mList){
				if(!haveList.contains(outMcode)){
					errList.add(outMcode);
				}
			}
			lineService.updateMerchantInvoice(haveList);
			if(errList.size() == 0){
				json.put("result", "success");
			}else{
				json.put("result", "partOfSuccess");
				StringBuffer msb = new StringBuffer();
				Iterator<String> itstr = errList.iterator();
				while(itstr.hasNext()){
					msb.append(itstr.next());
					if(itstr.hasNext()){
						msb.append(",");
					}
				}
				json.put("errList", msb.toString());
			}
		} catch (Exception e) {
			logger.error("导入发票商户系统异常，异常信息：" + e);
			json.put("result", "导入发票商户系统异常，异常信息："+e.getMessage());
		} 
    	return json;
    }*/
}
