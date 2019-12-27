package spring.po;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

/**
 * Description  spring.po
 * Create by  无语
 * Date on  2019/9/6 10:47
 */

@Data
public class Person {


    @Value("awsl")
    private  String  name;
    @Value("#{100-90}")
    private  Integer age;
    @Value("${cardNo}")
    private  String  cardNo;
    @Qualifier(value = "glassesOne")
    @Autowired
    private Glasses glasses;

    public Person() {
        System.out.println("person ....  constructor........");
    }


    private void init(){
        System.out.println(" person ..... init ............");
    }

    private void destroy(){
        System.out.println(" person ..... destroy ............");
    }
}
