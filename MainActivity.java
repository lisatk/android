/*
 * Klasse: MainActivity
 * Eier: lisa kjorli
 * Dato endret: 23.11.2013
 * 
 * hovedaktivitet. 
 * implementerer actionbar og main layout
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





public class MainActivity extends Activity {

	 
	static final String TAG = "Main";
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayShowTitleEnabled(false);
	}
	
	public void gotoVelgKategori()
	{
		Intent velgKategoriIntent = new Intent(this, VelgSpill.class);
		this.startActivity(velgKategoriIntent);
	}
	

	//actionbar layout
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//Actionbar lytter
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{  	
		    	switch (item.getItemId())
		    	{ 

		    	case R.id.om : 
		    				 this.startActivity(new Intent(this, Om.class));
		    				 return true;
		    	case R.id.start: gotoVelgKategori();
		    						return true;
		    	
		    	case R.id.avslutt: 
		    						finish();
		    						return true;
		    	default:
		    						return super.onOptionsItemSelected(item);
		        }
    }
	
}
