package vsp.networker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements  CreateNdefMessageCallback {
	NfcAdapter nfcAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("create");
		setContentView(R.layout.activity_main);
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		//nfcAdapter.setNdefPushMessageCallback(this, this);
		nfcAdapter.setNdefPushMessage(this.createNdefMessage(null), this, this);
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
			System.out.println("Got this - " +new String(msg.getRecords()[0].getPayload()));
			textView.setText(new String(msg.getRecords()[0].getPayload()));	
		}
	}

	@Override
	public NdefMessage createNdefMessage(NfcEvent arg0) {
        String text = ("Beam me up, Android!\n\n" +
                "Beam Time: " + System.currentTimeMillis());
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
}
