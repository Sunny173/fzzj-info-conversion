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
 * �ӱ��ض�ȡ�ļ��������ļ�ȥ���֣�С���ţ���ĸ
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

	// ��Json�����������Ӧ��ӳ������б�
	public <T> List<T> parseJsonArrayWithGson(String jsonData, Class<T> type) {
		Gson gson = new Gson();
		List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {
		}.getType());
		return result;
	}

	/**
	 * ���ܣ�Java��ȡtxt�ļ������� ���裺1���Ȼ���ļ���� 2������ļ��������������һ���ֽ���������Ҫ��������������ж�ȡ
	 * 3����ȡ������������Ҫ��ȡ�����ֽ��� 4��һ��һ�е������readline()�� ��ע����Ҫ���ǵ����쳣���
	 * 
	 * @param filePath
	 */
	public String readTxtFile(File file) {
		String result = "";
		try {
			String encoding = "gbk";
			if (file.isFile() && file.exists()) { // �ж��ļ��Ƿ����
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// ���ǵ������ʽ
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// System.out.println(lineTxt);
					result = result + lineTxt;
				}
				read.close();
			} else {
				System.out.println("�Ҳ���ָ�����ļ�");
			}
		} catch (Exception e) {
			System.out.println("��ȡ�ļ����ݳ���");
			e.printStackTrace();
		}
		return result;

	}
}
