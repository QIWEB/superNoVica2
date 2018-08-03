package com.qiweb.vica.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.qiweb.vica.a.util.HttpClientsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by office on 2018/8/1.
 */
@RequestMapping("/api/invoice/")
@RestController
public class VicaController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /*
    * #纸质发票及清单打印接口-航信税控盒版
    * */
    @RequestMapping(value = {"/printasync"})
    public String printasync(@RequestBody String sendData, HttpServletRequest request, HttpServletResponse response){
        System.out.println("acme发来打印发票请求："+sendData);
        Map<String, Object> jsonToMap =JsonToMap(sendData);
        String sRequestNo=jsonToMap.get("RequestNo").toString();
        String sUrl=jsonToMap.get("Url").toString();
        System.out.print(sRequestNo);
        logger.info("打印发票请求流水号:"+sRequestNo);
        logger.info("打印发票请求报文:"+sendData);
        logger.info("打印发票请求回调地址:"+sUrl);
        //回调用acme系统的报文
        String sendDatab="[\n" +
                "%s" +
                "]";

        //invoice id
        String id="661553822951";
        //得到long类型当前时间
        long l = System.currentTimeMillis();
        //new日期对象
        Date date = new Date(l);
        //转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //开票日期
        String InvoiceDate=dateFormat.format(date);
        System.out.println("打印日期:"+InvoiceDate);

        //返回成功报文模板
        String tempTrue=
                "    {\n" +
                        "        \"Code\": \"1\",\n" +
                        "        \"Data\": {\n" +
                        "            \"Id\": \"%s\"\n" +
                        "        },\n" +
                        "        \"Msg\": \"5011-打印成功 [,]\",\n" +
                        "        \"RequestNo\": \"%s\",\n" +
                        "        \"Success\": true\n" +
                        "    },";
        //返回失败报文模板
        String tempFlase="  {\n" +
                "        \"Code\": \"1\",\n" +
                "        \"Data\": {\n" +
                "            \"Id\": \"%s\"\n" +
                "        },\n" +
                "        \"Msg\": \"5013-打印失败 [0004,]\",\n" +
                "        \"RequestNo\": \"%s\",\n" +
                "        \"Success\": false\n" +
                "    },";
        JSONArray Parameters=  (JSONArray)jsonToMap.get("Parameters");
        StringBuffer sb=new StringBuffer();
        int icount=Parameters.size();
        for (int i=0;i<icount;i++) {
            JSONObject obj= (JSONObject)Parameters.get(i);
            Object o= obj.get("Id");
            id=o.toString();

            if(i%3==0){// 当开票记录3或者3的倍数之后所有偶数行设置成开票失败
                sb.append(String.format(tempFlase,id,sRequestNo));
            }else {
                sb.append(String.format(tempTrue, id, sRequestNo));
            }
        }



        String sendDatac=String.format(sendDatab,sb.toString().substring(0,sb.toString().length()-1));



        //开线程等 3秒再回调
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    String content = HttpClientsUtils.doPost(sendDatac, sUrl);
                    System.out.print("打印发票推送，acme返回："+sendDatac);
                    logger.info("打印发票请求回调报文:"+sendDatac);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return "{\"Success\":true,\"Msg\":\"请求已受理!\",\"Code\":\"1\",\"RequestNo\":\""+sRequestNo+"\"}";
    }

    /*
    * #航信发票作废接口
    * */
    @RequestMapping(value = {"/revokeasync"})
    public String revokeasync(@RequestBody String sendData, HttpServletRequest request, HttpServletResponse response){
        System.out.println("acme发来作废发票请求："+sendData);
        Map<String, Object> jsonToMap =JsonToMap(sendData);
        String sRequestNo=jsonToMap.get("RequestNo").toString();
        String sUrl=jsonToMap.get("Url").toString();
        System.out.print(sRequestNo);
        logger.info("作废发票请求流水号:"+sRequestNo);
        logger.info("作废发票请求报文:"+sendData);
        logger.info("作废发票请求回调地址:"+sUrl);
        //回调用acme系统的报文
        String sendDatab="[\n" +
                "%s" +
                "]";

        //invoice id
        String id="661553822951";
        //得到long类型当前时间
        long l = System.currentTimeMillis();
        //new日期对象
        Date date = new Date(l);
        //转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //开票日期
        String InvoiceDate=dateFormat.format(date);
        System.out.println("作废发票日期:"+InvoiceDate);

        //返回成功报文模板
        String tempTrue=
                "        {\n" +
                        "        \"Code\": \"1\",\n" +
                        "        \"Data\": {\n" +
                        "            \"Id\": \"%s\"\n" +
                        "        },\n" +
                        "        \"RequestNo\": \"%s\",\n" +
                        "        \"Success\": true\n" +
                        "    },";
        //返回失败报文模板
        String tempFlase="    {\n" +
                "        \"Code\": \"1\",\n" +
                "        \"Data\": {\n" +
                "            \"Id\": \"%s\"\n" +
                "        },\n" +
                "        \"Msg\": \"6013---6013-发票作废失败 [FPZF-000018,发票对象构造异常，尝试进行发票修复。]\",\n" +
                "        \"RequestNo\": \"%s\",\n" +
                "        \"Success\": false\n" +
                "    },";
        JSONArray Parameters=  (JSONArray)jsonToMap.get("Parameters");
        StringBuffer sb=new StringBuffer();
        int icount=Parameters.size();
        for (int i=0;i<icount;i++) {
            JSONObject obj= (JSONObject)Parameters.get(i);
            Object o= obj.get("Id");
            id=o.toString();

            if(i>4){// 当开票记录大于4的数之后所有设置成开票失败
                sb.append(String.format(tempFlase,id,sRequestNo));
            }else {
                sb.append(String.format(tempTrue, id, sRequestNo));
            }
        }

        String sendDatac=String.format(sendDatab,sb.toString().substring(0,sb.toString().length()-1));

        //开线程等 3秒再回调
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    String content = HttpClientsUtils.doPost(sendDatac, sUrl);
                    System.out.print("作废发票推送，acme返回："+sendDatac);
                    logger.info("作废发票请求回调报文:"+sendDatac);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return "{\"Success\":true,\"Msg\":\"请求已受理!\",\"Code\":\"1\",\"RequestNo\":\""+sRequestNo+"\"}";
    }
    /*
    * #库存校验接口
    * */
    @RequestMapping(value = {"/queryasync"})
    public String queryasync(@RequestBody String sendData, HttpServletRequest request, HttpServletResponse response){
        //{"Parameters":"0","ServiceInfo":{"Password":"12345678","TerminalNumber":"","TaxDiskSN":"","TaxCAPassword":"","SaleTaxNumber":"","PositiveOrNegative":"","RegistrationCode":""},"ClientInfo":{"SaleInfo":"ZTY","DeviceInfo":"2"},"RequestNo":"b68b2add-6364-4907-9bc6-03c075ad8844","Url":"http://127.0.0.1:8080/acme/invoice/stockHXCallBack","CommandIdentifier":""}
        System.out.println("acme发来库存请求："+sendData);

        Map<String, Object> jsonToMap =JsonToMap(sendData);
        String sRequestNo=jsonToMap.get("RequestNo").toString();
        String sUrl=jsonToMap.get("Url").toString();
        System.out.print(sRequestNo);
        logger.info("库存请求流水号:"+sRequestNo);
        logger.info("库存请求报文:"+sendData);
        logger.info("库存请求回调地址:"+sUrl);
        //返回报文模板
        String temp="{\n" +
                "    \"Code\": \"1\",\n" +
                "    \"Data\": {\n" +
                "        \"InvoiceCode\": \"%s\",\n" +
                "        \"InvoiceNumber\": \"%s\",\n" +
                "        \"MachineNo\": \"%s\",\n" +
                "        \"RemainingInvCount\": \"%s\"\n" +
                "    },\n" +
                "    \"RequestNo\": \"%s\",\n" +
                "    \"Success\": true\n" +
                "}";
        String InvoiceCode="3100181130";
        String InvoiceNumber="15342936";
        String MachineNo="661553822951";
        String RemainingInvCount="89";
        String sendDatab=String.format(temp,InvoiceCode,InvoiceNumber,MachineNo,RemainingInvCount,sRequestNo);
        //开线程等 3秒再回调
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    String content = HttpClientsUtils.doPost(sendDatab, sUrl);
                    System.out.print("库存校验仪推送，acme返回："+sendDatab);
                    logger.info("库存请求回调报文:"+sendDatab);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return "{\"Success\":true,\"Msg\":\"请求已受理!\",\"Code\":\"1\",\"RequestNo\":\""+sRequestNo+"\"}";
    }

    /*
* #纸质发票接口-航信税控盒版
* */
    @RequestMapping(value = {"/invoiceasync"})
    public String invoiceasync(@RequestBody String sendData, HttpServletRequest request, HttpServletResponse response){
        System.out.println("acme发来开票请求："+sendData);
        Map<String, Object> jsonToMap =JsonToMap(sendData);
        String sRequestNo=jsonToMap.get("RequestNo").toString();
        String sUrl=jsonToMap.get("Url").toString();
        System.out.print(sRequestNo);
        logger.info("开票请求流水号:"+sRequestNo);
        logger.info("开票请求报文:"+sendData);
        logger.info("开票请求回调地址:"+sUrl);
        //回调用acme系统的报文
        String sendDatab="[\n" +
                    "%s" +
                "]";

        //invoice id
        String id="661553822951";
        //得到long类型当前时间
        long l = System.currentTimeMillis();
        //new日期对象
        Date date = new Date(l);
        //转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //开票日期
        String InvoiceDate=dateFormat.format(date);
        System.out.println("开票日期:"+InvoiceDate);

        //返回成功报文模板
        String tempTrue=
                "    {\n" +
                "        \"Code\": \"1\",\n" +
                "        \"Data\": {\n" +
                "            \"Amount\": \"-15.64\",\n" +
                "            \"CheckCode\": \"\",\n" +
                "            \"Cipher\": \"\",\n" +
                "            \"Id\": \"%s\",\n" +
                "            \"InvoiceCode\": \"%s\",\n" +
                "            \"InvoiceDate\": \"%s\",\n" +
                "            \"InvoiceMonth\": \"5\",\n" +
                "            \"InvoiceNumber\": \"%s\",\n" +
                "            \"TaxAmount\": \"-2.66\"\n" +
                "        },\n" +
                "        \"RequestNo\": \"%s\",\n" +
                "        \"Success\": true\n" +
                "    },";
        //返回失败报文模板
        String tempFlase=" {\n" +
                "        \"Code\": \"1\",\n" +
                "        \"Data\": {\n" +
                "            \"Amount\": \"0.0\",\n" +
                "            \"Id\": \"%s\",\n" +
                "            \"TaxAmount\": \"0.0\"\n" +
                "        },\n" +
                "        \"Msg\": \"9005-商品明细折扣行添加金额或者税额不合法 [,]\",\n" +
                "        \"RequestNo\": \"%s\",\n" +
                "        \"Success\": false\n" +
                "    },";
        JSONArray Parameters=  (JSONArray)jsonToMap.get("Parameters");
        StringBuffer sb=new StringBuffer();
        int icount=Parameters.size();
        for (int i=0;i<icount;i++) {
            JSONObject obj= (JSONObject)Parameters.get(i);
            Object o= obj.get("Id");
            id=o.toString();

            //随机生成10位发票代码
            String InvoiceCode=""+(int)((Math.random()*9+1)*10000)+(int)((Math.random()*9+1)*10000);
            //随机生成8发票号
            String InvoiceNumber=""+(int)((Math.random()*9+1)*1000)+(int)((Math.random()*9+1)*1000);
            if(icount>=5&&i>5&&i%2==0){// 当开票记录大于等于5之后所有偶数行设置成开票失败
                sb.append(String.format(tempFlase,id,sRequestNo));
            }else {
                sb.append(String.format(tempTrue, id, InvoiceCode, InvoiceDate, InvoiceNumber, sRequestNo));
            }
        }



        String sendDatac=String.format(sendDatab,sb.toString().substring(0,sb.toString().length()-1));



        //开线程等 3秒再回调
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    String content = HttpClientsUtils.doPost(sendDatac, sUrl);
                    System.out.print("开票推送，acme返回："+sendDatac);
                    logger.info("开票请求回调报文:"+sendDatac);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return "{\"Success\":true,\"Msg\":\"请求已受理!\",\"Code\":\"1\",\"RequestNo\":\""+sRequestNo+"\"}";
    }




    /**
     * #纸质发票接口-航信税控盒版
     paperInvoiceCreateUrlByAisino=${vicaUrl}/api/invoice/invoiceasync
     #纸质发票及清单打印接口-航信税控盒版
     paperInvoicePrintAsync=${vicaUrl}/api/invoice/printasync
     #航信发票作废接口
     invalidUrl=${vicaUrl}/api/invoice/revokeasync
     #库存校验接口
     stockURL=${vicaUrl}/api/invoice/queryasync
     */
    /**
     * @Description: json 转换成map
     * @param @param json
     * @param @return
     * @return Map<String,Object>
     * @throws
     * @author qiweb
     * @date 2018年8月2日
     */
    public static Map<String, Object> JsonToMap(String json) {
        Map<String, Object> userMap = JSON.parseObject(json.toString(), new TypeReference<Map<String, Object>>() {
        });
        return userMap;
    }
}
