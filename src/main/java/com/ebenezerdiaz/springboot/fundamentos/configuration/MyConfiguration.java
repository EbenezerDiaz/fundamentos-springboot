package com.ebenezerdiaz.springboot.fundamentos.configuration;

import com.ebenezerdiaz.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public MyBean beanOperation(){
        return new MyBeanTwoImplement();
    }

    @Bean
    public MyOperation beanMyOperation(){
        return new MyOperationImplement();
    }

    @Bean
    public MyBeanWithDependency beanMyOperationImplement(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation);
    }
}
