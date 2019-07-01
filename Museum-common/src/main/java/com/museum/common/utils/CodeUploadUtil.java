package com.museum.common.utils;

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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.UUID;

public class CodeUploadUtil {
        private static final int BLACK = 0xFF000000;
        private static final int WHITE = 0xFFFFFFFF;

        public static String generateCode(String ak,String sk,String exhibitsName,String bucketName,String url) throws WriterException, IOException {
            // 这里是URL，扫描之后就跳转到这个界面
            String text = url;
            String string =exhibitsName+"-"+UUID.randomUUID().toString()+".jpg";
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
            Auth auth = Auth.create(ak, sk);
            Configuration config = new Configuration(Zone.autoZone());
            BucketManager bucketMgr = new BucketManager(auth, config);

            InputStream byteInputStream = new ByteArrayInputStream(os.toByteArray());
            String token = auth.uploadToken(bucketName);
            Response put = new UploadManager(config).put(byteInputStream, string, token,null,null);

            // 二维码的名称
            // code.jpg

            return string;
        }
    }


