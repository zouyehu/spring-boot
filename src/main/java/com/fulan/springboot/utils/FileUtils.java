package com.fulan.springboot.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 读取,写入文件操作工具类
 * @author zyh
 *
 */
@Component
public class FileUtils {

    private static Log log = LogFactory.getLog(FileUtils.class);

    @Value("${spring.file.path}")
    private String path;

    public void setFileUser(){
        String name = StrUtils.getRandomNumber(6);
        String pwd = StrUtils.getRandomNumber(6);
        BufferedOutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(path));
            String str = new String(name.concat("_").concat(pwd));
            log.info("---密码设置成功:" + str);
            outputStream.write(str.getBytes());
            outputStream.flush();
            
        }catch (Exception e){
            log.error("---写入文件发生异常:", e);
        }finally{
        	try {
				outputStream.close();
			} catch (IOException e) {
				log.error("---文件流关闭异常:", e);
			}
        }
    }

    public String getUserByFile(){
        //文件取值
        String userInfo = null;
        BufferedInputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(path));
            byte[] bytes = new byte[2048];
            int n = -1;
            while ((n=inputStream.read(bytes,0,bytes.length))!=-1){
                userInfo = new String(bytes,0,n,"utf-8");
            }
            log.info("---文件取值:" + userInfo);
            
        }catch (Exception e){
            log.error("---读取异常:", e);
        }finally{
        	try {
				inputStream.close();
			} catch (IOException e) {
				log.error("---文件流关闭异常-2:", e);
			}
        }
        return userInfo;
    }

    public void setFileSession(String sessionValue){
    	BufferedOutputStream outputStream = null;
        try {
            String user = getUserByFile();
            outputStream = new BufferedOutputStream(new FileOutputStream(path));
            outputStream.write((user.concat("###").concat(sessionValue)).getBytes());
            outputStream.flush();
            
        }catch (Exception e){
            log.error("---写入文件发生异常-2:", e);
        }finally{
        	try {
				outputStream.close();
			} catch (IOException e) {
				log.error("---文件流关闭异常-3:", e);
			}
        }
    }
}
