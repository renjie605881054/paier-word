package com.paier.word.util.custom;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化工具类
 * @author Administrator
 *
 */
public class SerializeUtil {

	public static byte[] serializeObj(Object obj){
		ObjectOutputStream oos = null ;
		ByteArrayOutputStream baos = null ;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			byte[] bytes = baos.toByteArray();
			return bytes ;
		} catch (IOException e) {
			System.out.println(8);
			return null;
		}
	}
	
	public static Object unserializeObj(byte[] bytes){
		ObjectInputStream oii = null;
		ByteArrayInputStream bis = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			oii = new ObjectInputStream(bis);
			Object obj = oii.readObject();
			return obj;
		} catch (Exception e) {
			return null;
		}
	}
	
}
