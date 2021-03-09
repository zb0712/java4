package com.szb.java4.service;

import com.szb.java4.bean.MyFile;

import java.util.List;

/**
 * @author 石致彬
 * @create 2021-02-20 21:34
 */
public interface MyFileService {
    int addMyFile(MyFile file);
    List<MyFile> queryMyFileByParentId(Integer parentFolderId);
    List<MyFile> queryMyFileByDiskId(Integer diskId);
    MyFile queryMyFileById(Integer id);
    int deleteMyFile(Integer id);
    int updateMyFile(MyFile myFile);
    List<MyFile> queryNoJudgeImage();
}
