package com.codermy.myspringsecurityplus.car.utils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.apache.http.ProtocolException;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Random;
public class COSClientUtil {
    private COSClient cOSClient;

    private static final String ENDPOINT = "ENDPOINT";  //用户可以指定域名，不指定则为默认生成的域名

    //secretId
    private static final String secretId = "secretId";

    //secretKey
    private static final String secretKey = "secretKey";

    //存储桶名称
    private static final String bucketName = "bucketName";//公有读私有写
    //APPID
    private static final String APPID = "APPID";


    // 1 初始化用户身份信息(secretId, secretKey)
    private static COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
    // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
    private static ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing-1"));
    // 3 生成cos客户端
    private static COSClient cosclient = new COSClient(cred, clientConfig);

    public COSClientUtil() {
        cOSClient = new COSClient(cred, clientConfig);
    }

    public static String getSecretId() {
        return secretId;
    }
    public static String getsecretKey() {
        return secretKey;
    }
    public static String getBucketName() {
        return bucketName;
    }
    public static String getAPPID() {
        return APPID;
    }

    /**
     * 销毁
     */
    public void destory() {
        cOSClient.shutdown();
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    public String uploadFileCos(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        try {
            InputStream inputStream = file.getInputStream();

            this.uploadFileCos(inputStream, originalFilename);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return originalFilename;
    }



    /**
     * 上传文件
     * @param instream
     * @param fileName
     * @return
     */
    public String uploadFileCos(InputStream instream, String fileName) {

        String ret = "";
        try {
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);

            PutObjectResult putResult = cOSClient.putObject(bucketName,fileName, instream, objectMetadata);
            ret = putResult.getETag();

            System.out.println(ret);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            cosclient.shutdown();
        }
        return ret;

    }

    /**
     * 获得文件路径 在上传文件之前获取预签名链接用的。
     *
     * @param fileUrl
     * @return
     */
    public String getFileUrl(String fileUrl) {
        return getUrl(fileUrl).toString();
    }

    /**
     * 生成预签名的上传链接
     *
     * @param key
     * @return
     */
    public URL getUrl(String key) {
        // 设置URL过期时间为10年 3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = cOSClient.generatePresignedUrl(bucketName, key, expiration, HttpMethodName.PUT);

        return url;
    }


    /**
     * Description: 判断Cos服务文件上传时文件的contentType
     *
     * @param filenameExtension 文件后缀
     * @return String
     */
    public static String getcontentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase("jpeg") || filenameExtension.equalsIgnoreCase("jpg")
                || filenameExtension.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        if (filenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase("pptx") || filenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase("docx") || filenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }


    /**
     * 下载文件
     * @param downFile
     * @param key
     * @param bucketName
     */
    public void download(File downFile, String key, String bucketName) {
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
        ObjectMetadata downObjectMeta = cOSClient.getObject(getObjectRequest, downFile);
        System.out.println(downObjectMeta.getContentLength());
    }

}
