/**
 * 
 */
package com.iith.sem3.datamining;

/**
 * @author CS21MDS14015 (Jagadeesh Krishnan)
 *
 */

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import ca.pfv.spmf.algorithms.frequentpatterns.apriori.AlgoApriori;
import ca.pfv.spmf.algorithms.frequentpatterns.eclat.AlgoEclat;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;
import ca.pfv.spmf.test.MainTestApriori_saveToFile;
import ca.pfv.spmf.test.MainTestEclat_saveToFile;
import ca.pfv.spmf.test.MainTestFPGrowth_saveToFile;

public class soln_1 {


	public static void main(String[] args) throws IOException {

		//
		long startTimestamp;
		long endTimestamp;
		HashMap<String, long[]> mapTimeTaken = new HashMap<>();
		HashMap<String, long[]> mapTimeTaken2 = new HashMap<>();
		double[] minsup = {0.005,0.01,0.02,0.03,0.05,0.07};
		URL url;
		String input;
		String output;
		long[] timeTaken;
		TransactionDatabase database;
				 
		 
		//Apriori
		url = MainTestApriori_saveToFile.class.getResource("/retail2.txt");
		input = java.net.URLDecoder.decode(url.getPath(),"UTF-8");
		output = ".//apriori2_output.txt";  // the path for saving the frequent itemsets found

		timeTaken = new long[6];
		for (int i = 0; i < minsup.length; i++) {
			AlgoApriori apriori = new AlgoApriori();
			startTimestamp = System.currentTimeMillis();
			apriori.runAlgorithm(minsup[i], input, output);
			endTimestamp = System.currentTimeMillis();
			timeTaken[i] = (endTimestamp - startTimestamp)/1000;
			apriori.printStats();
		}
		mapTimeTaken2.put("Apriori2", timeTaken);
		//
		 

		//FPGrowth
		url = MainTestFPGrowth_saveToFile.class.getResource("/retail2.txt");
		input= java.net.URLDecoder.decode(url.getPath(),"UTF-8");
		//output = ".//fpgrowth2_output.txt";  // the path for saving the frequent itemsets found

		timeTaken = new long[6];
		for (int i = 0; i < minsup.length; i++) {
			AlgoFPGrowth fpgrowth = new AlgoFPGrowth();
			output = ".//fpgrowth2_output_"+String.valueOf(minsup[i])+".txt";
			startTimestamp = System.currentTimeMillis();
			fpgrowth.runAlgorithm(input, output, minsup[i]);
			endTimestamp = System.currentTimeMillis();
			timeTaken[i] = (endTimestamp - startTimestamp)/1000;
			fpgrowth.printStats();
		}
		mapTimeTaken2.put("FPGrowth2", timeTaken);
		//
		
		
		//ECLat 
		url = MainTestEclat_saveToFile.class.getResource("/retail2.txt");
		input= java.net.URLDecoder.decode(url.getPath(),"UTF-8");
		output = ".//eclat2_output.txt";  // the path for saving the frequent itemsets found

		timeTaken = new long[6];
		database = new TransactionDatabase();
		try {
			database.loadFile(input);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		for (int i = 0; i < minsup.length; i++) {
			AlgoEclat eclat = new AlgoEclat();
			startTimestamp = System.currentTimeMillis();
			eclat.runAlgorithm(output, database, minsup[i], true);
			endTimestamp = System.currentTimeMillis();
			timeTaken[i] = (endTimestamp - startTimestamp)/1000;
			eclat.printStats();
		}
		mapTimeTaken2.put("ECLat2", timeTaken);
		//
		
		
		/*SwingUtilities.invokeLater(() -> {  
		      LinePlot example2 = new LinePlot("Freq. pattern for Retail 2 ",mapTimeTaken2,minsup);  
		      example2.setAlwaysOnTop(true);  
		      example2.pack();  
		      example2.setSize(600, 400);  
		      example2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
		      example2.setVisible(true);  
		    });
		   */ 
		
		
		// now for a BIGGER dataset
		//Apriori
		url = MainTestApriori_saveToFile.class.getResource("/retail1.txt");
		input = java.net.URLDecoder.decode(url.getPath(),"UTF-8");
		output = ".//apriori_output.txt";  // the path for saving the frequent itemsets found

		timeTaken = new long[6];
		for (int i = 0; i < minsup.length; i++) {
			AlgoApriori apriori = new AlgoApriori();
			startTimestamp = System.currentTimeMillis();
			apriori.runAlgorithm(minsup[i], input, output);
			endTimestamp = System.currentTimeMillis();
			timeTaken[i] = (endTimestamp - startTimestamp)/1000;
			apriori.printStats();
		}
		mapTimeTaken.put("Apriori", timeTaken);
		//

		//FPGrowth
		url = MainTestFPGrowth_saveToFile.class.getResource("/retail1.txt");
		input= java.net.URLDecoder.decode(url.getPath(),"UTF-8");
		output = ".//fpgrowth_output.txt";  // the path for saving the frequent itemsets found

		timeTaken = new long[6];
		for (int i = 0; i < minsup.length; i++) {
			AlgoFPGrowth fpgrowth = new AlgoFPGrowth();
			output = ".//fpgrowth1_output_"+String.valueOf(minsup[i])+".txt";
			startTimestamp = System.currentTimeMillis();
			fpgrowth.runAlgorithm(input, output, minsup[i]);
			endTimestamp = System.currentTimeMillis();
			timeTaken[i] = (endTimestamp - startTimestamp)/1000;
			fpgrowth.printStats();
		}
		mapTimeTaken.put("FPGrowth", timeTaken);
		//

		//ECLat 
		url = MainTestEclat_saveToFile.class.getResource("/retail1.txt");
		input= java.net.URLDecoder.decode(url.getPath(),"UTF-8");
		output = ".//eclat_output.txt";  // the path for saving the frequent itemsets found

		timeTaken = new long[6];
		database = new TransactionDatabase();
		try {
			database.loadFile(input);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		for (int i = 0; i < minsup.length; i++) {
			AlgoEclat eclat = new AlgoEclat();
			startTimestamp = System.currentTimeMillis();
			eclat.runAlgorithm(output, database, minsup[i], true);
			endTimestamp = System.currentTimeMillis();
			timeTaken[i] = (endTimestamp - startTimestamp)/1000;
			eclat.printStats();
		}
		mapTimeTaken.put("ECLat", timeTaken);
		//
		
		SwingUtilities.invokeLater(() -> {  
		      LinePlot example1 = new LinePlot(new String("Freq. pattern for Retail 1"),mapTimeTaken,minsup); 
		      example1.setAlwaysOnTop(true);  
		      example1.pack();  
		      example1.setSize(1000, 400);  
		      example1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
		      example1.setVisible(true); 
		      LinePlot example2 = new LinePlot(new String("Freq. pattern for Retail 2"),mapTimeTaken2,minsup);  
		      example2.setAlwaysOnTop(true); 
		      example2.pack();  
		      example2.setSize(1000, 400);  
		      example2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
		      example2.setVisible(true);  
		    });
	
	}

}
