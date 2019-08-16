package com.sgcc.common.config;

import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import com.alibaba.druid.pool.DruidDataSource;
import com.sgcc.common.CommonEnum;
import com.sgcc.common.component.DataSourceDomain;
import com.sgcc.common.component.TypeAliasesPackageScanner;
import com.sgcc.utils.mybatis.DefaultEnumTypeHandler;

@Configuration
public class DataSourceConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

	@Autowired
	private DataSourceDomain dataSourceDomain;

	@Autowired
	private TypeAliasesPackageScanner typeAliasesPackageScanner;

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		dataSourceDomain.config(druidDataSource);
		return druidDataSource;
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		SqlSessionFactory sqlSessionFactory = sessionFactoryBean.getObject();
		// 取得类型转换注册器
		TypeHandlerRegistry typeHandlerRegistry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
		TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
		String aliasBasePackage = typeAliasesPackageScanner.getTypeAliasesPackages();// 得到实体类和枚举类所在的包，这里其实得到枚举类所在包名即可，为了方便以后扩展，多扫描了实体类所在包
		String[] packages = null;
		if (StringUtils.isNotBlank(aliasBasePackage)) {
			packages = aliasBasePackage.split(",");
		}
		for (String aliasPackage : packages) {
			if (aliasPackage != null && aliasPackage.trim().length() > 0) {
				if (aliasPackage.contains("enums")) {
					ResolverUtil<Class<?>> resolverUtilForEnum = new ResolverUtil<Class<?>>();
					resolverUtilForEnum.find(new ResolverUtil.IsA(CommonEnum.class), aliasPackage.trim());// 查找aliasPackage包下所有CommonEnum的实现类
					Set<Class<? extends Class<?>>> mTypes = resolverUtilForEnum.getClasses();
					// enum
					for (Class<?> javaTypeClass : mTypes) {
						LOGGER.debug("Registered Enum type handler: '" + javaTypeClass.getName() + "'->"
								+ EnumOrdinalTypeHandler.class.getName());
						typeAliasRegistry.registerAlias(javaTypeClass.getName(), javaTypeClass);
						typeAliasRegistry.registerAlias(javaTypeClass.getName().replace("$", "."), javaTypeClass);
						typeHandlerRegistry.register(javaTypeClass, DefaultEnumTypeHandler.class);//注册枚举类型转换器
					}
				}
			}
		}
		return sqlSessionFactory;
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

}
