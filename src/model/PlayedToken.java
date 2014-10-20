package model;

public class PlayedToken 
{
	private int columnIndex;
	private int columnPosition;
	private String tokenColorPath;
	
	private static String colorPicker;
	
	public PlayedToken(int columnIndex, int columnPosition)
	{
		this.columnIndex = columnIndex;
		this.columnPosition = columnPosition;
		
		if(PlayedToken.colorPicker == "red")
		{
			this.tokenColorPath = "./images/Blue.png";
			
			PlayedToken.colorPicker = "blue";
		}
		else
		{
			this.tokenColorPath = "./images/Red.png";
			PlayedToken.colorPicker = "red";
		}
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
		return tokenColorPath;
	}

	public void setColor(String color) 
	{
		this.tokenColorPath = color;
	}
	
}
