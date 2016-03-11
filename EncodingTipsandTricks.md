Encoding Tips and Tricks

Please refer to the new location: https://github.com/leonardoanalista/java2word

<a href='Hidden comment: 

= Introduction =

Considering the huge number of amigos Latinos using Java2word, as well people from all over the world with different languages, this page will document all knowledge regarding to encoding issues that we have had in this projects.

If in your language you need to use any special characters or words with accents, go ahead and read this page.

For example, if you need: años, sábado, &, ¥ and so on.

As we figure it out, most of encoding issues come up when you have encoding conversion when you generate or when you open the file. Most when you generate the document. The tip here is always make sure you have the same encoding everywhere.



= Templates, characters replacements =

This is how Alejandro found out how to read and write enforcing the same encoding for templates (issue 76):

```
//Open file

	String xmlTemplate="";
	        
	        Writer writer2 = new StringWriter();
	        char[] buffer = new char[5000];
	        File f = new File("c:\\Users\\alejandro\\Desktop\\ReleaseNotesTemplate.xml");
	        InputStreamReader rd = new InputStreamReader(new FileInputStream(f),Charset.forName("UTF-8"));
	        
	        Reader reader = new BufferedReader(rd);

	        int n;
	        try {
	            while ((n = reader.read(buffer)) != -1) 
	            {

	                writer2.write(buffer, 0, n);
	            }
	        } catch (IOException ex) {
	            
	        }

//Save file

  File fileObj = new File("c:\\Users\\alejandro\\Desktop\\SalidaTemplate.doc");
	        OutputStream outputStream = new FileOutputStream(fileObj);
	        
	        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, Charset.forName("UTF-8")));
	        
	       
	        
	        writer.println(xmlTemplate);
	        writer.close();
```


There is also a very hacky way that I figure it out. Right before writing the output document, replace all special with the Unicode Code Point:

```
        // This works
 	IDocument myDoc = new Document2004();
    	myDoc.addEle(Heading1.with("Java2word = Consejería").create());
    	
    	String xx = myDoc.getContent().replace("í", "&#237;");
    	String xx = myDoc.getContent();
    	    	
    	System.out.println(xx); //at this point you should see literally "Consejer&#237;a" in your output.
    	
    	TestUtils.createLocalDoc(xx, "leo01.doc"); //creates however you feel like
```

Complete list can be found here: http://en.wikipedia.org/wiki/List_of_XML_and_HTML_character_entity_references

(under investigation) It is very strange but it only works if the replacement is done inside the same method. I created a method on Utils.java to do all encoding replacements but it does NOT work and I can"t see any rational reason. It seems that the JVM is doing some sort of text conversion when passes to other class or method.

In the above code, when this problem happens, you see in the output "Consejer[]a" rather than "Consejer&#237;a"

Then when you open the the document on MS Word, it shows a square rather than "í".

```
        // This DOES NOT works
 	IDocument myDoc = new Document2004();
    	myDoc.addEle(Heading1.with("Java2word = Consejería").create());
    	
    	String xx = myDoc.getContent();
        xx = Utils.replaceSpecialCharacters(this); //it does the replacement for many accent marks. Take a look at it on the codebase.
        TestUtils.createLocalDoc(xx, "leo01.doc");
```


= Where the f#$% did &#237; come from =

In the list http://en.wikipedia.org/wiki/List_of_XML_and_HTML_character_entity_references, get the column "Unicode Code Point", get the decimal value. Then add "&#" at the beginning and ";" at the end. MS Word knows what to do with this representation (issue 70).


=Related Issues =
issue 70
issue 76


'></a>