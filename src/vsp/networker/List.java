package vsp.networker;

import java.util.ArrayList;

public class List
{
	private String listName;
	/**
	 * @return the listName
	 */
	
	
	public List() {
		arrayList = new ArrayList<BusinessCard>();
	}
	public List(String name) {
		listName=name;
		arrayList = new ArrayList<BusinessCard>();
	}
	
	public String getListName() {
		return listName;
	}
	/**
	 * @param listName the listName to set
	 */
	public void setListName(String listName) {
		this.listName = listName;
	}

	private ArrayList<BusinessCard> arrayList;
	/**
	 * @return the arrayList
	 */
	public ArrayList<BusinessCard> getArrayList() {
		return arrayList;
	}
	/**
	 * @param arrayList the arrayList to set
	 */
	public void setArrayList(ArrayList<BusinessCard> arrayList) {
		this.arrayList = arrayList;
	}
	
	public void addBusinessCard(BusinessCard b) {
		arrayList.add(b);
	}
	
	public ArrayList<String> generateArrayListNames()
	{
		ArrayList<String> names = new ArrayList<String>();
		for(int i=0;i<arrayList.size();i++) {
			names.add(arrayList.get(i).USER_NAME);
		}
		return names;
	}
}
