package spring.imp;

import api.six.io.filter.MyFileFilter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 类定义注册器
 * Description  spring.imp
 * Create by  无语
 * Date on  2019/11/8 10:11
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * @param importingClassMetadata 当前类的注解元信息
     * @param registry 类定义注册器
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

            BeanDefinition rootBeanDefinition = new RootBeanDefinition(MyFileFilter.class);

            registry.registerBeanDefinition("MyFileFilter",rootBeanDefinition);
            rootBeanDefinition.setBeanClassName("spring.imp.MyBeanPostProcessor");
             registry.registerBeanDefinition("MyBeanPostProcessor",rootBeanDefinition);


    }





}
