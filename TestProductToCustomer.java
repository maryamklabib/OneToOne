import java.io.File;
//import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import org.apache.commons.io.FileUtils;

public class TestProductToCustomer {
	/** 
	 * To test this class, I had these additional lines in the SalesTaxes class
	 * 
	 * outputfile = new File(args[1]);
	 * PrintReceipt(inputfile, outputfile);
	 * 
	 * FileWriter writer = new FileWriter(output, true);
	 * writer.write(quantity + name + ": " + stotal + "\n");
	 * writer.write("Sales Taxes: " + salltaxes + "\n");
	 * writer.write("Total: " + sallprices);
	 * writer.close();

	 * **/

	@Test
	public void test() {
		
		//Saving the files and paths we're testing
		
		String inputpath1 = "/Users/lababib/Documents/eclipsecode/ProductToCustomer/src/input1";
		String inputpath2 = "/Users/lababib/Documents/eclipsecode/ProductToCustomer/src/input2";
		String inputpath3 = "/Users/lababib/Documents/eclipsecode/ProductToCustomer/src/input3";
		
		File output1 = new File("/Users/lababib/Documents/eclipsecode/ProductToCustomer/src/output1");
		File output2 = new File("/Users/lababib/Documents/eclipsecode/ProductToCustomer/src/output2");
		File output3 = new File("/Users/lababib/Documents/eclipsecode/ProductToCustomer/src/output3");
		
		String testpath1 = "/Users/lababib/Documents/eclipsecode/ProductToCustomer/src/test1";
		String testpath2 = "/Users/lababib/Documents/eclipsecode/ProductToCustomer/src/test2";
		String testpath3 = "/Users/lababib/Documents/eclipsecode/ProductToCustomer/src/test3";
		File test1 = new File("/Users/lababib/Documents/eclipsecode/ProductToCustomer/src/test1");
		File test2 = new File("/Users/lababib/Documents/eclipsecode/ProductToCustomer/src/test2");
		File test3 = new File("/Users/lababib/Documents/eclipsecode/ProductToCustomer/src/test3");

		
		//call method with the file path passed in
		try {
			
			//Testing first example
			String[] args = new String[2];
			args[0] = inputpath1;
			args[1] = testpath1;
			ProductToCustomer.main(args);
						
			//Testing second example
//			args = new String[2];
//			args[0] = inputpath2;
//			args[1] = testpath2;
//			ProductToCustomer.main(args);

			
			//Testing third example
//			args = new String[2];
//			args[0] = inputpath3;
//			args[1] = testpath3;
//			ProductToCustomer.main(args);
				
		
			//Confirm the output
		
			boolean compare1 = FileUtils.contentEquals(output1, test1);
			System.out.println("Are output1 and test1 the same? " + compare1);
			
			boolean compare2 = FileUtils.contentEquals(output2, test2);
			System.out.println("Are output2 and test2 the same? " + compare2);
			
			boolean compare3 = FileUtils.contentEquals(output3, test3);
			System.out.println("Are output3 and test3 the same? " + compare3);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
