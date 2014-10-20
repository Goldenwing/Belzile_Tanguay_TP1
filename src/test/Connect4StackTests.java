package test;

import org.junit.*;

import model.Connect4Stack;
import model.PlayedToken;

public class Connect4StackTests {

	@Test
	public void newStackOfCapacity3MustBeOfSpecifiedCapacity() {
		Connect4Stack stack = new Connect4Stack(3);
		Assert.assertEquals(3, stack.getStackCapacity());
	}
	
	@Test
	public void newStackMustBeEmpty() {
		Connect4Stack stack = new Connect4Stack(3);
		Assert.assertTrue(stack.getNumberStackElements() == 0);
	}
	
	@Test
	public void pushElementToNonFullStackMustReturnTrue() {
		PlayedToken token = new PlayedToken(0,0);
		Connect4Stack stack = new Connect4Stack(2);
		Assert.assertTrue(stack.pushTest(token));
	}
	
	@Test
	public void pushElementToFullStackMustReturnFalse() {
		PlayedToken token = new PlayedToken(0,0);
		PlayedToken token2 = new PlayedToken(1,1);
		Connect4Stack stack = new Connect4Stack(1);
		stack.pushTest(token);
		Assert.assertFalse(stack.push(token2));
	}
	
	@Test
	public void peekAtElementAtSpecifiedPositionMustReturnGoodElement() {
		Connect4Stack stack = new Connect4Stack(3);
		PlayedToken peekedElement = null;
		PlayedToken token = new PlayedToken(0,0);
		PlayedToken token2 = new PlayedToken(1,1);
		PlayedToken token3 = new PlayedToken(2,2);
		stack.pushTest(token);
		stack.pushTest(token2);
		stack.pushTest(token3);
		
		peekedElement = stack.peekAt(1);
		Assert.assertEquals(token2, peekedElement);
		
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void peekAtElementAtNonExistantPositionMustReturnNull() {
		Connect4Stack stack = new Connect4Stack(3);
		PlayedToken peekedElement = null;
		PlayedToken token = new PlayedToken(0,0);
		PlayedToken token2 = new PlayedToken(1,1);
		PlayedToken token3 = new PlayedToken(2,2);
		stack.pushTest(token);
		stack.pushTest(token2);
		stack.pushTest(token3);
		
		peekedElement = stack.peekAt(6);
	}
	
}
