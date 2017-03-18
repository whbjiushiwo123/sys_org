package com.whb;


import org.springframework.boot.SpringApplication;  
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;  
import org.springframework.context.annotation.ImportResource;

/** 
* <p>User: 
* <p>Date: 13-12-22 
* <p>Version: 1.0 
*/  
@ComponentScan(basePackages = {
	    "com.whb"
	})
@SpringBootApplication
/**
 * 二、在 SpringBootApplication 上使用@ServletComponentScan 注解后，
 * Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener
 *  注解自动注册，无需其他代码。
 * @author WHB
 *
 */
@ServletComponentScan
@ImportResource(locations = {"classpath:*.xml"})

public class Application {  
   public static void main(String[] args) {  
       SpringApplication.run(Application.class);  
   } 

}  
