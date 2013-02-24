package vsp.networker.data;

import java.util.ArrayList;

public class EventList {
	private static ArrayList<String> list = null;
	
	public static void loadData() {
		list = new ArrayList<String>();
		//get data from file, you can add temporary objects here
	}
	
	public static void saveData() {
		//save data to file
	}
	
	public static ArrayList<String> getList() {
		if (list == null) loadData();
		return list;
	}
}
