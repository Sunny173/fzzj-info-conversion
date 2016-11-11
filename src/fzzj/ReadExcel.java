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
		// 1,��ȡ�����ļ�
		Gson gson = new Gson();
		Regex ee = new Regex();
		List<Regex> regex = gson.fromJson(ee.getStr(), RegexString.class).regex_data;
		// 2,�xȡһ��Ŀ¼�µ�excel�ļ�
//		File dir = new File("/home/sunny/yyhudong/fzzj/nv/w1-w100");
		 File dir = new File("/home/sunny/yyhudong/fzzj/nv1/w1-w100");
		// File dir = new File("G:\\jiejie\\fzzj_qiandao\\nv\\w1-w100");
		// File dir = new File("G:\\jiejie\\fzzj_qiandao\\nv\\222");
		ArrayList<Person> errPersonList = new ArrayList<Person>();
		File[] list = dir.listFiles();
		for (File file : list) {
			try {
				Person person = new Person(file);
				// ����һ�NҎ�tƥ�䣬������ƥ������
				for (int i = 0; i < regex.size(); i++) {
					// System.out.println("��" + (i + 1) + "�NҎ�t");
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
			} // ���ļ����л�ȡExcel����������WorkBook��

		}
		// for (int i = 0; i < personList.size(); i++) {
		// Person person = personList.get(i);
		// System.out.println(person.file.getName() + "��" + i + "�lӛ�," +
		// person.toString());
		// }
		System.out.println("��Ϣ����������=" + personList.size());
		for (int i = 0; i < errPersonList.size(); i++) {
			Person person = errPersonList.get(i);
			System.out.println(person.file.getName() + "��" + i + "�lӛ�," + person.toString());
			String srcFileName = person.file.getPath();
			String destFileName = person.file.getPath();
			destFileName = destFileName.replaceAll("nv", "nv1");
			copyFile(srcFileName, destFileName, true);
		}
		System.err.println("���ݲ�����������=" + errPersonList.size());

		System.out.println("���������ݵ��ļ�����=" + errFileList.size());
		// for (int i = 0; i < errFileList.size(); i++) {
		// File file = errFileList.get(i);
		// // System.out.println(file.getName() + "��" + i + "�lӛ�,");
		// String srcFileName = file.getPath();
		// String destFileName = file.getPath();
		// destFileName = destFileName.replaceAll("fzzj", "fzzj1");
		// copyFile(srcFileName, destFileName, true);
		// }
	}

	/**
	 * ���Ƶ����ļ�
	 * 
	 * @param srcFileName
	 *            �����Ƶ��ļ���
	 * @param descFileName
	 *            Ŀ���ļ���
	 * @param overlay
	 *            ���Ŀ���ļ����ڣ��Ƿ񸲸�
	 * @return ������Ƴɹ�����true�����򷵻�false
	 */
	public static boolean copyFile(String srcFileName, String destFileName, boolean overlay) {
		File srcFile = new File(srcFileName);

		// �ж�Դ�ļ��Ƿ����
		if (!srcFile.exists()) {
			System.err.println("Դ�ļ���" + srcFileName + "�����ڣ�");
			return false;
		} else if (!srcFile.isFile()) {
			System.err.println("�����ļ�ʧ�ܣ�Դ�ļ���" + srcFileName + "����һ���ļ���");
			return false;
		}

		// �ж�Ŀ���ļ��Ƿ����
		File destFile = new File(destFileName);
		if (destFile.exists()) {
			// ���Ŀ���ļ����ڲ�������
			if (overlay) {
				// ɾ���Ѿ����ڵ�Ŀ���ļ�������Ŀ���ļ���Ŀ¼���ǵ����ļ�
				new File(destFileName).delete();
			}
		} else {
			// ���Ŀ���ļ�����Ŀ¼�����ڣ��򴴽�Ŀ¼
			if (!destFile.getParentFile().exists()) {
				// Ŀ���ļ�����Ŀ¼������
				if (!destFile.getParentFile().mkdirs()) {
					// �����ļ�ʧ�ܣ�����Ŀ���ļ�����Ŀ¼ʧ��
					return false;
				}
			}
		}

		// �����ļ�
		int byteread = 0; // ��ȡ���ֽ���
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
