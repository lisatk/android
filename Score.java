/*
 * Klasse: Score
 * Eier: lisa kjorli
 * Dato endret: 25.11.2013
 * 
 * viser score og printer resultat av alle svar.
 * resultat vises med spørsmål, ditt svar, fasit. du kan bla frem og tilbake og se 
 * hvilke spørsmål du har svart riktig på 
 * feil svar viser rød skrift
 * 
 */
package com.example.fransk;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Score extends Activity 
{
	TextView resultat; 
	TextView score;
	TextView tvSpm;
	TextView tvSvar;
	TextView heading;
	Button frem;
	Button tilbake;
	int antSpmPrint =0;

	String[] ordArray; 
	String[] beskrivelseArray; 	
	ArrayList<Integer> gjettet;
	ArrayList<String> ordklikket;
	String kategori;
	int totalScore;
	String spill;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		getActionBar().setDisplayHomeAsUpEnabled(true);	
		Resources res = getResources();	
		
		//bundle
		Bundle bundle = getIntent().getExtras();
		int scoreInt = bundle.getInt("score");
		totalScore = bundle.getInt("antallSpm");
		gjettet = bundle.getIntegerArrayList("gjettet");
		ordklikket = bundle.getStringArrayList("ordklikket");
		ordArray = bundle.getStringArray("ordA");
		beskrivelseArray  = bundle.getStringArray("beskrivelseA");;	
		kategori = bundle.getString("kategori");	
		spill = bundle.getString("spill");
		
		//LAYOUT
		resultat= (TextView)findViewById(R.id.resultatTxt);
		score = (TextView)findViewById(R.id.scoreTxt);
		TextView tv = (TextView)findViewById(R.id.kategoriHeading);
		tv.setText(kategori);
		
		heading = (TextView)findViewById(R.id.heading);
		tvSpm = (TextView)findViewById(R.id.spm);
		tvSvar = (TextView)findViewById(R.id.svar);
		printSpm(antSpmPrint);
		
		frem = (Button)findViewById(R.id.fremBtn);
		tilbake = (Button)findViewById(R.id.tilbakeBtn);
		
		//print resultat
				if(scoreInt < 0) //1 rikti = 3 . o riktig =5
					scoreInt = totalScore + scoreInt;
				score.setText(scoreInt + "/" + totalScore);
		 
	}	

	public boolean printSpm(int i)
	{
		if(i < totalScore && i > -1)
		{
			String ut = "";
			ut += beskrivelseArray[gjettet.get(i)] + "\n\n";//spm
			ut += "Fasit:\n" + ordArray[ gjettet.get(i)] ;//fasit
			ut += "\n\nDitt svar:";
			
        	heading.setText("Spørsmål " + (i+1) + "\n");
			tvSpm.setText(ut);	
			tvSvar.setText( ordklikket.get(i) );
			
			if(! ordklikket.get(i).equals(ordArray[ gjettet.get(i)] ))
				tvSvar.setTextColor(getResources().getColor(R.color.red));
			return true;			
		}
		else
		 return false;
	}
	
	public void gotoSpillIgjen()
	{

		Intent intent;
		if(spill.equals(MultipleChoiceSpill.TAG))
		{
			intent= new Intent(getApplicationContext(), MultipleChoiceSpill.class);
		}
		else
		{
			intent= new Intent(getApplicationContext(), FyllInnSpill.class);
		}
		intent.putExtra("antallSpm", totalScore);
		intent.putExtra("kategori", kategori);
		startActivity(intent);					
		finish();
	}
	
	//actionbar layout
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.menu_p100, menu);
		return true;
	}
	
	//-----------------LYTTERE----------------//
	
	//actionbar lytter
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId())
    	{ 
    	case R.id.start: gotoSpillIgjen();
    						return true;
        default: finish();
        		 return true;
    	}
	}
	
	//knappe lytter piler
	public void pilClick(View v)
	{
		if( v.getId() == frem.getId() )
		{
			printSpm(++antSpmPrint);
		}
		else
			printSpm(--antSpmPrint);
		
	}
}
