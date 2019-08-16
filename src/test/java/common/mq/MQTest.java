package common.mq;

import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;
import com.sgcc.BreakPointApplication;
import com.sgcc.common.component.mq.MQMessage;
import com.sgcc.common.component.mq.MQProducerFactory;
import com.sgcc.common.component.mq.SimpleMQProducer;
import com.sgcc.utils.TimeUtil;

@SpringBootTest(classes=BreakPointApplication.class)
@RunWith(SpringRunner.class)
public class MQTest {

	@Value("${message-queue.topics}")
	private String topic;
	@Autowired
	private MQProducerFactory mqProducerFactory;

	@Rule
	public OutputCapture outputCapture=new OutputCapture();

	@Test
	public void testSendAndReceive() throws InterruptedException {

		SimpleMQProducer simpleMQProducer = mqProducerFactory.createDefaultProducer();

		String messageKey = TimeUtil.getCurrentTime17ByMillis();
		simpleMQProducer.send(topic, "4TEST", new MQMessage("_"+messageKey, "TEST"));
		
		Thread.sleep(1000*30);
		Assert.assertThat(outputCapture.toString(), containsString("MQ|RECEIVE|_" + messageKey));
		
	}

}
