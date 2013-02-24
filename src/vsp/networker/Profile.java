package vsp.networker;

import java.util.HashMap;

import vsp.networker.data.User;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Profile extends Activity {

	LayoutInflater inflater = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_profile, menu);
		return true;
	}
	
	public void submitButton(View view) {
	
		String name =  findViewById(R.id.editText1).toString().trim();
		String firstName = name.substring(0, name.lastIndexOf(' '));
		String lastName = name.substring(name.lastIndexOf(' ')+1);
		String email =  findViewById(R.id.editText2).toString();
		String phoneNo =  findViewById(R.id.editText3).toString();
		String designation =  findViewById(R.id.designation).toString();
		String department =  findViewById(R.id.department).toString();
		String companyName =  findViewById(R.id.company_name).toString();
		String companyAddress =  findViewById(R.id.editText4).toString();
		Intent create_event = new Intent(this,Contacts.class);
		HashMap<String,String> profDetails= User.getProfessionalDetailsMap(designation, department,
			companyName,companyAddress);
		User usr = new User(name, email, phoneNo, "", profDetails);
		usr.details.put(User.USER_FIRST_NAME, firstName);
		usr.details.put(User.USER_LAST_NAME, lastName);
		User.currentUser.details = usr.details;
		startActivity(create_event);
	}
}
