package word.utils;

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
	
	
}
