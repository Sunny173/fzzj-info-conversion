package fzzj.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取配置文件，不同版本的excel表格
 * 
 * @author sunny
 *
 */
public class RegexFile {
	static List<ArrayList<FieldPosition>> regexs = new ArrayList<ArrayList<FieldPosition>>();

	public static List<ArrayList<FieldPosition>> regex() {
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

		return regexs;
	}

}
