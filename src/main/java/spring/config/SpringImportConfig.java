package spring.config;

import org.springframework.context.annotation.*;
import spring.imp.MyImportBeanDefinitionRegistrar;
import spring.imp.MyImportSelector;
import spring.po.Glasses;
import spring.po.Person;

/**
 * Description  spring.config
 * Create by  无语
 * Date on  2019/11/8 10:27
 */
@Configuration

@Import(value={MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
@PropertySource(value = {"classpath:PropertyTest"})

public class SpringImportConfig {




    @Bean(initMethod = "init",destroyMethod ="destroy" )
    public Person Person(){

        return new Person();
    }
    @Bean

    public Glasses glasses(){
        return new  Glasses();
    }
    @Bean("glassesOne")
    public Glasses glasses1(){
        Glasses glasses = new Glasses();
        glasses.setLabel("2");
        return glasses;
    }





}
