package test;

import model.Connect4Stack;
import model.PlayedToken;

import org.junit.*;

public class ControllerTests
{
	@Test
	public void TestStackBackendCreation()
	{
		Connect4Stack stackTest = new Connect4Stack(6);
		
		Assert.assertNotNull(stackTest);
	}
	
	@Test
	public void TestStackAddToken()
	{
		Connect4Stack stackTest = new Connect4Stack(6);
		PlayedToken tokenTest = new PlayedToken(5, 2);
		
		stackTest.push(tokenTest);
		
		Assert.assertEquals(1, stackTest.getNumberStackElements());
		
	}
}
