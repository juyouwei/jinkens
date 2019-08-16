package com.sgcc.main;
import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Pattern;
import com.alibaba.fastjson.JSON;
import com.sgcc.utils.TimeUtil;
public class BaseDecodeTest {
	public static void main(String[] args) throws Exception {
		
	    	String regex = "^[-\\+]?\\d+$"; 
	    	  String digit="50";
	    	  
	    	  String[] split = digit.split("\\.");
	    	  System.out.println(split[0]);
	         System.out.println(Pattern.matches(regex,digit)); 
	         
	         System.out.println("BUY_SYN".getBytes());
	         System.out.println(TimeUtil.isToday(new Date(System.currentTimeMillis())));
	        System.out.println(TimeUtil.formatTime17ByMillis(TimeUtil.getCurrentTime17ByMillis()));
	        
	        
	        String strList="222";
	        String[] split2 = strList.split("\\|");
	         
	        System.out.println(JSON.toJSONString(split2));
	        System.out.println();
	        BigDecimal bigDecimal = new BigDecimal("50.0");
	} 
	}
  

