package fzzj.excel;

import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;

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

	public Person(Sheet sheet) {
		this.sheet = sheet;
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

	// ��ɽ��סʱ��

}
