package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.api.interfaces.IElement;
import word.api.interfaces.IDocument;
import word.utils.TestUtils;
import word.utils.Utils;
import word.w2004.elements.HyperLink;

public class HyperLinkTest extends Assert {

    @Test
    public void testCreateHyperlink() {
        HyperLink link = HyperLink.with("http://ffffound.com", "FFFFound!");
        assertEquals(1, TestUtils.regexCount(link.getContent(), "<w:t>FFFFound!</w:t>"));
        assertEquals(1, TestUtils.regexCount(link.getContent(), "<w:hlink w:dest=\"http://ffffound.com\">"));
    }

    @Test
    public void testCreateHyperlinkDoc() {
        IDocument myDoc = new Document2004();
        myDoc.addEle(HyperLink.with("http://ffffound.com", "FFFFound!").create());
        TestUtils.createLocalDoc(myDoc.getContent());
    }

}
