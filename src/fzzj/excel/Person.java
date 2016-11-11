package fzzj.excel;

import java.io.File;
import java.util.ArrayList;

import fzzj.test.regex.Regex;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * excel�������ֶ���Ϣ
 * 
 * @author sunny
 *
 */
public class Person {
	// ����
	public String name;
	// �ֻ�
	public String phone;
	// �Ա�
	public String sex;
	// ��� ɽ��5�� ����ɽ�ϡ�ɽ�¡�����
	public String group;
	// ��� ��ʦ���೤��ѧԱ����סǰ��
	public String role;
	// ��������
	public String from_date;
	// רҵ
	public String zhuan_ye;
	// ���ѧ����ҵԺУ
	public String school;
	// ѧ��
	public String education;
	// ����ȼ�
	public String language_class;
	// ְҵ
	public String occupation;
	// ְ��
	public String job;
	// ����
	public String marriage;
	// �������ڵ�
	public String Hukou;
	// ��סǰ�е��Ĳ���
	public String changzhu_buzu;

	Sheet sheet;
	public File file;

	public Person(Sheet sheet) {
		this.sheet = sheet;
	}

	public Person(File file) {
		this.file = file;
	}

	@Override
	public String toString() {
		String str = name + "," + phone + "," + sex + "," + group + "," + role + "," + from_date + "," + zhuan_ye + ","
				+ school + "," + education + "," + language_class + "," + occupation + "," + job + "," + marriage + ","
				+ Hukou + "," + changzhu_buzu;

		return str;
	}

	public void setData(FieldPosition fieldPosition) {
		switch (fieldPosition.field_type) {
		case FieldPosition.NMAE:
			name = fieldPosition.getValue(sheet);
			break;
		case FieldPosition.phone:
			phone = fieldPosition.getValue(sheet);
			break;
		case FieldPosition.sex:
			sex = fieldPosition.getValue(sheet);
			break;
		case FieldPosition.group:
			group = fieldPosition.getValue(sheet);
			break;
		case FieldPosition.role:
			role = fieldPosition.getValue(sheet);
			break;
		case FieldPosition.from_date:
			from_date = fieldPosition.getValue(sheet);
			break;
		case FieldPosition.zhuan_ye:
			zhuan_ye = fieldPosition.getValue(sheet);
			break;
		case FieldPosition.school:
			school = fieldPosition.getValue(sheet);
			break;
		case FieldPosition.education:
			education = fieldPosition.getValue(sheet);
			break;
		case FieldPosition.language_class:
			language_class = fieldPosition.getValue(sheet);
			break;
		case FieldPosition.occupation:
			occupation = fieldPosition.getValue(sheet);
			break;
		case FieldPosition.job:
			job = fieldPosition.getValue(sheet);
			break;
		case FieldPosition.marriage:
			marriage = fieldPosition.getValue(sheet);
			break;
		case FieldPosition.Hukou:
			Hukou = fieldPosition.getValue(sheet);
			break;
		case FieldPosition.changzhu_buzu:
			changzhu_buzu = fieldPosition.getValue(sheet);
			break;
		default:
			break;
		}

	}

	/**
	 * 
	 * @param regex
	 * @return Ҏ�t����ȫ�����_���xȡ�������Y����ӣ�����Ҏ�t��ѭ�h true:��ʾ��Ҫ�xȡ��һ��Ҏ�t
	 */
	public boolean setData1(ArrayList<FieldPosition> regex) {
		try {
			// ��һ�е�ֵ����ȡ��һ�У�
			for (FieldPosition fieldPosition : regex) {
				Cell keyCell = sheet.getCell(fieldPosition.key_column, fieldPosition.key_row);
				String strc00 = keyCell.getContents().trim().replace(" ", "");
				if (fieldPosition.key_field.equals(strc00)) {
					// ��ʾ�ֶ����ஔ,�_ʼӛ��挍����
					setData(fieldPosition);
				} else {
					// �ļ���,��һ�ֹ���
					return false;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;

	}

	/**
	 * ������ʱ���������ļ������ݹ���ȫ����������
	 * 
	 * @param regex
	 * @return Ҏ�t����ȫ�����_���xȡ�������Y����ӣ�����Ҏ�t��ѭ�h true�� name ��= null,�����ݣ���ʾ����Ҫ��ȡ��һ������
	 */
	public boolean analysisRegex(Regex regex) {
		ArrayList<FieldPosition> fieldPositions = regex.regexFields;
		try {
			Workbook wb = Workbook.getWorkbook(file);
			// ��һ�е�ֵ����ȡ��һ�У�
			for (FieldPosition fieldPosition : fieldPositions) {
				sheet = wb.getSheet(0); // �ӹ�������ȡ��ҳ��Sheet��
				if (fieldPosition.validateField(sheet)) {
					// ��ʾ�ֶ����ஔ,�_ʼӛ��挍����
					setData(fieldPosition);
				} else {
					// ���ֿ��ܣ����򲻶ԣ�����������
					// �ļ���,��һ�ֹ���
					sheet = wb.getSheet(1);
					if (fieldPosition.validateField(sheet)) {
						// ��ʾ�ֶ����ஔ,�_ʼӛ��挍����
						setData(fieldPosition);
					} else {
						// ��Ҫ��ȡ��һ����
						return false;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// name ��= null,��ʾ����Ҫ��ȡ��һ������
		return name != null;
	}

	public boolean isNoValue() {
		// || group == null || role == null
		return name == null || phone == null || sex == null || from_date == null || zhuan_ye == null || school == null
				|| education == null || language_class == null || occupation == null || job == null || marriage == null
				|| Hukou == null || changzhu_buzu == null;
	}

	// ��ɽ��סʱ��

}
