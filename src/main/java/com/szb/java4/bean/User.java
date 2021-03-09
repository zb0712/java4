package com.szb.java4.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 石致彬
 * @create 2021-02-16 18:29
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId;
    private String username;
    private String password;
    private Integer diskId;
    private Integer identity;
}
