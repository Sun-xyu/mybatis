# mybatis 源码阅读须知

1. mybatis源码版本为 3.5.3

2. 官方下载的源码无法直接使用，pom文件做了改动，作为学习，只需保证编译运行调试通过即可。

3. 增加了测试代码，即可运行调试。

## 准备 

1. [官方源码](https://github.com/mybatis/mybatis-3)

2. [官方中文文档](https://mybatis.org/mybatis-3/zh/index.html)

## 开始调试

### Mybatis如何获取数据源
1. org.apache.ibatis.session.SqlSessionFactoryBuilder.build(java.io.InputStream, java.lang.String, java.util.Properties)
   -- org.apache.ibatis.builder.xml.XMLConfigBuilder.parse
    -- org.apache.ibatis.builder.xml.XMLConfigBuilder.propertiesElement
     -- org.apache.ibatis.io.Resources.getResourceAsProperties(java.lang.String)  读取到了<properties resource = "config.properties"/>文件
       -- org.apache.ibatis.builder.xml.XMLConfigBuilder.environmentsElement
         -- org.apache.ibatis.builder.xml.XMLConfigBuilder.dataSourceElement
          -- org.apache.ibatis.builder.BaseBuilder.resolveClass
            -- org.apache.ibatis.type.TypeAliasRegistry.resolveAlias
       -- org.apache.ibatis.session.Configuration.setEnvironment   ####设置数据库源参数
       
### Mybatis如何获取SQL语句
1.org.apache.ibatis.session.SqlSessionFactoryBuilder.build(java.io.InputStream, java.lang.String, java.util.Properties)
   -- org.apache.ibatis.builder.xml.XMLConfigBuilder.parse
    --org.apache.ibatis.builder.xml.XMLConfigBuilder.mapperElement
      -- org.apache.ibatis.builder.xml.XMLMapperBuilder
       -- org.apache.ibatis.builder.xml.XMLMapperBuilder.parse
        -- org.apache.ibatis.builder.xml.XMLMapperBuilder.configurationElement
         -- org.apache.ibatis.builder.xml.XMLMapperBuilder.buildStatementFromContext(java.util.List<org.apache.ibatis.parsing.XNode>)
          -- org.apache.ibatis.builder.xml.XMLStatementBuilder
          --org.apache.ibatis.builder.MapperBuilderAssistant.addMappedStatement(java.lang.String, org.apache.ibatis.mapping.SqlSource, org.apache.ibatis.mapping.StatementType, org.apache.ibatis.mapping.SqlCommandType, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Class<?>, java.lang.String, java.lang.Class<?>, org.apache.ibatis.mapping.ResultSetType, boolean, boolean, boolean, org.apache.ibatis.executor.keygen.KeyGenerator, java.lang.String, java.lang.String, java.lang.String, org.apache.ibatis.scripting.LanguageDriver, java.lang.String)                             
          --org.apache.ibatis.session.Configuration.addMappedStatement ### 设置sql
扩展：面试题 mybatis加载mapper有几种方式，优先级怎么样？

### Mybatis如何操作数据库
1.org.apache.ibatis.session.SqlSessionFactory.openSession()
 -- org.apache.ibatis.session.defaults.DefaultSqlSessionFactory.openSessionFromDataSource
   -- org.apache.ibatis.session.Configuration.newExecutor(org.apache.ibatis.transaction.Transaction, org.apache.ibatis.session.ExecutorType)
    -- org.apache.ibatis.session.defaults.DefaultSqlSession