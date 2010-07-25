package word.w2004.elements;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import word.api.interfaces.IImage;
import word.utils.ImageUtils;

public class Image implements IImage {

	static Logger log = Logger.getLogger(Image.class);
	private StringBuilder txt = new StringBuilder("");
	private boolean hasBeenCalledBefore = false;
	private String fullPath = "";
	private String width = "";
	private String height = "";

	/**
	 * @param fullPath
	 *            Full path absolute including file name and extension.
	 */
	public Image(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getOriginalWidthHeight(){
		//URL url = new URL("http://localhost:8080/app02/img/dtpick.gif");
		BufferedImage img;
		try {
			img = ImageIO.read(new File(fullPath));
		} catch (IOException e) {
			e.printStackTrace();
			//throw new IOException(e); 
			return "error";
		}
		String res = img.getWidth() + "#" + img.getHeight() + "";
		log.debug(res);
		return res;
	}
	
	private void setUpSize(){
		if("".equals(this.width) || "".equals(this.height)){
			String [] wh = getOriginalWidthHeight().split("#");
			String ww = wh[0];
			String hh = wh[1];
			if("".equals(this.width)){
				this.width = ww; 
			}
			if("".equals(this.height)){
				this.height = hh; 
			}
		}
		
	}
	
	public String getContent() {
		if(hasBeenCalledBefore ){
			return txt.toString();	
		}else{
			hasBeenCalledBefore = true;
		}
		// Placeholders: internalFileName, fileName, binary, width and height

		log.debug("@@@ fullPath: " + fullPath);

		String[] arr = fullPath.split("/");
		String fileName = arr[arr.length - 1];

		log.debug("@@@ fileName: " + fileName);

		String internalFileName = System.currentTimeMillis() + fileName;
		log.debug(internalFileName);

		String binary = ImageUtils.getImageHexaBase64(fullPath);
		log.debug("@@@ Bynary: " + binary);

		setUpSize();

		String res = img;
		res = res.replace("{fileName}", fileName);
		res = res.replace("{internalFileName}", internalFileName);
		res = res.replace("{binary}", binary);
		res = res.replace("{width}", this.width);
		res = res.replace("{height}", this.height);
		
		txt.append(res);
		return txt.toString();
	}
	
	public void setWidth(String value){
		this.width = value;
	}
	public void setHeight(String value){
		this.height = value;
	}

	private String img = "\n<w:pict>"
			+ "\n	<v:shapetype id=\"_x0000_t75\" coordsize=\"21600,21600\" o:spt=\"75\" o:preferrelative=\"t\" path=\"m@4@5l@4@11@9@11@9@5xe\" filled=\"f\" stroked=\"f\">"
			+ "		<v:stroke joinstyle=\"miter\"/>"
			+ "		<v:formulas>"
			+ "			<v:f eqn=\"if lineDrawn pixelLineWidth 0\"/>"
			+ "			<v:f eqn=\"sum @0 1 0\"/><v:f eqn=\"sum 0 0 @1\"/>"
			+ "			<v:f eqn=\"prod @2 1 2\"/>"
			+ "			<v:f eqn=\"prod @3 21600 pixelWidth\"/>"
			+ "			<v:f eqn=\"prod @3 21600 pixelHeight\"/>"
			+ "			<v:f eqn=\"sum @0 0 1\"/>"
			+ "			<v:f eqn=\"prod @6 1 2\"/>"
			+ "			<v:f eqn=\"prod @7 21600 pixelWidth\"/>"
			+ "			<v:f eqn=\"sum @8 21600 0\"/>"
			+ "			<v:f eqn=\"prod @7 21600 pixelHeight\"/>"
			+ "			<v:f eqn=\"sum @10 21600 0\"/>"
			+ "		</v:formulas>"
			+ "		<v:path o:extrusionok=\"f\" gradientshapeok=\"t\" o:connecttype=\"rect\"/>"
			+ "		<o:lock v:ext=\"edit\" aspectratio=\"t\"/>"
			+ "	</v:shapetype>"
			+ "\n<w:binData w:name=\"wordml://{internalFileName}\" xml:space=\"preserve\">{binary}</w:binData>"
			+ "\n	<v:shape id=\"_x0000_i1026\" type=\"#_x0000_t75\" style=\"width:{width}pt;height:{height}pt\"><v:imagedata src=\"wordml://{internalFileName}\" o:title=\"{fileName}\"/>"
			+ "\n	</v:shape>" + "\n</w:pict>";

/*	
	private String imgTst = "\n<w:pict>"
			+ "\n	<v:shapetype id=\"_x0000_t75\" coordsize=\"21600,21600\" o:spt=\"75\" o:preferrelative=\"t\" path=\"m@4@5l@4@11@9@11@9@5xe\" filled=\"f\" stroked=\"f\"><v:stroke joinstyle=\"miter\"/><v:formulas><v:f eqn=\"if lineDrawn pixelLineWidth 0\"/><v:f eqn=\"sum @0 1 0\"/><v:f eqn=\"sum 0 0 @1\"/><v:f eqn=\"prod @2 1 2\"/><v:f eqn=\"prod @3 21600 pixelWidth\"/><v:f eqn=\"prod @3 21600 pixelHeight\"/><v:f eqn=\"sum @0 0 1\"/><v:f eqn=\"prod @6 1 2\"/><v:f eqn=\"prod @7 21600 pixelWidth\"/><v:f eqn=\"sum @8 21600 0\"/><v:f eqn=\"prod @7 21600 pixelHeight\"/><v:f eqn=\"sum @10 21600 0\"/></v:formulas><v:path o:extrusionok=\"f\" gradientshapeok=\"t\" o:connecttype=\"rect\"/><o:lock v:ext=\"edit\" aspectratio=\"t\"/></v:shapetype>"
			+ "\n<w:binData w:name=\"wordml://01000002.gif\" xml:space=\"preserve\">R0lGODlhHwAaAMQAAMjIyOHh4cLCwufn56ioqKOjo+zs7L29va6urs3NzdLS0ri4uNfX19zc3LOz"
			+ "s/Hx8Z6engAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAAA"
			+ "AAAALAAAAAAfABoAAAWv4COOZDCQ6Gg0aRs40NGSQAGd82MIUH/PDYJPNgsgfL1FC4DsBVy2JoRF"
			+ "OkghjtQgKhWQeNcCbgSTIqiixhUCMKDU3VQZSXimrM2EVkpwt7g9WSkJUmgoAVIKLQtNBDkMUn4o"
			+ "R0heM0xNDpqalnybmnYPmGtYIqQ+aKNrgqc9qa2sra+nsaezpLWkt6umsiOqV7lru8G9tr+wxrrI"
			+ "tMrDIwqf0pwi09OhOdna29wpIQA7"
			+ "</w:binData>"
			+ "\n	<v:shape id=\"_x0000_i1026\" type=\"#_x0000_t75\" style=\"width:31pt;height:26pt\"><v:imagedata src=\"wordml://01000002.gif\" o:title=\"quote.gif\"/>"
			+ "\n	</v:shape>" + "\n</w:pict>";

	private String img02 = "\n<w:pict> "
			+ "\n	<v:shapetype id=\"_x0000_t75\" coordsize=\"21600,21600\" o:spt=\"75\" o:preferrelative=\"t\" path=\"m@4@5l@4@11@9@11@9@5xe\" filled=\"f\" stroked=\"f\"> "
			+ "\n		<v:stroke joinstyle=\"miter\"/><v:formulas><v:f eqn=\"if lineDrawn pixelLineWidth 0\"/> "
			+ "\n		<v:f eqn=\"sum @0 1 0\"/> "
			+ "\n		<v:f eqn=\"sum 0 0 @1\"/> "
			+ "\n		<v:f eqn=\"prod @2 1 2\"/> "
			+ "\n		<v:f eqn=\"prod @3 21600 pixelWidth\"/> "
			+ "\n		<v:f eqn=\"prod @3 21600 pixelHeight\"/> "
			+ "\n		<v:f eqn=\"sum @0 0 1\"/> "
			+ "\n		<v:f eqn=\"prod @6 1 2\"/> "
			+ "\n		<v:f eqn=\"prod @7 21600 pixelWidth\"/> "
			+ "\n		<v:f eqn=\"sum @8 21600 0\"/> "
			+ "\n		<v:f eqn=\"prod @7 21600 pixelHeight\"/> "
			+ "\n		<v:f eqn=\"sum @10 21600 0\"/> "
			+ "\n	</v:formulas> "
			+ "\n		<v:path o:extrusionok=\"f\" gradientshapeok=\"t\" o:connecttype=\"rect\"/> "
			+ "\n		<o:lock v:ext=\"edit\" aspectratio=\"t\"/> "
			+ "\n	</v:shapetype> "
			+ "\n<w:binData w:name=\"wordml:\01000002.gif\" xml:space=\"preserve\">"
			+ "R0lGODlhHwAaAMQAAMjIyOHh4cLCwufn56ioqKOjo+zs7L29va6urs3NzdLS0ri4uNfX19zc3LOz"
			+ "s/Hx8Z6engAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAAA"
			+ "AAAALAAAAAAfABoAAAWv4COOZDCQ6Gg0aRs40NGSQAGd82MIUH/PDYJPNgsgfL1FC4DsBVy2JoRF"
			+ "OkghjtQgKhWQeNcCbgSTIqiixhUCMKDU3VQZSXimrM2EVkpwt7g9WSkJUmgoAVIKLQtNBDkMUn4o"
			+ "R0heM0xNDpqalnybmnYPmGtYIqQ+aKNrgqc9qa2sra+nsaezpLWkt6umsiOqV7lru8G9tr+wxrrI"
			+ "tMrDIwqf0pwi09OhOdna29wpIQA7"
			+ "</w:binData>"
			+ "\n	<v:shape id=\"_x0000_i1026\" type=\"#_x0000_t75\" style=\"width:31pt;height:26pt\"><v:imagedata src=\"wordml:\01000002.gif\" o:title=\"quote.gif\"/>"
			+ "\n	</v:shape>" + "\n</w:pict>";

*/
}
