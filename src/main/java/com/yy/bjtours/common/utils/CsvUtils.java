package com.yy.bjtours.common.utils;

import com.csvreader.CsvWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 * CsvUtils
 *
 * @author lufl
 * @date 2016/12/22
 */
public class CsvUtils {
    protected static final String[] headers = new String[]{"交易时间", "订单号", "终端号", "支付类型", "签约人员",
            "代理商编号", "商户编号", "商户名称", "签购单名称", "交易金额", "商户交易手续费", "结算金额", "通道成本手续费",
            "手续费收益"};

    /**
     * 生成CSV文件头部
     *
     * @param writer
     * @throws IOException
     */
    public static void writeHeader(CsvWriter writer) throws IOException {
        writer.writeRecord(headers);
    }


//    public static void writeRecord(CsvWriter writer, PosSettlementList settlement) throws IOException {
//        String[] records = new String[headers.length];
//        records[0] = "\t" + DateUtils.formatDate(settlement.getTradeDate(), "yyyy-MM-dd HH:mm:ss");
//        records[1] = "\t" + settlement.getOrderNo();
//        records[2] = "\t" + settlement.getTerminalNo();
//        records[3] = "\t" + getAccountType(settlement.getChkAccountType());
//        records[4] = "\t" + settlement.getSalemanName();
//        records[5] = "\t" + settlement.getBelongTo();
//        records[6] = "\t" + settlement.getMerchantCode();
//        records[7] = "\t" + settlement.getMerchantName();
//        records[8] = "\t" + (StringUtils.isEmpty(settlement.getSignPlistName()) ? "" : settlement.getSignPlistName());
//        records[9] = "\t" + BigDecimal.valueOf(settlement.getTradeAmt()).movePointLeft(2).doubleValue();
//        records[10] = "\t" + BigDecimal.valueOf(settlement.getFeeAmt()).movePointLeft(2).doubleValue();
//        records[11] = "\t" + BigDecimal.valueOf(settlement.getSettlementAmt()).movePointLeft(2).doubleValue();
//        records[12] = "\t" + BigDecimal.valueOf(settlement.getChannelFeeAmt()).movePointLeft(2).doubleValue();
//        records[13] = "\t" + BigDecimal.valueOf(settlement.getFeeAmtEarn()).movePointLeft(2).doubleValue();
//        writer.writeRecord(records, true);
//    }

    private static String getAccountType(Integer accountType) {
        String result;
        if (Integer.valueOf(1).equals(accountType)) {
            result = "收单";
        } else if (Integer.valueOf(2).equals(accountType)) {
            result = "支付宝";
        } else if (Integer.valueOf(3).equals(accountType)) {
            result = "微信";
        } else if (Integer.valueOf(4).equals(accountType)) {
            result = "支付宝";
        } else {
            result = "未知";
        }
        return result;
    }

}
