package cn.lhfei.drill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import cn.lhfei.drill.config.EnvironmentProperties;

@SpringBootApplication
@ServletComponentScan
public class DrillApplication {
	
	@Bean
    @ConfigurationProperties(prefix = "env")
    public EnvironmentProperties dataSourceProd() {
        return new EnvironmentProperties();
    }
	
	/*@Bean
	@ConfigurationProperties(prefix = "datasource")
	public DatasourceProperties getDatasources() {
		return new DatasourceProperties();
	}*/

	public static void main(String[] args) {
		SpringApplication.run(DrillApplication.class, args);
	}
	
}
