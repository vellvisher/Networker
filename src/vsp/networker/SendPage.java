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
import android.widget.TextView;

public class SendPage extends Activity implements CreateNdefMessageCallback {
	NfcAdapter nfcAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_page);
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		if(nfcAdapter == null) return;
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
        		new NdefRecord[] { NdefRecord.createMime("application/vsp.networker", text.getBytes())
        });
        return msg;
	}

	@Override
	public void onResume() {
		super.onResume();
		if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
			processIntent(getIntent());
		}
	}

	private void processIntent(Intent intent) {
		TextView textView = (TextView) findViewById(R.id.send_view);
		Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		NdefMessage msg = (NdefMessage) rawMsgs[0];
		textView.setText(new String(msg.getRecords()[0].getPayload()));	
	}
}
