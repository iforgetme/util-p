package spring.imp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 *  自定义后置处理器   自动拦截ioc容器中的所有类 可以做一些属性装配.........
 * Description  spring.imp
 * Create by  无语
 * Date on  2019/11/8 11:12
 */

public class MyBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
       // System.out.println("postProcessBeforeInitialization........."+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
