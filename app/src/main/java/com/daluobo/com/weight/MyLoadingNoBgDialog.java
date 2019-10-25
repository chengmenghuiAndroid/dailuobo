package com.daluobo.com.weight;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.daluobo.com.R;


public class MyLoadingNoBgDialog extends Dialog {

	private Context context;
	private String loadtext;
	private TextView load_txt;
	private ImageView succeed_img;
	private ImageView error_img;
	private ProgressBar progressBar1;
	
    public MyLoadingNoBgDialog(Context context, String txt, int theme) {
        super(context,theme);
        this.context = context;
        this.loadtext = txt;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.loading_nobg_view);
        
        load_txt = (TextView)findViewById(R.id.load_txt);
        load_txt.setText(loadtext);
        
        succeed_img = (ImageView)findViewById(R.id.succeed_img);
        error_img = (ImageView)findViewById(R.id.error_img);
        progressBar1 = (ProgressBar)findViewById(R.id.progressBar1);

    }
    
    public void setSucceedDialog(String txt)
    {
    	load_txt.setText(txt);
    	succeed_img.setVisibility(View.VISIBLE);
    	progressBar1.setVisibility(View.GONE);
    }
    
    public void setErrorDialog(String txt) {
    	load_txt.setText(txt);
    	error_img.setVisibility(View.VISIBLE);
    	progressBar1.setVisibility(View.GONE);
    }
}
