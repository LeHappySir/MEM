package com.graduation.mem.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Day {
	
	public static final String STANDARD_FORMAT="yyyy-MM-dd";//标准格式
	public static final String SECOND="yyyy-MM-dd HH:mm:ss";
	public static final String MILLI_SECOND="yyyy-MM-dd HH:mm:ss:SSS";
	Calendar cal;
	Date date;
	
	public static void main(String[] args){

		Day day=new Day();
		day.set(2016, 1, 1);
		System.out.println(day.getYear());
		System.out.println(day.getMonth());
		System.out.println(day.MS(""));
		System.out.println(day.ME(""));
	}
/**
 * 默认是当时时间
 * @author moon
 */
	public Day(){
		this.date=new Date();
		Calendar cal=Calendar.getInstance();
        cal.setTime(this.date);
		this.cal=cal;
	}
	
	public Day(String str,String format){
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			this.date = formatter.parse(str);
		} catch (ParseException e) {
			this.date=new Date();
			e.printStackTrace();
		}
		Calendar cal=Calendar.getInstance();
        cal.setTime(this.date);
		this.cal=cal;
	}
	
	public Day(long gettime){
		if(gettime>0){
			this.date=new Date(gettime);
		}else{
			this.date=new Date();
		}
	}
	
	public Day(Date d){
		this.date=d;
	}
	
	//格式化日期
	/**
	 * 格式化日期
	 * @param format 格式，如：Day.MILLI_SECOND 返回有三位毫秒的日期字符串；Day.STANDART_FORMAT 返回不带时间
	 * 的日期字符串；Day.SECOND 返回带秒数的日期字符串
	 * @return
	 */
	public String str(String format){
		if(Str.IsNull(format))format=Day.MILLI_SECOND;
		return StrFormat(this.date,format);
	}
	
	//返回long类型的日期
	public long getLong(){
		return date.getTime();
	}
	
	//返回某个格式的整数
	public int Int(String f){
		return Integer.parseInt(StrFormat(this.date,f));
	}
	//用标准格式格式日期部分
	public String strDate(String f){
		if(new Str(f).IsNull())f=STANDARD_FORMAT;
		return this.str(f);
	}
	//用标准格式格式时间部分
	public String strTime(String f){
		if(new Str(f).IsNull())f="HH:mm:ss";
		return this.str(f);
	}
	
	public void set(int year,int month,int day){
		this.cal.set(year, month, day);
	}

	/**
	 * 获得当前年份
	 * @return
	 */
	public String getYear(){
		return ""+this.cal.get(Calendar.YEAR);
	}
	/**
	 * 取时间的月份值
	 * @return 小于10会在前面补0
	 */
	public String getMonth(){
		
		int month=this.cal.get(Calendar.MONTH)+1;
		return formatMonth(month);
	}
	
	public String getLastMonth(){
		
		int month=this.cal.get(Calendar.MONTH);
		return formatMonth(month);
	}
	
	private String formatMonth(int month){
		
		if(month<10){
			return "0"+month;
		}else{
			return ""+month;
		}
	}
	
	public String getDay(){
		
		int day=this.cal.get(Calendar.DAY_OF_MONTH);
		if(day<10){
			return "0"+day;
		}else{
			return ""+day;
		}
	}
	/**
	 * 全年第几天
	 * @return
	 */
	public int YearDays(){
        return this.cal.get(Calendar.DAY_OF_YEAR);
	}
	
	public int MonthDays(){//全月第几天
        return this.cal.get(Calendar.DAY_OF_MONTH);
	}

	public int WeekDays(){//全周第几天
        return this.cal.get(Calendar.DAY_OF_WEEK);
	}

	public int YearWeeks(){//全年第几周
        return this.cal.get(Calendar.WEEK_OF_YEAR);
	}
	/**
	 * 周的第一天
	 * @param f 格式化
	 * @return
	 * @author moon
	 */
	public String WS(String f){//周初
		Calendar Cal=this.cal;
		Cal.set(Calendar.DAY_OF_WEEK,1);
        if(Str.IsNull(f))f=STANDARD_FORMAT;
        return StrFormat(Cal.getTimeInMillis(),f);
	}
	/**
	 * 周的最后一天
	 * @param f 格式化
	 * @return
	 * @author moon
	 */
	public String WE(String f){//周末
		Calendar Cal=this.cal;
		Cal.set(Calendar.DAY_OF_WEEK,7);
        if(Str.IsNull(f))f=STANDARD_FORMAT;
        return StrFormat(Cal.getTimeInMillis(),f);
	}
	/**
	 * 月的第一天
	 * @param f 格式化
	 * @return
	 * @author moon
	 */
	public String MS(String f){//月初
		Calendar Cal=this.cal;
		Cal.set(Calendar.DAY_OF_MONTH, 1);
        if(Str.IsNull(f))f=STANDARD_FORMAT;
        return StrFormat(Cal.getTimeInMillis(),f);
	}
	/**
	 * 月的最后一天
	 * @param f 格式化
	 * @return
	 * @author moon
	 */
	public String ME(String f){//月末
		Calendar Cal=this.cal;
		int y=Cal.get(Calendar.YEAR);//取年
		int m=Cal.get(Calendar.MONTH)+1;//取月
		if(m>11){y++;m=0;}
		Cal.set(y, m, 0);
        if(Str.IsNull(f))f=STANDARD_FORMAT;
        return StrFormat(Cal.getTimeInMillis(),f);
	}
	/**
	 * 季的第一天
	 * @param f 格式化
	 * @return 季度第一天
	 * @author moon
	 */
	public String SS(String f){
		Calendar Cal=this.cal;
		int y=Cal.get(Calendar.YEAR);//取年
		int m=Cal.get(Calendar.MONTH)+1;//取月
		if(m<=3){m=1;
		}else if(m<=6){m=4;
		}else if(m<=9){m=7;
		}else m=10;
		Cal.set(y, m-1, 1);
        if(Str.IsNull(f))f=STANDARD_FORMAT;
        return StrFormat(Cal.getTimeInMillis(),f);
	}
	/**
	 * 季度的最后一天
	 * @param f 格式化
	 * @return 季度的最后一天
	 * @author moon
	 */
	public String SE(String f){
		Calendar Cal=this.cal;
		int y=Cal.get(Calendar.YEAR);//取年
		int m=Cal.get(Calendar.MONTH)+1;//取月
		if(m<=3){m=1;
		}else if(m<=6){m=4;
		}else if(m<=9){m=7;
		}else m=10;
		Cal.set(y, m, 0);
        if(Str.IsNull(f))f=STANDARD_FORMAT;
        return StrFormat(Cal.getTimeInMillis(),f);
	}
	/**
	 * 年的第一天
	 * @param f 格式化
	 * @return
	 * @author moon
	 */
	public String YS(String f){//年初
		Calendar Cal=this.cal;
		int y=Cal.get(Calendar.YEAR);//取年
		Cal.set(y, 0, 1);
        if(Str.IsNull(f))f=STANDARD_FORMAT;
        return StrFormat(Cal.getTimeInMillis(),f);
	}
	/**
	 * 年的最后一天
	 * @param f 格式化
	 * @return
	 * @author moon
	 */
	public String YE(String f){//年末
		Calendar Cal=this.cal;
		int y=Cal.get(Calendar.YEAR)+1;//取年
		Cal.set(y, 0, 0);
		//Cal.add(Calendar.DATE,-1);//减去1天
        if(Str.IsNull(f))f=STANDARD_FORMAT;
        return StrFormat(Cal.getTimeInMillis(),f);
	}
	/**
	 * 日期加减
	 * @param p 加减的对象，y年、M月、w周、d天、h时、m分、s秒
	 * @param d 加减的数量
	 * @return 新的日期对象
	 * @author moon
	 */
	public Day Add(String p,int d){//+-时间
		Calendar Cal=this.cal;
		if(p.equals("y"))Cal.add(Calendar.YEAR,d);//年加减
		if(p.equals("M"))Cal.add(Calendar.MONTH,d);//月加减
		if(p.equals("w"))Cal.add(Calendar.WEEK_OF_YEAR,d);//周加减
		if(p.equals("d"))Cal.add(Calendar.DATE,d);//天加减
		if(p.equals("h"))Cal.add(Calendar.HOUR,d);//时加减
		if(p.equals("m"))Cal.add(Calendar.MINUTE,d);//分加减
		if(p.equals("s"))Cal.add(Calendar.SECOND,d);//秒加减
		this.cal=Cal;
		this.date=new Date(Cal.getTimeInMillis());
		return this;
	}
	
    static String StrFormat(Date d,String format){
    	SimpleDateFormat f = new SimpleDateFormat(format);
		return f.format(d);
    }
    
    static String StrFormat(long d,String format){
    	SimpleDateFormat f = new SimpleDateFormat(format);
		return f.format(d);
    }
}
