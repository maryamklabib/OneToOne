How to run the program:
Download the source code and initialize the ProductToCustomer class object with a path of the text file of your choice. 
It assumes that the text file is composed of any number of lines in the form of [product,product,product;customer,customer,customer] with varying amounts of products/customers.

Testing: 

I used an external library from Commons IO 2.4:
https://commons.apache.org/proper/commons-io/javadocs/api-2.4/org/apache/commons/io/FileUtils.html
and used the static boolean contentEquals(File file1, File file2) function. 
For testing, I wrote it out to a file and compared the contents with the expected output.
