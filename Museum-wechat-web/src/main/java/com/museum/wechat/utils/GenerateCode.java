package com.museum.wechat.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.Hashtable;
import java.util.UUID;

public class GenerateCode {
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    public static void main(String[] args) {
        try {
            boolean flag = generateCode("518");
            if (flag) {
                System.out.println("成功生成二维码");
            }
        } catch (WriterException | IOException e) {
            System.err.println("生成二维码失败");
            e.printStackTrace();
        }
    }

    public static boolean generateCode(String productId) throws WriterException, IOException {
        // 这里是URL，扫描之后就跳转到这个界面
        String text = "https://www.baidu.com";
        String string ="museum/code/"+UUID.randomUUID().toString()+"我的";
        int width = 400;
        int height = 400;
        // 二维码图片格式
        String format = "jpg";
        // 设置编码，防止中文乱码
        Hashtable<EncodeHintType, Object> ht = new Hashtable<EncodeHintType, Object>();
        ht.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 设置二维码参数(编码内容，编码类型，图片宽度，图片高度,格式)
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, ht);

        int b_width = bitMatrix.getWidth();
        int b_height = bitMatrix.getHeight();
        // 建立图像缓冲器
        BufferedImage image = new BufferedImage(b_width, b_height, BufferedImage.TYPE_3BYTE_BGR);
        for (int x = 0; x < b_width; x++) {
            for (int y = 0; y < b_height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
            }
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // 生成二维码
        ImageIO.write(image, format,os);
        //上传到七牛云
        Auth auth = Auth.create("CnR-Fl77zEBcEbBOUoex7A72eeLoYwsAZywmr4ji", "fX-ZdBaErQvS-HTauYajABxBBlDdhGVz7DWtELf5");
        Configuration config = new Configuration(Zone.autoZone());
        BucketManager bucketMgr = new BucketManager(auth, config);

        InputStream byteInputStream = new ByteArrayInputStream(os.toByteArray());
        String token = auth.uploadToken("museum");
        Response put = new UploadManager(config).put(byteInputStream, string, token,null,null);

        // 二维码的名称
        // code.jpg

        return true;
    }
}

