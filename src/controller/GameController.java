package controller;

import model.GameModel;
import model.PlayedToken;

public class GameController 
{
	GameModel modelGrid;
	public GameController()
	{
		
	}
	
	public boolean verifyTokenSpace(int columnIndex)
	{
		//return modelGrid.checkspace(columnIndex);
		return true;
	}
	public void addToken(String color, int x, int y)
	{
		PlayedToken newToken = new PlayedToken(color, x, y);
		this.modelGrid.add(newToken);
		//apres, le model devrait verifier si on a gagner. Ce soir je vais faire l'algo pour verifier.
	}
}
