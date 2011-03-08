package word.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUtils {

	public static int regexCount(String text, String regex){
		if(text == null || regex == null){
			throw new IllegalArgumentException("Can't be null.");
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		
		int total = 0;
		while (matcher.find()) {
			total ++;
        }
		
		return total;
	}
	
    public static void createLocalDoc(String myDoc) {
        File fileObj = new File("/home/leonardo/Desktop/Java2word_allInOne.doc");

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileObj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String myWord = myDoc;

        writer.println(myWord);
        writer.close();
    }
	
}
