package com.szb.java4.service;

import com.szb.java4.bean.FileFolderToShow;
import com.szb.java4.bean.Folder;

import java.util.List;

/**
 * @author 石致彬
 * @create 2021-02-20 21:34
 */
public interface FolderService {
    int addFolder(Folder folder);
    List<Folder> queryFolderByParentId(Integer parentFolderId);
    List<Folder> queryFolderByDiskId(Integer diskId);
    List<FileFolderToShow> queryFileFolderByDiskId(Integer diskId,Integer current,Integer pageSize);
    Integer getRootPageTotalCount(Integer diskId);
    List<FileFolderToShow> queryFileFolderByParentId(Integer ParentId,Integer current,Integer pageSize);
    Integer getPageTotalCount(Integer ParentId);
}
