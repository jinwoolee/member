package kr.or.lgdlab;

import com.samskivert.mustache.Mustache;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication(scanBasePackageClasses = {ComponentScanI.class})
public class SpringMustacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMustacheApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Mustache.Compiler mustacheCompiler(Mustache.TemplateLoader templateLoader) {

        return Mustache.compiler()
                .defaultValue("")               //존재하지 않는 속성은 빈 문자열로 처리
                .withLoader(templateLoader);
    }
}
