package com.sentinel.demo02.datasource;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MyFileDatasource implements InitFunc {
    private static final Logger logger = LoggerFactory.getLogger(MyFileDatasource.class);

    @Override
    public void init() throws Exception {
        logger.info("MyFileDatasource init");
        //1. 找到resources目录下的文件
        String filePath = MyFileDatasource.class.getResource("FlowRule.json").getFile();
        logger.info("filePath: {}", filePath);
        //2. 读取文件内容
        FileRefreshableDataSource<List<FlowRule>> myDatasource = new FileRefreshableDataSource<>(filePath, new Converter<String, List<FlowRule>>() {
            @Override
            public List<FlowRule> convert(String json) {
                //3. 解析文件内容，构建FlowRule对象
                return JSON.parseArray(json, FlowRule.class);
            }
        });
//3. 利用数据源对象 将规则加载给当前程序的sentinel
        FlowRuleManager.register2Property(myDatasource.getProperty());
    }
}
