package com.ebenezerdiaz.springboot.fundamentos.configuration;

import com.ebenezerdiaz.springboot.fundamentos.bean.MyBean;
import com.ebenezerdiaz.springboot.fundamentos.bean.MyBeanTwoImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public MyBean beanOperation(){
        return new MyBeanTwoImplement();
    }
}
