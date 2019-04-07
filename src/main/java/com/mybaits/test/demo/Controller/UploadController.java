package com.mybaits.test.demo.Controller;

import com.mybaits.test.demo.Common.JSONResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UploadController {
    private final static String filePath = "D://IT_java/upload/";
//    private final static String filePath = "/data/upload/";

    @PostMapping("/api/uploadImg")
    public JSONResult uploadImg(@RequestParam("file")MultipartFile file, HttpServletRequest request){

        String fileName=file.getOriginalFilename();
        System.out.println(fileName);
        String fileExt=fileName.substring(fileName.lastIndexOf("."));
        String newFileName=UUID.randomUUID().toString()+fileExt;
        System.out.println(newFileName);
        File files=new File(filePath+newFileName);
        Map<String ,String> f=new HashMap<>();
        f.put("fileName",newFileName);
        f.put("filePath",files.getAbsolutePath());
        f.put("url","/img/"+newFileName);
        try {
            file.transferTo(files);
            return   JSONResult.ok(f);
        } catch (IOException e) {
            e.printStackTrace();
            return     JSONResult.errorMsg(e.getMessage());

        }

    }
}
