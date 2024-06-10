package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import javax.swing.*;
import Brawlers.*;
import java.lang.Thread;
import GUI.*;

public class Game {
	
	public Game(){}
	
	public static void main(String[] args){
		
		SoundManager.intro.play();
			
		Build defaultBuild = new Build();
		Brawler[] brawlers = Brawler.brawlers;
		
		Brawler[] chosens = new Brawler[2];
		
		JFrame window = new JFrame();
		window.setSize(900,600);
		window.setTitle("TURN BY TURN! Client");
		Container con;
		
		JPanel titleNamePanel,
			   titleCreditPanel,
			   quitPanel,
			   startButtonPanel, 
			   player1BrawlerPanel, 
			   player2BrawlerPanel, 
			   helpPanel,
			   versionPanel,
			   patchNotesPanel,
			   gamemodePanel;
		
		JLabel titleName,titleCredit,version,gamemodeLabel;
		JButton startButton, quitButton, helpButton, patchNotes, player1Brawler,player2Brawler;
		JComboBox<String> gamemode;
		
		String[] brawlersName = new String[brawlers.length];
		for(int i = 0; i < brawlers.length; i++) {
			brawlersName[i] = brawlers[i].name;
		}
		
		
		//The window
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.DARK_GRAY);
		window.setLayout(null);
		con = window.getContentPane();
		window.setIconImage(new ImageIcon("res/images/turnbyturn.png").getImage());
		
				
		//version
		versionPanel = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEADING,5, 5));
		version = new JLabel("P-BETA 1.0", SwingConstants.LEFT);
		version.setHorizontalAlignment(SwingConstants.RIGHT);
		versionPanel.setBackground(Color.DARK_GRAY);
		versionPanel.setBounds(0,535,150,30);
		versionPanel.add(version);
		version.setForeground(Color.white);
		version.setFont(new Font("Arial", Font.PLAIN, 15));
		con.add(versionPanel);
		
		
		//TitleName
		titleNamePanel = new JPanel();
		titleCreditPanel = new JPanel();
		titleNamePanel.setBounds(150,10,600,80);
		titleCreditPanel.setBounds(190,85,500,35);
		titleNamePanel.setBackground(Color.DARK_GRAY);
		titleCreditPanel.setBackground(Color.darkGray);
		titleName = new JLabel("TURN BY TURN!");
		titleCredit = new JLabel("by Ege Ündeniş");
		titleName.setForeground(Color.white);
		titleName.setFont(new Font("Arial", Font.BOLD, 70 ));
		titleCredit.setForeground(Color.gray);
		titleCredit.setFont(new Font("Arial", Font.BOLD, 25 ));
		titleCredit.setVisible(true);
		titleNamePanel.add(titleName);
		titleCreditPanel.add(titleCredit);
		
		//Gamemode
				String[] gamemodes = {
						"Classic",
						"Choose",
						"Duplicates",
						"Wizardary",
						"Decaying",
						"Technical"
				};
				gamemode = new JComboBox<String>(gamemodes);
				gamemode.setFocusable(false);
				gamemode.setFont(new Font("Arial", Font.BOLD, 15));
				((JLabel)gamemode.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
				gamemodeLabel = new JLabel("GAMEMODE");
				gamemodeLabel.setFont(new Font("Arial", Font.BOLD, 20));
				gamemodeLabel.setForeground(Color.white);
				gamemodeLabel.setBounds(390,320,120,60);
				con.add(gamemodeLabel);
				gamemodePanel = new JPanel();
				gamemodePanel.setBounds(400,360,100,60);
				gamemode.setBackground(Color.black);
				gamemode.setForeground(Color.white);
				gamemodePanel.setBackground(Color.darkGray);
				gamemodePanel.setVisible(true);
				
				
				gamemodePanel.add(gamemode);
		
		//StartButton
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(350,400,200,50);
		startButtonPanel.setBackground(Color.darkGray);
		startButton = new JButton("START");
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setFont(new Font("Arial", Font.BOLD, 30 ));
		startButton.setFocusPainted(false);
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				switch((String) gamemode.getSelectedItem()) {
				
				case "Choose":
					if(chosens[0] != null && chosens[1] != null && chosens[1] != chosens[0]) {
					playGame1v1(chosens[0], chosens[1], "Choose");
					}
					else
						JOptionPane.showMessageDialog(window, "Duplicate brawlers are not allowed.");
					break;
				
				case "Classic":
					Random ran = new Random();
					
					Brawler chosenBrawler1 = new Todd(defaultBuild);
					Brawler chosenBrawler2 = new Todd(defaultBuild);
					
					int chosen1 = 0, chosen2 = 0;
					while(chosen1 == chosen2){
					int len = brawlers.length;
					chosen1 = new Random().nextInt(0, len);	
					chosen2 = new Random().nextInt(0, len);
					chosenBrawler1 = brawlers[chosen1].newInstance();
					chosenBrawler2 = brawlers[chosen2].newInstance();
					}
					chosenBrawler1.build = new Build(ran);
					chosenBrawler2.build = new Build(ran);
					
					playGame1v1(chosenBrawler1,chosenBrawler2, "Classic");
					break;
					
					
				case "Duplicates":
					if(chosens[0] != null && chosens[1] != null && chosens[1] == chosens[0]) {
					playGame1v1(chosens[0], chosens[1], "Duplicates");
					}
					else {
						JOptionPane.showMessageDialog(window, "Chosen brawlers are not duplicates");	
					}
					break;
					
				case "Wizardary":
					if(chosens[0] != null && chosens[1] != null && chosens[1] != chosens[0]) {
					playGame1v1(chosens[0], chosens[1], "Wizardary");
					}
					else
						JOptionPane.showMessageDialog(window, "Duplicate brawlers are not allowed.");
					break;
					
				case "Decaying":
					if(chosens[0] != null && chosens[1] != null && chosens[1] != chosens[0]) {
					playGame1v1(chosens[0], chosens[1], "Decaying");
					}
					else
						JOptionPane.showMessageDialog(window, "Duplicate brawlers are not allowed.");
					break;
					
				case "Technical":
					if(chosens[0] != null && chosens[1] != null && chosens[1] != chosens[0]) {
					playGame1v1(chosens[0], chosens[1], "Technical");
					}
					else
						JOptionPane.showMessageDialog(window, "Duplicate brawlers are not allowed.");
					break;
					
				
				}
				
				
			}
		});
		startButtonPanel.add(startButton);
		
		//Experimental!
		JPanel expButtonPanel = new JPanel();
		expButtonPanel.setBounds(390,300,120,45);
		expButtonPanel.setBackground(Color.darkGray);
		JButton expButton = new JButton("Experimental");
		expButton.setBackground(Color.black);
		expButton.setForeground(Color.white);
		expButton.setFont(new Font("Arial", Font.BOLD, 15 ));
		expButton.setFocusPainted(false);
		expButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//still nothing yet!!!!!
				SoundManager.click.play();
				
			}
		});
		expButtonPanel.add(expButton);
		con.add(expButtonPanel);
		
		//Options Button
		JPanel OptionButtonPanel = new JPanel();
		OptionButtonPanel.setBounds(150,470,180,100);
		OptionButtonPanel.setBackground(Color.darkGray);
		JButton optionsButton = new JButton("OPTIONS");
		optionsButton.setBackground(Color.black);
		optionsButton.setForeground(Color.white);
		optionsButton.setFont(new Font("Times New Roman", Font.ITALIC, 25 ));
		optionsButton.setFocusPainted(false);
		optionsButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
			SoundManager.click.play();

				JWindow window = new JWindow();
				window.setLocationRelativeTo(null);
				window.setBounds(645,280,250,350);
				window.getContentPane().setBackground(Color.LIGHT_GRAY);
				window.setVisible(true);
				window.setLayout(null);
				Container wincon = window.getContentPane();
				
				JLabel menuSoundsLabel = new JLabel("Menu Sound Effects:");
				menuSoundsLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
				menuSoundsLabel.setBounds(10,10, 150,30);
				wincon.add(menuSoundsLabel);				
				JToggleButton menuSoundsButton = new JToggleButton();
				menuSoundsButton.setBounds(150,10, 30,30);
				wincon.add(menuSoundsButton);
				
				JLabel brawlerSoundsLabel = new JLabel("Brawler Sound Effects:");
				brawlerSoundsLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
				brawlerSoundsLabel.setBounds(10,50, 160,30);
				wincon.add(brawlerSoundsLabel);				
				JToggleButton brawlerSoundsButton = new JToggleButton();
				brawlerSoundsButton.setBounds(170,50, 30,30);
				wincon.add(brawlerSoundsButton);
				
				
				
				JButton exitOptions = new JButton("EXIT");
				exitOptions.setBounds(75,300, 100,30);
				exitOptions.setBackground(Color.black);
				exitOptions.setForeground(Color.white);
				exitOptions.setFont(new Font("Times New Roman", Font.ITALIC, 20));
				exitOptions.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						SoundManager.click.play();
						window.dispose();
						
					}
				});
				wincon.add(exitOptions);		
				
			}}
		);
		/*
		OptionButtonPanel.add(optionsButton);
		con.add(OptionButtonPanel);
		*/
		
		//Help button
		helpPanel = new JPanel();
		helpPanel.setBounds(270,470,100,100);
		helpPanel.setBackground(Color.darkGray);
		helpButton = new JButton("HELP");
		helpButton.setBackground(Color.black);
		helpButton.setForeground(Color.white);
		helpButton.setFont(new Font("Times New Roman", Font.ITALIC, 25 ));
		helpButton.setFocusPainted(false);
		helpButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
			SoundManager.click.play();
			helpScreen(brawlers, brawlersName);
			}});
		
		helpPanel.add(helpButton);
		
		//Player1 choosing character
		JPanel p1p = new JPanel(); 
		JLabel p1 = new JLabel("PLAYER 1", JLabel.LEFT);
		p1p.setBounds(20,165, 100 , 40);
		p1.setFont(new Font("Arial", Font.BOLD, 20));
		p1.setForeground(Color.white);
		p1p.setBackground(Color.DARK_GRAY);
		p1p.add(p1);
		con.add(p1p);
		
		player1BrawlerPanel = new JPanel();
		player1BrawlerPanel.setBounds(20, 200, 200, 100);
		player1BrawlerPanel.setBackground(Color.darkGray);
		player1Brawler = new JButton("SELECT BRAWLER");
		player1Brawler.setBackground(new Color(217, 180, 212) );
		player1Brawler.setFocusable(false);
		player1Brawler.setFont(new Font("Times New Roman", Font.BOLD ,20));
		player1Brawler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SoundManager.click.play();

				JWindow brawlerChooseWindow1 = new JWindow();
				brawlerChooseWindow1.setLocationRelativeTo(null);
				brawlerChooseWindow1.setIconImage(new ImageIcon("res/images/turnbyturn.png").getImage());
				brawlerChooseWindow1.setBounds(375,280,786,390);
				brawlerChooseWindow1.getContentPane().setBackground(Color.LIGHT_GRAY);
				brawlerChooseWindow1.setVisible(true);
				brawlerChooseWindow1.setLayout(null);
				Container bcwcon1 = brawlerChooseWindow1.getContentPane();
				
				
				JLabel welcome = new JLabel("Choose your Brawler!");
				welcome.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
				welcome.setHorizontalAlignment(SwingConstants.CENTER);
				welcome.setVerticalAlignment(SwingConstants.CENTER);
				welcome.setBounds(580, 20, 200 , 20);
				bcwcon1.add(welcome);
				
				JLabel expl = new JLabel("<html><p style=\"width:110px\"> Select a brawler to get some brief information! <html>");
				expl.setBackground(Color.white);
				expl.setOpaque(true);
				expl.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
				expl.setBounds(600, 140 , 150 ,180);
				expl.setHorizontalAlignment(SwingConstants.LEFT);
				expl.setVerticalAlignment(SwingConstants.TOP);
				expl.setBorder(BorderFactory.createLineBorder(Color.black, 2) );
				bcwcon1.add(expl);
				
				BrawlerSelectionGrid bsg1 = new BrawlerSelectionGrid(welcome, expl);
				bsg1.setBounds(30, 20, 550, 350);
				bsg1.setVisible(true);
				bcwcon1.add(bsg1);
	
				JPanel buttonPane1 = new JPanel();
				buttonPane1.setBounds(630, 50, 100, 40);
				buttonPane1.setBackground(Color.lightGray);
				JButton player1BrawlerButton = new JButton("Lock in");
				player1BrawlerButton.setBackground(Color.black);
				player1BrawlerButton.setForeground(Color.white);
				player1BrawlerButton.setFont(new Font("Arial", Font.PLAIN, 18 ));
				player1BrawlerButton.setFocusable(false);
				player1BrawlerButton.setVisible(true);
				player1BrawlerButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							
						boolean bool = bsg1.randomlyChosen;
						if(bool) {
							brawlerChooseWindow1.dispose();
							Brawler.brawlerCall(welcome.getText().toLowerCase());
							return;						
						}
						
						
						String chosenBrawlerName =  (String) bsg1.getSelectedBrawler();
						for(int i = 0; i < brawlers.length; i++) 
							if (brawlers[i].name == chosenBrawlerName) {
								chosens[0] = brawlers[i];					
								p1.setText(brawlers[i].name);
								brawlerChooseWindow1.dispose();
								Brawler.brawlerCall(welcome.getText().toLowerCase());
							}	
					}
				});
				buttonPane1.add(player1BrawlerButton);
				bcwcon1.add(buttonPane1);
				
				//Random
				JPanel random1Panel = new JPanel();
				random1Panel.setBounds(630, 90, 100, 60);
				random1Panel.setBackground(Color.lightGray);
				JButton random1 = new JButton("Random");
				random1.setBackground(Color.black);
				random1.setForeground(Color.white);
				random1.setFont(new Font("Arial", Font.PLAIN, 18 ));
				random1.setFocusable(false);
				random1.setVisible(true);
				random1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							
						int len = brawlers.length;
						int chosen = new Random().nextInt(0, len);
						chosens[0] = brawlers[chosen];
						chosens[0].build = new Build(new Random());
						
						p1.setText(brawlers[chosen].name);
						welcome.setText(brawlers[chosen].name.toUpperCase());
                        welcome.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
                        p1.setText(brawlers[chosen].name);
                        
                        bsg1.randomlyChosen = true;
                        bsg1.changeLabels( brawlers[chosen].name);
						
					}
				});		
				random1Panel.add(random1);
				bcwcon1.add(random1Panel);
				
				//Exit
				JPanel exit1Panel = new JPanel();
				exit1Panel.setBounds(630, 330, 100, 60);
				random1Panel.setBackground(Color.lightGray);
				exit1Panel.setBackground(Color.lightGray);
				JButton exit1 = new JButton("Exit");
				exit1.setBackground(Color.black);
				exit1.setForeground(Color.white);
				exit1.setFont(new Font("Arial", Font.PLAIN, 18 ));
				exit1.setFocusable(false);
				exit1.setVisible(true);
				exit1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {

						brawlerChooseWindow1.dispose();
					}
				});
				exit1Panel.add(exit1);
				bcwcon1.add(exit1Panel);
				
				
			}
		});
		player1BrawlerPanel.add(player1Brawler);
			
		//Player 2 choosing character
		JPanel p2p = new JPanel(); 
		JLabel p2 = new JLabel("PLAYER 2", JLabel.RIGHT);
		p2p.setBounds(760,165, 100 , 40);
		p2.setFont(new Font("Arial", Font.BOLD, 20));
		p2.setForeground(Color.white);
		p2p.setBackground(Color.DARK_GRAY);
		p2p.add(p2);
		con.add(p2p);
		
		player2BrawlerPanel = new JPanel();
		player2BrawlerPanel.setBounds(665, 200, 200, 100);
		player2BrawlerPanel.setBackground(Color.darkGray);
		player2Brawler = new JButton("SELECT BRAWLER");
		player2Brawler.setBackground(new Color(217, 180, 212) );
		player2Brawler.setFocusable(false);
		player2Brawler.setFont(new Font("Times New Roman", Font.BOLD ,20));
		player2Brawler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SoundManager.click.play();

				JWindow brawlerChooseWindow1 = new JWindow();
				brawlerChooseWindow1.setLocationRelativeTo(null);
				brawlerChooseWindow1.setIconImage(new ImageIcon("res/images/turnbyturn.png").getImage());
				brawlerChooseWindow1.setBounds(375,280,786,390);
				brawlerChooseWindow1.getContentPane().setBackground(Color.LIGHT_GRAY);
				brawlerChooseWindow1.setVisible(true);
				brawlerChooseWindow1.setLayout(null);
				Container bcwcon1 = brawlerChooseWindow1.getContentPane();
				
				
				JLabel welcome = new JLabel("Choose your Brawler!");
				welcome.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
				welcome.setHorizontalAlignment(SwingConstants.CENTER);
				welcome.setVerticalAlignment(SwingConstants.CENTER);
				welcome.setBounds(580, 20, 200 , 20);
				bcwcon1.add(welcome);
				
				JLabel expl = new JLabel("<html><p style=\"width:110px\"> Select a brawler to get some brief information! <html>");
				expl.setBackground(Color.white);
				expl.setOpaque(true);
				expl.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
				expl.setBounds(600, 140 , 150 ,180);
				expl.setHorizontalAlignment(SwingConstants.LEFT);
				expl.setVerticalAlignment(SwingConstants.TOP);
				expl.setBorder(BorderFactory.createLineBorder(Color.black, 2) );
				bcwcon1.add(expl);
				
				BrawlerSelectionGrid bsg1 = new BrawlerSelectionGrid(welcome, expl);
				bsg1.setBounds(30, 20, 550, 350);
				bsg1.setVisible(true);
				bcwcon1.add(bsg1);
				
				JPanel buttonPane1 = new JPanel();
				buttonPane1.setBounds(630, 50, 100, 40);
				buttonPane1.setBackground(Color.lightGray);
				JButton player1BrawlerButton = new JButton("Lock in");
				player1BrawlerButton.setBackground(Color.black);
				player1BrawlerButton.setForeground(Color.white);
				player1BrawlerButton.setFont(new Font("Arial", Font.PLAIN, 18 ));
				player1BrawlerButton.setFocusable(false);
				player1BrawlerButton.setVisible(true);
				player1BrawlerButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							
						boolean bool = bsg1.randomlyChosen;
						if(bool) {
							brawlerChooseWindow1.dispose();
							Brawler.brawlerCall(welcome.getText().toLowerCase());
							return;						
						}
						
						
						String chosenBrawlerName =  (String) bsg1.getSelectedBrawler();
						for(int i = 0; i < brawlers.length; i++) 
							if (brawlers[i].name == chosenBrawlerName) {
								chosens[1] = brawlers[i];					
								p2.setText(brawlers[i].name);
								brawlerChooseWindow1.dispose();
								Brawler.brawlerCall(welcome.getText().toLowerCase());
							}	
					}
				});
				buttonPane1.add(player1BrawlerButton);
				bcwcon1.add(buttonPane1);
				
				//Random
				JPanel random1Panel = new JPanel();
				random1Panel.setBounds(630, 90, 100, 60);
				random1Panel.setBackground(Color.lightGray);
				JButton random1 = new JButton("Random");
				random1.setBackground(Color.black);
				random1.setForeground(Color.white);
				random1.setFont(new Font("Arial", Font.PLAIN, 18 ));
				random1.setFocusable(false);
				random1.setVisible(true);
				random1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							
						int len = brawlers.length;
						int chosen = new Random().nextInt(0, len);
						chosens[1] = brawlers[chosen];
						chosens[1].build = new Build(new Random());
						
						p2.setText(brawlers[chosen].name);
						welcome.setText(brawlers[chosen].name.toUpperCase());
                        welcome.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
                        p2.setText(brawlers[chosen].name);
                        
                        bsg1.randomlyChosen = true;
                        bsg1.changeLabels( brawlers[chosen].name);
						
					}
				});		
				random1Panel.add(random1);
				bcwcon1.add(random1Panel);
				
				//Exit
				JPanel exit1Panel = new JPanel();
				exit1Panel.setBounds(630, 330, 100, 60);
				random1Panel.setBackground(Color.lightGray);
				exit1Panel.setBackground(Color.lightGray);
				JButton exit1 = new JButton("Exit");
				exit1.setBackground(Color.black);
				exit1.setForeground(Color.white);
				exit1.setFont(new Font("Arial", Font.PLAIN, 18 ));
				exit1.setFocusable(false);
				exit1.setVisible(true);
				exit1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {

						brawlerChooseWindow1.dispose();
					}
				});
				exit1Panel.add(exit1);
				bcwcon1.add(exit1Panel);
				
				
			}
		});
		player2BrawlerPanel.add(player2Brawler);
		con.add(player2BrawlerPanel);
			
		//Builds
		String[] Potions = Build.Potions;
		String[] PotionsName = Build.PotionsName;
		String[] GadgetsArray = Build.Gadgets;
		String[] GearsArray = Build.Gears;
		String[] SpellArray = Build.Spells;
		
		 JPanel build1Panel = new JPanel();
		 build1Panel.setBounds(20, 250, 100, 100);
		 build1Panel.setBackground(Color.darkGray);
		 JButton build1 = new JButton("BUILD");
		 build1.setBackground(new Color(217, 180, 212));
		 build1.setFont(new Font("Times New Roman", Font.BOLD ,20));
		 build1.setFocusable(false);
		 build1.setVisible(true);
		 build1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SoundManager.click.play();
				if (chosens[0] != null) {

				JWindow buildWindow1 = new JWindow();
				buildWindow1.setLocationRelativeTo(null);
				buildWindow1.setIconImage(new ImageIcon("res/images/turnbyturn.png").getImage());
				buildWindow1.setBounds(375,280,786,390);
				buildWindow1.getContentPane().setBackground(Color.lightGray);
				buildWindow1.setVisible(true);
				buildWindow1.setLayout(null);
				Container bwcon1 = buildWindow1.getContentPane();
				
				JComboBox<String> potionCombo = new JComboBox<String>(PotionsName);
				((JLabel)potionCombo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
				JLabel potionComboTitle = new JLabel("POTION", SwingConstants.CENTER);
				potionComboTitle.setFont(new Font("Arial",Font.BOLD,20));
				potionComboTitle.setForeground(Color.black);
				potionComboTitle.setBounds(20,20,150,30);
				bwcon1.add(potionComboTitle);
				potionCombo.setBackground(Color.black);
				potionCombo.setForeground(Color.white);
				potionCombo.setFocusable(false);
				potionCombo.setMaximumRowCount(10);
				potionCombo.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				JPanel potionComboPanel = new JPanel();
				potionComboPanel.setBackground(Color.lightGray);
				potionComboPanel.setBounds(20,50,150,75);
				potionComboPanel.add(potionCombo);
				bwcon1.add(potionComboPanel);
				
				//Gadget
				JLabel gadgetTitle = new JLabel("GADGET");
				gadgetTitle.setForeground(Color.black);
				gadgetTitle.setFont(new Font("Arial",Font.BOLD,20));
				gadgetTitle.setBounds(210,20,150,30);
				bwcon1.add(gadgetTitle);
 				JPanel gadgetPanel = new JPanel();
				gadgetPanel.setBounds(180, 50, 150,75);
				gadgetPanel.setBackground(Color.lightGray);
				
				JComboBox<String> gadgetchoose = new JComboBox<String>(GadgetsArray);
				((JLabel)gadgetchoose.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
				gadgetchoose.setBackground(Color.black);
				gadgetchoose.setForeground(Color.white);
				gadgetchoose.setFocusable(false);
				gadgetchoose.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				gadgetchoose.setMaximumRowCount(6);
				gadgetPanel.add(gadgetchoose);
				bwcon1.add(gadgetPanel);
				 
				//Gear
				JLabel geartitle = new JLabel("GEAR");
				geartitle.setForeground(Color.black);
				geartitle.setFont(new Font("Arial",Font.BOLD,20));
				geartitle.setBounds(425,20,150,30);
				bwcon1.add(geartitle);
 				JPanel gearPanel = new JPanel();
				gearPanel.setBounds(355, 50, 195,75);
				gearPanel.setBackground(Color.lightGray);
				
				JComboBox<String> gearchoose = new JComboBox<String>(GearsArray);
				((JLabel)gearchoose.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
				gearchoose.setBackground(Color.black);
				gearchoose.setForeground(Color.white);
				gearchoose.setFocusable(false);
				gearchoose.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				gearchoose.setMaximumRowCount(8);
				gearPanel.add(gearchoose);
				bwcon1.add(gearPanel);
				
				//Spell
				JLabel spellTitle = new JLabel("SPELLS");
				spellTitle.setForeground(Color.black);
				spellTitle.setFont(new Font("Arial",Font.BOLD,20));
				spellTitle.setBounds(640,20,150,30);
				bwcon1.add(spellTitle);
 				JPanel spellPanel = new JPanel();
				spellPanel.setBounds(590, 50, 180,75);
				spellPanel.setBackground(Color.lightGray);
			
				JComboBox<String> spellChoose = new JComboBox<String>(SpellArray);
				((JLabel)spellChoose.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
				spellChoose.setBackground(Color.black);
				spellChoose.setForeground(Color.white);
				spellChoose.setFocusable(false);
				spellChoose.setMaximumRowCount(11);
				spellChoose.setFont(new Font("Times New Roman", Font.PLAIN, 17));
				spellPanel.add(spellChoose);
				bwcon1.add(spellPanel);
				
				JPanel randomButtonPanel = new JPanel();
				randomButtonPanel.setBounds(295,280,200,50);
				randomButtonPanel.setBackground(Color.lightGray);
				JButton randomButton = new JButton("RANDOM");
				randomButton.setBackground(Color.black);
				randomButton.setForeground(Color.white);
				randomButton.setFocusable(false);
				randomButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
				randomButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						SoundManager.click.play();
						chosens[0].build = new Build(new Random());
						buildWindow1.dispose();
						
					}
				});
				randomButtonPanel.add(randomButton);
				bwcon1.add(randomButtonPanel);
				
				JLabel msg = new JLabel( ("Create a build for " + chosens[0].name) );
				msg.setBounds(290, 240, 250, 50);
				msg.setFont(new Font("Arial",Font.BOLD,20));
				msg.setForeground(Color.black);
				bwcon1.add(msg);
				
				JPanel doneButtonPanel = new JPanel();
				doneButtonPanel.setBounds(320,330,150,100);
				doneButtonPanel.setBackground(Color.lightGray);
				JButton doneButton = new JButton("DONE");
				doneButton.setBackground(Color.black);
				doneButton.setForeground(Color.white);
				doneButton.setFocusable(false);
				doneButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
				doneButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						SoundManager.click.play();
						chosens[0].build = new Build(
								GadgetsArray[gadgetchoose.getSelectedIndex()], 
								Potions[potionCombo.getSelectedIndex()], 
								GearsArray[gearchoose.getSelectedIndex()],
								SpellArray[spellChoose.getSelectedIndex()]		
								);
						buildWindow1.dispose();			
					}
				});  
				doneButtonPanel.add(doneButton);
			    bwcon1.add(doneButtonPanel);
				
				}
			}
		});
		 build1Panel.add(build1);
		 con.add(build1Panel);
		 
		 JPanel build2Panel = new JPanel();
		 build2Panel.setBounds(765, 250, 100, 100);
		 build2Panel.setBackground(Color.darkGray);
		 JButton build2 = new JButton("BUILD");
		 build2.setBackground(new Color(217, 180, 212));
		 build2.setFont(new Font("Times New Roman", Font.BOLD ,20));
		 build2.setFocusable(false);
		 build2.setVisible(true);
		 build2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					SoundManager.click.play();
					if (chosens[1] != null) {

					JWindow buildWindow1 = new JWindow();
					buildWindow1.setLocationRelativeTo(null);
					buildWindow1.setIconImage(new ImageIcon("res/images/turnbyturn.png").getImage());
					buildWindow1.setBounds(375,280,786,390);
					buildWindow1.getContentPane().setBackground(Color.lightGray);
					buildWindow1.setVisible(true);
					buildWindow1.setLayout(null);
					Container bwcon1 = buildWindow1.getContentPane();
					
					JComboBox<String> potionCombo = new JComboBox<String>(PotionsName);
					((JLabel)potionCombo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
					JLabel potionComboTitle = new JLabel("POTION", SwingConstants.CENTER);
					potionComboTitle.setFont(new Font("Arial",Font.BOLD,20));
					potionComboTitle.setForeground(Color.black);
					potionComboTitle.setBounds(20,20,150,30);
					bwcon1.add(potionComboTitle);
					potionCombo.setBackground(Color.black);
					potionCombo.setForeground(Color.white);
					potionCombo.setFocusable(false);
					potionCombo.setMaximumRowCount(10);
					potionCombo.setFont(new Font("Times New Roman", Font.PLAIN, 19));
					JPanel potionComboPanel = new JPanel();
					potionComboPanel.setBackground(Color.lightGray);
					potionComboPanel.setBounds(20,50,150,75);
					potionComboPanel.add(potionCombo);
					bwcon1.add(potionComboPanel);
					
					//Gadget
					JLabel gadgetTitle = new JLabel("GADGET");
					gadgetTitle.setForeground(Color.black);
					gadgetTitle.setFont(new Font("Arial",Font.BOLD,20));
					gadgetTitle.setBounds(210,20,150,30);
					bwcon1.add(gadgetTitle);
	 				JPanel gadgetPanel = new JPanel();
					gadgetPanel.setBounds(180, 50, 150,75);
					gadgetPanel.setBackground(Color.lightGray);
					
					JComboBox<String> gadgetchoose = new JComboBox<String>(GadgetsArray);
					((JLabel)gadgetchoose.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
					gadgetchoose.setBackground(Color.black);
					gadgetchoose.setForeground(Color.white);
					gadgetchoose.setFocusable(false);
					gadgetchoose.setFont(new Font("Times New Roman", Font.PLAIN, 19));
					gadgetchoose.setMaximumRowCount(6);
					gadgetPanel.add(gadgetchoose);
					bwcon1.add(gadgetPanel);
					 
					//Gear
					JLabel geartitle = new JLabel("GEAR");
					geartitle.setForeground(Color.black);
					geartitle.setFont(new Font("Arial",Font.BOLD,20));
					geartitle.setBounds(425,20,150,30);
					bwcon1.add(geartitle);
	 				JPanel gearPanel = new JPanel();
					gearPanel.setBounds(355, 50, 195,75);
					gearPanel.setBackground(Color.lightGray);
					
					JComboBox<String> gearchoose = new JComboBox<String>(GearsArray);
					((JLabel)gearchoose.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
					gearchoose.setBackground(Color.black);
					gearchoose.setForeground(Color.white);
					gearchoose.setFocusable(false);
					gearchoose.setFont(new Font("Times New Roman", Font.PLAIN, 19));
					gearchoose.setMaximumRowCount(8);
					gearPanel.add(gearchoose);
					bwcon1.add(gearPanel);
					
					//Spell
					JLabel spellTitle = new JLabel("SPELLS");
					spellTitle.setForeground(Color.black);
					spellTitle.setFont(new Font("Arial",Font.BOLD,20));
					spellTitle.setBounds(640,20,150,30);
					bwcon1.add(spellTitle);
	 				JPanel spellPanel = new JPanel();
					spellPanel.setBounds(590, 50, 180,75);
					spellPanel.setBackground(Color.lightGray);
				
					JComboBox<String> spellChoose = new JComboBox<String>(SpellArray);
					((JLabel)spellChoose.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
					spellChoose.setBackground(Color.black);
					spellChoose.setForeground(Color.white);
					spellChoose.setFocusable(false);
					spellChoose.setMaximumRowCount(11);
					spellChoose.setFont(new Font("Times New Roman", Font.PLAIN, 17));
					spellPanel.add(spellChoose);
					bwcon1.add(spellPanel);
					
					JPanel randomButtonPanel = new JPanel();
					randomButtonPanel.setBounds(295,280,200,50);
					randomButtonPanel.setBackground(Color.lightGray);
					JButton randomButton = new JButton("RANDOM");
					randomButton.setBackground(Color.black);
					randomButton.setForeground(Color.white);
					randomButton.setFocusable(false);
					randomButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
					randomButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							SoundManager.click.play();
							chosens[1].build = new Build(new Random());
							buildWindow1.dispose();
							
						}
					});
					randomButtonPanel.add(randomButton);
					bwcon1.add(randomButtonPanel);
					
					JLabel msg = new JLabel( ("Create a build for " + chosens[1].name) );
					msg.setBounds(290, 240, 250, 50);
					msg.setFont(new Font("Arial",Font.BOLD,20));
					msg.setForeground(Color.black);
					bwcon1.add(msg);
					
					JPanel doneButtonPanel = new JPanel();
					doneButtonPanel.setBounds(320,330,150,100);
					doneButtonPanel.setBackground(Color.lightGray);
					JButton doneButton = new JButton("DONE");
					doneButton.setBackground(Color.black);
					doneButton.setForeground(Color.white);
					doneButton.setFocusable(false);
					doneButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
					doneButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							SoundManager.click.play();
							chosens[1].build = new Build(
									GadgetsArray[gadgetchoose.getSelectedIndex()], 
									Potions[potionCombo.getSelectedIndex()], 
									GearsArray[gearchoose.getSelectedIndex()],
									SpellArray[spellChoose.getSelectedIndex()]		
									);
							buildWindow1.dispose();			
						}
					});  
					doneButtonPanel.add(doneButton);
				    bwcon1.add(doneButtonPanel);
					
					}
				}
			});
		 build2Panel.add(build2);
		 con.add(build2Panel);
		
		quitPanel = new JPanel();
		quitPanel.setBounds(400,470,100,80);
		quitPanel.setBackground(Color.darkGray);
		quitPanel.setVisible(true);
		quitButton = new JButton("QUIT");
		quitButton.setVisible(true);
		quitButton.setForeground(Color.white);
		quitButton.setBackground(Color.black);
		quitButton.setFont(new Font("Times New Roman", Font.ITALIC, 25 ));
		quitButton.setFocusPainted(false);
		quitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SoundManager.click.play();
				System.exit(0);
				
			}
		});
		quitPanel.add(quitButton);
		
		//Patch Notes
		patchNotesPanel = new JPanel();
		patchNotesPanel.setBounds(520,470,220,80);
		patchNotesPanel.setBackground(Color.darkGray);
		patchNotesPanel.setVisible(true);
		patchNotes = new JButton("UPDATE NOTES");
		patchNotes.setVisible(true);
		patchNotes.setForeground(Color.white);
		patchNotes.setBackground(Color.black);
		patchNotes.setFont(new Font("Times New Roman", Font.ITALIC, 25 ));
		patchNotes.setFocusPainted(false);
		patchNotes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SoundManager.click.play();
				patchNotes(version.getText());
				
			}
		});
		patchNotesPanel.add(patchNotes);
		
		
		con.add(titleNamePanel);
		con.add(startButtonPanel);
		con.add(player1BrawlerPanel);
		con.add(player2BrawlerPanel);
		con.add(quitPanel);
		con.add(titleCreditPanel);
		con.add(helpPanel);
		con.add(patchNotesPanel);
		con.add(gamemodePanel);
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);	
		
	}	

	public static void playGame1v1(Brawler A1, Brawler B2, String gamemode) {
		
		SoundManager SM = new SoundManager();
				//Start
		SoundManager.intro.play();
				
		Brawler[] brawlers = Brawler.brawlers;
		
		Random ran = new Random();
		Brawler A = A1.newInstance();
		Brawler B = B2.newInstance();	
		
		String potionA = A.build.potionChoise;
		String potionB = B.build.potionChoise;
		
		
		if(gamemode == "Wizardary") {
			A.potionCount = 10;
			B.potionCount = 10;
		}
		
		if(gamemode == "Technical") {
			A.gadgetCount += 5;
			B.gadgetCount += 5;
		}

		
		JPanel Quit,
			   AtPanel1, AtPanel2,
			   SuPanel1, SuPanel2,
			   FFPanel1, FFPanel2,
			   remPanel,
			   GaPanel1, GaPanel2,
			   PoPanel1, PoPanel2
			   ;
		
		JLabel Name1, Name2,
			   title1, title2,
			   HP1, HP2,
			   SG1, SG2,
			   STATICON1, STATICON2,
			   REG1, REG2,
			   gMode,
			   Potion2Amount, Potion1Amount,
			   Gadget1Amount, Gadget2Amount,
			   turn,
			   cd1, cd2
		;
		
		JButton QuitButton,
			    Attack1, Attack2,
				Super1, Super2,
				FF1, FF2,
				Rematch,
				Gadget1, Gadget2,
				Potion1, Potion2,
				Spell1, Spell2
				;
		
		//All buttons
		Super1 = new JButton("Super");
		Super2 = new JButton("Super");
		Super1.setMnemonic('r');
		Super2.setMnemonic('r');
		Super1.setDisplayedMnemonicIndex(-1);
		Super2.setDisplayedMnemonicIndex(-1);
		FF1 = new JButton("Forfeit");
		FF2 = new JButton("Forfeit");
		Attack1 = new JButton("Attack");	
		Attack2 = new JButton("Attack");
		Attack1.setMnemonic('q');
		Attack2.setMnemonic('q');
		Gadget1 = new JButton("Gadget");
		Gadget2 = new JButton("Gadget");
		Gadget1.setMnemonic('w');
		Gadget2.setMnemonic('w');
		Potion1 = new JButton("Potion");
		Potion2 = new JButton("Potion");
		Potion1.setMnemonic('e');
		Potion2.setMnemonic('e');
		Spell1 = new JButton("Spell");
		Spell2 = new JButton("Spell");
		Spell1.setMnemonic('t');
		Spell2.setMnemonic('t');
		
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		ArrayList<JButton> P1buttons = new ArrayList<JButton>();
		ArrayList<JButton> P2buttons = new ArrayList<JButton>();
		buttons.add(Attack1); buttons.add(Attack2);
		buttons.add(Super1); buttons.add(Super2);
		buttons.add(FF1); buttons.add(FF2);
		buttons.add(Gadget1); buttons.add(Gadget2);
		buttons.add(Potion1); buttons.add(Potion2);
		buttons.add(Potion1); buttons.add(Potion2);
		buttons.add(Spell1); buttons.add(Spell2);
		P1buttons.add(Attack1); P2buttons.add(Attack2);
		P1buttons.add(Super1); P2buttons.add(Super2);
		P1buttons.add(FF1); P2buttons.add(FF2);
		P1buttons.add(Gadget1); P2buttons.add(Gadget2);
		P1buttons.add(Potion1); P2buttons.add(Potion2);
		P1buttons.add(Spell1); P2buttons.add(Spell2);
		
		for(JButton but : buttons)
			but.setFocusable(false);

		//Window
		JFrame window = new JFrame();
		window.setResizable(false);
		window.setTitle("TURN BY TURN!");
		window.setSize(1000,775);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.getContentPane().setBackground(Color.lightGray);
		window.setLayout(null);
		window.setResizable(false);
		window.setIconImage(new ImageIcon("res/images/turnbyturn.png").getImage());
		Container con = window.getContentPane();
		
		
		//NAMES
		Name1 = new JLabel(A.name.toUpperCase(Locale.US), JLabel.LEFT);
		Name1.setBounds(55,0,200,60);
		Name1.setFont(new Font("Arial", Font.BOLD, 40));
		Name2 = new JLabel(B.name.toUpperCase(Locale.US), JLabel.RIGHT);
		Name2.setBounds(735,0,200,60);
		Name2.setFont(new Font("Arial", Font.BOLD, 40));
		con.add(Name1); con.add(Name2);

		
		//TITLES
		title1 = new JLabel(A.title, JLabel.LEFT);
		title1.setBounds(55,25,200,60);
		title1.setFont(new Font("Arial", Font.BOLD, 15));
		title2 = new JLabel(B.title, JLabel.RIGHT);
		title2.setBounds(735,25,200,60);
		title2.setFont(new Font("Arial", Font.BOLD, 15));
		con.add(title1); con.add(title2);
		
		//HP
		HP1 = new JLabel("HP: " + A.HP);
		HP1.setBounds(55,140,265,50);
		HP1.setFont(new Font("Arial", Font.PLAIN, 45));
		HP2 = new JLabel(("HP: " + B.HP), JLabel.RIGHT);
		HP2.setBounds(668,140,265,50);
		HP2.setFont(new Font("Arial", Font.PLAIN, 45));
		con.add(HP1); con.add(HP2);
		
		//HP BAR
		HealthBar hpbar1 = new HealthBar(A.newInstance().HP, "P1");
		hpbar1.getHealthBar().setBounds(55,190,400,40);
		con.add(hpbar1.getHealthBar());
		HealthBar hpbar2 = new HealthBar(B.newInstance().HP, "P2");
		hpbar2.getHealthBar().setBounds(535,190,400,40);
		con.add(hpbar2.getHealthBar());
		 
		//ChargeBar
		ChargeBar scbar1 = new ChargeBar(A, "P1");
		scbar1.getChargeBar().setBounds(55,230,300,30);
		con.add(scbar1.getChargeBar());
		ChargeBar scbar2 = new ChargeBar(B, "P2");
		scbar2.getChargeBar().setBounds(635,230,300,30);
		con.add(scbar2.getChargeBar());
		
		//Shield BAR
		HealthBar shbar1 = new HealthBar(395, "P1");
		shbar1.healthBar.setForeground(Color.GRAY);
        shbar1.healthBar.setBackground(Color.white);
		shbar1.getHealthBar().setBounds(315,175,140,15);
		shbar1.healthBar.setValue(0);
		con.add(shbar1.getHealthBar());
		HealthBar shbar2 = new HealthBar(395, "P2");
		shbar2.healthBar.setForeground(Color.gray);
        shbar2.healthBar.setBackground(Color.white);
        shbar2.healthBar.setValue(0);
		shbar2.getHealthBar().setBounds(535,175,140,15);
		con.add(shbar2.getHealthBar());
		
		//Hypercharge BAR
		HyperBar hcbar1 = new HyperBar(A, "P1");
		hcbar1.getChargeBar().setBounds(55,260,300, 15);
		con.add(hcbar1.getChargeBar());
		HyperBar hcbar2 = new HyperBar(B, "P2");
		hcbar2.getChargeBar().setBounds(635,260,300,15);
		con.add(hcbar2.getChargeBar());
		
		ImageIcon empty = new ImageIcon("res/images/empty_slot.png");
		
		//Hypercharge icon
		ImageIcon hypercharged = new ImageIcon("res/images/hypercharged.png");
		
		JLabel hyperCountdown1 = new JLabel(A.hyperchargeTurn+"");
		hyperCountdown1.setBounds(420, 128 ,50 , 50);
		hyperCountdown1.setFont(new Font("Times New Roman", Font.BOLD, 20) );
		con.add(hyperCountdown1);
		JLabel hypericon1 = new JLabel(hypercharged);
		hypericon1.setBounds(405, 125 ,50 , 50);
		con.add(hypericon1);
		
		JLabel hyperCountdown2 = new JLabel(A.hyperchargeTurn+"");
		hyperCountdown2.setBounds(550, 128 ,50 , 50);
		hyperCountdown2.setFont(new Font("Times New Roman", Font.BOLD, 20) );
		con.add(hyperCountdown2);
		JLabel hypericon2 = new JLabel(hypercharged);
		hypericon2.setBounds(535, 125 ,50 , 50);
		con.add(hypericon2);
		
		//Brawler specific icon
		
		JLabel brawlerSpecific1 = new JLabel();
		brawlerSpecific1.setBounds(355, 125 ,50 , 50);
		brawlerSpecific1.setVisible(true);
		brawlerSpecific1.setIcon(empty);
		con.add(brawlerSpecific1);
		
		JLabel brawlerSpecific2 = new JLabel();
		brawlerSpecific2.setBounds(585, 125 ,50 , 50);
		brawlerSpecific2.setVisible(true);
		brawlerSpecific2.setIcon(empty);
		con.add(brawlerSpecific2);
		
		JLabel[] brawlerLabels = {
				hyperCountdown1, 
				hypericon1, 
				hyperCountdown2, 
				hypericon2, 
				brawlerSpecific1, //4
				brawlerSpecific2  //5
		};
		
		//REGEN
		REG1 = new JLabel("Regen: " + A.regen);
		REG1.setBounds(58,110,170,50);
		REG1.setFont(new Font("Arial", Font.PLAIN, 15));
		REG2 = new JLabel(("Regen: " + B.regen), JLabel.RIGHT);
		REG2.setBounds(760,110,170,50);
		REG2.setFont(new Font("Arial", Font.PLAIN, 15));
		con.add(REG1); con.add(REG2);
		 
		//SuperCharge
		SG1 = new JLabel(A.SuperCharge + "/100");
		SG1.setBounds(360,220,250,50);
		SG1.setFont(new Font("Arial", Font.PLAIN, 20));
		SG2 = new JLabel((B.SuperCharge + "/100"),JLabel.RIGHT);
		SG2.setBounds(380,220,250,50);
		SG2.setFont(new Font("Arial", Font.PLAIN, 20));
		con.add(SG1); con.add(SG2);
		
		//TURN
		TurnManager TM = new TurnManager();
		turn = new JLabel("Turn " + TM.getTurn());
		turn.setBounds(470,53,100,100);
		turn.setFont(new Font("Arial", Font.PLAIN, 20));
		con.add(turn);
		
		//Amounts
		Potion1Amount = new JLabel(A.potionCount + "/" + A.newInstance().potionCount);
		Potion2Amount = new JLabel(B.potionCount + "/" + B.newInstance().potionCount);
		Gadget1Amount = new JLabel(A.gadgetCount + "/" + A.newInstance().gadgetCount);
		Gadget2Amount = new JLabel(B.gadgetCount + "/" + B.newInstance().gadgetCount);
		Gadget2Amount.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//Cooldown
		cd1 = new JLabel(A.spell.currentCooldown+"");
		cd2 = new JLabel((B.spell.currentCooldown+""), JLabel.RIGHT);

		//Status
		ImageIcon stun = new ImageIcon("res/images/stun.png");
		ImageIcon weak = new ImageIcon("res/images/weak.png");
		ImageIcon strong = new ImageIcon("res/images/strong.png");
		ImageIcon confused = new ImageIcon("res/images/confused.png");
		ImageIcon normal = new ImageIcon("res/images/normal.png");
		ImageIcon guard = new ImageIcon("res/images/guard.png");
		ImageIcon raged = new ImageIcon("res/images/raged.png");
		ImageIcon frosty = new ImageIcon("res/images/frosty.png");
		ImageIcon scarred = new ImageIcon("res/images/scarred.png");
		ImageIcon intoxicated = new ImageIcon("res/images/intoxicated.png");
		ImageIcon[] icons = {
				normal,
				stun,
				strong,
				weak,
				confused,
				guard, //5
				raged,
				frosty,
				scarred,
				hypercharged, //9
				intoxicated
		};
		STATICON1 = new JLabel(normal);
		STATICON1.setBounds(10,7,50,45);
		STATICON2 = new JLabel(normal);
		STATICON2.setBounds(930,7,50,45);
		con.add(STATICON1); con.add(STATICON2);
		STATICON1.setVisible(true);
		STATICON2.setVisible(true);
		
		//GearIcon
		ImageIcon hpgear = new ImageIcon("res/images/hpgear.png");
		ImageIcon potgear = new ImageIcon("res/images/potiongear.png");
		ImageIcon gadgear = new ImageIcon("res/images/gadgetgear.png");
		ImageIcon healgear = new ImageIcon("res/images/healgear.png");
		ImageIcon cooldowngear = new ImageIcon("res/images/cooldowngear.png");
		ImageIcon regengear = new ImageIcon("res/images/regengear.png");
		ImageIcon hypergear = new ImageIcon("res/images/hypergear.png");
		JLabel GEARICON1 = new JLabel();
		switch(A.build.gearChoise) {
			case "HP GEAR":
				GEARICON1.setIcon(hpgear);
				break;
			case "POTION GEAR":
				GEARICON1.setIcon(potgear);
				break;
			case "GADGET GEAR":
				GEARICON1.setIcon(gadgear);
				break;
			case "HEAL GEAR":
				GEARICON1.setIcon(healgear);
				break;
			case "COOLDOWN GEAR":
				GEARICON1.setIcon(cooldowngear);
				break;
			case "REGEN GEAR":
				GEARICON1.setIcon(regengear);
				break;
			case "HYPERCHARGE GEAR":
				GEARICON1.setIcon(hypergear);
				break;
		}
		GEARICON1.setBounds(127,67,50,45);
		JLabel GEARICON2 = new JLabel();
		switch(B.build.gearChoise) {
		case "HP GEAR":
			GEARICON2.setIcon(hpgear);
			break;
		case "POTION GEAR":
			GEARICON2.setIcon(potgear);
			break;
		case "GADGET GEAR":
			GEARICON2.setIcon(gadgear);
			break;
		case "HEAL GEAR":
			GEARICON2.setIcon(healgear);
			break;
		case "COOLDOWN GEAR":
			GEARICON2.setIcon(cooldowngear);
			break;
		case "REGEN GEAR":
			GEARICON2.setIcon(regengear);
			break;
		case "HYPERCHARGE GEAR":
			GEARICON2.setIcon(hypergear);
			break;
		}
		GEARICON2.setBounds(810,67,50,45);
		con.add(GEARICON1); con.add(GEARICON2);
		GEARICON1.setVisible(true);
		GEARICON2.setVisible(true);
		
		
		//Gamemode Label
		gMode = new JLabel(gamemode);
		gMode.setBounds(7,698,150,50);
		gMode.setFont(new Font("Arial", Font.BOLD, 20));
		con.add(gMode);
		
		//Ability Names
		String[][] abilityNames = Build.abilityNames;
		
		int Aindex = 0;
		for (int i = 0; i < brawlers.length; i++)
			if (brawlers[i].name == A.name)
				Aindex = i;
		
		int Bindex = 0;
		for (int i = 0; i < brawlers.length; i++)
			if (brawlers[i].name == B.name)
				Bindex = i;
		
		Font ability = new Font("Arial",Font.BOLD, 16);		
		
		JLabel attackNameA = new JLabel(abilityNames[Aindex][0]);
		JLabel attackNameB = new JLabel((abilityNames[Bindex][0]), SwingConstants.RIGHT);
		attackNameA.setFont(ability); attackNameB.setFont(ability);
		attackNameA.setBounds(55,267,150,50);
		attackNameB.setBounds(780,267,150,50);
		con.add(attackNameA); con.add(attackNameB);
		
		JLabel superNameA = new JLabel(abilityNames[Aindex][1]);
		JLabel superNameB = new JLabel((abilityNames[Bindex][1]), SwingConstants.RIGHT);
		superNameA.setFont(ability); superNameB.setFont(ability);
		superNameA.setBounds(55,347,150,50);
		superNameB.setBounds(783,347,150,50);
		con.add(superNameA); con.add(superNameB);
		
		JLabel gadgetNameA = new JLabel(abilityNames[Aindex][2 + (A.build.gadgetChoise == "FIRST" ? 0 : 1)]);
		JLabel gadgetNameB = new JLabel((abilityNames[Bindex][2+ (B.build.gadgetChoise == "FIRST" ? 0 : 1)]), SwingConstants.RIGHT);
		gadgetNameA.setFont(ability); gadgetNameB.setFont(ability);
		gadgetNameA.setBounds(60,427,150,50);
		gadgetNameB.setBounds(780,427,150,50);
		con.add(gadgetNameA); con.add(gadgetNameB);
		
		JLabel[] abilityNamesArray = {
				attackNameA,
				superNameA,
				gadgetNameA,
				attackNameB,
				superNameB,
				gadgetNameB
		};
		
		//Potion
		String[] PotionsName = Build.PotionsName;
		
		String[] Potions = Build.Potions;
		
		int AindexP = -1;
		for(int i = 0; i < Potions.length; i++) {
			if(Potions[i].equals(A.build.potionChoise))
				AindexP = i;
		}
		
		int BindexP = -1;
		for(int i = 0; i < Potions.length; i++) {
			if(Potions[i].equals(B.build.potionChoise))
				BindexP = i;
		}		
		 
		JLabel potionNameA = new JLabel(PotionsName[AindexP]);
		JLabel potionNameB = new JLabel(PotionsName[BindexP], SwingConstants.RIGHT);
		potionNameA.setFont(ability); potionNameB.setFont(ability);
		potionNameA.setBounds(60,507,150,50);
		potionNameB.setBounds(780,507,150,50);
		con.add(potionNameA); con.add(potionNameB);
		
		//Spells
		JLabel spellNameA = new JLabel(A.build.spellChoise);
		JLabel spellNameB = new JLabel(B.build.spellChoise, SwingConstants.RIGHT);
		spellNameA.setFont(ability); spellNameB.setFont(ability);
		spellNameA.setBounds(60,587,200,50);
		spellNameB.setBounds(730,587,200,50);
		con.add(spellNameA); con.add(spellNameB);
		
		/*
		// Brawler Information Button
        JPanel infoPanel1 = new JPanel();
        infoPanel1.setBounds(178, 70, 40, 40);
        infoPanel1.setBackground(Color.LIGHT_GRAY);
        JButton infoButton1 = new JButton("info");
        infoButton1.setFont(new Font("Arial", Font.PLAIN, 15));
        infoButton1.setBackground(Color.black);
        infoButton1.setForeground(Color.white);
        infoButton1.setFocusable(false);
        infoButton1.setBorderPainted(false);
        infoPanel1.add(infoButton1);
        window.add(infoPanel1);

        // Create the info window
        JLabel infoLabel = new JLabel("Brawler information goes here.");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        infoLabel.setLocation(window.getX() + infoPanel1.getX() + infoPanel1.getWidth() + 300, window.getY() + infoPanel1.getY() + 300);
        infoLabel.setBackground(Color.LIGHT_GRAY);
        infoLabel.setVisible(false);
        infoButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("here");
                infoLabel.setVisible(true);
                String str = "<html>HP: " + A.HP
                        + "<br>Attack Damage: " + A.AttackDamage
                        + "<br>Super Charge: " + A.SuperCharge
                        + "<br>Super Damage: " + A.SuperDamage
                        + "<br>Regeneration: " + A.regen
                        + "<br>Shield: " + A.shield
                        + "<br>Status: " + (A.stat != null ? A.stat.toString() : "None")
                        + "<br>Hypercharge: " + A.HyperCharge
                        + "<br>Hypercharge Turn: " + A.hyperchargeTurn
                        + "<br>Is Hypercharged: " + A.isHypercharged
                        + "<br>Specific Uses: " + A.hak
                        + "</html>";
                infoLabel.setText(str);
            }
        });
		
		
		infoPanel1.add(infoButton1);
		con.add(infoPanel1);
		window.add(infoLabel);
		*/
		
		
		//Brawler Specific Button
		
		JPanel SpePanel1 = new JPanel();
		SpePanel1.setBounds(165, 370, 60, 60);
		SpePanel1.setBackground(Color.lightGray);
		JButton Specific1 = new JButton();
		Specific1.setFont(new Font("Arial", Font.PLAIN, 20));
		Specific1.setBackground(Color.LIGHT_GRAY);
		Specific1.setForeground(Color.white);
		Specific1.setFocusable(false);
		Specific1.setBorderPainted(false);
		Specific1.setVisible(true);
		
		P1buttons.add(Specific1);
		Specific1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				A.brawlerSpecificActivity(B);					
				enableOrDisable(A, B, P1buttons, P2buttons, "SPECIFIC");
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2, shbar1, shbar2, scbar1, scbar2, hcbar1, hcbar2, TM, turn,Name1, Name2 ,abilityNamesArray, brawlerLabels, gamemode);					
			}
		});
		
		SpePanel1.setVisible(false);
		SpePanel1.add(Specific1);
		con.add(SpePanel1);
		
		
		JPanel SpePanel2 = new JPanel();
		SpePanel2.setBounds(760, 370, 60, 60);
		SpePanel2.setBackground(Color.lightGray);
		JButton Specific2 = new JButton();
		Specific2.setFont(new Font("Arial", Font.PLAIN, 20));
		Specific2.setBackground(Color.LIGHT_GRAY);
		Specific2.setForeground(Color.white);
		Specific2.setFocusable(false);
		Specific2.setBorderPainted(false);
		Specific2.setVisible(true);
		
		P2buttons.add(Specific2);
		
		//SPESIFIC ABILITY
		Specific2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				B.brawlerSpecificActivity(A);					
				enableOrDisable(A, B, P1buttons, P2buttons, "SPECIFIC");
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2, shbar1, shbar2, scbar1, scbar2, hcbar1, hcbar2, TM, turn,Name1, Name2 ,abilityNamesArray, brawlerLabels, gamemode);					
			}
		});
		
		SpePanel2.setVisible(false);
		SpePanel2.add(Specific2);
		con.add(SpePanel2);
		
		if(A.name.equals("Qirale")) {						
			SpePanel1.setVisible(true);		
			Specific1.setIcon(new Qirale(new Build()).qirale_specific);
		}
		
		if(B.name.equals("Qirale")) {						
			SpePanel2.setVisible(true);		
			Specific2.setIcon(new Qirale(new Build()).qirale_specific);
		}
		
		//Attack
		AtPanel1 = new JPanel();
		AtPanel1.setBounds(55, 300, 100, 50);
		AtPanel1.setBackground(Color.lightGray);
		Attack1.setFont(new Font("Arial", Font.PLAIN, 25));
		Attack1.setBackground(new Color(66, 66, 66));
		Attack1.setForeground(Color.white);
		Attack1.setVisible(true);
		//NORMAL ATTACK
		Attack1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enableOrDisable(A, B, P1buttons, P2buttons, "ALL");
				attack(A, B);				
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2, shbar1, shbar2, scbar1, scbar2, hcbar1, hcbar2, TM, turn,Name1, Name2 ,abilityNamesArray, brawlerLabels, gamemode);
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2,scbar1, scbar2, buttons);		
				superChargeCheck(A, Super1, B, Super2);
			}
		});
		
		AtPanel2 = new JPanel();
		AtPanel2.setBounds(835, 300, 100, 50);
		AtPanel2.setBackground(Color.lightGray);
		Attack2.setFont(new Font("Arial", Font.PLAIN, 25));
		Attack2.setBackground(new Color(66, 66, 66));
		Attack2.setForeground(Color.white);
		Attack2.setVisible(true);
		//NORMAL ATTACK
		Attack2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enableOrDisable(B, A, P2buttons, P1buttons, "ALL");
				attack(B, A);
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2, scbar1, scbar2, hcbar1, hcbar2, TM, turn,Name1, Name2,abilityNamesArray, brawlerLabels, gamemode);	
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2, scbar1, scbar2, buttons);	
				superChargeCheck(A, Super1, B, Super2);
			}
		});
		AtPanel1.add(Attack1); AtPanel2.add(Attack2);
		con.add(AtPanel1); con.add(AtPanel2);
 
		//Supers
		SuPanel1 = new JPanel();
		SuPanel1.setBounds(55, 380, 100, 50);
		SuPanel1.setBackground(Color.lightGray);
		Super1.setFont(new Font("Arial", Font.PLAIN, 25));
		Super1.setBackground(new Color(66, 66, 66));
		Super1.setForeground(Color.white);
		Super1.setVisible(true);
		//SUPER ATTACK
		Super1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean isDone = superAbility(A, B , Super1, Super2, TM);
				if (isDone) {
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2, shbar1, shbar2, scbar1, scbar2, hcbar1, hcbar2, TM, turn,Name1, Name2,abilityNamesArray, brawlerLabels, gamemode);
				enableOrDisable(A, B, P1buttons, P2buttons, "ALL");
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2, scbar1, scbar2, buttons);	
				superChargeCheck(A, Super1, B, Super2);
				}
			}
					
				
				
				
			});
		SuPanel1.add(Super1);
		con.add(SuPanel1);
		
		
		SuPanel2 = new JPanel();
		SuPanel2.setBounds(835, 380, 100, 50);
		SuPanel2.setBackground(Color.lightGray);
		Super2.setFont(new Font("Arial", Font.PLAIN, 25));
		Super2.setBackground(new Color(66, 66, 66));
		Super2.setForeground(Color.white);
		Super2.setVisible(true);
		//SUPER ATTACK
		Super2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				boolean isDone = superAbility(B, A , Super2, Super1,TM);
				if (isDone) {
				superChargeCheck(A, Super1, B, Super2);
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2, scbar1, scbar2, hcbar1, hcbar2, TM, turn,Name1, Name2,abilityNamesArray, brawlerLabels, gamemode);
				enableOrDisable(B, A, P2buttons, P1buttons, "ALL");
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2,scbar1, scbar2, buttons);	
				superChargeCheck(A, Super1, B, Super2);
				}
				
			}
			});
		SuPanel2.add(Super2);
		con.add(SuPanel2);
		
		
		//GADGETS
		GaPanel1 = new JPanel();
		GaPanel1.setBounds(60, 460, 95, 50);
		GaPanel1.setBackground(Color.lightGray);
		Gadget1.setFont(new Font("Arial", Font.PLAIN, 25));
		Gadget1.setBackground(new Color(66, 66, 66));
		Gadget1.setForeground(Color.white);
		Gadget1.setVisible(true);
		Gadget1Amount.setBounds(160,460,50,50);
		Gadget1Amount.setFont(new Font("Arial", Font.PLAIN, 20));
		con.add(Gadget1Amount);
		Gadget1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SM.gadget.play();
				gadget(A, B);
				superChargeCheck(A, Super1, B, Super2);
				enableOrDisable(A, B, P1buttons, P2buttons, "GADGET_POTION");
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2,scbar1, scbar2,  hcbar1, hcbar2,TM, turn,Name1, Name2,abilityNamesArray, brawlerLabels, gamemode);
				cooldownCheck(A, Spell1);
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2,scbar1, scbar2, buttons);	
				superChargeCheck(A, Super1, B, Super2);
				
			}
		});
		
		GaPanel1.add(Gadget1);
		con.add(GaPanel1);
		
		GaPanel2 = new JPanel();
		GaPanel2.setBounds(835, 460, 95, 50);
		GaPanel2.setBackground(Color.lightGray);
		Gadget2.setFont(new Font("Arial", Font.PLAIN, 25));
		Gadget2.setBackground(new Color(66, 66, 66));
		Gadget2.setForeground(Color.white);
		Gadget2.setVisible(true);
		Gadget2Amount.setBounds(780,462,50,50);
		Gadget2Amount.setFont(new Font("Arial", Font.PLAIN, 20));
		con.add(Gadget2Amount);
		Gadget2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SM.gadget.play();
				gadget(B, A);
				superChargeCheck(A, Super1, B, Super2);
				enableOrDisable(B, A, P2buttons,P1buttons , "GADGET_POTION");
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2, scbar1, scbar2, hcbar1, hcbar2, TM, turn,Name1, Name2,abilityNamesArray, brawlerLabels, gamemode);
				cooldownCheck(B, Spell2);
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2,scbar1, scbar2, buttons);	
				superChargeCheck(A, Super1, B, Super2);
				
			}
		});
		
		
		
		GaPanel2.add(Gadget2);
		con.add(GaPanel2);
		
		
		//POTIONS
 
		
		//A

		PoPanel1 = new JPanel();
		PoPanel1.setBounds(60, 540, 95, 50);
		PoPanel1.setBackground(Color.lightGray);
		Potion1.setFont(new Font("Arial", Font.PLAIN, 25));
		Potion1.setBackground(new Color(66, 66, 66));
		Potion1.setForeground(Color.white);
		Potion1.setVisible(true);
		
		Potion1Amount.setBounds(160,540,50,50);
		Potion1Amount.setFont(new Font("Arial", Font.PLAIN, 20));
		con.add(Potion1Amount);

		
		switch(potionA) {
		case "Red":
			Potion1.setBackground(Color.red);
			Potion1.setForeground(Color.white);
			break;
		case "Yellow":
			Potion1.setBackground(Color.yellow);
			Potion1.setForeground(Color.black);
			break;
		case "Dark Purple":
			Potion1.setBackground(new Color(128,0,128));
			Potion1.setForeground(Color.white);
			break;
		case "Dark Blue":
			Potion1.setBackground(new Color(5, 6, 99));
			Potion1.setForeground(Color.white);
			break;
		case "Dark Red":
			Potion1.setBackground(new Color(168, 1 , 1));
			Potion1.setForeground(Color.white);
			break;
		case "Green":
			Potion1.setBackground(Color.green);
			Potion1.setForeground(Color.black);
			break;
		case "Gray":
			Potion1.setBackground(Color.GRAY);
			Potion1.setForeground(Color.white);
			break;
		case "Dark Gray":
			Potion1.setBackground(Color.darkGray);
			Potion1.setForeground(Color.white);
			break;
		case "Brown":
			Potion1.setBackground(new Color(150, 75 , 0));
			Potion1.setForeground(Color.white);
			break;
		case "Light Purple":
			Potion1.setBackground(new Color(181, 11 , 204));
			Potion1.setForeground(Color.white);
			break;
			
		}
		Potion1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SM.potion.play();
				potion(A, B, potionA, Potion1, Super1);
				superChargeCheck(A, Super1, B, Super2);
				enableOrDisable(A, B, P1buttons, P2buttons, "GADGET_POTION");
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2,scbar1, scbar2,  hcbar1, hcbar2,TM, turn,Name1, Name2,abilityNamesArray, brawlerLabels, gamemode);
				cooldownCheck(A, Spell1);
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2, scbar1, scbar2, buttons);	
				superChargeCheck(A, Super1, B, Super2);
				
			}
		});
		
		
			
		PoPanel1.add(Potion1);
		con.add(PoPanel1);
				
		
		
		//B
		PoPanel2 = new JPanel();
		PoPanel2.setBounds(835, 540, 95, 50);
		PoPanel2.setBackground(Color.lightGray);
		Potion2.setFont(new Font("Arial", Font.PLAIN, 25));
		Potion2.setBackground(new Color(66, 66, 66));
		Potion2.setForeground(Color.white);
		Potion2.setVisible(true);
		Potion2Amount.setBounds(800,540,50,50);
		Potion2Amount.setFont(new Font("Arial", Font.PLAIN, 20));
		con.add(Potion2Amount);

		
		switch(potionB) {
		case "Red":
			Potion2.setBackground(Color.red);
			Potion2.setForeground(Color.white);
			break;
		case "Yellow":
			Potion2.setBackground(Color.yellow);
			Potion2.setForeground(Color.black);
			break;
		case "Dark Purple":
			Potion2.setBackground(new Color(128,0,128));
			Potion2.setForeground(Color.white);
			break;
		case "Dark Blue":
			Potion2.setBackground(new Color(5, 6, 99));
			Potion2.setForeground(Color.white);
			break;
		case "Dark Red":
			Potion2.setBackground(new Color(168, 1 , 1));
			Potion2.setForeground(Color.white);
			break;
		case "Green":
			Potion2.setBackground(Color.green);
			Potion2.setForeground(Color.black);
			break;
		case "Gray":
			Potion2.setBackground(Color.GRAY);
			Potion2.setForeground(Color.white);
			break;
		case "Dark Gray":
			Potion2.setBackground(Color.darkGray);
			Potion2.setForeground(Color.white);
			break;
		case "Brown":
			Potion2.setBackground(new Color(150, 75 , 0));
			Potion2.setForeground(Color.white);
			break;
		case "Light Purple":
			Potion2.setBackground(new Color(181, 11 , 204));
			Potion2.setForeground(Color.white);
			break;
			
		}
		
		Potion2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SM.potion.play();
				potion(B, A, potionB, Potion2, Super2);
				superChargeCheck(A, Super1, B, Super2);			
				enableOrDisable(B, A, P2buttons, P1buttons, "GADGET_POTION");
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2,scbar1, scbar2,  hcbar1, hcbar2,TM, turn,Name1, Name2,abilityNamesArray, brawlerLabels, gamemode);
				cooldownCheck(B, Spell2);
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2, scbar1, scbar2, buttons);	
				superChargeCheck(A, Super1, B, Super2);
			}
		});
			
		PoPanel2.add(Potion2);
		con.add(PoPanel2);
		
		
	 
		//Spells
		JPanel SpellPanel1 = new JPanel();
		SpellPanel1.setBounds(55, 620, 100, 50);
		SpellPanel1.setBackground(Color.lightGray);
		Spell1.setFont(new Font("Arial", Font.PLAIN, 25));
		cd1.setFont(new Font("Arial", Font.PLAIN, 20));
		cd1.setBounds(60,650,50,50);
		con.add(cd1);
		Spell1.setBackground(new Color(66, 66, 66));
		Spell1.setForeground(Color.white);
		Spell1.setVisible(true);
		Spell1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spell(A, B, A.spell);
				superChargeCheck(A, Super1, B, Super2);
				enableOrDisable(A, B, P1buttons, P2buttons, (A.spell.doesSkip ? "ALL" : "SPELL") );
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2,scbar1, scbar2,  hcbar1, hcbar2,TM, turn,Name1, Name2,abilityNamesArray, brawlerLabels, gamemode);
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2,scbar1, scbar2, buttons);		
				superChargeCheck(A, Super1, B, Super2);
			}
		});
		
		JPanel SpellPanel2 = new JPanel();
		SpellPanel2.setBounds(835, 620, 100, 50);
		SpellPanel2.setBackground(Color.lightGray);
		cd2.setFont(new Font("Arial", Font.PLAIN, 20));
		cd2.setBounds(877,650,50,50);
		con.add(cd2);
		Spell2.setFont(new Font("Arial", Font.PLAIN, 25));
		Spell2.setBackground(new Color(66, 66, 66));
		Spell2.setForeground(Color.white);
		Spell2.setVisible(true);
		Spell2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
				spell(B, A, B.spell);
				superChargeCheck(A, Super1, B, Super2);
				enableOrDisable(B, A, P2buttons, P1buttons, (B.spell.doesSkip ? "ALL" : "SPELL"));
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2, scbar1, scbar2, hcbar1, hcbar2, TM, turn,Name1, Name2,abilityNamesArray, brawlerLabels, gamemode);	
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2, scbar1, scbar2, buttons);	
				superChargeCheck(A, Super1, B, Super2);
			}
		});
		SpellPanel1.add(Spell1); SpellPanel2.add(Spell2);
		con.add(SpellPanel1); con.add(SpellPanel2);
		
		//Forfeit
				FFPanel1 = new JPanel();
				FFPanel1.setBounds(30,70,125,30);
				FFPanel1.setBackground(Color.LIGHT_GRAY);
				buttons.add(FF1); 
				buttons.add(FF2);
				FF1.setBackground(Color.black);
				FF1.setForeground(Color.white);
				FF1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						A.HP -= 99999;
						hpbar1.updateHealth(A);
						hpbar2.updateHealth(B);
						HP1.setText("HP: " + A.HP);
						isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2, scbar1, scbar2, P2buttons);
						
					}
				});
				FFPanel1.add(FF1);
				con.add(FFPanel1);
				
				
				FFPanel2 = new JPanel();
				FFPanel2.setBounds(835,70,125,30);
				FFPanel2.setBackground(Color.LIGHT_GRAY);
				FF2.setBackground(Color.black);
				FF2.setForeground(Color.white);
				FF2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						B.HP -= 99999;
						hpbar1.updateHealth(A);
						hpbar2.updateHealth(B);
						HP2.setText("HP: " + B.HP);
						isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2, scbar1, scbar2, P2buttons);
						
					}
				});
				FFPanel2.add(FF2);
				con.add(FFPanel2);
		
		//Quitting
		Quit = new JPanel();
		Quit.setBounds(450,10,100,40);
		Quit.setBackground(Color.lightGray);
		QuitButton = new JButton("QUIT");
		QuitButton.setMnemonic('X');
		QuitButton.setBackground(Color.black);
		QuitButton.setForeground(Color.white);
		QuitButton.setFont(new Font("Times New Roman", Font.ITALIC, 20 ));
		QuitButton.setFocusPainted(false);
		QuitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				A.reset();
				B.reset();
				window.dispose();
				
			}
		});
		Quit.add(QuitButton);
		con.add(Quit);
		
		//Rematch
		remPanel = new JPanel();
		remPanel.setBounds(436,50,125,40);
		remPanel.setBackground(Color.lightGray);
		Rematch = new JButton("REMATCH");
		Rematch.setMnemonic(KeyEvent.VK_Z);
		Rematch.setBackground(Color.black);
		Rematch.setForeground(Color.white);
		Rematch.setFont(new Font("Times New Roman", Font.ITALIC, 20 ));
		Rematch.setFocusPainted(false);
		Rematch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SoundManager.intro.play();
				//Stats
				
				TM.resetTurn();
				A.spell.currentCooldown = 0;
				B.spell.currentCooldown = 0;
				A.shield = 0;
				B.shield = 0;
				A.reset();
				B.reset();
				
				if(A.spell.name == "Demonic Cuteness" || A.spell.name == "Hypercharge") {
					A.spell.effectTurn = 0;
				}
				if(B.spell.name == "Demonic Cuteness" || B.spell.name == "Hypercharge") {
					B.spell.effectTurn = 0;
				}
				 
				HP1.setText("HP: " + A.HP);
				HP2.setText(("HP: " + B.HP));
				SG1.setText(A.SuperCharge + "/100");
				SG2.setText(B.SuperCharge + "/100");
				REG1.setText("Regen: " + A.regen);
				REG2.setText("Regen: " + B.regen);
				 
				//buttons
				for (JButton but : buttons)
					but.setEnabled(true);
				
				
				A.potionCount = A.newInstance().potionCount;
				B.potionCount = B.newInstance().potionCount;
				if(gamemode == "Wizardary") {
					A.potionCount += 3;
					B.potionCount += 3;
				}
				
				switch(potionA) {
				case "Red":
					Potion1.setBackground(Color.red);
					Potion1.setForeground(Color.white);
					break;
				case "Yellow":
					Potion1.setBackground(Color.yellow);
					Potion1.setForeground(Color.black);
					break;
				case "Light Purple":
					Potion1.setBackground(new Color(128,0,128));
					Potion1.setForeground(Color.white);
					break;
				case "Dark Blue":
					Potion1.setBackground(new Color(5, 6, 99));
					Potion1.setForeground(Color.white);
					break;
				case "Dark Red":
					Potion1.setBackground(new Color(168, 1 , 1));
					Potion1.setForeground(Color.white);
					break;
				case "Green":
					Potion1.setBackground(Color.green);
					Potion1.setForeground(Color.black);
					break;
				case "Gray":
					Potion1.setBackground(Color.GRAY);
					Potion1.setForeground(Color.white);
					break;
				case "Dark Purple":
					Potion1.setBackground(new Color(181, 11 , 204));
					Potion1.setForeground(Color.white);
					break;
				}
				 
				switch(potionB) {
				case "Red":
					Potion2.setBackground(Color.red);
					Potion2.setForeground(Color.white);
					break;
				case "Yellow":
					Potion2.setBackground(Color.yellow);
					Potion2.setForeground(Color.black);
					break;
				case "Light Purple":
					Potion2.setBackground(new Color(128,0,128));
					Potion2.setForeground(Color.white);
					break;
				case "Dark Blue":
					Potion2.setBackground(new Color(5, 6, 99));
					Potion2.setForeground(Color.white);
					break;
				case "Dark Red":
					Potion2.setBackground(new Color(168, 1 , 1));
					Potion2.setForeground(Color.white);
					break;
				case "Green":
					Potion2.setBackground(Color.green);
					Potion2.setForeground(Color.black);
					break;
				case "Gray":
					Potion2.setBackground(Color.GRAY);
					Potion2.setForeground(Color.white);
					break;
				case "Dark Purple":
					Potion2.setBackground(new Color(181, 11 , 204));
					Potion2.setForeground(Color.white);
					break;
					
				}
	
				STATICON1.setEnabled(true);
				STATICON2.setEnabled(true);
				STATICON1.setIcon(icons[0]);
				STATICON2.setIcon(icons[0]);
				
				A.hyperchargeTurn = -1;
				B.hyperchargeTurn = -1;
				
				Name1.setForeground(Color.black);
				Name2.setForeground(Color.black);
				Super1.setBackground(new Color(66, 66, 66));
				Super2.setBackground(new Color(66, 66, 66));
				Super1.setForeground(Color.white);
				Super2.setForeground(Color.white);	
				Potion1Amount.setText(A.potionCount + "/" + A.newInstance().potionCount);
				Potion2Amount.setText(B.potionCount + "/" + B.newInstance().potionCount);
				Gadget1Amount.setText(A.gadgetCount + "/" + A.newInstance().gadgetCount);
				Gadget2Amount.setText(B.gadgetCount + "/" + B.newInstance().gadgetCount);
				hpbar1.updateHealth(A);
				hpbar2.updateHealth(B);
				scbar1.updateCharge(A);
				scbar2.updateCharge(B);
				hcbar1.updateCharge(A);
				hcbar2.updateCharge(B);
				
				//Determine the starter
				int turny = ran.nextInt(0, 2);
				if(turny == 0) {
					Attack2.setEnabled(false);
				    Super2.setEnabled(false);
					Gadget2.setEnabled(false);
					Potion2.setEnabled(false);
					Spell2.setEnabled(false);
				}
				else {
					Attack1.setEnabled(false);
				    Super1.setEnabled(false);
				    Gadget1.setEnabled(false);
				    Potion1.setEnabled(false);
				    Spell1.setEnabled(false);
				}
				
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2, shbar1, shbar2, scbar1, scbar2,  hcbar1, hcbar2,TM, turn ,Name1, Name2,abilityNamesArray, brawlerLabels, gamemode);
				
				
			}
		});
		remPanel.add(Rematch);
		con.add(remPanel);
		
		
		//Determine the starter
		int turny = ran.nextInt(0, 2);
		if(turny == 0) {
			Attack2.setEnabled(false);
		    Super2.setEnabled(false);
			Gadget2.setEnabled(false);
			Potion2.setEnabled(false);
			Spell2.setEnabled(false);
		}
		else {
			Attack1.setEnabled(false);
		    Super1.setEnabled(false);
		    Gadget1.setEnabled(false);
		    Potion1.setEnabled(false);
		    Spell1.setEnabled(false);
		}
				
				
		//The window!
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		

		update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2,STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2,scbar1, scbar2,  hcbar1, hcbar2,TM, turn, Name1, Name2,abilityNamesArray, brawlerLabels, gamemode);
	}	
	 
	public static void attack(Brawler attacker, Brawler attacked) {	
		
		//Confused
		if (attacker.stat == Status.Confused) {
			attacker.attackAbility(attacker);
			attacker.changeSTATUS(Status.Normal);
			return;
		}
		
		//Guarded
		if (attacked.stat == Status.Guarded) {
			new SoundManager().noten.play();
			attacked.changeSTATUS(Status.Normal);
			return;
		}
		
		//Stun
		if (attacked.stat == Status.Stunned) {
			attacked.changeREGEN(-2);
			attacked.changeSTATUS(Status.Normal);
		}
		
		attacker.attackAbility(attacked);
		
		
		
		
		}
	
	public static boolean superAbility(Brawler attacker, Brawler attacked, JButton Super1, JButton Super2, TurnManager TM) {

	    SoundManager SM = new SoundManager();

	    if (attacker.SuperCharge >= 100) {

	        int enemyHPprev = attacked.shield + attacked.HP;
	        Status initialEnemyStatus = attacked.stat;

	        attacker.superAbility(attacked);
	        attacker.changeCHARGE(-100);

	        // Calculate the final state of the attacked Brawler
	        int enemyHPnext = attacked.shield + attacked.HP;

	        // If they attacked the enemy and the super ability dealt damage
	        if ((enemyHPnext - enemyHPprev != 0) && (initialEnemyStatus == Status.Stunned && attacked.stat != Status.Stunned)) {
	            // Check if the enemy was stunned and got hit by a super that stuns
	            if (initialEnemyStatus != Status.Stunned || attacked.stat == Status.Stunned) {
	                attacked.changeREGEN(-2);
	                attacked.changeSTATUS(Status.Normal);
	            }
	        }

	        return true;
	    } else {
	        SM.noten.play();
	        return false;
	    }
	}
		
	public static void superChargeCheck(Brawler A, JButton asuper, Brawler B, JButton bsuper) {

		//If Super is charged show
		if (A.SuperCharge >= 100) {
			asuper.setBackground(Color.yellow);
			asuper.setForeground(Color.black);
			if(A.isHypercharged) {
				asuper.setBackground(Color.magenta);
				asuper.setForeground(Color.white);
			}
		}
		else {
			asuper.setBackground(new Color(66, 66, 66));
			asuper.setForeground(Color.white);
		}
		
		if (B.SuperCharge >= 100) {
			bsuper.setBackground(Color.yellow);
			bsuper.setForeground(Color.black);
			if(B.isHypercharged) {
				bsuper.setBackground(Color.magenta);
				bsuper.setForeground(Color.white);
			}
		}
		else {
			bsuper.setBackground(new Color(66, 66, 66));
			bsuper.setForeground(Color.white);
		}
	}
	
	public static void regenerate(Brawler A, Brawler B) {
		if(A.stat != Status.Stunned && (A.stat != Status.Frosty || A.regen < 0) )
			A.regenerate();
		if(B.stat != Status.Stunned && (B.stat != Status.Frosty || B.regen < 0))
			B.regenerate();
	}
	
	public static void eachTurnPassives(Brawler A, Brawler B) {
		
		SoundManager SM = new SoundManager();
		
		//Hypercharge
		A.passiveHyperCharge();
		B.passiveHyperCharge();
		
		//Regen
		regenerate(A, B);
		
		//Shield Decay
			if (A.shield < 5 && A.shield > -1)
					A.shield = 0;
			if (A.shield > 5)
					A.changeSHIELD(-5);
				
			if (B.shield < 5 && B.shield > -1)
					B.shield = 0;
			if (B.shield > 5)
					B.changeSHIELD(-5);
				
		//Scar check
			if(A.stat == Status.Scarred) {
					A.changeHP((int)(A.HP*-0.015));	
				}
			if(B.stat == Status.Scarred) {
					B.changeHP((int)(B.HP*-0.015));	
				}
	
		//Toxic Check
			if(A.stat == Status.Intoxicated) {
				A.changeHP((int)(A.newInstance().HP*-0.05 * (A.name.equals("Olea") ? 0 : 1)));	
				A.changeSTATUS(Status.Normal);	
			}
			if(B.stat == Status.Intoxicated) {
				B.changeHP((int)(B.newInstance().HP*-0.05 * (B.name.equals("Olea") ? 0 : 1)));	
				B.changeSTATUS(Status.Normal);	
			}
				
		//Spell Cool downs
			if(A.spell.currentCooldown != 0)
					A.spell.currentCooldown--;
			if(B.spell.currentCooldown != 0)
					B.spell.currentCooldown--;
			
			//Light Super
			if(A.name.equals("Light")) {
				Light temp = (Light) A;
				if(temp.lightInt == 0) {
					SM.light_superdeath.play();
					B.HP = -9999;
				}
				if(temp.lightInt != -1) {
					temp.lightInt--;
				}
			}
			
			if(B.name.equals("Light")) {
				Light temp = (Light) B;
				if(temp.lightInt == 0) {
					SM.light_superdeath.play();
					A.HP = -9999;
				}
				if(temp.lightInt != -1) {
					temp.lightInt--;
				}
			}
			
			//Louis Super
			if(A.name.equals("Louis")) {
				Louis temp = (Louis) A;	
				if(temp.passiveTurns == 0) {					
					B.changeHP(temp.decreasedHP);
					A.changeHP(-temp.decreasedHP/2);
					temp.decreasedHP = 0;
					temp.louis_superburst.play();
					try {Thread.sleep(300);}catch(Exception e){}
				}			
				if(temp.passiveTurns >= 0)
					temp.passiveTurns--;			
			}
			
			if(B.name.equals("Louis")) {
				Louis temp = (Louis) B;	
				if(temp.passiveTurns == 0) {					
					A.changeHP(temp.decreasedHP);
					B.changeHP(-temp.decreasedHP/2);
					temp.decreasedHP = 0;			
					temp.louis_superburst.play();
				}			
				if(temp.passiveTurns >= 0)
					temp.passiveTurns--;			
			}
			
			//Olea Super
			if(A.name.equals("Olea")) {
				Olea temp = (Olea) A;
				if(temp.superTurns > 0) {
					B.changeHP(-temp.SuperDamage);
					A.changeSTATUS(Status.Intoxicated);
					B.changeSTATUS(Status.Intoxicated);
					temp.superTurns--;
				}
			}
			
			if(B.name.equals("Olea")) {
				Olea temp = (Olea) B;
				if(temp.superTurns > 0) {
					A.changeHP(-temp.SuperDamage);
					A.changeSTATUS(Status.Intoxicated);
					B.changeSTATUS(Status.Intoxicated);
					temp.superTurns--;
				}
			}
		
			//Itan Super
			if(A.name.equals("Itan")) {
				Itan temp = (Itan) A;
				if(temp.BeurcHP > 0) {
					Itan.itan_super_attack.play();
					B.changeHP(-temp.SuperDamage);
					A.changeHP(10);
				}
			}

			if(B.name.equals("Itan")) {
				Itan temp = (Itan) B;
				if(temp.BeurcHP > 0) {
					Itan.itan_super_attack.play();
					A.changeHP(-temp.SuperDamage);
					B.changeHP(10);
				}
			}
			
			//Missy Passive
			if(A.name == "Missy" && A.regen < 0)
				A.changeREGEN(1);
			if(B.name == "Missy" && B.regen < 0)
				B.changeREGEN(1);
			
			//John Passive
			if(A.name == "John" && A.HP < 0)
				attack(A, B);
			if(B.name == "John" && B.HP < 0)
				attack(B, A);
			
			//Mark Passive
			if(A.name == "Mark")
				A.changeREGEN(-1);
			if(B.name == "Mark")
				B.changeREGEN(-1);
			
			//Crow Passive
			if(A.name == "Crow" && B.regen < 0)
				A.changeCHARGE(B.regen / -3);
			if(B.name == "Crow" && A.regen < 0)
				B.changeCHARGE(A.regen / -3);
			
			//Effective Spells
			if(A.spell.effectTurn != -1) {
				switch(A.spell.name) {
				
				case "Demonic Cuteness":
					if(A.spell.effectTurn == 0)
						break;
					B.changeHP(-15);
					SM.demonic_burn.play();
					A.spell.effectTurn--;
				break;
				
				case "Meditation Medication":
					if(A.spell.effectTurn == 0) {
						A.changeHP(100);
						A.spell.effectTurn--;
						break;
					}
					A.spell.effectTurn--;
				break;
				
				}
			}
			
			if(B.spell.effectTurn != -1) {
				switch(B.spell.name) {
				
				case "Demonic Cuteness":
					if(B.spell.effectTurn == 0)
						break;
					A.changeHP(-15);
					SM.demonic_burn.play();
					B.spell.effectTurn--;
				break;
				
				case "Meditation Medication":
					if(B.spell.effectTurn == 0) {
						B.changeHP(100);
						B.spell.effectTurn--;
						break;
					}					
					B.spell.effectTurn--;
				break;
				
				}
			}
			
	//end of eachturnpassives function		
	}

	public static void update(Brawler A, 
							  Brawler B, 
							  JLabel HP1, 
							  JLabel HP2 ,
							  JLabel SG1, 
							  JLabel SG2 ,
							  JLabel REG1, 
							  JLabel REG2,
							  JLabel Potion1Amount, 
							  JLabel Potion2Amount,
							  JLabel Gadget1Amount, 
							  JLabel Gadget2Amount,
							  JLabel cooldown1,
							  JLabel cooldown2,
							  JLabel STATICON1, 
							  JLabel STATICON2,
							  ImageIcon[] icons,
							  HealthBar hpbar1,
							  HealthBar hpbar2,
							  HealthBar shbar1,
							  HealthBar shbar2,
							  ChargeBar scbar1,
							  ChargeBar scbar2,
							  HyperBar hcbar1,
							  HyperBar hcbar2,
							  TurnManager TM,
							  JLabel turn, 
							  JLabel Name1, 
							  JLabel Name2,
							  JLabel[] abilityNames,
							  JLabel[] brawlerLabels,
							  String gamemode) {
		
		SoundManager SM = new SoundManager();
		
		//Overtime
		TM.increaseTurn();
		turn.setText("Turn " + TM.getTurn());
		if(TM.getTurn() == 99)
			SM.overtime.play();
		if(TM.getTurn() > 100) {
			A.changeSTATUS(Status.Enraged);
			B.changeSTATUS(Status.Enraged);	
			A.changeREGEN(A.regen*-1);
			B.changeREGEN(B.regen*-1);
			A.shield = 0;
			B.shield = 0;
		}
		if(TM.getTurn() == 150) {
			A.HP = -9999;
			B.HP = -9999;
		}
		
		//Decaying
		if(gamemode.equals("Decaying")) {
			A.changeREGEN(-1);
			B.changeREGEN(-1);
		}
		
		//Each Turn Passive
		eachTurnPassives(A, B);
		
		//brawler specific things
		updateBrawlerSpecific(abilityNames, brawlerLabels, A, B);
					
		//Updates
		hpbar1.updateHealth(A);
		hpbar2.updateHealth(B);
		shbar1.updateShield(A);
		shbar2.updateShield(B);
		scbar1.updateCharge(A);
		scbar2.updateCharge(B);
		hcbar1.updateCharge(A);
		hcbar2.updateCharge(B);
		HP1.setText("HP: " + A.HP);
		HP2.setText(("HP: " + B.HP));
		SG1.setText(A.SuperCharge + "/100");
		SG2.setText(B.SuperCharge + "/100");
		REG1.setText("Regen: " + A.regen);
		REG2.setText("Regen: " + B.regen);
		Potion1Amount.setText(A.potionCount + "/" + A.newInstance().potionCount);
		Potion2Amount.setText(B.potionCount + "/" + B.newInstance().potionCount);
		Gadget1Amount.setText(A.gadgetCount + "/" + A.newInstance().gadgetCount);
		Gadget2Amount.setText(B.gadgetCount + "/" + B.newInstance().gadgetCount);
		cooldown1.setText(A.spell.currentCooldown+"");
		cooldown2.setText(B.spell.currentCooldown+"");
		
		if (A.stat == Status.Stunned)
			STATICON1.setIcon(icons[1]);
		else if (A.stat == Status.Strengthened)
			STATICON1.setIcon(icons[2]);
		else if (A.stat == Status.Weakened)
			STATICON1.setIcon(icons[3]);
		else if (A.stat == Status.Confused)
			STATICON1.setIcon(icons[4]);
		else if (A.stat == Status.Guarded)
			STATICON1.setIcon(icons[5]);
		else if (A.stat == Status.Enraged)
			STATICON1.setIcon(icons[6]);
		else if (A.stat == Status.Frosty)
			STATICON1.setIcon(icons[7]);
		else if (A.stat == Status.Scarred)
			STATICON1.setIcon(icons[8]);
		else if (A.stat == Status.Intoxicated)
			STATICON1.setIcon(icons[10]);
		else {
			STATICON1.setIcon(icons[0]);
			Name1.setForeground(Color.black);
		}
		
		if (A.isHypercharged) {
			Name1.setForeground(new Color(181,11,204));
		}
		else {
			Name1.setForeground(Color.black);
		}
		
		if (B.stat == Status.Stunned)
			STATICON2.setIcon(icons[1]);
		else if (B.stat == Status.Strengthened)
			STATICON2.setIcon(icons[2]);
		else if (B.stat == Status.Weakened)
			STATICON2.setIcon(icons[3]);
		else if (B.stat == Status.Confused)
			STATICON2.setIcon(icons[4]);
		else if (B.stat == Status.Guarded)
			STATICON2.setIcon(icons[5]);
		else if (B.stat == Status.Enraged)
			STATICON2.setIcon(icons[6]);
		else if (B.stat == Status.Frosty)
			STATICON2.setIcon(icons[7]);
		else if (B.stat == Status.Scarred)
			STATICON2.setIcon(icons[8]);
		else if (B.stat == Status.Intoxicated)
			STATICON2.setIcon(icons[10]);
		else {
			STATICON2.setIcon(icons[0]);
			Name2.setForeground(Color.black);
		}
		
		if (B.isHypercharged) {
			Name2.setForeground(new Color(181,11,204));
		}else {
			Name2.setForeground(Color.black);
		}
		
	}
	
	public static void updateBrawlerSpecific(JLabel[] abilityNames,
			  								 JLabel[] brawlerLabels,
			  								 Brawler A, 
			  							     Brawler B) {
		
		ImageIcon stackIcon = new ImageIcon("res/images/stack_count.png");
		
		
		//Hypercharge!
				if(A.hyperchargeTurn != -1) {
					A.isHypercharged = true;
					A.hyperchargeTurn--;
					brawlerLabels[1].setText((A.hyperchargeTurn+1)+"");
					brawlerLabels[1].setVisible(true);
					brawlerLabels[1].setFont(new Font("Times New Roman", Font.BOLD, 20));
					brawlerLabels[1].setHorizontalTextPosition(JLabel.CENTER);
					brawlerLabels[1].setVerticalTextPosition(JLabel.CENTER);
					
				}else {
					A.isHypercharged = false;
					brawlerLabels[0].setVisible(false);
					brawlerLabels[1].setVisible(false);
				}
				
				if(B.hyperchargeTurn != -1) {
					B.isHypercharged = true;
					B.hyperchargeTurn--;
					brawlerLabels[3].setText((B.hyperchargeTurn+1)+"");
					brawlerLabels[3].setVisible(true);
					brawlerLabels[3].setFont(new Font("Times New Roman", Font.BOLD, 20));
					brawlerLabels[3].setHorizontalTextPosition(JLabel.CENTER);
					brawlerLabels[3].setVerticalTextPosition(JLabel.CENTER);
				}else {
					B.isHypercharged = false;
					brawlerLabels[2].setVisible(false);
					brawlerLabels[3].setVisible(false);
				}
		
		//Passives
				
				//Timmy
				if(A.name.equals("Timmy")) {
					Timmy temp = (Timmy) A;
					if (temp.HP < (int) (temp.newInstance().HP * 0.30))
						brawlerLabels[4].setIcon(temp.timmy_dragon);
					else
						brawlerLabels[4].setIcon(null);
				}
				
				if(B.name.equals("Timmy")) {
					Timmy temp = (Timmy) B;
					if (temp.HP < (int) (temp.newInstance().HP * 0.30))
						brawlerLabels[5].setIcon(temp.timmy_dragon);
					else
						brawlerLabels[5].setIcon(null);
				}
				
				//Itan
				if(A.name.equals("Itan")) {				
					Itan temp = (Itan) A;
					if (temp.BeurcHP > 0) {
						brawlerLabels[4].setText(temp.BeurcHP+"");
						brawlerLabels[4].setVisible(true);
						brawlerLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
						brawlerLabels[4].setHorizontalTextPosition(JLabel.CENTER);
						brawlerLabels[4].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						brawlerLabels[4].setText("");
				}
				
				if(B.name.equals("Itan")) {				
					Itan temp = (Itan) B;
					if (temp.BeurcHP > 0) {
						brawlerLabels[5].setText(temp.BeurcHP+"");
						brawlerLabels[5].setVisible(true);
						brawlerLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
						brawlerLabels[5].setHorizontalTextPosition(JLabel.CENTER);
						brawlerLabels[5].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						brawlerLabels[5].setText("");
				}
				
				//Light
				if(A.name.equals("Light")) {				
					Light temp = (Light) A;
					if (temp.lightInt > 0) {
						brawlerLabels[4].setText(temp.lightInt+"");
						brawlerLabels[4].setVisible(true);
						brawlerLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
						brawlerLabels[4].setHorizontalTextPosition(JLabel.CENTER);
						brawlerLabels[4].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						brawlerLabels[4].setText("");
				}
				
				if(B.name.equals("Light")) {				
					Light temp = (Light) B;
					if (temp.lightInt > 0) {
						brawlerLabels[5].setText(temp.lightInt+"");
						brawlerLabels[5].setVisible(true);
						brawlerLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
						brawlerLabels[5].setHorizontalTextPosition(JLabel.CENTER);
						brawlerLabels[5].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						brawlerLabels[5].setText("");
				}
				
				//Louis
				if(A.name.equals("Louis")) {				
					Louis temp = (Louis) A;
					if (temp.passiveTurns > 0) {
						brawlerLabels[4].setText(temp.passiveTurns+"");
						brawlerLabels[4].setVisible(true);
						brawlerLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
						brawlerLabels[4].setHorizontalTextPosition(JLabel.CENTER);
						brawlerLabels[4].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						brawlerLabels[4].setText("");
				}
				
				if(B.name.equals("Louis")) {				
					Louis temp = (Louis) B;
					if (temp.passiveTurns > 0) {
						brawlerLabels[5].setText(temp.passiveTurns+"");
						brawlerLabels[5].setVisible(true);
						brawlerLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
						brawlerLabels[5].setHorizontalTextPosition(JLabel.CENTER);
						brawlerLabels[5].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						brawlerLabels[5].setText("");
				}
		
		//Stacks:
				
				//Jester
				if(A.name.equals("Jester")) {
					Jester temp = (Jester) A;
					brawlerLabels[4].setIcon(stackIcon);
					brawlerLabels[4].setText(temp.normalAttackCounter+"");
					brawlerLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
					brawlerLabels[4].setHorizontalTextPosition(JLabel.CENTER);
					brawlerLabels[4].setVerticalTextPosition(JLabel.CENTER);
				}
				
				if(B.name.equals("Jester")) {
					Jester temp = (Jester) B;
					brawlerLabels[5].setIcon(stackIcon);
					brawlerLabels[5].setText(temp.normalAttackCounter+"");
					brawlerLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
					brawlerLabels[5].setHorizontalTextPosition(JLabel.CENTER);
					brawlerLabels[5].setVerticalTextPosition(JLabel.CENTER);
				}
				
				//Olea
				if(A.name.equals("Olea")) {
					Olea temp = (Olea) A;
					brawlerLabels[4].setIcon(stackIcon);
					brawlerLabels[4].setText(temp.superTurns+"");
					brawlerLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
					brawlerLabels[4].setHorizontalTextPosition(JLabel.CENTER);
					brawlerLabels[4].setVerticalTextPosition(JLabel.CENTER);
				}
				
				if(B.name.equals("Olea")) {
					Olea temp = (Olea) B;
					brawlerLabels[5].setIcon(stackIcon);
					brawlerLabels[5].setText(temp.superTurns+"");
					brawlerLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
					brawlerLabels[5].setHorizontalTextPosition(JLabel.CENTER);
					brawlerLabels[5].setVerticalTextPosition(JLabel.CENTER);
				}
				
				//Vollie
				if(A.name.equals("Vollie")) {
					Vollie temp = (Vollie) A;
					brawlerLabels[4].setIcon(stackIcon);
					brawlerLabels[4].setText(temp.runicStacks+"");
					brawlerLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
					brawlerLabels[4].setHorizontalTextPosition(JLabel.CENTER);
					brawlerLabels[4].setVerticalTextPosition(JLabel.CENTER);
				}
				
				if(B.name.equals("Vollie")) {
					Vollie temp = (Vollie) B;
					brawlerLabels[5].setIcon(stackIcon);
					brawlerLabels[5].setText(temp.runicStacks+"");
					brawlerLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
					brawlerLabels[5].setHorizontalTextPosition(JLabel.CENTER);
					brawlerLabels[5].setVerticalTextPosition(JLabel.CENTER);
				}
				
				//Giran
				if(A.name.equals("Giran")) {
					Giran temp = (Giran) A;
					brawlerLabels[4].setIcon(stackIcon);
					brawlerLabels[4].setText(temp.superstack+"");
					brawlerLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
					brawlerLabels[4].setHorizontalTextPosition(JLabel.CENTER);
					brawlerLabels[4].setVerticalTextPosition(JLabel.CENTER);
				}
				
				if(B.name.equals("Giran")) {
					Giran temp = (Giran) B;
					brawlerLabels[5].setIcon(stackIcon);
					brawlerLabels[5].setText(temp.superstack+"");
					brawlerLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
					brawlerLabels[5].setHorizontalTextPosition(JLabel.CENTER);
					brawlerLabels[5].setVerticalTextPosition(JLabel.CENTER);
				}
				
		//Name Updates
				
		//Clyde Cannon name updates
				if(A.name.equals("Clyde")) {
					Clyde temp = (Clyde) A;
					if(temp.isCannon != true) {
						brawlerLabels[4].setIcon(temp.clyde_solo);
						abilityNames[0].setText("Fireworking"); 
						abilityNames[1].setText("Hop On Back!"); 
						abilityNames[2].setText(A.build.gadgetChoise == "FIRST" ? "Headbutt!" : "Fully Charged"); 		
					}
					else {
						brawlerLabels[4].setIcon(temp.clyde_cannon);
						abilityNames[0].setText("Cannon Attack!"); 
						abilityNames[1].setText("Wooohooo!"); 
						abilityNames[2].setText(A.build.gadgetChoise == "FIRST" ? "Shield Conversion" : "Steady Charge"); 
					}
				}
				
				if(B.name.equals("Clyde")) {
					Clyde temp = (Clyde) B;
					if(temp.isCannon != true) {
						brawlerLabels[5].setIcon(temp.clyde_solo);
						abilityNames[3].setText("Fireworking"); 
						abilityNames[4].setText("Hop On Back!"); 
						abilityNames[5].setText(B.build.gadgetChoise == "FIRST" ? "Headbutt!" : "Fully Charged"); 		
					}
					else {
						brawlerLabels[5].setIcon(temp.clyde_cannon);
						abilityNames[3].setText("Cannon Attack!"); 
						abilityNames[4].setText("Wooohooo!"); 
						abilityNames[5].setText(B.build.gadgetChoise == "FIRST" ? "Shield Conversion" : "Steady Charge"); 
					}
				}
				
				//Amber ability updates
				if(A.name.equals("Amber")) {
					Amber temp = (Amber) A;			
					switch (temp.currentWeapon) {
						case 1:
							brawlerLabels[4].setIcon(temp.amber_gun1);
							abilityNames[0].setText("Rapidfire");
							abilityNames[1].setText("Fiercefire");
							break;
						case 2:
							brawlerLabels[4].setIcon(temp.amber_gun2);
							abilityNames[0].setText("Clencher");
							abilityNames[1].setText("Double Squeeze");
							break;
						case 3:
							brawlerLabels[4].setIcon(temp.amber_gun3);
							abilityNames[0].setText("Harsh Pierce");
							abilityNames[1].setText("Gashopener");
							break;
						case 4:
							brawlerLabels[4].setIcon(temp.amber_gun4);
							abilityNames[0].setText("Soulfire");
							abilityNames[1].setText("Soulless Strike");
							break;
					}	
				}
				
				if(B.name.equals("Amber")) {
					Amber temp = (Amber) B;			
					switch (temp.currentWeapon) {
						case 1:
							brawlerLabels[5].setIcon(temp.amber_gun1);
							abilityNames[3].setText("Rapidfire");
							abilityNames[4].setText("Fiercefire");
							break;
						case 2:
							brawlerLabels[5].setIcon(temp.amber_gun2);
							abilityNames[3].setText("Clencher");
							abilityNames[4].setText("Double Squeeze");
							break;
						case 3:
							brawlerLabels[5].setIcon(temp.amber_gun3);
							abilityNames[3].setText("Harsh Pierce");
							abilityNames[4].setText("Gashopener");
							break;
						case 4:
							brawlerLabels[5].setIcon(temp.amber_gun4);
							abilityNames[3].setText("Soulfire");
							abilityNames[4].setText("Soulless Strike");
							break;
					}	
				}
				
				//Anton Name Updates
				if(A.name.equals("Anton")) {
					Anton temp = (Anton) A;
					
					switch(temp.passive) {
					case 0:
						abilityNames[0].setText("Selfless Attack");
						abilityNames[1].setText("Breeze of Heart");
						abilityNames[2].setText("Anger");
						brawlerLabels[4].setIcon(temp.anton_calm);
						break;
					case 1:
						abilityNames[0].setText("Selfish Strike");
						abilityNames[1].setText("Heat of Heart");
						abilityNames[2].setText("Calm");
						brawlerLabels[4].setIcon(temp.anton_angry);
						break;	
					}	
				}
				
				if(B.name.equals("Anton")) {
					Anton temp = (Anton) B;
					
					switch(temp.passive) {
					case 0:
						abilityNames[3].setText("Selfless Attack");
						abilityNames[4].setText("Breeze of Heart");
						abilityNames[5].setText("Anger");
						brawlerLabels[5].setIcon(temp.anton_calm);
						break;
					case 1:
						abilityNames[3].setText("Selfish Strike");
						abilityNames[4].setText("Heat of Heart");
						abilityNames[5].setText("Calm");
						brawlerLabels[5].setIcon(temp.anton_angry);
						break;	
					}	
				}
				
				//Qirale Name Updates
				
				if(A.name.equals("Qirale")) {
					Qirale temp = (Qirale) A;
					
					switch(temp.passive) {
					case 0:
						brawlerLabels[4].setIcon(temp.qirale_water);
						break;
					case 1:
						brawlerLabels[4].setIcon(temp.qirale_fire);
						break;
					case 2:
						brawlerLabels[4].setIcon(temp.qirale_earth);
						break;
					case 3:
						brawlerLabels[4].setIcon(temp.qirale_air);
						break;
					}	
				}
				
				if(B.name.equals("Qirale")) {
					Qirale temp = (Qirale) B;
					
					switch(temp.passive) {
					case 0:
						brawlerLabels[5].setIcon(temp.qirale_water);
						break;
					case 1:
						brawlerLabels[5].setIcon(temp.qirale_fire);
						break;
					case 2:
						brawlerLabels[5].setIcon(temp.qirale_earth);
						break;
					case 3:
						brawlerLabels[5].setIcon(temp.qirale_air);
						break;
					}	
				}
		
		
	}
	
	public static void enableOrDisable(Brawler attacker, 
								       Brawler attacked, 
								       ArrayList<JButton> attackerButtons,
								       ArrayList<JButton> attackedButtons,
								       String what
								       ) {
	
		if(what.equals("STUN") || what.equals("ALL")) {
		if(attacked.stat != Status.Stunned) {
			for (JButton buts : attackerButtons)
				buts.setEnabled(false);
			for (JButton buts : attackedButtons)
				buts.setEnabled(true);
			}
			else {
				for (JButton buts : attackerButtons)
					buts.setEnabled(true);
				for (JButton buts : attackedButtons)
					buts.setEnabled(false);
			}
		}
		
		if(what.equals("GADGET_POTION") || what.equals("ALL")) {
			
		if (attacker.gadgetCount < 1) attackerButtons.get(3).setEnabled(false);
		if (attacker.potionCount < 1) attackerButtons.get(4).setEnabled(false);
		if (attacked.gadgetCount < 1) attackedButtons.get(3).setEnabled(false);
		if (attacked.potionCount < 1) attackedButtons.get(4).setEnabled(false);
		
		}
		
		if(what.equals("SPELL") || what.equals("ALL")) {
			
		if (attacker.spell.currentCooldown > 1) attackerButtons.get(5).setEnabled(false);
		if (attacked.spell.currentCooldown > 1) attackedButtons.get(5).setEnabled(false);
				
		}
		
		if(what.equals("SPECIFIC") || what.equals("ALL")) {
			
			if (attacker.hak == -1) attackerButtons.get(6).setEnabled(false);
			if (attacked.hak == -1) attackedButtons.get(6).setEnabled(false);
			
		}
		
 		 
	}

	public static void isOver(Brawler A, 
			  Brawler B, 
			  JLabel HP1, 
			  JLabel HP2 ,
			  JLabel SG1, 
			  JLabel SG2 ,
			  JLabel REG1, 
			  JLabel REG2 ,
			  JLabel Potion1Amount, 
			  JLabel Potion2Amount,
			  JLabel Gadget1Amount, 
			  JLabel Gadget2Amount,
			  JLabel STATICON1, 
			  JLabel STATICON2,
			  JLabel Name1,
			  JLabel Name2,
			  ImageIcon[] icons,
			  HealthBar hpbar1,
			  HealthBar hpbar2,
			  ChargeBar scbar1,
			  ChargeBar scbar2,
			  ArrayList<JButton> buttons) {
		
		SoundManager SM = new SoundManager();
		
		if (A.HP < 0) {
			hpbar1.updateHealth(A);
			hpbar2.updateHealth(B);
			scbar1.updateCharge(A);
			scbar2.updateCharge(B);
			HP1.setText("HP: " + A.HP);
			HP2.setText(("HP: " + B.HP));
			SG1.setText(A.SuperCharge + "/100");
			SG2.setText(B.SuperCharge + "/100");
			REG1.setText("Regen: " + A.regen);
			REG2.setText("Regen: " + B.regen);
			STATICON1.setVisible(false);
			STATICON2.setVisible(false);
			for (JButton but : buttons)
				but.setEnabled(false);
			Name2.setForeground(Color.yellow);
			SM.outro.play();
			if(A.spell.name == "Demonic Cuteness" || A.spell.name == "Hypercharge") {
				A.spell.effectTurn = 0;
			}
			if(B.spell.name == "Demonic Cuteness" || B.spell.name == "Hypercharge") {
				B.spell.effectTurn = 0;
			}
			
		}
		if (B.HP < 0) {
			hpbar1.updateHealth(A);
			hpbar2.updateHealth(B);
			scbar1.updateCharge(A);
			scbar2.updateCharge(B);
			HP1.setText("HP: " + A.HP);
			HP2.setText(("HP: " + B.HP));
			SG1.setText(A.SuperCharge + "/100");
			SG2.setText(B.SuperCharge + "/100");
			REG1.setText("Regen: " + A.regen);
			REG2.setText("Regen: " + B.regen);
				STATICON1.setVisible(false);
				STATICON2.setVisible(false);
			for (JButton but : buttons)
				but.setEnabled(false);
			Name1.setForeground(Color.yellow);
			SM.outro.play();
			if(A.spell.name == "Demonic Cuteness" || A.spell.name == "Hypercharge") {
				A.spell.effectTurn = 0;
			}
			if(B.spell.name == "Demonic Cuteness" || B.spell.name == "Hypercharge") {
				B.spell.effectTurn = 0;
			}
		}
	}
	
	public static void gadget(Brawler attacker, Brawler attacked) {
		
	    if(attacker.gadgetCount > 0) {
	        // Save the current enemy HP and status before using the gadget
	        int enemyHPbeforeGadget = attacked.shield + attacked.HP;
	        Status enemyStatBeforeGadget = attacked.stat;

	        attacker.gadgetAbility(attacked);
	        attacker.changeGADCNT(-1);

	        int enemyHPafterGadget = attacked.shield + attacked.HP;

	        // Check if the gadget dealt damage to the enemy
	        boolean gadgetDealtDamage = (enemyHPafterGadget < enemyHPbeforeGadget);

	        // If the enemy was stunned, and the gadget didn't deal damage, break stun and change regen
	        if (enemyStatBeforeGadget == Status.Stunned && gadgetDealtDamage) {
	            attacked.changeREGEN(-2);
	            attacked.changeSTATUS(Status.Normal);
	        }
	    }
	}
	
	public static void potion(Brawler user, Brawler enemy, String potion, JButton Potion1, JButton Super1) {
		
			SoundManager SM = new SoundManager();
		
			int initcharge = user.SuperCharge;
		
			switch(potion) {
			case "Red":
				user.changeHP(50);
				user.changePOTCNT(-1);
				if(user.potionCount <= 0) {
					Potion1.setBackground(new Color(66, 66, 66));
					Potion1.setForeground(Color.white);
					Potion1.setEnabled(false);
					}
				break;
			case "Yellow":
				user.changeCHARGE(15);
				user.changePOTCNT(-1);
				if(user.potionCount <= 0) {
					Potion1.setBackground(new Color(66, 66, 66));
					Potion1.setForeground(Color.white);
					Potion1.setEnabled(false);
					}
				break;
			case "Dark Purple":
				user.changeREGEN(3);
				user.changePOTCNT(-1);
				if(user.potionCount <= 0) {
					Potion1.setBackground(new Color(66, 66, 66));
					Potion1.setForeground(Color.white);
					Potion1.setEnabled(false);
					}
				break;
			case "Dark Blue":
				if(enemy.name != "Simon")
					if (enemy.stat != Status.Guarded) {
						enemy.changeSTATUS(Status.Weakened);
						SM.weakSE.play();
					}else enemy.changeSTATUS(Status.Normal);
				
				user.changePOTCNT(-1);
				if(user.potionCount <= 0) {
					Potion1.setBackground(new Color(66, 66, 66));
					Potion1.setForeground(Color.white);
					Potion1.setEnabled(false);
					}
				break;
			case "Dark Red":
				user.changeSTATUS(Status.Strengthened);
				user.changePOTCNT(-1);
				SM.strongSE.play();
				if(user.potionCount <= 0) {
					Potion1.setBackground(new Color(66, 66, 66));
					Potion1.setForeground(Color.white);
					Potion1.setEnabled(false);
					}
				break;
			case "Green":
				if(enemy.name != "Simon")
					if (enemy.stat != Status.Guarded) {
						enemy.changeSTATUS(Status.Confused);
					}else enemy.stat = Status.Normal;
				user.changePOTCNT(-1);
				if(user.potionCount <= 0) {
					Potion1.setBackground(new Color(66, 66, 66));
					Potion1.setForeground(Color.black);
					Potion1.setEnabled(false);
					}
				break;
			case "Gray":
				Status prev = user.stat;
				user.changeSTATUS(Status.Normal);
				user.changePOTCNT(-1);
				if (prev != Status.Normal) user.changeHP(20);
				//SE[4].play();
				if(user.potionCount <= 0) {
					Potion1.setBackground(new Color(66, 66, 66));
					Potion1.setForeground(Color.white);
					Potion1.setEnabled(false);
					}
				break;
			case "Dark Gray":
				SM.guardian.play();
				user.shield += 60;
				user.changePOTCNT(-1);
				if(user.potionCount <= 0) {
					Potion1.setBackground(new Color(66, 66, 66));
					Potion1.setForeground(Color.white);
					Potion1.setEnabled(false);
					}
				break;
			case "Brown":
				SM.demonic_burn.play();
				enemy.changeHP(-30);
				user.changePOTCNT(-1);
				if(user.potionCount <= 0) {
					Potion1.setBackground(new Color(66, 66, 66));
					Potion1.setForeground(Color.white);
					Potion1.setEnabled(false);
					}
				break;
			case "Light Purple":
				user.changeHYPERCHARGE(+30);
				user.changePOTCNT(-1);
				if(user.potionCount <= 0) {
					Potion1.setBackground(new Color(66, 66, 66));
					Potion1.setForeground(Color.white);
					Potion1.setEnabled(false);
					}
				break;
							
			}
			
			if(user.SuperCharge > 99 && initcharge < 100)
				SM.charged.play();
		
	}

	public static void spell(Brawler user, Brawler enemy, Spell spell) {
		
		SoundManager SM = new SoundManager();

	    Random ran = new Random();

	    int enemyHPbeforeSpell = enemy.shield + enemy.HP;
	    Status enemyStatBeforeSpell = enemy.stat;
		 
		switch (spell.name) {
		
		case "Static Shock":
			SM.static_damage.play();
			int num = user.SuperCharge;
			if (num >= 100) {
				enemy.changeSTATUS(Status.Stunned);
				SM.static_stun.play();
			}
			enemy.changeHP((int)(-num*0.5));
			user.changeCHARGE(-num);
			break;
		case "Redemption":
			SM.redemption1.play();
			SM.redemption2.play();
			user.changeHP(40);
			enemy.changeHP(-40);
			break;
			
		case "Heart of Steel":
			SM.Heartsteel.play();
			enemy.changeHP( (int)(user.HP * -0.15) );
			break;
			
		case "Glacial Gale":
			SM.GlacialGale.play();
			enemy.changeHP(-30);
			enemy.changeSTATUS(Status.Frosty);
			
			if(ran.nextInt(0,2) == 1) {
				enemy.changeSTATUS(Status.Stunned);
				enemy.changeHP(-30);
				SM.GlacialGaleStun.play();
			}
			break;
			
		case "Electric Storm":
			enemy.changeHP(-25);
			SM.electro1.play();
					
			if(ran.nextInt(0,3) % 2 == 0) {
				try {Thread.sleep(300);}catch(InterruptedException e){}
				enemy.changeHP(-50);
				SM.electro2.play();
				
				if(ran.nextInt(0,3) == 1) {
					try {Thread.sleep(300);}catch(InterruptedException e){}
					enemy.changeHP(-100);
					SM.electro3.play();
				}
			}
			break;
			
		case "Guardian":
			SM.guardian.play();
			user.changeSHIELD(125);
			break;
			
		case "Prixie":
			if (ran.nextInt(0,2) == 0) {
				SM.prixieheal.play();
				user.changeHP(30);
				user.changeSHIELD(30);
			}
			else {
				SM.prixiedmg.play();
				enemy.changeHP(-30);
				enemy.changeSTATUS(Status.Weakened); 
			}
			break;
		
		case "Last Strike":
			SM.laststrikeREADY.play();
			try {Thread.sleep(500);}catch(InterruptedException e){}
			int count = 0;
			for(int i = 0; i < 20; i++) 
				if(ran.nextInt(0,2) == 0)
					count++;
			
			for(int i = 0; i < count; i++) {				
				switch(ran.nextInt(0,3)) {
				case 0: SM.laststrike1.play(); try {Thread.sleep(125);}catch(InterruptedException e){} break;
				case 1: SM.laststrike2.play(); try {Thread.sleep(125);}catch(InterruptedException e){} break;
				case 2: SM.laststrike3.play(); try {Thread.sleep(125);}catch(InterruptedException e){} break;
				}
				enemy.changeHP(-10);	
			}
				
			break;	
		
		case "Demonic Cuteness":
			SM.demonic_cuteness.play();
			user.spell.effectTurn = 5;
			break;
		case "Mirror":
			SM.mirror.play();
			if(enemy.spell.name == "Mirror") {
				try {Thread.sleep(355);}catch(InterruptedException e){}
				SM.mirror.play();
			}
			else {				
				user.spell.name = enemy.spell.name;
				spell(user, enemy, user.spell);
			}
			break;
		case "Meditation Medication":
			SoundManager.medmed.play();
			user.spell.effectTurn = 5;
			break;
			
			
		//end of switch
		}
		
		
		// Check if the spell dealt damage to the enemy
	    int enemyHPafterSpell = enemy.shield + enemy.HP;
	    boolean spellDealtDamage = (enemyHPafterSpell < enemyHPbeforeSpell);

	    // If the enemy was stunned, and the spell didn't deal damage, break stun and change regen
	    if (enemyStatBeforeSpell == Status.Stunned && spellDealtDamage) {
	        enemy.changeREGEN(-2);
	        enemy.changeSTATUS(Status.Normal);
	    }
        
		spell.currentCooldown = user.build.gearChoise == "COOLDOWN GEAR" ? ((int) (spell.cooldown * 0.8)) : spell.cooldown;
		
	}
	
	public static void cooldownCheck(Brawler A, JButton spell){
		if (A.spell.currentCooldown == 0)
			spell.setEnabled(true);	
	}
	
	public static void helpScreen(Brawler[] bs, String[] bsName) {

		
		ArrayList<String> konusList = new ArrayList<String>();
		for(String konu : bsName)
			konusList.add(konu);
		konusList.add("Potions");
		konusList.add("Statuses");
		konusList.add("Gears");
		konusList.add("Spells");
		konusList.add("Abilities");
		konusList.add("Hypercharge");
		String[] konus = konusList.toArray(new String[0]);
		 
		JFrame window = new JFrame("Turn by Turn - Help");
		window.setSize(1000,800);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.getContentPane().setBackground(Color.lightGray);
		window.setIconImage(new ImageIcon("res/images/turnbyturn.png").getImage());
		window.setLayout(null);
		Container con = window.getContentPane();
		
		JPanel quitPanel,
			   comboBoxPanel,
			   lookPanel,
			   descriptionPanel;
		
		JButton quitButton, lookButton;
		JComboBox<String> combo = new JComboBox<String>(konus);
		((JLabel)combo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		JLabel description = new JLabel("Choose a topic to get help with!");
		
		//quit
		quitPanel = new JPanel();
		quitPanel.setBounds(30,700,100,80);
		quitPanel.setBackground(Color.lightGray);
		quitPanel.setVisible(true);
		quitButton = new JButton("QUIT");
		quitButton.setVisible(true);
		quitButton.setForeground(Color.white);
		quitButton.setBackground(Color.black);
		quitButton.setFont(new Font("Times New Roman", Font.ITALIC, 25 ));
		quitButton.setFocusPainted(false);
		quitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				window.dispose();
				
			}
		});
		quitPanel.add(quitButton);
		con.add(quitPanel);
		
		//look
		lookPanel = new JPanel();
		lookPanel.setBounds(30,10,100,40);
		lookPanel.setBackground(Color.lightGray);
		lookPanel.setVisible(true);
		lookButton = new JButton("LOOK");
		lookButton.setVisible(true);
		lookButton.setForeground(Color.white);
		lookButton.setBackground(Color.black);
		lookButton.setFont(new Font("Times New Roman", Font.ITALIC, 25 ));
		lookButton.setFocusPainted(false);
		lookButton.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String FROSTY = new String("<font color=\"blue\">FROSTY</font>");
				String SHIELD = new String("<font color=\"gray\">SHIELD</font>");
				String STUNS = new String("<font color=\"yellow\">STUNS</font>");
				String STUNNED = new String("<font color=\"yellow\">STUNNED</font>");
				String WEAKENED = new String("<font color=\"#0000cc\">WEAKENED</font>");
				String SCARRED = new String("<font color=\"red\">SCARRED</font>");
				String ENRAGED = new String("<font color=\"red\">ENRAGED</font>");
				String GUARDED = new String("<font color=\"gray\">GUARDED</font>");
				String STRENGTHENED = new String("<font color=\"red\">STRENGTHENED</font>");
				String HYPERCHARGE = new String("<font color=\"purple\">HYPERCHARGE</font>");
				String INTOXICATED = new String("<font color=\"#964b00\">INTOXICATED</font>");
				
				Brawler brawler = new Todd(new Build(new Random()));
				String brawlerST = (String) combo.getSelectedItem();
				for (Brawler i : bs)
					if (i.name == brawlerST)
						brawler = i.newInstance();
				
				int atk = brawler.AttackDamage;
				int sup = brawler.SuperDamage;
				
				switch(brawlerST) {
				case "Todd":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Todd swings his sword to the ground and deals " + atk + " damage"
							+ "<br/><br/>SUPER: Todd attacks and uses his commitment to the kingdom as sheer power to either double his damage or heal himself. "
							+ "(" + HYPERCHARGE + ") Todd's damage and/or heal doubles!"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Todd drinks some regional beer to get some Regeneration"
							+ "<br/>Second Gadget: Todd remembers his fallen comarades and increases his ATK and becomes " + STRENGTHENED
							+ "<br/><br/>PASSIVE (ALWAYS READY): Todd starts the match with some shield"
							);
							break;
				case "Zach":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Zach throws a piece of his body at the enemy! It hurts him as much as the enemy though."
							+ "<br/><br/>SUPER: Zach regenerates his missing pieces."
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Zach sets a piece of him aside for harsh days, He increases his REGEN but decreases his HP"
							+ "<br/>Second Gadget: In a last ditch attempt he collects his scattered body from around and heals in proportion to his missing HP"
							+ "<br/><br/>PASSIVE (STRANGE MATTER): Zach has a small chance to absorb any type of damage he's taking and heal from it"
							);
							break; 
				case "Raven":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Raven throws a knife dipped in toxic chemicals. It deals " + atk + " damage and decreases the enemy's Regeneration by 2"
							+ "<br/><br/>SUPER: Raven flies to the sky and hastily throws 4 stronger knives. (" + HYPERCHARGE + ") Throws an additional knife."
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Enemy's regeneration decreases by a flat amount"
							+ "<br/>Second Gadget: Enemy's regeneration decreases by a percentage"
							+ "<br/><br/>PASSIVE (TOXIC MOTIVATION): Raven's super charge charges in opposition to the enemy's Regeneration"
							);
							break; 
				case "Simon":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Simon makes a random spell that deals damage between 15 and 35. For each 5 damage interval, there is a catch:"
							+ " (15-20): He gets regeneration in proportion to the damage, (21-25): Simon gets double super charge from this attack, "
							+ " (26-30): The enemy's super charge is decreased in proportion to the damage, (31-35): Simon heals the damage he deals"
							+ "<br/><br/>SUPER: Simon throws a ice spell that " + STUNS + "the enemy and deals " + sup + " damage"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Simon restockes his potion stash and gets 1 more potion count"
							+ "<br/>Second Gadget: Simon throws a fire spell that deals damage and decreases the REGEN of the enemy"
							+ "<br/><br/>PASSIVE (MAGICAL RESISTANCE): Simon can not get a Soft-Negative Status effect "
							);
					break;
				case "Susan":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Susan kicks the enemy and deals " + atk + " damage"
							+ "<br/><br/>SUPER: Susan uses all her force to blow a kick that " + STUNS + " and deals " + sup + " damage"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Susan meditates and rests her legs. She heals a little"
							+ "<br/>Second Gadget: Susan blows 2 kicks back to back and deals some damage"
							+ "<br/><br/>PASSIVE (BELLOW THE WAIST): Susan deals more damage to the enemy if they have a negative status effect"
							);
							break; 
				case "Mark":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Mark claws the enemy and deals " + (atk) + " damage. If the enemy was " + SCARRED + " deals more damage." 
							+ "<br/><br/>SUPER: Mark bites the enemy's neck, dealing " + (sup) + " damage, increasing his ATK ans SUP damage. Also makes the enemy"
							+ " " + SCARRED
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Mark bites itself and heals a little, but he bleeds too."
							+ "<br/>Second Gadget: Mark bites the enemy's gut, dealing damage and bleeding them by decreasing their regeneration. It also makes them " + SCARRED
							+ "<br/><br/>PASSIVE (VAMPIRE KING): Mark heals as much as he deals damage. This heal increases if the enemy is " + SCARRED + ". "
							+ "Mark can not have positive regeneration. "
							+ "All regen increase, increases his damage instead. Also, he loses 1 regeneration every turn, and losing regeneration"
							+ " increases his damage too."
							);
							break; 	
				case "Lisa":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Lisa brings the fire of justice from herself and deals " + atk + " damage"
							+ "<br/><br/>SUPER: In a fit of perseverence, Lisa almost doubles her ATK and REGEN"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Lisa prays for a help from the heavens and gets super charge"
							+ "<br/>Second Gadget: Lisa makes a deal with the gods, with a portion of her HP gone, she gets some super charge"
							+ "<br/><br/>PASSIVE (FALLEN ANGEL): If Lisa's super is charged, She reduces any damage coming to her"
							);
							break; 	
				case "Jester":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + "(-5, 10)"
							+ "<br/><br/>ATTACK: Jester throws his bells in a pattern of " + atk + " x1 -> x2 -> x3 -> x4 -> x1 ..."
							+ "<br/><br/>SUPER: Jester opens his mystery box! There is 5 possible outcomes! "
							+ "1-) Jester Heals. 2-) Jester deals a massive damage to the enemy 3-) Jester stuns the enemy 4-) Jester weakenes, deals damage and decreases the enemy's regen "
							+ "5-) Jester uses a random spell."
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Jester randomly changes his HP and Regeneration"
							+ "<br/>Second Gadget: Jester randomly increases his Super Charge"
							+ "<br/><br/>PASSIVE (SURVIVALIST UNPREDICTABILITY): If Jester is bellow %30 HP his attacks become unpredictable!"
							);
							break; 	
				case "Finn":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Finn fires a ghouly shot from the locket on his neck and deals " + atk + " damage"
							+ "<br/><br/>SUPER: Finn steals a portion of the enemy's soul! Well, more like their attack damage and regeneration... Basically the same thing!"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Finn steals a part of the enemy's health"
							+ "<br/>Second Gadget: Finn steals a part of the enemy's super charge"
							+ "<br/><br/>PASSIVE (SOUL TREAT): After attacking 3 times Finn devours a soul on his locket and heals himself for a treat!"
							);
							break; 	
				case "Timmy":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Timmy fires his gun, dealing " + atk + " damage plus a portion of the enemy's current HP" 
							+ "<br/><br/>SUPER: Timmy's trusty Dragonic pet fires an enchanting flame, dealing " + sup +  " damage and instantly deleting "
							+ "a portion of the enemy's HP"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Timmy's pet latches on the enemy and " + STUNS + " them"
							+ "<br/>Second Gadget: Timmy heals in proportion to the enemy's missing HP"
							+ "<br/><br/>PASSIVE (DRAGON MASTER): If Timmy has less then %30 of his HP, his pet starts attacking with him, dealing extra guaranteed damage"
							);
							break; 	
				case "Kasse":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Kasse sends a cosmic butterfly to an enemy. It deals " + atk + " damage"
							+ "<br/><br/>SUPER: The enemy's time on this universe is over. Kasse executes the enemy."
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Kasse gets 10 super charge and makes the enemy " + WEAKENED 
							+ "<br/>Second Gadget: Kasse gets 15 super charge and makes himself " + WEAKENED
							+ "<br/><br/>PASSIVE (COSMIC CALL): For each 100 HP missing from him, Kasse's attacks deal 1 more damage"
							);
							break; 
				case "John":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: John shoots his rifle and deals " + atk + " damage"
							+ "<br/><br/>SUPER: John shoots his shutgun and deals " + sup + " damage on top of a certain portion of his current HP. (" + HYPERCHARGE + ") "
							+ "John's super deals %20 more damage"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: John gets " + WEAKENED + " but heals some HP"
							+ "<br/>Second Gadget: John gets " + STRENGTHENED + " but loses some HP"
							+ "<br/><br/>PASSIVE (LAST BREATH): Before John dies he quickly uses his attack one last time"
							);
							break;
				case "Light":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Light punches the enemy and deals " + atk + " damage"
							+ "<br/><br/>SUPER: Light does something... We don't know what he does but the enemy dies 25 turns later."
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Light somehow increases his health"
							+ "<br/>Second Gadget: Light somehow decreases his enemy's health"
							+ "<br/><br/>PASSIVE (IMPATIENCE): Attacking the enemy gives Light a gadget charge."
							);
							break;
				case "Felix":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Felix swings their sword and deals " + atk + " damage"
							+ "<br/><br/>SUPER: Felix stands still and spears their sword, breaking any " + SHIELD + " from the enemy and " + STUNS + " them if "
							+ "either Felix or the Enemy is not on the Normal status"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Felix becomes " + GUARDED
							+ "<br/>Second Gadget: The enemy of Felix becomes " + ENRAGED
							+ "<br/><br/>PASSIVE (ROYAL DANCE): Attacking an enemy increases his Attack Damage by 1"
							);
							break; 
				case "Missy":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Missy throws a kiss! That stings!? It deals " + atk + " damage"
							+ "<br/><br/>SUPER: Missy focuses on self-care and increases her Regeneration. She also increases her Attack Damage in proportion to her regeneration. "
							+ "(" + HYPERCHARGE + ") Regeneration and Attack damage increase, increases by %25!"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Missy increases her regen"
							+ "<br/>Second Gadget: Missy deals damage to the enemy in proportion to her regen"
							+ "<br/><br/>PASSIVE (REGENLIOUS): If Missy's Regeneration goes bellow 0, She starts to regain regeneration slowly."
							);
							break; 
				case "Imelda":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Imelda swings her fist and deals " + atk + " damage"
							+ "<br/><br/>SUPER: Imelda filles herself with fury and decreases her HP but increases her Attack damage"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Imelda decreases her HP but increases her Attack damage"
							+ "<br/>Second Gadget: Imelda decreases her HP but increases her Regeneration"
							+ "<br/><br/>PASSIVE (FURIOUS FISTS): Imelda permanantly becomes " + ENRAGED + " if she's bellow %50 HP"
							);
							break; 	
				case "Betty":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Betty throws some glitter(?) to the enemy and deals " + atk + " damage"
							+ "<br/><br/>SUPER: Betty mimics his enemy and uses their super ability! All by herself!"
							+ "<br/><br/>GADGETS: <br/>First Gadget: Betty forces the enemy to attack theirselves! "
							+ "<br/>Second Gadget: Betty creates a stronger copy of the enemy's potion and uses it!"
							+ "<br/><br/>PASSIVE (MAGICAL RESISTANCE): Betty can not get a Soft-Negative Status effect"
							);
							break;
				case "Nanni":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Nanni charges her pulsaters and shoots them. Each has a 1/3 chance to attack and deals " + atk + " damage! But at least one always hits."
							+ "<br/><br/>SUPER: Nanni charges her laser and deals " + sup + " damage"
							+ "<br/><br/>GADGETS: <br/>First Gadget: Nanni gets " + ENRAGED + "! Out of fear, the enemy becomes " + WEAKENED +  ". "
							+ "<br/>Second Gadget: Nanni overcharges and " + STUNS + " the enemy."
							+ "<br/><br/>PASSIVE (ENERGY CONVERSION): If Nanni gets " + STUNNED + ", she gets some shield."
							);
				 break;
				case "Hassan":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Hassan's weapon is loose today! First strike deals " + atk + " damage "
							+ "and the second strike deals damage in proportion to the enemy's missing HP"
							+ "<br/><br/>SUPER: With just a look, the enemy's chunk of health is gone! Wait... What's that on his forehead?"
							+ "<br/><br/>GADGETS: <br/>First Gadget: Hassan starves his enemy and decreases their regeneration "
							+ "substantially <br/>Second Gadget: Hassan throws a big feast and increases his regeneration substantially"
							+ "<br/><br/>PASSIVE (ROYAL PROTECTION): Hassan gets some shield in proportion to his second strike in his attack."
							); 	
					break;
				case "Ignace":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Ignace brings the passion out of the enemy and deals " + atk + " damage"
							+ "<br/><br/>SUPER: Ignace ignites the enemy on fire and deals " + sup + " damage, He also "
							+ "lowers their regeneration"
							+ "<br/><br/>GADGETS: <br/>First Gadget: Deals a little damage to the enemy, if the enemy is " + WEAKENED + " "
							+ "deals more damage and " + STUNS + " the enemy. <br/>Second Gadget: Decreases the enemy's regen a little, "
							+ "if the enemy is " + WEAKENED + " decreases the regen further and " + STUNS + " the enemy."
							+ "<br/><br/>PASSIVE (EXHAUSTING): Ignace's attacks makes the enemy " + WEAKENED
							);
					break;
				case "Gusty":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Gusty throws his trusty balloon friend that deals " + atk + " damage."
							+ "<br/><br/>SUPER: Gusty wraps himself with some elastic balloon  that give him some " + SHIELD + " (" + HYPERCHARGE + ") "
									+ " Gives %20 more shield!"
							+ "<br/><br/>GADGETS: <br/>First Gadget: Gusty's balloon is filled with rage! It deals damage in proportion to "
							+ "Gusty's missing HP. <br/>Second Gadget: Gusty deals damage to the enemy in proportion to his current shield"
							+ "<br/><br/>PASSIVE (SHIELD BASH): Gusty's attacks deal more damage if he has " + SHIELD
							);
					break;
				case "Anvaa":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US) +
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Anvaa throws an ice piece at the enemies that deals " + atk + " damage. "
							+ "If the enemy is " + FROSTY + " it deals more damage and heals herself."
							+ "<br/><br/>SUPER: Anvaa creates a snow storm and deals " + sup + " damage to the enemy and "
							+ "changes their status to " + FROSTY + " If the enemy is already " + FROSTY + " deals more damage."
							+ "<br/><br/>GADGETS: <br/>First Gadget: If the enemy is " + FROSTY + " she " + STUNS + " them and deals damage. <br/>Second Gadget: "
							+ "Anvaa becomes " + FROSTY + " and heals some HP"
							+ "<br/><br/>PASSIVE (IMMORTALITY): If Anvaa dies, She gets reborned, she gets " + STUNNED + " but also gets some " + SHIELD
							+ " She can get reborn only once per game"
							);
					break;
				case "Vollie":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Vollie claws his enemy and deals " + atk + " damage. The more he attacks the more he gets "
						    + "his runic stacks. The more runic stacks he has, the more damage he deals! If he gets stunned he loses all his stacks!"
							+ "<br/><br/>SUPER: Vollie bites the enemy and deals " + sup + " damage and makes the enemy " + SCARRED + " if "
						    + "the enemy was already " + SCARRED + " He heals instead, the damage and heal increases with the runic stacks"
							+ "<br/><br/>GADGETS: <br/>First Gadget: Vollie deals some damage to the enemy and makes them " + SCARRED + " <br/>Second Gadget: "
							+ "Vollie throws himself to an enemy and deals a big damage!"
							+ "<br/><br/>PASSIVE (FAUX FUR): Vollie gets a shield in proportion to the damage he took and the amount of runic stacks he has."
							); 	
					break;
				case "Giran":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Giran waves his stick to the enemy and deals " + atk + " damage. Each third attack, Giran attacks 3 times back to back "
							+ "<br/><br/>SUPER: Giran drops a bunch of explosives on top of the enemy and deals " + sup + " damage, the damage increases each use. (" + HYPERCHARGE +") "
									+ "Bombs deals extra damage and extra damage increases each bomb"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Giran commits cannibalism and eats some fried chicken, healing himself."
							+ "<br/>Second Gadget: Giran swiftly uses his main attack 2 times"
							+ "<br/><br/>PASSIVE (ADRENALINE): There's a %50 chance Giran gets double charge from his attacks"
							);
							break;
				case "Clyde":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: (CANNON) Clyde fires her cannon and deals " + atk + " damage. (SOLO) Clyde fires 3 fireworks to the enemy dealing " + (atk*2) + " damage"
							+ "<br/><br/>SUPER: (CANNON) Clyde shoots herself from her cannon, dealing " + sup + " damage and " + STUNS +" the enemy. She loses her cannon protection and becomes SOLO!"
								+ " (SOLO) Clyde hops back on her cannon, getting her sheld back."
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: (CANNON) Clyde converts her cannon's shield to HP. (SOLO) Clyde heatbutts the enemy with her helmet and " + STUNS + " them."
							+ "<br/>Second Gadget: (CANNON) Clyde charges some super charge. (SOLO) Clyde fully charges her super."
							+ "<br/><br/>PASSIVE (Reckless Stunts): Hopping in and out of the Cannon makes Clyde " + ENRAGED
							);
							break;
				
				case "Amber":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Amber has many weapons on her arsenal that she uses in a cycle 1-) Amber shoots her pistol one at a time, it has an ever decreasing chance to shoot 1 more weaker bullet "
							+ "2-) Amber throws a weakening net that stuns the enemy if already weakened. 3-) Amber brings out her shotgun to deal a massive damage. If the enemy is low on health, she shoots again for good measure! "
							+ "4-) Stolen from Finn, this weapon steals the enemy's HP and gives it to Amber!"
							+ "<br/><br/>SUPER: Amber overcharges her current weapon and uses them in a stronger manner!"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Amber uses the weapon on her hand but doesn't switch it."
							+ "<br/>Second Gadget: Amber changes her weapon to the next in cycle and uses it."
							+ "<br/><br/>PASSIVE (Fast Hands): Each time Amber switches weapons she gets a little shield"
							);
							break; 
							
				case "Anton":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Anton deals some damage to an enemy with a magical attack."
							+ "<br/><br/>SUPER: Anton's staff sends a big wave of magic."
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Anton becomes Furious"
							+ "<br/>Second Gadget: Anton becomes Balanced"
							+ "<br/><br/>PASSIVE (Balanced): Anton has 2 forms: Balanced and Furious! Balanced form deals less damage but heals him more while Furious form deals a lot more damage! Going from balanced to furious"
							+ " makes Anton " + ENRAGED + ". While going from Furious to Balanced heals him."
							);
							break;
				case "Qirale":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Qirale throws a chunk of whatever element she has been using. Air can recursively attack, Water makes the enemy "+WEAKENED+", "
									+ "Earth gets twice the super charge and Fire decreases the enemy's regen"
							+ "<br/><br/>SUPER: Qirale focuses and sends a big shockwave of the element she was holding. Water heals her, Fire burns the enemy, Earth gives her some shield, "
							+ "Air makes the enemy confused!"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Qirale randomly takes on another element"
							+ "<br/>Second Gadget: Qirale uses the element she's holding to help her in this fight!"
							+ "<br/><br/>PASSIVE (Grandmage): Qirale can have 4 elemental modes to choose from! From less damage to most; Air, Water, Earth and Fire! Each"
							+ " with its own unique normal and super attacks!"
							+ "<br><br>SPECIFIC: Qirale takes on a random element she found. (5 uses per match)"
							);
							break;
				case "Olea":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Olea shoots an arrow from his FABOLOUS bow and deals " + atk + " damage. If he is " + INTOXICATED + " he deals more "
							+ "damage and decreases the enemy's regeneration"
							+ "<br/><br/>SUPER: Olea makes himself and the enemy " + INTOXICATED + " for some turns!"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Olea heals himself. If he is " +INTOXICATED+ " he heals more."
							+ "<br/>Second Gadget: Olea makes himself and the enemy " + INTOXICATED+ " for a few turns"
							+ "<br/><br/>PASSIVE (DRAMA QUEEN!): Olea is immune from taking damage from being "+INTOXICATED+""
							);
							break;
				case "Itan":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Itan creates an annoying quake that deals " + atk + " damage"
							+ "<br/><br/>SUPER: Itan summons his best friend, Beurc! Beurc deals " + sup + " damage each turn and tanks damage for Itan."
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Itan makes the enemy " + WEAKENED + ". If Beurc was summoned, they make the enemy " + STUNNED + " instead."
							+ "<br/>Second Gadget: Itan heals a little. If Beurc was summoned They're both healed a lot more."
							+ "<br/><br/>PASSIVE (UNBEARABLE): Hitting an enemy with a quake heals Beurc a little. Beurc hitting the enemy heals Itan a little."
							);
							break; 	
				case "Louis":
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: Louis shoots his trusty electrical gun and deals " + atk + " damage"
							+ "<br/><br/>SUPER: Louis surrounds himself in a electric field that absorbs all damage coming to him for a few turns, at the end, the"
							+ " field explodes, dealing all the damage back to the enemy, It also heals him a little."
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Louis stabs himself and makes himself " + ENRAGED
							+ "<br/>Second Gadget: Louis stabs himself and the enemy"
							+ "<br/><br/>PASSIVE (CLUMSY): Louis' gun has a small chance too overcharge and hurt Louis too. That chance is MUCH bigger if his super is active"
							+ " and hitting himself charges his super too!"
							);
							break; 		
					
					/*
					description.setText("<html><p style=\"width:600px\">"
							+ brawler.name.toUpperCase(Locale.US) + " " + brawler.title.toUpperCase(Locale.US)+
							"<br/>HP: " + brawler.HP + "<br/>REGENERATION: " + brawler.regen
							+ "<br/><br/>ATTACK: "
							+ "<br/><br/>SUPER: "
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: "
							+ "<br/>Second Gadget:"
							+ "<br/><br/>PASSIVE: "
							);
							break; 		 
					*/
					
				default:
					description.setText("<html>" + brawler.title.toUpperCase() +
							 			"<br/>HP: " + brawler.HP +
										"<br/>Attack DMG: " + brawler.AttackDamage +
										"<br/>Regeneration: " + brawler.regen +
										"<br/>Super DMG: " + brawler.SuperDamage + "<html>");
					break;
				case "Potions":
					description.setText("<html><p style=\"width:600px\">"
									  + "Potions are given to each brawler at the start of the match. They are not brawler specific so each potion is the same for every brawler."
									  + "<br/>Currently there are 3 kinds of potions:"
									  + "<br/>Red: Gives HP"
									  + "<br/>Yellow: Gives Super charge"
									  + "<br/>Light Purple: Gives Regeneration"
									  + "<br/>Dark red: Gives Strength to user, %35 more damage."
									  + "<br/>Dark blue: Gives Weakness to enemy, %35 less damage."
									  + "<br/>Green: Gives Confusion to enemy, They attack themselves."
									  + "<br/>Gray: Cleanses and heals the user."
									  + "<br/>Dark Gray: Gives Shield"
									  + "<br>Brown: Deals damage to enemy"
									  + "<br>Dark Purple: Gives Hyper charge");
					break;
				case "Statuses":
					description.setText("<html><p style=\\\"width:600px\\\">"
							+ "Status effects indicate what the brawler can/will do."
							+ "<br/>Normal: Normal, nothing will happen."
							+ "<br/>Stunned: The brawler can't do anything, until the enemy breakes the "
							+ "<br/>stun by hitting them. (H-)" 
							+ "<br/>Weakened: The brawler will deal less damage (S-)" 
							+ "<br/>Strengthened: The brawler will deal more damage (S+)" 
							+ "<br/>Confused: The brawler will deal less damage but to theirself (S-)"
							+ "<br/>Guarded: The brawler won't get effected by anything from the enemy "
							+ "<br/>for a round. (H+)"
							+ "<br/>Enraged: The brawler will deal %100 more damage (S+)"
							+ "<br/>Frosty: The brawler can not regenerate (if the regen is positive) (S-)"
							+ "<br/>Scarred: The enemy loses %1 of their HP each turn (S-)"
							+ "<br/>Intoxicated: The brawler loses %5 of its maximum HP each turn (H-)");
					break;
				case "Gears":
					description.setText("<html><p style=\"width:600px\">Gears are buffs available for every brawler! You just have to choose one:"
							+ "<br/>Health Gear: Increases the Brawler's HP"
							+ "<br/>Potion Gear: Increases the Brawler's potion count by one"
							+ "<br/>Gadget Gear: Increases the Brawler's gadget count by one"
							+ "<br/>Heal Gear: Increases the Brawler's all incoming heals"
							+ "<br/>Cooldown Gear: Decreases spell cooldown"
							+ "<br>Regen Gear: Increases Regeneration"
							+ "<br>Hypercharge Gear: Increases passive hypercharging");
					break;
				case "Spells":
					description.setText("<html><p style=\"width:600px\">Spells are abilities available for every brawler! You just have to choose: "
							+ "<br/>Static Shock: Use all your super charge for damage, " + STUNS + " the enemy if it's used with more than or equal to 100 super charge."
							+ "<br/>Redemption: Heals the user, Deals damage to the enemy."
							+ "<br/>Heart of Steel: Deals damage to the enemy according to the user's current health"
							+ "<br/>Glacial Gale: A strong wind that has a chance to stun and deal more damage!"
							+ "<br/>Electric Storm: A storm that hits harder but rarer each strike."
							+ "<br/>Guardian: Gives the user some " + SHIELD
							+ "<br/>Prixie: Summon Prixie to heal and " + SHIELD + " yourself or attack the enemy and make them " + WEAKENED
							+ "<br/>Last Strike: After a quick charge, quickly throws many small pellets at the enemy!"
							+ "<br/>Demonic Cuteness: Curses an enemy for 5 turns. It deals damage each cursed turn"
							+ "<br/>Mirror: Uses the same spell as the enemy but the cooldown stays the same!"
							+ "<br>Meditation Medication: After 5 turns, heals."
							);
					break;
				case "Abilities":
					description.setText("<html><p style=\"width:600px\">"
							+ "There are 3 main abilities each brawler has that's unique to them and their playstyle!"
							+ "<br/>Normal Attacks: Normally, weak attacks that brawler can use freely that costs nothing. It skips the turn to the enemy, "
							+ "or breaks any stuns they might have. It also charges the super the same as the damage it deals."
							+ "<br/><br/>Super Ability: Strong abilities that can be only used if the brawler has more than 100 super charge. It also consumes all the charge."
							+ "<br/><br/>Gadgets: Small abilities that can be used without the turn going back to the enemy. Varying brawlers have varying amout of charges."
							);
					break;
				case "Hypercharge":
					description.setText("<html><p style=\"width:600px\">"
							+ "Charging your super ability, regenerating, using your super, or just existing in the first place, charges your " + HYPERCHARGE + "! If you have enough hypercharge you automatically "
							+ "activate it to hypercharge your brawler for 15 turns! A hypercharged brawler takes less damage, deals more damage and their super ability becomes much more powerful! Instead of changing the status, "
							+ "it is its own thing!"
							);
					break;
					
				}
				
			}
		});
		lookPanel.add(lookButton);
		con.add(lookPanel);
		
		comboBoxPanel = new JPanel();
		comboBoxPanel.setBounds(15, 70, 140, 50);
		comboBoxPanel.setBackground(Color.lightGray);
		combo.setBackground(new Color(217, 180, 212));
		combo.setFont(new Font("Times New Roman", Font.BOLD ,20));
		combo.setMaximumRowCount(22);
		comboBoxPanel.add(combo);
		con.add(comboBoxPanel);
		
		
		descriptionPanel = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEADING,10, 15));
		descriptionPanel.setBackground(Color.white);
		descriptionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 6));
		descriptionPanel.setBounds(170, 15, 800,730);
		description.setFont(new Font("Calibri", Font.PLAIN, 25));
		
		descriptionPanel.add(description);
		con.add(descriptionPanel);
		
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
				
	}

	public static void patchNotes(String patch) {
	
		JFrame window = new JFrame("Turn by Turn - " + patch);
        window.setSize(850, 800);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.getContentPane().setBackground(Color.darkGray);
        window.setLayout(null);
        window.setIconImage(new ImageIcon("res/images/turnbyturn.png").getImage());
        Container con = window.getContentPane();

        JLabel title = new JLabel(patch, SwingConstants.CENTER);
        title.setBounds(0, 0, 830, 45);
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        con.add(title);

        JPanel notesPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 7, 10));
        notesPanel.setBounds(24, 40, 790, 710);
        notesPanel.setBackground(Color.LIGHT_GRAY);
        notesPanel.setBorder(BorderFactory.createLineBorder(Color.black, 6));

        JLabel notes = new JLabel("");
        notes.setForeground(Color.black);
        notes.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
        
        StringBuilder patchNotesContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/GUI/patchnotes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                patchNotesContent.append(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading patchnotes.txt: " + e.getMessage());
        }

        notes.setText(patchNotesContent.toString());

        notesPanel.add(notes);

        // Wrap notesPanel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(notesPanel);
        scrollPane.setBounds(24, 40, 790, 710);
        scrollPane.setBackground(Color.darkGray);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        con.add(scrollPane);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    
	
}

	

//End of main class
}
