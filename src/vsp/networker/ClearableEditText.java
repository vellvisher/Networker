package vsp.networker;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class ClearableEditText extends RelativeLayout
{
	LayoutInflater inflater = null;
	EditText edit_text;
	ImageButton btn_clear;

public ClearableEditText(Context context, AttributeSet attrs, int defStyle)
{
	super(context, attrs, defStyle);
	initViews();
}

public ClearableEditText(Context context, AttributeSet attrs)
{
	super(context, attrs);
	initViews();

}

public ClearableEditText(Context context)
{
	super(context);
	initViews();
}

void initViews()
{
	inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	inflater.inflate(R.layout.clearable_edit_text, this, true);
	edit_text = (EditText) findViewById(R.id.clearable_edit);
	btn_clear = (ImageButton) findViewById(R.id.clearable_button_clear);
	btn_clear.setVisibility(RelativeLayout.INVISIBLE);
	setFontColor();
	clearText();
	showHideClearButton();
}

private void setFontColor() {
	edit_text.setOnFocusChangeListener(new OnFocusChangeListener() {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                v.setBackgroundColor(Color.WHITE);
                ((EditText) v).setTextColor(Color.BLACK);
            } else {

                v.setBackgroundColor(Color.WHITE);
                ((EditText) v).setTextColor(Color.BLACK);
            }

        }
    });
	
}

void clearText()
{
	btn_clear.setOnClickListener(new OnClickListener()
	{
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		edit_text.setText("");
		}
	});
}

void showHideClearButton()
{
edit_text.addTextChangedListener(new TextWatcher()
{
	
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count)
	{
		// TODO Auto-generated method stub
		if (s.length() > 0)
		btn_clear.setVisibility(RelativeLayout.VISIBLE);
		else
		btn_clear.setVisibility(RelativeLayout.INVISIBLE);
	}
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after)
	{
	// TODO Auto-generated method stub
	
	}

@Override
public void afterTextChanged(Editable s)
{
	// TODO Auto-generated method stub
	
}
	});
}

public  Editable getText()
{
	Editable text = edit_text.getText();
	return text;
}

public void emptyText() {
	edit_text.setText("");
}

}