package spring.po;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Description  spring.po
 * Create by  无语
 * Date on  2019/11/8 10:59
 */
public class teacher {

    public teacher() {
        System.out.println("teacher...constructor....");

    }

    @PostConstruct
    private void init(){
        System.out.println("teacher.....init ....");
    }

    @PreDestroy
    private void destroy() {

        System.out.println(" teacher ....destroy....");
    }

}
