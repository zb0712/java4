package com.szb.java4.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 石致彬
 * @create 2021-02-16 18:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Folder {
    private Integer folderId;
    private String folderName;
    private Integer parentFolderId;
    private Integer diskId;
    private String myType;
}
