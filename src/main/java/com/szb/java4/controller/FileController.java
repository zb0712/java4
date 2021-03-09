package com.szb.java4.controller;

import com.szb.java4.bean.Disk;
import com.szb.java4.bean.MyFile;
import com.szb.java4.bean.User;
import com.szb.java4.common.Result;
import com.szb.java4.service.DiskService;
import com.szb.java4.service.FolderService;
import com.szb.java4.service.MyFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author 石致彬
 * @create 2021-02-25 11:26
 */
@Controller
public class FileController extends BaseController {

    @Autowired
    MyFileService myFileService;

    @Autowired
    DiskService diskService;


    @PostMapping("/upload")
    @ResponseBody
    public Result uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("parentFolderId") String pid,
                             @RequestParam("path") String path
    ) {
        User loginUser = (User) session.getAttribute("loginUser");
        Disk disk = diskService.queryByUserId(loginUser.getUserId());
        Integer parentFolderId = Integer.parseInt(pid);
        //检查文件是否重名
        List<MyFile> myFiles;
        if (parentFolderId == 0) {
            myFiles = myFileService.queryMyFileByDiskId(loginUser.getDiskId());
        } else {
            myFiles = myFileService.queryMyFileByParentId(parentFolderId);
        }
        for (MyFile myFile1 : myFiles) {
            if (myFile1.getFileName().equals(file.getOriginalFilename())) {
                return Result.error("文件已存在");
            }
        }
        //检查文件的格式是否为图片
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        int index = filename.lastIndexOf(".");
        String format = filename.substring(index + 1);
        System.out.println(format);
        if (!isImg(format)) {
            return Result.error("不支持该文件格式上传");
        }
        //检查空间是否足够
        int size = (int) (file.getSize() / 1024);
        if (disk.getMaxSize() - disk.getSize() < size) {
            return Result.error("磁盘容量不足,上传失败");
        }

        new File("D:\\data\\" + loginUser.getUsername() + "\\" + path).mkdirs();
        //文件上传
        try {
            file.transferTo(new File("D:\\data\\" + loginUser.getUsername() + "\\" + path + "\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("服务器异常,文件上传失败");
        }
        path = "D:\\data\\" + loginUser.getUsername() + "\\" + path + "\\" + filename;
        //写入数据库
        if (parentFolderId == 0) {
            parentFolderId = null;
        }
        MyFile myFile = new MyFile(null, filename, parentFolderId, path, new Date(new java.util.Date().getTime()), format
                , size, 0, disk.getDiskId(), "file");
        myFileService.addMyFile(myFile);
        //更新磁盘容量
        size = size + disk.getSize();
        disk.setSize(size);
        diskService.updateDisk(disk);
        return Result.ok(null);
    }

    /**
     * 下载
     *
     * @param id
     */
    @ResponseBody
    @GetMapping("/download")
    public void downloadFile(@RequestParam("fileId") String id) {
        Integer fileId = Integer.parseInt(id);
        MyFile file = myFileService.queryMyFileById(fileId);
        System.out.println(file);
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getFileName(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ServletOutputStream os = null;
        try {
            File file1 = new File(file.getFilePath());
            fis = new FileInputStream(file1);
            bis = new BufferedInputStream(fis);
            os = response.getOutputStream();
            int len;
            while ((len = bis.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取文件数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/fileInfo")
    public Result getFileInfo(@RequestParam("fileId") String id) {
        Integer fileId = Integer.parseInt(id);
        MyFile file = myFileService.queryMyFileById(fileId);
        if (file == null) {
            return Result.error("未找到该文件");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("file", file);
        return Result.ok(map);
    }

    private boolean isImg(String format) {
        if ("jpg".equalsIgnoreCase(format) || "png".equalsIgnoreCase(format) || "gif".equalsIgnoreCase(format) || "bmp".equalsIgnoreCase(format)
                || "ico".equalsIgnoreCase(format)) {
            return true;
        }
        return false;
    }


}
