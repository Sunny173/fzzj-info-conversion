package fzzj;

import java.util.List;

import com.google.gson.Gson;

import fzzj.test.regex.NewRegexFile;
import fzzj.test.regex.Regex;
import fzzj.test.regex.RegexString;

public class Test {
	public static void main(String[] args) {
		Gson gson = new Gson();
//		RegexString regexs = NewRegexFile.regex();
//		String str = gson.toJson(regexs);
//		System.out.println(str);

		Regex ee = new Regex();
		RegexString regex = gson.fromJson(ee.getStr(), RegexString.class);
		String str1 = gson.toJson(regex);
		System.err.println(str1);

	}
}
