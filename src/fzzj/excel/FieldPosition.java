package fzzj.excel;

import jxl.Cell;
import jxl.Sheet;

/**
 * ����excel����ÿ���ֶε�λ�� ����field_type�жϵ�ǰ����ֶ��� {@link Person}
 * �е�ÿ���ֶζ�����һ����Ӧ��FieldPosition��Ϣ
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

	// �� ��
	String key_field;
	// key��
	int key_column;
	// key��
	int key_row;

	// ����
	String value_field;
	// value��
	int value_column;
	// value��,����ѧУ�����ж���
	int[] value_row;

	/**
	 * ���ӡ� �� �������� �� ֵ-���ĵļ��㷽ʽ=�У�key_column+1�� �У�key_row��
	 * 
	 * @param key_field
	 *            ������
	 * @param key_column
	 *            ��������excel������λ�ã���0��ʼ
	 * @param key_row
	 *            ��������excel������λ�ã���0��ʼ
	 * @param type
	 *            ��Ӧ{@link Person}�е�ÿ���ֶ�
	 * 
	 */
	public FieldPosition(String key_field, int key_column, int key_row, int type) {
		init(key_field, key_column, key_row, type);
		int[] temp = { key_row };
		value_row = temp;
		value_column = key_column + 1;
	}

	/**
	 * ���ӡ�ѧУ����λ���� ����ʡ������ũ���ؽ�ɽСѧ ����ʡ������ũ���ػƽ���ƽ����ѧУ �����иɵ��г�ӪҵԱ �Ϻ���һ����Ʒ��ӪҵԱ ��
	 * ֵ-����ѧУ�ļ��㷽ʽ�ļ��㷽ʽ=�У�key_column�� �У�key_row+i��Ĭ�����¶�10���У�
	 * 
	 * @param key_field
	 *            ������
	 * @param key_column
	 *            ��������excel������λ�ã���0��ʼ
	 * @param key_row
	 *            ��������excel������λ�ã���0��ʼ
	 * @param type
	 *            ��Ӧ{@link Person}�е�ÿ���ֶ�
	 * 
	 */
	public FieldPosition(String key_field, int key_column, int key_row,
			boolean to_more, int type) {
		init(key_field, key_column, key_row, type);
		value_row = new int[6];
		for (int i = 0; i < 6; i++) {
			value_row[i] = key_row + i + 1;
		}
		value_column = key_column;
	}

	/**
	 * ����֮�����ϴ�
	 * 
	 * @param key_field
	 *            ������
	 * @param key_column
	 *            ��������excel������λ�ã���0��ʼ
	 * @param key_row
	 *            ��������excel������λ�ã���0��ʼ
	 * @param type
	 *            ��Ӧ{@link Person}�е�ÿ���ֶ�
	 */
	public FieldPosition(String key_field, int key_column, int key_row,
			int type, int key_to_value_postion) {
		init(key_field, key_column, key_row, type);
		int[] temp = { key_row };
		value_row = temp;
		value_column = key_column + key_to_value_postion;
	}

	private void init(String key_field, int key_column, int key_row, int type) {
		this.key_field = key_field.trim().replace(" ", "").replaceAll("��", "");
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

	/**
	 * ��ʾ�ֶ����ஔ,�_ʼӛ��挍����
	 * 
	 * @param sheet
	 * @return
	 */
	public boolean validateField(Sheet sheet) {
		Cell keyCell = sheet.getCell(key_column, key_row);
		String strc00 = keyCell.getContents().trim().replace(" ", "")
				.replaceAll("��", "");
		return key_field.equals(strc00);
	}
}
