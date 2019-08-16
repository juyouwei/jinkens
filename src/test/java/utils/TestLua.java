package utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.sgcc.BreakPointApplication;
import com.sgcc.utils.RedisUtil;

@SpringBootTest(classes=BreakPointApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class TestLua {
	
	@Test
	public void test01(){
		
		String busiId = RedisUtil.getBusiId("9");
		busiId=busiId.replace("{key}", "");
		int length = busiId.length();
		System.out.println(busiId);

	}
	
}
