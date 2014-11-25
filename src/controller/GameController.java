package controller;

import view.View;
import model.GameGrid;
import model.PlayedToken;

public class GameController 
{
	GameGrid modelStackArray;
	View view;
	
	public GameController(int nbRows, int nbColumns, View view)
	{
		this.view = view;
		
		this.modelStackArray = new GameGrid(nbColumns, nbRows);
		
		this.modelStackArray.attach(this.view);
		
	}
	
	public GameController(int nbColumns, int nbRows)
	{	
		//Pour les tests
		this.modelStackArray = new GameGrid(nbColumns, nbRows);
	}
	
	public boolean verifyTokenSpace(int columnIndex)
	{
		return this.modelStackArray.isFull(columnIndex);
	}
	
	public void addToken(int columnIndex)
	{
		int columnPosition = this.modelStackArray.getNumberStackElements(columnIndex);
		
		PlayedToken newToken = new PlayedToken(columnIndex, columnPosition);
		this.modelStackArray.push(newToken, columnIndex);
		
		if (GameGrid.getNbTokens() >= (this.view.getNbrRows() * this.view.getNbrColumns())) {
			this.view.endGameWindow("Tie");
		}
		
		if(GameGrid.getNbTokens() >= 7)
		{
			boolean isWinner = checkVictory(newToken);
			if ((isWinner) && newToken.getColor().contains("Blue")) {
				this.view.endGameWindow("blueVictory");
				}
			else if ((isWinner) && newToken.getColor().contains("Red")) {
				this.view.endGameWindow("redVictory");
				}
			}
		}
		
	
	private boolean checkVictory(PlayedToken lastToken)
	{
		int goodTokensMax = this.view.getNbrSucessiveTokensToWin();
		String goodColor = "";
		if(lastToken.getColor().contains("Red"))
		{
			goodColor = "Red";
		}
		else
			goodColor = "Blue";
		
		int counter = 1;
		
		boolean victoryAchieved = false;
		
		while(victoryAchieved == false && counter < 5)
		{
			switch(counter)
			{
				case 1: 
				{
					victoryAchieved = checkHorizontal(lastToken, goodTokensMax, goodColor);
					break;
				}
				case 2:
				{
					victoryAchieved = checkVertical(lastToken, goodTokensMax, goodColor);
					break;
				}
				case 3:
				{
					victoryAchieved = checkDiagonalUpToDown(lastToken, goodTokensMax, goodColor);
					break;
				}
				case 4:
				{
					victoryAchieved = checkDiagonalDownToUp(lastToken, goodTokensMax, goodColor);
					break;
				}
			}
			counter++;
		}

		return victoryAchieved;
	}
	
	private boolean checkHorizontal(PlayedToken token, int goodTokensMax, String goodColor)
	{	
		int goodTokens = 0;
		int columnIndex = token.getColumnIndex();
		int columnPosition = token.getColumnPosition();
		
		
		goodTokens = checkLeft(columnIndex, columnPosition, goodTokens, goodColor);
		
		if(goodTokens < goodTokensMax)
		{
			columnIndex = token.getColumnIndex() + 1;
			goodTokens = checkRight(columnIndex, columnPosition, goodTokens, goodColor);
		}
		
		if(goodTokens == 4)
			return true;
		else
			return false;
	}
	
	private int checkLeft(int columnIndex, int columnPosition, int goodTokens, String goodColor)
	{
		while(columnIndex >= 0 && checkSpace(columnIndex, columnPosition, goodColor))
		{
			goodTokens++;
			
			if(goodTokens == this.view.getNbrSucessiveTokensToWin())
			{
				break;
			}
			
			columnIndex--;
		}
		
		return goodTokens;
	}
	
	private int checkRight(int columnIndex, int columnPosition, int goodTokens, String goodColor)
	{
		while(columnIndex <= this.view.getNbrColumns() - 1  && checkSpace(columnIndex, columnPosition, goodColor))
		{
			goodTokens++;
				
			if(goodTokens == this.view.getNbrSucessiveTokensToWin())
			{
				break;
			}
			
			columnIndex++;
		}
		
		return goodTokens;
	}
	
	private boolean checkVertical(PlayedToken token, int goodTokensMax, String goodColor)
	{
		int goodTokens = 1;
		
		int columnPosition = token.getColumnPosition();
		
		if (columnPosition >= goodTokensMax - 1)
		{
			for (int i = columnPosition; i >= 1; i--) 
			{
				if (this.modelStackArray.peekAt(i - 1, token.getColumnIndex()).getColor().contains(goodColor)) 
				{
					goodTokens++;
				}
				else 
				{
					break;
				}
			}
		}
		else 
		{
			return false;
		}
		
		if(goodTokens == 4)
			return true;
		else
			return false;
	}
	
	private boolean checkDiagonalUpToDown(PlayedToken token, int goodTokensMax, String goodColor)
	{
		int columnIndex = token.getColumnIndex();
		int columnPosition = token.getColumnPosition();
		
		int goodTokens = 0;
		
		goodTokens = checkUpLeft(columnIndex, columnPosition, goodTokens, goodColor);
		
		if(goodTokens < goodTokensMax)
		{		
			columnIndex = token.getColumnIndex() + 1;
			columnPosition = token.getColumnPosition() - 1;

			goodTokens = checkDownRight(columnIndex, columnPosition, goodTokens, goodColor);
		}
		
		if(goodTokens == 4)
			return true;
		else
			return false;
	}
	
	private int checkUpLeft(int columnIndex, int columnPosition, int goodTokens, String goodColor)
	{
		while(columnIndex >= 0 && columnPosition != this.view.getNbrRows() - 1  && checkSpace(columnIndex, columnPosition, goodColor))
		{
			goodTokens++;
					
			if(goodTokens == this.view.getNbrSucessiveTokensToWin())
			{
				break;
			}
			
			columnIndex--;
			columnPosition++;
		}
		
		return goodTokens;
	}
	
	private int checkDownRight(int columnIndex, int columnPosition, int goodTokens, String goodColor)
	{
		while(columnIndex <= this.view.getNbrColumns() - 1  && checkSpace(columnIndex, columnPosition, goodColor))
		{
			goodTokens++;
					
			if(goodTokens == this.view.getNbrSucessiveTokensToWin())
			{
				break;
			}
			
			columnIndex++;
			columnPosition--;
		}
		return goodTokens;
	}
	
	private boolean checkDiagonalDownToUp(PlayedToken token, int goodTokensMax, String goodColor)
	{
		int columnIndex = token.getColumnIndex();
		int columnPosition = token.getColumnPosition();
		int goodTokens = 0;
		
		goodTokens = checkDownLeft(columnIndex, columnPosition, goodTokens, goodColor);
		
		if(goodTokens < goodTokensMax)
		{
			columnIndex = token.getColumnIndex() + 1;
			columnPosition = token.getColumnPosition() + 1;

			goodTokens = checkUpRight(columnIndex, columnPosition, goodTokens, goodColor);
		}
		
		if(goodTokens == 4)
			return true;
		else
			return false;
	}
	
	private int checkDownLeft(int columnIndex, int columnPosition, int goodTokens, String goodColor)
	{
		while(columnIndex >= 0 && columnPosition >= 0 && checkSpace(columnIndex, columnPosition, goodColor))
		{	
			goodTokens++;
		
			if(goodTokens == this.view.getNbrSucessiveTokensToWin())
			{
				break;
			}
			
			columnIndex--;
			columnPosition--;
		}
		
		return goodTokens;
	}
	
	private int checkUpRight(int columnIndex, int columnPosition, int goodTokens, String goodColor)
	{
		while(columnIndex <= this.view.getNbrColumns() - 1 && columnPosition <= this.view.getNbrRows() - 1 && checkSpace(columnIndex, columnPosition, goodColor))
		{				
			goodTokens++;
					
			if(goodTokens == this.view.getNbrSucessiveTokensToWin())
			{
				break;
			}
			
			columnIndex++;
			columnPosition++;
		}
		return goodTokens;
	}
	
	private boolean checkSpace(int columnIndex, int columnPosition, String goodColor)
	{
		if(!this.modelStackArray.checkPositionifEmpty(columnPosition, columnIndex))
		{
			if(this.modelStackArray.peekAt(columnPosition, columnIndex).getColor().contains(goodColor))
			{
				return true;
			}
		}
		return false;
	}

}

