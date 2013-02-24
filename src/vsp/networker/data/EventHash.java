package vsp.networker.data;

import java.util.HashMap;

public class EventHash {
	public static HashMap<String, EventObject> map = null;
	
	public void loadData() {
		map = new HashMap<String, EventObject>();
		//get data from file, you can add temporary objects here
	}
	
	public void saveData() {
		//save data to file
	}
}
