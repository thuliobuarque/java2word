Please refer to the new location: https://github.com/leonardoanalista/java2word

cheers

Leonardo

<a href='Hidden comment: 
This document gives you the guidelines how to implement a new element/component.
=How to Implement a New Element/Component=

<br><br>
<font size="4">Table of Contents

Unknown end tag for &lt;/font&gt;


----
<wiki:toc max_depth="4" />
<br><br>

=Introduction=

This document gives you the guidelines how to implement a new element/component for the Java2Word project.

_Consider this only a simple demonstration how to implement something new in this framework._


=The new element=

Imagine you want to use one component and this component does not exist yet. A good example could be the "Lists", which hasn"t been done yet (27-Mar-2011). But for this exercise we will use "Paragraph".

Lets define steps how to implement this "Paragraph"(or any other) component.

If you are not a  "committer" in this project or submit the code by attaching the changed classes to the issue


=Set up your Development Environment=

* Check out those projects *java2word* and optionally *j2w-webtest*
* Copy the file build.properties.EXAMPLE to build.properties.
* In the java2word directory, run: *"mvn clean install"*

This is it! Simple like that!

_Remember: If you are under a proxy, locate your settings.xml and edit the proxy details. Regardless of proxy configuration, after importing the project to eclipse, edit the eclipse environment variable M2_REPO to ~/.m2/repository_

If you use eclipse, run "mvn eclipse:eclipse" in order to import the project to it.

Optionally, after a successful build, you can take a look at your .m2 folder and you should be able to see the generated jar in : ~/.m2/repository/java2word/java2word/3.0/java2word-3.0.jar


=Implementation of the new element=

Now you will see that there is no mystery behind *Java2word* library.

* Create your class called *Paragraph.java* implementing the interface  *IElement.java*.
* Also create a test class called *ParagraphTest.java*
* Preferable, first write your test in the class
* Write the code of the method is *getContent()*. This method has to return the contend ready to be added to the document.


I assume you don"t know what to write in your *getContent()* right? Now it is time to do the *reverse engineering* and figure out how to implement the code to generate a *Paragraph* block.

* Open your Microsoft Word 2004 (or greater then)
* Type some text, select part of the text.
* Save it as "Word 2003 XML Document"
* Open using any text editor or XML indent. I use those websites: [http://xmlindent.com/ http://xmlindent.com/] and [http://www.shell-tools.net/index.php?op=xml_format http://www.shell-tools.net/index.php?op=xml_format]

* Don"t worry about the huge generated XML. The important right now bit is between *<w:body>* and *

Unknown end tag for &lt;/body&gt;

*


At this point you can see something like:

```
... more stuff, don"t worry now...

<w:body>
	<w:p wsp:rsidR="00D711DA" wsp:rsidRDefault="00FE5527">
		<w:r>
			<w:t>Leonardo </w:t>
		</w:r>
	</w:p>
<w:body>

... more stuff don"t worry now...
```

Now we know how to implement the *Paragraph* component!!!

* Following the development guidelines, you will use implement the interface *IFluentElement<Paragraph>*. This interface will force you to implement a method called "create()".

* In order to start the *fluent flow*, you will have to create another static method called *with(String)*. It doesn"t have to be "with" but something that starts the flow.

```
public static Paragraph with(String value) {
    this.value = value //actual paragraph content
    return new Paragraph();
}
```

Notice that the method is very simple and only returns a new reference to the Paragraph object. *You should also make the constructor private in order to force the use of the fluent interface*.

* After, I could have a variable with the *place holder*.
```
String PARAGRAPH_TEMPLATE = 
	"<w:r wsp:rsidRPr=\"00FE5527\">"
	+ "\n		<w:r>"
	+"\n			{styleText}"
	+"\n			<w:t>{value}</w:t>"
	+"\n		</w:r>";
	"</w:r>";
```

For convention, we use a place holder for the actual paragraph content ({value}).

__We will talk about the other place holder ({styleText}) later.__

* Time to replace the place holder and return the *getContent()*;
```

  public String getContent() {
     return PARAGRAPH_TEMPLATE.replace("{value}", this.value);   
  }

```

* And I would utilize like that:

```
   IDocument myDoc = new Document2004();
   myDoc.addEle(Paragraph.with("Leonardo").create()) );
```

Time to create your *Unit Tests* (or TDD, up to you).

* Create a class *ParagraphTest.java* and extend Assert. This is one example of unit test:

```
    @Test
    public void sanityTestParagraph() {
        IElement par = Paragraph.with("par01").withStyle().align(Align.CENTER).create();

       assertEquals(1, TestUtils.regexCount(par.getContent(), <w:t>par01</w:t>"));
       assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:p wsp:rsidR="));
       assertEquals(2, TestUtils.regexCount(par.getContent(), "<*w:r>"));
       assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:p>"));
       assertEquals(2, TestUtils.regexCount(par.getContent(), "<*w:pPr>"));
    }
```


Not finished yet, you need to know if your new element really works in a real Microsoft Word document. Create another dummy and temporary test in Document2004Test.java. Eg.:

```
    @Test
    public void testParagraph() {
        IDocument myDoc = new Document2004();
        myDoc.addEle(Paragraph.with("Leonardo").create()) );
        
    }
```

You have two options now

* Option 1) j2w-webtest
* Print out the content of the document: System.out.println(myDoc.getContent());
* Copy this value to clip board
* Deploy j2w-webtest in your tomcat
* access the url localhost:8080/j2w-webtest
* paste the content and click in "get Word Document"
* Open the file and see if makes sense.


* Option 2) Generate Document (don"t need j2w-webtest at all)
* in your unit test, add the method: *TestUtils.createLocalDoc(myDoc.getContent());*
* Copy the file *build.properties.EXAMPLE* to *build.properties*. Add it to SVN ignore list
* change the property "tmp.docs.dir" to any directory in your machine (Desktop or whatever)
* This should create the document in your Desktop
* Open the file and see if makes sense.

I use option 2 most times but option 1 is quite useful when you are creating a new element.


=Important thing in Java2word=

Normally at work we don"t have time to write the best code. We have to delivery regardless of the code quality. Other thing at works is we always have to use old or obsolete stack of technology.

*Java2word is where I can use whatever I fucking want, however I fucking want!*

There is no pressure or dead line to meet. We can try to use the latest and the best of everything.



=Code Metrics=
Take a look at the ANT file or run "ant -p" to see the list of tasks. I add the build.xml to my eclipse and run it in one click.

* *Cobertura*: Coverage test should be almost 100%
* *Cyclomatic Complexity*: You can see that all methods are small. The highest CC was 7. I don"t recomend anything higher than 7 in this API.
* *PMD, CPD, Findbugs*: you should occasionally have a look if there is any issue.
* *Checkstyle*: I don"t care too much about this one.


=Develpment patterns in Java2word=
* don"t use any deprecated method in the API.
*always create code examples or unit tests using fluent API.
*look at other elements to have an idea about the pattern in the project
*unit tests, cyclomatic complexity matter here!
*do the document generation and see if it is all right. Don"t rely only in unit tests.
*Add javadoc to new methods (or existing methods).
*always write style code in a separate class. *ParagraphStyle.java* would be the one here. Test the paragraph content and style in the class *ParagraphTest*, by write unit test against *Paragraph.java* element. This way you show other devs how to use the element.



<br><br>


Leonardo Correa

'></a>