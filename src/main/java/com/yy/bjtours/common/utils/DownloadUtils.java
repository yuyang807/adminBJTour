package com.yy.bjtours.common.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 文件下载工具类
 *
 * @author lufl
 * @create 2017-08-07 17:32
 **/
public class DownloadUtils {
    public static final String[] IEBrowserSignals  = {"MSIE", "Trident", "Edge"};

    /**
     * 判断是否IE浏览器
     * @param request
     * @return
     */
    public static boolean isMSBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        for (String signal : IEBrowserSignals) {
            if (userAgent.contains(signal))
                return true;
        }
        return false;
    }
    /**
     * 文件下载
     * @param request
     * @param fileName 需要下载文件名称
     * @param dfileName 下载文件显示名称
     * @return
     * @throws IOException
     */
    public static ResponseEntity<byte[]> download(HttpServletRequest request, String fileName, String dfileName) throws IOException {
        String ctxPath = request.getSession().getServletContext().getRealPath("");
        String filePath = ctxPath + "/downloadTemplate/"+fileName;
        //解决不同浏览器中文乱码问题
        if (isMSBrowser(request)) {
            dfileName = URLEncoder.encode(dfileName, "UTF-8");
        } else {
            dfileName = new String(dfileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //解决IE浏览器无法下载文件问题
        headers.set("Content-Disposition", String.format("attachment; filename=\"%s\"", dfileName));
        return new ResponseEntity<byte[]>(org.apache.commons.io.FileUtils.readFileToByteArray(new File(filePath)), headers, HttpStatus.OK);
    }
}
