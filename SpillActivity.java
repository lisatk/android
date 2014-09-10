/*
 * Klasse: SpillActivity
 * Eier: lisa kjorli
 * Dato endret: 27.11.2013
 * 
 * Forelder til multipleChoiceSpill og FyllInnSpill. 
 * inneholder metoder som er felles for alle spill.
 * inneholder Spiller objekt
 * 
 */
package com.example.fransk;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SpillActivity extends Activity
{

	String[] ordArray; 
	String[] beskrivelseArray; 		
	TypedArray progressbarArray;
	Spiller spiller;
	int antSpValgt;
	String kategori;
	
	Resources res;
	TextView progressBar;
	TextView kategoriTV;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);		
		getActionBar().setDisplayShowTitleEnabled(false);
		res = getResources();
		
		//bundle
		antSpValgt = getIntent().getExtras().getInt("antallSpm");		
		kategori =  getIntent().getExtras().getString("kategori");
		
		spiller = new Spiller(antSpValgt);			
		
		
		//arrays
		setKategori(kategori);
		setProgressBar(antSpValgt);		
	}
	
	//actionbar layout
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.menu_p0, menu);
		return true;
	}
	
	public void setKategori(String kategori)
	{
		//array kategori
		if(kategori.equals("kryptering"))
		{
			ordArray = res.getStringArray(R.array.ord_kryptering);
			beskrivelseArray = res.getStringArray(R.array.forklaring_kryptering);
		}
		else if (kategori.equals("sikkerhetsangrep"))
		{
			ordArray = res.getStringArray(R.array.ord_angrep);
			beskrivelseArray = res.getStringArray(R.array.forklaring_angrep);			
		}
		else
		{
			ordArray = res.getStringArray(R.array.ord_sikkerhetstjenester);
			beskrivelseArray = res.getStringArray(R.array.forklaring_sikkerhetstjenester);			
		}		
	}
	
	public void setProgressBar(int pb)
	{
		if(pb == 5)
		  progressbarArray = res.obtainTypedArray(R.array.progressbar_array_5);
		else
		  progressbarArray = res.obtainTypedArray(R.array.progressbar_array_10);
		
	}
	
	public void gotoScore(Intent scoreIntent)
	{
		scoreIntent.putExtra("score", spiller.getScore());
		scoreIntent.putExtra("antallSpm", antSpValgt);
		scoreIntent.putIntegerArrayListExtra("gjettet", spiller.getOrdGjettet());
		scoreIntent.putStringArrayListExtra("ordklikket", spiller.getOrdKlikket());
		scoreIntent.putExtra("ordA", ordArray);
		scoreIntent.putExtra("beskrivelseA", beskrivelseArray);
		scoreIntent.putExtra("kategori", kategori);
		this.startActivity(scoreIntent);
		
	}
	
	public void paintProgressBar()
	{
		Drawable pb_icon = progressbarArray.getDrawable( antSpValgt-spiller.getLiv() );
		progressBar= (TextView)findViewById(R.id.progressBar);
		progressBar.setBackground(pb_icon);
		
	}

	//actionbar lytter
		@Override
		public boolean onOptionsItemSelected(MenuItem item) 
		{
			createDialog();
			//finish();
			return true;
		}
		
		private void createDialog()
		{
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			alertDialogBuilder.setTitle("Avslutt spill").setMessage(("Er du sikker på at du vil avslutte spillet?"));
			
			alertDialogBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener()
			{
				
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					finish();
				}
			});
			
			alertDialogBuilder.setNegativeButton("Avbryt", new DialogInterface.OnClickListener()
			{
				
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					//dialog.cancel();
				}
			});
			
			alertDialogBuilder.setCancelable(false); // Denne gjør at dialogboksen bare forsvinner når man trykker på knappene i dialogboksen. Kan fjernes om du vil.
			alertDialogBuilder.create().show();
		}
	

}
