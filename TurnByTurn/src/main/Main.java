package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

import Fighters.*;
import GUI.*;

public class Main {
	
	public Main(){}
	
	public static void main(String[] args){
		
		SoundManager.intro.play();
			
		Build defaultBuild = new Build();
		Fighter[] fighters = Fighter.fighters;
		
		Fighter[] chosens = new Fighter[2];
		
		JFrame window = new JFrame();
		window.setSize(900,600);
		window.setTitle("TURN BY TURN! Client");
		Container con;
		
		JPanel titleNamePanel,
			   titleCreditPanel,
			   quitPanel,
			   startButtonPanel, 
			   player1FighterPanel, 
			   player2FighterPanel, 
			   helpPanel,
			   versionPanel,
			   patchNotesPanel,
			   gamemodePanel;
		
		JLabel titleName,titleCredit,version,gamemodeLabel;
		JButton startButton, quitButton, helpButton, patchNotes, player1Fighter,player2Fighter;
		JComboBox<String> gamemode;
		
		String[] fighterName = new String[fighters.length];
		for(int i = 0; i < fighters.length; i++) {
			fighterName[i] = fighters[i].name;
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
		version = new JLabel("P-BETA 1.3", SwingConstants.LEFT);
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
						"Technical",
						"Hypermode",
						"Nostalgic"
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
				gamemodePanel.setBounds(390,360,120,60);
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
					Game.playGame1v1(chosens[0], chosens[1], "Choose");
					}
					else
						JOptionPane.showMessageDialog(window, "Duplicate fighters are not allowed.");
					break;
				
				case "Classic":
					Random ran = new Random();
					
					Fighter chosenFighter1 = new Todd(defaultBuild);
					Fighter chosenFighter2 = new Todd(defaultBuild);
					
					int chosen1 = 0, chosen2 = 0;
					while(chosen1 == chosen2){
					int len = fighters.length;
					chosen1 = new Random().nextInt(0, len);	
					chosen2 = new Random().nextInt(0, len);
					chosenFighter1 = fighters[chosen1].newInstance();
					chosenFighter2 = fighters[chosen2].newInstance();
					}
					chosenFighter1.build = new Build(ran);
					chosenFighter2.build = new Build(ran);
					
					Game.playGame1v1(chosenFighter1,chosenFighter2, "Classic");
					break;
					
					
				case "Duplicates":
					if(chosens[0] != null && chosens[1] != null && chosens[1] == chosens[0]) {
						Game.playGame1v1(chosens[0], chosens[1], "Duplicates");
					}
					else {
						JOptionPane.showMessageDialog(window, "Chosen fighters are not duplicates");	
					}
					break;
					
				case "Wizardary":
					if(chosens[0] != null && chosens[1] != null && chosens[1] != chosens[0]) {
						Game.playGame1v1(chosens[0], chosens[1], "Wizardary");
					}
					else
						JOptionPane.showMessageDialog(window, "Duplicate fighters are not allowed.");
					break;
					
				case "Decaying":
					if(chosens[0] != null && chosens[1] != null && chosens[1] != chosens[0]) {
						Game.playGame1v1(chosens[0], chosens[1], "Decaying");
					}
					else
						JOptionPane.showMessageDialog(window, "Duplicate fighters are not allowed.");
					break;
					
				case "Technical":
					if(chosens[0] != null && chosens[1] != null && chosens[1] != chosens[0]) {
						Game.playGame1v1(chosens[0], chosens[1], "Technical");
					}
					else
						JOptionPane.showMessageDialog(window, "Duplicate fighters are not allowed.");
					break;
				case "Hypermode":
					if(chosens[0] != null && chosens[1] != null && chosens[1] != chosens[0]) {
						Game.playGame1v1(chosens[0], chosens[1], "Hypermode");
					}
					else
						JOptionPane.showMessageDialog(window, "Duplicate fighters are not allowed.");
					break;
				case "Nostalgic":
					if(chosens[0] != null && chosens[1] != null && chosens[1] != chosens[0]) {
						Game.playGame1v1(chosens[0], chosens[1], "Nostalgic");
					}
					else
						JOptionPane.showMessageDialog(window, "Duplicate fighters are not allowed.");
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
				
				Fighter.isCallAllowed = true; 
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
				
				JLabel fighterSoundsLabel = new JLabel("Fighter Sound Effects:");
				fighterSoundsLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
				fighterSoundsLabel.setBounds(10,50, 160,30);
				wincon.add(fighterSoundsLabel);				
				JToggleButton fighterSoundsButton = new JToggleButton();
				fighterSoundsButton.setBounds(170,50, 30,30);
				wincon.add(fighterSoundsButton);
				
				
				
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
			HelpScreen.helpScreen(fighters, fighterName);
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
		
		player1FighterPanel = new JPanel();
		player1FighterPanel.setBounds(20, 200, 200, 100);
		player1FighterPanel.setBackground(Color.darkGray);
		player1Fighter = new JButton("SELECT FIGHTER");
		player1Fighter.setBackground(new Color(217, 180, 212) );
		player1Fighter.setFocusable(false);
		player1Fighter.setFont(new Font("Times New Roman", Font.BOLD ,20));
		player1Fighter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SoundManager.click.play();

				JWindow fighterChooseWindow1 = new JWindow();
				fighterChooseWindow1.setLocationRelativeTo(null);
				fighterChooseWindow1.setIconImage(new ImageIcon("res/images/turnbyturn.png").getImage());
				fighterChooseWindow1.setBounds(375,280,786,390);
				fighterChooseWindow1.getContentPane().setBackground(Color.LIGHT_GRAY);
				fighterChooseWindow1.setVisible(true);
				fighterChooseWindow1.setLayout(null);
				Container bcwcon1 = fighterChooseWindow1.getContentPane();
				
				
				JLabel welcome = new JLabel("Choose your Fighter!");
				welcome.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
				welcome.setHorizontalAlignment(SwingConstants.CENTER);
				welcome.setVerticalAlignment(SwingConstants.CENTER);
				welcome.setBounds(580, 20, 200 , 20);
				bcwcon1.add(welcome);
				
				JLabel expl = new JLabel("<html><p style=\"width:110px\"> Select a fighter to get some brief information! <html>");
				expl.setBackground(Color.white);
				expl.setOpaque(true);
				expl.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
				expl.setBounds(600, 140 , 150 ,180);
				expl.setHorizontalAlignment(SwingConstants.LEFT);
				expl.setVerticalAlignment(SwingConstants.TOP);
				expl.setBorder(BorderFactory.createLineBorder(Color.black, 2) );
				bcwcon1.add(expl);
				
				FighterSelectionGrid bsg1 = new FighterSelectionGrid(welcome, expl);
				bsg1.setBounds(30, 20, 550, 350);
				bsg1.setVisible(true);
				bcwcon1.add(bsg1);
	
				JPanel buttonPane1 = new JPanel();
				buttonPane1.setBounds(630, 50, 100, 40);
				buttonPane1.setBackground(Color.lightGray);
				JButton player1FighterButton = new JButton("Lock in");
				player1FighterButton.setBackground(Color.black);
				player1FighterButton.setForeground(Color.white);
				player1FighterButton.setFont(new Font("Arial", Font.PLAIN, 18 ));
				player1FighterButton.setFocusable(false);
				player1FighterButton.setVisible(true);
				player1FighterButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							
						boolean bool = bsg1.randomlyChosen;
						if(bool) {
							fighterChooseWindow1.dispose();
							Fighter.fighterCall(welcome.getText().toLowerCase());
							return;						
						}
						
						
						String chosenFighterName =  (String) bsg1.getSelectedFighter();
						for(int i = 0; i < fighters.length; i++) 
							if (fighters[i].name == chosenFighterName) {
								chosens[0] = fighters[i];					
								p1.setText(fighters[i].name);
								fighterChooseWindow1.dispose();
								Fighter.fighterCall(welcome.getText().toLowerCase());
							}	
					}
				});
				buttonPane1.add(player1FighterButton);
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
							
						int len = fighters.length;
						int chosen = new Random().nextInt(0, len);
						chosens[0] = fighters[chosen];
						chosens[0].build = new Build(new Random());
						
						p1.setText(fighters[chosen].name);
						welcome.setText(fighters[chosen].name.toUpperCase());
                        welcome.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
                        p1.setText(fighters[chosen].name);
                        
                        bsg1.randomlyChosen = true;
                        bsg1.changeLabels( fighters[chosen].name);
						
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

						fighterChooseWindow1.dispose();
					}
				});
				exit1Panel.add(exit1);
				bcwcon1.add(exit1Panel);
				
				
			}
		});
		player1FighterPanel.add(player1Fighter);
			
		//Player 2 choosing character
		JPanel p2p = new JPanel(); 
		JLabel p2 = new JLabel("PLAYER 2", JLabel.RIGHT);
		p2p.setBounds(760,165, 100 , 40);
		p2.setFont(new Font("Arial", Font.BOLD, 20));
		p2.setForeground(Color.white);
		p2p.setBackground(Color.DARK_GRAY);
		p2p.add(p2);
		con.add(p2p);
		
		player2FighterPanel = new JPanel();
		player2FighterPanel.setBounds(665, 200, 200, 100);
		player2FighterPanel.setBackground(Color.darkGray);
		player2Fighter = new JButton("SELECT FIGHTER");
		player2Fighter.setBackground(new Color(217, 180, 212) );
		player2Fighter.setFocusable(false);
		player2Fighter.setFont(new Font("Times New Roman", Font.BOLD ,20));
		player2Fighter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SoundManager.click.play();

				JWindow fighterChooseWindow1 = new JWindow();
				fighterChooseWindow1.setLocationRelativeTo(null);
				fighterChooseWindow1.setIconImage(new ImageIcon("res/images/turnbyturn.png").getImage());
				fighterChooseWindow1.setBounds(375,280,786,390);
				fighterChooseWindow1.getContentPane().setBackground(Color.LIGHT_GRAY);
				fighterChooseWindow1.setVisible(true);
				fighterChooseWindow1.setLayout(null);
				Container bcwcon1 = fighterChooseWindow1.getContentPane();
				
				
				JLabel welcome = new JLabel("Choose your Fighter!");
				welcome.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
				welcome.setHorizontalAlignment(SwingConstants.CENTER);
				welcome.setVerticalAlignment(SwingConstants.CENTER);
				welcome.setBounds(580, 20, 200 , 20);
				bcwcon1.add(welcome);
				
				JLabel expl = new JLabel("<html><p style=\"width:110px\"> Select a fighter to get some brief information! <html>");
				expl.setBackground(Color.white);
				expl.setOpaque(true);
				expl.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
				expl.setBounds(600, 140 , 150 ,180);
				expl.setHorizontalAlignment(SwingConstants.LEFT);
				expl.setVerticalAlignment(SwingConstants.TOP);
				expl.setBorder(BorderFactory.createLineBorder(Color.black, 2) );
				bcwcon1.add(expl);
				
				FighterSelectionGrid bsg1 = new FighterSelectionGrid(welcome, expl);
				bsg1.setBounds(30, 20, 550, 350);
				bsg1.setVisible(true);
				bcwcon1.add(bsg1);
				
				JPanel buttonPane1 = new JPanel();
				buttonPane1.setBounds(630, 50, 100, 40);
				buttonPane1.setBackground(Color.lightGray);
				JButton player1FighterButton = new JButton("Lock in");
				player1FighterButton.setBackground(Color.black);
				player1FighterButton.setForeground(Color.white);
				player1FighterButton.setFont(new Font("Arial", Font.PLAIN, 18 ));
				player1FighterButton.setFocusable(false);
				player1FighterButton.setVisible(true);
				player1FighterButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							
						boolean bool = bsg1.randomlyChosen;
						if(bool) {
							fighterChooseWindow1.dispose();
							Fighter.fighterCall(welcome.getText().toLowerCase());
							return;						
						}
						
						
						String chosenFighterName =  (String) bsg1.getSelectedFighter();
						for(int i = 0; i < fighters.length; i++) 
							if (fighters[i].name == chosenFighterName) {
								chosens[1] = fighters[i];					
								p2.setText(fighters[i].name);
								fighterChooseWindow1.dispose();
								Fighter.fighterCall(welcome.getText().toLowerCase());
							}	
					}
				});
				buttonPane1.add(player1FighterButton);
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
							
						int len = fighters.length;
						int chosen = new Random().nextInt(0, len);
						chosens[1] = fighters[chosen];
						chosens[1].build = new Build(new Random());
						
						p2.setText(fighters[chosen].name);
						welcome.setText(fighters[chosen].name.toUpperCase());
                        welcome.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
                        p2.setText(fighters[chosen].name);
                        
                        bsg1.randomlyChosen = true;
                        bsg1.changeLabels( fighters[chosen].name);
						
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

						fighterChooseWindow1.dispose();
					}
				});
				exit1Panel.add(exit1);
				bcwcon1.add(exit1Panel);
				
				
			}
		});
		player2FighterPanel.add(player2Fighter);
		con.add(player2FighterPanel);
			
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
				PatchNotes.patchNotes(version.getText());
				
			}
		});
		patchNotesPanel.add(patchNotes);
		
		
		con.add(titleNamePanel);
		con.add(startButtonPanel);
		con.add(player1FighterPanel);
		con.add(player2FighterPanel);
		con.add(quitPanel);
		con.add(titleCreditPanel);
		con.add(helpPanel);
		con.add(patchNotesPanel);
		con.add(gamemodePanel);
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);	
		
	}	

//End of main class
}
