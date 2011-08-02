package word.utils;

import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//import javax.xml.transform.Source;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.stream.StreamResult;
//import javax.xml.transform.stream.StreamSource;

//import org.dom4j.DocumentException;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Node;


public class Utils {

    /**
     * @return
     *
     * The root of the web app as String.
     */
    public static String getAppRoot(){
        File file = new File(".");
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't get app root directory", e);
        }
    }


    /**
     *
     * @param file:
     * It is the full path to the file
     *
     * @return
     * String with the content of the file
     */
    public static String readFile(String file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find the file", e);
        }
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            reader = null;
        }
    }


    /**
     *
     * @param xml
     * xml to be pretifized
     *
     * @return
     * pretifized xml
     */
    /*
    public static String pretty(String xml) {
        try {
            org.dom4j.Document document = DocumentHelper.parseText(xml)
                    .getDocument();

            String res = formatXml(document, false);
            return res;
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't parse xml", e);
        }
    }
*/

    /*
    public static String formatXml(Node node, boolean oneLine) {

        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();

            transformer.setOutputProperty("omit-xml-declaration", "yes");
            transformer.setOutputProperty("method", "xml");
            transformer.setOutputProperty("encoding", "ISO-8859-1");
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty("indent", "yes");
            java.io.StringWriter sw = new java.io.StringWriter();
            StreamResult sr = new StreamResult(sw);
            // Must strip out new lines and whitespace, because formatter thinks
            // this is content that should be preserved in pretty
            String xml = node.asXML().replaceAll(">\\s*(\\r|\\n)\\s*", ">")
                    .replaceAll("\\s*(\\r|\\n)\\s*<", "<");

            // String xml = node.asXML();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(xml
                    .getBytes("UTF-8"));
            Source domSource = new StreamSource(inputStream);
            transformer.transform(domSource, sr);

            String prettyXml = sw.toString();

            // Although this looks like the same thing as above, it actually
            // isn't
//			if (oneLine) {
//				prettyXml = prettyXml.replaceAll(">\\s*(\\r|\\n)\\s*<", "><");
//			}

            return prettyXml;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
*/



}
