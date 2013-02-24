package vsp.networker;

import java.util.ArrayList;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;


public class ListViewLoader extends Activity {

	 private ListView mainListView ;  
	 private ArrayAdapter<String> listAdapter ; 
	 
	 public void onCreate(Bundle savedInstanceState) {  
		    super.onCreate(savedInstanceState);  
		    setContentView(R.layout.activity_list_view_loader);  
		      
		    // Find the ListView resource.   
		    mainListView = (ListView) findViewById( R.id.mainListView);  
		    
		   ArrayList<String> names = getIntent().getStringArrayListExtra("haha");

		    listAdapter = new ArrayAdapter<String>(this, R.layout.simple_row,names);  
		      
		    // Add more planets. If you passed a String[] instead of a List<String>   
		    // into the ArrayAdapter constructor, you must not add more items.   
		    // Otherwise an exception will occur.        
		    // Set the ArrayAdapter as the ListView's adapter.  
		    mainListView.setAdapter( listAdapter );        
		  }  
}

