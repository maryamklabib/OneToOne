Language Used: 

Java 


How to run the program:
Download the source code and initialize the class with a path of the text file of your choice. 
It assumes that the input is a series of lines in accordance with the spec.

Testing: 

I used an external library from Commons IO 2.4:
https://commons.apache.org/proper/commons-io/javadocs/api-2.4/org/apache/commons/io/FileUtils.html

and used the static boolean contentEquals(File file1, File file2) function. 
For testing, I made it write out to a file and compared the contents with the expected output. If you want to test the same way, you’d have to make those files yourself or else pass in the path you’d like them to be at.

For right now, it prints to standard output as requested.


Note on Judgment:
I went for the more clear/easy to read coding style as opposed to any finicky functions
or complicated operations. This is in line with what I tend to do in the work force. I’ve found that it’s a matter of preference from company to company, so it’s generally something I adjust with experience.

