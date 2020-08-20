package ie.cct.showcasefarmca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

//CA - Cloud Computing 
//Student: Yuri Andrade 
//Student number: 2019154

//We need to tell Spring that we have a code to be scan 
//We tell the package and all files from this package (*)
@ComponentScan("ie.cct.showcasefarmca*")
public class ShowcaseFarmcaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowcaseFarmcaApplication.class, args);
	}

}
