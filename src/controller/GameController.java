package controller;

import model.GameModel;
import model.PlayedToken;

public class GameController 
{
	GameModel modelGrid;
	public GameController()
	{
		
	}
	
	public void AddToken(String color, int x, int y)
	{
		PlayedToken newToken = new PlayedToken(color, x, y);
		this.modelGrid.add(newToken);
	}
}
