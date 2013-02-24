package vsp.networker.data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import android.content.Context;

public class User {
	public static final String USER_NAME = "name";
	public static final String USER_PHONE_NUMBER = "phoneNumber";
	public static final String USER_EMAIL = "email";
	public static final String USER_WEBSITE = "website";
	public static final String COMPANY_DESIGNATION = "companyDesignation";
	public static final String COMPANY_DEPARTMENT = "companyDepartment";
	public static final String COMPANY_NAME = "companyName";
	public static final String COMPANY_ADDRESS = "companyAddress";
	public static final String USER_DATA_FILENAME = "data_file";
	
	private HashMap<String, String> details;
	
	public HashMap<String, String> getProfessionalDetailsMap(String designation, String department,
			String companyName, String companyAddress) {
		HashMap<String, String> profDetails = new HashMap<String, String>();
		profDetails.put(COMPANY_DESIGNATION, designation);
		profDetails.put(COMPANY_DEPARTMENT, department);
		profDetails.put(COMPANY_NAME, companyName);
		profDetails.put(COMPANY_ADDRESS, companyAddress);
		return profDetails;
	}
	
	public User(String name, String email, String phoneNumber, String website,
			HashMap<String, String> professionalDetails) {
		if (details == null) details = new HashMap<String, String>();
		details.put(USER_NAME, name);
		details.put(USER_PHONE_NUMBER, phoneNumber);
		details.put(USER_EMAIL, email);
		details.put(USER_WEBSITE, website);
		if (professionalDetails != null) {
			details.putAll(professionalDetails);
		}
	}
	
	public void loadData() {
		
	}
	
	public void saveData(Context context) {
		String string = "hello world!";

		FileOutputStream fos;
		try {
			fos = context.openFileOutput(USER_DATA_FILENAME, Context.MODE_PRIVATE);
			fos.write(string.getBytes());
			fos.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}