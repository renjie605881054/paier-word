package com.paier.word.util.sftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.paier.word.util.StringUtil;

public class SFTPUtil {

	private static JSch jSch = new JSch() ;
	private static Session session = null ;
	private static Channel channel = null ;
	private static ChannelSftp sftp = null ;
	
	/**
	 * 登陆远程服务器
	 * @param host 地址
	 * @param port 端口
	 * @param userName 登录名
	 * @param password 密码 
	 * @param privateKey 私钥
	 * @param passphrase 私钥密码
	 * @return
	 */
	private static boolean login(String host, int port, String userName, String password, String privateKey,
			String passphrase) {
		try {
			if (!StringUtil.isBlank(privateKey)) {
				if (!StringUtil.isBlank(passphrase)) {
					// 设置带口令的密钥
					jSch.addIdentity(privateKey, passphrase);
				} else {
					// 设置不带口令的密钥
					jSch.addIdentity(privateKey);
				}
			}
			if (port <= 0) {
				// 连接服务器，采用默认端口
				session = jSch.getSession(userName, host);
			} else {
				// 采用指定的端口连接服务器
				session = jSch.getSession(userName, host, port);
			}
			// 设置登陆主机的密码
			if (!StringUtil.isBlank(password)) {
				session.setPassword(password);// 设置密码
			}
			Properties sshConfig = new Properties();
			// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
			sshConfig.put("StrictHostKeyChecking", "no");
			session.setConfig(sshConfig);
			session.connect();
			channel = session.openChannel("sftp"); // 打开SFTP通道
			channel.connect(); // 建立SFTP通道的连接
			sftp = (ChannelSftp) channel;
			return true;
		} catch (JSchException e) {
			System.err.println("SSH方式连接FTP服务器时有JSchException异常!");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * 下载文件 
	 * @param host 远程地主
	 * @param port 端口号
	 * @param userName 登录名
	 * @param password 密码
	 * @param privateKey 私钥
	 * @param passphrase 私钥密码
	 * @param remotePath 远程文件夹
	 * @param remoteFilename 文件名
	 * @param localFilename 下载文件名
	 * @return
	 */
	public static boolean downloadFile(String host, int port, String userName, String password, String privateKey,
			String passphrase, String remotePath, String remoteFilename, String localFilename) {  
        FileOutputStream output = null;  
        boolean success = false;  
        try {  
            // SSH方式登录FTP服务器  
            success = login(host, port, userName, password, privateKey, passphrase);  
            //登录失败  
            if (!success) {  
                return success;  
            }  
            if (!StringUtil.isBlank(remotePath)) {  
                sftp.cd(remotePath);  
            }  
            File localFile = new File(localFilename);  
            //有文件和下载文件重名  
            if (localFile.exists()) {  
                sftp.disconnect();  
                System.err.println("文件: " + localFilename + " 已经存在!");  
                return success;  
            }
            output = new FileOutputStream(localFile);  
            sftp.get(remoteFilename, output);  
            success = true;  
        } catch (SftpException e) {  
            System.err.println("接收文件时有SftpException异常!");  
            e.printStackTrace();  
            System.err.println(e.getMessage());  
            return success;  
        } catch (IOException e) {  
            System.err.println("接收文件时有I/O异常!");  
            System.err.println(e.getMessage());  
            return success;  
        } finally {  
            try {  
                if (null != output) {  
                    output.close();  
                }  
            } catch (IOException e) {  
                System.err.println("关闭文件时出错!");  
                System.err.println(e.getMessage());  
            }  
            if (null != sftp && sftp.isConnected()) {  
                sftp.disconnect();  
            }  
            if (null != channel && channel.isConnected()) {  
                channel.disconnect();  
            }  
            if (null != session && session.isConnected()) {  
                session.disconnect();  
            }  
        }  
        return success;  
    }  

	public static void main(String[] args) {
		downloadFile("222.73.39.37", 50022, "200004595271", null, 
				"D:\\workspace\\zhongziben\\treasureFinal-p2padmin\\src\\main\\resources\\properties\\id_rsa",
				null, "/upload/busiexport/", "20160423_zwmx-yh-cqg.zip", "E:\\20160323_zwmx-yh-cqg.zip");
	}
	
}
