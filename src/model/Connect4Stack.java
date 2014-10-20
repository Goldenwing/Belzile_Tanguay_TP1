package model;
import observer.Subject;
import model.PlayedToken;

public class Connect4Stack extends Subject
{
	private static int nbTokens;
	private int stackSize;
	private final int stackCapacity;
	private Node<PlayedToken> currentNode;
	
	public Connect4Stack(int capacity) 
	{
		Connect4Stack.nbTokens = 0;
		this.stackSize = 0;
		this.stackCapacity = capacity;
		this.currentNode = null;
	}
	
	public int getStackCapacity() 
	{
		return this.stackCapacity;
	}
	public static int getNbTokens()
	{
		return Connect4Stack.nbTokens;
	}
	
	public boolean push(PlayedToken token) 
	{
		if (!this.isFull()) 
		{
			Node<PlayedToken> oldNode = this.currentNode;
			this.currentNode = new Node<PlayedToken>(token, oldNode);
			this.stackSize++;
			Connect4Stack.nbTokens++;
			
			this.notify(this.currentNode.element);
			return true;
		}
		return false;
	}
	
	public boolean pushTest(PlayedToken token) 
	{
		if (!this.isFull()) 
		{
			Node<PlayedToken> oldNode = this.currentNode;
			this.currentNode = new Node<PlayedToken>(token, oldNode);
			this.stackSize++;
			Connect4Stack.nbTokens++;
			return true;
		}
		return false;
	}
	
	public boolean isFull() 
	{
		return (this.stackSize == this.stackCapacity);
	}
	
	public int getNumberStackElements() 
	{
		return this.stackSize;
	}

	public void clear() 
	{
		this.currentNode = null;
		this.stackSize = 0;
	}
	
	public PlayedToken peekAt(int position) 
	{
		if(position >= this.stackCapacity)
		{
			throw new IndexOutOfBoundsException();
		}
		
		PlayedToken peekedElement = null;
		Node<PlayedToken> readerNode = this.currentNode;
		for (int i = this.currentNode.getElement().getColumnPosition(); i > -1; i--) 
		{
			if (readerNode != null) 
			{
				if (i == position) 
				{
					peekedElement = readerNode.getElement();
					break;
				}
				else 
				{
					readerNode = readerNode.getNextNode();
				}
			}
			else
			{
				break;
			}
		}
		return peekedElement;
	}

	
//////////////////////////////////////////////////////////////////////	
	private class Node<T> 
	{
		
		private T element;
		private Node<T> nextNode;
		
		public Node(T element, Node<T> next) 
		{
			this.element = element;
			this.nextNode = next;
		}
		
		public T getElement() 
		{
			return element;
		}
		
		public Node<T> getNextNode() 
		{
			return nextNode;
		}
	}
}


