package com.lww.design.graduation.utils;

import com.lww.design.graduation.common.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Slf4j
public class FileUtil {
    private static final File uploadDirectory = new File(AppConstant.FILE_UPLOAD_PATH);

    public static void saveFile(MultipartFile file, String filepwd) throws Exception {
        if (StringUtils.isBlank(file.getOriginalFilename())) {
            throw new Exception("文件为空");
        }
        byte[] data = readInputStream(file.getInputStream());
        File uploadFile = new File(filepwd + file.getOriginalFilename());
        //判断文件夹是否存在，不存在就创建一个
        File fileDirectory = new File(filepwd);
        synchronized (uploadDirectory) {
            if (!uploadDirectory.exists()) {
                if (!uploadDirectory.mkdir()) {
                    throw new Exception("保存文件的父文件夹创建失败！路径为：" + filepwd);
                }
            }
            createFileDirectory(fileDirectory);
        }

        //创建输出流
        try (FileOutputStream outStream = new FileOutputStream(uploadFile)) {//写入数据
            outStream.write(data);
            outStream.flush();
        } catch (Exception e) {
            log.error("文件写入出错" + e.getMessage());
        }
    }

    private static byte[] readInputStream(InputStream inStream) throws IOException {
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

    private static boolean createFileDirectory(File fileDirectory) {
        synchronized (fileDirectory) {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    if (createFileDirectory(fileDirectory.getParentFile())) {
                        createFileDirectory(fileDirectory);
                    }
                }
            }
            return true;
        }
    }


}

