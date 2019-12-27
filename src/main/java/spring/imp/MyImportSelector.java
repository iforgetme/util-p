package spring.imp;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/**
 * 导入选择器 selectImports返回值为导入ioc容器的类
 * Description  spring.imp
 * Create by  无语
 * Date on  2019/11/8 10:01
 */
public class MyImportSelector implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata metadata) {
        String className = metadata.getClassName();
        Set<String> annotationTypes = metadata.getAnnotationTypes();
        annotationTypes.forEach((a)->{
           // System.out.println("当前类注解信息：----------------"+a);
        });
      //  System.out.println("selectImports  ............"+className);


        return new String[]{"spring.po.Student","spring.po.teacher"};
    }
}
