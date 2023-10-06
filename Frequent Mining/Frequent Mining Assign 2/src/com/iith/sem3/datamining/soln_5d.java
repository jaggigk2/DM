/**
 * 
 */
package com.iith.sem3.datamining;

/**
 * @author CS21MDS14015 (Jagadeesh Krishnan)
 *
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author CS21MDS14015 (Jagadeesh Krishnan)
 *
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.pfv.spmf.algorithms.frequentpatterns.MSApriori.AlgoMSApriori;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemset;
import ca.pfv.spmf.test.MainTestMSApriori_saveToFile;

public class soln_5d {


	public static void main(String[] args) throws IOException {

		//
		double[] ls = {0.01};
		URL url;
		String input;
		String output;
		double beta =0.5;	
		String lineRead;
		
		long startTimestamp;
		long endTimestamp;

		//MS Apriori
		url = MainTestMSApriori_saveToFile.class.getResource("/retail1.txt");
		input = java.net.URLDecoder.decode(url.getPath(),"UTF-8");
		output = ".//MSapriori1_output.txt";  // the path for saving the frequent itemsets found

		startTimestamp = System.currentTimeMillis();
		for (int i = 0; i < ls.length; i++) {
			AlgoMSApriori msaprori = new AlgoMSApriori();
			msaprori.runAlgorithm(input, output, beta, ls[i]);
			msaprori.printStats();
		}

		// sdc implementations
		BufferedReader br = new BufferedReader(new FileReader(output));
		String items[];
		String str_1[];
		String str_2 = null;
		float T = 541909;
		double sdc = 0.05;

		List<ArrayList<Integer>> list_itemset = new ArrayList<>();
		ArrayList<Integer> list_itemcount = new ArrayList<Integer>();
		Map<Integer,Integer> oneItems = new HashMap<Integer,Integer>();

		List<ArrayList<Integer>> sdc_list_itemset = new ArrayList<>();
		ArrayList<Integer> sdc_list_itemcount = new ArrayList<Integer>();

		List<ArrayList<Integer>> frequentKitemSetCopy = null;
		ArrayList<Integer> frequentKitemCountSetCopy = null;

		while (((lineRead = br.readLine()) != null)) {
			items = lineRead.split("#");
			str_1 = items[0].split(" ");

			ArrayList<Integer> itemset = new ArrayList<Integer>();
			int oneitem = 0;
			for(int i = 0; i < str_1.length; i++) {
				oneitem = Integer.parseInt(str_1[i]);
				itemset.add(oneitem);

			}

			list_itemset.add(itemset);
			str_2 = items[1].substring(5).trim();
			list_itemcount.add(Integer.parseInt(str_2));

			if(str_1.length==1) {
				oneItems.put(oneitem, Integer.parseInt(str_2));
			}

			//System.out.println("");
		} // end of while loop

		for (int ind = 0; ind < list_itemset.size(); ind++) {
			if(list_itemset.get(ind).size()>1) {
				ArrayList<Integer> temp_itemset = list_itemset.get(ind);
				ArrayList<Integer> findmin_list = new ArrayList<Integer>();
				ArrayList<Integer> findmax_Subtracted = new ArrayList<Integer>();
				for (Integer item : temp_itemset) {
					findmin_list.add(oneItems.get(item));
				}
				int minCount = Collections.min(findmin_list);
				for (Integer item : temp_itemset) {
					findmax_Subtracted.add(oneItems.get(item)-minCount);
				}
				long maxCount = Collections.max(findmax_Subtracted);
				float sdcComp = maxCount/T;
				//To prevent very frequent items and very rare items from appearing in the same itemsets,
				if(sdcComp<=sdc) {
					sdc_list_itemset.add(temp_itemset);
					sdc_list_itemcount.add(list_itemcount.get(ind));
				}
			}
			else {
				sdc_list_itemset.add(list_itemset.get(ind));
				sdc_list_itemcount.add(list_itemcount.get(ind));
			}
		}// end of for loop

		// write to file
		List<Itemset> candidatesK = new ArrayList<Itemset>();
		for (int i = 0; i < sdc_list_itemset.size(); i++) {
			candidatesK.add(new Itemset(sdc_list_itemset.get(i), sdc_list_itemcount.get(i)));

		}

		String output_sdc = ".//MSapriori1_output_sdc.txt";  //
		BufferedWriter writer = new BufferedWriter(new FileWriter(output_sdc));

		// item_constraint implementation
		ArrayList<Integer> cbt1 = new ArrayList<Integer>(Arrays.asList(1534, 1943));
		ArrayList<Integer> cbt2 = new ArrayList<Integer>(Arrays.asList(1816, 1834));
		ArrayList<Integer> cbt3 = new ArrayList<Integer>(Arrays.asList(225, 1215));
		ArrayList<Integer> cbt4 = new ArrayList<Integer>(Arrays.asList(1394, 1989));
		ArrayList<Integer> cbt5 = new ArrayList<Integer>(Arrays.asList(1534, 1582));
		List<ArrayList<Integer>> cannotBeTogetherItemSet = new ArrayList<>();
		cannotBeTogetherItemSet.add(cbt1); cannotBeTogetherItemSet.add(cbt2); 
		cannotBeTogetherItemSet.add(cbt3);cannotBeTogetherItemSet.add(cbt4);
		cannotBeTogetherItemSet.add(cbt5);
		List<Integer> mustHaveItemSet = new ArrayList<Integer>(Arrays.asList(225,1394,1534,1816));
		frequentKitemSetCopy = new ArrayList<>();
		frequentKitemCountSetCopy = new ArrayList<>();
		
		for(int i=0;i<sdc_list_itemset.size();i++)
		{
			int flag1=0, flag2=0;

			for (int j=0;j<cannotBeTogetherItemSet.size();j++)
			{
				if((sdc_list_itemset.get(i)).containsAll(cannotBeTogetherItemSet.get(j)))
				{
					flag1=1;
				}
			}
			for(int j=0;j<mustHaveItemSet.size();j++)
			{
				if(sdc_list_itemset.get(i).contains(mustHaveItemSet.get(j)))
				{
					flag2=1;
				}
			}
			if(flag1==0 && flag2==1) {
				frequentKitemSetCopy.add(sdc_list_itemset.get(i));
				frequentKitemCountSetCopy.add(sdc_list_itemcount.get(i));
			}
		} // for ended
		
		endTimestamp = System.currentTimeMillis();
		long timeTaken = (endTimestamp - startTimestamp)/1000;
		System.out.println("\tTime taken with Item Constraints = "+String.valueOf(timeTaken));
		
		//List<Itemset> candidatesK2 = new ArrayList<Itemset>();
		for (int i = 0; i < frequentKitemSetCopy.size(); i++) {
			candidatesK.add(new Itemset(frequentKitemSetCopy.get(i), frequentKitemCountSetCopy.get(i)));
		}

		for (Itemset candidate : candidatesK) {
			// write the itemset with its support count
			writer.write(candidate.toString() + " #SUP: "	+ candidate.getAbsoluteSupport());
			writer.newLine();
		}
		writer.close();

	}

}
