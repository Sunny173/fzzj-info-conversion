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
					+ "\\" + name_start + "_������Ϣ��." + name_end;
			System.out.println("��" + i + "��" + "---destFileName="
					+ destFileName);
			copyFile(srcFileName, destFileName, true);
		}
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
	public static boolean copyFile(String srcFileName, String destFileName,
			boolean overlay) {
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
