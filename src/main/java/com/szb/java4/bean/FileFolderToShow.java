package com.szb.java4.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * @author 石致彬
 * @create 2021-02-24 21:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileFolderToShow {
    private Integer id;
    private String name;
    private Integer parentFolderId;
    private String myType;
}
