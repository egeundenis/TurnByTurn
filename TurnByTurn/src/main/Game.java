package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Fighters.*;
import GUI.ChargeBar;
import GUI.HealthBar;
import GUI.HyperBar;

public class Game {

	public Game() {}

	public static void playGame1v1(Fighter A1, Fighter B2, String gamemode) {
		
		SoundManager.intro.play();
				
		Fighter[] fighters = Fighter.fighters;
		
		Random ran = new Random();
		Fighter A = A1.newInstance();
		Fighter B = B2.newInstance();	
		
		String potionA = A.build.potionChoise;
		String potionB = B.build.potionChoise;
		
		A.enemy = B;
		B.enemy = A;
		
		if(gamemode == "Wizardary") {
			A.potionCount = 10;
			B.potionCount = 10;
		}
		
		if(gamemode == "Technical") {
			A.gadgetCount += 5;
			B.gadgetCount += 5;
		}
		
		if(gamemode == "Hypermode") {
			SoundManager.hypercharge.play();
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
		
		//Fighter specific icon
		
		JLabel fighterSpecific1 = new JLabel();
		fighterSpecific1.setBounds(355, 125 ,50 , 50);
		fighterSpecific1.setVisible(true);
		fighterSpecific1.setIcon(empty);
		con.add(fighterSpecific1);
		
		JLabel fighterSpecific2 = new JLabel();
		fighterSpecific2.setBounds(585, 125 ,50 , 50);
		fighterSpecific2.setVisible(true);
		fighterSpecific2.setIcon(empty);
		con.add(fighterSpecific2);
		
		JLabel[] fighterLabels = {
				hyperCountdown1, 
				hypericon1, 
				hyperCountdown2, 
				hypericon2, 
				fighterSpecific1, //4
				fighterSpecific2  //5
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
		Potion1Amount = new JLabel(A.potionCount+"");
		Potion2Amount = new JLabel(B.potionCount+"");
		Gadget1Amount = new JLabel(A.gadgetCount+"");
		Gadget2Amount = new JLabel(B.gadgetCount+"");
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
		ImageIcon poisoned = new ImageIcon("res/images/poisoned.png");
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
				intoxicated,
				poisoned
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
		
		
		//Trait Icon
		ImageIcon tanktrait = new ImageIcon("res/images/tank_trait.png");
		ImageIcon magicaltrait = new ImageIcon("res/images/magical_trait.png");
		ImageIcon normaltrait = new ImageIcon("res/images/normal_trait.png");
		JLabel TRAITICON1 = new JLabel();
		switch(A.trait) {
			case Normal:
				TRAITICON1.setIcon(normaltrait);
				break;
			case Tank:
				TRAITICON1.setIcon(tanktrait);
				break;
			case Magical:
				TRAITICON1.setIcon(magicaltrait);
				break;
		}
		TRAITICON1.setBounds(177,64,50,45);
		JLabel TRAITICON2 = new JLabel();
		switch(B.trait) {
			case Normal:
				TRAITICON2.setIcon(normaltrait);
				break;
			case Tank:
				TRAITICON2.setIcon(tanktrait);
				break;
			case Magical:
				TRAITICON2.setIcon(magicaltrait);
				break;
		}
		TRAITICON2.setBounds(760,64,50,45);
		con.add(TRAITICON1); con.add(TRAITICON2);
		TRAITICON1.setVisible(true);
		TRAITICON2.setVisible(true);
		
		
		//Gamemode Label
		gMode = new JLabel(gamemode);
		gMode.setBounds(7,698,150,50);
		gMode.setFont(new Font("Arial", Font.BOLD, 20));
		con.add(gMode);
		
		//Ability Names
		String[][] abilityNames = Build.abilityNames;
		
		int Aindex = 0;
		for (int i = 0; i < fighters.length; i++)
			if (fighters[i].name == A.name)
				Aindex = i;
		
		int Bindex = 0;
		for (int i = 0; i < fighters.length; i++)
			if (fighters[i].name == B.name)
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
		//Target Choosing Buttons
		
		JPanel TargetPanel1 = new JPanel();
		TargetPanel1.setBounds(205, 270, 30, 30);
		TargetPanel1.setBackground(Color.lightGray);
		JButton Target1 = new JButton();
		Target1.setFont(new Font("Arial", Font.PLAIN, 20));
		Target1.setBackground(Color.LIGHT_GRAY);
		Target1.setForeground(Color.white);
		Target1.setFocusable(false);
		Target1.setBorderPainted(false);
		Target1.setVisible(true);
		
		P1buttons.add(Target1);
		Target1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		TargetPanel1.setVisible(false);
		TargetPanel1.add(Target1);
		con.add(TargetPanel1);
		
		
		JPanel TargetPanel2 = new JPanel();
		TargetPanel2.setBounds(630, 270, 30, 30);
		TargetPanel2.setBackground(Color.lightGray);
		JButton Target2 = new JButton();
		Target2.setFont(new Font("Arial", Font.PLAIN, 20));
		Target2.setBackground(Color.DARK_GRAY);
		Target2.setForeground(Color.white);
		Target2.setFocusable(false);
		Target2.setBorderPainted(false);
		Target2.setVisible(true);
		
		P2buttons.add(Target2);
		
		//SPESIFIC ABILITY
		Target2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		TargetPanel2.setVisible(false);
		TargetPanel2.add(Target2);
		con.add(TargetPanel2);
		
		
		if(A.canThisFighterChooseTarget || B.canThisFighterChooseTarget) {
			TargetPanel1.setVisible(true);
			TargetPanel2.setVisible(true);
			Target1.setVisible(true);
			Target2.setVisible(true);
			Target1.setIcon(new ImageIcon("res/images/target.png"));
			Target2.setIcon(new ImageIcon("res/images/target.png"));
		}

		
		*/
		
		//Fighter Specific Buttons
		
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
				if(A.fighterSpecificActivity(B)) 	
					enableOrDisable(A, B, P1buttons, P2buttons, "ALL");					
				else
					enableOrDisable(A, B, P1buttons, P2buttons, "SPECIFIC");
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2, shbar1, shbar2, scbar1, scbar2, hcbar1, hcbar2, TM, turn,Name1, Name2 ,abilityNamesArray, fighterLabels, gamemode);					
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
				if(B.fighterSpecificActivity(A)) 	
					enableOrDisable(B, A, P2buttons, P1buttons, "ALL");					
				else
					enableOrDisable(B, A, P2buttons, P1buttons, "SPECIFIC");					

				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2, shbar1, shbar2, scbar1, scbar2, hcbar1, hcbar2, TM, turn,Name1, Name2 ,abilityNamesArray, fighterLabels, gamemode);					
			}
		});
		
		SpePanel2.setVisible(false);
		SpePanel2.add(Specific2);
		con.add(SpePanel2);
		
		if(A.name.equals("Qirale")) {						
			SpePanel1.setVisible(true);		
			Specific1.setIcon(new Qirale(new Build()).qirale_specific);
		}
		
		if(A.name.equals("John")) {
			SpePanel1.setVisible(true);		
			Specific1.setIcon(new John(new Build()).john_specific);
		}
		
		if(A.name.equals("Anton")) {
			SpePanel1.setVisible(true);		
			Specific1.setIcon(new Anton(new Build()).anton_specific);
		}
		
		if(A.name.equals("Aboa")) {
			SpePanel1.setVisible(true);		
			Specific1.setIcon(new Aboa(new Build()).aboa_specific);
		}
		
		if(B.name.equals("Qirale")) {						
			SpePanel2.setVisible(true);		
			Specific2.setIcon(new Qirale(new Build()).qirale_specific);
		}
		
		if(B.name.equals("John")) {
			SpePanel2.setVisible(true);		
			Specific2.setIcon(new John(new Build()).john_specific);
		}
		
		if(B.name.equals("Anton")) {
			SpePanel2.setVisible(true);		
			Specific2.setIcon(new Anton(new Build()).anton_specific);
		}
		
		if(B.name.equals("Aboa")) {
			SpePanel2.setVisible(true);		
			Specific2.setIcon(new Aboa(new Build()).aboa_specific);
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
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2, shbar1, shbar2, scbar1, scbar2, hcbar1, hcbar2, TM, turn,Name1, Name2 ,abilityNamesArray, fighterLabels, gamemode);
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2,scbar1, scbar2, buttons);		
				superChargeCheck(A, Super1, B, Super2);
				if(A.stat == Status.Stunned || B.stat == Status.Stunned)
					 enableOrDisable(A, B, P1buttons, P2buttons, "ALL");
				updateEnhancedNormal(A, B, Attack1, Attack2);
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
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2, scbar1, scbar2, hcbar1, hcbar2, TM, turn,Name1, Name2,abilityNamesArray, fighterLabels, gamemode);	
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2, scbar1, scbar2, buttons);	
				superChargeCheck(A, Super1, B, Super2);
				if(A.stat == Status.Stunned || B.stat == Status.Stunned)
					enableOrDisable(B, A, P2buttons, P1buttons, "ALL");
				updateEnhancedNormal(A, B, Attack1, Attack2);
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
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2, shbar1, shbar2, scbar1, scbar2, hcbar1, hcbar2, TM, turn,Name1, Name2,abilityNamesArray, fighterLabels, gamemode);
				enableOrDisable(A, B, P1buttons, P2buttons, "ALL");
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2, scbar1, scbar2, buttons);	
				superChargeCheck(A, Super1, B, Super2);
				if(A.stat == Status.Stunned || B.stat == Status.Stunned)
					 enableOrDisable(A, B, P1buttons, P2buttons, "ALL");
				updateEnhancedNormal(A, B, Attack1, Attack2);
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
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2, scbar1, scbar2, hcbar1, hcbar2, TM, turn,Name1, Name2,abilityNamesArray, fighterLabels, gamemode);
				enableOrDisable(B, A, P2buttons, P1buttons, "ALL");
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2,scbar1, scbar2, buttons);	
				superChargeCheck(A, Super1, B, Super2);
				if(A.stat == Status.Stunned || B.stat == Status.Stunned)
					enableOrDisable(B, A, P2buttons, P1buttons, "ALL");
				updateEnhancedNormal(A, B, Attack1, Attack2);
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
				SoundManager.gadget.play();
				gadget(A, B);
				superChargeCheck(A, Super1, B, Super2);
				enableOrDisable(A, B, P1buttons, P2buttons, "GADGET_POTION");				
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2,scbar1, scbar2,  hcbar1, hcbar2,TM, turn,Name1, Name2,abilityNamesArray, fighterLabels, gamemode);
				cooldownCheck(A, Spell1);
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2,scbar1, scbar2, buttons);	
				superChargeCheck(A, Super1, B, Super2);
				if(A.stat == Status.Stunned || B.stat == Status.Stunned)
					 enableOrDisable(A, B, P1buttons, P2buttons, "ALL");
				updateEnhancedNormal(A, B, Attack1, Attack2);
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
		Gadget2Amount.setBounds(775,462,50,50);
		Gadget2Amount.setFont(new Font("Arial", Font.PLAIN, 20));
		con.add(Gadget2Amount);
		Gadget2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SoundManager.gadget.play();
				gadget(B, A);
				superChargeCheck(A, Super1, B, Super2);
				enableOrDisable(B, A, P2buttons,P1buttons , "GADGET_POTION");
				
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2, scbar1, scbar2, hcbar1, hcbar2, TM, turn,Name1, Name2,abilityNamesArray, fighterLabels, gamemode);
				cooldownCheck(B, Spell2);
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2,scbar1, scbar2, buttons);	
				superChargeCheck(A, Super1, B, Super2);
				if(A.stat == Status.Stunned || B.stat == Status.Stunned)
					enableOrDisable(B, A, P2buttons, P1buttons, "ALL");
				updateEnhancedNormal(A, B, Attack1, Attack2);
				
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
				SoundManager.potion.play();
				potion(A, B, potionA, Potion1, Super1);
				superChargeCheck(A, Super1, B, Super2);
				enableOrDisable(A, B, P1buttons, P2buttons, "GADGET_POTION");
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2,scbar1, scbar2,  hcbar1, hcbar2,TM, turn,Name1, Name2,abilityNamesArray, fighterLabels, gamemode);
				cooldownCheck(A, Spell1);
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2, scbar1, scbar2, buttons);	
				superChargeCheck(A, Super1, B, Super2);
				if(A.stat == Status.Stunned || B.stat == Status.Stunned)
					 enableOrDisable(A, B, P1buttons, P2buttons, "ALL");
				updateEnhancedNormal(A, B, Attack1, Attack2);
				
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
		Potion2Amount.setBounds(815,540,50,50);
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
				
				SoundManager.potion.play();
				potion(B, A, potionB, Potion2, Super2);
				superChargeCheck(A, Super1, B, Super2);			
				enableOrDisable(B, A, P2buttons, P1buttons, "GADGET_POTION");
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2,scbar1, scbar2,  hcbar1, hcbar2,TM, turn,Name1, Name2,abilityNamesArray, fighterLabels, gamemode);
				cooldownCheck(B, Spell2);
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2, scbar1, scbar2, buttons);	
				superChargeCheck(A, Super1, B, Super2);
				if(A.stat == Status.Stunned || B.stat == Status.Stunned)
					enableOrDisable(B, A, P2buttons, P1buttons, "ALL");
				updateEnhancedNormal(A, B, Attack1, Attack2);
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
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2,scbar1, scbar2,  hcbar1, hcbar2,TM, turn,Name1, Name2,abilityNamesArray, fighterLabels, gamemode);
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2,scbar1, scbar2, buttons);		
				superChargeCheck(A, Super1, B, Super2);
				if(A.stat == Status.Stunned || B.stat == Status.Stunned)
					 enableOrDisable(A, B, P1buttons, P2buttons, "ALL");
				updateEnhancedNormal(A, B, Attack1, Attack2);
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
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2, scbar1, scbar2, hcbar1, hcbar2, TM, turn,Name1, Name2,abilityNamesArray, fighterLabels, gamemode);	
				isOver(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, STATICON1, STATICON2, Name1, Name2, icons, hpbar1, hpbar2, scbar1, scbar2, buttons);	
				superChargeCheck(A, Super1, B, Super2);
				if(A.stat == Status.Stunned || B.stat == Status.Stunned)
					enableOrDisable(B, A, P2buttons, P1buttons, "ALL");
				updateEnhancedNormal(A, B, Attack1, Attack2);
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
				Potion1Amount.setText(A.potionCount+"");
				Potion2Amount.setText(B.potionCount+"");
				Gadget1Amount.setText(A.gadgetCount+"");
				Gadget2Amount.setText(B.gadgetCount+"");
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
				
				update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2, STATICON1, STATICON2, icons, hpbar1, hpbar2, shbar1, shbar2, scbar1, scbar2,  hcbar1, hcbar2,TM, turn ,Name1, Name2,abilityNamesArray, fighterLabels, gamemode);
				
				
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
		

		update(A, B, HP1, HP2, SG1, SG2, REG1, REG2, Potion1Amount, Potion2Amount, Gadget1Amount, Gadget2Amount, cd1,cd2,STATICON1, STATICON2, icons, hpbar1, hpbar2,shbar1, shbar2,scbar1, scbar2,  hcbar1, hcbar2,TM, turn, Name1, Name2,abilityNamesArray, fighterLabels, gamemode);
		enableOrDisable(A, B, P1buttons, P2buttons, "ALL");
		enableOrDisable(B, A, P2buttons, P1buttons, "ALL");
		
	}	
	 
	public static void attack(Fighter attacker, Fighter attacked) {	
		
		//Confused
		if (attacker.stat == Status.Confused) {
			attacker.attackAbility(attacker);
			attacker.changeSTATUS(Status.Normal);
			return;
		}
		
		//Guarded
		if (attacked.stat == Status.Guarded) {
			SoundManager.noten.play();
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
	
	public static boolean superAbility(Fighter attacker, Fighter attacked, JButton Super1, JButton Super2, TurnManager TM) {


	    if (attacker.SuperCharge >= 100) {

	        int enemyHPprev = attacked.shield + attacked.HP;
	        Status initialEnemyStatus = attacked.stat;

	        attacker.superAbility(attacked);
	        attacker.changeCHARGE(-100);

	        // Calculate the final state of the attacked Fighter
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
	    	SoundManager.noten.play();
	        return false;
	    }
	}
		
	public static void superChargeCheck(Fighter A, JButton asuper, Fighter B, JButton bsuper) {

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
	
	public static void regenerate(Fighter A, Fighter B) {
		if(A.stat != Status.Stunned && (A.stat != Status.Frosty || A.regen < 0) )
			A.regenerate();
		if(B.stat != Status.Stunned && (B.stat != Status.Frosty || B.regen < 0))
			B.regenerate();
	}
	
	public static void eachTurnPassives(Fighter A, Fighter B) {
		
		
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
			
			A.eachTurnChecks(B);
			B.eachTurnChecks(A);

			
			//Effective Spells
			if(A.spell.effectTurn != -1) {
				switch(A.spell.name) {
				
				case "Demonic Cuteness":
					if(A.spell.effectTurn == 0)
						break;
					B.changeHP(-10);
					SoundManager.demonic_burn.play();
					A.spell.effectTurn--;
				break;
				
				case "Meditation Medication":
					if(A.spell.effectTurn == 0) {
						A.changeHP(80);
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
					A.changeHP(-10);
					SoundManager.demonic_burn.play();
					B.spell.effectTurn--;
				break;
				
				case "Meditation Medication":
					if(B.spell.effectTurn == 0) {
						B.changeHP(80);
						B.spell.effectTurn--;
						break;
					}					
					B.spell.effectTurn--;
				break;
				
				}
			}
			
	//end of eachturnpassives function		
	}

	public static void update(Fighter A, 
							  Fighter B, 
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
							  JLabel[] fighterLabels,
							  String gamemode) {
		
		
		//Overtime
		TM.increaseTurn();
		turn.setText("Turn " + TM.getTurn());
		if(TM.getTurn() == 99)
			SoundManager.overtime.play();
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
		
		//Hypermode
		if(gamemode.equals("Hypermode")) {
			A.HyperCharge += 9999;
			B.HyperCharge += 9999;
		}
		
		//Nostalgic
		if(gamemode.equals("Nostalgic")) {
			A.gadgetCount = 0;
			A.potionCount = 0;
			A.spell.cooldown = 9999;
			B.gadgetCount = 0;
			B.potionCount = 0;
			B.spell.cooldown = 9999;
		} 
		
		//Each Turn Passive
		eachTurnPassives(A, B);
		
		//fighter specific things
		updateFighterSpecific(abilityNames, fighterLabels, A, B);
					
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
		Potion1Amount.setText(A.potionCount+"");
		Potion2Amount.setText(B.potionCount+"");
		Gadget1Amount.setText(A.gadgetCount+"");
		Gadget2Amount.setText(B.gadgetCount+"");
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
		else if (A.stat == Status.Poisoned)
			STATICON1.setIcon(icons[11]);
		else {
			STATICON1.setIcon(icons[0]);
			Name1.setForeground(Color.black);
		}
		
		if (A.isHypercharged) {
			Name1.setForeground(new Color(181,11,204));
		}
		else{
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
		else if (B.stat == Status.Poisoned)
			STATICON2.setIcon(icons[11]);
		else {
			STATICON2.setIcon(icons[0]);
			Name2.setForeground(Color.black);
		}
		
		if (B.isHypercharged) {
			Name2.setForeground(new Color(181,11,204));
		}else {
			Name2.setForeground(Color.black);
		}
		
		//Color
		if(A.name.equals("Rits")) {
			Rits temp = (Rits) A;
			if(temp.passive > 0)
				Name1.setForeground(Color.red);
		}				
		if(B.name.equals("Rits")) {
			Rits temp = (Rits) B;
			if(temp.passive > 0)
				Name2.setForeground(Color.red);
		}
		
	}
	
	public static void updateFighterSpecific(JLabel[] abilityNames,
			  								 JLabel[] fighterLabels,
			  								 Fighter A, 
			  							     Fighter B) {
		
		ImageIcon stackIcon = new ImageIcon("res/images/stack_count.png");
		
		
		//Hypercharge!
				if(A.hyperchargeTurn != -1) {
					A.isHypercharged = true;
					A.hyperchargeTurn--;
					fighterLabels[1].setText((A.hyperchargeTurn+1)+"");
					fighterLabels[1].setVisible(true);
					fighterLabels[1].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[1].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[1].setVerticalTextPosition(JLabel.CENTER);
					
				}else {
					A.isHypercharged = false;
					fighterLabels[0].setVisible(false);
					fighterLabels[1].setVisible(false);
				}
				
				if(B.hyperchargeTurn != -1) {
					B.isHypercharged = true;
					B.hyperchargeTurn--;
					fighterLabels[3].setText((B.hyperchargeTurn+1)+"");
					fighterLabels[3].setVisible(true);
					fighterLabels[3].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[3].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[3].setVerticalTextPosition(JLabel.CENTER);
				}else {
					B.isHypercharged = false;
					fighterLabels[2].setVisible(false);
					fighterLabels[3].setVisible(false);
				}
		
		//Passives
				
				//Timmy
				if(A.name.equals("Timmy")) {
					Timmy temp = (Timmy) A;
					if (temp.HP < (int) (temp.newInstance().HP * 0.30))
						fighterLabels[4].setIcon(temp.timmy_dragon);
					else
						fighterLabels[4].setIcon(null);
				}
				
				if(B.name.equals("Timmy")) {
					Timmy temp = (Timmy) B;
					if (temp.HP < (int) (temp.newInstance().HP * 0.30))
						fighterLabels[5].setIcon(temp.timmy_dragon);
					else
						fighterLabels[5].setIcon(null);
				}
				
				//Itan
				if(A.name.equals("Itan")) {				
					Itan temp = (Itan) A;
					if (temp.BeurcHP > 0) {
						fighterLabels[4].setText(temp.BeurcHP+"");
						fighterLabels[4].setVisible(true);
						fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
						fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
						fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						fighterLabels[4].setText("");
				}
				
				if(B.name.equals("Itan")) {				
					Itan temp = (Itan) B;
					if (temp.BeurcHP > 0) {
						fighterLabels[5].setText(temp.BeurcHP+"");
						fighterLabels[5].setVisible(true);
						fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
						fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
						fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						fighterLabels[5].setText("");
				}
				
				//Felix
				if(A.name.equals("Felix")) {				
					Felix temp = (Felix) A;
					if (temp.AttackDamage > 0) {
						fighterLabels[4].setText(temp.AttackDamage+"");
						fighterLabels[4].setVisible(true);
						fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
						fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
						fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						fighterLabels[4].setText("");
				}
				
				if(B.name.equals("Felix")) {				
					Felix temp = (Felix) B;
					if (temp.AttackDamage > 0) {
						fighterLabels[5].setText(temp.AttackDamage+"");
						fighterLabels[5].setVisible(true);
						fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
						fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
						fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						fighterLabels[5].setText("");
				}
				
				//Todd
				if(A.name.equals("Todd")) {				
					Todd temp = (Todd) A;
					if (temp.superTurns >= 0) {
						fighterLabels[4].setText(temp.superTurns+"");
						fighterLabels[4].setVisible(true);
						fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
						fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
						fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						fighterLabels[4].setText("");
				}
				
				if(B.name.equals("Todd")) {				
					Todd temp = (Todd) B;
					if (temp.superTurns >= 0) {
						fighterLabels[5].setText(temp.superTurns+"");
						fighterLabels[5].setVisible(true);
						fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
						fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
						fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						fighterLabels[5].setText("");
				}
				
				//Light
				if(A.name.equals("Light")) {				
					Light temp = (Light) A;
					if (temp.lightInt > 0) {
						fighterLabels[4].setText(temp.lightInt+"");
						fighterLabels[4].setVisible(true);
						fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
						fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
						fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						fighterLabels[4].setText("");
				}
				
				if(B.name.equals("Light")) {				
					Light temp = (Light) B;
					if (temp.lightInt > 0) {
						fighterLabels[5].setText(temp.lightInt+"");
						fighterLabels[5].setVisible(true);
						fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
						fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
						fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						fighterLabels[5].setText("");
				}
				
				//Louis
				if(A.name.equals("Louis")) {				
					Louis temp = (Louis) A;
					if (temp.passiveTurns >= 0) {
						fighterLabels[4].setText(temp.passiveTurns+"");
						fighterLabels[4].setVisible(true);
						fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
						fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
						fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						fighterLabels[4].setText("");
				}
				
				if(B.name.equals("Louis")) {				
					Louis temp = (Louis) B;
					if (temp.passiveTurns >= 0) {
						fighterLabels[5].setText(temp.passiveTurns+"");
						fighterLabels[5].setVisible(true);
						fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
						fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
						fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						fighterLabels[5].setText("");
				}
				
				//Rits
				if(A.name.equals("Rits")) {				
					Rits temp = (Rits) A;
					if (temp.passive > 0) {
						fighterLabels[4].setText(temp.passive+"");
						fighterLabels[4].setVisible(true);
						fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
						fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
						fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						fighterLabels[4].setText("");
				}
				
				if(B.name.equals("Rits")) {				
					Rits temp = (Rits) B;
					if (temp.passive > 0) {
						fighterLabels[5].setText(temp.passive+"");
						fighterLabels[5].setVisible(true);
						fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
						fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
						fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
					}					
					else
						fighterLabels[5].setText("");
				}
		
		//Stacks:
				
				//Jester
				if(A.name.equals("Jester")) {
					Jester temp = (Jester) A;
					fighterLabels[4].setIcon(stackIcon);
					fighterLabels[4].setText(temp.normalAttackCounter+"");
					fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
				}
				
				if(B.name.equals("Jester")) {
					Jester temp = (Jester) B;
					fighterLabels[5].setIcon(stackIcon);
					fighterLabels[5].setText(temp.normalAttackCounter+"");
					fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
				}
				
				//Gash
				if(A.name.equals("Gash")) {
					Gash temp = (Gash) A;
					fighterLabels[4].setIcon(stackIcon);
					fighterLabels[4].setText(temp.poisons.size()+"");
					fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
				}
				
				if(B.name.equals("Gash")) {
					Gash temp = (Gash) B;
					fighterLabels[5].setIcon(stackIcon);
					fighterLabels[5].setText(temp.poisons.size()+"");
					fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
				}
				
				//Olea
				if(A.name.equals("Olea")) {
					Olea temp = (Olea) A;
					fighterLabels[4].setIcon(stackIcon);
					fighterLabels[4].setText(temp.superTurns+"");
					fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
				}
				
				if(B.name.equals("Olea")) {
					Olea temp = (Olea) B;
					fighterLabels[5].setIcon(stackIcon);
					fighterLabels[5].setText(temp.superTurns+"");
					fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
				}
				
				//Vollie
				if(A.name.equals("Vollie")) {
					Vollie temp = (Vollie) A;
					fighterLabels[4].setIcon(stackIcon);
					fighterLabels[4].setText(temp.runicStacks+"");
					fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
				}
				
				if(B.name.equals("Vollie")) {
					Vollie temp = (Vollie) B;
					fighterLabels[5].setIcon(stackIcon);
					fighterLabels[5].setText(temp.runicStacks+"");
					fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
				}
				
				//June
				if(A.name.equals("June")) {
					June temp = (June) A;
					fighterLabels[4].setIcon(stackIcon);
					fighterLabels[4].setText(temp.normalStack+"");
					fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
				}
				
				if(B.name.equals("June")) {
					June temp = (June) B;
					fighterLabels[5].setIcon(stackIcon);
					fighterLabels[5].setText(temp.normalStack+"");
					fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
				}
				
				//Pine
				if(A.name.equals("Pine")) {
					Pine temp = (Pine) A;
					fighterLabels[4].setIcon(stackIcon);
					fighterLabels[4].setText(temp.stacks+"");
					fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
				}
				
				if(B.name.equals("Pine")) {
					Pine temp = (Pine) B;
					fighterLabels[5].setIcon(stackIcon);
					fighterLabels[5].setText(temp.stacks+"");
					fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
				}
				
				//John
				if(A.name.equals("John")) {
					John temp = (John) A;
					fighterLabels[4].setIcon(stackIcon);
					fighterLabels[4].setText(temp.bulletCount+"");
					fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
				}
				
				if(B.name.equals("John")) {
					John temp = (John) B;
					fighterLabels[5].setIcon(stackIcon);
					fighterLabels[5].setText(temp.bulletCount+"");
					fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
				}
				
				//Aboa
				if(A.name.equals("Aboa")) {
					Aboa temp = (Aboa) A;
					fighterLabels[4].setIcon(stackIcon);
					fighterLabels[4].setText(temp.fairyCount+"");
					fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
				}
				
				if(B.name.equals("Aboa")) {
					Aboa temp = (Aboa) B;
					fighterLabels[5].setIcon(stackIcon);
					fighterLabels[5].setText(temp.fairyCount+"");
					fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
				}
				
				//Kasse
				if(A.name.equals("Kasse")) {
					Kasse temp = (Kasse) A;
					fighterLabels[4].setIcon(stackIcon);
					fighterLabels[4].setText(temp.superCount+"");
					fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
				}
				
				if(B.name.equals("Kasse")) {
					Kasse temp = (Kasse) B;
					fighterLabels[5].setIcon(stackIcon);
					fighterLabels[5].setText(temp.superCount+"");
					fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
				}
				
				//Giran
				if(A.name.equals("Giran")) {
					Giran temp = (Giran) A;
					fighterLabels[4].setIcon(stackIcon);
					fighterLabels[4].setText(temp.superstack+"");
					fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
				}
				
				if(B.name.equals("Giran")) {
					Giran temp = (Giran) B;
					fighterLabels[5].setIcon(stackIcon);
					fighterLabels[5].setText(temp.superstack+"");
					fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
				}
				
				//Betty
				if(A.name.equals("Betty")) {
					Betty temp = (Betty) A;
					fighterLabels[4].setIcon(stackIcon);
					fighterLabels[4].setText(temp.cloneCount+"");
					fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
				}
				
				if(B.name.equals("Betty")) {
					Betty temp = (Betty) B;
					fighterLabels[5].setIcon(stackIcon);
					fighterLabels[5].setText(temp.cloneCount+"");
					fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
				}
				
				//Jack
				if(A.name.equals("Jack")) {
					Jack temp = (Jack) A;
					fighterLabels[4].setIcon(stackIcon);
					fighterLabels[4].setText(temp.passive+"");
					fighterLabels[4].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[4].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[4].setVerticalTextPosition(JLabel.CENTER);
				}
				
				if(B.name.equals("Jack")) {
					Jack temp = (Jack) B;
					fighterLabels[5].setIcon(stackIcon);
					fighterLabels[5].setText(temp.passive+"");
					fighterLabels[5].setFont(new Font("Times New Roman", Font.BOLD, 20));
					fighterLabels[5].setHorizontalTextPosition(JLabel.CENTER);
					fighterLabels[5].setVerticalTextPosition(JLabel.CENTER);
				}
				
		//Name Updates
				
		//Clyde Cannon name updates
				if(A.name.equals("Clyde")) {
					Clyde temp = (Clyde) A;
					if(temp.isCannon != true) {
						fighterLabels[4].setIcon(temp.clyde_solo);
						abilityNames[0].setText("Fireworking"); 
						abilityNames[1].setText("Hop On Back!"); 
						abilityNames[2].setText(A.build.gadgetChoise == "FIRST" ? "Headbutt!" : "Fully Charged"); 		
					}
					else {
						fighterLabels[4].setIcon(temp.clyde_cannon);
						abilityNames[0].setText("Cannon Attack!"); 
						abilityNames[1].setText("Wooohooo!"); 
						abilityNames[2].setText(A.build.gadgetChoise == "FIRST" ? "Shield Conversion" : "Steady Charge"); 
					}
				}
				
				if(B.name.equals("Clyde")) {
					Clyde temp = (Clyde) B;
					if(temp.isCannon != true) {
						fighterLabels[5].setIcon(temp.clyde_solo);
						abilityNames[3].setText("Fireworking"); 
						abilityNames[4].setText("Hop On Back!"); 
						abilityNames[5].setText(B.build.gadgetChoise == "FIRST" ? "Headbutt!" : "Fully Charged"); 		
					}
					else {
						fighterLabels[5].setIcon(temp.clyde_cannon);
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
							fighterLabels[4].setIcon(temp.amber_gun1);
							abilityNames[0].setText("Rapidfire");
							abilityNames[1].setText("Fiercefire");
							break;
						case 2:
							fighterLabels[4].setIcon(temp.amber_gun2);
							abilityNames[0].setText("Clencher");
							abilityNames[1].setText("Double Squeeze");
							break;
						case 3:
							fighterLabels[4].setIcon(temp.amber_gun3);
							abilityNames[0].setText("Harsh Pierce");
							abilityNames[1].setText("Gashopener");
							break;
						case 4:
							fighterLabels[4].setIcon(temp.amber_gun4);
							abilityNames[0].setText("Soulfire");
							abilityNames[1].setText("Soulless Strike");
							break;
					}	
				}
				
				if(B.name.equals("Amber")) {
					Amber temp = (Amber) B;			
					switch (temp.currentWeapon) {
						case 1:
							fighterLabels[5].setIcon(temp.amber_gun1);
							abilityNames[3].setText("Rapidfire");
							abilityNames[4].setText("Fiercefire");
							break;
						case 2:
							fighterLabels[5].setIcon(temp.amber_gun2);
							abilityNames[3].setText("Clencher");
							abilityNames[4].setText("Double Squeeze");
							break;
						case 3:
							fighterLabels[5].setIcon(temp.amber_gun3);
							abilityNames[3].setText("Harsh Pierce");
							abilityNames[4].setText("Gashopener");
							break;
						case 4:
							fighterLabels[5].setIcon(temp.amber_gun4);
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
						fighterLabels[4].setIcon(temp.anton_calm);
						break;
					case 1:
						abilityNames[0].setText("Selfish Strike");
						abilityNames[1].setText("Heat of Heart");
						fighterLabels[4].setIcon(temp.anton_angry);
						break;	
					}	
				}
				
				if(B.name.equals("Anton")) {
					Anton temp = (Anton) B;
					
					switch(temp.passive) {
					case 0:
						abilityNames[3].setText("Selfless Attack");
						abilityNames[4].setText("Breeze of Heart");
						fighterLabels[5].setIcon(temp.anton_calm);
						break;
					case 1:
						abilityNames[3].setText("Selfish Strike");
						abilityNames[4].setText("Heat of Heart");
						fighterLabels[5].setIcon(temp.anton_angry);
						break;	
					}	
				}
				
				//Qirale Name Updates
				
				if(A.name.equals("Qirale")) {
					Qirale temp = (Qirale) A;
					
					switch(temp.passive) {
					case 0:
						fighterLabels[4].setIcon(temp.qirale_water);
						break;
					case 1:
						fighterLabels[4].setIcon(temp.qirale_fire);
						break;
					case 2:
						fighterLabels[4].setIcon(temp.qirale_earth);
						break;
					case 3:
						fighterLabels[4].setIcon(temp.qirale_air);
						break;
					}	
				}
				
				if(B.name.equals("Qirale")) {
					Qirale temp = (Qirale) B;
					
					switch(temp.passive) {
					case 0:
						fighterLabels[5].setIcon(temp.qirale_water);
						break;
					case 1:
						fighterLabels[5].setIcon(temp.qirale_fire);
						break;
					case 2:
						fighterLabels[5].setIcon(temp.qirale_earth);
						break;
					case 3:
						fighterLabels[5].setIcon(temp.qirale_air);
						break;
					}	
				}
		
		
	}
	
	public static void updateEnhancedNormal(Fighter A, Fighter B, JButton Attack1, JButton Attack2) {
		
		if(A.isNormalModified) {
			Attack1.setBackground(Color.white);
			Attack1.setForeground(Color.black);
		} else {
			Attack1.setForeground(Color.white);
			Attack1.setBackground(Color.darkGray);
		}
		
		if(B.isNormalModified) {
			Attack2.setBackground(Color.white);
			Attack2.setForeground(Color.black);
		} else {
			Attack2.setForeground(Color.white);
			Attack2.setBackground(Color.darkGray);
		}
		
	}
	
	public static void enableOrDisable(Fighter attacker, 
								       Fighter attacked, 
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

	public static void isOver(Fighter A, 
			  Fighter B, 
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
			SoundManager.outro.play();
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
			SoundManager.outro.play();
			if(A.spell.name == "Demonic Cuteness" || A.spell.name == "Hypercharge") {
				A.spell.effectTurn = 0;
			}
			if(B.spell.name == "Demonic Cuteness" || B.spell.name == "Hypercharge") {
				B.spell.effectTurn = 0;
			}
		}
	}
	
	public static void gadget(Fighter attacker, Fighter attacked) {
		
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
	
	public static void potion(Fighter user, Fighter enemy, String potion, JButton Potion1, JButton Super1) {
		
		
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
				user.changeCHARGE(20);
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
						SoundManager.weakSE.play();
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
				SoundManager.strongSE.play();
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
				SoundManager.guardian.play();
				user.shield += 50;
				user.changePOTCNT(-1);
				if(user.potionCount <= 0) {
					Potion1.setBackground(new Color(66, 66, 66));
					Potion1.setForeground(Color.white);
					Potion1.setEnabled(false);
					}
				break;
			case "Brown":
				SoundManager.demonic_burn.play();
				enemy.changeHP(-40);
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
				SoundManager.charged.play();
		
	}

	public static void spell(Fighter user, Fighter enemy, Spell spell) {
		
	    int enemyHPbeforeSpell = enemy.shield + enemy.HP;
	    Status enemyStatBeforeSpell = enemy.stat;
		 
		Spell.doSpell(user, enemy, spell);
			
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
	
	public static void cooldownCheck(Fighter A, JButton spell){
		if (A.spell.currentCooldown == 0)
			spell.setEnabled(true);	
	}

	
}
