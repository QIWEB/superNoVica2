### 模拟vica在无税盘税控情况下测试开票系统
* 　　　　　　　　　　　　　　　　　　　　　　■■　　　　　　　　
* 　■■■■■■■■■■■■■　　　   　　　　　■■　　　　　　　　
* 　■■■■■■■■■■■■■　　　　　　　　■■■■■■■　　　
* 　　　　　　　■■　　　　　　　　　　　　　■■■■■■■　　　
* 　　　　　　　■■　　　　　　　　　　　　　■■　　　　　　　　
* 　　　　　　■■　　　　　　　　　　　　　　■■　　　　　　　　
* 　　　　　■■■　　■　　　　　■■■■■■■■■■■■■■■　
* 　　　　■■■■　　■■　　　　■■■■■■■■■■■■■■■　
* 　　　■■　■■　　　■■　　　　　　　　　■■　　　　　　　　
* 　　■■　　■■　　　　■■　　　　　　　　■■■■　　　　　　
* 　■■　　　■■　　　　　■　　　　　　　　■■　■■　　　　　
* 　　　　　　■■　　　　　　　　　　　　　　■■　　■■　　　　
* 　　　　　　■■　　　　　　　　　　　　　　■■　　　■■　　　
* 　　　　　　■■　　　　　　　　　　　　　　■■　　　　　　　　
* 　　　　　　■■　　　　　　　　　　　　　　■■　　　　　　　　
　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　
![img](https://github.com/QIWEB/superNoVica2/raw/master/src/main/resources/static/img/welcome2.gif) 
![https://github.com/QIWEB/superNoVica2/raw/master/src/main/resources/static/img/welcome2.gif](https://github.com/QIWEB/superNoVica2/raw/master/src/main/resources/static/img/welcome2.gif)

 <pre>
:: create By Qiweb
:: DATA:2018-08-01 14：00
:: Spring Boot ::        2.0.3.RELEASE
:: ACME-JAVA-SUPERNOVICA  ::        1.0.20180801

----------------------模拟vica 无税盘情况ACME系统测试开专票发票系统-------------------------------
系统启动成功，当前位置：D:\superNoVica
▲▲▲系统启动成功，打印帮助信息▲▲
●不卡为模拟vica开票，端口号为：8880
●访问ip本机为127.0.0.1:8880或者本机的ip：8880
●包含如下5个接口：
●发票库存校验：http://127.0.0.1:8880/api/invoice/invoiceasync
●发票库存校验：http://127.0.0.1:8880/api/invoice/queryasync
●发票库存校验：http://127.0.0.1:8880/api/invoice/printasync
●发票库存校验：http://127.0.0.1:8880/api/invoice/revokeasync
●查看帮助信息：http://127.0.0.1:8880/
●根据请求数有如下方法模拟规则》》
●发票打印：i%3==0 为失败其他为成功 
●发票作废：i>4 为失败其他为成功 
●库存校验：都为成功
●专普开票：size>=5&&i>5&&i%2==0 为失败其他为成功
 </pre>