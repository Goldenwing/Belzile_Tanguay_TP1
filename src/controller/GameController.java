package controller;

import model.Connect4Stack;
import model.PlayedToken;

public class GameController 
{
	Connect4Stack[] modelStackArray;
	public GameController(int nbColumns, int nbRows)
	{
		this.modelStackArray = new Connect4Stack[nbColumns];
		
		for(int i = 0; i < nbColumns; i++)
		{
			this.modelStackArray[i] = new Connect4Stack(nbRows);
		}
	}
	
	public boolean verifyTokenSpace(int columnIndex)
	{
		return this.modelStackArray[columnIndex].isFull();
	}
	
	public void addToken(String color, int columnIndex, int columnPosition)
	{
		PlayedToken newToken = new PlayedToken(color, columnIndex, columnPosition);
		this.modelStackArray[columnIndex].push(newToken);
		
		if(Connect4Stack.getNbTokens() >= 7)
		{
			checkVictory(newToken);
		}
		//apres, le model devrait verifier si on a gagner. Ce soir je vais faire l'algo pour verifier.
	}
	
	private boolean checkVictory(PlayedToken lastToken)
	{
		return true;
	}
}
