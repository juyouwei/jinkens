package com.sgcc.main;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jna.Library;
import com.sun.jna.Native;

//继承Library，用于加载库文件
public class Clibrary  {
	private static final Logger LOG = LoggerFactory.getLogger(Clibrary.class);
	private static String no = "0";
	private static String dateValue = "";//默认精确到毫秒的比对时间
	private static Random rand = new Random();
 
 
	public static synchronized String next() throws UnknownHostException {
		String ipStr = InetAddress.getLocalHost().getHostAddress();// 获取本机IP
		ipStr = ipStr.replace(".", "");
		if(ipStr.length() > 5){
			ipStr = ipStr.substring(ipStr.length()-2, ipStr.length());
		}
		String dateStr = new SimpleDateFormat("yyMMdd").format(new Date());
		String num = new StringBuilder()
				.append("9")
				.append(dateStr)
			    .append(ipStr).toString();
		if (!(String.valueOf(dateStr)).equals(dateValue)) {//不是同一个时段，从0开始递增序号
			no = "0";
			dateValue = dateStr;
		}
		num += getNo(no, num);
		return num;
	}
 
 
	/**
	 * 返回当前序号+1
	 */
	public static String getNo(String noCount, String num) {
		long i = Long.parseLong(noCount);
		i += 1;
		noCount = "" + i;
		for (int j = noCount.length(); j < 11 - num.length(); j++) {
			noCount = "0" + noCount;
		}
		no = noCount;
		return noCount;
	}
 
 
	/**
	 * 利用Set中不允许有重复的元素的特性，来判断集合元素中是否有重复元素
	 * 
	 * @param list:被判断的集合
	 * @return false:有重复元素或list为null; true:没有重复元素
	 */
	@SuppressWarnings("unused")
	private static boolean hasSame(List<? extends Object> list) {
		if (null == list)
			return false;
		return list.size() == new HashSet<Object>(list).size();
	}
	
	/**
	 * next()+5位随机数
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getTransactionId() throws UnknownHostException{
		return new StringBuilder().append(next())
		.append(String.valueOf(rand.nextInt(99999 - 10000 + 1) + 10000)).toString();
	}


  
}
