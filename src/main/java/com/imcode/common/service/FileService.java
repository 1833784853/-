package com.imcode.common.service;

import com.imcode.common.model.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Autowired(required = false)
    private HttpSession session;

    @Value("${file.server}")
    private String fileServer;

    @Value("${file.location}")
    private String fileLocation;

    public R upload(MultipartFile uploadFile) throws IOException {
        String fileName = uploadFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") - 1);
        String uuid = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        fileName = uuid + suffix;

        String realPath = session.getServletContext().getRealPath(fileLocation);
        File file = new File(realPath, fileName);
        if (!file.exists()) {
            file.mkdirs();
        }
        uploadFile.transferTo(file);
        R ok = R.ok();
        ok.put("url", fileServer + "/" + fileName);
        ok.put("name", fileName);
        System.out.println(ok);
        return ok;
    }

    public boolean deleteFile(String fileNmae) {
        String realPath = session.getServletContext().getRealPath(fileLocation);
        File file = new File(realPath,fileNmae);
        // 判断文件是否存在并且是文件
        if (file.exists() && file.isFile()) {
           return file.delete();
        } else {
            return false;
        }
    }

}
