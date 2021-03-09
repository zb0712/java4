package com.szb.java4.service.impl;

import com.szb.java4.bean.FileFolderToShow;
import com.szb.java4.bean.Folder;
import com.szb.java4.mapper.FolderMapper;
import com.szb.java4.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 石致彬
 * @create 2021-02-21 13:08
 */
@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    FolderMapper folderMapper;
    @Override
    public int addFolder(Folder folder) {
        return folderMapper.addFolder(folder);
    }

    @Override
    public List<Folder> queryFolderByParentId(Integer parentFolderId) {
        return folderMapper.queryFolderByParentId(parentFolderId);
    }

    @Override
    public List<Folder> queryFolderByDiskId(Integer diskId) {
        return folderMapper.queryFolderByDiskId(diskId);
    }

    @Override
    public List<FileFolderToShow> queryFileFolderByDiskId(Integer diskId,Integer current,Integer pageSize) {
        return folderMapper.queryFileFolderByDiskId( diskId, current, pageSize);
    }

    @Override
    public Integer getRootPageTotalCount(Integer diskId) {
        return folderMapper.getRootPageTotalCount(diskId);
    }

    @Override
    public List<FileFolderToShow> queryFileFolderByParentId(Integer ParentId, Integer current, Integer pageSize) {
        return folderMapper.queryFileFolderByParentId(ParentId, current, pageSize);
    }

    @Override
    public Integer getPageTotalCount(Integer ParentId) {
        return folderMapper.getPageTotalCount(ParentId);
    }
}
