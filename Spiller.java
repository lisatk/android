/*
 * Eier: lisa kjorli
 * Dato endret: 23.11.2013
 * 
 * 
 *  model opprettes i SpillAktivitet
 */

package com.example.fransk;

import java.util.ArrayList;



	public class Spiller {

		//Spill
		int gjettOrdIndex;//ord som skal gjettes	
		ArrayList<Integer> ordGjettet; //ord som allerede er gjettet		
		int antLiv;
		int score;
		ArrayList<String> ordKlikket;
		
		public Spiller(int liv)
		{
			ordGjettet = new ArrayList<Integer>();
			ordKlikket = new ArrayList<String>(); 
			score = 0;
			antLiv = liv;
		}
		
		//set-metoder
		public void setGjettOrd( int index)
		{		
			gjettOrdIndex = index;
			ordGjettet.add(index);
		}
		public void setOrdKlikket( String s)
		{		
			ordKlikket.add(s);
		}
		
		public void setScore()
		{		
			score++;
		}
		public void setLiv()
		{		
			antLiv--;
		}
		public void mistetScore()
		{
			score--;
		}

		//get-metoder
		public int getGjettOrd()
		{		
			return gjettOrdIndex;	
		}

		public ArrayList<Integer> getOrdGjettet()
		{		
			return ordGjettet;	
		}
				
		public int getLiv()
		{
			return antLiv;
			
		}
		public int getScore()
		{		
			return score;	
		}
		public ArrayList<String> getOrdKlikket()
		{		
			return ordKlikket;
		}

		
			
}
