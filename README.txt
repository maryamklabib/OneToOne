Language Used: Java Problem Solved: Problem Two, Sales Taxes.

How to run the program:
Download the source code and initialize the class with a path of the text file of your choice. 
It assumes that the input is a series of lines in the form of:
[quantity] + [name of product] at [price per unit]

If you have a File object:
SalesTaxes.PrintReceipt(File [input file]);

If you have a string of the path to the file:
SalesTaxes.main(String [input file path]);


Proof of Correctness: 

I used an external library from Commons IO 2.4:
https://commons.apache.org/proper/commons-io/javadocs/api-2.4/org/apache/commons/io/FileUtils.html

and used the static boolean	contentEquals(File file1, File file2) function. 

Refer to the screenshot in the zip file that was submitted
as "sufficient evidence" that my solution is complete and works
correctly against the supplied test data.

Note on Judgment:
It was smooth sailing (wrote most of the program in 2 hours) until I realized the
doubles were not getting rounded up necessarily. Using the built in rounding function
automatically rounded to the nearest decimal place, sometimes higher and sometimes lower.

I tried the Math.ceil and Math.floor and various combinations but they didn't work. After two days
of trying to get the decimal places to work, I decided to take the painful route of manually 
manipulating strings and turning them back to doubles.

It is far from ideal but it was the only way that I got the exact output consistently that I wanted. If this was in the real world,
I would try a simpler hack or possibly have a discussion about the need to round up.

I also had to make a few assumptions regarding the test cases I was given. There were a few inconsistencies in the example
and I made a few judgments of my own.

Assumptions made:

There is no space between the name and the colon (the example was inconsistent with this).

The name of the product in the input is returned in the same form in the output. For example in example 3, 

the input was 
Input 3:
1 box of imported chocolates at 11.25

but the output was

Output 3:
1 imported box of chocolates: 11.85


I made the assumption that the output would be 
1 box of imported chocolates: 11.85 
in consistency with the other examples.