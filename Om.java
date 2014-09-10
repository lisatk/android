/*
 * Klasse: Om
 * Eier: lisa kjorli
 * Dato endret: 23.11.2013
 * 
 * klassen viser informasjon om spillet
 * 
 */

package com.example.fransk;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Om extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_om);
		getActionBar().setDisplayHomeAsUpEnabled(true);	
		
	
	}


	//actionbar lytter
		 @Override
		 public boolean onOptionsItemSelected(MenuItem item) 
		 {
			finish();
			return true;
		 }

}
