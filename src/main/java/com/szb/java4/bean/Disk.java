package com.szb.java4.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 石致彬
 * @create 2021-02-16 18:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disk {
    private Integer diskId;
    private Integer userId;
    private Integer size;
    private Integer maxSize;
}
