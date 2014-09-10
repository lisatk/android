/*
 * Klasse: MultipleChoiceSpill
 * Eier: lisa kjorli
 * Dato endret: 23.11.2013
 * 
 * barn til SpillActivity.
 * starter spill og oppdaterer spiller og layout  
 * 
 * 
 */

package com.example.fransk;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MultipleChoiceSpill extends SpillActivity 
{	
	static final String TAG = "mcSpill";	
	//Layout
	ArrayList<Button> knapper = new ArrayList<Button>();
	TextView beskrivelseTxt;  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiple_choice);	
		
		Button bildeEn= (Button)findViewById(R.id.r1c1);
		Button bildeTo= (Button)findViewById(R.id.r1c2);
		Button bildeTre= (Button)findViewById(R.id.r2c1);
		Button bildeFire = (Button)findViewById(R.id.r2c2);
		knapper.add(bildeEn);
		knapper.add(bildeTo);
		knapper.add(bildeTre);
		knapper.add(bildeFire);
		beskrivelseTxt = (TextView)findViewById(R.id.beskrivelseTxt);
		kategoriTV=(TextView)findViewById(R.id.kategoriHeading);
		kategoriTV.setText(kategori);
		startSpill();	
	}
	
	
	//Actionbar
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
			getMenuInflater().inflate(R.menu.menu_p0, menu);
			return true;
	}

	
	//start spill	
	public void startSpill()
	{	
		
		if ( spiller.getLiv() > 0 )//spill er ikke ferdig
		{		
			//tegner hvit bakgrunn på knapper
			for(int i=0; i < knapper.size(); i++)
			{
				knapper.get(i).setBackground(getResources().getDrawable(R.drawable.alfabet_btn_background));
				knapper.get(i).setTextColor(Color.BLACK);
				knapper.get(i).setEnabled(true);
			}	
			
			// 4 ord
			ArrayList<Integer> knapperTxt = new ArrayList<Integer>(); // ord i knapper
			knapperTxt = getFireRandomWords();
		
			//print ord i knappene
			for(int i = 0; i < knapper.size(); i++ )
			{
				String btnTxt = ordArray[knapperTxt.get(i)] ;
				knapper.get(i).setText(btnTxt);		
			}
		
			//velger ett ord som skal gjettes
			int gjettOrdIndex=  knapperTxt.get( new Random().nextInt( knapperTxt.size() ));			
			do{
				gjettOrdIndex=  knapperTxt.get( new Random().nextInt( knapperTxt.size() ));
			}while(spiller.getOrdGjettet().contains(gjettOrdIndex) );	
			spiller.setGjettOrd(gjettOrdIndex);
			//.add(gjettOrdIndex);
		
			//print beskrivelse av ord
			beskrivelseTxt.setText(beskrivelseArray[gjettOrdIndex]);
							
		}
		else// viser resultat
		{	
			Intent scoreIntent = new Intent(this, Score.class);
			scoreIntent.putExtra("spill", this.TAG);
			gotoScore(scoreIntent);
			finish();
		}
	}
	
	//returnerer 4 random ord
	public ArrayList<Integer> getFireRandomWords()
	{
			ArrayList<Integer> spillerOrd = new ArrayList<Integer>();
			int index;
			
			//4 ord-knapper
			for(int i = 0; i < knapper.size(); i++ )
			{
				//finn ord
				do
				{
					index = new Random().nextInt(ordArray.length);
				}while(spillerOrd.contains(index) );
				
				//legg til ord
				spillerOrd.add(index);
									
			}
			return spillerOrd;			
	}
	
	//enabler alle knapper
	public void enableKnapper()
	{
		for(int i=0; i < knapper.size(); i++)
		{
			knapper.get(i).setEnabled(false);
		}
		
	}
	
	//-----------------LYTTER-------------------------//
	
	//4 ord knapper  lytter
		public void ordKlikket(View v)
		{
					
			 Button btnKlikket = (Button) findViewById(v.getId());
			 String btn = btnKlikket.getText().toString();
			 String riktigOrd = ordArray[spiller.getGjettOrd()];
			 
			 if (btn.equals(riktigOrd) ) //riktig ord klikket
			 {
				 btnKlikket.setBackground(getResources().getDrawable(R.drawable.alfabet_btn_background_riktig));
				 btnKlikket.setTextColor(Color.WHITE);
				 spiller.setScore();	
			 }
			 else//feil ord klikket
			 {
				 btnKlikket.setBackground(getResources().getDrawable(R.drawable.alfabet_btn_background_error));
				 btnKlikket.setTextColor(Color.WHITE);
				 
				 //finn riktig ord
				 int i = -1;
				 do
				 {
					 i++;		
			      }while( ! knapper.get(i).getText().equals(riktigOrd) );
				 //print grønn på riktig ord
				 knapper.get(i).setBackground(getResources().getDrawable(R.drawable.alfabet_btn_background_riktig));	
				 knapper.get(i).setTextColor(Color.WHITE);
			 }
			
			 spiller.setOrdKlikket(btn);
			 spiller.setLiv();
			 enableKnapper();
			 
			 
			 //pauser 2 sekunder
			 final Handler handler = new Handler();
			 handler.postDelayed(new Runnable() {
			   @Override
			   public void run() {
				   
					//tegner progressbar
					paintProgressBar();	
					// starter neste spill
					startSpill();
			   }
			 }, 1000);//2000
		
		}
		 
}
