package word;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseStream;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import junit.framework.Assert;

/**
 * @author leonardo_correa
 * ATENTION: All of this could possibly be achieved using JSF Mock Framework.
 * I decide to write my own mock in order to lead more about mocking objects.  
 * 
 * Take a look at the JSF Mock Framework:
 *  http://community.jboss.org/wiki/MockObjectsforTestDrivenJSFDevelopmentorgjbosstest-jsfjsf-mockproject
 *  
 */
public class AbstractFacesContext extends Assert{

	public static FacesContext getMockedFacesContext() throws FileNotFoundException{

		final ExternalContext	ec = AbstractFacesContext.getMockedExternalContext();
		
		FacesContext fc = new FacesContext() {

			@Override //### LEO
			public ExternalContext getExternalContext() {
				return  ec;
			}
			@Override
			public void responseComplete() {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void setViewRoot(UIViewRoot arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setResponseWriter(ResponseWriter arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setResponseStream(ResponseStream arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void renderResponse() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void release() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public UIViewRoot getViewRoot() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ResponseWriter getResponseWriter() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ResponseStream getResponseStream() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean getResponseComplete() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean getRenderResponse() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public RenderKit getRenderKit() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Iterator<FacesMessage> getMessages(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Iterator<FacesMessage> getMessages() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Severity getMaximumSeverity() {
				// TODO Auto-generated method stub
				return null;
			}
			
			
			@Override
			public Iterator<String> getClientIdsWithMessages() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Application getApplication() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void addMessage(String arg0, FacesMessage arg1) {
				// TODO Auto-generated method stub
				
			}
		};
		return fc;
	}
	
	
	private static HttpServletResponse getMockedHttpServletResponse() throws FileNotFoundException{
		File target = new File("target");
		if(!target.exists()){
			if (!target.mkdir()){
				System.out.println("ERROR: Can't create the dir 'target'");
			}
		}
		final PrintWriter pw  = new PrintWriter(new File("target/xxx.txt")); //inner classes can only access final stuffs for the outer.
		
		HttpServletResponse resp = new HttpServletResponse() {

			private String contentType;

			public PrintWriter getWriter() throws IOException {
				return pw;
			}			
			
			public void addCookie(Cookie arg0) {
				// TODO Auto-generated method stub
				
			}

			public void addDateHeader(String arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}

			public void addHeader(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			public void addIntHeader(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}

			public boolean containsHeader(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			public String encodeRedirectURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public String encodeRedirectUrl(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public String encodeURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public String encodeUrl(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public void sendError(int arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}

			public void sendError(int arg0, String arg1)
					throws IOException {
				// TODO Auto-generated method stub
				
			}

			public void sendRedirect(String arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}

			public void setDateHeader(String arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}

			public void setHeader(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			public void setIntHeader(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}

			public void setStatus(int arg0) {
				// TODO Auto-generated method stub
				
			}

			public void setStatus(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			public void flushBuffer() throws IOException {
				// TODO Auto-generated method stub
				
			}

			public int getBufferSize() {
				// TODO Auto-generated method stub
				return 0;
			}

			public String getCharacterEncoding() {
				// TODO Auto-generated method stub
				return null;
			}

			public String getContentType() {
				return this.contentType;
			}

			public Locale getLocale() {
				// TODO Auto-generated method stub
				return null;
			}

			public ServletOutputStream getOutputStream()
					throws IOException {
				// TODO Auto-generated method stub
				return null;
			}



			public boolean isCommitted() {
				// TODO Auto-generated method stub
				return false;
			}

			public void reset() {
				// TODO Auto-generated method stub
				
			}

			public void resetBuffer() {
				// TODO Auto-generated method stub
				
			}

			public void setBufferSize(int arg0) {
				// TODO Auto-generated method stub
				
			}

			public void setCharacterEncoding(String arg0) {
				// TODO Auto-generated method stub
				
			}

			public void setContentLength(int arg0) {
				// TODO Auto-generated method stub
				
			}

			public void setContentType(String contentType) {
				this.contentType = contentType;
			}

			public void setLocale(Locale arg0) {
				// TODO Auto-generated method stub
			}

		};	
		
		return resp;
	}
	
	private static ExternalContext getMockedExternalContext() throws FileNotFoundException{
		
		final HttpServletResponse resp = AbstractFacesContext.getMockedHttpServletResponse();
		
		ExternalContext ec = new ExternalContext(){
			
			@Override //### Leo
			public Object getResponse() {
				return resp;				
				
			}
			
			@Override 
			public Object getRequest() {
				return null;
			}
			
			@Override
			public void dispatch(String arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public String encodeActionURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String encodeNamespace(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String encodeResourceURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Map<String, Object> getApplicationMap() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAuthType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object getContext() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getInitParameter(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Map getInitParameterMap() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getRemoteUser() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getRequestContextPath() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Map<String, Object> getRequestCookieMap() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Map<String, String> getRequestHeaderMap() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Map<String, String[]> getRequestHeaderValuesMap() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Locale getRequestLocale() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Iterator<Locale> getRequestLocales() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Map<String, Object> getRequestMap() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Map<String, String> getRequestParameterMap() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Iterator<String> getRequestParameterNames() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Map<String, String[]> getRequestParameterValuesMap() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getRequestPathInfo() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getRequestServletPath() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public URL getResource(String arg0) throws MalformedURLException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public InputStream getResourceAsStream(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Set<String> getResourcePaths(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object getSession(boolean arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Map<String, Object> getSessionMap() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Principal getUserPrincipal() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isUserInRole(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void log(String arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void log(String arg0, Throwable arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void redirect(String arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
		};
		return ec;
	}
	
	
}
