package word.w2004.template;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import word.utils.TestUtils;
import word.utils.Utils;
import word.w2004.elements.Paragraph;
import word.w2004.elements.Table;
import word.w2004.elements.tableElements.TableEle;

/**
 * @author leonardo
 */
public class TemplateTest extends Assert {

    //@Ignore //just to not break the build for other devs...
    @Test
    public void testTemplate() {
        String xmlTemplate = Utils.readFile("src/test/resources/ReleaseNotesTemplate.doc");
        
        xmlTemplate = replacePh(xmlTemplate, "phCompanyName", "EasyWorld - coding for fun pty");
        xmlTemplate = replacePh(xmlTemplate, "phEnv", "Production");
        xmlTemplate = replacePh(xmlTemplate, "phVersion", "1.0 beta");
        xmlTemplate = replacePh(xmlTemplate, "phProjectLeader", "Leonardo Correa");
        
        Table tbl = new Table();
        tbl.addTableEle(TableEle.TH, "Jira Number", "Description");

        tbl.addTableEle(TableEle.TD, "J2W-1234", "Read Templates nicelly");
        tbl.addTableEle(TableEle.TD, "J2W-9999", "Make Java2word funky!!!");

        xmlTemplate = replacePh(xmlTemplate, "phTableIssues", tbl.getContent());
        
        Paragraph p01 = Paragraph.with("1) Stop the server").create();
        Paragraph p02 = Paragraph.with("2) Run the script to deploy the app xxx").create();
        Paragraph p03 = Paragraph.with("3) Start the server").create();
        Paragraph p04 = Paragraph.with("4) Hope for the best").create();
        
        String instructions = p01.getContent() + p02.getContent() + p03.getContent() + p04.getContent();
        
        //Workaround: phInstructions is already inside a 'text' fragment. 
        //If you know the template, you can remove the whole element and add all Paragraphs
        //* Table above doesn't need workaround because table can be normally inside a paragraph.
        xmlTemplate = replacePh(xmlTemplate, "<w:t>phInstructions</w:t>", instructions); 
        
        xmlTemplate = replacePh(xmlTemplate, "phDateTime", new Date().toString());
        
        TestUtils.createLocalDoc(xmlTemplate);        
    }
    
    /***
     * Does the Place Holder replacement but LOGS when can not find place holder.
     * @param base Base String that contains the big XML with all placeholders
     * @param placeHolder the actual place holder
     * @param value value to take place
     * @return the new string with place holder replaced
     */
    private String replacePh(String base, String placeHolder, String value) {
        if(!base.contains(placeHolder)) {
            //don't want to use log now because I want to keep it simple...
            System.out.println("### WARN: couldn't find the place holder: " + placeHolder);
            return base;
        }        
        return base.replace(placeHolder, value);
    }

}
