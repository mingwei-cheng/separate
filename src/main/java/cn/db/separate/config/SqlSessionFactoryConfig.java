package cn.db.separate.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Cheng
 * @create 2019-07-05 13:42
 **/
@Configuration
@EnableTransactionManagement
public class SqlSessionFactoryConfig {

    @Resource(name = "targetDataSource")
    private DataSource targetDataSource;

    /**
     * 初始化sqlSessionFactory,指定mapper路径
     *
     * @return
     * @throws Exception
     */
    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(targetDataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:cn/db/separate/mapper/*.xml"));
        return sessionFactoryBean.getObject();
    }

    /**
     * 事务管理器
     *
     * @return
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(targetDataSource);
    }
}
