package fzzj.excel;

import jxl.Cell;
import jxl.Sheet;

/**
 * 定义excel表中每个字段的位置 根据field_type判断当前这个字段是
 * {@link Person}中的每个字段都会有一个对应的FieldPosition信息
 * 
 * @author sunny
 *
 */
public class FieldPosition {
	public static final int NMAE = 1001;
	public static final int phone = 1002;
	public static final int sex = 1003;
	public static final int group = 1004;
	public static final int role = 1005;
	public static final int from_date = 1006;
	public static final int zhuan_ye = 1007;
	public static final int school = 1008;
	public static final int education = 1009;
	public static final int language_class = 1010;
	public static final int occupation = 1011;
	public static final int job = 1012;
	public static final int marriage = 1013;
	public static final int Hukou = 1014;
	public static final int changzhu_buzu = 1015;
	int field_type;

	// 姓 名
	String key_field;
	// key列
	int key_column;
	// key行
	int key_row;

	// 赵晗
	String value_field;
	// value列
	int value_column;
	// value行,若是学校，会有多行
	int[] value_row;

	/**
	 * 列子『 姓 名：李四 』 值-李四的计算方式=列（key_column+1） 行（key_row）
	 * 
	 * @param key_field
	 *            ：姓名
	 * @param key_column
	 *            ：姓名在excel表中列位置，从0开始
	 * @param key_row
	 *            ：姓名在excel表中行位置，从0开始
	 * @param type
	 *            对应{@link Person}中的每个字段
	 * 
	 */
	public FieldPosition(String key_field, int key_column, int key_row, int type) {
		init(key_field, key_column, key_row, type);
		int[] temp = { key_row };
		value_row = temp;
		value_column = key_column + 1;
	}

	/**
	 * 列子『 学 校 \n 李四 』 值-李四的计算方式=列（key_column+1） 行（key_row）
	 * 
	 * @param key_field
	 *            ：姓名
	 * @param key_column
	 *            ：姓名在excel表中列位置，从0开始
	 * @param key_row
	 *            ：姓名在excel表中行位置，从0开始
	 * @param type
	 *            对应{@link Person}中的每个字段
	 * 
	 */
	public FieldPosition(String key_field, int key_column, int key_row, boolean to_more, int type) {
		init(key_field, key_column, key_row, type);
		value_row = new int[10];
		for (int i = 0; i < 10; i++) {
			value_row[i] = key_row + i + 1;
		}
		value_column = key_column;
	}

	public FieldPosition(String key_field, int key_column, int key_row, int type, int key_to_value_postion) {
		init(key_field, key_column, key_row, type);
		int[] temp = { key_row };
		value_row = temp;
		value_column = key_column + key_to_value_postion;
	}

	private void init(String key_field, int key_column, int key_row, int type) {
		this.key_field = key_field.trim().replace(" ", "");
		this.key_column = key_column;
		this.key_row = key_row;
		field_type = type;
	}

	public String getValue(Sheet sheet) {
		String str = "";
		if (value_row.length == 1) {
			Cell keyCell = sheet.getCell(value_column, value_row[0]);
			str = str + keyCell.getContents();
		} else {
			for (int i = 0; i < value_row.length; i++) {
				Cell keyCell = sheet.getCell(value_column, value_row[i]);
				str = str + keyCell.getContents() + "@";
			}
		}

		return str;
	}
}
