package vsp.networker;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class Contacts extends Activity 
{
	    
    private ClearableEditText clrObj = null;
        
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		
		GridView gridview = (GridView) findViewById(R.id.gridView1);
	    gridview.setAdapter(new ImageAdapter(this));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            Toast.makeText(Contacts.this, "" + position, Toast.LENGTH_SHORT).show();
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contacts, menu);
		return true;
	}
	
	public void addFolder(View view) {
		
	clrObj =(ClearableEditText) findViewById(R.id.edit_text_clearable);
	Editable text = clrObj.getText();
	
	clrObj.emptyText();
	
	}
}
