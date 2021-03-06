package order.service;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.sgcc.BreakPointApplication;
import com.sgcc.order.entity.SaleOrder;
import com.sgcc.order.mapper.SaleOrderMapper;
import com.sgcc.order.service.impl.OrderServiceImpl;
@SpringBootTest(classes=BreakPointApplication.class)
@RunWith(SpringRunner.class)
public class OrderServiceTest {
	
	@Mock
    private SaleOrderMapper saleOrderMapper;
	
	@Autowired
	private OrderServiceImpl orderService;
	    @Test
	    public void testSyncOrder(){
	    	SaleOrder saleOrder=new SaleOrder();
	    	String saleId="20190128110000009933xxx";
	    	saleOrder.setSaleId(saleId);
	    	saleOrder.setOrderAmount(new BigDecimal(100));
	    	saleOrder.setOrderStatus("02");
	    	when(saleOrderMapper.selectByPrimaryKey(saleId)).thenReturn(saleOrder);
	    }
	
}
