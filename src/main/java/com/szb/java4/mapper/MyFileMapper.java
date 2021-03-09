package com.szb.java4.mapper;

import com.szb.java4.bean.MyFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 石致彬
 * @create 2021-02-20 21:32
 */
@Mapper
public interface MyFileMapper {
    /**
     * 将文件数据写入数据库
     * @param file
     * @return
     */
    int addMyFile(MyFile file);

    /**
     * 根据父文件夹id获取文件夹下的所有文件
     * @param parentFolderId
     * @return
     */
    List<MyFile> queryMyFileByParentId(Integer parentFolderId);

    /**
     * 根据磁盘id获取 根文件?
     * @param diskId
     * @return
     */
    List<MyFile> queryMyFileByDiskId(Integer diskId);

    /**
     * 根据文件id获取文件
     * @param id
     * @return
     */
    MyFile queryMyFileById(Integer id);

    int deleteMyFile(Integer id);

    int updateMyFile(MyFile myFile);

    List<MyFile> queryNoJudgeFile();
}
