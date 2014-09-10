/*
 * Klasse: VelgAntallSpill
 * Eier: lisa kjorli
 * Dato endret: 23.11.2013
 * 
 * lar deg velge antall spørsmål som skal stilles. Sender deg videre til valgt spill
 * 
 * 
 */


package com.example.fransk;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View.OnClickListener;

public class VelgAntallSpill extends Activity {

	static final String TAG = "velgAntall";
	Button b1;
	Button b2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_velg_antall_spill);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		b1 = (Button)findViewById(R.id.b1);
		b2 = (Button)findViewById(R.id.b2);

		
		//lytter 
	    OnClickListener valgClickedHandler = new  OnClickListener() {
	    	@Override
	    	public void onClick(View v)
	        {
	    		Intent intent;
	    		Bundle bundle = getIntent().getExtras();
	    		
	        	if (bundle.getString("spill").equals(MultipleChoiceSpill.TAG))//multiple choice
	        		intent= new Intent(getApplicationContext(), MultipleChoiceSpill.class);
	        	else//fyll inn spill
	        		intent= new Intent(getApplicationContext(), FyllInnSpill.class);	        	
	        	
	        	if( v.getId() == b1.getId()  )
	        		intent.putExtra("antallSpm", 5 );
	        	else 
	        		intent.putExtra("antallSpm", 10);
	        
	        	//bundle
	    		intent.putExtra("spill", bundle.getString("spill"));
	        	intent.putExtra("kategori", bundle.getString("kategori"));
	        	startActivity(intent);					
				finish();	
	        }
	    };
	    
	    b1.setOnClickListener(valgClickedHandler);
	    b2.setOnClickListener(valgClickedHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.velg_antall_spill, menu);
		return true;
	}
	//lytter actionbar
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) 
	 {
	
		Intent intent = new Intent(getApplicationContext(), VelgKategori.class);
		intent.putExtra("spill",  getIntent().getExtras().getString("spill"));
		startActivity(intent);
		finish();
		return true;
	 }
		

}
