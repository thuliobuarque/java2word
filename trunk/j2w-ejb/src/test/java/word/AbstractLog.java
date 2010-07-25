package word;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.jboss.seam.log.Log;

public class AbstractLog extends Assert{

	
	private static String getArgs(final Object... arg1){
		String argsStr = "";
		for (int i = 0; i < arg1.length; i++) {
			argsStr += arg1[i] + ", ";
		}
		return argsStr;
	}
	
	@SuppressWarnings("unchecked")
	public static Log getMockedLog(Class clazz){
		
		final Logger log = Logger.getLogger(clazz);
		
		Log mylog = new Log() {
			
			public void warn(Object arg0, Throwable arg1, Object... arg2) {
				// TODO Auto-generated method stub
				
			}
			
			public void warn(Object arg0, Object... arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void trace(Object arg0, Throwable arg1, Object... arg2) {
				// TODO Auto-generated method stub
				
			}
			
			public void trace(Object arg0, Object... arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public boolean isWarnEnabled() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isTraceEnabled() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isInfoEnabled() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isFatalEnabled() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isErrorEnabled() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isDebugEnabled() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public void info(Object arg0, Throwable arg1, Object... arg2) {
				log.info(arg0 + " - args: " + getArgs(arg2), arg1);
			}
			
			public void info(Object arg0, Object... arg1) {
				log.info(arg0 + " - args: " + getArgs(arg1));
			}
			
			public void fatal(Object arg0, Throwable arg1, Object... arg2) {
				// TODO Auto-generated method stub
				
			}
			
			public void fatal(Object arg0, Object... arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void error(Object arg0, Throwable arg1, Object... arg2) {
				log.error(arg0 + " - args: " + getArgs(arg2), arg1);				
			}
			
			public void error(Object arg0, Object... arg1) {
				log.error(arg0 + " - args: " + getArgs(arg1));
			}
			
			public void debug(Object arg0, Throwable arg1, Object... arg2) {
				log.debug(arg0 + " - args: " + getArgs(arg2), arg1);				
			}
			
			public void debug(Object arg0, Object... arg1) {
				log.debug(arg0 + " - args: " + getArgs(arg1));
			}
		}; 
		return mylog;
	}
	
}
