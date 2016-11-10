package fzzj;

public class Test {
	public static void main(String[] args) {
		String str = "ÐÕ      Ãû";
		str = "ÐÕ¡¡¡¡Ãû";
		String[] strArray = str.split(" ");
		for (String string : strArray) {
			System.err.println(string);
		}
		str = str.replaceAll("¡¡", "");
		System.out.println(str);
	}
}
