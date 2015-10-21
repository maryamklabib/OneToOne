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

	/**
	 * 
	 * @author Maryam Labib
	 * You can directly call the main method if you have an array of strings with the pathname
	 * or you can call the maxSSScore method if you have the file object.
	 *
	 */
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
         
         /**
          * 
          * @param textfile it will read line by line and determine the highest possible SS score of the options.
          * @throws FileNotFoundException if the textfile passed in is invalid
          * @throws IOException the usual i/o errors that can happen
          */
         public static void maxSSScore(File textfile) throws FileNotFoundException, IOException
         {
        	 String line;
             BufferedReader in;
             
             //open the file
             in = new BufferedReader(new FileReader(textfile));
             line = in.readLine();

             //read the file
             while(line != null)
             {
            	 	Double maxSS = 0.00;
            	 	//parse each line to a list of two items
            	 	String[] two = line.split(";");
            	 	//the first half of it is the customers
            	 	String[] customers = two[0].split(",");
            	 	
            	 	//the second half is the products
            	 	String[] products = two[1].split(",");
            	 	
            	 	//see which list is longer and make all permutations of the longer one
            	 	//keep a boolean to track if you're permuting the customers or the products
            	 	String[] changes;
            	 	String[] control;
            	 	if (customers.length >= products.length)
            	 	{
            	 		changes = customers;
            	 		control = products;
            	 		customerChanges = true;
            	 	} else
            	 	{
            	 		changes = products;
            	 		control = customers;
            	 		customerChanges = false;
            	 	}
            	 	
            	 	//make a list of all the permutations of the list
            	 	ArrayList<ArrayList<String>> permutations = permute(changes);
            	 	
            	 	//for each permutation, get SS score of products to customers and keep track of highest
            	 	for (int i = 0; i < permutations.size(); i ++)
            	 	{
                	 	Double test = getSS(permutations.get(i), control);
                	 	if (test > maxSS)
                	 	{
                	 		maxSS = test;
                	 	}
	
            	 	}
            	 	System.out.print(decim.format(maxSS));
            	 	System.out.println();
            	 	line = in.readLine();
             }
             in.close();
         }
         
		 
		/**
		 * Given a list of strings, returns an arraylist of each possible permutation of the
		 * list [the mini lists are arraylists too]
		 */
		 private static ArrayList<ArrayList<String>> permute(String[] str) {
				ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
			 
				//would rather work with ArrayLists haha
				result.add(new ArrayList<String>());
			 
				for (int i = 0; i < str.length; i++) {

					//list of list in current iteration of the array str
					ArrayList<ArrayList<String>> current = new ArrayList<ArrayList<String>>();
			 
					for (ArrayList<String> l : result) {
						// # of locations to insert is largest index + 1
						for (int j = 0; j < l.size()+1; j++) {
							// + add str[i] to different locations
							l.add(j, str[i]);
			 
							ArrayList<String> temp = new ArrayList<String>(l);
							current.add(temp);
			 
							// - remove str[i] add
							l.remove(j);
						}
					}
			 
					result = new ArrayList<ArrayList<String>>(current);
				}
			 
				return result;
			}
		 
		 
		 /**
		  * 
		  * @param changes this is the one that's a permutation
		  * @param control this is the one that was never changed
		  * @return the SS score of this combination
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
        	 Double total = 0.00; //for this whole permutations
        	 for (int i = 0; i < Math.min(products.size(), customers.size()); i++)
        	 {
        		 Double sum = 0.00; //for just this product and customer
        		 String product = products.get(i);
        		 String customer = customers.get(i);
        		 
        		 /**
        		  * Note: I considered caching a lot of the things we were calculating over and over,
        		  * such as number of letters, number of vowels, number of consonants, 
        		  * and perhaps most efficiently SS scores for each combination.
        		  */
//            	 1. If the number of letters in the product's name is even then the SS is 
//        		 the number of vowels (a, e, i, o, u, y) in the customer's name multiplied by 1.5.
        		 int numLetters = getNumLetters(product);
        		 int numCus = getNumLetters(customer);
        		 if (numLetters % 2 == 0)
        		 {
        			 double vow = getNumVowels(customer);
        			 sum += vow * 1.5;
        		 }
        		
//            	 2. If the number of letters in the product's name is odd then the SS is the number
//        		 of consonants in the customer's name. 
        		 if (numLetters % 2 != 0)
        		 {
        			 sum += getNumConsonants(customer);
        		 }

//            	 3. If the number of letters in the product's name shares any common factors (besides 1) 
//        		 with the number of letters in the customer's name then the SS is multiplied by 1.5. 
        		 if (gcf(numLetters, numCus) != 1)
        		 {
        			 sum = sum * 1.5;
        		 }
        		 total += sum;
        	 }
         	 return total;
 		}

		private static int getNumLetters(String product) {
			int sum = 0;
			for (int i = 0; i < product.length(); i++)
			{
				if (Character.isAlphabetic(product.charAt(i)))
				{
					sum += 1;
				}
			}
			return sum;
		}

		private static double getNumConsonants(String s) {
			s = s.toLowerCase();
			double consonantCount = 0;
			for (int i = 0; i < s.length(); ++i) {
				char c = s.charAt(i);
				if (c != 'a' & c != 'e' & c != 'i' & c != 'o' & c != 'u' & c != 'y' & Character.isAlphabetic(c))
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
			            break;
			    }
			}
			return vowelCount;
		}

		private static int gcf(int a, int b) {
			if (b == 0) return a;
		    else return (gcf (b, a % b));
		}
        
    }