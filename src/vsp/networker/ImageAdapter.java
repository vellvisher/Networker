package vsp.networker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    private Context context;

    public ImageAdapter(Context c) {
        context = c;
    }

    public int getCount() {
        return mThumbIds.length;
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
    	tv1.setText("Title" + position);

 
    	// Add The Image!!!
    	ImageView iv = (ImageView) MyView.findViewById(R.id.image_item);
    	iv.setImageResource(mThumbIds[position]);
    	}

    	return MyView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_2, 
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_5,
            
    };
    
    private String[] mThumbtext = {
            "This Week",
            "C&EN Store",
            "My Library", 
            "Latest News",
            "More"
    };
}