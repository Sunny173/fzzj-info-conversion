package fzzj.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ȡ�����ļ�����ͬ�汾��excel���
 * 
 * @author sunny
 *
 */
public class RegexFile {
	static List<ArrayList<FieldPosition>> regexs = new ArrayList<ArrayList<FieldPosition>>();

	public static List<ArrayList<FieldPosition>> regex() {
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

		return regexs;
	}

}
