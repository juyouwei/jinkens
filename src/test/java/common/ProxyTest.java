package common;
import com.sgcc.common.component.mq.AbstractMessageHandler;
import com.sgcc.common.component.mq.MQMessageHandler;
import com.sgcc.mqmessage.OrderMessageHandler;

public class ProxyTest {
  public static void main(String[] args) {
	  MQMessageHandler message=new OrderMessageHandler();
	  AbstractMessageHandler ab=(AbstractMessageHandler)message;
	  System.out.println(ab);
	  
}
}
