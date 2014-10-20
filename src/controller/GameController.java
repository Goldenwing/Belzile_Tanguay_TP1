package controller;

import view.View;
import model.Connect4Stack;
import model.PlayedToken;

public class GameController 
{
	Connect4Stack[] modelStackArray;
	View view;
	
	public GameController(int nbColumns, int nbRows, View view)
	{
		this.view = view;
		
		this.modelStackArray = new Connect4Stack[nbColumns];
		
		for(int i = 0; i < nbColumns; i++)
		{
			this.modelStackArray[i] = new Connect4Stack(nbRows);
			this.modelStackArray[i].attach(this.view);
		}
	}
	
	public boolean verifyTokenSpace(int columnIndex)
	{
		return this.modelStackArray[columnIndex].isFull();
	}
	
	public void addToken(int columnIndex)
	{
		int columnPosition = this.modelStackArray[columnIndex].getNumberStackElements();
	
		PlayedToken newToken = new PlayedToken(columnIndex, columnPosition);
		this.modelStackArray[columnIndex].push(newToken);
		
		if(Connect4Stack.getNbTokens() >= 7)
		{
			checkVictory(newToken);
		}
		
	}
	
	private boolean checkVictory(PlayedToken lastToken)
	{
		boolean victoryAchieved = false;
		boolean checkedAllDirections = false;
		
		while(victoryAchieved = false || checkedAllDirections == false)
		{
			
		}
		//victoryAchieved = checkDown(columnIndex, columnPosition+1);
		//victoryAchieved = checkLeft(columnIndex -1, columnPosition);
		//victoryAchieved = checkRight(columnIndex +1, columnPosition);
		//victoryAchieved = checkUpLeft(columnIndex-1, columnPosition -1);
		//victoryAchieved = checkUpRight(columnIndex+1, columnPosition -1);
		//victoryAchieved = checkDownLeft(columnIndex-1, columnPosition + 1);
		//victoryAchieved = checkDownRight(columnIndex +1, column Position +1);
		return true;
	}
}
