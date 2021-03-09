package com.szb.java4.service.impl;

import com.szb.java4.bean.Disk;
import com.szb.java4.mapper.DiskMapper;
import com.szb.java4.service.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 石致彬
 * @create 2021-02-20 18:25
 */
@Service
public class DiskServiceImpl implements DiskService {

    @Autowired
    DiskMapper diskMapper;
    @Override
    public int insertDisk(Disk disk) {
        return diskMapper.insertDisk(disk);
    }

    @Override
    public Disk queryByUserId(int UserId) {
        Disk disk = diskMapper.queryByUserId(UserId);
        return disk;
    }

    @Override
    public int updateDisk(Disk disk) {
        return diskMapper.updateDisk(disk);
    }

    @Override
    public Disk queryByDiskId(int diskId) {
        return diskMapper.queryById(diskId);
    }
}
