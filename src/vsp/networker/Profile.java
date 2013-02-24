package vsp.networker;

import java.util.HashMap;

import vsp.networker.data.User;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;

public class Profile extends Activity {

	private LayoutInflater inflater = null;
	private ScrollView scrollBg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		
		scrollBg = (ScrollView)findViewById(R.id.scrollview);
		scrollBg.setOnTouchListener(new OnTouchListener() {

	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	            imm.hideSoftInputFromWindow(scrollBg.getWindowToken(), 0);
	            return false;
	        }
	    });
		
		AccountManager am = AccountManager.get(this); // "this" references the current Context

		Account[] accounts = am.getAccountsByType("com.google");
		if(accounts.length >= 1) {
			Account account = accounts[0];
			EditText emailField = (EditText) findViewById(R.id.editText2);
			if(account.name.contains("gmail")) emailField.setText(account.name);
		}
		getUserDetails();
	}
	
	public void getUserDetails() {
		Cursor c = this.getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
		int count = c.getCount();
		String[] columnNames = c.getColumnNames();
		boolean b = c.moveToFirst();
		int position = c.getPosition();
		String displayName = "";
		if (count == 1 && position == 0) {
		    for (int j = 0; j < columnNames.length; j++) {
		        String columnName = columnNames[j];
		        String columnValue = c.getString(c.getColumnIndex(columnName));
		        if("display_name".equals(columnName)) displayName = columnValue;
		    }
		}
		EditText nameField = (EditText) findViewById(R.id.editText1);
		if (!"".equals(displayName)) nameField.setText(displayName);
		c.close();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_profile, menu);
		return true;
	}
	
	public void submitButton(View view) {
	
		String name =  ((EditText)findViewById(R.id.editText1)).getText().toString().trim();
		System.out.println("NAME:"+name);
		User.currentUser.details.put(User.USER_NAME, name);
		
		String firstName = name.substring(0, name.lastIndexOf(' '));
		User.currentUser.details.put(User.USER_FIRST_NAME, firstName);
		
		String lastName = name.substring(name.lastIndexOf(' ')+1);
		User.currentUser.details.put(User.USER_LAST_NAME, lastName);

		String email =  ((EditText)findViewById(R.id.editText2)).getText().toString().trim();
		User.currentUser.details.put(User.USER_EMAIL, email);

		String phoneNo =  ((EditText)findViewById(R.id.editText3)).getText().toString().trim();
		User.currentUser.details.put(User.USER_PHONE_NUMBER, phoneNo);

		String designation =  ((EditText)findViewById(R.id.designation)).getText().toString().trim();
		String department =  ((EditText)findViewById(R.id.department)).getText().toString().trim();
		String companyName =  ((EditText)findViewById(R.id.company_name)).getText().toString().trim();
		String companyAddress =  ((EditText)findViewById(R.id.editText4)).getText().toString().trim();

		HashMap<String,String> profDetails= User.getProfessionalDetailsMap(designation, department,
			companyName,companyAddress);
		//User usr = new User(name, email, phoneNo, "", profDetails);
		for(String key : profDetails.keySet()) {
			User.currentUser.details.put(key, profDetails.get(key));
		}
		MainActivity.saveData(this);
		Intent create_event = new Intent(this, Contacts.class);
		startActivity(create_event);
	}
	
	public void twitterActivity(View view) {
		Intent profileIntent = new Intent(this, TwitterActivity.class);
		startActivity(profileIntent);
		
	}
	
	public void linkedInActivity(View view) {
		Intent profileIntent = new Intent(this, LinkedInActivity.class);
		startActivity(profileIntent);
		
	}
}
