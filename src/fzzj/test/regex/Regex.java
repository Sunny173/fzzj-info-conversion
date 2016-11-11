package fzzj.test.regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fzzj.excel.FieldPosition;
import jxl.Cell;
import jxl.Sheet;

/**
 * 读取表格的规则信息
 * 
 * @author sunny
 *
 */
public class Regex {
	String regexName;
	public ArrayList<FieldPosition> regexFields = new ArrayList<FieldPosition>();
	boolean isUsed = true;
	public static String str = "";

	public String getStr() {
		String parentPath = getClass().getResource("../").getFile().toString();
		String[] pathArray = parentPath.split("bin");
		parentPath = pathArray[0] + "regex/";
		// /home/sunny/yyhudong/fzzj/fzzj-info-conversion/regex/fzzj/test/
		File dir = new File(parentPath);
		File[] list = dir.listFiles();
		return readTxtFile(list[0]);
	}

	/**
	 * 功能：Java读取txt文件的内容 步骤：1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	 * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
	 * 
	 * @param filePath
	 */
	public String readTxtFile(File file) {
		String result = "";
		try {
			String encoding = "gbk";
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// System.out.println(lineTxt);
					result = result + lineTxt;
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return result;

	}
}
