package observer;

import model.PlayedToken;

public interface IObserver 
{
	public void Update(String color, int x, int y);
}
