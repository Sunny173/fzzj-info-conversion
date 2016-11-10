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
		// 1,读取配置文件
		regex();
		// 2,讀取excel表
		File dir = new File("/home/sunny/yyhudong/fzzj/w1-w100");
		// File dir = new File("G:\\jiejie\\fzzj_qiandao\\nv\\w1-w100");
		// File dir = new File("G:\\jiejie\\fzzj_qiandao\\nv\\222");
		File[] list = dir.listFiles();
		ArrayList<File> errFileList = new ArrayList<File>();
		ArrayList<Person> personList = new ArrayList<Person>();
		for (File file : list) {
			try {
				Workbook wb = Workbook.getWorkbook(file);
				Sheet sheet = wb.getSheet(0); // 从工作区中取得页（Sheet）
				Person person = new Person(sheet);
				// 若第一種規則匹配，不用在匹配其他
				// ArrayList<FieldPosition> regex2 = regexs.get(2);
				// person.setData1(regex2);
				for (int i = 0; i < regexs.size(); i++) {
					// System.out.println("第" + (i + 1) + "種規則");
					ArrayList<FieldPosition> regex = regexs.get(i);
					boolean isContinut = person.setData1(regex);
					if (isContinut) {
						break;
					}

				}
				if (person.name == null) {
					sheet = wb.getSheet(1);
					person = new Person(sheet);
					// 若第一種規則匹配，不用在匹配其他
					for (int i = 0; i < regexs.size(); i++) {
						// System.out.println("第" + (i + 1) + "種規則");
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
					// System.err.println(file.getName() + "第" +
					// personList.size() + "條記錄,");
					System.out.println(person.toString() + "\n");
				}

			} catch (Exception e) {
				errFileList.add(file);
			} // 从文件流中获取Excel工作区对象（WorkBook）

		}
		// System.out.println("personList.size=" + personList.size());
		// System.out.println("errFileList.size=" + errFileList.size());
		for (int i = 0; i < errFileList.size(); i++) {
			File file = errFileList.get(i);
			// System.out.println(file.getName() + "第" + i + "條記錄,");
			String srcFileName = file.getPath();
			String destFileName = file.getPath();
			destFileName = destFileName.replaceAll("fzzj", "fzzj1");
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

	private static ArrayList<FieldPosition> regex() {
		// 1,（2015-3-20第七版）
		ArrayList<FieldPosition> fields = new ArrayList<FieldPosition>();
		fields.add(new FieldPosition("姓　　名", 4, 4, FieldPosition.NMAE, 6));
		fields.add(new FieldPosition("手机号码", 4, 5, FieldPosition.phone, 6));
		fields.add(new FieldPosition("学　　历", 4, 7, FieldPosition.education, 6));
		fields.add(new FieldPosition("职　　业", 4, 8, FieldPosition.occupation, 6));
		fields.add(new FieldPosition("外语语种（水平）", 4, 10, FieldPosition.language_class, 9));
		fields.add(new FieldPosition("承担部组", 4, 11, FieldPosition.changzhu_buzu, 6));
		fields.add(new FieldPosition("家庭地址", 0, 12, FieldPosition.Hukou));
		fields.add(new FieldPosition("性　　别", 21, 4, FieldPosition.sex, 6));
		fields.add(new FieldPosition("专　　业", 21, 7, FieldPosition.zhuan_ye, 6));
		fields.add(new FieldPosition("职　　务", 21, 8, FieldPosition.job, 6));
		fields.add(new FieldPosition("出生年月日", 35, 4, FieldPosition.from_date, 4));
		fields.add(new FieldPosition("婚姻状况", 35, 6, FieldPosition.marriage, 4));
		fields.add(new FieldPosition("学校及单位名称", 16, 15, true, FieldPosition.school));
		regexs.add(fields);

		// 2, (2015-09-12第9版)
		fields = new ArrayList<FieldPosition>();
		fields.add(new FieldPosition("姓　　名", 0, 4, FieldPosition.NMAE));
		fields.add(new FieldPosition("学　　历", 0, 7, FieldPosition.education));
		fields.add(new FieldPosition("职　　业", 0, 8, FieldPosition.occupation));
		fields.add(new FieldPosition("外语语种（水平）", 0, 10, FieldPosition.language_class));
		fields.add(new FieldPosition("承担部组", 0, 11, FieldPosition.changzhu_buzu));
		fields.add(new FieldPosition("手机号码", 0, 12, FieldPosition.phone));
		fields.add(new FieldPosition("家庭地址", 0, 13, FieldPosition.Hukou));
		fields.add(new FieldPosition("性　　别", 2, 4, FieldPosition.sex));
		fields.add(new FieldPosition("专　　业", 2, 7, FieldPosition.zhuan_ye));
		fields.add(new FieldPosition("职　　务", 2, 8, FieldPosition.job));
		fields.add(new FieldPosition("学校及单位名称", 2, 16, true, FieldPosition.school));
		fields.add(new FieldPosition("出生日期", 4, 4, FieldPosition.from_date));
		fields.add(new FieldPosition("婚姻状况", 4, 6, FieldPosition.marriage));
		regexs.add(fields);

		// 3, (2015-08-28第9版)
		fields = new ArrayList<FieldPosition>();
		fields.add(new FieldPosition("姓　　名", 0, 4, FieldPosition.NMAE));
		fields.add(new FieldPosition("学　　历", 0, 7, FieldPosition.education));
		fields.add(new FieldPosition("职　　业", 0, 8, FieldPosition.occupation));
		fields.add(new FieldPosition("外语语种（水平）", 0, 10, FieldPosition.language_class));
		fields.add(new FieldPosition("承担部组", 0, 11, FieldPosition.changzhu_buzu));
		fields.add(new FieldPosition("手机号码", 0, 12, FieldPosition.phone));
		fields.add(new FieldPosition("家庭地址", 0, 13, FieldPosition.Hukou));
		fields.add(new FieldPosition("性　　别", 2, 4, FieldPosition.sex));
		fields.add(new FieldPosition("专　　业", 2, 7, FieldPosition.zhuan_ye));
		fields.add(new FieldPosition("职　　务", 2, 8, FieldPosition.job));
		fields.add(new FieldPosition("学校及单位名称", 2, 16, true, FieldPosition.school));
		fields.add(new FieldPosition("出生日期", 4, 4, FieldPosition.from_date));
		fields.add(new FieldPosition("婚姻状况", 4, 6, FieldPosition.marriage));
		regexs.add(fields);

		// 4, (2015-09-12第9版)多常住日期
		fields = new ArrayList<FieldPosition>();
		fields.add(new FieldPosition("姓　　名", 0, 4, FieldPosition.NMAE));
		fields.add(new FieldPosition("学　　历", 0, 7, FieldPosition.education));
		fields.add(new FieldPosition("职　　业", 0, 8, FieldPosition.occupation));
		fields.add(new FieldPosition("外语语种（水平）", 0, 10, FieldPosition.language_class));
		fields.add(new FieldPosition("承担部组", 0, 11, FieldPosition.changzhu_buzu));
		fields.add(new FieldPosition("手机号码", 0, 13, FieldPosition.phone));
		fields.add(new FieldPosition("户口所在地", 0, 14, FieldPosition.Hukou));
		fields.add(new FieldPosition("性　　别", 2, 4, FieldPosition.sex));
		fields.add(new FieldPosition("专　　业", 2, 7, FieldPosition.zhuan_ye));
		fields.add(new FieldPosition("职　　务", 2, 8, FieldPosition.job));
		fields.add(new FieldPosition("学校及单位名称", 2, 21, true, FieldPosition.school));
		fields.add(new FieldPosition("出生日期", 4, 4, FieldPosition.from_date));
		fields.add(new FieldPosition("婚姻状况", 4, 6, FieldPosition.marriage));
		regexs.add(fields);

		// 5,(2016-07-18 201607版)
		fields = new ArrayList<FieldPosition>();
		fields.add(new FieldPosition("姓　　名", 0, 4, FieldPosition.NMAE));
		fields.add(new FieldPosition("最高学历", 0, 7, FieldPosition.education));
		fields.add(new FieldPosition("职　　业", 0, 9, FieldPosition.occupation));
		fields.add(new FieldPosition("承担部组", 0, 11, FieldPosition.changzhu_buzu));
		fields.add(new FieldPosition("手机号码", 0, 13, FieldPosition.phone));
		fields.add(new FieldPosition("户口所在地", 0, 14, FieldPosition.Hukou));
		fields.add(new FieldPosition("性　　别", 2, 4, FieldPosition.sex));
		fields.add(new FieldPosition("外语1、水平", 2, 8, FieldPosition.language_class));
		fields.add(new FieldPosition("职　　务", 2, 9, FieldPosition.job));
		fields.add(new FieldPosition("出生日期", 4, 4, FieldPosition.from_date));
		fields.add(new FieldPosition("婚姻状况", 4, 6, FieldPosition.marriage));
		fields.add(new FieldPosition("专    业", 4, 7, FieldPosition.zhuan_ye));
		fields.add(new FieldPosition("所在学校", 3, 21, true, FieldPosition.school));
		regexs.add(fields);

		return fields;
	}

}
