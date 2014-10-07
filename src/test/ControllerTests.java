package test;

import model.GameModel;
import model.PlayedToken;

import org.junit.Test;
import org.junit.Assert;

import controller.GameController;

public class ControllerTests 
{
	@Test
	public void TestGridPaneBackendCreation()
	{
		GameModel gridTest = new GameModel();
		
		Assert.assertNotNull(gridTest);
	}
	
	@Test
	public void TestGridPaneAddToken()
	{
		GameModel gridTest = new GameModel();
		PlayedToken tokenTest = new PlayedToken("red", 5, 2);
		
		gridTest.add(tokenTest);
		
		Assert.assertEquals(1, gridTest.getNbTokens());
		
	}
}
