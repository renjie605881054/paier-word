package com.paier.word.util.image;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;


public class UploadImage {
	
	public String upload(String image,String path){
		// 定义变量存储图片地址
		String imagePath = "";
    	try{
        // 将base64 转 字节数组
        Base64 base = new Base64();
        byte[] decode = base.decode(image);
        // 图片输出路径
        imagePath = path + System.currentTimeMillis() + ".png";
        // 定义图片输入流
        InputStream fin = new ByteArrayInputStream(decode);
        // 定义图片输出流
        FileOutputStream fout=new FileOutputStream(imagePath);
        // 写文件
        byte[] b=new byte[1024];
        int length=0;
        while((length=fin.read(b))>0){
            
            fout.write(b, 0, length);
        }
        
        // 关闭数据流
        fin.close();
        fout.close();
    	}catch(Exception e){
        
    		e.printStackTrace();
    	}
		return imagePath; 
	}
}
