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

import model.PlayedToken;

import controller.GameController;

import observer.IObserver;

public class View extends JFrame implements IObserver
{
	private static final String IMAGE_PATH = "./IMAGES/Connect4/";

	private static final long serialVersionUID = 1L;

	private JButton[] controlButtons;

	private MyImageContainer[][] placeHolders;

	private GameController gameController;
	
	private final JTextField message = new JTextField(20);
	private final JPanel centerPane = new JPanel();

	private View()
	{
		this.setTitle("Connect4");
		this.gameController = new GameController();

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

	private void initBoard(int nbRows, int nbColumns)
	{
		this.centerPane.removeAll();
		this.placeHolders = new MyImageContainer[nbRows][nbColumns];
		this.controlButtons = new JButton[nbColumns];

		centerPane.setLayout(new GridLayout(nbRows + 1, nbColumns));

		for (int i = 0; i < nbColumns; i++)
		{
			JButton button = new JButton("T");
			this.controlButtons[i] = button;
			button.addActionListener(new ButtonHandler(i));
			centerPane.add(button);
		}

		for (int row = nbRows - 1; row >= 0; row--)
		{
			for (int column = 0; column < nbColumns; column++)
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
	public void Update(String color, int x, int y) 
	{
		String image = "";
		if(color == "red")
		{
			image = "./Images/CONNECT4/Red.jpg"; //path a verifier
		}
		else
		{
			image = "./Images/CONNECT4/Blue.jpg"; //path a verifier
		}
		this.placeHolders[x][y].setImageIcon(new ImageIcon(image));
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
			if(gameController.verifyTokenSpace(columnIndex))
			{
				//gameController.addToken(color, x, y)
			}
			else
			{
				//errorMessage "No space!";
			}
			
			
			System.out.println("Action on button: " + columnIndex);
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
			case "Victory":
			{
				endText = victoryText();
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
            this.initBoard(6, 7);
        }
		else
		{
			System.exit(0);
		}
	}
	
	private String victoryText()
	{
		return "Victoire! vous avez réussi! Félicitations, champion! \n" +
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
	
	public static void main(String[] args)
	{
		// test
		View view = new View();
		view.initBoard(6, 7);
	}

}
