package kr.or.ddit;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@ComponentScan(basePackageClasses = {ComponentScanI.class} )
//@ComponentScan(basePackages = "kr.or.ddit")
@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	// thymeleaf layout
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}

}
