/** Zenreach ProductToCustomer
 
Can only offer each product to one customer and we may only offer one product to each customer.

Each day we will get the list of products that are eligible for these special discounts. 
We then have to decide which products to offer to which of our customers. 
how likely a given customer is to buy an offered product by calculating what we call the "suitability score" (SS). 
The top-secret algorithm to calculate the SS between a customer and a product is this: 

1. If the number of letters in the product's name is even then the SS is the number of vowels (a, e, i, o, u, y) 
in the customer's name multiplied by 1.5. 
2. If the number of letters in the product's name is odd then the SS is the number of consonants in the customer's name. 
3. If the number of letters in the product's name shares any common factors (besides 1) with the number of letters 
in the customer's name then the SS is multiplied by 1.5. 

Your task is to implement a program that assigns each customer a product to be offered in a way that maximizes the combined 
total SS across all of the chosen offers. 
Note that there may be a different number of products and customers. 
You may include code from external libraries as long as you cite the source.


==========
 **/
import java.io.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

     public class ProductToCustomer 
     {
    	 public static File inputfile;
    	 public static File outputfile;
    	 public static Boolean customerChanges = true;
    	 public static DecimalFormat decim = new DecimalFormat("0.00");
    	 
         public static void main (String[] args) throws IOException
         {        	 
        	 inputfile = new File(args[0]);
        	 maxSSScore(inputfile);
         }
         
         public static void maxSSScore(File textfile) throws FileNotFoundException, IOException
         {
        	/**
        	 * assume text file is one line,
        	 * for each line,
        	 * will split on semicolon
        	 * and save the first half as the customers, delimit on commas
        	 * and save the second half as the products, delimit on commas
        	 * 
        	 *now for an array of length n, there can be p permutations
        	 *make an array of length p that stores p arrays where each is a permutation of the og array
        	 *
        	 *make a cache for each customer to product combination, such that when you're building up the lists,
        	 *it's constant access time.
        	 *
        	 *
        	 *now also make a hashset that maps strings to SS score
        	 *the string is permutation + customer, the value is SS score as a double
        	 *
        	 *for each permutation of the customer array, get the SS score of that with the same array of products
        	 *have a helper function, given two arrays, finds the SS score between them until length of one ends
        	 *and returns it as a double
        	 *
        	 *save that in the all time total
        	 *
        	 *after that loop return the all time total
        	 */
             Double maxSS = 21.00;
        	 String line;
             BufferedReader in;
             
             //open the file
             in = new BufferedReader(new FileReader(textfile));
             line = in.readLine();

             //read the file
             while(line != null)
             {
            	 	//parse each line to a list of two items
            	 	String[] two = line.split(";");
            	 	//the first half of it is the customers
            	 	String[] customers = two[0].split(",");
            	 	
            	 	//the second half is the products
            	 	String[] products = two[1].split(",");
            	 	
            	 	//see which list is longer
            	 	String[] listtopermutate;
            	 	String[] control;
            	 	if (customers.length >= products.length)
            	 	{
            	 		listtopermutate = customers;
            	 		control = products;
            	 		customerChanges = true;
            	 	} else
            	 	{
            	 		listtopermutate = products;
            	 		control = customers;
            	 		customerChanges = false;
            	 	}
            	 	
            	 	//we want to make a list of all the permutations of the list
            	 	ArrayList<ArrayList<String>> permutations = permute(listtopermutate);
            	 	
            	 	System.out.println(permutations.toString());
            	 	
            	 	//for each permutation, get SS score and keep track of highest
            	 	for (int i = 0; i < permutations.size(); i ++)
            	 	{
                	 	Double test = getSS(permutations.get(i), control);
                	 	if (test > maxSS)
                	 	{
                	 		maxSS = test;
                	 	}
	
            	 	}
            	 	System.out.println(two[0]);
            	 	System.out.println(two[1]);
            	 	line = in.readLine();
             }
             System.out.println(decim.format(maxSS));
             in.close();
         }
         
		 
		/**
		 * This is the permutations function.
		 */
		 private static ArrayList<ArrayList<String>> permute(String[] num) {
				ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
			 
				//start from an empty list
				result.add(new ArrayList<String>());
			 
				for (int i = 0; i < num.length; i++) {
					//list of list in current iteration of the array num
					ArrayList<ArrayList<String>> current = new ArrayList<ArrayList<String>>();
			 
					for (ArrayList<String> l : result) {
						// # of locations to insert is largest index + 1
						for (int j = 0; j < l.size()+1; j++) {
							// + add num[i] to different locations
							l.add(j, num[i]);
			 
							ArrayList<String> temp = new ArrayList<String>(l);
							current.add(temp);
			 
							// - remove num[i] add
							l.remove(j);
						}
					}
			 
					result = new ArrayList<ArrayList<String>>(current);
				}
			 
				return result;
			}
		 
		 
		 /**
		  * 
		  * @param changes
		  * @param control
		  * @return the SS score
		  */
         
         private static double getSS(ArrayList<String> changes, String[] control ) {
 			// Calculates the SS score between each corresponding customer and product and returns the sum of the combo
        	 
        	 //need to know which is the product and which is the customer
        	 ArrayList<String> customers = new ArrayList<String>();
        	 ArrayList<String> products = new ArrayList<String>();
        	 if (customerChanges)
        	 {
        		customers = changes;
        		for (int i = 0; i < control.length; i++)
        		{
        			products.add(control[i]);
        		}
        	 } else {
        		 products = changes;
         		for (int i = 0; i < control.length; i++)
         		{
         			customers.add(control[i]);
         		}
        	 }
        	 
        	 //gonna start going through each element
        	 int range = Math.min(changes.size(), control.length);
        	 Double sum = 0.00;
        	 for (int i = 0; i < range; i++)
        	 {
        		 String product = products.get(i);
        		 String customer = customers.get(i);
//            	 1. If the number of letters in the product's name is even then the SS is the number of vowels (a, e, i, o, u, y) 
//            	 in the customer's name multiplied by 1.5.
        		 if (product.length() % 2 == 0)
        		 {
        			 double vow = getNumVowels(customer);
        			 sum += vow * 1.5;
        		 }
        		
//            	 2. If the number of letters in the product's name is odd then the SS is the number of consonants in the customer's name. 
        		 if (product.length() % 2 != 0)
        		 {
        			 double con = getNumConsonants(customer);
        			 sum += con * 1.5;
        		 }

//            	 3. If the number of letters in the product's name shares any common factors (besides 1) with the number of letters 
//            	 in the customer's name then the SS is multiplied by 1.5. 
        		 if (gcf(product.length(), customer.length()) > 1)
        		 {
        			 sum *= 1.5;
        		 }

        	 }
         	 return sum;
 		}

		private static double getNumConsonants(String customer) {
			String s = customer.toLowerCase();
			double consonantCount = 0;
			for (int i = 0; i < s.length(); ++i) {
				char c = s.charAt(i);
				if (c != 'a' & c != 'e' & c != 'i' & c != 'o' & c != 'u' & c != 'y')
				{
					consonantCount++;
				}
			}
			return consonantCount;
		}

		private static double getNumVowels(String customer) {
			String s = customer.toLowerCase();
			double vowelCount = 0;
			for (int i = 0; i < s.length(); ++i) {
			    switch(s.charAt(i)) {
			        case 'a':
			        case 'e':
			        case 'i':
			        case 'o':
			        case 'u':
			        case 'y':
			        	vowelCount++;
			            break;
			        default:
			            // do nothing
			    }
			}
			return vowelCount;
		}

		private static int gcf(int a, int b) {
			if (b == 0) return a;
		    else return (gcf (b, a % b));
		}
        
    }