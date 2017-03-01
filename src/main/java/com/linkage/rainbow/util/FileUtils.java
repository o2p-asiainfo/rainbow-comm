package com.linkage.rainbow.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtils {

	private static Log log = LogFactory.getLog(FileUtils.class);
	public static final String lineSeparator = System
			.getProperty("line.separator");
	public final static String fileSeparator = System
			.getProperty("file.separator");

 
	public static void moveFile(String oldDir, String oldFileName,
			String newDir, String newFileName) {
		File oldFile = new File(oldDir + fileSeparator + oldFileName);
		File newFile = new File(newDir + fileSeparator + newFileName);
		oldFile.renameTo(newFile);
	}
	public static void writeFile(String chkFileName, String line) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(chkFileName, true);
			fw.write(line + lineSeparator);
			fw.flush();
		} catch (IOException e) {
			log.error(e.getStackTrace());
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					log.error(e.getStackTrace());
				}
			}
		}
	}

 
	public static List<String> getFileListByDir(String dirPath) {
		List<String> fileAbsPathList = new ArrayList<String>();

		File dir = null;
		File files[] = null;
		try {
			dir = new File(dirPath);
			files = dir.listFiles();

			if (files == null) {
				return null;
			}

			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					fileAbsPathList.add(files[i].getAbsolutePath());

				}
			}
		} catch (Exception ex) {
			log.error(ex.getStackTrace());
		} finally {
			dir = null;
			files = null;
		}

		return fileAbsPathList;
	}
 
	public static final boolean copy(String from, String to) throws Exception {
		makeParentDir(to);
		FileInputStream fis = new FileInputStream(from);
		try {
			FileOutputStream fos = new FileOutputStream(to, false);
			try {
				byte[] buf = new byte[1024 * 16];
				int size = 0;
				while ((size = fis.read(buf)) != -1)
					fos.write(buf, 0, size);
				return true;
			} catch (Exception ex) {
				return false;
			} finally {
				fos.flush();
				fos.close();
			}
		} catch (Exception ex) {
			return false;
		} finally {
			fis.close();
		}
	}

	 
	public static final void makeParentDir(String filename) throws Exception {
		File file = new File(filename);
		if (!file.exists()) {
			String parent = file.getParent();
			if (parent != null)
				new File(parent).mkdirs();
		}
	}

 
	public static long getLineNum(String fileName) throws Exception {
		long lineNum = 0;
		if (fileName != null) {
			BufferedReader in = null;
			try {
				in = new BufferedReader(new FileReader(fileName));
				while ((in.readLine()) != null) {
					lineNum++;
				}
			} catch (Exception e) {
				throw e;
			} finally {
				if(in!=null){
					in.close();
				}
			}
		}
		return lineNum;
	}

 
	public static byte[] read(String fileName) throws Exception {
		if (fileName == null) {
			return null;
		}
		FileInputStream in = null;
		try {
			in = new FileInputStream(fileName);
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			return bytes;
		} catch (Exception e) {
			throw e;
		} finally {
			if(in!=null){
				in.close();
			}
		}
	}

 
	public static void write(byte[] bytes, String fileName) throws Exception {
		if (bytes == null || fileName == null) {
			return;
		}
		mkDir(fileName);  
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(fileName);
			out.write(bytes);
		} catch (Exception e) {
			throw e;
		} finally {
			out.close();
		}
	}

	 
	public static String readTxt(String fileName) throws Exception {
		if (fileName == null) {
			return null;
		}
		BufferedReader in = null;
		String lineIn = null;
		StringBuffer buffer = new StringBuffer();
		try {
			in = new BufferedReader(new FileReader(fileName));
			while ((lineIn = in.readLine()) != null) {
				buffer.append(lineIn.trim());
				buffer.append(lineSeparator);  
			}
			return buffer.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			if(in!=null){
				in.close();
			}
		}
	}
 
	public static String readLine(String fileName) throws Exception {
		if (fileName == null) {
			return null;
		}
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileName));
			String str = in.readLine();
			if (str != null) {
				str = str.trim();
			}
			return str;
		} catch (Exception e) {
			throw e;
		} finally {
			if(in!=null){
				in.close();
			}
		}
	}

	 
	public static void write(String str, String fileName, boolean isAppend)
			throws Exception {
		if (fileName == null || str == null) {
			return;
		}
		makeParentDir(fileName);  
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new FileWriter(fileName, isAppend));
			out.write(str);
		} catch (Exception e) {
			throw e;
		} finally {
			out.close();
		}
	}

 
	public static void writeTxt(String str, String fileName) throws Exception {
		write(str, fileName, false);
	}

	 
	public static void appendTxt(String str, String fileNane) throws Exception {
		write(str, fileNane, true);
	}

 

	public static boolean mkDir(String dirName) {
		File file = new File(dirName);
		if (file.exists())  
		{
			if (file.canWrite() == false)  
			{
				return false;
			} else {
				return true;
			}
		} else  
		{
			String path = null;

			int firstSlash = dirName.indexOf(File.separator);
			int finalSlash = dirName.lastIndexOf(File.separator);

			if (finalSlash == 0)  
			{
				return false;
			} else if (finalSlash == 1)  
			{
				path = File.separator;
			} else if (firstSlash == finalSlash)  
			{
				path = dirName.substring(0, finalSlash + 1);
			} else {
				path = dirName.substring(0, finalSlash);
			}

			File dir = new File(path);
			return dir.mkdirs();
		}
	}

	public static String getFileSize(String filePath) {
		String fileSize = String.valueOf(Double.valueOf(new File(filePath)
				.length())
				/ Double.valueOf(1000 * 1000));
		if ((Double.valueOf(new File(filePath).length()) / Double
				.valueOf(1000 * 1000)) < 0.001) {
			fileSize = "0.001";
		}
		if (fileSize.indexOf(".") != -1) {
			fileSize = fileSize.substring(0, fileSize.indexOf(".") + 4);
		}
		return fileSize + "M";
	}


	public static Timestamp getFileCreateTime(String filePath) {
		return new Timestamp(new File(filePath).lastModified());
	}
 
	public static String get_charset(File file) {
		String charset = "GBK";
		byte[] first3Bytes = new byte[3];
		BufferedInputStream bis = null;
		try {
			boolean checked = false;
			bis= new BufferedInputStream(
					new FileInputStream(file));
			bis.mark(0);
			int read = bis.read(first3Bytes, 0, 3);
			if (read == -1)
				return charset;
			if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
				charset = "UTF-16LE";
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xFE
					&& first3Bytes[1] == (byte) 0xFF) {
				charset = "UTF-16BE";
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xEF
					&& first3Bytes[1] == (byte) 0xBB
					&& first3Bytes[2] == (byte) 0xBF) {
				charset = "UTF-8";
				checked = true;
			}
			bis.reset();
			if (!checked) {
				int len = 0;
				int loc = 0;
				while ((read = bis.read()) != -1) {
					loc++;
					if (read >= 0xF0)
						break;
					if (0x80 <= read && read <= 0xBF)  
						break;
					if (0xC0 <= read && read <= 0xDF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) 
							continue;
						else
							break;
					} else if (0xE0 <= read && read <= 0xEF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							read = bis.read();
							if (0x80 <= read && read <= 0xBF) {
								charset = "UTF-8";
								break;
							} else
								break;
						} else
							break;
					}
				}
			}
			
		} catch (Exception e) {
			log.error(e.getStackTrace());
		} finally{
			if(bis !=null){
				try {bis.close();} catch (Exception e2) {}
			}
		}
		return charset;
	}

}
