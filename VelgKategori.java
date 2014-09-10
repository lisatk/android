/*
 * Klasse: VelgKategori
 * Eier: lisa kjorli
 * Dato endret: 23.11.2013
 * 
 * lar deg velge kategori. 
 * Sender deg videre til velgAntallSpill
 * eller du kan klikke deg tilbake til velgSpill
 * 
 * 
 */
package com.example.fransk;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class VelgKategori extends Activity {
	
	static final String TAG = "velgKategori";
	Button b1;
	Button b2;
	Button b3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_velg_kategori);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		b1 = (Button)findViewById(R.id.b1);
		b2 = (Button)findViewById(R.id.b2);
		b3 =  (Button)findViewById(R.id.b3);
		
		//lytter 
	    OnClickListener valgClickedHandler = new  OnClickListener() {
	    	@Override
	    	public void onClick(View v)
	        {    		
				Intent intent = new Intent(getApplicationContext(), VelgAntallSpill.class);
	        	intent.putExtra("spill", getIntent().getExtras().getString("spill"));
	        	
	        	if (v.getId() == b1.getId() )
	        		intent.putExtra("kategori", "kryptering");
	        	else if (v.getId() == b2.getId() )
	        		intent.putExtra("kategori", "sikkerhetsangrep");	        	
	        	else
	        		intent.putExtra("kategori", "sikkerhetstjenester");
	        			        
	        	startActivity(intent);					
				finish();	
	        }
	    };
	    b1.setOnClickListener(valgClickedHandler);
  	    b2.setOnClickListener(valgClickedHandler);
  	    b3.setOnClickListener(valgClickedHandler);
	}

	@Override
	 public boolean onOptionsItemSelected(MenuItem item) 
	 {
		Intent intent = new Intent(getApplicationContext(), VelgSpill.class);
		startActivity(intent);
		
		finish();
		return true;
	 }

}
