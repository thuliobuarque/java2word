package word.w2000;

import junit.framework.Assert;

import org.junit.Test;

import word.w2000.elements.Heading1;
import word.w2000.elements.Heading2;
import word.w2000.elements.Heading3;
import word.w2000.elements.Heading4;
import word.w2000.elements.Heading5;
import word.w2000.elements.Heading6;

public class HeadingTest extends Assert{

	@Test
	public void createHeadingTest(){
		Heading1 h1 = new Heading1("h1h1");
		assertEquals("<h1>h1h1</h1>", h1.getContent());

		Heading2 h2 = new Heading2("h2h2");
		assertEquals("<h2>h2h2</h2>", h2.getContent());
		
		Heading3 h3 = new Heading3("h3h3");
		assertEquals("<h3>h3h3</h3>", h3.getContent());
		
		Heading4 h4 = new Heading4("h4h4");
		assertEquals("<h4>h4h4</h4>", h4.getContent());
		
		Heading5 h5 = new Heading5("h5h5");
		assertEquals("<h5>h5h5</h5>", h5.getContent());
		
		Heading6 h6 = new Heading6("h6h6");
		assertEquals("<h6>h6h6</h6>", h6.getContent());
		
	}

	@Test
	public void createHeadingEmptyTest(){
		Heading1 h1 = new Heading1("");
		assertEquals("<h1></h1>", h1.getContent());
		
		Heading2 h2 = new Heading2("");
		assertEquals("<h2></h2>", h2.getContent());
		
		Heading3 h3 = new Heading3("");
		assertEquals("<h3></h3>", h3.getContent());
		
		Heading4 h4 = new Heading4("");
		assertEquals("<h4></h4>", h4.getContent());
		
		Heading5 h5 = new Heading5("");
		assertEquals("<h5></h5>", h5.getContent());
		
		Heading6 h6 = new Heading6("");
		assertEquals("<h6></h6>", h6.getContent());
		
	}	
}
