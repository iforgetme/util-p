package spring.filter;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * 自定义拦截器  判断类是否加载到spring容器
 * Description  spring.filter
 * Create by  无语
 * Date on  2019/9/6 10:42
 */

public class MyFilter implements TypeFilter {
    /**
     * 判断扫描的类是否加载到spring容器
     * @param metadataReader  当前类的元数据信息类
     * @param metadataReaderFactory  获取其他类的元数据信息类
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        System.out.println("match loading...."+classMetadata.getClassName());
        return false;
    }
}
