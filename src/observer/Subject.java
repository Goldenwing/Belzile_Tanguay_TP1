package observer;

import java.util.ArrayList;
import java.util.List;

import observer.IObserver;
import view.View;

public abstract class Subject
{
	List<IObserver> observers = new ArrayList<IObserver>();
	
	public void Attach(IObserver view)
	{
		observers.add(view);
	}
	
	public void Detatch(View view)
	{
		observers.remove(view);
	}
	
	public void Notify(String path)
	{
		for(int i = 0; i < observers.size(); i++)
		{
			observers.get(i).Update(path);
		}
	}
}
