package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndGame
{
	public EndGame(String condition) 
	{
		JFrame finishFrame = new JFrame("C'est la fin!");
		finishFrame.setSize(300, 300);
		
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setSize(300, 300);
		
		JLabel finishText = new JLabel();
		finishText.setLocation(10, 10);
		
		switch(condition)
		{
			case "Victory":
			{
				finishText.setText(victoryText());
				break;
			}
			
			case "Defeat":
			{
				finishText.setText(defeatText());
				break;
			}
			
			case "Tie":
			{
				finishText.setText(tieText());
				break;
			}
		}
		
		contentPane.add(finishText);
		
		JButton replay = new JButton("Rejouer");
		replay.setActionCommand("replay");
		replay.setSize(100, 50);
		replay.setLocation(25, 200);
		replay.addActionListener(new ReplayListener());
		contentPane.add(replay);
		
		JButton quit = new JButton("Quitter");
		quit.setActionCommand("replay");
		quit.setSize(100, 50);
		quit.setLocation(125, 200);
		quit.addActionListener(new QuitListener());
		contentPane.add(quit);
		
		finishFrame.setContentPane(contentPane);
		
		finishFrame.setLayout(null);
		finishFrame.setVisible(true);
		
	}
	
	private String victoryText()
	{
		return "Victoire! vous avez réussi! Félicitations, champion!";
	}
	
	private String defeatText()
	{
		return "Oh... c'est la défaite. Meilleure chance la prochaine fois, mon ami.";
	}
	
	private String tieText()
	{
		return "Égalité! Le tableau est plein! Refaites le combat pour gagner!";
	}
	
	private class ReplayListener  implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
		}
	}
	
	private class QuitListener  implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
		}
	}
}
