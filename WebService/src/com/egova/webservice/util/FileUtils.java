package com.egova.webservice.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 文件处理工具类
 * @author nyj
 */
public class FileUtils {
	private static Log logger = LogFactory.getLog(FileUtils.class);
	
	/**
	 * 根据完整文件路径获取目录结构，并将\转换为/
	 * @param filePath 完整文件路径 
	 * @return String
	 */
	public static String getDirFromPath(String filePath) {
		filePath = filePath.replace('\\', '/');
		int index = filePath.lastIndexOf("/");
		if(index == -1) {
			return filePath;
		}
		return filePath.substring(0, index);
	}
	
	/**
	 * 根据完整文件路径获取文件名称
	 * @param filePath 完整文件路径 
	 * @return String
	 */
	public static String getFileNameFromPath(String filePath) {
		filePath = filePath.replace('\\', '/');
		int index = filePath.lastIndexOf("/");
		if(index == -1 || index == filePath.length()) {
			return filePath;
		}
		/*
		//正则匹配，过滤路径取文件名
		filePath.replaceAll("\\", "/");
		String regExp=".+//(.+)$";
        Pattern p = Pattern.compile(regExp);
        Matcher match = p.matcher(filePath);
        boolean result = match.find();
        String fileName = null;
        if(result) {
        	fileName = match.group(1);
        }*/
		return filePath.substring(index + 1);
	}
	
	/**
	 * 将流写入指定共享目录文件中
	 * @param shareDir 共享目录路径
	 * @param filePath 共享目录中的文件保存目录结构，包含文件名，如果目录不存在则创建
	 * @param userName 用户名
	 * @param password 密码
	 * @param File 源文件
	 * @return 是否成功
	 */
	public static boolean writeToSharePath(String shareDir, String filePath,
			String userName, String password, File source) {
		boolean result = false;
		try {
			InputStream is = new FileInputStream(source);
			result = writeToSharePath(shareDir, filePath, userName, password, is);
		} catch(Exception e) {
			logger.error("FileUtils.writeToSharePath:" + e);
			result = false;
		}
		return result;
	}

	/**
	 * 删除指定文件
	 * @param shareDir 共享目录路径
	 * @param filePath 共享目录中的文件保存目录结构，包含文件名，如果目录不存在则创建
	 * @param userName 用户名
	 * @param password 密码
	 * @param fileName 文件名称
	 * @return 是否成功
	 */
	public static boolean deleteFile(String shareDir, String filePath,
			String userName, String password, String fileName) {
		shareDir = shareDir.replace('\\', '/');
		if(shareDir.startsWith("//")) {
			shareDir = shareDir.substring(2);
		}
		if(!shareDir.endsWith("/")) {
			shareDir += "/";
		}
		String smbFilePath = "smb://" + userName + ":" + password 
			+ "@" + shareDir;
		logger.info(smbFilePath);
		String tempPath = smbFilePath;
		try {
			SmbFile netFilePath = new SmbFile(smbFilePath);
			if(!netFilePath.exists()) {
				logger.error("FileUtils.deleteFile[无法找到共享目录]:" + shareDir);
				return false;
			}
			//如果目录不存在则创建
			String dirPath = getDirFromPath(filePath);
			String [] dirPaths = dirPath.split("/");
			for(int i = 0; i < dirPaths.length; i++) {
				tempPath += dirPaths[i] + "/";
				netFilePath= new SmbFile(tempPath);
				if(!netFilePath.exists()) {
					return true;
				}
			}
			if(!filePath.endsWith("/")) {
				filePath += "/";
			}
			logger.info(smbFilePath + filePath + fileName);
			SmbFile smbFile = new SmbFile(smbFilePath + filePath + fileName);
			if(!smbFile.exists()){
				return true;
			}
			smbFile.delete();
		} catch(Exception e) {
			logger.error("FileUtils.deleteFile:" + e);
			return false;
		} finally {
		}
		return true;
	}
	
	/**
	 * 将流写入指定共享目录文件中
	 * @param shareDir 共享目录路径
	 * @param filePath 共享目录中的文件保存目录结构，包含文件名，如果目录不存在则创建
	 * @param userName 用户名
	 * @param password 密码
	 * @param fis 文件流
	 * @return 是否成功
	 */
	public static boolean writeToSharePath(String shareDir, String filePath,
			String userName, String password, InputStream fis) {
		shareDir = shareDir.replace('\\', '/');
		if(shareDir.startsWith("//")) {
			shareDir = shareDir.substring(2);
		}
		if(!shareDir.endsWith("/")) {
			shareDir += "/";
		}
		String smbFilePath = "smb://" + userName + ":" + password 
			+ "@" + shareDir;
		String tempPath = smbFilePath;
		SmbFileOutputStream sfos = null;
		try {
			SmbFile netFilePath = new SmbFile(smbFilePath);
			if(!netFilePath.exists()) {
				logger.error("FileUtils.writerToSharePath[无法找到共享目录]:" + shareDir);
				return false;
			}
			//如果目录不存在则创建
			String dirPath = getDirFromPath(filePath);
			String [] dirPaths = dirPath.split("/");
			for(int i = 0; i < dirPaths.length; i++) {
				tempPath += dirPaths[i] + "/";
				netFilePath= new SmbFile(tempPath);
				if(!netFilePath.exists()) {
					netFilePath.mkdir();
				}
			}
			SmbFile smbFile = new SmbFile(smbFilePath + filePath);
			sfos = new SmbFileOutputStream(smbFile);
			int nBufSize = 64 * 1024;
			byte[] buf = new byte[nBufSize];
			int nRead = -1;
			while ((nRead = fis.read(buf)) >  -1) {
				sfos.write(buf, 0, nRead);
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("FileUtils.writerToSharePath:" + e);
			return false;
		} finally {
			if(sfos != null) {
				try {
					sfos.close();
				} catch(Exception e) {
				}
			}
			if(fis != null) {
				try {
					fis.close();
				} catch(Exception e) {
				}
			}
		}
		return true;
	}
	
	/**
	 * 获取共享目录文件输入流
	 * @param shareDirFile
	 * @param filePath
	 * @param userName
	 * @param password
	 * @return
	 */
	public static InputStream getShareFileInputStream(String shareDir, String filePath,String userName, String password){
		shareDir = shareDir.replace('\\', '/');
		if(shareDir.startsWith("//")) {
			shareDir = shareDir.substring(2);
		}
		if(!shareDir.endsWith("/")) {
			shareDir += "/";
		}
		String smbFilePath = "smb://" + userName + ":" + password 
			+ "@" + shareDir;
		InputStream sfis = null;
		try {
			SmbFile smbFile = new SmbFile(smbFilePath + filePath);
			if(!smbFile.exists()) {
				System.out.println("文件不存在！");
				return sfis;
			}
			sfis = new SmbFileInputStream(smbFile);
			return (InputStream)sfis;
		}catch(Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 将共享目录文件写入本地目录
	 * @param shareDir 共享目录路径
	 * @param filePath 共享目录中的文件保存目录结构，包含文件名
	 * @param userName 用户名
	 * @param password 密码
	 * @param localDir 本地目录
	 * @return 生成的文件路径，包含文件名，失败返回null
	 */
	public static String writeShareFileToLocalPath(String shareDir, String filePath,
			String userName, String password, String localDir) {
		localDir = localDir.replace('\\', '/');
		if(!localDir.endsWith("/")) {
			localDir += "/";
		}
		shareDir = shareDir.replace('\\', '/');
		if(shareDir.startsWith("//")) {
			shareDir = shareDir.substring(2);
		}
		if(!shareDir.endsWith("/")) {
			shareDir += "/";
		}
		String smbFilePath = "smb://" + userName + ":" + password 
			+ "@" + shareDir;
		SmbFileInputStream sfis = null;
		FileOutputStream fos = null;
		try {
			SmbFile smbFile = new SmbFile(smbFilePath + filePath);
			if(!smbFile.exists()) {
				logger.error("FileUtils.writeShareFileToLocalPath:can't found file:"
						+ smbFilePath + filePath);
				return null;
			}
			sfis = new SmbFileInputStream(smbFile);
			String fileName = getFileNameFromPath(filePath);
			File localFile = new File(localDir + fileName);
			fos = new FileOutputStream(localFile);
			int nBufSize = 64 * 1024;
			byte[] buf = new byte[nBufSize];
			int nRead = -1;
			while ((nRead = sfis.read(buf)) >  -1) {
				fos.write(buf, 0, nRead);
			}
			return localDir + fileName;
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("FileUtils.writeShareFileToLocalPath:" + e);
			return null;
		} finally {
			if(sfis != null) {
				try {
					sfis.close();
				} catch(Exception e) {
				}
			}
			if(fos != null) {
				try {
					fos.close();
				} catch(Exception e) {
				}
			}
		}
	}
	
	/**
	 * 拷贝一个文件从共享目录到另外一个共享目录
	 * @param sourceDir
	 * @param sourceFilePath
	 * @param sourceUserName
	 * @param sourcePassword
	 * @param targetDir
	 * @param targetFilePath
	 * @param targetUserName
	 * @param targetPassword
	 * @return
	 */
	public static boolean copyShareFileToSharFile(String sourceDir, String sourceFilePath, String sourceUserName, String sourcePassword,
			String targetDir, String targetFilePath, String targetUserName, String targetPassword){
		InputStream in = getShareFileInputStream(sourceDir, sourceFilePath,sourceUserName, sourcePassword);
		if(in == null){
			return false;
		}
		return writeToSharePath(targetDir, targetFilePath, targetUserName, targetPassword, in);
	}
	
	/**
	 * 共享目录复制文件
	 * @param shareDir 共享目录路径
	 * @param userName 用户名
	 * @param password 密码
	 * @param sourceFilePath 共享目录中源文件目录结构，包含文件名
	 * @param targetFilePath 共享目录中目标文件目录结构，包含文件名
	 * @return 是否成功
	 */
	public static boolean copyFileInShareDir(String shareDir, String userName, String password,
			String sourceFilePath, String targetFilePath) {
		sourceFilePath = sourceFilePath.replace('\\', '/');
		targetFilePath = targetFilePath.replace('\\', '/');
		
		shareDir = shareDir.replace('\\', '/');
		if(shareDir.startsWith("//")) {
			shareDir = shareDir.substring(2);
		}
		if(!shareDir.endsWith("/")) {
			shareDir += "/";
		}
		String smbFilePath = "smb://" + userName + ":" + password 
			+ "@" + shareDir;
		
		try {
			SmbFile smbSourceFile = new SmbFile(smbFilePath + sourceFilePath);
			if(!smbSourceFile.exists()) {
				logger.error("FileUtils.copyFileInShareDir:can't found file:"
						+ smbFilePath + sourceFilePath);
				return false;
			}
			
			String tempPath = smbFilePath;
			//如果目标目录不存在则创建
			String dirPath = getDirFromPath(targetFilePath);
			if(dirPath.startsWith("/")) {
				dirPath = dirPath.substring(1);
			}
			String [] dirPaths = dirPath.split("/");
			for(int i = 0; i < dirPaths.length; i++) {
				tempPath += dirPaths[i] + "/";
				SmbFile netFilePath= new SmbFile(tempPath);
				if(!netFilePath.exists()) {
					netFilePath.mkdir();
				}
			}
			
			SmbFile smbTargetFile = new SmbFile(smbFilePath + targetFilePath);
			smbSourceFile.copyTo(smbTargetFile);
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("FileUtils.copyFileInShareDir:" + e);
			return false;
		} finally {
		}
		return true;
	}
	
	/**
	 * 删除目标文件夹
	 * @param 目标路径
	 * @return 是否成功
	 */
	public static boolean deleteFloder(String filePath) {
		if (filePath == null) return false;
		File path = new File(filePath);
		return deleteFloder(path);
	}

	/**
	 * 删除目标文件夹
	 * @param 目标路径
	 * @return 是否成功
	 */
	public static boolean deleteFloder(File filePath) {
		boolean result = true;
		if (filePath == null) return false;
		if (!filePath.exists()) return true;
		if (!filePath.isDirectory()) return false;
		// ********** 清空给定路径内所有内容 **********
		File[] allFiles = filePath.listFiles();
		for (File file: allFiles) {
			if (file == null) continue;
			if (file.isDirectory()) result = FileUtils.deleteFloder(file);
			else if (file.isFile()) {
				try { result = file.delete(); }
				catch (Exception e) { result = false; }
			}
			if (!result) break;	// 如果 file 没能成功处理，则终止循环
		}
		// ********** 删除给定路径本身 **********
		if (result) result = filePath.delete();
		return result;
	}   

	/**
	 * 拷贝文件
	 * @param 源文件
	 * @param 目标文件
	 */
	public static void copyFile(File in, File out) throws Exception {
	     FileInputStream fis  = new FileInputStream(in);
	     FileOutputStream fos = new FileOutputStream(out);
	     byte[] buf = new byte[1024];
	     int i = 0;
	     while((i=fis.read(buf))!=-1) {
	       fos.write(buf, 0, i);
	       }
	     fis.close();
	     fos.close();
	}

	/**
	 * 将文件保存到本地
	 * @param in
	 * @param localpath
	 */
	public static void saveFileStreamToLocalPath(InputStream in,String localpath){
		FileOutputStream fos = null;
		try {
			File localFile = new File(localpath);
			fos = new FileOutputStream(localFile);
			int nBufSize = 64 * 1024;
			byte[] buf = new byte[nBufSize];
			int nRead = -1;
			while ((nRead = in.read(buf)) >  -1) {
				fos.write(buf, 0, nRead);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {
				}
			}
			if(fos != null) {
				try {
					fos.close();
				} catch(Exception e) {
				}
			}
		}
	}
	
	/**
	 * 拷贝文件
	 * @param 源文件路径
	 * @param 目标文件路径
	 */
	public static void copyFile(String smbFilePath, String localpath) throws Exception {
//		 File inFile = new File(inputFilePath);
//		 File outFile = new File(outputFilePath);
//		 copyFile(inFile,outFile);
        File localfile = new File(localpath);   
        InputStream bis = null;   
        OutputStream bos = null;   
        try {   
            SmbFile rmifile  =  new SmbFile(smbFilePath);
            if(rmifile.exists()){
	            bis = new BufferedInputStream(new SmbFileInputStream(rmifile));                  
	            bos = new BufferedOutputStream(new FileOutputStream(localfile));   
	            int length = rmifile.getContentLength();   
	            byte[] buffer = new byte[length];   
	            bis.read(buffer);   
	            bos.write(buffer);
            }
        } catch (Exception e){   
            logger.error(e.getMessage());   
        }finally{   
            try {
            	if(bos != null)
            		bos.close();   
                if(bis != null)
                	bis.close();   
            } catch (IOException e) {   
                e.printStackTrace();   
            }               
        }   
    }   
	
	/**
	 * 下载本地文件
	 * @param localPath 文件完整路径
	 * @param fileDiplayName 文件显示名称
	 * @param response
	 */
	public static void downloadSoftware(String localPath, String fileDiplayName,
			HttpServletResponse response) {
		response.reset();
		response.setContentType("application/octet-stream");
		try {
			response.setHeader("Content-Disposition","attachment; filename=" +  
					java.net.URLEncoder.encode(fileDiplayName == null ? "" : fileDiplayName, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		File file = null;
		FileInputStream fis = null;
		try {
			OutputStream os = response.getOutputStream();
			file = new File(localPath);
			fis = new FileInputStream(file);
			int nBufSize = 64 * 1024;
			byte[] buf = new byte[nBufSize];
			int nRead = -1;
			while ((nRead = fis.read(buf)) >  -1) {
				os.write(buf, 0, nRead);
			}
		}
		catch(Exception e) {
			System.out.println("可能该软件已经被删除！或者用户取消下载！");
		} finally {			
			try {
				fis.close();
				file.delete();
			} catch(Exception e) {
				
			}			
		}
	}
	
	/**
	 * 获取目标文件夹下的文件名称列表
	 * @param 目标路径
	 * @return 是否成功
	 */
	public static ArrayList<String> getFielCountsByFloder(String shareDir,String userName,String password) {
	    ArrayList<String>  list = new ArrayList<String>();
	    shareDir = shareDir.replace('\\', '/');	    
		if(shareDir.startsWith("//")) {
			shareDir = shareDir.substring(2);
		}
		if(!shareDir.endsWith("/")) {
			shareDir += "/";
		}		
		//smbFilePath smb://administrator:nbzhcg-123@10.19.72.30/MediaRoot/NavigationVideo/ 
		String smbFilePath = "smb://" + userName + ":" + password 
			+ "@" + shareDir;		
		try {		
			SmbFile dir = new SmbFile(smbFilePath);
			if(!dir.exists()) {
				logger.error("FileUtils.getFielCountsByFloder[无法找到共享目录中导航视频目录]:can't found file:"
						+ dir );
				return null;
			}
			SmbFile[] file = dir.listFiles();
			 
			String fileName = "";
			for (int i = 0 ;i < file.length;i++){				 
				fileName = getFileNameFromPath(file[i].getPath());
				if(fileName.indexOf(".db")  == -1){
					list.add(fileName) ;
				}
			}			   
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("FileUtils.getFielCountsByFloder: smbFilePath=" + smbFilePath + " " + e);
			return null;
		} finally {
		} 
	}   
	

	/**
	 * 判断指定文件是否存在
	 * @param shareDir 共享目录路径
	 * @param filePath 共享目录中的文件保存目录结构，包含文件名，如果目录不存在则创建
	 * @param userName 用户名
	 * @param password 密码
	 * @param fileName 文件名称
	 * @return 是否存在
	 */
	public static boolean isExistsFile(String shareDir, String filePath,
			String userName, String password, String fileName) {
		shareDir = shareDir.replace('\\', '/');
		if(shareDir.startsWith("//")) {
			shareDir = shareDir.substring(2);
		}
		if(!shareDir.endsWith("/")) {
			shareDir += "/";
		}
		String smbFilePath = "smb://" + userName + ":" + password 
			+ "@" + shareDir;
		logger.info(smbFilePath);
		try {
			SmbFile netFilePath = new SmbFile(smbFilePath);
			if(!netFilePath.exists()) {
				logger.error("FileUtils.deleteFile[无法找到共享目录]:" + shareDir);
				return false;
			}
			if(!filePath.endsWith("/")) {
				filePath += "/";
			}
			logger.info(smbFilePath + filePath + fileName);
			SmbFile smbFile = new SmbFile(smbFilePath + filePath + fileName);
			if(!smbFile.exists()){
				return false;
			}
		} catch(Exception e) {
			logger.error("FileUtils.deleteFile:" + e);
			return false;
		} finally {
		}
		return true;
	}
}
