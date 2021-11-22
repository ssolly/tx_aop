package com.care.root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class TxConfig {	//트랜잭션

	@Autowired HikariDataSource ds;
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager txMgr = new DataSourceTransactionManager();
		txMgr.setDataSource(ds);
		return txMgr;
	}
	
	/* root-context.xml에서의 설정 방법 : 동일한 역할이지만 설정 방법이 다르다
	 <bean name="txMgr" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	 	<property name="dataSource" ref="ds" />
	 </bean>
	  */
	
}
