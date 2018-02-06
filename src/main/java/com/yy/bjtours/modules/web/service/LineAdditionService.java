package com.yy.bjtours.modules.web.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.collect.Maps;
import com.yy.bjtours.common.service.CrudService;
import com.yy.bjtours.modules.web.dao.LineAdditionDao;
import com.yy.bjtours.modules.web.dao.LinePicDao;
import com.yy.bjtours.modules.web.dto.TLineAdditionDto;
import com.yy.bjtours.modules.web.dto.TLinePicDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yy
 * @date: 2017年12月31日 下午2:43:19
 * @desc:线路Service
 */
@Service
@Transactional(readOnly = true)
public class LineAdditionService extends CrudService<LineAdditionDao, TLineAdditionDto> {
    private static final Logger logger = LoggerFactory.getLogger(LineAdditionService.class);

   /* *//**
     * 验证商户编码是否存在
     *//*
    public Long verifyMerchantName(PosMerchant posMerchant) {
        return dao.verifyMerchantName(posMerchant);
    }

    public List<PosMerchant> getAgenCodeList() {
        return dao.getAgenCodeList();
    }

    public List<PosMerchant> getMerchantCodeList(String belongTo) {
        return dao.getMerchantCodeList(belongTo);
    }

    public List<PosMerchant> getHQMerchant(PosMerchantVO posMerchantV) {
        return dao.getHQMerchant(posMerchantV);
    }

    *//**
     * 根据商户code查询商户名称
     *
     * @param merchantCode
     * @return merchantName
     *//*
    public String getMerchantName(String merchantCode) {
        return dao.getMerchantName(merchantCode);
    }

    *//**
     * 根据商户code查询商户
     *
     * @param merchantCode
     * @return PosMerchant
     *//*
    public PosMerchant getPosMerchant(String merchantCode) {
        return dao.getPosMerchant(merchantCode);
    }

    *//**
     * @param hqMerchantCode
     * @return HqMerchantName
     * @brief 根据商户hqMerchantCode查询商户名称
     * @author - 2016年6月13日 zs
     *//*
    public String getHqMerchantName(String hqMerchantCode) {
        return dao.getHqMerchantName(hqMerchantCode);
    }

    @Transactional(propagation = Propagation.NEVER)
    public String getMerchantMid(String merchantCode) {
        return dao.getMerchantMid(merchantCode);
    }

    public List<PosMerchant> getHqMerchantList(PosMerchant posMerchant) {
        return dao.getHqMerchantList(posMerchant);
    }

    @Transactional(readOnly = false)
    public void saveMechant(PosMerchantBo posMerchantBo, Integer merchantType) {
        if (posMerchantBo == null || merchantType == null) {
            logger.info("###PosMerchantService.saveMechant请求参数不能为空");
            throw new RuntimeException("参数完整性校验失败");
        }
        PosMerchant posMerchant = posMerchantBo.getMerchant();
        if (merchantType.equals(Constants.POS_TYPE_AGENT)) {
            posMerchant.setMerchantType(Constants.POS_TYPE_AGENT);
        } else {
            posMerchant.setMerchantType(Constants.POS_TYPE_MERCHANT);
            if (StringUtils.isEmpty(posMerchant.getMerchantCode())) {
                long merLastId = getMerchantLastId();
                String autoMerCode = buildAutoMerchantCode(merLastId);
                posMerchant.setMerchantCode(autoMerCode);
            }
        }
        posMerchant.setBizType(Constants.POS_BIZ_IPOS);
        posMerchant.setIsNewRecord(true);
        // 保存商户信息

        save(posMerchant);
        logger.info("## method saveMechant 保存商户信息功能");
        String merchantCode = posMerchant.getMerchantCode();
        // 保存法人信息
        PosLegalPersonInfo legalInfo = posMerchantBo.getLegal();
        legalInfo.setMerchantCode(merchantCode);
        legalInfo.setIsNewRecord(true);
        legalService.save(legalInfo);
        logger.info("## method saveMechant 保存法人信息成功");
        // 保存银行卡信息
        PosBankCard bankCard = posMerchantBo.getBankCard();
        bankCard.setMerchantCode(merchantCode);
        bankCard.setIsNewRecord(true);
        cardService.save(bankCard);
        logger.info("## method saveMechant 保存法人信息成功");
        // 保存附件信息
        PosAttachment attachment = posMerchantBo.getAttach();
        attachment.setMerchantCode(merchantCode);
        attachment.setIsNewRecord(true);
        attachmentService.save(attachment);
        logger.info("## method saveMechant 保存附件信息成功");
    }

    *//**
     * @return PosMerchantBo    返回类型
     * @throws
     * @Title: getMerchantBo
     * @Description: 查询商户明细
     * @parammId
     *//*
    public PosMerchantBo getMerchantBo(Long mId) {
        PosMerchantBo posMerchantBo = null;

        if (mId == null) {
            logger.info("##PosMerchantService.getMerchantBo请求参数不能为空");
            return null;
        }
        try {

            PosMerchant merchant = get("" + mId);
            PosLegalPersonInfo legalInfo = legalService.getPosLegalPersonInfoByMid(merchant.getMerchantCode());
            PosBankCard bankCard = cardService.getBankCardByMid(merchant.getMerchantCode());
            PosAttachment attachment = attachmentService.getAttachmentByMid(merchant.getMerchantCode());
            // 查询商户服务信息
            PosMerchantBiz merchantBiz = new PosMerchantBiz();
            merchantBiz.setBizCode(Constants.POS_BIZ_TYPE_POSTONE);
            merchantBiz.setMerchantCode(merchant.getMerchantCode());
            PosMerchantBiz posMerchantBiz = merchantBizDao.getMerchantBiz(merchantBiz);

            posMerchantBo = new PosMerchantBo();
            posMerchantBo.setMerchant(merchant);
            posMerchantBo.setPosMerchantBiz(posMerchantBiz);
            posMerchantBo.setLegal(legalInfo);
            posMerchantBo.setBankCard(bankCard);
            posMerchantBo.setAttach(attachment);
        } catch (Exception e) {
            logger.error("##getMerchantBo 查询代理商信息系统异常，异常信息：{}", e);
        }
        return posMerchantBo;
    }

    *//**
     * 更新商户信息
     *
     * @param posMerchantBo
     *//*
    @Transactional(readOnly = false)
    public void modifyMerchant(PosMerchantBo posMerchantBo) {
        if (posMerchantBo == null) {
            logger.info("###PosMerchantService.saveMechant请求参数不能为空");
            throw new RuntimeException("参数完整性校验失败！");
        }
        PosMerchant merchant = posMerchantBo.getMerchant();
        merchant.setIsNewRecord(false);
        // 保存商户信息
        save(merchant);
        logger.info("## method modifyMerchant 更新商户信息功能");
        // 保存法人信息
        PosLegalPersonInfo legalInfo = posMerchantBo.getLegal();
        legalInfo.setIsNewRecord(false);
        legalService.save(legalInfo);
        // 更新商户后删除redis缓存中的商户信息，以便web接口端应用读取最新的商户信息
        redisServiceFacade.deleteValue(Constants.REDIS_APP_NAME_MERCHANT_CODE + merchant.getMerchantCode());
        logger.info("## method modifyMerchant 更新法人信息成功");
        // 保存银行卡信息
        PosBankCard bankCard = posMerchantBo.getBankCard();
        bankCard.setIsNewRecord(false);
        cardService.save(bankCard);
        logger.info("## method saveMechant 更新结算信息成功");
    }

    *//**
     * 更新商户信息1（不包括银卡信息的更新）
     *//*
    @Transactional(readOnly = false)
    public void updateMerchant(PosMerchantBo posMerchantBo) {
        if (posMerchantBo == null) {
            logger.info("###PosMerchantService.updateMerchant请求参数不能为空");
            throw new RuntimeException("参数完整性校验失败！");
        }
        PosMerchant merchant = posMerchantBo.getMerchant();
        merchant.setIsNewRecord(false);
        // 保存商户信息
        save(merchant);
        // 更新商户后删除redis缓存中的商户信息，以便web接口端应用读取最新的商户信息
        redisServiceFacade.deleteValue(Constants.REDIS_APP_NAME_MERCHANT_CODE + merchant.getMerchantCode());
        logger.info("## method modifyMerchant 更新商户信息功能");
        // 保存法人信息
        PosLegalPersonInfo legalInfo = posMerchantBo.getLegal();
        legalInfo.setIsNewRecord(false);
        legalService.save(legalInfo);
        logger.info("## method modifyMerchant 更新法人信息成功");
    }

    *//**
     * 商户停用
     *//*
    @Transactional(readOnly = false)
    public void stopMerchant(PosMerchant merchant) {
        // 保存商户信息
        merchant.preUpdate();
        save(merchant);
    }

    public String getAgentCode(String areaCode) {
        Area area = areaService.get(areaCode);
        if (area == null) {
            logger.info("##getAgentCode 查询区域编码不存在，areaCode:{}", areaCode);
            throw new RuntimeException("区域信息不存在");
        }
        String result;
        String agentCode = dao.getAgentCode(areaCode);
        if (StringUtils.isBlank(agentCode)) {
            result = area.getCode() + StringUtils.leftPad("1", 4, "0");
        } else {
            result = agentCode;
        }
        return result;
    }

    public Page<PosMerchant> findMerPage(Page<PosMerchant> page,
                                         PosMerchant posMerchant) {
        posMerchant.setPage(page);
        page.setList(dao.findMerchantTwo(posMerchant));
        return page;
    }

    public long getMerchantLastId() {
        return dao.getMerchantLastId();
    }

    public String buildAutoMerchantCode(long id) {

        String autoMerCode = String.format("%015d", id + 1);

        return autoMerCode;
    }

    public List<PosMerchant> findAgenList(PosMerchantVO posMerchantVO) {
        return dao.findAgenList(posMerchantVO);
    }


    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void pospBatchMerchantInput(Map<String, Object> messageMap) {
        String userName = "TYJJ-" + messageMap.get("currLogUser");
        User user = new User(userName);
        // 商户编号
        String merchantCode = (String) messageMap.get("merchantCode");
        // 商户类型
        String merchantType = (String) messageMap.get("merchantType");
        // 商户名称
        String merchantName = (String) messageMap.get("merchantName");
        // 签购单名称
        String signPlistName = (String) messageMap.get("signPlistName");
        // KSN
        String machineSn = (String) messageMap.get("machineSn");
        // 默认商户类型是2
        int mType = StringUtils.isNotBlank(merchantType) ? Integer.parseInt(merchantType) : 2;
        Map<String, Object> params = Maps.newHashMap();
        params.put("merchantCode", merchantCode);
        params.put("merchantType", mType);
        //查询商户是否存在
        PosMerchant merchant = this.dao.getByMerchantCodeAndType(params);
        if (merchant == null) {
            merchant = new PosMerchant();
            merchant.setMerchantName(merchantName);
            merchant.setMerchantCode(merchantCode);
            merchant.setIndustryType(2); //行业类型 默认一般类
            merchant.setBelongTo((String) messageMap.get("belongTo"));
            merchant.setBusinessLicenseExpire(DateUtils.parseDate(messageMap.get("businessLicenseExpire")));
            merchant.setBusinessLicenseNo(messageMap.get("businessLicenseNo").toString());
            merchant.setRegistCapital(BigDecimal.valueOf(Long.parseLong((String) messageMap.get("registCapital"))));
            merchant.setTaxLicenseNo((String) messageMap.get("taxLicenseNo"));
            merchant.setRegistAddress((String) messageMap.get("registAddress"));
            merchant.setBusinessAddress((String) messageMap.get("businessAddress"));
            merchant.setLegalName((String) messageMap.get("legalName"));
            merchant.setSalemanName((String) messageMap.get("salemanName"));
            merchant.setSignatureDate(DateUtils.parseDate(messageMap.get("signatureDate")));
            merchant.setMerchantType(mType);
            //商户简称
            merchant.setMerchantShortName(signPlistName);
            //统一进件标识
            merchant.setTypeinUsername(userName);
            merchant.setPrimaryBusiness((String) messageMap.get("primaryBusiness"));
            merchant.setOrganizationNo((String) messageMap.get("organizationNo"));
            merchant.setBelongType(Constants.POS_TYPE_AGENT);
            merchant.setBizType(Constants.POS_BIZ_IPOS);
            merchant.setChainType(0); //默认非连锁
            merchant.setCreateBy(user);
            merchant.setUpdateBy(user);
            merchant.setIsNewRecord(true);
            // 防止并发导致插入失败问题
            PosMerchant merchantRecord = this.dao.getByMerchantCodeAndType(params);
            if (merchantRecord != null) {
                logger.info("## method pospBatchMerchantInput 统一进件 " + merchantCode + "商户已存在！");
                return;
            }
            //保存商户信息
            save(merchant);
            //法人
            PosLegalPersonInfo legalPersonInfo = new PosLegalPersonInfo();
            legalPersonInfo.setMerchantCode(merchantCode);
            legalPersonInfo.setIdCard((String) messageMap.get("idCard"));
            legalPersonInfo.setLegalName((String) messageMap.get("legalName"));
            legalPersonInfo.setLegalMobilePhone((String) messageMap.get("legalMobilePhone"));
            legalPersonInfo.setLegalEmail((String) messageMap.get("legalEmail"));
            legalPersonInfo.setLinkmanEmail((String) messageMap.get("linkmanEmail"));
            legalPersonInfo.setLinkmanName((String) messageMap.get("linkmanName"));
            legalPersonInfo.setLinkmanPhone((String) messageMap.get("linkmanPhone"));
            legalPersonInfo.setCreateBy(user);
            legalPersonInfo.setUpdateBy(user);
            legalPersonInfo.setUpdateBy(user);
            legalPersonInfo.setIsNewRecord(true);
            legalService.save(legalPersonInfo);

            //银行卡
            PosBankCard bankCard = new PosBankCard();
            bankCard.setMerchantCode(merchantCode);
            bankCard.setAccountType(Integer.valueOf((String) messageMap.get("accountType")));
            bankCard.setBankName((String) messageMap.get("bankName"));
            bankCard.setBankBranchName((String) messageMap.get("bankBranchName"));
            bankCard.setAccountNo((String) messageMap.get("accountNo"));
            bankCard.setAccountName((String) messageMap.get("accountName"));
            bankCard.setBankCode((String) messageMap.get("bankCode"));
            bankCard.setCreateBy(user);
            bankCard.setUpdateBy(user);
            bankCard.setIsNewRecord(true);
            cardService.save(bankCard);

            //结算
            PosSettlementInfo settlementInfo = new PosSettlementInfo();
            settlementInfo.setMerchantCode(merchantCode);
            settlementInfo.setAccountName((String) messageMap.get("accountName"));
            settlementInfo.setAccountNo((String) messageMap.get("accountNo"));
            settlementInfo.setAccountType(Integer.valueOf((String) messageMap.get("accountType")));
            settlementInfo.setOpenAccBankCode((String) messageMap.get("bankCode"));
            settlementInfo.setOpenAccBankName((String) messageMap.get("bankName"));
            // 支付类型默认为1：收单 2：支付宝 3：微信
            settlementInfo.setPayType(PayTypeEnum.PAY_TYPE_POS_T1.getCode());
            settlementInfo.setCreateBy(user);
            settlementInfo.setUpdateBy(user);
            settlementInfo.setIsNewRecord(true);
            settlementInfoService.save(settlementInfo);

            //统一进件开通默认服务
            merchantBizService.openDefaultMerchantBiz(merchantCode, merchantCode, user);
        } else {
            logger.info("## method pospBatchMerchantInput 统一进件 " + merchantCode + "商户已存在！");
        }

        String terminalNo = (String) messageMap.get("terminalNo");
        // 防止出现推送绑定关系中终端号和终端表不一致问题
        PosClientRelation clientRelation = posClientRelationDao.getRelationByKSN(machineSn);
        if (clientRelation != null && !clientRelation.getTerminalNo().equals(terminalNo)) {
            String relationTerminalNO = clientRelation.getTerminalNo();
            // 如果绑定关系存在，并且KSN对应终端信息不一致，更新绑定关系对应终端号
            clientRelation.setTerminalNo(terminalNo);
            posClientRelationDao.updateTerminalNo(clientRelation);
            //删除旧终端绑定关系缓存信息
            redisServiceFacade.deleteValue(relationTerminalNO);
            logger.info("##method pospBatchMerchantInput 统一进件,删除终端：{} 推送绑定缓存。更新终端号为：{}",
                    machineSn, terminalNo);
        }

        // 根据KSN查询终端信息
        PosTerminalInfo terminalInfo = terminalInfoDao.findByMachineSn(machineSn);
        if (terminalInfo != null) {
            PosTerminalInfo terminalHisInfo = new PosTerminalInfo();
            BeanUtils.copyProperties(terminalInfo, terminalHisInfo);
            String oldTerminalNo = terminalInfo.getTerminalNo();
            String oldMerchantCode = terminalInfo.getMerchantCode();
            // 是否需要更新终端信息
            boolean needUpdate = false;
            if (!merchantCode.equals(oldMerchantCode)) {
                // 商户编码不一致同时需要更新对应商户ID信息
                terminalInfo.setmId(merchant.getId());
                terminalInfo.setMerchantCode(merchantCode);
                needUpdate = true;
            }
            if (!terminalNo.equals(oldTerminalNo)) {
                terminalInfo.setTerminalNo(terminalNo);
                needUpdate = true;
            }
            if (needUpdate) {
                // 保存终端历史信息
                terminalHisInfo.preInsert();
                terminalInfoDao.saveHisTerminal(terminalHisInfo);
                terminalInfo.setUpdateBy(user);
                terminalInfo.preUpdate();
                terminalInfoDao.update(terminalInfo);
                // 删除终端缓存信息
                redisServiceFacade.deleteValue(Constants.REDIS_APP_NAME_MACHINE_SN + machineSn);
                logger.info("##method pospBatchMerchantInput 统一进件,删除终端：{}缓存。", machineSn);
                // 查询设备是否有登录信息
                PosLoginInfo loginInfo = posLoginInfoDao.queryLoginInfo(machineSn);
                if (loginInfo != null) {
                    String token = loginInfo.getToken();
                    // 设备登录信息过期
                    posLoginInfoDao.updateLoginInfo(machineSn);
                    // 删除登录缓存信息
                    redisServiceFacade.deleteValue(Constants.REDIS_APP_NAME + token);
                    logger.info("##method pospBatchMerchantInput 统一进件,删除终端：{} 登录信息缓存。", machineSn);
                }
                logger.info("## method pospBatchMerchantInput 统一进件 更新:{} 对应终端号为:{}", machineSn, terminalNo);
            } else {
                logger.info("## method pospBatchMerchantInput 统一进件 " + machineSn + "设备已存在！");
            }

            // 查询该终端KSN是否绑定聚合码
            List<String> qrCodeList = qrCodeDao.getQrcodeStrBySn(machineSn);
            if (CollectionUtils.isNotEmpty(qrCodeList)) {
                try {
                    // 删除聚合码绑定关系
                    qrCodeDao.deleteQrCodeRelation(machineSn);
                    final AssetsManagementMsg assetsManagementMsg = new AssetsManagementMsg();
                    assetsManagementMsg.setStatus(Constants.ASSETS_UNBIND_LOSE);
                    assetsManagementMsg.setOperatorType(Constants.ASSETS_OPERATOR_UNBIND);
                    for (String qrCode : qrCodeList) {
                        assetsManagementMsg.setCodeId(qrCode);
                        String msgContent = assetsManagementMsg.toString();
                        // 桌牌解绑发送MQ消息
                        rabbitTemplate.convertAndSend(Constants.MQ_ASSETS_QUEUE, msgContent);
                        if (logger.isInfoEnabled()) {
                            logger.info("##统一进件解绑聚合码发送MQ成功，商户号[{}],终端KSN[{}],聚合码[{}],消息内容[{}]",
                                    merchantCode, machineSn, qrCode, msgContent);
                        }
                        // 删除聚合码缓存信息
                        redisServiceFacade.deleteValue(Constants.REDIS_APP_NAME_QRCODE_ID + qrCode);
                        logger.info("##method pospBatchMerchantInput 统一进件,删除聚合码缓存信息：{}", qrCode);
                    }
                    *//** 聚合码状态设置为解绑丢失*//*
                    qrCodeInfoDao.batchSetToInvalid(qrCodeList);
                } catch (Exception e) {
                    logger.warn("##统一进件解绑聚合码发送MQ失败，商户号[{}],终端KSN[{}]", merchantCode, machineSn, e);
                }
            }
            logger.info("## method pospBatchMerchantInput 统一进件功能完成！");
            return;
        }

        // 设备管理
        terminalInfo = new PosTerminalInfo();
        terminalInfo.setMerchantCode(merchantCode);
        terminalInfo.setmId(merchant.getId());
        terminalInfo.setTerminalNo(terminalNo);
        String loginNO;
        if (machineSn.length() > 6) {
            loginNO = machineSn.substring(machineSn.length() - 6);
        } else {
            loginNO = StringUtils.leftPad(machineSn, 6, "0");
        }
        terminalInfo.setMachineSn(machineSn);
        String terminalModel = (String) messageMap.get("terminalModel");
        if (StringUtils.isNotEmpty(terminalModel) && terminalModel.toUpperCase().contains("A8")) {
            terminalInfo.setTerminalModel("2");
        } else {
            terminalInfo.setTerminalModel("1");
        }

        // 终端所有权 默认1我司所有
        terminalInfo.setBelongTo(1);
        // 机具所有权 默认2租赁
        terminalInfo.setOwnership(2);

        terminalInfo.setLoginNo(loginNO);
        terminalInfo.setDirectorsPwd(Constants.POS_TERMINAL_ADMIN);
        terminalInfo.setLoginPwd(Constants.POS_TERMIAL_LOGIN);
        // 统一进件标识
        terminalInfo.setTypeinUserName(userName);
        terminalInfo.setCreateBy(user);
        terminalInfo.setUpdateBy(user);
        terminalInfo.preInsert();
        terminalInfoDao.insert(terminalInfo);
        //更新商户终端数量
        this.dao.updateTerminalNums(merchantCode);
        logger.info("## method pospBatchMerchantInput 统一进件功能完成！");
    }

    private PosMerchantBiz createWcSecondClearServer(String merchantCode, User user, Map<String, Object> mailMap) {
        PosMerchantBiz merchantBiz = new PosMerchantBiz();
        merchantBiz.setClearType(2);
        merchantBiz.setCreateBy(user);
        merchantBiz.setUpdateBy(user);
        //查询微信服务编码
        String serviceBizCode = serviceInfoService.getBizCodeByType(String.valueOf(Constants.POS_BIZ_TYPE_WECHATPAY));
//        merchantBiz.setmId(mId);
        merchantBiz.setMerchantCode(merchantCode);
        merchantBiz.setBizCode(serviceBizCode);
        //获取微信二清商户进件请求URL
        String url = Global.getConfig("pay.merchant.create");
        //封装微信二清商户进件请求参数
        Map<String, String> paramsMap = buildParamsMap(mailMap);
        //调用http接口进件
        String remoteResStr = HttpClientUtil.post(url, paramsMap);
        int status; //服务开通状态
        if (StringUtils.isNotEmpty(remoteResStr)) {
            JSONObject jsonObject = JSONObject.parseObject(remoteResStr);
            String resStatus = jsonObject.getString("responseCode");
            String resMessage = jsonObject.getString("responseDesc");
            logger.info("##微信商户进件，调用进件接口响应码：" + resStatus + " | 响应描述:" + resMessage);
            if (GlobalSettings.API_SUCCESS_RCODE.equals(resStatus)) {
                String customerNo = jsonObject.getString("customerNo");
                String checkedStatus = jsonObject.getString("checkedStatus");
                if ("I".equals(checkedStatus)) {
                    status = 3;
                } else if ("1".equals(checkedStatus)) {
                    status = 1;
                } else {
                    status = 2;
                }
                merchantBiz.setMerchantPayCode(customerNo);
            } else {
                status = 2;
            }

        } else {
            //如果http请求响应为空，商户微信二清服务开通失败
            status = 2;
            merchantBiz.setRemarks("调用进件请求无响应");
        }
        merchantBiz.setStatus(status);
        return merchantBiz;
    }

    private Map<String, String> buildParamsMap(Map<String, Object> mailMap) {
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("customerName", (String) mailMap.get("merchantName")); //商户名称
        paramsMap.put("customerShortName", (String) mailMap.get("merchantName")); //商户简称
        paramsMap.put("customerProperty", "E"); //商户性质 智能POS商户默认为企业商户
        paramsMap.put("bizInfo", "IPOSSYS"); //系统来源 新业务系统 : BIZSYS, IPOS系统 : IPOSSYS
        paramsMap.put("mccCode", "5200"); //MCC编码
        paramsMap.put("addrInfo", (String) mailMap.get("businessAddress")); //地址信息
        paramsMap.put("isOneLic", "1"); //是否三证合一默认：三证合一
        paramsMap.put("licNo", (String) mailMap.get("businessLicenseNo")); //营业执照号
        String primaryBusiness = StringUtils.isBlank((String) mailMap.get("primaryBusiness")) ? "餐饮服务" : (String) mailMap.get("primaryBusiness");
        paramsMap.put("licRange", primaryBusiness); //经营范围
        paramsMap.put("licInvalidTime", (String) mailMap.get("businessLicenseExpire")); //营业执照失效时间
        paramsMap.put("orgNo", (String) mailMap.get("organizationNo")); //组织机构代码
        paramsMap.put("taxNo", (String) mailMap.get("taxLicenseNo")); //税务登记号
        paramsMap.put("corpName", (String) mailMap.get("legalName")); //法人姓名
        //法人联系电话、邮箱 如果为空则为联系人电话、邮箱
        String legalMobilePhone = StringUtils.isBlank((String) mailMap.get("legalMobilePhone")) ? (String) mailMap.get("linkmanPhone") : (String) mailMap.get("legalMobilePhone");
        String legalEmail = StringUtils.isBlank((String) mailMap.get("legalEmail")) ? (String) mailMap.get("linkmanEmail") : (String) mailMap.get("legalEmail");
        String linkManMobile = StringUtils.isBlank((String) mailMap.get("linkmanPhone")) ? (String) mailMap.get("legalMobilePhone") : (String) mailMap.get("linkmanPhone");
        String linkManEmail = StringUtils.isBlank((String) mailMap.get("linkmanEmail")) ? (String) mailMap.get("legalEmail") : (String) mailMap.get("linkmanEmail");
        paramsMap.put("cropPhone", legalMobilePhone);
        paramsMap.put("cropMail", legalEmail);
        paramsMap.put("corpType", "IDCARD"); //证件类型
        paramsMap.put("corpNo", (String) mailMap.get("idCard")); //身份证号
        paramsMap.put("corpLicInvalidTime", "2020-12-31 00:00:00");
        //联系人姓名为空，默认法人姓名
        String contactName = StringUtils.isBlank((String) mailMap.get("linkmanName")) ? (String) mailMap.get("legalName") : (String) mailMap.get("linkmanName");
        paramsMap.put("contactName", contactName); //联系人姓名
        paramsMap.put("contactPhone", linkManMobile); //联系人电话
        paramsMap.put("contactMail", linkManEmail); //联系人邮箱
        paramsMap.put("bdUser", (String) mailMap.get("salemanName")); //BD人员
        paramsMap.put("signTime", (String) mailMap.get("signatureDate")); //签约时间
        paramsMap.put("opUser", "系统管理员");
        结算信息
        paramsMap.put("settleAccount", (String) mailMap.get("accountNo")); //结算账户（银行卡号）
        paramsMap.put("oaBank", (String) mailMap.get("bankCode")); //开户行（联行号）
        paramsMap.put("oaName", (String) mailMap.get("accountName")); //开户姓名
        开户行账户类型 目前数据存储数据 1：对私 2：对公 
        paramsMap.put("oaType", (String) mailMap.get("accountType"));
        paramsMap.put("clearType", "T"); //清算类型：T:日清, W:周清, M:月清 这里默认日清
        paramsMap.put("clearPeriod", "1"); //清算周期
        paramsMap.put("clearMinAmount", "0"); //最小清算金额
        paramsMap.put("isEntrustOut", "1"); //是否支持委托提现:0.否, 1.是 默认支持
        return paramsMap;
    }

    *//**
     * 根据代理商编码查询商户编码信息
     *
     * @param agentCode
     * @return
     *//*
    public List<String> findMerchantCodeByAgentCode(String agentCode) {
        return dao.findMerchantCodeByAgentCode(agentCode);
    }
    
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updateMerchantInvoice(List<String> mCodeList) {
    	Map<String, Object> pm = new HashMap<String, Object>();
    	pm.put("mCodeList", mCodeList);
    	return dao.updateInvoiceByMCode(pm);
    }
    
    public List<String> queryExistM(List<String> mCodeList) {
    	Map<String, Object> pm = new HashMap<String, Object>();
    	pm.put("mCodeList", mCodeList);
    	return dao.queryExistM(pm);
    }*/
}
