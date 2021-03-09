package com.szb.java4.mapper;

import com.szb.java4.bean.Disk;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 石致彬
 * @create 2021-02-20 18:08
 */
@Mapper
public interface DiskMapper {
    /**
     * 向数据库中插入一条网盘数据(创建网盘)
     * @param disk
     * @return
     */
    int insertDisk(Disk disk);

    /**
     * 根据UserId获得其网盘
     * @param UserId
     * @return
     */
    Disk queryByUserId(int UserId);

    /**
     * 根据网盘id获取网盘
     * @param id
     * @return
     */
    Disk queryById(int id);

    /**
     * 上传文件后更新网盘大小
     * @param disk
     * @return
     */
    int updateDisk(Disk disk);
}
