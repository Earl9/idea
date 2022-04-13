package com.wild.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    /** 绝对路径 **/
//    private static String absolutePath = "src/main/resources/static/upload"
//    private static String absolutePath;
//    /** 静态目录 **/
//    private static String staticDir = "";
//    /** 文件存放的目录 **/
//    private static String fileDir = "";
    /**
     * 上传单个文件
     * 最后文件存放路径为：static/img/11.jpg
     * 文件访问路径为：http://127.0.0.1:8080/img/11.jpg
     * 该方法返回值为：/img/11.jpg
     * @param inputStream 文件流
     * @param path 文件路径，如：image/
     * @param filename 文件名，如：test.jpg
     * @return 成功：上传后的文件访问路径，失败返回：null
     */
    public static String upload(InputStream inputStream, String path, String filename) {

        //第一次会创建文件夹
        createDirIfNotExists(path);
        //获取后缀
        String sname = filename.substring(filename.lastIndexOf("."));
        //获取文件名
        String qname = filename.substring(0,filename.lastIndexOf("."));
        //时间格式化格式
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyyMMddHHmmss");
        //获取当前时间并作为时间戳
        String timeStamp=simpleDateFormat.format(new Date());
        //拼接新的文件名
        String newName = timeStamp+sname;

        String resultPath =  path + newName;

        //存文件
        File uploadFile = new File(resultPath);
        try {
            FileUtils.copyInputStreamToFile(inputStream, uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return newName;
    }
    /**
     * 创建文件夹路径
     */
    private static void createDirIfNotExists(String path) {
        if (!path.isEmpty()) {return;}

        //获取跟目录
        File file = null;
        try {
            file = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("获取根目录失败，无法创建上传目录！");
        }
        if(!file.exists()) {
            file = new File("");
        }

        path = file.getAbsolutePath();

        File upload = new File(path);
        if(!upload.exists()) {
            upload.mkdirs();
        }
    }
    /**
     * 删除文件
     * @param path 文件访问的路径upload开始 如：img/11.jpg
     * @return true 删除成功； false 删除失败
     */
    public static boolean delete(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }
    /**
     * 获取服务部署根路径 http:// + ip + port
     *
     * @param request
     * @return
     */
    public static String getServerIPPort(HttpServletRequest request) {
        //+ ":" + request.getServerPort()
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }
}
