package fzzj.excel;

import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;

/**
 * excel表所需字段信息
 * 
 * @author sunny
 *
 */
public class Person {
	// 姓名
	public String name;
	// 手机
	public String phone;
	// 性别
	public String sex;
	// 组别 山上5组 包含山上、山下、京外
	public String group;
	// 类别 讲师、班长、学员（常住前）
	public String role;
	// 出生年月
	public String from_date;
	// 专业
	public String zhuan_ye;
	// 最高学历毕业院校
	public String school;
	// 学历
	public String education;
	// 外语等级
	public String language_class;
	// 职业
	public String occupation;
	// 职务
	public String job;
	// 婚姻
	public String marriage;
	// 户口所在地
	public String Hukou;
	// 常住前承担的部组
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
	 * @return 規則可以全部正確的讀取數據，結束外層（其他規則）循環 true:表示需要讀取下一個規則
	 */
	public boolean setData1(ArrayList<FieldPosition> regex) {
		try {
			// 第一列的值，获取第一行，
			for (FieldPosition fieldPosition : regex) {
				Cell keyCell = sheet.getCell(fieldPosition.key_column, fieldPosition.key_row);
				String strc00 = keyCell.getContents().trim().replace(" ", "");
				if (fieldPosition.key_field.equals(strc00)) {
					// 表示字段名相當,開始記錄真實數據
					setData(fieldPosition);
				} else {
					// 文件名,下一种规则
					return false;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;

	}

	// 上山常住时间

}
