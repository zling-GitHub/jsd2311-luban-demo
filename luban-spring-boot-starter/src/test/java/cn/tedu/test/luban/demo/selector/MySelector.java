package cn.tedu.test.luban.demo.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MySelector implements ImportSelector {
    /**
     * selectImports 选择所导入的配置类的 全路径名称
     * @param annotationMetadata 选择器的环境 可以利用环境对象做一些逻辑判断
     *                           比如 导入的配置类是否存在某个注解 某个类型泛型等 使用用来筛选
     * @return String[] 每一个元素 都代表一个配置类全路径名称 最终被容器加载
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //想要导入MyConfiguraiton02
        String configPath="com.example.config.MyConfiguration02";
        String[] configs={configPath};
        return configs;
    }
}