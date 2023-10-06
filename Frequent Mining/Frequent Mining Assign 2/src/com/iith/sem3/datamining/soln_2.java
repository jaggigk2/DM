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

import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPClose;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPMax;
import ca.pfv.spmf.test.MainTestFPClose_saveToFile;
import ca.pfv.spmf.test.MainTestFPGrowth_saveToFile;
import ca.pfv.spmf.test.MainTestFPMax_saveToFile;

public class soln_2 {


	public static void main(String[] args) throws IOException {

		//
		long startTimestamp;
		long endTimestamp;
		HashMap<String, long[]> mapTimeTaken = new HashMap<>();
		double[] minsup = {0.005,0.01,0.02,0.03,0.05,0.07};
		URL url;
		String input;
		String output;
		long[] timeTaken;

		//FPGrowth
		url = MainTestFPGrowth_saveToFile.class.getResource("/retail2.txt");
		input= java.net.URLDecoder.decode(url.getPath(),"UTF-8");
		output = ".//fpgrowth2_output.txt";  // the path for saving the frequent itemsets found

		timeTaken = new long[6];
		for (int i = 0; i < minsup.length; i++) {
			AlgoFPGrowth fpgrowth = new AlgoFPGrowth();
			startTimestamp = System.currentTimeMillis();
			fpgrowth.runAlgorithm(input, output, minsup[i]);
			endTimestamp = System.currentTimeMillis();
			timeTaken[i] = (endTimestamp - startTimestamp);
			fpgrowth.printStats();
		}
		mapTimeTaken.put("FPGrowth2", timeTaken);
		//

		//FPClose
		url = MainTestFPClose_saveToFile.class.getResource("/retail2.txt");
		input= java.net.URLDecoder.decode(url.getPath(),"UTF-8");
		output = ".//fpclose2_output.txt";  // the path for saving the frequent itemsets found

		timeTaken = new long[6];
		for (int i = 0; i < minsup.length; i++) {
			AlgoFPClose fpclose = new AlgoFPClose();
			startTimestamp = System.currentTimeMillis();
			fpclose.runAlgorithm(input, output, minsup[i]);
			endTimestamp = System.currentTimeMillis();
			timeTaken[i] = (endTimestamp - startTimestamp);
			fpclose.printStats();
		}
		mapTimeTaken.put("FPClose2", timeTaken);
		//

		//FPMax
		url = MainTestFPMax_saveToFile.class.getResource("/retail2.txt");
		input= java.net.URLDecoder.decode(url.getPath(),"UTF-8");
		output = ".//fpmax2_output.txt";  // the path for saving the frequent itemsets found

		timeTaken = new long[6];
		for (int i = 0; i < minsup.length; i++) {
			AlgoFPMax fpmax = new AlgoFPMax();
			startTimestamp = System.currentTimeMillis();
			fpmax.runAlgorithm(input, output, minsup[i]);
			endTimestamp = System.currentTimeMillis();
			timeTaken[i] = (endTimestamp - startTimestamp);
			fpmax.printStats();
		}
		mapTimeTaken.put("FPMax2", timeTaken);
		//
		
		// now for a bigger dataset
		//FPGrowth
		url = MainTestFPGrowth_saveToFile.class.getResource("/retail1.txt");
		input= java.net.URLDecoder.decode(url.getPath(),"UTF-8");
		output = ".//fpgrowth_output.txt";  // the path for saving the frequent itemsets found

		timeTaken = new long[6];
		for (int i = 0; i < minsup.length; i++) {
			AlgoFPGrowth fpgrowth = new AlgoFPGrowth();
			startTimestamp = System.currentTimeMillis();
			fpgrowth.runAlgorithm(input, output, minsup[i]);
			endTimestamp = System.currentTimeMillis();
			timeTaken[i] = (endTimestamp - startTimestamp);
			fpgrowth.printStats();
		}
		mapTimeTaken.put("FPGrowth", timeTaken);
		//

		//FPClose
		url = MainTestFPClose_saveToFile.class.getResource("/retail1.txt");
		input= java.net.URLDecoder.decode(url.getPath(),"UTF-8");
		output = ".//fpclose2_output.txt";  // the path for saving the frequent itemsets found

		timeTaken = new long[6];
		for (int i = 0; i < minsup.length; i++) {
			AlgoFPClose fpclose = new AlgoFPClose();
			startTimestamp = System.currentTimeMillis();
			fpclose.runAlgorithm(input, output, minsup[i]);
			endTimestamp = System.currentTimeMillis();
			timeTaken[i] = (endTimestamp - startTimestamp);
			fpclose.printStats();
		}
		mapTimeTaken.put("FPClose", timeTaken);
		//

		//FPMax
		url = MainTestFPMax_saveToFile.class.getResource("/retail1.txt");
		input= java.net.URLDecoder.decode(url.getPath(),"UTF-8");
		output = ".//fpmax2_output.txt";  // the path for saving the frequent itemsets found

		timeTaken = new long[6];
		for (int i = 0; i < minsup.length; i++) {
			AlgoFPMax fpmax = new AlgoFPMax();
			startTimestamp = System.currentTimeMillis();
			fpmax.runAlgorithm(input, output, minsup[i]);
			endTimestamp = System.currentTimeMillis();
			timeTaken[i] = (endTimestamp - startTimestamp);
			fpmax.printStats();
		}
		mapTimeTaken.put("FPMax", timeTaken);
		//
	}

}
