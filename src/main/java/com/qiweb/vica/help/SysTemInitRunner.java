package com.qiweb.vica.help;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author:qiweb
 * @DATE:Created on 2018/8/1 13:20
 * @Modified By:
 * @Class Description:CommandLineRunner 实现
 */
@Component
@Order(value=2)
class SysTemInitRunner implements CommandLineRunner {
    @Override
    public void run(String... var1) throws Exception {
        System.out.println("▲▲▲系统启动成功，打印帮助信息▲▲");
        System.out.println("●不卡为模拟vica开票，端口号为：8880");
        System.out.println("●访问ip本机为127.0.0.1:8880或者本机的ip：8880");
        System.out.println("●包含如下5个接口：");
        System.out.println("●发票库存校验：http://127.0.0.1:8880/api/invoice/invoiceasync");
        System.out.println("●发票库存校验：http://127.0.0.1:8880/api/invoice/queryasync");
        System.out.println("●发票库存校验：http://127.0.0.1:8880/api/invoice/printasync");
        System.out.println("●发票库存校验：http://127.0.0.1:8880/api/invoice/revokeasync");
        System.out.println("●查看帮助信息：http://127.0.0.1:8880/");
        System.out.println("●根据请求数有如下方法模拟规则》》");
        System.out.println("●发票打印：i%3==0 为失败其他为成功 ");
        System.out.println("●发票作废：i>4 为失败其他为成功 ");
        System.out.println("●库存校验：都为成功");
        System.out.println("●专普开票：size>=5&&i>5&&i%2==0 为失败其他为成功");
    }
}