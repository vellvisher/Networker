package vsp.networker;

import java.util.ArrayList;

import vsp.networker.data.EventList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    
    
    private Integer folderImage = R.drawable.android_folder;   
    public ImageAdapter(Context c) {
        context = c;
    }
  
    public String getEventName(int position) {
    	return ListBook.getListName(position);
    }
    
    public int getCount() {
        return ListBook.Size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
    	View MyView = convertView;
    	if (convertView == null) {
    	/* we define the view that will display on the grid */

    	LayoutInflater li = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    	MyView = li.inflate(R.layout.custom_image, null);

    	// Add The Text!!!
    	TextView tv1 = (TextView) MyView.findViewById(R.id.text_item);
    	tv1.setText(getEventName(position));

 
    	// Add The Image!!!
    	ImageView iv = (ImageView) MyView.findViewById(R.id.image_item);
    	iv.setImageResource(folderImage);
    	}

    	return MyView;
    }

}