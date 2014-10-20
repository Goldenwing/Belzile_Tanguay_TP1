package test;

import model.Connect4Stack;
import model.PlayedToken;

import org.junit.*;

import controller.GameController;

public class ControllerTests
{
	@Test
	public void TestControllerCreation()
	{
		GameController test = new GameController(5, 6);
		
		Assert.assertNotNull(test);
	}
	
	@Test
	public void TestControllerCheckSpaceWhenEmpty()
	{
		GameController test = new GameController(5, 6);
		
		Assert.assertFalse(test.verifyTokenSpace(0));
	}
	
	@Test
	public void TestControllerCheckSpaceWhenFull()
	{
		GameController test = new GameController(5, 8);
		Connect4Stack stack = new Connect4Stack(1);
		stack.pushTest(new PlayedToken(0, 0));
		
		Assert.assertFalse(test.verifyTokenSpace(0));
	}
	
//	@Test
//	public void TestControllerCheckSpaceWithBadIndex()
//	{
//		GameController test = new GameController(5, 2);
//		
//		test.verifyTokenSpace(7);
//	}
	@Test
	public void TestStackAddToken()
	{
		Connect4Stack stackTest = new Connect4Stack(6);
		PlayedToken tokenTest = new PlayedToken(5, 2);
		
		stackTest.pushTest(tokenTest);
		
		Assert.assertEquals(1, stackTest.getNumberStackElements());
		
	}
}
