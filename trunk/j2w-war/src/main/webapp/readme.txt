Useful Links:

### About formats
http://en.wikipedia.org/wiki/Microsoft_Office_XML_formats

## 2003
http://msdn.microsoft.com/en-us/library/aa159917%28office.11%29.aspx //dev tequniques
http://msdn.microsoft.com/en-us/magazine/cc164064.aspx //doc example


### 2007 package format
http://msdn.microsoft.com/en-us/library/bb264572%28office.12%29.aspx
http://msdn.microsoft.com/en-us/library/bb266220%28v=office.12%29.aspx


### Application Guidelines ###
http://code.google.com/p/java2word

- API separated from implementation. So they could be shipped in different jar files from the implementation (like JSF, JDBC (Bridge Pattern)).   
- There is one implementation for word version <= 2004 (97-04), referred here as "Word2000", HTML based.
- There is another one for Word version > 2004, which is XML based. This will be referred as "Word2004"

XML is the new and current standard for word, open XML documents and so on. So we should spend more time in this one. 
However there are a lot of users using <= 2004 and we should at least consider this and write a very basic version of this one as well.

- Unit Test for everything, preferable TDD 
- No cucumber test now 
- Cobertura at least 80% (Test coverage metric)
- PMD, CPD, NCSS and Findbugs

- Small and testable methods (Low cyclomatic complexity) 


### How to test??? 

1 - write your code and test class 
2 - In your test class, print out the Document.getDocumentSource
3 - Copy this result to your clip board
4 - Access the test web page "http://localhost/app02"
5 - paste this result in the text area field and click in "Word"
6 - Save the word document file, open and see if it is what you expected.
   
   

### Table of Supported tags: 

Tag		           | 2000 | 2004 |				
---------------------------------
h1.. h6            |   y  |  y   |  
PageBreak          |   y  |  y   |
BreakLine          |   y  |  y   |
header and footer  |      |  y   | *
Image              |      |  y   |
paragraph          |   y  |  y   |
Table              |   y  |  y   |
                   |      |      |
                   |      |      |
----------------------------------
* 2004 has only h1 to h3

   
### Code Quality Tools
   
JavaNCSS � Cyclomatic Complexity (CC) of methods. Basically it shows  the number of paths. 
PMD � It shows reports about bad or questionable practices. Rulesets applied so far: braces.xml, basic.xml, unusedcode.xml and design.xml (List of all rulesets: http://pmd.sourceforge.net/rules/index.html )
CPD � Duplicated Code - Copy and paste code.
FindBugs � Typical code problems or possible problems.

If you are interested in Cyclomatic Complexity I have attached the original article written by Thomas McCabe in 1976. For something updated: http://en.wikipedia.org/wiki/Cyclomatic_complexity

Hot to see the report all of this? Run "mvn cobertura:cobertura" and take a look at the target folder.
cobertura:cobertura 
javancss:report 
pmd:cpd 
pmd:pmd 
findbugs:findbugs 




### to-do list

- Comments on the API
- Comments on the classes
- Create Web Test Application for JBoss Seam
- Create Web Test Application for Java Servlet

- Create Abstract Factory to instantiate the correct Implementation
- Log debug


   