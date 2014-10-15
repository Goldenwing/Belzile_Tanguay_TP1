package model;

public class PlayedToken 
{
	private int positionX;
	private int positionY;
	
	private String color;
	
	public PlayedToken(String color, int x, int y)
	{
		this.color = color;
		this.positionX = x;
		this.positionY = y;
	}

	public int getPositionX() 
	{
		return positionX;
	}

	public void setPositionX(int positionX) 
	{
		this.positionX = positionX;
	}

	public int getPositionY() 
	{
		return positionY;
	}

	public void setPositionY(int positionY) 
	{
		this.positionY = positionY;
	}

	public String getColor() 
	{
		return color;
	}

	public void setColor(String color) 
	{
		this.color = color;
	}
	
}
