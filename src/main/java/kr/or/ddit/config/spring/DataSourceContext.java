package kr.or.ddit.config.spring;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DataSourceContext {
    
    @Bean
    public DataSource dataSource() {
//	DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	dataSource.setUrl("jdbc:h2:mem:test");
//	dataSource.setDriverClassName("org.h2.Driver");
//	dataSource.setUsername("sa");
//	dataSource.setPassword("");
//	return dataSource;
	
	EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	return builder.setType(EmbeddedDatabaseType.H2).build();
    }
}
