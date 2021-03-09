package com.szb.java4.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szb.java4.bean.FileFolderToShow;
import com.szb.java4.bean.Folder;
import com.szb.java4.bean.User;
import com.szb.java4.common.MyPage;
import com.szb.java4.common.Result;
import com.szb.java4.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 石致彬
 * @create 2021-02-21 13:17
 */
@Controller
public class FolderController extends BaseController {

    @Autowired
    FolderService folderService;

    /**
     * 新建文件夹 需要文件夹的名称和父文件夹的id
     * @param name
     * @param pid
     * @return
     */
    @ResponseBody
    @PostMapping("/addFolder")
    public Result addFolder(@RequestParam("folderName")String name,
                            @RequestParam("parentFolderId") String pid) {
        Integer id = Integer.parseInt(pid);
        System.out.println(name);
        System.out.println(id);
        Folder folder = new Folder(null,name,id,null,"folder");
        System.out.println(folder);
        User loginUser = (User) session.getAttribute("loginUser");
        System.out.println("folderSession:"+session.getId());
        System.out.println(loginUser);
        if (loginUser == null) {
            return Result.error("没有登录信息");
        }
        folder.setDiskId(loginUser.getDiskId());
        List<Folder> folders;
        if (folder.getParentFolderId() == 0) {
            folder.setParentFolderId(null);
            //如果是根目录根据磁盘ID获取同级文件夹(所有根目录)
            folders = folderService.queryFolderByDiskId(folder.getDiskId());
        } else {
            //根据父文件夹ID获取同级的文件夹
            folders = folderService.queryFolderByParentId(folder.getParentFolderId());
        }
        String folderName = folder.getFolderName();
        if (folders != null) {
            for (Folder folder1 : folders) {
                if (folder1.getFolderName().equalsIgnoreCase(folderName)) {
                    return Result.error("文件夹已存在");
                }
            }
        }
        folderService.addFolder(folder);
        return Result.ok(null);
    }

    /**
     * 根据文件夹id查询所有的子文件夹和文件
     * @param id
     * @param cu
     * @param psize
     * @return
     */
    @ResponseBody
    @PostMapping("/view")
    public Result view(@RequestParam("parentFolderId") String id,
                       @RequestParam("current")String cu,
                       @RequestParam("pageSize")String psize) {
        Integer parentFolderId = Integer.parseInt(id);
        Integer current = Integer.parseInt(cu);
        Integer pageSize = Integer.parseInt(psize);
        System.out.println(parentFolderId);
        System.out.println(current);
        System.out.println(pageSize);
        HashMap<String, Object> map = new HashMap<>();
        current--;
        if (parentFolderId == 0) {
            //如果是根目录
            User loginUser = (User) session.getAttribute("loginUser");
            System.out.println("viewSession:"+session.getId());
            parentFolderId = loginUser.getDiskId();
            MyPage<FileFolderToShow> page = rootPage(parentFolderId,current,pageSize);
            page.setCurrent(page.getCurrent()+1);
            map.put("page", page);
            System.out.println(page);
        } else {
            MyPage<FileFolderToShow> page = Page(parentFolderId,current,pageSize);
            page.setCurrent(page.getCurrent()+1);
            System.out.println(page);
            map.put("page", page);
        }

        return Result.ok(map);
    }

    private MyPage<FileFolderToShow> rootPage(Integer id,Integer current,Integer pageSize) {
        MyPage<FileFolderToShow> page = new MyPage<>(current, pageSize);
        Integer pageTotalCount = folderService.getRootPageTotalCount(id);
        page.setPageTotalCount(pageTotalCount);
        int pageTotal = pageTotalCount / page.getPageSize();
        if (pageTotalCount % page.getPageSize() > 0) {
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        List<FileFolderToShow> fileFolderToShows = folderService.queryFileFolderByDiskId(id, page.getCurrent()*page.getPageSize(), page.getPageSize());
        page.setItems(fileFolderToShows);
        return page;
    }
    private MyPage<FileFolderToShow> Page(Integer id,Integer current,Integer pageSize) {
        MyPage<FileFolderToShow> page = new MyPage<>(current, pageSize);
        Integer pageTotalCount = folderService.getPageTotalCount(id);
        page.setPageTotalCount(pageTotalCount);
        int pageTotal = pageTotalCount / page.getPageSize();
        if (pageTotalCount % page.getPageSize() > 0) {
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        List<FileFolderToShow> fileFolderToShows = folderService.queryFileFolderByParentId(id, page.getCurrent()*page.getPageSize(), page.getPageSize());
        System.out.println(fileFolderToShows);
        page.setItems(fileFolderToShows);
        return page;
    }



}
