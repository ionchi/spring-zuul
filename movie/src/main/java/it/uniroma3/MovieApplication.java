package it.uniroma3;

import it.uniroma3.model.Movie;
import it.uniroma3.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class MovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}


	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Spring REST Movies with Swagger")
				.description("Spring REST with Swagger")
				.termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
				.contact("Ion Chiriac")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
				.version("2.0")
				.build();
	}

	@Bean
	CommandLineRunner init(MovieRepository repo) {
		return (evt) -> {
			repo.save(new Movie(1,"titanic","cameron",7.7));
			repo.save(new Movie(2,"jaws","spielberg",8.0));
			repo.save(new Movie(3,"the-wolf-of-wall-street","scorsese",8.2));
			repo.save(new Movie(4,"psycho","hitchcock",8.5));
			repo.save(new Movie(5,"the-shining","kubrick",8.4));
			repo.save(new Movie(6,"pulp-fiction","tarantino",8.9));
		};
	}

}
