package vsp.networker;

import java.util.ArrayList;
import java.util.HashMap;

public class BusinessCard
{
	
	public  final String USER_NAME = "name";
	public  final String USER_PHONE_NUMBER = "phoneNumber";
	public  final String USER_EMAIL = "email";
	public  final String USER_WEBSITE = "website";
	public  final String COMPANY_DESIGNATION = "companyDesignation";
	public  final String COMPANY_DEPARTMENT = "companyDepartment";
	public  final String COMPANY_NAME = "companyName";
	public  final String COMPANY_ADDRESS = "companyAddress";
	public  final String USER_DATA_FILENAME = "data_file";
	public  final String TWITTER = "TWITTER";
	public  final String LINKEDIN = "LINKEDIN";
	public  final String TWITTER_ID = "TWITTER_ID";
	
	public HashMap<String, String> details;
	
	public HashMap<String, String> getProfessionalDetailsMap(String designation, String department,
			String companyName, String companyAddress) {
		HashMap<String, String> profDetails = new HashMap<String, String>();
		profDetails.put(COMPANY_DESIGNATION, designation);
		profDetails.put(COMPANY_DEPARTMENT, department);
		profDetails.put(COMPANY_NAME, companyName);
		profDetails.put(COMPANY_ADDRESS, companyAddress);
		return profDetails;
	}
	
	public BusinessCard(String name, String email, String phoneNumber, String website,
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
	
	public BusinessCard() {
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	 * @return the uSER_NAME
	 */
	public String getUSER_NAME() {
		return USER_NAME;
	}

	/**
	 * @return the uSER_PHONE_NUMBER
	 */
	public String getUSER_PHONE_NUMBER() {
		return USER_PHONE_NUMBER;
	}

	/**
	 * @return the uSER_EMAIL
	 */
	public String getUSER_EMAIL() {
		return USER_EMAIL;
	}

	/**
	 * @return the uSER_WEBSITE
	 */
	public String getUSER_WEBSITE() {
		return USER_WEBSITE;
	}

	/**
	 * @return the cOMPANY_DESIGNATION
	 */
	public String getCOMPANY_DESIGNATION() {
		return COMPANY_DESIGNATION;
	}

	/**
	 * @return the cOMPANY_DEPARTMENT
	 */
	public String getCOMPANY_DEPARTMENT() {
		return COMPANY_DEPARTMENT;
	}

	/**
	 * @return the cOMPANY_NAME
	 */
	public String getCOMPANY_NAME() {
		return COMPANY_NAME;
	}

	/**
	 * @return the cOMPANY_ADDRESS
	 */
	public String getCOMPANY_ADDRESS() {
		return COMPANY_ADDRESS;
	}

	/**
	 * @return the uSER_DATA_FILENAME
	 */
	public String getUSER_DATA_FILENAME() {
		return USER_DATA_FILENAME;
	}

	/**
	 * @return the tWITTER
	 */
	public String getTWITTER() {
		return TWITTER;
	}

	/**
	 * @return the lINKEDIN
	 */
	public String getLINKEDIN() {
		return LINKEDIN;
	}

	/**
	 * @return the tWITTER_ID
	 */
	public String getTWITTER_ID() {
		return TWITTER_ID;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(HashMap<String, String> details) {
		this.details = details;
	}

	public HashMap<String, String> getDetails() {
		return details;
	}

}
