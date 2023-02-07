package com.sangeng;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


/**
 * @author bing_  @create 2022/2/7-16:41
 */
public class CodeGenerator {

    // 生成的代码放到哪个工程中(工程名)
    private static String PROJECT_NAME = "sangeng-framework";

    // 数据库名称
    private static String DATABASE_NAME = "sg_blog";


    public static void main(String[] args) {

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/";
        // gc.setOutputDir(projectPath + "/src/main/java");
        // PROJECT_NAME 生成的代码放到哪个工程中，上面动态配置
        gc.setOutputDir(projectPath + PROJECT_NAME + "/src/main/java");
        gc.setAuthor("Bing");
        // 生成后是否打开资源管理器
        gc.setOpen(false);
        // 覆盖现有的
        gc.setFileOverride(false);
        // 去掉Service接口的首字母I
        gc.setServiceName("%sService");
        // 主键策略 自增长 要配合数据库表一起设置
        // gc.setIdType(IdType.AUTO);
        // 分布式主键策略
        gc.setIdType(IdType.ASSIGN_ID);
        // 只适合 Date 格式，其它格式不配置这项
        // gc.setDateType(DateType.ONLY_DATE);
        // 开启Swagger2模式
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        // 上面 动态配置数据库名 DATABASE_NAME
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Bing_yu2001");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        // 子包名 MODULE_NAME ，上面动态配置
        PackageConfig pc = new PackageConfig();
        // 父包名
        pc.setParent("com.sangeng");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setXml("mapper.xml");
        // 实体类存储包名
        pc.setEntity("entity");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表映射到实体的命名策略 驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略 驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        // 实体类的实现接口 Serializable
        strategy.setEntitySerialVersionUID(true);
        // 逻辑删除字段名
        strategy.setLogicDeleteFieldName("del_flag");
        // 去掉表前缀
        strategy.setTablePrefix("sg_");
        // 去掉布尔值的is_前缀（确保tinyint(1)）
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        // restful api风格控制器 返回json
        strategy.setRestControllerStyle(true);
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        // 生成 1 张或多张表代码,不设置为生成所有表
        strategy.setInclude("sg_category");

        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();

    }
}
