package spring.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import spring.filter.MyFilter;
import spring.po.Person;

/**
 * Description  spring
 * Create by  无语
 * Date on  2019/9/6 10:37
 */
@Configuration
@ComponentScan(
   value = "spring",

        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.CUSTOM,value = MyFilter.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION,value =Controller.class )
        }

)
public class SpringConfigTest {



    @Bean("xhm")
    public Person getPerson(){

        return  new Person();
    }

    @Bean("mx")
    public Person getPerson1(){

        return  new Person();
    }








}
