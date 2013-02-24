package vsp.networker;

import vsp.networker.data.EventList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.Toast;

public class Contacts extends Activity 
{

    private ClearableEditText clrObj = null;
    GridView gridview;
    private ImageAdapter imgAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		
	    gridview = (GridView) findViewById(R.id.gridView1);
		imgAdapter = new ImageAdapter(this);
	    gridview.setAdapter(imgAdapter);

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	List l=ListBook.getList(position);
	        	Intent intent = new Intent(getApplicationContext(), ListViewLoader.class);
	        	intent.putStringArrayListExtra("haha", l.generateArrayListNames());
	    		startActivity(intent); 
	            Toast.makeText(Contacts.this, "" + position, Toast.LENGTH_SHORT).show();
	        }
	    });
	    
	    
	    
		gridview.setOnTouchListener(new OnTouchListener() {

	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	            imm.hideSoftInputFromWindow(gridview.getWindowToken(), 0);
	            return false;
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
	String text = clrObj.getText().toString();
	if(!text.contentEquals("")) {	
    ListBook.add(new List(text));
    imgAdapter.notifyDataSetChanged();
    gridview.setAdapter(imgAdapter);
	clrObj.emptyText();
	}
	}
}
