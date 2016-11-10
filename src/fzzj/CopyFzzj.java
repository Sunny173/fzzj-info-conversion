package fzzj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import fzzj.excel.FieldPosition;
import fzzj.excel.Person;
import fzzj.excel.RegexFile;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class CopyFzzj {

	public static void main(String[] args) {
		// File dir = new File("/home/sunny/yyhudong/fzzj/w1-w100");
		File dir = new File("G:\\jiejie\\fzzj_qiandao\\nv\\pre_copy");
		// File dir = new File("G:\\jiejie\\fzzj_qiandao\\nv\\222");
		File[] list = dir.listFiles();
		String[] regex = { "\\d{1,10}", "w", "W", "m", "-", " " };
		for (int i = 0; i < list.length; i++) {
			File file = list[i];
			String srcFileName = file.getPath();
			String name = file.getName();
			String[] nameArray = name.split("\\.");
			String name_start = nameArray[0];
			for (String reg : regex) {
				name_start = name_start.replaceAll(reg, "");
			}
			String name_end = nameArray[1];
			String destFileName = "G:\\jiejie\\fzzj_jingwai\\" + name_start
					+ "\\" + name_start + "_个人信息表." + name_end;
			System.out.println("第" + i + "条" + "---destFileName="
					+ destFileName);
			copyFile(srcFileName, destFileName, true);
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @param srcFileName
	 *            待复制的文件名
	 * @param descFileName
	 *            目标文件名
	 * @param overlay
	 *            如果目标文件存在，是否覆盖
	 * @return 如果复制成功返回true，否则返回false
	 */
	public static boolean copyFile(String srcFileName, String destFileName,
			boolean overlay) {
		File srcFile = new File(srcFileName);

		// 判断源文件是否存在
		if (!srcFile.exists()) {
			System.err.println("源文件：" + srcFileName + "不存在！");
			return false;
		} else if (!srcFile.isFile()) {
			System.err.println("复制文件失败，源文件：" + srcFileName + "不是一个文件！");
			return false;
		}

		// 判断目标文件是否存在
		File destFile = new File(destFileName);
		if (destFile.exists()) {
			// 如果目标文件存在并允许覆盖
			if (overlay) {
				// 删除已经存在的目标文件，无论目标文件是目录还是单个文件
				new File(destFileName).delete();
			}
		} else {
			// 如果目标文件所在目录不存在，则创建目录
			if (!destFile.getParentFile().exists()) {
				// 目标文件所在目录不存在
				if (!destFile.getParentFile().mkdirs()) {
					// 复制文件失败：创建目标文件所在目录失败
					return false;
				}
			}
		}

		// 复制文件
		int byteread = 0; // 读取的字节数
		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];

			while ((byteread = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteread);
			}
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
