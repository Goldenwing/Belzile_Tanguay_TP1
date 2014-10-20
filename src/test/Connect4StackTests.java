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
		PlayedToken token = new PlayedToken("rouge",0,0);
		Connect4Stack stack = new Connect4Stack(2);
		Assert.assertTrue(stack.push(token));
	}
	
	@Test
	public void pushElementToFullStackMustReturnFalse() {
		PlayedToken token = new PlayedToken("rouge",0,0);
		PlayedToken token2 = new PlayedToken("bleu",1,1);
		Connect4Stack stack = new Connect4Stack(1);
		stack.push(token);
		Assert.assertFalse(stack.push(token2));
	}
	
	@Test
	public void peekAtElementAtSpecifiedPositionMustReturnGoodElement() {
		Connect4Stack stack = new Connect4Stack(3);
		PlayedToken peekedElement = null;
		PlayedToken token = new PlayedToken("rouge",0,0);
		PlayedToken token2 = new PlayedToken("bleu",1,1);
		PlayedToken token3 = new PlayedToken("vert",2,2);
		stack.push(token);
		stack.push(token2);
		stack.push(token3);
		
		peekedElement = stack.peekAt(2);
		Assert.assertEquals(token2, peekedElement);
		
	}
	
	@Test
	public void peekAtElementAtNonExistantPositionMustReturnNull() {
		Connect4Stack stack = new Connect4Stack(3);
		PlayedToken peekedElement = null;
		PlayedToken token = new PlayedToken("rouge",0,0);
		PlayedToken token2 = new PlayedToken("bleu",1,1);
		PlayedToken token3 = new PlayedToken("vert",2,2);
		stack.push(token);
		stack.push(token2);
		stack.push(token3);
		
		peekedElement = stack.peekAt(6);
		Assert.assertEquals(null, peekedElement);
	}
	
}
