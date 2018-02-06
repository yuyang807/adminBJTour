package com.yy.bjtours.modules.sys.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * @author: zs
 * @date :2016年5月10日 下午4:01:03
 */
public class DateUtils {
	/**
	 * @brief
	 *		时间比对
	 * @details
	 *		根据系统时间和页面时间进行比对，返回相应的结果
	 * @author
	 *	- 2016年5月10日 zs
	 * @param systemDate
	 * @param pageDate
	 * @return result
	 */
	public static int dateCompare(Date systemDate, String pageDate){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = sf.format(systemDate).toString();
		int sysYear, sysMonth, sysDay;
		int currYear, currMonth, currDay;
		String[] decoDate = sysDate.split("-");
		String systemYear = decoDate[0];
		String systemMon = decoDate[1];
		String systemDay = decoDate[2];
		sysYear = Integer.parseInt(systemYear);
		sysMonth = Integer.parseInt(systemMon);
		sysDay = Integer.parseInt(systemDay);
		
		Calendar sysCal = Calendar.getInstance();
		sysCal.set(sysYear, sysMonth, sysDay);
		
		String[] decoPageDate = pageDate.split("-");
		String yearStr = decoPageDate[0];
		String monthStr = decoPageDate[1];
		String dayStr = decoPageDate[2];
		currYear = Integer.parseInt(yearStr);
		currMonth = Integer.parseInt(monthStr);
		currDay = Integer.parseInt(dayStr);
		
		Calendar currCal = Calendar.getInstance();
		currCal.set(currYear, currMonth, currDay);
		
		int result = sysCal.compareTo(currCal);
		return result;
	}
	
	/**
	 * @brief	获取当前时间后几天的日期(yyyy-MM-dd)
	 * @author
	 *	- 2017年6月21日 zs
	 * @param	day
	 * @return	date
	 */
	public static String getBeforeXDay(int day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.DAY_OF_MONTH, -day);
		currentDate = cal.getTime();
		String date = sdf.format(currentDate);
		return date;
	}
	/**
	 * @brief	获取指定日期的前一天
	 * @author
	 *	- 2017年6月16日 zs
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayBefore(String specifiedDay){ 
		Calendar c = Calendar.getInstance(); 
		Date date=null; 
		try { 
		date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay); 
		} catch (ParseException e) { 
		e.printStackTrace(); 
		} 
		c.setTime(date); 
		int day=c.get(Calendar.DATE); 
		c.set(Calendar.DATE,day-1); 

		String dayBefore=new SimpleDateFormat("yyyyMMdd").format(c.getTime()); 
		return dayBefore; 
	}
	
	public static void main(String[] args) {
		/*int result = dateCompare(new Date(),"2016-5-10");
		System.out.println(result);
		if(result == -1){
			System.out.println("-----------");
		}else if(result == 1){
			System.out.println("++++++++++");
		}else{
			System.out.println("00000000000");
		}*/
		System.out.println(getBeforeXDay(3));
		getSpecifiedDayBefore("2017-07-01");
	}
}
