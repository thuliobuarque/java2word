<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>

<html:html>
<head>
	<title>Java2Word Generator - Struts Application Example</title>
</head>
<body>

<html:link href="/ExampleStruts">Home</html:link>

    <html:form action="/salaryReport.do">
    	<h2>Java2Word Generator - Struts Application Example</h2>
    	<hr></hr>
    	<h2>Employee Salary Report</h2>
    	<h4>Report Filter:</h4>
        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td>Name:</td>
                <td><html:text property="name" /></td>
            </tr>
			<tr>
                <td>Salary Greater Than:</td>
                <td><html:text property="greaterThan" /> </td>
            </tr>            
			<tr>
                <td>Salary Less Than:</td>
                <td><html:text property="lessThan" /></td>
            </tr>            
            <tr>
                <td colspan="2">
                    <html:submit value="   Get Word Report!   " />
                </td>
            </tr>
        </table>
    </html:form>
    
</body>
</html:html>
