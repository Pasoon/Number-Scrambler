import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.JLabel;

public class PuzzleGameMenu extends JFrame implements ActionListener{
	
	public JButton startButton, quitButton;
	public JLabel Title = new JLabel("Number Scrambler");
	public JLabel Author = new JLabel("By: Pasoon Azimi");
	
	public PuzzleGameMenu(){
		super("ICS4U - Summative");
		setSize(425, 425);
		getContentPane().setBackground(Color.BLACK);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		
		
		Font f = new Font("Calibri", Font.BOLD, 26);
		Font g = new Font("Calibri", Font.BOLD, 16);
		
		Title.setFont(f);
	    Title.setSize(300,100);
	    Title.setLocation(100,70);
	    Title.setForeground(Color.WHITE);
	    add(Title);
	    
	    Author.setFont(g);
	    Author.setSize(200,100);
	    Author.setLocation(140,210);
	    Author.setForeground(Color.WHITE);
	    add(Author);
		
		startButton = new JButton("Start Game");
		startButton.setFont(g);
		startButton.addActionListener(this);
		startButton.setSize(150,30);
		startButton.setLocation(140,150);
		startButton.setBackground(Color.WHITE);
		add(startButton);
		quitButton = new JButton("Quit Game");
		quitButton.setFont(g);
		quitButton.addActionListener(this);
		quitButton.setSize(150,30);
		quitButton.setLocation(140,200);
		quitButton.setBackground(Color.WHITE);
		add(quitButton);
		playMusic();
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new PuzzleGameMenu();
	}

	
	 //Method to play background music
    public static void playMusic() {
    		//new thread for music only 
    	
	      
					try {
						File file = new File("music.wav");
						Clip clip = AudioSystem.getClip();
						clip.open(AudioSystem.getAudioInputStream(file));
						clip.start();
				        clip.loop(Clip.LOOP_CONTINUOUSLY);
				        Thread.sleep(10000); // looping as long as this thread is aliv
						
			             } catch (Exception e) {
			                System.out.println(e.getMessage());
			               }
				
			
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == startButton){
			setVisible(false);
			new PuzzleGame();
		}
		
		if(e.getSource() == quitButton){
			System.exit(0);
		}
	}
	
	
}



