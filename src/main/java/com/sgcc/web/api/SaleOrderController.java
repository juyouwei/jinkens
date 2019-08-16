package com.sgcc.web.api;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sgcc.order.entity.JyRechargeRegMain;
import com.sgcc.order.service.OrderService;
import com.sgcc.utils.RedisUtil;
import com.sgcc.web.ResponseResult;
import com.sgcc.web.TimeFormat;


@RestController
@RequestMapping("/api/sale_order")
public class SaleOrderController {

	@Autowired
	private OrderService orderService;
	

	@RequestMapping("/save")
	public ResponseResult<Object> saveSaleOrder() {
		orderService.save(null);
		return new ResponseResult<>(null);
	}
	
	@TimeFormat(value="yyyy-MM-dd HH:mm:ss")
	@GetMapping("/find")
	public ResponseResult<Object> findOrder(String sysId) {
		JyRechargeRegMain jyRechargeRegMain = orderService.findJyRechargeRegMain(sysId);
		return ResponseResult.success(jyRechargeRegMain);
	}
	
	@PostMapping("/test")
	public ResponseResult<Object> test(String type) {
		CountDownLatch startSignal = new CountDownLatch(1);

        for (int i = 0; i < 10; ++i) {
            new Thread(new Task(startSignal)).start();
        }

        startSignal.countDown();
		return ResponseResult.success("ok");
	}
	 class Task implements Runnable {
        private final CountDownLatch startSignal;
        private Vector<String> s = new Vector<>();
        Task(CountDownLatch startSignal) {
            this.startSignal = startSignal;
        }

        public void run() {
            try {
                String seq = RedisUtil.getBusiId("9");
                seq=seq.replace("{key}.", "");
                System.out.println(seq);
                if (s.contains(seq)) {
                   throw new RuntimeException("重复");
                } else {
                    s.add(seq);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
