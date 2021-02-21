package kr.or.ddit.config.spring;

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
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
	factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
	return factoryBean;
    }
}
