package io.ojw.mall;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import com.google.common.base.Predicate;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.base.Predicates.*;
import static springfox.documentation.builders.PathSelectors.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api(){		
		return new Docket(DocumentationType.SWAGGER_2)  
		          .select()                                  
		          //.apis(RequestHandlerSelectors.any())	//현재 RequestMapping으로 할당된 모든 URL 리스트를 추출              
		          .apis(RequestHandlerSelectors.basePackage("io.ojw.mall"))	
		          .paths(opPaths())               //필터링 ex) 여기서는 /api/*.*           
		          .build()		          
		          .directModelSubstitute(LocalDate.class, String.class)		          
		          .genericModelSubstitutes(ResponseEntity.class);
	}
	
	private Predicate<String> opPaths() {
		return or(
				regex("/*.*")
				);
	}
}