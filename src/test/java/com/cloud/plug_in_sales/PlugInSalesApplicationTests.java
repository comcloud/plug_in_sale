package com.cloud.plug_in_sales;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.filechooser.FileSystemView;

@SpringBootTest
class
PlugInSalesApplicationTests {

    @Test
    void contextLoads() throws WriterException, IOException {



        JSONObject jsonObject = new JSONObject();
        jsonObject.put("company","www.cloud.com");
        jsonObject.put("companyName","云端三七");
        jsonObject.put("author","张玉雷");
        jsonObject.put("wife","戚士树");
        jsonObject.put("home","河南和安徽");
        String content = jsonObject.toString();

        //定义二维码的宽和高
        int width = 200;
        int height = 200;

        //存放字符集
        Map<EncodeHintType,Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");

        //创建矩阵对象
        BitMatrix bit = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hints);

        //根据矩阵对象生成图片

        String filePath = "f://";
        String fileName = "QRCode.jpg";
        Path path = FileSystems.getDefault().getPath(filePath,fileName);
        MatrixToImageWriter.writeToPath(bit,"jpg",path);
        System.out.println("生成二维码成功");
    }

}
