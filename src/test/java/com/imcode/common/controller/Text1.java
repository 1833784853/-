package com.imcode.common.controller;

import com.imcode.common.model.CacheMap;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Text1 {
    @Test
    public void text() throws InterruptedException {
//        List<String> warnings = new ArrayList<String>();
//        boolean overwrite = true;
//        //如果这里出现空指针，直接写绝对路径即可。
//        String genCfg = "/generatorConfig.xml";
//        File configFile = new File(Text1.class.getResource(genCfg).getFile());
//        ConfigurationParser cp = new ConfigurationParser(warnings);
//        Configuration config = null;
//        try {
//            config = cp.parseConfiguration(configFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (XMLParserException e) {
//            e.printStackTrace();
//        }
//        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//        MyBatisGenerator myBatisGenerator = null;
//        try {
//            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//        } catch (InvalidConfigurationException e) {
//            e.printStackTrace();
//        }
//        try {
//            myBatisGenerator.generate(null);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        CacheMap<Object, Object> cacheMap = CacheMap.getInstance();
        cacheMap.put(1, 22);
        cacheMap.put(2, 2333);

        int i = 0;
        while ((i++) <= 5) {
            System.out.println("内容" + i);
            for (Map.Entry<Object, Object> entry : cacheMap.entrySet()) {
                System.out.println(entry.getKey() + "----" + entry.getValue());
            }
            Thread.sleep(10000);
        }
    }
}
