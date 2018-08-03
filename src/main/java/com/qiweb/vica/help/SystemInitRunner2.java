package com.qiweb.vica.help;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import sun.tools.jar.CommandLine;

/**
 * @Author:qiweb
 * @DATE:Created on 2018/8/1 13:20
 * @Modified By:
 * @Class Description:ApplicationRunner 实现
 */
@Component
@Order(value = 1)   //执行顺序控制
public class SystemInitRunner2 implements ApplicationRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    /**
     * @Author:qiweb
     * @DATE:Created on 2018/8/1 13:20
     * @Modified By:
     * @Class Description:ApplicationRunner 实现
     */
    public void run(ApplicationArguments applicationArguments) throws Exception {
        //项目路径 path =  E:\IDE\workspace\ideaWorkspace\spring boot\spring-boot
        String path = System.getProperty("user.dir");
        //CommandLine.main(new String[]{path+"\\src\\main\\resources\\ftpserver\\ftpd-typical.xml"});
        System.out.println("----------------------模拟vica 无税盘情况ACME系统测试开专票发票系统" + "-------------------------------");
        System.out.println("系统启动成功，当前位置："+path);
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");


    }
}