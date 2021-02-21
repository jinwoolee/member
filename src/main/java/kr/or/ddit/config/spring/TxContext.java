package kr.or.ddit.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class TxContext {

    @Bean
    public TransactionManager txManager(@Autowired LocalContainerEntityManagerFactoryBean emFactoryBean) {
	JpaTransactionManager txManager = new JpaTransactionManager();
	txManager.setEntityManagerFactory(emFactoryBean.getObject());
	return txManager;
    }
}
