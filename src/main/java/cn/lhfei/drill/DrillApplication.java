package cn.lhfei.drill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class DrillApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrillApplication.class, args);
	}
	
}
