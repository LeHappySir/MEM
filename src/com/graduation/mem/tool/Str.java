package com.graduation.mem.tool;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;

public class Str {
/*	public static void main(String[] args) throws Exception {

	}*/
	
	private static final String Number="0123456789";
	private static final int DouNum=8;//标准的小数位数
	public String str;
	
	public Str(){
	}
	
	public Str(String s){
		if(IsNull(s))s="";
		this.str=s;
	}
	
	public Str(int n){
		this.str=String.valueOf(n);
	}	
	
	public Str(double n){
		this.str=String.valueOf(n);
	}	
	
	public Str(String[] n){
		this.str=ArrToString(n);
	}
	
	//判断文本为空
	public static boolean IsNull(String c) {
    	/*if (c!= null && !c.equalsIgnoreCase("null") && !c.equalsIgnoreCase("") && !c.equalsIgnoreCase("undefined")) {
    	    return false;
    	}else{
    		return true;
    	}*/
		if(c==null || c.trim().isEmpty() || c.equalsIgnoreCase("null") || c.equalsIgnoreCase("undefined")){
			return true;
		}else{
			return false;
		}
    }
	
	public static boolean IsNotNull(String c){
		return !IsNull(c);
	}
	
	public boolean IsNull() {
    	return IsNull(this.str);
    }	
	
	//去除转行等容易出错的字符
    public static String EscapeStr(String c) {
    	if(IsNull(c))return "";
        c=c.replace("\r\n","          ");
        c=c.replace("\r","          ");
        c=c.replace("\n","          ");
        c=c.replace("\t","          ");
        c=c.replace("\\"," ");
        c=c.replace("\""," ");//会导致Json格式的"消失
        //System.out.println(c);
        return c;
    }

    public Str EscapeStr() {
    	this.str=EscapeStr(this.str);
    	return this;
    }
    
  
   
    
    //文本四舍五入取整
    public static int Int(String s){
    	Double dou=Dou(s,0);
    	int d=dou.intValue();
    	//s=String.valueOf(d);//更改对象值
    	return d;
    }
    
  //数值四舍五入取整
    public static int Int(double dou){
    	Double dou1=Dou(dou,0);
    	return dou1.intValue();
    }
    
    //文本转数值以标准位数四舍五入
    public static double Dou(String s){
    	return Dou(s,DouNum);
    }
    
    //文本转数值以指定位数四舍五入
    public static double Dou(String s,int f){
    	//String s=this.str;
    	double d=0;
    	if(!IsNull(s)){
    		try{
    			d=Double.parseDouble(s);
    		}catch(NumberFormatException e){
    			d=0.0;
    		}
    	}
    	d=Dou(d,f);
    	//this.str=String.valueOf(d);//更改对象值
        return d;
    }
    
    //将数值以标准位数四舍五入
    public static double Dou(double dou){
        return Dou(dou,DouNum);
    }
    
    //将数值以指定位数四舍五入
    public static double Dou(double dou,int f){
    	try{
    		dou=new BigDecimal(dou).setScale(f, BigDecimal.ROUND_HALF_UP).doubleValue();
    	}catch(NumberFormatException e){
    		dou=0.0;
    	}
        return dou;
    }
    
    public static String douFormat(double dou){
    	return douFormat(dou,DouNum);
    }
    
    /**
     * 把浮点数转换成指定位数的字符串，用于保留数据的精度
     * @param dou
     * @param f
     * @return
     * @change 设置常用的精度，提高性能  20170427
     * @author 陈志东
     * @at     20170321
     */
    public static String douFormat(double dou,int f){
    	
    	StringBuffer sb=new StringBuffer("###############0.0");
    	if(f==8){
    		sb.append("#######");
    	}else if(f==2){
    		sb.append("#");
    	}else if(f==4){
    		sb.append("###");
    	}else{
    		if(f>1){
        		f=f>DouNum?DouNum:f;
        		f=f-1;
        		for(int i=0;i<f;i++){
        			sb.append("#");
        		}
        	}
    	}
    	DecimalFormat df=new DecimalFormat(sb.toString());
    	return df.format(dou);
    }
    
    //除法
    public static double Div(double a,double b){
    	if(b==0)return 0;
    	return Dou(a/b);
    }
    
    //PlusChar("1s000",1)="1s001";数字文本加减
    public Str PlusChar(int d) {
    	String s=this.str;
    	String a="";//数字前的文本
    	String b="";//后面的数字
    	boolean c=true;//数字模式
    	for(int i=s.length();i>0;i--){
    		String e=s.substring(i-1, i);
    		if(c){
    			if(Number.indexOf(e)<0){c=false;}
    			else{b=e+b;}//串数字
    		}
    		if(!c)a=e+a;//串文本
    	}
    	int f=b.length();//原数字的长度
    	d=Integer.parseInt(b)+d;//文本转数字
    	if(d<0)d=0;
        b=String.valueOf(d);//数字转文本
        if(f>b.length()){//小于原长度则补0
        	do{
        		b="0"+b;
        	}while(f>b.length());
        }else{//大于原长度则截取
        	b=b.substring(b.length()-f);
        }
        this.str= a+b;
        return this;
    }
    
    public static String AddQuote(String str){
    	String[] strarr=str.toString().split(",");
    	String strR="";
    	for(int i=0;i<strarr.length;i++){
    		if(i>0)strR+=",";
    		strR+="'"+strarr[i]+"'";
    	}
        return strR;
    }

    public static String En(String s){//加密
		try {
			s=URLEncoder.encode(s,"UTF-8");
			s=s.replaceAll("\\+", "%20");//encode会将空格变成+，需要再转一次
			return s;
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return "";
    }
    
    public static String De(String s){//解密
		try {
			s=URLDecoder.decode(s,"UTF-8");
			//s=s.replaceAll("%20", " ");//encode会将空格变成+
			//System.out.println(s);
			return s;
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return "";
    }
    
    private static String ArrToString(String[] arr){//数组转数组文本
    	String str="";
    	for(int i=0;i<arr.length;i++){
    	    if(i>0)str+=",";
    	    str+=arr[i];
    	}
        return str;
    }
    
    public static String Where(String w,String s){
    	if(Str.IsNull(w))return "";
    	return " "+s+" ("+w+") ";
    }
    
    public static void Bug(boolean b){
    	System.out.println(new Day(0).str("yyyy-MM-dd HH:mm:ss")+" : "+b);
    	CutOff();
    }  
    
    public static void Bug(String s){
    	System.out.println(new Day(0).str("yyyy-MM-dd HH:mm:ss")+" : "+s);
    	CutOff();
    }
    
    public static void Bug(int s){
    	System.out.println(new Day(0).str("yyyy-MM-dd HH:mm:ss")+" : "+s);
    	CutOff();
    }
    
	public static void Bug(long s) {
		System.out.println(new Day(0).str("yyyy-MM-dd HH:mm:ss")+" : "+s);
		CutOff();
	}
    
	public static void Bug(double s) {
		System.out.println(new Day(0).str("yyyy-MM-dd HH:mm:ss")+" : "+s);
		CutOff();
	}
	
          

	public static void Bug(StringBuffer sql) {
		System.out.println(new Day(0).str("yyyy-MM-dd HH:mm:ss")+" : "+sql.toString());
		CutOff();
	}
	
    private static void CutOff(){
    	System.out.println("");
    	//System.out.println("---- cut-off rule ----");
    } 
    
  
  
    /**
     * 比较字符大小
     * @param a 前字符
     * @param o 比较符,==、!=、>、>=、<、<=
     * @param b 后字符
     * @return 
     * @author moon
     */
    public static boolean Compare(String a,String o,String b){
    	boolean r=false;//默认不满足
    	switch(o){
            case "!=":
                r=a.compareTo(b)!=0;
                break;
            case ">":
            	r=a.compareTo(b)>0;
                break;
            case "<":
            	r=a.compareTo(b)<0;
                break;
            case ">=":
            	r=a.compareTo(b)>=0;
                break;
            case "<=":
            	r=a.compareTo(b)<=0;
                break;
            default:
            	r=a.compareTo(b)==0;
        }
    	return r;
    }
	
}
