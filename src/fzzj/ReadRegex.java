package fzzj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fzzj.excel.FieldPosition;

/**
 * 从本地读取文件，将该文件去数字，小括号，子母
 * 
 * @author sun
 * 
 */
public class ReadRegex {

	private String parentPath;

	public ReadRegex() {
		parentPath = getClass().getResource("../").getFile().toString();
		parentPath = parentPath.replaceAll("bin/", "regex/");

	}

	public void getFieldPositions() {
		File dir = new File(parentPath);
		File[] list = dir.listFiles();
		Gson gson = new Gson();
		for (File file : list) {
			String jsonStr = readTxtFile(file);
			List<FieldPosition> filed = parseJsonArrayWithGson(jsonStr, FieldPosition.class);
			// FieldList filed = gson.fromJson(jsonStr, FieldList.class);
			FieldPosition temp = filed.get(0);
			System.out.println(filed.size());
			System.out.println(temp.key_column);
		}
		// return null;
	}

	// 将Json数组解析成相应的映射对象列表
	public <T> List<T> parseJsonArrayWithGson(String jsonData, Class<T> type) {
		Gson gson = new Gson();
		List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {
		}.getType());
		return result;
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
