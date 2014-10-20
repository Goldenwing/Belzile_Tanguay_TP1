package observer;

import java.util.ArrayList;
import java.util.List;

import model.PlayedToken;

import observer.IObserver;
import view.View;

public abstract class Subject
{
	List<IObserver> observers = new ArrayList<IObserver>();
	
	public void attach(IObserver view)
	{
		observers.add(view);
	}
	
	public void detatch(View view)
	{
		observers.remove(view);
	}
	
	public void notify(PlayedToken token)
	{
		observers.get(0).Update(token.getColor(), token.getColumnIndex(), token.getColumnPosition());
	}
}
