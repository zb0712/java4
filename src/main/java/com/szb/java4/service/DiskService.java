package com.szb.java4.service;

import com.szb.java4.bean.Disk;

/**
 * @author 石致彬
 * @create 2021-02-20 18:25
 */
public interface DiskService {
    int insertDisk(Disk disk);
    Disk queryByUserId(int UserId);
    int updateDisk(Disk disk);
    Disk queryByDiskId(int diskId);
}
