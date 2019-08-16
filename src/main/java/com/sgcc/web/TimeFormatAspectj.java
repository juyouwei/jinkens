package com.sgcc.web;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSONObject;
import com.sgcc.utils.TimeUtil;

/**
 * 将数据库中的yyyyMMddHHmmssSSS字符串时间转为 
 * yyyy-MM-dd HH:mm:ss的时间 返给前端
 * @author dell
 *
 */
@Aspect
@Component
public class TimeFormatAspectj {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Pointcut(value = "@annotation(com.sgcc.web.TimeFormat)")
	public void timeTransfor() {
	}

	@SuppressWarnings("unchecked")
	@AfterReturning(value = "timeTransfor()",returning ="value")
	public Object timeFormat(JoinPoint joinPoint, Object value) throws Throwable {
		LOGGER.info("时间注解返回结果：{}",JSON.toJSONString(value));
		String jsonString = JSON.toJSONString(value);
	    Map<String,Object> map = JSONObject.parseObject(jsonString,Map.class);
	    Map<String,Object> dataMap = (Map<String, Object>) map.get("data");
	    String createTime =(String) dataMap.get("createTime");
	    String updateTime =(String) dataMap.get("updateTime");
	    if(StringUtils.isNotBlank(createTime)){
	    	createTime=TimeUtil.formatTime17ByMillis(createTime);
	    	dataMap.put("createTime", createTime);
	    }
	    if(StringUtils.isNotBlank(updateTime)){
	    	updateTime=TimeUtil.formatTime17ByMillis(updateTime);
	    	dataMap.put("updateTime", updateTime);
	    }
	    if(value instanceof ResponseResult){
	    	ResponseResult<Object> response=(ResponseResult<Object>)value;
	    	response.setData(dataMap);
	    }
		return value;
	}

}
