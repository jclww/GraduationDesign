package com.lww.design.graduation.controller.common;

import com.lww.design.graduation.common.AppConstant;
import com.lww.design.graduation.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@Slf4j
public class FileController {

    @RequestMapping(value = "/image/avatar/{user}/{fileId}.png")
    public void getPngImage(@PathVariable(value = "fileId") String fileId, @PathVariable(value = "user") String user, HttpServletResponse response) {
        getImage(fileId, user, AppConstant.PNG_SUFFIX, response);
    }

    @RequestMapping(value = "/image/avatar/{user}/{fileId}.jpg")
    public void getJpgImage(@PathVariable(value = "fileId") String fileId, @PathVariable(value = "user") String user, HttpServletResponse response) {
        getImage(fileId, user, AppConstant.JPG_SUFFIX, response);
    }
    @RequestMapping(value = "/image/avatar/{user}/{fileId}.jpeg")
    public void getJpegImage(@PathVariable(value = "fileId") String fileId, @PathVariable(value = "user") String user, HttpServletResponse response) {
        getImage(fileId, user, AppConstant.JPEG_SUFFIX, response);
    }
    @RequestMapping(value = "/image/goods/{spuId}/{imgname}.jpg")
    public void getGoodsJpgImage(@PathVariable(value = "spuId") String spuId, @PathVariable(value = "imgname") String imgname, HttpServletResponse response) {
        getGoodsImage(spuId, imgname, AppConstant.JPG_SUFFIX, response);
    }
    @RequestMapping(value = "/image/goods/{spuId}/detail/{imgname}.jpg")
    public void getGoodsDetailJpgImage(@PathVariable(value = "spuId") String spuId, @PathVariable(value = "imgname") String imgname, HttpServletResponse response) {
        getGoodsImage(spuId, "detail/" + imgname, AppConstant.JPG_SUFFIX, response);
    }

    public void getGoodsImage(String spuId, String imgname, String suffix, HttpServletResponse response) {
        log.info("get goodsImg spuId:{} imgname:{}", spuId, imgname);
        FileInputStream fis = null;
        response.setContentType("image/gif");
        try {
            OutputStream out = response.getOutputStream();  //通过response显示图片
            File file = new File(AppConstant.GOODS_IMG_UPLOAD_PATH + spuId + "/" + imgname + suffix);     //服务器上图片的路径
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            log.error("get file error, message:{}", e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error("IO close error, message:{}", e.getMessage());
                }
            }
        }
    }


    public void getImage(String fileId, String user, String suffix, HttpServletResponse response) {
        log.info("get avatar fileId:{}", fileId);
        FileInputStream fis = null;
        response.setContentType("image/gif");
        try {
            OutputStream out = response.getOutputStream();  //通过response显示图片
            File file = new File(AppConstant.AVATAR_UPLOAD_PATH + user + "/" + fileId + suffix);     //服务器上图片的路径
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            log.error("get file error, message:{}", e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error("IO close error, message:{}", e.getMessage());
                }
            }
        }
    }

    @RequestMapping("/fileupload/avatar")
    @ResponseBody
    public String fileupload(MultipartFile[] uploadFiles) {
        if (ArrayUtils.isEmpty(uploadFiles)) {
            return "files is empty";
        }

        for (MultipartFile file : uploadFiles) {
            try {
                FileUtil.saveFile(file, AppConstant.AVATAR_UPLOAD_PATH);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return "上传成功";
    }

}

