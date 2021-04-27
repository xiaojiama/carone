package com.codermy.myspringsecurityplus.car.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2021000117645216";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCT2aMf90kdZB1FVhCJbVf9BSs699Q556gn5/LVHiCYLHT4LVhIP7TOGmao0v2lCsUCMKkcsZUImnCNzPGT7iB+afJewllNPo0433HiH6YFQBBNuRzCfgV7Yg2kF+3eMoI8Qlx3pl8p3KJVD4s8rkwqiz2Du+xIXvKUNOcIlZe/JNNDOrOzgvYZm6A779OCbIAce/EGmbAxVd/VazTP1KVJuVD5yfExoeAsKbyv4yBRwRfo2sk/+p44AHqBnwaKVUeMSV2uCd8yMjrOdw/8U4ol4Oevg7ZigPgHfKK/UTTShRUF1AX3NQ4AObf9cdw4fmkuvvD4dw0To9may1qBX2BDAgMBAAECggEALD8cp2tc8TPN4rsv9cDIbUtHtb0HQVEHrPGoZYk/neroEoGXgv+1v1x9TpbeG69OaCph4dlvTYTSnE0yGuBPzyUryJoHGavXnaaZMV/hUn7qoif86GPIhjVA+dPDdxTMjvYZMCGVJzUFS8l8FOvglXbYMP1jaAEqPZpbbeE+6Y/I8ADe87iJaqgtWf1RpAK82BqXzUd5OhcCmXg/s7FMmO/NtTr0weRgufihSfxfu6CDK9dLG76ZOMV3WHJVN+9C65JFfqutL5eTWpUU48vK3fZBWvO5XZumEdQd7gZKZmKPfadNNvqGcvRcyIPkB/3uNAF12NYLRrPgO2e8Hv3nmQKBgQDinIuy8thucGkL9xsVu3ehVIWx36YnJ8HVnJwAKFb9PbO4XUHkTo+rGVbSMGprV+Pd9A6vX3xuIjzzz5SqGqMKwXcVuVUan4q3w6DfaJB2pQBmZj3rRNHkolu39j0Enee7pb3zrFQPIF4svOK1CWQbPDyBnC91Pf1g+jUH+QTXlwKBgQCnBjscGmISxc0RulxSupCQKuUIFcVB4WmVf+VJ04e6rtWX61I1as2Rn+439PZKE1OW9tcoQqbs4rTJzMNmDXTTuKZVDGGZSXwZiU90em5ekmsDxSCdlZJP5oeAGuBdM7OUSrV1RDZhQiiwI+0V01tUb21OCIyCWnVKPBvlPz7yNQKBgAlNJ6bGtP+DeaxWDjSMJ1Ll5z7v0SAWT6x26yhOf5ORbskIeuXmG5uIRLoH5rZVOzMnC1tDbhYPLbVMNevtf/e3DS5FFysqlUUJsJa68gMMrsGFPNFlV8AssdsRLlv8J0Dagrt3vVOJnpPzhNKtegBsLoebY49KZhYO7xJOtFYtAoGAI+6YOtSGMsmw81ZnVDjkrs87lICyfhjTUSFBn59NPOEt/E/Y0LsYXHLOmeUuMwNzYS/bW8LzDFU9VYiipMrSTlYUGoneWU7QzuGWYVWRdvEC6r225Y/u+Rd8IRgnGoXr5UQpQ4AYPtYv0Kz57U8Z/9E076uxJr31K7JAwhHYJd0CgYB9HTTilNFgJshC53ofHltvbkJHXTL6DsaJWAquTj4tRrCsqrKVNi+hdLPNAmVMsfj3RaIo1E1EGBMgqsFPcxE3t9U0IzHNLPo0itGRCp2HGejwAeSGonvrp0mu44U/dl196dc8UV1+0oFhf1fSrt0cp7fUVl+i89fKUw4hVzGaTQ==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhm7usoETxCPQqnZbMDKrP/3uRe5eTcrlscwJsQo2VxREZyub9RlYjw4N+DAC4gA8btuufU6QMPNI36Qmjd4s37aZMtMr/Abquq77F67I7+SkTf+/7Ei0PR4v7lTXFoREGxsDIDTnyRaIYWzepNV44UKRnrSaU0LPOuZQ1u1wiZrwr5FPystb86xlB2tI2LKkfklhP3bJePJdgBDw1KvJDmUfrHAfgWMquoI4XTMh8A0LQpfhDCt4OYOAl2nE8yOmC5dtcavCWm6HDocAQGf3tHJzaMoRBjFWVLGmrY4cu60sZERT9cT1GCo4TGYKSeQau3bDp2HXKf2hpgo1Sv6yTwIDAQAB";

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
