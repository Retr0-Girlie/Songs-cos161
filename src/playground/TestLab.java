package playground;

import songanalyzer.SingerAnalyzer;
public class TestLab {

	public static void main(String[] args) {
		String filePath = "C:\\Users\\Iris\\Downloads\\Taylor Swift";
		//Map<String, String> test = SingerAnalyzer.readFiles(filePath);
		SingerAnalyzer taylor = new SingerAnalyzer(filePath);
		//System.out.println(test.keySet().toString());
		System.out.println(taylor.search("obsessed with me").toString());
		System.out.println(taylor.search("help me").toString());
		System.out.println(taylor.search("life is pain").toString());
	}

}
