package cn.gzggzy.yyh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ServletComponentScan
@ImportResource(locations = {"classpath:druid-bean.xml"})
@MapperScan("cn.gzggzy.yyh.dao")
public class XinjingApplication {

	public static void main(String[] args) {
		SpringApplication.run(XinjingApplication.class, args);
	}
}
