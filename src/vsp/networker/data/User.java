package vsp.networker.data;

import java.util.HashMap;

public class User implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7147071047698604994L;
	public static User currentUser;
	static {
		currentUser = new User();
	}
	
	public static final String USER_NAME = "name";
	public static final String USER_FIRST_NAME = "firstName";
	public static final String USER_LAST_NAME = "lastName";
	public static final String USER_PHONE_NUMBER = "phoneNumber";
	public static final String USER_EMAIL = "email";
	public static final String USER_WEBSITE = "website";
	public static final String COMPANY_DESIGNATION = "companyDesignation";
	public static final String COMPANY_DEPARTMENT = "companyDepartment";
	public static final String COMPANY_NAME = "companyName";
	public static final String COMPANY_ADDRESS = "companyAddress";
	public static final String SOCIAL_MEDIA_KEYS = "social_media_keys";
	public final HashMap<String, AccessTokenSecretKeyPair> socialMediaKeys = new HashMap<String, AccessTokenSecretKeyPair>();
	public static final String TWITTER = "TWITTER";
	public static final String LINKEDIN = "LINKEDIN";
	public static final String TWITTER_ID = "TWITTER_ID";
	public static final String LINKEDIN_ID = "LINKEDIN_ID";
	
	public HashMap<String, String> details;
	
	public static HashMap<String, String> getProfessionalDetailsMap(String designation, String department,
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
	
	public User() {

	}
	
	public HashMap<String, String> getDetails() {
		if(details == null) details = new HashMap<String, String>();
		return details;
	}
}