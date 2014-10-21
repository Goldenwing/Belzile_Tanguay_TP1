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
		
		if (Connect4Stack.getNbTokens() >= (this.view.getNbrRows() * this.view.getNbrColumns())) {
			this.view.endGameWindow("Tie");
		}
		
		boolean isWinner = checkVictory(newToken);
		if ((isWinner) && newToken.getColor().contains("Blue")) {
			this.view.endGameWindow("blueVictory");
			}
		else if ((isWinner) && newToken.getColor().contains("Red")) {
			this.view.endGameWindow("redVictory");
			}
		}
	
	private boolean checkVictory(PlayedToken lastToken)
	{
		int counter = 1;
		
		boolean victoryAchieved = false;
		
		while(victoryAchieved == false && counter < 5)
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
		
		int goodTokensMax = this.view.getNbrSucessiveTokensToWin();
		int goodTokens = 1;
		String goodColor = "";
		if(token.getColor().contains("Red"))
		{
			goodColor = "Red";
		}
		else
			goodColor = "Blue";
		
		//Nous parcourons a gauche en premier, ensuite nous allons vers la droite.
		if(token.getColumnIndex() != 0)
		{
			for(int i = 1; i < token.getColumnIndex() + 1; i++)
			{
				if(!this.modelStackArray[token.getColumnIndex() - i].checkPositionifEmpty(token.getColumnPosition()) && 
						this.modelStackArray[token.getColumnIndex() - i].peekAt(token.getColumnPosition()).getColor().contains(goodColor))
				{
					if(goodTokens == goodTokensMax)
					{
						break;
					}
					else
						goodTokens++;
				}
				else
					break;
			}
		}
		
		if(goodTokens < goodTokensMax)
		{
			int difference = ((this.view.getNbrColumns() - 1) - token.getColumnIndex());
			
			for(int i = 1; i < difference; i++)
			{
				if(!this.modelStackArray[token.getColumnIndex() + i].checkPositionifEmpty(token.getColumnPosition()) && 
						this.modelStackArray[token.getColumnIndex() + i].peekAt(token.getColumnPosition()).getColor().contains(goodColor))
				{
					if(goodTokens == goodTokensMax)
					{
						break;
					}
					else
						goodTokens++;
				}
				else
					break;
			}
		}
		
		if(goodTokens == 4)
			return true;
		else
			return false;
	}
	
	private boolean checkVertical(PlayedToken token)
	{
		
		int goodTokensMax = this.view.getNbrSucessiveTokensToWin();
		int goodTokens = 1;
		String goodColor = "";
		
		if(token.getColor().contains("Red"))
		{
			goodColor = "Red";
		}
		else
			goodColor = "Blue";
		
		int startPosition = token.getColumnPosition();
		if (startPosition >= goodTokensMax - 2) {
			for (int i = startPosition; i >= 1; i--) {
				if (this.modelStackArray[token.getColumnIndex()].peekAt(i - 1).getColor().contains(goodColor)) {
					goodTokens++;
				}
				else {
					break;
				}
			}
		}
		else {
			return false;
		}
		
		if(goodTokens == 4)
			return true;
		else
			return false;
	}
	
	private boolean checkDiagonalUpToDown(PlayedToken token)
	{
		int goodTokensMax = this.view.getNbrSucessiveTokensToWin();
		int goodTokens = 1;
		String goodColor = "";
		if(token.getColor().contains("Red"))
		{
			goodColor = "Red";
		}
		else
			goodColor = "Blue";
		
		//Nous allons en-haut a gauche en premier, ensuite en-bas a droite.
		if(token.getColumnIndex() != 0 && token.getColumnPosition() != this.view.getNbrRows() - 1)
		{
			int difference = this.view.getNbrRows() - token.getColumnPosition();
			for(int i = 1; i < difference; i++)
			{
				if(!this.modelStackArray[token.getColumnIndex() - i].checkPositionifEmpty(token.getColumnPosition() + i) && 
						this.modelStackArray[token.getColumnIndex() - i].peekAt(token.getColumnPosition() + i).getColor().contains(goodColor))
				{
					if(goodTokens == goodTokensMax)
					{
						break;
					}
					else
						goodTokens++;
				}
				else
					break;
			}
		}
		
		if(goodTokens < goodTokensMax)
		{
			int difference = ((this.view.getNbrColumns() - 1) - token.getColumnIndex());
			
			for(int i = 1; i < difference; i++)
			{
				if(!this.modelStackArray[token.getColumnIndex() + i].checkPositionifEmpty(token.getColumnPosition() - i) && 
						this.modelStackArray[token.getColumnIndex() + i].peekAt(token.getColumnPosition() - i).getColor().contains(goodColor))
				{
					if(goodTokens == goodTokensMax)
					{
						break;
					}
					else
						goodTokens++;
				}
				else
					break;
			}
		}
		
		if(goodTokens == 4)
			return true;
		else
			return false;
	}
	
	private boolean checkDiagonalDownToUp(PlayedToken token)
	{
		int goodTokensMax = this.view.getNbrSucessiveTokensToWin();
		int goodTokens = 1;
		String goodColor = "";
		if(token.getColor().contains("Red"))
		{
			goodColor = "Red";
		}
		else
			goodColor = "Blue";
		
		//Nous allons en-bas a gauche en premier, ensuite en-haut a droite.
		if(token.getColumnIndex() != 0 && token.getColumnPosition() != 0)
		{
			for(int i = 1; i < token.getColumnIndex() + 1; i++)
			{
				if(!this.modelStackArray[token.getColumnIndex() - i].checkPositionifEmpty(token.getColumnPosition() - i) && 
						this.modelStackArray[token.getColumnIndex() - i].peekAt(token.getColumnPosition() - i).getColor().contains(goodColor))
				{
					if(goodTokens == goodTokensMax)
					{
						break;
					}
					else
						goodTokens++;
				}
				else
					break;
			}
		}
		
		if(goodTokens < goodTokensMax)
		{
			if(token.getColumnPosition() != this.view.getNbrRows() - 1 && token.getColumnIndex() != this.view.getNbrColumns() - 1)
			{
				int difference = this.view.getNbrRows() - (this.view.getNbrRows() - token.getColumnPosition());
				
				for(int i = 1; i < difference; i++)
				{
					if(!this.modelStackArray[token.getColumnIndex() + i].checkPositionifEmpty(token.getColumnPosition() + i) && 
							this.modelStackArray[token.getColumnIndex() + i].peekAt(token.getColumnPosition() + i).getColor().contains(goodColor))
					{
						if(goodTokens == goodTokensMax)
						{
							break;
						}
						else
							goodTokens++;
					}
					else
						break;
				}
			}
		}
		
		if(goodTokens == 4)
			return true;
		else
			return false;
	}
}
