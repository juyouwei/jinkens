package web;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.sgcc.BreakPointApplication;
@SpringBootTest(classes=BreakPointApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class APIExceptionHandlerTest {

	private MockMvc mockMvc;
	
	
	 @Autowired
	 private WebApplicationContext webApplicationContext;
	
	 @Before
	 public void setUp() {
	     mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).alwaysDo(MockMvcResultHandlers.print()).build();
	  }

	 @Test
	 public void testAPIExceptionHandler() throws Exception{
		  mockMvc.perform(MockMvcRequestBuilders.post("/api/sale_order/save")
	                .content(JSON.toJSONString(null))
	                .accept(MediaType.APPLICATION_JSON_UTF8)
	                .contentType(MediaType.APPLICATION_JSON_UTF8))
		            .andExpect(MockMvcResultMatchers.status().isOk())
		            .andExpect(MockMvcResultMatchers.content().json("{'code':-1002}",false));//对返回内容
	             
	 }
}
