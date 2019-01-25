import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
//import com.sun.speech.freetts.*;

public class BingoGUI {
	private static int timerChange = 0;
	private static Timer timer = new Timer(4000, new Change());
	private static JLabel CallBingo = new JLabel("Get Ready To Play Bingo!");
	private static String[] callBoard = callBoardValues();
	private static JPanel callBoardPanel = new JPanel();
	private static JLabel callBoardLabels[] = new JLabel[76];
	private static JButton playerBoard[][] = playerBoard();
	private static JButton playerBoard2[][] = playerBoard();
	private static JLabel checkWinner[][] = new JLabel[5][5];
	private static JLabel checkWinnerCol[][] = new JLabel[5][5];
	private static JLabel cpuBoard[][] = cpuBoard();
	private static JLabel cpuBoard2[][] = cpuBoard();
	private static JPanel panel1 = new JPanel();
	private static JPanel panel2 = new JPanel();
	private static JPanel panel3 = new JPanel();
	private static JPanel panel4 = new JPanel();
	private static JPanel mainPanel = new JPanel();
	private static String letter1 = "B";
	private static String letter2 = "I";
	private static String letter3 = "N";
	private static String letter4 = "G";
	private static String letter5 = "O";
	private static int rngBingo = 0;
	private static JComboBox Files;
	private static JPanel filePanel = new JPanel();
	private static JFrame frame = new JFrame("BINGO GUI");
	private static JPanel playerMotherPanel = new JPanel();
	private static JPanel cpuMotherPanel = new JPanel();
	private static JMenuItem newGame = new JMenuItem("New Game");
	private static JMenuItem callBingo = new JMenuItem("Call Bingo");
	private static JMenuItem exitGame = new JMenuItem("Exit Game");

	public static void main(String args[]) {
		JMenuBar fileMenu = new JMenuBar();
		JMenu file = new JMenu ("File");
		restartMain();
		
		newGame.addActionListener(new menuActionListener());
		newGame.setActionCommand("New");
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		callBingo.addActionListener(new menuActionListener());
		callBingo.setActionCommand("Bingo");
		callBingo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK));
		exitGame.addActionListener(new menuActionListener());
		exitGame.setActionCommand("Exit");
		exitGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
		file.add(newGame);
		file.add(callBingo);
		file.add(exitGame);
		fileMenu.add(file);
		
		frame.setJMenuBar(fileMenu);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setPreferredSize(new Dimension(1600, 1000));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(filePanel, BorderLayout.PAGE_START);
		frame.getContentPane().add(callBoardPanel, BorderLayout.PAGE_START);
		frame.getContentPane().add(playerMotherPanel, BorderLayout.LINE_END);
		frame.getContentPane().add(cpuMotherPanel, BorderLayout.LINE_START);
		frame.getContentPane().add(CallBingo, BorderLayout.SOUTH);
		frame.pack();
	  }
	  
	public static void restartMain() {

		//Voice voice = VoiceManager.getInstance().getVoice("kevin16");
		//voice.allocate();
		JLabel callBoardBINGO = new JLabel();
		JLabel callBoardValues[] = new JLabel[76];
		JLabel BingoCallIs = new JLabel("Bingo Call is: ");
		JPanel cardPanel = new JPanel();
		int[]rngBingoList = new int[77];
		int x = 0;
		displayCallBoard(callBoardValues(), callBoardPanel);
		BingoHeader(panel1);
		BingoHeader(panel2);
		BingoHeader(panel3);
		BingoHeader(panel4);
		
		displayPlayerBoard(playerBoard, panel1);
		displayPlayerBoard(playerBoard2, panel2);
		displayCpuBoard(cpuBoard, panel3);
		displayCpuBoard(cpuBoard2, panel4);
		
		
		panel1.setLayout(new GridLayout(6, 5));
		panel2.setLayout(new GridLayout(6, 5));
		panel3.setLayout(new GridLayout(6, 5));
		panel4.setLayout(new GridLayout(6, 5));
		playerMotherPanel.add(panel1);
		playerMotherPanel.add(panel2);
		playerMotherPanel.setBorder(BorderFactory.createTitledBorder("<html><font color = 'red'>PLAYER BOARD</html>"));
		cpuMotherPanel.add(panel3);
		cpuMotherPanel.add(panel4);
		cpuMotherPanel.setBorder(BorderFactory.createTitledBorder("<html><font color = 'orange'>CPU BOARD </html>"));

		cardPanel.add(playerMotherPanel, cpuMotherPanel);
		mainPanel.add(callBoardPanel);
		mainPanel.add(cardPanel);
	  	mainPanel.add(CallBingo);
	  	mainPanel.setPreferredSize(new Dimension(1000, 750));
	  	mainPanel.setLayout(new GridLayout(3,1));
		frame.revalidate(); 
		frame.repaint(); 
		timer.start();
	}
	
	public static void displayPlayerBoard (JButton[][] boards, JPanel panels) {
	  	JButton[][] board = boards;
	  	JPanel panel = panels;
	  	for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++){
				board[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				board[i][j].setPreferredSize(new Dimension(40, 40));
				board[i][j].addActionListener(new playerDaubBoard());
				panel.add(board[i][j]);
				}
			}
	  }

	public static void displayCpuBoard (JLabel[][] boards, JPanel panels) {
	  	JLabel[][] board = boards;
	  	JPanel panel = panels;
	  	for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++){
				board[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				board[i][j].setPreferredSize(new Dimension(40, 40));
				panel.add(board[i][j]);
				}
			}
	  }
  	public static int generateBingo() { 
    	int randomBingo =(int)(Math.random() * 75) + 1;
    	return randomBingo;
	}
  
	public static String displayBingo(int rngBingo) { 
		String displayBingo = new String();
    	if (rngBingo < 16) {
      		displayBingo = ("B" + String.valueOf(rngBingo));
    	} else if (30 >= rngBingo && rngBingo > 15) {
      		displayBingo = ("I" + String.valueOf(rngBingo));
    	}else if (45 >= rngBingo && rngBingo > 30) {
      		displayBingo = ("N" + String.valueOf(rngBingo));
    	} else if (60 >= rngBingo && rngBingo > 45) {
      		displayBingo = ("G" + String.valueOf(rngBingo));
    	} else if (rngBingo > 60) {
      		displayBingo = ("O" + String.valueOf(rngBingo));
    	}
    	return displayBingo;
	}
	
	public static JButton[][] playerBoard() { //Inputing values for the boards
    	String[][]bingoCard = new String[5][5];
    	int count = 0; int x = 0;
    	while (count < 5) {
      		int min = x; int max = x +15; int count2 = 0; //making sure the values inputed in the board do not repeat
      		while(count2 < 5) {
        		String num = String.valueOf((int) (Math.random() * (max - min)) + min + 1); 
        		boolean flag = true;
        		for (int i = 0; i < 5; i++) {
          			for (int j = 0; j < 5; j++) {
            			if(num.equals(bingoCard[i][j])){
              				flag = false;
            			} 
          			}
        		}
       			if(flag) {
          			bingoCard[count2][count] = num;
          			count2 ++;
       			}
      		}
      	count++;
      	x += 15;
    	}
    	bingoCard[2][2] = ("F");
	    
	    JButton playerBoardValues[][] = new JButton[5][5];
	    for (int i = 0; i < 5; i++) {
	    	for (int j = 0; j < 5; j++){
		    	playerBoardValues[i][j] = new JButton(bingoCard[i][j]);
	    	}
	    }
	    return playerBoardValues;
    }
    
    public static JLabel[][] cpuBoard() { //Inputing values for the boards
    	String[][]bingoCard = new String[5][5];
    	int count = 0; int x = 0;
    	while (count < 5) {
      		int min = x; int max = x +15; int count2 = 0; //making sure the values inputed in the board do not repeat
      		while(count2 < 5) {
        		String num = String.valueOf((int) (Math.random() * (max - min)) + min + 1); 
        		boolean flag = true;
        		for (int i = 0; i < 5; i++) {
          			for (int j = 0; j < 5; j++) {
            			if(num.equals(bingoCard[i][j])){
              				flag = false;
            			} 
          			}
        		}
       			if(flag) {
          			bingoCard[count2][count] = num;
          			count2 ++;
       			}
      		}
      	count++;
      	x += 15;
    	}
    	bingoCard[2][2] = ("F");
	    
	    JLabel cpuBoardValues[][] = new JLabel[5][5];
	    for (int i = 0; i < 5; i++) {
	    	for (int j = 0; j < 5; j++){
		    	cpuBoardValues[i][j] = new JLabel(bingoCard[i][j], SwingConstants.CENTER);
	    	}
	    }
	    return cpuBoardValues;
    }
    
    public static void BingoHeader (JPanel panel) {
   	JLabel bingoHeader[] = new JLabel[5];
	for (int i = 0; i < 5; i ++) {
		switch(i) {
			case 0: 
				BingoHeaderSet(bingoHeader, letter1, i, panel);
				break;
			case 1: 
				BingoHeaderSet(bingoHeader, letter2, i, panel);
				break;
			case 2: 
				BingoHeaderSet(bingoHeader, letter3, i, panel);
				break;
			case 3: 
				BingoHeaderSet(bingoHeader, letter4, i, panel);
				break;
			case 4: 
				BingoHeaderSet(bingoHeader, letter5, i, panel);
				break;
			}
		}
  	}
  
	public static void BingoHeaderSet (JLabel[] bingoHeader, String letter, int i, JPanel panel) {
  		bingoHeader[i] = new JLabel(" " + letter,SwingConstants.CENTER);
		panel.add(bingoHeader[i]);
		bingoHeader[i].setBorder(BorderFactory.createLineBorder(Color.black));
		bingoHeader[i].setPreferredSize(new Dimension(70, 70));
	}
  	
	public static String[] callBoardValues () { 
  		String[]callBoard = new String[76];
    	for (int i= 1; i < 76; i++) {
    		callBoard[i] = String.valueOf(i);
    	}
      return callBoard;
    }
    
	public static void displayCallBoard(String[] callBoard, JPanel callBoardPanel) { //displaying the CallBoard
    	for (int i = 1; i < 76; i++) {
    		callBoardLabels[i] = new JLabel (callBoard[i],SwingConstants.CENTER);
			switch(i) {
				case 1:
					displayCallBoardset(letter1);
					break;
				case 16:
					displayCallBoardset(letter2);
					break;
				case 31:
					displayCallBoardset(letter3);
					break;
				case 46:
					displayCallBoardset(letter4);
					break;
				case 61:
					displayCallBoardset(letter5);
					break;
			}
			callBoardLabels[i].setPreferredSize(new Dimension(40,40));
			callBoardLabels[i].setBorder(BorderFactory.createLineBorder(Color.black));
			callBoardPanel.add(callBoardLabels[i]);
	
    	}
    	callBoardPanel.setLayout(new GridLayout(0, 16));    
    	callBoardPanel.setBorder(BorderFactory.createTitledBorder("<html><font color = 'green'>CALL BOARD</html>"));
    }
    
    public static void displayCallBoardset(String letter) {
    	JLabel callBoardBINGO = new JLabel("<html> <font color='red'> " + letter + " </font></html>",SwingConstants.CENTER);
		callBoardBINGO.setPreferredSize(new Dimension(20,70));
		callBoardBINGO.setBorder(BorderFactory.createLineBorder(Color.black));
		callBoardPanel.add(callBoardBINGO);
    }
    	
	private static class Change implements ActionListener {
    	public void actionPerformed(ActionEvent event) {
        	int x = 0;
			int[]rngBingoList = new int[77];
	        rngBingo = generateBingo();
			int l = 0;
			rngBingoList[x] = rngBingo;
			while(l < 1) { //so the Bingo call values do not repeat
				boolean flag = true;
				for (int i = 0; i < 77; i++){
					if(rngBingo == rngBingoList[i]){
						flag = false;
						rngBingo = generateBingo();
					}
				}
				if(flag) {
					rngBingoList[x] = rngBingo;	
	           	l++;
				}	
				x++;
				CallBingo.setText("Bingo Call is: " + displayBingo(rngBingo)); 	
				daubCallBoard(callBoardLabels, rngBingo);	        
				cpuDaubBoard(cpuBoard, rngBingo); 
				cpuDaubBoard(cpuBoard2, rngBingo); 
				if (winner(cpuBoard) == 1 || winner(cpuBoard2) == 1) {
					JOptionPane.showMessageDialog(null ,"CPU Wins. Horizontal Bingo!.");
					timer.stop();
				} else if (winner(cpuBoard) == 2 || winner(cpuBoard2) == 2) {
					JOptionPane.showMessageDialog(null ,"CPU Wins. Vertical Bingo!.");
					timer.stop();
				} else if (winner(cpuBoard) == 3 || winner(cpuBoard2) == 3) {
					timer.stop();
					JOptionPane.showMessageDialog(null ,"CPU Wins. Diagonal Bingo!.");
				}
			}
        }
	}
	
	public static void cpuDaubBoard (JLabel[][] cpuBoard, int rngBingo) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++){
				if (cpuBoard[i][j].getText().equals(Integer.toString(rngBingo))) {
					cpuBoard[i][j].setBackground(Color.blue);
					cpuBoard[i][j].setForeground(Color.white);
					cpuBoard[i][j].setOpaque(true);
				}
			}
		}
	}
		
	    public static boolean daubCallBoard (JLabel[] callBoardLabels, int rngBingo) {
	    boolean valid = false;
    	for (int i = 1; i < 76; i ++) {
    		if (callBoardLabels[i].getText().equals(Integer.toString(rngBingo))) {
    			callBoardLabels[i].setBackground(Color.yellow); 
    			callBoardLabels[i].setOpaque(true);
    			break;
    		}
    	}
    	return valid;
    }
    
    private static class menuActionListener implements ActionListener {
    		
    	public void actionPerformed(ActionEvent event) {
    		String event1 = event.getActionCommand();
    		System.out.println (" " + event1 + event);
    		if (event1.equals("New")) {
    			restartMain();
    		} else if (event1.equals("Bingo")) {	
    				if (winnerPlayer(playerBoard) == 1 || winnerPlayer(playerBoard2) == 1) {
						JOptionPane.showMessageDialog(null ,"You Win. Horizontal Bingo!.");
						timer.stop();
					} else if (winnerPlayer(playerBoard) == 2 || winnerPlayer(playerBoard2) == 2) {
						timer.stop();
						JOptionPane.showMessageDialog(null ,"You Win. Vertical Bingo!.");
					} else if (winnerPlayer(playerBoard) == 3 || winnerPlayer(playerBoard2) == 3) {
						timer.stop();
						JOptionPane.showMessageDialog(null ,"You Win! Diagonal Bingo.");
					} else {
						timer.stop();
						JOptionPane.showMessageDialog(null ,"You falsed Bingo! You lose.");
					}
    		} else if (event1.equals("Exit")) {
    								System.exit(0);
					timer.stop();
    		}
    	}
    }
 
    
 	private static class playerDaubBoard implements ActionListener { 
 		
 		public void actionPerformed(ActionEvent daub) {
 			String event = daub.getActionCommand();
 			JButton source = (JButton)daub.getSource();
 			if (event.equals(Integer.toString(rngBingo))) {
 				source.setBackground(Color.red);
 				source.setForeground(Color.white);
 			} else {
 				timer.stop();
 				JOptionPane.showMessageDialog(null ,"False daub! You lose.");
 			}
 		}
 	}
 	
 	public static int winner (JLabel[][] playerBoard) { //Winner method (returns 1 if there is a win, 0 if there is not)
    	int win = 0;
    	for (int i = 0; i < 5; i++) {
        	for (int row = 0; row < 5; row++) { //Checks for horizontal wins
				if (playerBoard[row][i].getBackground() == Color.blue)  { //trim eliminates leading + trailing spaces
                	win++;
                }
            }
            if (win == 5) { //if all 5 * or F is in the row return win
            	return 1;
            } else {
            	win = 0; //return loss
            }
            for (int col = 0; col < 5; col++) { //checks for vertical wins
                if (playerBoard[i][col].getBackground() == Color.blue)  {
                    win++;
                }
            }
            if (win == 5) {
                return 2;
            } else {
            	win = 0;
            }
        } //For Diagonal Wins
        if ((playerBoard[0][0].getBackground() == Color.blue) && (playerBoard[1][1].getBackground() == Color.blue) && (playerBoard[2][2].getBackground() == Color.blue) && (playerBoard[3][3].getBackground() == Color.blue)&& (playerBoard[4][4].getBackground() == Color.blue) || 
            (playerBoard[4][0].getBackground() == Color.blue) && (playerBoard[3][1].getBackground() == Color.blue) && (playerBoard[2][2].getBackground() == Color.blue) && (playerBoard[1][3].getBackground() == Color.blue) && (playerBoard[0][4].getBackground() == Color.blue)) {
            return 3;
        }
        return 0;
    }
    
    public static int winnerPlayer (JButton[][] playerBoard) { //Winner method (returns 1 if there is a win, 0 if there is not)
    	int win = 0;
    	for (int i = 0; i < 5; i++) {
        	for (int row = 0; row < 5; row++) { //Checks for horizontal wins
				if (playerBoard[row][i].getBackground() == Color.red)  { //trim eliminates leading + trailing spaces
                	win++;
                }
            }
            if (win == 5) { //if all 5 * or F is in the row return win
            	return 1;
            } else {
            	win = 0; //return loss
            }
            for (int col = 0; col < 5; col++) { //checks for vertical wins
                if (playerBoard[i][col].getBackground() == Color.red)  {
                    win++;
                }
            }
            if (win == 5) {
                return 2;
            } else {
            	win = 0;
            }
        } //For Diagonal Wins
        if ((playerBoard[0][0].getBackground() == Color.red) && (playerBoard[1][1].getBackground() == Color.red) && (playerBoard[2][2].getBackground() == Color.red) && (playerBoard[3][3].getBackground() == Color.red)&& (playerBoard[4][4].getBackground() == Color.red) || 
            (playerBoard[4][0].getBackground() == Color.red) && (playerBoard[3][1].getBackground() == Color.red) && (playerBoard[2][2].getBackground() == Color.red) && (playerBoard[1][3].getBackground() == Color.red) && (playerBoard[0][4].getBackground() == Color.red)) {
            return 3;
        }
        return 0;
    }
}
