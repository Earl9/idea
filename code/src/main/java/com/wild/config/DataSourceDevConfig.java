//package com.wild.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(basePackages = "com.wild.mapper.dev", sqlSessionTemplateRef = "devSqlSessionTemplate")
//public class DataSourceDevConfig {
//    /**
//     * 是application-test.yml中的spring.datasource.dev配置生效
//     * @return
//     */
//    @Bean(name = "devDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.dev")
//    public DataSource devDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    /**
//     * 将配置信息注入到SqlSessionFactoryBean中
//     * @param dataSource    数据库连接信息
//     * @return
//     * @throws Exception
//     */
//    @Bean(name = "devSqlSessionFactory")
//    public SqlSessionFactory devSqlSessionFactory(@Qualifier("devDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        // 使配置信息加载到类中，再注入到SqlSessionFactoryBean
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        configuration.setMapUnderscoreToCamelCase(true);
//        bean.setConfiguration(configuration);
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath: mapper/dev/*.xml"));
//        return bean.getObject();
//    }
//
//    /**
//     * 事务管理器，在实例化时注入主库dev
//     * @param dataSource
//     * @return
//     */
//    @Bean(name = "devTransactionManager")
//    public DataSourceTransactionManager devTransactionManager(@Qualifier("devDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    /**
//     * SqlSessionTemplate具有线程安全性
//     * @param sqlSessionFactory
//     * @return
//     * @throws Exception
//     */
//    @Bean(name = "devSqlSessionTemplate")
//    public SqlSessionTemplate devSqlSessionTemplate(@Qualifier("devSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}
