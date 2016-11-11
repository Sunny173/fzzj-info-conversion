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
 * ��ȡ���Ĺ�����Ϣ
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
