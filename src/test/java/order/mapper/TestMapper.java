package order.mapper;
import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.sgcc.BreakPointApplication;
import com.sgcc.order.entity.SaleOrder;
import com.sgcc.order.enums.ShipStatus;
import com.sgcc.order.mapper.SaleOrderMapper;
import com.sgcc.utils.TimeUtil;

@SpringBootTest(classes=BreakPointApplication.class)
@RunWith(SpringRunner.class)
public class TestMapper{
	
	@Autowired
	private SaleOrderMapper saleOrderMapper;
	
	
	
    @Test
    @Sql(scripts ={"/sql/SaleOrder.sql"})
    @Commit
    public void testSelectByPrimaryKey(){
    	String saleId="20190128110000009933xxx";
    	SaleOrder selectByPrimaryKey = saleOrderMapper.selectByPrimaryKey(saleId);
    	Assert.assertEquals(selectByPrimaryKey.getSaleId(), "20190128110000009933xxx");
    }
	
    @Test
    public void testSave(){
    	String saleId="20190128110000009933xYY";
    	SaleOrder saleOrder = SaleOrder.builder()
    	.withChannelCode("")
    	.withChannelName("")
    	.withChkStat("")
    	.withCloseTime(TimeUtil.getCurrentTime17ByMillis())
    	.withCompleteTime(TimeUtil.getCurrentTime17ByMillis())
    	.withCouponAmount(new BigDecimal("100.35"))
    	.withCreateTime("")
    	.withGoodsCode("")
    	.withOrderAmount(new BigDecimal(100))
    	.withOrderStatus("01")
    	.withPayAmount(new BigDecimal(100))
    	.withPowerWalducAmount(new BigDecimal(100))
    	.withPayComStime("")
    	.withPayOrderId("")
    	.withPayStatus("01")
    	.withPointsAmount(new BigDecimal(100))
    	.withWalletAmount(new BigDecimal(100))
    	.withSaleId(saleId)
    	.withShipStatus(ShipStatus.NOT_SEND_GOODS)
    	.withUserId("cjfjfsjs")
    	.withUserType("01")
    	.withUnBillDetailId("")
    	.withUpdateTime(TimeUtil.getCurrentTime17ByMillis())
    	.withSaleRtnNo("")
    	.withRechargeAmount(new BigDecimal(100))
    	.withPointsNum("")
    	.withReduceAmount(new BigDecimal(100))
    	.withSalePayId("")
    	.withTicketNum("")
    	.build();
    	int row = saleOrderMapper.insert(saleOrder);
    	Assert.assertEquals(row, 1);
    	SaleOrder saleOrder2 = saleOrderMapper.selectByPrimaryKey(saleId);
    	System.out.println(saleOrder2);
    	
    }
    
    @After
    public void clear(){
    	saleOrderMapper.deleteByPrimaryKey("20190128110000009933xxx");
    	//saleOrderMapper.deleteByPrimaryKey("20190128110000009933xxY");
//    	deleteFromTables("SALE_ORDER");
    }
    
   
}
