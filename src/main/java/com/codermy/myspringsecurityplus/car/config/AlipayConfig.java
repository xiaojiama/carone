package com.codermy.myspringsecurityplus.car.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2021000117645216";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCrY4LYfgVkDQK9q9uQxyZ3ioUubyvVfIxhYLYCUKWjgaWjCPmC3lBSC/i+i0PmwvRP4xrRwq0yLPpEfIkksFWxAYzHyEX33dpsZc1giPM0pOLBtelYKVqoejoPPPdRMDbeNq6ImM44vYj0lTqoDr7HXeSReLa0O+wOpr8Uy4Rqec6f+nmXQ0fdCbvCJ6ghxES+Wduk+CsHxoDb1kNnEV24BHLsMz7D8zo4IEdllft9DtiQ6XdOCA6MotJ9G3ds+ps4NKd+MNcQ6qMjh22X6OF8rrxykle9p6VnCzbMwP1kgx2VzTFEA4woNNf8oSaDXtk34OoEGKumJyEjHyEhRlWHAgMBAAECggEAb8wjyBC8d309Mnhua0xdPVEwew+tqj7qW4L4dx4gTj39zBHIoKNgBYUlpzW0/0xgjdP45Kd2zuoj/pS16SbVXvdB2/g6G+ut1VO62qO68bY07GM7aXTgXFIOmW4NbfpjcCR/ST9UFhMcyV6muoWT0PzgYjw6AeusmgoPTwNKxyDIHHVk1Hf9mw9JokBzjA0fe0UYvXqaYV/615tD8z66ZqtP9W+54V1gHQvPnzDjYb8GTjdcMwIcCWFsSgUbJN1PeJ5jKnYe5heXN9+F7kdzXlqoGuKtOG41gxU46G1gY9Ik/yJwmIQ4y+0xlWCLywBuTWMhvtPntTtVm9bLv7LooQKBgQDb9DH5d3hTHW78YmRSxsTAKfkgAj75RoudtYePDl/tiZTD2obkkmGeeBKkcfTCL1VQOlkSSImuYbhyHA0Rq7eNaUj66IlIpWFQh/+f+qWOMdD38nd4xq/5ssOAKiMqpZql5B+2G5+b+io9/53+Onm0iQYNzOVMrYJdCD8WXeoG7wKBgQDHedf9kWjzde5XEpLB65+l30m/8Zr0Sn3lJvcri+zL4L7nxhc8VFRQs8cXHmg8V4ToNBXcBNI0pc+NBO+QDnXeYZ934ebhZ5rrosirbysx0amwxxXsiiChjWM/aQHe9GeFwkfWVHisf8glcMcHHLxqPoeaochYRQAPBgugnIpa6QKBgAZQ+uZpEeGBJODGio79+kRycB8FMX3DEttSjTQEe6i8nLMLIXiK45bDZ/Wk+Bxjc5W8NzaZMqr5T2yndZCeV8UN0vSxnM+jQCop3aNgdUSiFZgpNFkZi5AmHAsgKqZc3xYIeQinvmuiQXhKUOUuFLSkgrH+wk7zZJz7UaR4yfGtAoGAMwTM44PfbfWJwidz9blIGeqkOD2ZPkYXMRboPPEK3ZJcAXe+1aVT81gn4ZfbXSOdUK6RsLZi9Sg7j3vlsdig+QHLAaR7oscW7pGx9Xb3e0R+vEIPL3KcqfU5nyxCWD32hFWOfhkYzDXagQj5Pq5w2lOn5zrdvQXY3/7R1oBzOpECgYEAk/x1HGCu87g/E5G9pd2v8+WyI+8tsSCzKEpjiAvXFzGQuv12Uge6HhdlUR0FXFWDOuL+fA+v6CIaqWZryR0SSEn49NlPXKecIJsM8s5D9jCJFY0dhtbNrwQ005/xxmCNiPiuDlZDLWKPW3UgQT8uTCvkvBcZ5EFEY0diyGPWSLs=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq2OC2H4FZA0CvavbkMcmd4qFLm8r1XyMYWC2AlClo4Glowj5gt5QUgv4votD5sL0T+Ma0cKtMiz6RHyJJLBVsQGMx8hF993abGXNYIjzNKTiwbXpWClaqHo6Dzz3UTA23jauiJjOOL2I9JU6qA6+x13kkXi2tDvsDqa/FMuEannOn/p5l0NH3Qm7wieoIcREvlnbpPgrB8aA29ZDZxFduARy7DM+w/M6OCBHZZX7fQ7YkOl3TggOjKLSfRt3bPqbODSnfjDXEOqjI4dtl+jhfK68cpJXvaelZws2zMD9ZIMdlc0xRAOMKDTX/KEmg17ZN+DqBBirpichIx8hIUZVhwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8088/alipay/alipayNotifyNotice";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:8088/alipay/alipayReturnNotice";

    // 签名方式
    public static String SIGN_TYPE = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String GATEWAYURL = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
