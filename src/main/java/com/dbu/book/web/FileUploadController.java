package com.dbu.book.web;

import com.alibaba.fastjson.JSONObject;

import com.sun.deploy.net.HttpResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.HttpResource;
import sun.misc.BASE64Encoder;


import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


/**
 * 文件上传的Controller
 */
@Controller
@RequestMapping("/image")
public class FileUploadController {
    public static final String ROOT = "upload-dir";

    private final ResourceLoader resourceLoader;

    @Autowired
    public FileUploadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 文件上传具体实现方法（单文件上传）
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String jsonImage = "";
        if (!file.isEmpty()) {
            try {
                String name = file.getOriginalFilename();
                String filename = UUID.randomUUID().toString() + name.substring(name.lastIndexOf("."));
                Path path = Paths.get(ROOT, filename);
                File dir = new File(ROOT);
                if (!dir.exists()) {
                    Files.createDirectory(path);
                }
                Files.copy(file.getInputStream(), path);
                jsonImage = filename;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        json.put("info", "上传成功");
        json.put("image",jsonImage);
        return json;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
    @ResponseBody
    public void getFile(@PathVariable String filename, HttpServletResponse response) {
        try {
            String path = Paths.get(ROOT, filename).toString();
            FileInputStream fis = null;
            response.setContentType("image/gif");
            try {
                OutputStream out = response.getOutputStream();
                File file = new File(path);
                fis = new FileInputStream(file);
                byte[] b = new byte[fis.available()];
                fis.read(b);
                out.write(b);
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            //return ResponseEntity.notFound().build();
        }
    }


}