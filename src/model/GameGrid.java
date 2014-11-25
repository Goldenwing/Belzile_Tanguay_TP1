package model;

import observer.Subject;

public class GameGrid extends Subject
{
	private Connect4Stack[] gridStack;
	private int nbRows;
	private static int nbTokens;
	
	public GameGrid()
	{
		this.nbRows = 7;
		
		this.gridStack = new Connect4Stack[7];
		for(int i = 0; i < 7; i++)
		{
			this.gridStack[i] = new Connect4Stack(6);
		}
	}
	
	public GameGrid(int nbColumns, int nbRows)
	{
		GameGrid.nbTokens = 0;
		this.nbRows = nbRows;
		
		this.gridStack = new Connect4Stack[nbColumns];
		
		for(int i = 0; i < nbColumns; i++)
		{
			this.gridStack[i] = new Connect4Stack(nbRows);
		}
		
	}

	public int getStackCapacity() 
	{
		return this.nbRows;
	}
	
	public boolean isFull(int index) 
	{
		return this.gridStack[index].isFull();
	}
	
	public int getNumberStackElements(int index) 
	{
		return this.gridStack[index].getNumberStackElements();
	}
	
	public void clear()
	{
		for(int i = 0; i <this.gridStack.length; i++)
		{
			this.gridStack[i].clear();
		}
		
		GameGrid.nbTokens = 0;
		
	}
	
	public static int getNbTokens()
	{
		return GameGrid.nbTokens;
	}
	
	public boolean push(PlayedToken token, int position)
	{
		if(this.gridStack[position].push(token))
		{
			GameGrid.nbTokens++;
			notify(token);
			return true;
		}
		else
			return false;
	}
	
	public PlayedToken peekAt(int position, int index) 
	{ 
		return this.gridStack[index].peekAt(position);
	}
	
	public boolean checkPositionifEmpty(int position, int index)
	{
		return this.gridStack[index].checkPositionifEmpty(position);
	}

}
