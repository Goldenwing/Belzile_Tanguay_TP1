package model;

public class PlayedToken 
{
	private int columnIndex;
	private int columnPosition;
	
	private String color;
	
	public PlayedToken(String color, int columnIndex, int columnPosition)
	{
		this.color = color;
		this.columnIndex = columnIndex;
		this.columnPosition = columnPosition;
	}

	public int getColumnIndex() 
	{
		return this.columnIndex;
	}

	public void setColumnIndex(int columnIndex) 
	{
		this.columnIndex = columnIndex;
	}
	
	public int getColumnPosition()
	{
		return this.columnPosition;
	}
	
	public void setColumnPosition(int columnPosition)
	{
		this.columnPosition = columnPosition;
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
