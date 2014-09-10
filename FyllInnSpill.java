/*
 * Klasse: FyllInnSpill
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
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FyllInnSpill extends SpillActivity 
{	
	static final String TAG = "fyllinnSpill";	
	//Layout
	TextView beskrivelseTxt; 
	TextView input;
	Button sjekkBtn;
	ArrayList<Button> knapper = new ArrayList<Button>();
	ArrayList<String> ekstraBokstaver = new ArrayList<String>();
	Button btnKlikket;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fyll_inn_spill);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);		
				
		//layout
		beskrivelseTxt = (TextView)findViewById(R.id.beskrivelseTxt);
		input = (TextView)findViewById(R.id.ordInput);
		sjekkBtn = (Button)findViewById(R.id.sjekksvarBtn);
		kategoriTV=(TextView)findViewById(R.id.kategoriHeading);
		kategoriTV.setText(kategori);
		
		//knapper
		knapper.add((Button)findViewById(R.id.b1));
		knapper.add((Button)findViewById(R.id.b2));
		knapper.add((Button)findViewById(R.id.b3));
		knapper.add((Button)findViewById(R.id.b4));
		knapper.add((Button)findViewById(R.id.b5));
		knapper.add((Button)findViewById(R.id.b6));
		knapper.add((Button)findViewById(R.id.b7));
		knapper.add((Button)findViewById(R.id.b8));
		knapper.add((Button)findViewById(R.id.b9));
		knapper.add((Button)findViewById(R.id.b10));
			
		startSpill();
	}
		
	//start spill
	public void startSpill()
	{	
		if(spiller.getLiv() > 0 )
		{					
			//velger ett ord som skal gjettes
			int gjettOrdIndex;
			do{
				gjettOrdIndex= new Random().nextInt(ordArray.length);
			}while(spiller.getOrdGjettet().contains(gjettOrdIndex) );		
			spiller.setGjettOrd(gjettOrdIndex);
			spiller.setOrdKlikket(ordArray[gjettOrdIndex]);
			
			//print bokstav knapper
			printBokstavKnapper();
			
			//print beskrivelse av ord
			beskrivelseTxt.setText(beskrivelseArray[gjettOrdIndex]);
			
		}
		else//vis resultat
		{
			Intent scoreIntent = new Intent(this, Score.class);
			scoreIntent.putExtra("spill", this.TAG);
			gotoScore(scoreIntent);
			finish();
		}
	}
	
	
	//print kanpper
	public void printBokstavKnapper()
	{		
		String gjettord = ordArray[ spiller.getGjettOrd() ];
		ArrayList<String> printBokstaver = new ArrayList<String>();
		ArrayList<String> antBokstaverArray = getOrdChar(gjettord);		
		int antChar = antBokstaverArray.size();
		ekstraBokstaver.clear();
		
		if( antChar > knapper.size() )//flere forskellige bokstaver i ord enn knapper 
		{
			for(int i =0; i< knapper.size(); i++)
				  printBokstaver.add(antBokstaverArray.get(i));	
			
			for(int i =knapper.size(); i < antBokstaverArray.size(); i++)
				ekstraBokstaver.add(antBokstaverArray.get(i));	
			
		}
		else if (antChar <= knapper.size())//færre forskjellige bokstaver i orde enn kanpper eller  like mange
		{
			for(int i =0; i< antBokstaverArray.size(); i++)
				  printBokstaver.add(antBokstaverArray.get(i));
			
			//legg til random bokstav
			String []alfabet = getResources().getStringArray(R.array.alfabet_array);
			for(int i =antBokstaverArray.size() ; i < knapper.size(); i++ )
			{
				String s = "";
				do
				{
					s = alfabet[new Random().nextInt(alfabet.length)];					
				}while(printBokstaver.contains(s));
			  printBokstaver.add(s);
			}
		}
		
		//print knapper
		for (int i =0; i < knapper.size(); i++)
		{
			String s =  printBokstaver.get( new Random().nextInt(printBokstaver.size()) );
			printBokstaver.remove(s);
			knapper.get(i).setText(s);
		}
	}
	
	//returener liste med bokstaver som finnes i ord
	public ArrayList<String> getOrdChar(String gjettord)
	{
		ArrayList<String> a = new ArrayList<String>();				
		for(int i =0; i< gjettord.length(); i++)
		{
			String c  = gjettord.charAt(i) + "";
			if (! a.contains(c.toUpperCase())  )
			{
				if(! c.equals(" "))
			        a.add(c.toUpperCase());
			}
		}
		return a;		
	}	
	
	//neste spørsmål
	public void nesteSpill()
	{
		
		//pauser 2 sekunder
		 final Handler handler = new Handler();
		 handler.postDelayed(new Runnable() {
			   @Override
			   public void run() 
			   {
				   input.setTextColor(Color.BLACK);
				   spiller.setLiv();
				   paintProgressBar();					  
				   input.setText("");			   
				   startSpill(); // starter neste spill				   
			   }
			 }, 2000);//2000
	}

	
	
	
	
	//----------------LYTTERE-------------------//
	
	//knappe lytter bokstaver
	public void bokstavClick(View v)
	{
			btnKlikket = (Button) findViewById(v.getId());
			int neste_index = input.getText().length();
			String btnBokstav = btnKlikket.getText().toString();
			String gjettOrd = ordArray[spiller.gjettOrdIndex];			 
			String nesteBokstav = gjettOrd.charAt(neste_index) + "";
			
			if(nesteBokstav.equals(" "))//mellomrom
			{
				input.append(nesteBokstav);			
				nesteBokstav = gjettOrd.charAt(++neste_index) + "";
			}
			
			if(btnBokstav.equals( nesteBokstav.toUpperCase() ))//riktig bokstav klikket
			{
				    btnKlikket.setBackground(getResources().getDrawable(R.drawable.alfabet_btn_background_riktig) );
					btnKlikket.setTextColor(Color.WHITE);		
					input.append(String.valueOf(gjettOrd.charAt(neste_index) ).toUpperCase());
					
					if(input.getText().length() ==  ordArray[spiller.gjettOrdIndex].length())
					 input.setTextColor(getResources().getColor(R.color.greenstartgame));
									
			}
			else//feil bokstav klikket
			{
					btnKlikket.setBackground(getResources().getDrawable(R.drawable.alfabet_btn_background_error));
					btnKlikket.setTextColor(Color.WHITE);			
			}
				
			//pauser 0.3 sekunder
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
		    @Override
			public void run() 
			{				   
				btnKlikket.setBackground(getResources().getDrawable(R.drawable.alfabet_btn_background));
			    btnKlikket.setTextColor(Color.BLACK);
				
			    if(input.getText().length() ==  ordArray[spiller.gjettOrdIndex].length())//neste spill
					nesteSpill();
					
				if(ekstraBokstaver.size() != 0) //ord har flere bokstaver enn knapper 
				{
					//bytter ut bokstav 
					if(! ordInneholderChar(ordArray[spiller.gjettOrdIndex],input.getText().length(), btnKlikket.getText().toString()))
					{
							   btnKlikket.setText( ekstraBokstaver.get(0) );
							   ekstraBokstaver.remove(0);
					 }
					 }
				 }
			}, 200); //0.3
	}	
	
	//sjekker om bokstav klikket finnes i ord etter start indeks
	public boolean ordInneholderChar( String ord, int start, String c )
	{
		for(int i=start; i < ord.length(); i++)
		{
			String s = ord.charAt(i) + "";
			 if ( s.toUpperCase().equals(c) )
			 return true;
		}
		return false;		
	}
	
	public void fasitClick(View v)
	{
		if(! ( spiller.getOrdKlikket().size() == 0  ))
		  spiller.getOrdKlikket().remove(spiller.getOrdKlikket().size() - 1);
		spiller.getOrdKlikket().add("X");
		spiller.mistetScore();
		input.setText(ordArray[spiller.getGjettOrd()]);
		nesteSpill();
		
		
	}
		
	//actionbar lytter
		@Override
		public boolean onOptionsItemSelected(MenuItem item) 
		{
			finish();
			return true;
		}
		
}
	
	

