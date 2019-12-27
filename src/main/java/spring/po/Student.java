package spring.po;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Description  spring.po
 * Create by  无语
 * Date on  2019/11/8 10:08
 */
public class Student implements InitializingBean, DisposableBean {


    @Override
    public void destroy() throws Exception {
        System.out.println("Student  ......destroy.....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Student  ......afterPropertiesSet.....");
    }



}
