package vsp.networker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import vsp.networker.data.User;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.RawContacts.Data;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements  CreateNdefMessageCallback {
	NfcAdapter nfcAdapter;
	public static final String USER_DATA_FILENAME = "data_file";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("create");
		setContentView(R.layout.activity_main);
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		//nfcAdapter.setNdefPushMessageCallback(this, this);
		if(nfcAdapter != null) {
			nfcAdapter.setNdefPushMessage(this.createNdefMessage(null), this, this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void sendPageButton(View view) {
		Intent intent = new Intent(this, SendPage.class);
		startActivity(intent);
	}
	
	public void onResume() {
		super.onResume();
		System.out.println("resume");
		Parcelable[] rawMsgs;
		System.out.println(getIntent().getAction());
		if((rawMsgs = getIntent().getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)) != null) {
			//Intent intent = new Intent(this, SendPage.class);
			//startActivity(intent);
			System.out.println("nirvana");
			TextView textView = (TextView) findViewById(R.id.hello_view);
			NdefMessage msg = (NdefMessage) rawMsgs[0];
			String data = new String(msg.getRecords()[0].getPayload());
			System.out.println("Got this - " +new String(msg.getRecords()[0].getPayload()));
			textView.setText(new String(msg.getRecords()[0].getPayload()));	
			try {
				final JSONObject otherUserObject = new JSONObject(data);
				if(otherUserObject.get(User.TWITTER_ID) != null) {
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							try {
								TwitterActivity.followContact((String)otherUserObject.get(User.TWITTER_ID));
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}).start();
				}
				if(otherUserObject.get(User.LINKEDIN_ID) != null) {
					//Follow on twitter using api
					//LinkedIn
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							try {
								LinkedInActivity.connectWithPerson((String)otherUserObject.get(User.USER_FIRST_NAME), 
										(String)otherUserObject.get(User.USER_LAST_NAME), (String)otherUserObject.get(User.USER_EMAIL));
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
				
				/*
				// add user details to android event
				ContentValues values = new ContentValues();
	            values.put(Data.RAW_CONTACT_ID, 001);
	            values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
	            values.put(Phone.NUMBER, otherUserObject.getString(User.USER_PHONE_NUMBER));
	            values.put(Phone.TYPE, Phone.TYPE_CUSTOM);
	            values.put(Phone.LABEL, "");
	            Uri dataUri = getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
				*/
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public NdefMessage createNdefMessage(NfcEvent arg0) {
        //String text = ("Beam me up, Android!\n\n" +
        //        "Beam Time: " + System.currentTimeMillis());
		if (!loadData(this)) profilePageButton(null);
        if (User.currentUser == null || User.currentUser.details == null) return null;
		JSONObject userData = new JSONObject(User.currentUser.details);
        String text = userData.toString();
        NdefMessage msg = new NdefMessage(
        		new NdefRecord[] { NdefRecord.createMime("application/vsp.networker", text.getBytes())
        });
        return msg;
	}

	public void profilePageButton(View view) {
		
		Intent profileIntent = new Intent(this, Profile.class);
		startActivity(profileIntent);
	}
	
	public void showSocialActivity(View view) {
		Intent socialActivity = new Intent(this, SocialActivity.class);
		startActivity(socialActivity);
	}
	
	public static boolean loadData(Context context) {
		FileInputStream fis;
		boolean firstTime = false;
		try {
			fis = context.openFileInput(USER_DATA_FILENAME);
			ObjectInputStream is = new ObjectInputStream(fis);
			User.currentUser = (User) is.readObject();
			is.close();
		} catch (FileNotFoundException e) {
			User.currentUser = new User();
			User.currentUser.details = new HashMap<String, String>();
			saveData(context);
			firstTime = true;
		} catch (OptionalDataException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(User.currentUser == null || User.currentUser.details == null ||
				User.currentUser.details.get(User.USER_NAME) == null || 
				"".equals(User.currentUser.details.get(User.USER_NAME)))
			firstTime = true;
		return !firstTime;
	}
	
	public static void saveData(Context context){
		try {
			FileOutputStream fos = context.openFileOutput(USER_DATA_FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(User.currentUser);
			os.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
