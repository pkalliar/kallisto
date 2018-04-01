package com.pankal.commons;

//import com.com.pankal.inventory.car.Type;
//import com.com.pankal.inventory.car.VehicleCategory;
//import com.com.pankal.inventory.car.VehicleMake;
//import com.com.pankal.inventory.car.VehicleModel;

/**
 * Created by com.pankal on 7/2/18.
 */

public class Csv2DB {

	final static String COMMA = ",";

//	public static List<VehicleModel> processInputFile(String inputFilePath) {
//		List<VehicleModel> inputList = new ArrayList<VehicleModel>();
//		try{
//			File inputF = new File(inputFilePath);
//			InputStream inputFS = new FileInputStream(inputF);
//			BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
//			// skip the header of the csv
//			inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
//			br.close();
//		} catch (IOException e) {
//      		e.printStackTrace();
//		}
//		return inputList ;
//	}
//
//	private static Function<String, VehicleModel> mapToItem = (line) -> {
//		String[] p = line.split(COMMA);// a CSV has comma separated lines
//		VehicleModel item = new VehicleModel();
//		item.setModel(Integer.parseInt(p[0].replaceAll("\"", "")));//<-- this is the first column in the csv file
//		item.setName(p[1].replaceAll("\"", ""));
//		item.setMake(Integer.parseInt(p[2].replaceAll("\"", "")));//<-- this is the first column in the csv file
//		item.setMake_name(p[3].replaceAll("\"", ""));
//		return item;
//	};





}
