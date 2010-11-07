package java2word.actions;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.opensymphony.xwork2.ActionSupport;

public class Testing extends ActionSupport implements ServletResponseAware {

	public String execute() throws Exception {
//		System.out.println("### action init...");

//		System.out.println("XML is: \n" + this.xml + "\n");

		if (!StringUtils.isEmpty(xml)) {
			servletResponse.setContentType("application/msword");
			servletResponse.setHeader("Content-disposition",
					"inline;filename=wordDoc.doc");

			PrintWriter writer = servletResponse.getWriter();
			writer.println(xml);
		}else{
			System.out.println("Error: Empty XML field!");
		}

//		System.out.println("### action END...");
		return SUCCESS;
	}

	// ### Getters and setters
	private String xml;
	private HttpServletResponse servletResponse;

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	@Override
	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

}
