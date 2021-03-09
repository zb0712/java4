package com.szb.java4.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @author 石致彬
 * @create 2021-02-16 18:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyFile {
    private Integer fileId;
    private String fileName;
    private Integer parentFolderId;
    private String filePath;
    private Date uploadTime;
    private String format;
    private Integer size;
    private Integer examed;
    private Integer diskId;
    private String myType;
    private User user;

    public MyFile(Integer fileId, String fileName, Integer parentFolderId, String filePath, Date uploadTime, String format, Integer size, Integer examed, Integer diskId, String myType) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.parentFolderId = parentFolderId;
        this.filePath = filePath;
        this.uploadTime = uploadTime;
        this.format = format;
        this.size = size;
        this.examed = examed;
        this.diskId = diskId;
        this.myType = myType;
    }
}
