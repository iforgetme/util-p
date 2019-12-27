package spring.start;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.SpringImportConfig;
import spring.config.SpringJdbcConfig;
import spring.po.Person;

/**
 * Description  spring.start
 * Create by  无语
 * Date on  2019/9/6 10:51
 */
public class initSpring {




    @Test
    public void initImportConfig(){
        //配置类的ioc启动  有生命周期
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(SpringImportConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        Person person = (Person) context.getBean("Person");
        System.out.println(person);
        context.close();

    }

    @Test
    public void initSpringJdbcConfig(){
        //配置类的ioc启动  有生命周期
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        context.close();

    }




}
