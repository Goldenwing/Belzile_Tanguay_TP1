package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import model.PlayedToken;

import controller.GameController;

import observer.IObserver;
import test.Connect4StackTests;
import test.ControllerTests;

public class View extends JFrame implements IObserver
{
	private static final long serialVersionUID = 1L;

	private JButton[] controlButtons;

	private MyImageContainer[][] placeHolders;

	private GameController gameController;
	
	private final JTextField message = new JTextField(20);
	private final JPanel centerPane = new JPanel();
	
	private int nbrRows;
	private int nbrColumns;
	private int nbrSucessiveTokensToWin;
	
	private View()
	{
		this.setTitle("Connect4");

		this.configureWindow();
		
		this.setLayout(new BorderLayout());
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout());
		panelNorth.add(this.message);
		this.message.setEditable(false);
		this.message.setText("Welcome!");
		this.add(panelNorth, BorderLayout.NORTH);
		this.createMenu();
		this.setVisible(true);
	}

	private void createMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		JMenuItem resignMenuItem = new JMenuItem("Resign");
		resignMenuItem.addActionListener(new ResignActionHandler());
		gameMenu.add(resignMenuItem);
		menuBar.add(gameMenu);

		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(new AboutActionHandler());
		helpMenu.add(aboutMenuItem);
		menuBar.add(helpMenu);

		this.setJMenuBar(menuBar);
	}

	private void initBoard(int nbRows, int nbColumns, int nbTokensToWin)
	{
		this.setGameParameters(nbRows, nbColumns, nbTokensToWin);
		this.centerPane.removeAll();
		this.placeHolders = new MyImageContainer[this.nbrRows][this.nbrColumns];
		this.controlButtons = new JButton[this.nbrColumns];

		centerPane.setLayout(new GridLayout(this.nbrRows + 1, this.nbrColumns));
		this.gameController = new GameController(this.nbrRows, this.nbrColumns, this);
		for (int i = 0; i < this.nbrColumns; i++)
		{
			JButton button = new JButton("T");
			this.controlButtons[i] = button;
			button.addActionListener(new ButtonHandler(i));
			centerPane.add(button);
		}

		for (int row = this.nbrRows - 1; row >= 0; row--)
		{
			for (int column = 0; column < this.nbrColumns; column++)
			{
				MyImageContainer button = new MyImageContainer();
				button.setOpaque(true);
				placeHolders[row][column] = button;
				centerPane.add(button);
			}
		}
		this.add(centerPane, BorderLayout.CENTER);
		this.revalidate();
	}

	private void configureWindow()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(((screenSize.width * 3) / 6), ((screenSize.height * 4) / 7));
		setLocation(((screenSize.width - getWidth()) / 2), ((screenSize.height - getHeight()) / 2));
	}
	
	@Override
	public void Update(String colorPath, int columnIndex, int columnPosition) 
	{
		if(colorPath.contains("Red"))
		{
			View.this.message.setText("C'est au tour du joueur bleu!");
		}
		else
		{
			View.this.message.setText("C'est au tour du joueur rouge!");
		}
		
		this.placeHolders[columnPosition][columnIndex].setImageIcon(new ImageIcon(colorPath));
	}

	private class ButtonHandler implements ActionListener
	{
		private final int columnIndex;

		private ButtonHandler(int columnIndex)
		{
			this.columnIndex = columnIndex;
		}

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(!gameController.verifyTokenSpace(columnIndex))
			{
				gameController.addToken(columnIndex);
			}
			else
			{
				View.this.message.setText("Cette rangee est pleine!"); 
			}
		}
	}

	private class ResignActionHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			endGameWindow("Defeat");
		}
	}

	private class AboutActionHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			JOptionPane.showMessageDialog(View.this, "GUI for Connect4\n420-520-SF TP1\n\nAuthor: François Gagnon", "About", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void endGameWindow(String condition)
	{
		String endText = "";
		switch(condition)
		{
			case "blueVictory":
			{
				endText = blueVictoryText();
				break;
			}
			
			case "redVictory":
			{
				endText = redVictoryText();
				break;
			}
			
			case "Defeat":
			{
				endText = defeatText();
				break;
			}
			
			case "Tie":
			{
				endText = tieText();
				break;
			}
		}
		
		int userInput = JOptionPane.showConfirmDialog(null, endText, "Game Over", JOptionPane.YES_NO_OPTION);
		
		if (userInput == JOptionPane.YES_OPTION)
        {
			this.initBoard(this.nbrRows, this.nbrColumns, this.nbrSucessiveTokensToWin);
        }
		else
		{
			System.exit(0);
		}
	}
	
	private String redVictoryText()
	{
		return "Victoire! Les Rouges ont réussi! Félicitations, champion! \n" +
				"Voulez-vous rejouer?";
	}
	
	private String blueVictoryText()
	{
		return "Victoire! Les Bleus ont réussi! Félicitations, champion! \n" +
				"Voulez-vous rejouer?";
	}
	
	private String defeatText()
	{
		return "Oh... c'est la défaite. Meilleure chance la prochaine fois, mon ami.\n" +
				"Voulez-vous rejouer?";
	}
	
	private String tieText()
	{
		return "Égalité! Le tableau est plein! Refaites le combat pour gagner! \n" +
				"Voulez-vous rejouer?";

	}
	
	void setGameParameters(int rows, int columns, int nbrSucessiveTokens) 
	{
		this.nbrRows = rows;
		this.nbrColumns = columns;
		this.nbrSucessiveTokensToWin = nbrSucessiveTokens;
	}
	
	public int getNbrSucessiveTokensToWin() {
		return nbrSucessiveTokensToWin;
	}
	
	public int getNbrColumns(){
		return this.nbrColumns;
	}
	
	public int getNbrRows(){
		return this.nbrRows;
	}
	
	public static void main(String[] args)
	{
		JUnitCore junit = new JUnitCore();
		Result result1 = junit.run(Connect4StackTests.class);
		Result result2 = junit.run(ControllerTests.class);
		if (result1.getFailureCount() != 0 || result2.getFailureCount() != 0) {
			System.out.println("Erreur dans les tests. Fin du programme.");
			System.exit(1);
		}
		
		View view = new View();
		
		int nbrRows = 0;
		int nbrColumns = 0;
		int nbrSucessiveTokensToWin = 0;
		
		try {
			nbrRows = Integer.parseInt(args[0]);
			nbrColumns = Integer.parseInt(args[1]);
			nbrSucessiveTokensToWin = Integer.parseInt(args[2]);
		}
		catch (NumberFormatException e) {
	        System.err.println("Un des parametres n'est pas un entier. Fin du programme.");
	        System.exit(1);
		}
		
		view.initBoard(nbrRows, nbrColumns, nbrSucessiveTokensToWin);
	}

}
