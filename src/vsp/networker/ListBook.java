package vsp.networker;

import java.util.ArrayList;

public class ListBook
{
	private static ArrayList<List> listBook= new ArrayList<List>();

	/**
	 * @return the listBook
	 */
	
	public ListBook() {
	
	}
	public ArrayList<List> getListBook() {
		return listBook;
	}

	/**
	 * @param listBook the listBook to set
	 */
	public void setListBook(ArrayList<List> listBook) {
		this.listBook = listBook;
	}
	
	public static void add(List a) {
		listBook.add(a);
	}
	
	public List getList(String name) {
		for(int i=0;i<listBook.size();i++) {
			if(listBook.get(i).getListName()==name) {
				return listBook.get(i);
			}
		}
		return null;
	}
	
	public static List getList(int position) {
	
				return listBook.get(position);
	}
	
	public static String getListName(int position) {
		
		return listBook.get(position).getListName();
	}
	public static int Size() {
		// TODO Auto-generated method stub
		return listBook.size();
	}

}
