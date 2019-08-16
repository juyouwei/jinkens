package recharge.mapper;
import java.math.BigDecimal;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sgcc.BreakPointApplication;
import com.sgcc.order.entity.JyRechargeRegMain;
import com.sgcc.order.mapper.JyRechargeRegMainMapper;

import junit.framework.Assert;

@SpringBootTest(classes=BreakPointApplication.class)
@RunWith(SpringRunner.class)
public class RechargeTest {
	
	@Autowired
	private JyRechargeRegMainMapper jyRechargeRegMainMapper;
	@Test
	public void testSave(){
		 JyRechargeRegMain jyRechargeRegMain = JyRechargeRegMain.builder()
		 .withCheckState("")
		 .withCzkCheckDescription("")
		 .withCzkSysId(UUID.randomUUID().toString().replaceAll("-", ""))
		 .withElecustomerNo("")
		 .withIsCheck("")
		 .withOrderId("")
		 .withPowerCompanyCode("")
		 .withPowerCompanyNm("")
		 .withProvCode("")
		 .withReason("")
		 .withReqTime("")
		 .withState("")
		 .withSysId(UUID.randomUUID().toString().replaceAll("-", ""))
		 .withTotalAmont(new BigDecimal("100.365"))
		 .withUpdateTime(com.sgcc.utils.TimeUtil.getCurrentTime17ByMillis())
		 .withUserAddr("")
		 .withUserIdentification("")
		 .withUserName("")
		 .build();
		 int row = jyRechargeRegMainMapper.insert(jyRechargeRegMain);
		Assert.assertEquals(1, row);
	}

}
