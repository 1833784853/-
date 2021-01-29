package com.imcode.common.controller;

import com.imcode.common.model.R;
import com.imcode.common.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/common/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    @ResponseBody
    public R upload(@RequestParam("uploadFile") MultipartFile uploadFile) {
        try {
            return fileService.upload(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @PostMapping("/uploads")
    @ResponseBody
    public R uploads(@RequestParam("uploadFile") MultipartFile[] uploadFile) {
        ArrayList<Map<String, Object>> urls = new ArrayList<Map<String, Object>>();
        try {
            for (MultipartFile f : uploadFile) {
                urls.add(fileService.upload(f));
            }
            R ok = R.ok().put("data", urls);
            return ok;
        } catch (IOException e) {
            e.printStackTrace();

            return R.error(e.getMessage()).put("data", urls);
        }
    }
}
