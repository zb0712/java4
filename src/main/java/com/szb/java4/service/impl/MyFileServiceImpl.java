package com.szb.java4.service.impl;

import com.szb.java4.bean.MyFile;
import com.szb.java4.mapper.MyFileMapper;
import com.szb.java4.service.MyFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 石致彬
 * @create 2021-02-25 13:21
 */
@Service
public class MyFileServiceImpl implements MyFileService {

    @Autowired
    MyFileMapper myFileMapper;

    @Override
    public int addMyFile(MyFile file) {
        return myFileMapper.addMyFile(file);
    }

    @Override
    public List<MyFile> queryMyFileByParentId(Integer parentFolderId) {
        return myFileMapper.queryMyFileByParentId(parentFolderId);
    }

    @Override
    public List<MyFile> queryMyFileByDiskId(Integer diskId) {
        return myFileMapper.queryMyFileByDiskId(diskId);
    }

    @Override
    public MyFile queryMyFileById(Integer id) {
        return myFileMapper.queryMyFileById(id);
    }

    @Override
    public int deleteMyFile(Integer id) {
        return myFileMapper.deleteMyFile(id);
    }

    @Override
    public int updateMyFile(MyFile myFile) {
        return myFileMapper.updateMyFile(myFile);
    }

    @Override
    public List<MyFile> queryNoJudgeImage() {
        return myFileMapper.queryNoJudgeFile();
    }
}
