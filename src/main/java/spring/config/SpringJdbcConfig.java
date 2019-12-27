package spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Description  spring.config
 * Create by  无语
 * Date on  2019/11/26 9:51
 */
@Configuration(
        value = "spring.jdbc"
)
@PropertySource(value = {"classpath:/jdbc.properties"})
public class SpringJdbcConfig {



    @Bean(value = "dataSource")
    public DataSource getDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("${url}");
        druidDataSource.setPassword("${password}");
        druidDataSource.setUsername("${username}");
        druidDataSource.setDriverClassName("${driver_class}");
        return druidDataSource;
    };

    @Bean(value = "jdbcTemplate")
    public JdbcTemplate getJdbcTemplate(@Autowired DataSource dataSource){
        JdbcTemplate template=new JdbcTemplate(dataSource);
        return  template;
    }



}
