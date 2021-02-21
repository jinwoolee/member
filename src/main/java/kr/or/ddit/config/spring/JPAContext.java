package kr.or.ddit.config.spring;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import kr.or.ddit.ScanIterface;

@ComponentScan(basePackageClasses= {ScanIterface.class}, useDefaultFilters=false,
		includeFilters= {@Filter(type=FilterType.ANNOTATION, classes= {Repository.class, Service.class})})
@Configuration
public class JPAContext {

    @Bean
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
	return new HibernateJpaVendorAdapter();
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Autowired DataSource dataSource) {
	LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
	
	factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
	factoryBean.setDataSource(dataSource);
	
	Properties properties = new Properties();
	properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	properties.put("hibernate.hbm2ddl.auto", "create");
	properties.put("hibernate.show_sql", "true");
	properties.put("hibernate.format_sql", "true");
	properties.put("hibernate.use_sql_comments", "true");

	factoryBean.setJpaProperties(properties);
	
	return factoryBean;
    }
}
