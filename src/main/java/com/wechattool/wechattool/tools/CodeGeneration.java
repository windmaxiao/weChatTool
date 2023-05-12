package com.wechattool.wechattool.tools;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.util.Collections;

/**
 * @author MAXIAO
 */
public class CodeGeneration {
    public static void main(String[] args) {


        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/wx?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                        "root", "123456")
                .dataSourceConfig(builder -> {
                    new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/wx?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                            "root","123456")
                            .dbQuery(new MySqlQuery())
                            //.schema("mybatis-plus")
                            .typeConvert(new MySqlTypeConvert())
                            .keyWordsHandler(new MySqlKeyWordsHandler())
                            .build();
                })
                .globalConfig(builder -> {
                    new GlobalConfig.Builder()
                            .fileOverride()
                            // 指定输出目录
                            .outputDir("d://")
                            .author("maxiao")
                            // 开启 kotlin 模式
                            //.enableKotlin()
                            // 	开启 swagger 模式
                            //.enableSwagger()
                            // 时间策略
                            .dateType(DateType.TIME_PACK)
                            // 注释日期
                            .commentDate("yyyy-MM-dd")
                            .build();
                })
                .packageConfig(builder -> {
                    new PackageConfig.Builder()
                            // 父包名
                            .parent("com.wechattool.wechattool")
                            // 父包模块名
                            //.moduleName("sys")
                            // Entity 包名
                            .entity("model")
                            // Service 包名
                            .service("service")
                            // Service Impl 包名
                            .serviceImpl("service.impl")
                            // Mapper 包名
                            .mapper("mapper")
                            // Mapper XML 包名
                            .xml("mapper.xml")
                            // Controller 包名
                            .controller("controller")
                            //.other("other")
                            // 路径配置信息
                            .pathInfo(Collections.singletonMap(OutputFile.entity, "D://"))
                            .build();
                }).strategyConfig(builder -> {
                    new StrategyConfig.Builder()

                            // 策略配置 ( StrategyConfig )
                            // 开启大写命名
                            .enableCapitalMode()
                            // 开启跳过视图
                            //.enableSkipView()
                            .disableSqlFilter()
                            // 模糊表匹配(sql 过滤)
                            //.likeTable(new LikeTable("USER"))
                            .addInclude("t_simple")
                            .addTablePrefix("t_", "c_")
                            .addFieldSuffix("_flag")

                            // Entity 策略配置
                            .entityBuilder()
                            // 设置父类
                            //.superClass("BaseEntity")
                            //禁用生成 serialVersionUID
                            //.disableSerialVersionUID()
                            // 开启链式模型
                            .enableChainModel()
                            // 开启 lombok 模型
                            .enableLombok()
                            // 开启 Boolean 类型字段移除 is 前缀
                            .enableRemoveIsPrefix()
                            // 开启生成实体时生成字段注解
                            .enableTableFieldAnnotation()
                            // 开启 ActiveRecord 模型
                            .enableActiveRecord()
                            // 乐观锁字段名(数据库)
                            //.versionColumnName("version")
                            // 乐观锁属性名(实体)
                            //.versionPropertyName("version")
                            // 逻辑删除字段名(数据库)
                            //.logicDeleteColumnName("deleted")
                            // 逻辑删除属性名(实体)
                            //.logicDeletePropertyName("deleteFlag")
                            // 数据库表映射到实体的命名策略	默认下划线转驼峰命名
                            .naming(NamingStrategy.underline_to_camel)
                            // 数据库表字段映射到实体的命名策略
                            .columnNaming(NamingStrategy.underline_to_camel)
                            // 添加父类公共字段
                            // .addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time")
                            // 添加忽略字段
                            //.addIgnoreColumns("age")
                            // 添加表字段填充
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            // 添加表字段填充
                            .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                            // 全局主键类型
                            .idType(IdType.AUTO)
                            // 转换文件名称
                            //.formatFileName("%sEntity")

                            // Controller 策略配置
                            .controllerBuilder()
                            // 设置父类
                            //.superClass(BaseController.class)
                            // 开启驼峰转连字符
                            .enableHyphenStyle()
                            // 开启生成@RestController 控制器
                            .enableRestStyle()
                            // 格式化文件名称
                            .formatFileName("%Controller")

                            // Service 策略配置
                            .serviceBuilder()
                            // 设置 service 接口父类
                            //.superServiceClass(BaseService.class)
                            // 设置 service 实现类父类
                            //.superServiceImplClass(BaseServiceImpl.class)
                            .formatServiceFileName("%Service")
                            .formatServiceImplFileName("%ServiceImp")

                            // Mapper 策略配置
                            .mapperBuilder()
                            //.superClass(BaseMapper.class)
                            // 开启 @Mapper 注解
                            .enableMapperAnnotation()
                            // 启用 BaseResultMap 生成
                            .enableBaseResultMap()
                            // 启用 BaseColumnList
                            .enableBaseColumnList()
                            // 设置缓存实现类
                            //.cache(MyMapperCache.class)
                            .formatMapperFileName("%Mapper")
                            .formatXmlFileName("%Mapper")

                            .build();
                }).injectionConfig(builder -> {
                    new InjectionConfig.Builder()
                            .beforeOutputFile((tableInfo, objectMap) -> {
                                System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
                            })
                            .customMap(Collections.singletonMap("test", "baomidou"))
                            .customFile(Collections.singletonMap("test.txt", "/templates/test.vm"))
                            .build();
                }).execute();


    }
}
