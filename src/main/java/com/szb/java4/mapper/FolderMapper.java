package com.szb.java4.mapper;

import com.szb.java4.bean.FileFolderToShow;
import com.szb.java4.bean.Folder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 石致彬
 * @create 2021-02-20 21:32
 */
@Mapper
public interface FolderMapper {
    /**
     * 新建文件夹
     * @param folder
     * @return
     */
    int addFolder(Folder folder);

    /**
     * 根据父文件夹id获取所有子文件夹
     * @param parentFolderId
     * @return
     */
    List<Folder> queryFolderByParentId(Integer parentFolderId);

    /**
     * 根据磁盘id获取所有根目录
     * @param diskId
     * @return
     */
    List<Folder> queryFolderByDiskId(Integer diskId);

    /**
     * 根据id获取文件夹
     * @param id
     * @return
     */
    Folder queryFolderById(Integer id);

    /**
     * 获取根目录下的分页数据
     * @param diskId
     * @param current
     * @param pageSize
     * @return
     */
    List<FileFolderToShow> queryFileFolderByDiskId(Integer diskId,Integer current,Integer pageSize);

    /**
     * 获取根目录下的分页数据总数
     * @param diskId
     * @return
     */
    Integer getRootPageTotalCount(Integer diskId);

    /**
     * 获取文件夹下的分页数据
     * @param ParentId
     * @param current
     * @param pageSize
     * @return
     */
    List<FileFolderToShow> queryFileFolderByParentId(Integer ParentId,Integer current,Integer pageSize);

    Integer getPageTotalCount(Integer ParentId);
}
