//package com.sgcc.utils.mybatis;
// 
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URISyntaxException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.log4j.BasicConfigurator;
//import org.mybatis.generator.api.MyBatisGenerator;
//import org.mybatis.generator.config.Configuration;
//import org.mybatis.generator.config.xml.ConfigurationParser;
//import org.mybatis.generator.exception.InvalidConfigurationException;
//import org.mybatis.generator.exception.XMLParserException;
//import org.mybatis.generator.internal.DefaultShellCallback;
//
//
///**
// * @title 逆向工程启动类
// * @author meng xiang fa
// * @date 2018.07.17
// */
//public class StartUp {
//    public static void main(String[] args) throws URISyntaxException {
//        try {
//        	BasicConfigurator.configure(); 
//            List<String> warnings = new ArrayList<String>();
//            boolean overwrite = true;
//            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//            if(null == classloader){
//            	throw new RuntimeException("系统异常");
//            }
//            InputStream is = classloader.getResourceAsStream("generatorConfig.xml");
//            ConfigurationParser cp = new ConfigurationParser(warnings);
//            Configuration config=null;
//            try {
//            	config = cp.parseConfiguration(is);
//			} catch (IOException e) {
//				 e.printStackTrace();
//			}finally {
//				try {
//					is.close() ;
//				} catch (IOException e2) {
//					 e2.printStackTrace();
//				}
//				
//			}
//            

//            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//            if(myBatisGenerator!=null){
//            	myBatisGenerator.generate(null);
//            }
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (InvalidConfigurationException e) {
//            e.printStackTrace();
//        } catch (XMLParserException e) {
//            e.printStackTrace();
//        }
//    }
//}
