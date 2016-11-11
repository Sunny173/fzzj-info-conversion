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
import fzzj.test.regex.Regex;
import fzzj.test.regex.RegexString;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {
	static ArrayList<File> errFileList = new ArrayList<File>();
	static ArrayList<Person> personList = new ArrayList<Person>();

	public static void main(String[] args) {
		// 1,读取配置文件
		Gson gson = new Gson();
		Regex ee = new Regex();
		List<Regex> regex = gson.fromJson(ee.getStr(), RegexString.class).regex_data;
		// 2,讀取一个目录下的excel文件
//		File dir = new File("/home/sunny/yyhudong/fzzj/nv/w1-w100");
		 File dir = new File("/home/sunny/yyhudong/fzzj/nv1/w1-w100");
		// File dir = new File("G:\\jiejie\\fzzj_qiandao\\nv\\w1-w100");
		// File dir = new File("G:\\jiejie\\fzzj_qiandao\\nv\\222");
		ArrayList<Person> errPersonList = new ArrayList<Person>();
		File[] list = dir.listFiles();
		for (File file : list) {
			try {
				Person person = new Person(file);
				// 若第一種規則匹配，不用在匹配其他
				for (int i = 0; i < regex.size(); i++) {
					// System.out.println("第" + (i + 1) + "種規則");
					if (person.analysisRegex(regex.get(i))) {
						break;
					}

				}
				if (person.name == null) {
					errFileList.add(file);
				} else if (person.isNoValue()) {
					errPersonList.add(person);
				} else {
					personList.add(person);
				}

			} catch (Exception e) {
				errFileList.add(file);
			} // 从文件流中获取Excel工作区对象（WorkBook）

		}
		// for (int i = 0; i < personList.size(); i++) {
		// Person person = personList.get(i);
		// System.out.println(person.file.getName() + "第" + i + "條記錄," +
		// person.toString());
		// }
		System.out.println("信息完整的人数=" + personList.size());
		for (int i = 0; i < errPersonList.size(); i++) {
			Person person = errPersonList.get(i);
			System.out.println(person.file.getName() + "第" + i + "條記錄," + person.toString());
			String srcFileName = person.file.getPath();
			String destFileName = person.file.getPath();
			destFileName = destFileName.replaceAll("nv", "nv1");
			copyFile(srcFileName, destFileName, true);
		}
		System.err.println("数据不完整的人数=" + errPersonList.size());

		System.out.println("读不出数据的文件个数=" + errFileList.size());
		// for (int i = 0; i < errFileList.size(); i++) {
		// File file = errFileList.get(i);
		// // System.out.println(file.getName() + "第" + i + "條記錄,");
		// String srcFileName = file.getPath();
		// String destFileName = file.getPath();
		// destFileName = destFileName.replaceAll("fzzj", "fzzj1");
		// copyFile(srcFileName, destFileName, true);
		// }
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
	public static boolean copyFile(String srcFileName, String destFileName, boolean overlay) {
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
