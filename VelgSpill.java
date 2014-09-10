/*
 * Klasse: VelgSpill
 * Eier: lisa kjorli
 * Dato endret: 23.11.2013
 * 
 * lar deg velge kategori. 
 * Sender deg videre til velgKategoriSpill
 * eller du kan klikke deg tilbake til mainActivity som viser startskjerm
 * 
 */

package com.example.fransk;


import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class VelgSpill extends Activity 
{
	static final String TAG = "velgSpill";
	
	Button b1 ;
	Button b2 ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_velg_spill);
		
		b1 = (Button)findViewById(R.id.b1);
		b2 = (Button)findViewById(R.id.b2);
				       
		//lytter 
	    OnClickListener valgClickedHandler = new  OnClickListener() {
	    	
	    	@Override
	    	public void onClick(View v)
	        {
				Intent intent = new Intent(getApplicationContext(), VelgKategori.class) ;
				
	    		if(v.getId() == b1.getId())// mutiple choice valgt
					intent.putExtra("spill", MultipleChoiceSpill.TAG);
				else
					intent.putExtra("spill", FyllInnSpill.TAG);
				
				startActivity(intent);					
				finish();
				
			}
          };
          
        b1.setOnClickListener(valgClickedHandler);
  	    b2.setOnClickListener(valgClickedHandler);
	}
	
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) 
	 {
		finish();
		return true;
	 }
			
	}

