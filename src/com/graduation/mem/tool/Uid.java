package com.graduation.mem.tool;

public class Uid {

/*	private String model;

	public Uid(String model){
		this.model=model;
	}*/
	
	public Uid(){
		//this.model="i";
	}
	//生成ID
	public static String GetId(int len) {
		//if(this.model.equals("i"))return IUid(len);
		return IUid(len);
	}
	//标准长度
	public static String GetId() {
		//if(this.model.equals("i"))return IUid(20);
		return IUid(20);
	}
	
    //生成日期+随机字母生成的ID
	/**
	 * 生成日期+随机字母生成的ID
	 * @param len 长度，<=5时，全部是随机码<br>
	 * <=20时，后5位是随机码，前面补时间<br>
	 * >20时，前面15位时间，后面全部随机
	 * @return
	 */
	private static String IUid(int len) {
    	String charlist="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    	//String tempid=new Day(0).str(null);//取当前日期时间
    	//tempid=tempid.replace("-", "").replace(" ", "").replace(":", "").substring(2);//将年份的千和百去掉
    	StringBuffer r=new StringBuffer();
    	if(len<=5){
    		for(int i=0;i<len;i++){
        		double Temp=Math.floor(Math.random()*62);
        		r.append(charlist.substring((int)Temp, (int)Temp+1));
        	}
    	}else if(len<=20){
    		String tempid=new Day(0).str(null);//取当前日期时间
        	tempid=tempid.replace("-", "").replace(" ", "").replace(":", "").substring(2+15+5-len);
        	r=new StringBuffer(tempid);
        	for(int i=0;i<5;i++){
        		double Temp=Math.floor(Math.random()*62);
        		r.append(charlist.substring((int)Temp, (int)Temp+1));
        	}
    	}else{
    		String tempid=new Day(0).str(null);//取当前日期时间
        	tempid=tempid.replace("-", "").replace(" ", "").replace(":", "").substring(2);
        	r=new StringBuffer(tempid);
        	for(int i=0;i<len-15;i++){
        		double Temp=Math.floor(Math.random()*62);
        		r.append(charlist.substring((int)Temp, (int)Temp+1));
        	}
    	}
        return r.toString();
    }   
}
