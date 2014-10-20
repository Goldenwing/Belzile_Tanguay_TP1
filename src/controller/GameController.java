package controller;

import view.View;
import model.Connect4Stack;
import model.PlayedToken;

public class GameController 
{
	Connect4Stack[] modelStackArray;
	View view;
	
	public GameController(int nbRows, int nbColumns, View view)
	{
		this.view = view;
		
		this.modelStackArray = new Connect4Stack[nbColumns];
		
		for(int i = 0; i < nbColumns; i++)
		{
			this.modelStackArray[i] = new Connect4Stack(nbRows);
			this.modelStackArray[i].attach(this.view);
		}
	}
	
	public GameController(int nbColumns, int nbRows)
	{	
		//Pour les tests
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
		int counter = 0;
		
		boolean victoryAchieved = false;
		
		while(victoryAchieved = false)
		{
			switch(counter)
			{
				case 1: 
				{
					victoryAchieved = checkHorizontal(lastToken);
					break;
				}
				case 2:
				{
					victoryAchieved = checkVertical(lastToken);
					break;
				}
				case 3:
				{
					victoryAchieved = checkDiagonalUpToDown(lastToken);
					break;
				}
				case 4:
				{
					victoryAchieved = checkDiagonalDownToUp(lastToken);
					break;
				}
			}
			counter++;
		}

		return victoryAchieved;
	}
	
	private boolean checkHorizontal(PlayedToken token)
	{
		int goodTokens = 1;
		String goodColor = "";
		if(token.getColor().contains("Red"))
		{
			goodColor = "Red";
		}
		else
			goodColor = "Blue";
		
		if(token.getColumnIndex() != 0)
		{
			for(int i = 0; i < 3; i++)
			{
				if(this.modelStackArray[token.getColumnIndex() - i].peekAt(token.getColumnPosition()).getColor().contains(goodColor))
				{
					goodTokens++;
				}
				else
					break;
			}
		}
		//victoryAchieved = checkLeft(columnIndex -1, columnPosition);
		//victoryAchieved = checkRight(columnIndex +1, columnPosition);
		return false;
	}
	
	private boolean checkVertical(PlayedToken token)
	{
		int goodTokens = 0;
		String goodColor = "";
		if(token.getColor().contains("Red"))
		{
			goodColor = "red";
		}
		else
			goodColor = "blue";
		//victoryAchieved = checkDown(columnIndex, columnPosition+1);
		return false;
	}
	
	private boolean checkDiagonalUpToDown(PlayedToken token)
	{
		int goodTokens = 0;
		String goodColor = "";
		if(token.getColor().contains("Red"))
		{
			goodColor = "red";
		}
		else
			goodColor = "blue";
		//victoryAchieved = checkUpLeft(columnIndex-1, columnPosition -1);
		//victoryAchieved = checkDownRight(columnIndex +1, column Position +1);
		return false;
	}
	
	private boolean checkDiagonalDownToUp(PlayedToken token)
	{
		int goodTokens = 0;
		String goodColor = "";
		if(token.getColor().contains("Red"))
		{
			goodColor = "red";
		}
		else
			goodColor = "blue";
		//victoryAchieved = checkUpRight(columnIndex+1, columnPosition -1);
		//victoryAchieved = checkDownLeft(columnIndex-1, columnPosition + 1);
		
		return true;
	}
}
