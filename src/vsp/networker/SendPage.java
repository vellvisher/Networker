package vsp.networker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.view.Menu;

public class SendPage extends Activity implements CreateNdefMessageCallback {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_page);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_send_page, menu);
		return true;
	}

	@SuppressLint("NewApi")
	@Override
	public NdefMessage createNdefMessage(NfcEvent arg0) {
        String text = ("Beam me up, Android!\n\n" +
                "Beam Time: " + System.currentTimeMillis());
        NdefMessage msg = new NdefMessage(
                new NdefRecord[] { NdefRecord.createMime(
                        "vsp.networker", text.getBytes()), NdefRecord.createApplicationRecord("vsp.networker")
        });
        return msg;
	}

}
