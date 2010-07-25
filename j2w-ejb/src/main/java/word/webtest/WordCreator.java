package word.webtest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Name;

@Name("wordCreator")
public class WordCreator {

	static Logger log = Logger.getLogger(WordCreator.class);
	private String xml;
	private FacesContext fc = FacesContext.getCurrentInstance();

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public void write(String txt, byte[] bytes, String mimeType, String fileName) {
		// FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletResponse servletResponse = (HttpServletResponse) fc
				.getExternalContext().getResponse();

		servletResponse.setContentType(mimeType);
		/*
		 * if (mimeType.equals("application/excel")) { if (fileName == null) {
		 * fileName = "report.xls"; }
		 * servletResponse.setHeader("Content-Disposition",
		 * "attachment; filename=" + fileName); } else if (fileName != null) {
		 * servletResponse.setHeader("Content-disposition", "inline; filename="
		 * + fileName); }
		 */

//		if (fileName != null) {
			servletResponse.setHeader("Content-disposition",
					"inline; filename=" + fileName);
//		}

		// ServletOutputStream os;
		try {
			// os = servletResponse.getOutputStream();

			PrintWriter writer = servletResponse.getWriter();
			// writer.println("This is an word documet by Leo");
			// writer.println("------------------------------");
			writer.println(txt);

			// os.write("");

			// os.write(bytes);
			fc.responseComplete();
		} catch (IOException e) {
			log.error("@@@@@@@@@ Problem executing report.");
		}
	}

	public String doIt(String txt) {

		byte[] data = null;

		log.info("### about to generate your Word Document");

		log.debug("#########################################");
		log.debug(txt);

		log.debug("### porra - XML from the textArea: " + this.getXml());
		log.debug("#########################################");

		this.write(this.getXml(), data, "application/msword", "leoWord" + "."
				+ "doc");

		return "ok";
	}

	// just to be able to set this from outside - testable.
	public void setFacesContext(FacesContext facesContext) {
		this.fc = facesContext;
	}
}
