package com.szb.java4.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.szb.java4.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author 石致彬
 * @create 2021-02-17 17:45
 */
@CrossOrigin
@Controller
public class KaptchaController extends BaseController{

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @ResponseBody
    @GetMapping("/kaptcha")
    public void getKaptcha2(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String text = defaultKaptcha.createText();

        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        BufferedImage bi = defaultKaptcha.createImage(text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

}
