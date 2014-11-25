package model;
import observer.Subject;
import model.PlayedToken;

public class Connect4Stack extends Subject
{
	
	private int stackSize;
	private final int stackCapacity;
	private Node<PlayedToken> currentNode;
	
	public Connect4Stack(int capacity) 
	{
		this.stackSize = 0;
		this.stackCapacity = capacity;
		this.currentNode = null;
	}

	public boolean push(PlayedToken token) 
	{
		if (!this.isFull()) 
		{
			Node<PlayedToken> oldNode = this.currentNode;
			this.currentNode = new Node<PlayedToken>(token, oldNode);
			this.stackSize++;
		
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
	public boolean checkPositionifEmpty(int position)
	{
		if (this.peekAt(position) != null) 
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	public PlayedToken peekAt(int position) 
	{
		if(position >= this.stackCapacity)
		{
			throw new IndexOutOfBoundsException();
		}
		
		PlayedToken peekedElement = null;
		Node<PlayedToken> readerNode = this.currentNode;
		if (readerNode != null) 
		{
			for (int i = this.currentNode.getElement().getColumnPosition(); i > -1; i--) 
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
		}
		else
			return null;
		
		
		return peekedElement;
	}

	public int getStackCapacity() 
	{
		return this.stackCapacity;
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


