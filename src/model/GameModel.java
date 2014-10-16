package model;

import observer.Subject;

public class GameModel extends Subject
{
	private int nbTokens;
	
	public GameModel()
	{
		
	}
	
	public void add(PlayedToken token)
	{
		
		//Notify(token);
	}
	
	public int getNbTokens()
	{
		return this.nbTokens;
	}
}
