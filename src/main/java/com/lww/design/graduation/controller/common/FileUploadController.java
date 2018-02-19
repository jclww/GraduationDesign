package com.lww.design.graduation.controller.common;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.common.AppConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Collections;

@RestController
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(com.lww.design.graduation.controller.HelloController.class);
    private static final File uploadDirectory = new File(AppConstant.FILE_UPLOAD_PATH);


    @RequestMapping("/hello")
    @ResponseBody
    public String selectByName(MultipartFile[] uploadFiles) {
        if (ArrayUtils.isEmpty(uploadFiles)) {
            return "files is empty";
        }

        for (MultipartFile file : uploadFiles) {
            logger.info(file.getName());
            try {
                saveFile(file);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        String str = "sadsa";
        return JSON.toJSONString(str);
    }

    private void saveFile(MultipartFile file) throws Exception {
        byte[] data = readInputStream(file.getInputStream());
        File uploadFile = new File(AppConstant.FILE_UPLOAD_PATH + file.getName());
        //判断文件夹是否存在，不存在就创建一个
        File fileDirectory = new File(AppConstant.FILE_UPLOAD_PATH);
        synchronized (uploadDirectory) {
            if (!uploadDirectory.exists()) {
                if (!uploadDirectory.mkdir()) {
                    throw new Exception("保存文件的父文件夹创建失败！路径为：" + AppConstant.FILE_UPLOAD_PATH);
                }
            }
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new Exception("文件夹创建失败！路径为：" + AppConstant.FILE_UPLOAD_PATH);
                }
            }
        }

        //创建输出流
        try (FileOutputStream outStream = new FileOutputStream(uploadFile)) {//写入数据
            outStream.write(data);
            outStream.flush();
        } catch (Exception e) {
            logger.error("文件写入出错"+e.getMessage());
        }

    }
    public static byte[] readInputStream(InputStream inStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}

