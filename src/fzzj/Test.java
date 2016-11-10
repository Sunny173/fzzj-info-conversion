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
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Test {
	static List<ArrayList<FieldPosition>> regexs = new ArrayList<ArrayList<FieldPosition>>();

	public static void main(String[] args) {
		// test();
		// 1,��ȡ�����ļ�
		regex();
		// 2,�xȡexcel��
		File dir = new File("/home/sunny/yyhudong/fzzj/w1-w100");
		// File dir = new File("G:\\jiejie\\fzzj_qiandao\\nv\\w1-w100");
		// File dir = new File("G:\\jiejie\\fzzj_qiandao\\nv\\222");
		File[] list = dir.listFiles();
		ArrayList<File> errFileList = new ArrayList<File>();
		ArrayList<Person> personList = new ArrayList<Person>();
		for (File file : list) {
			try {
				Workbook wb = Workbook.getWorkbook(file);
				Sheet sheet = wb.getSheet(0); // �ӹ�������ȡ��ҳ��Sheet��
				Person person = new Person(sheet);
				// ����һ�NҎ�tƥ�䣬������ƥ������
				// ArrayList<FieldPosition> regex2 = regexs.get(2);
				// person.setData1(regex2);
				for (int i = 0; i < regexs.size(); i++) {
					// System.out.println("��" + (i + 1) + "�NҎ�t");
					ArrayList<FieldPosition> regex = regexs.get(i);
					boolean isContinut = person.setData1(regex);
					if (isContinut) {
						break;
					}

				}
				if (person.name == null) {
					sheet = wb.getSheet(1);
					person = new Person(sheet);
					// ����һ�NҎ�tƥ�䣬������ƥ������
					for (int i = 0; i < regexs.size(); i++) {
						// System.out.println("��" + (i + 1) + "�NҎ�t");
						ArrayList<FieldPosition> regex = regexs.get(i);
						boolean isContinut = person.setData1(regex);
						if (isContinut) {
							break;
						}

					}
				}
				if (person.name == null) {
					errFileList.add(file);
				} else {
					personList.add(person);
					// System.err.println(file.getName() + "��" +
					// personList.size() + "�lӛ�,");
					System.out.println(person.toString() + "\n");
				}

			} catch (Exception e) {
				errFileList.add(file);
			} // ���ļ����л�ȡExcel����������WorkBook��

		}
		// System.out.println("personList.size=" + personList.size());
		// System.out.println("errFileList.size=" + errFileList.size());
		for (int i = 0; i < errFileList.size(); i++) {
			File file = errFileList.get(i);
			// System.out.println(file.getName() + "��" + i + "�lӛ�,");
			String srcFileName = file.getPath();
			String destFileName = file.getPath();
			destFileName = destFileName.replaceAll("fzzj", "fzzj1");
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

	private static ArrayList<FieldPosition> regex() {
		// 1,��2015-3-20���߰棩
		ArrayList<FieldPosition> fields = new ArrayList<FieldPosition>();
		fields.add(new FieldPosition("�ա�����", 4, 4, FieldPosition.NMAE, 6));
		fields.add(new FieldPosition("�ֻ�����", 4, 5, FieldPosition.phone, 6));
		fields.add(new FieldPosition("ѧ������", 4, 7, FieldPosition.education, 6));
		fields.add(new FieldPosition("ְ����ҵ", 4, 8, FieldPosition.occupation, 6));
		fields.add(new FieldPosition("�������֣�ˮƽ��", 4, 10, FieldPosition.language_class, 9));
		fields.add(new FieldPosition("�е�����", 4, 11, FieldPosition.changzhu_buzu, 6));
		fields.add(new FieldPosition("��ͥ��ַ", 0, 12, FieldPosition.Hukou));
		fields.add(new FieldPosition("�ԡ�����", 21, 4, FieldPosition.sex, 6));
		fields.add(new FieldPosition("ר����ҵ", 21, 7, FieldPosition.zhuan_ye, 6));
		fields.add(new FieldPosition("ְ������", 21, 8, FieldPosition.job, 6));
		fields.add(new FieldPosition("����������", 35, 4, FieldPosition.from_date, 4));
		fields.add(new FieldPosition("����״��", 35, 6, FieldPosition.marriage, 4));
		fields.add(new FieldPosition("ѧУ����λ����", 16, 15, true, FieldPosition.school));
		regexs.add(fields);

		// 2, (2015-09-12��9��)
		fields = new ArrayList<FieldPosition>();
		fields.add(new FieldPosition("�ա�����", 0, 4, FieldPosition.NMAE));
		fields.add(new FieldPosition("ѧ������", 0, 7, FieldPosition.education));
		fields.add(new FieldPosition("ְ����ҵ", 0, 8, FieldPosition.occupation));
		fields.add(new FieldPosition("�������֣�ˮƽ��", 0, 10, FieldPosition.language_class));
		fields.add(new FieldPosition("�е�����", 0, 11, FieldPosition.changzhu_buzu));
		fields.add(new FieldPosition("�ֻ�����", 0, 12, FieldPosition.phone));
		fields.add(new FieldPosition("��ͥ��ַ", 0, 13, FieldPosition.Hukou));
		fields.add(new FieldPosition("�ԡ�����", 2, 4, FieldPosition.sex));
		fields.add(new FieldPosition("ר����ҵ", 2, 7, FieldPosition.zhuan_ye));
		fields.add(new FieldPosition("ְ������", 2, 8, FieldPosition.job));
		fields.add(new FieldPosition("ѧУ����λ����", 2, 16, true, FieldPosition.school));
		fields.add(new FieldPosition("��������", 4, 4, FieldPosition.from_date));
		fields.add(new FieldPosition("����״��", 4, 6, FieldPosition.marriage));
		regexs.add(fields);

		// 3, (2015-08-28��9��)
		fields = new ArrayList<FieldPosition>();
		fields.add(new FieldPosition("�ա�����", 0, 4, FieldPosition.NMAE));
		fields.add(new FieldPosition("ѧ������", 0, 7, FieldPosition.education));
		fields.add(new FieldPosition("ְ����ҵ", 0, 8, FieldPosition.occupation));
		fields.add(new FieldPosition("�������֣�ˮƽ��", 0, 10, FieldPosition.language_class));
		fields.add(new FieldPosition("�е�����", 0, 11, FieldPosition.changzhu_buzu));
		fields.add(new FieldPosition("�ֻ�����", 0, 12, FieldPosition.phone));
		fields.add(new FieldPosition("��ͥ��ַ", 0, 13, FieldPosition.Hukou));
		fields.add(new FieldPosition("�ԡ�����", 2, 4, FieldPosition.sex));
		fields.add(new FieldPosition("ר����ҵ", 2, 7, FieldPosition.zhuan_ye));
		fields.add(new FieldPosition("ְ������", 2, 8, FieldPosition.job));
		fields.add(new FieldPosition("ѧУ����λ����", 2, 16, true, FieldPosition.school));
		fields.add(new FieldPosition("��������", 4, 4, FieldPosition.from_date));
		fields.add(new FieldPosition("����״��", 4, 6, FieldPosition.marriage));
		regexs.add(fields);

		// 4, (2015-09-12��9��)�ೣס����
		fields = new ArrayList<FieldPosition>();
		fields.add(new FieldPosition("�ա�����", 0, 4, FieldPosition.NMAE));
		fields.add(new FieldPosition("ѧ������", 0, 7, FieldPosition.education));
		fields.add(new FieldPosition("ְ����ҵ", 0, 8, FieldPosition.occupation));
		fields.add(new FieldPosition("�������֣�ˮƽ��", 0, 10, FieldPosition.language_class));
		fields.add(new FieldPosition("�е�����", 0, 11, FieldPosition.changzhu_buzu));
		fields.add(new FieldPosition("�ֻ�����", 0, 13, FieldPosition.phone));
		fields.add(new FieldPosition("�������ڵ�", 0, 14, FieldPosition.Hukou));
		fields.add(new FieldPosition("�ԡ�����", 2, 4, FieldPosition.sex));
		fields.add(new FieldPosition("ר����ҵ", 2, 7, FieldPosition.zhuan_ye));
		fields.add(new FieldPosition("ְ������", 2, 8, FieldPosition.job));
		fields.add(new FieldPosition("ѧУ����λ����", 2, 21, true, FieldPosition.school));
		fields.add(new FieldPosition("��������", 4, 4, FieldPosition.from_date));
		fields.add(new FieldPosition("����״��", 4, 6, FieldPosition.marriage));
		regexs.add(fields);

		// 5,(2016-07-18 201607��)
		fields = new ArrayList<FieldPosition>();
		fields.add(new FieldPosition("�ա�����", 0, 4, FieldPosition.NMAE));
		fields.add(new FieldPosition("���ѧ��", 0, 7, FieldPosition.education));
		fields.add(new FieldPosition("ְ����ҵ", 0, 9, FieldPosition.occupation));
		fields.add(new FieldPosition("�е�����", 0, 11, FieldPosition.changzhu_buzu));
		fields.add(new FieldPosition("�ֻ�����", 0, 13, FieldPosition.phone));
		fields.add(new FieldPosition("�������ڵ�", 0, 14, FieldPosition.Hukou));
		fields.add(new FieldPosition("�ԡ�����", 2, 4, FieldPosition.sex));
		fields.add(new FieldPosition("����1��ˮƽ", 2, 8, FieldPosition.language_class));
		fields.add(new FieldPosition("ְ������", 2, 9, FieldPosition.job));
		fields.add(new FieldPosition("��������", 4, 4, FieldPosition.from_date));
		fields.add(new FieldPosition("����״��", 4, 6, FieldPosition.marriage));
		fields.add(new FieldPosition("ר    ҵ", 4, 7, FieldPosition.zhuan_ye));
		fields.add(new FieldPosition("����ѧУ", 3, 21, true, FieldPosition.school));
		regexs.add(fields);

		return fields;
	}

}
