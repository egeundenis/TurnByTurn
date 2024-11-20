package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Fighters.Fighter;
import Fighters.Todd;
import main.Build;
import main.Trait;

public class HelpScreen {

	public HelpScreen() {}
	
	public static void helpScreen(Fighter[] bs, String[] bsName) {
		
		ArrayList<String> konusList = new ArrayList<String>();
		for(String konu : bsName)
			konusList.add(konu);
		konusList.add("Potions");
		konusList.add("Statuses");
		konusList.add("Gears");
		konusList.add("Spells");
		konusList.add("Abilities");
		konusList.add("Hypercharge");
		konusList.add("Traits");
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
				String POISONED = new String("<font color=\"green\">POISONED</font>");
				
				Fighter fighter = new Todd(new Build(new Random()));
				String fighterST = (String) combo.getSelectedItem();
				for (Fighter i : bs)
					if (i.name == fighterST)
						fighter = i.newInstance();
				
				int atk = fighter.AttackDamage;
				int sup = fighter.SuperDamage;
				
				String starterString = fighter.name.toUpperCase(Locale.US) + " " + fighter.title.toUpperCase(Locale.US)+
						"<br/>HP: " + fighter.HP 
						+ "<br/>REGENERATION: " + fighter.regen 
						+ "<br>ATTACK CHARGE: %" + fighter.AttackCharge 
						+ (fighter.trait != Trait.Normal ? ("<br>TRAIT: " + fighter.trait.toString() + (fighter.trait == Trait.Tank ? " (" + fighter.tankTraitRatio + ")" : "") ) : "")
						+ "<br>PASSIVE HYPERCHARGE GAIN: " + fighter.passiveHypercharge;
				
				switch(fighterST) {
				case "Todd":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: Todd swings his sword to the ground and deals " + atk + " damage"
							+ "<br/><br/>SUPER: Todd raises his shield and takes/deals/charges %50 less damage for 5 turns, then he charges forth to deal " + sup + " damage and make the enemy " + STUNNED
							+ " (" + HYPERCHARGE + ") Todd's super last a little longer; doesn't reduce his damage and further decreases the damage he takes"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Todd drinks some regional beer to get some Regeneration"
							+ "<br/>Second Gadget: Todd remembers his fallen comrades and increases his ATK and becomes " + ENRAGED
							+ "<br/><br/>PASSIVE (ALWAYS READY): Todd starts the match with some shield"
							);
							break;
				case "Zachy":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: Zachy throws a piece of his body at the enemy! It hurts him as much as the enemy though."
							+ "<br/><br/>SUPER: Zachy regenerates his missing pieces."
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Zachy sets a piece of him aside for harsh days, He increases his REGEN but decreases his HP"
							+ "<br/>Second Gadget: In a last ditch attempt he collects his scattered body from around and heals in proportion to his missing HP"
							+ "<br/><br/>PASSIVE (STRANGE MATTER): Zachy has a small chance to absorb any type of damage he's taking and heal from it"
							);
							break; 
				case "Raven":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: Raven throws a knife dipped in toxic chemicals. It deals " + atk + " damage and decreases the enemy's Regeneration by 2"
							+ "<br/><br/>SUPER: Raven flies to the sky and hastily throws 4 stronger knives. It also makes the enemy " + POISONED + " (" + HYPERCHARGE + ") Throws an additional knife."
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Enemy's regeneration decreases by a flat amount"
							+ "<br/>Second Gadget: Enemy's regeneration decreases by a percentage"
							+ "<br/><br/>PASSIVE (TOXIC MOTIVATION): Raven's super charge charges in opposition to the enemy's Regeneration"
							);
							break; 
				case "Simon":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: Simon makes a random spell that deals damage between 15 and 35. For each 5 damage interval, there is a catch:"
							+ " (15-20): He gets regeneration in proportion to the damage, (21-25): Simon gets double super charge from this attack, "
							+ " (26-30): The enemy's super charge is decreased in proportion to the damage, (31-35): Simon heals the damage he deals"
							+ "<br/><br/>SUPER: Simon throws a ice spell that " + STUNS + "the enemy and deals " + sup + " damage"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Simon restockes his potion stash and gets 1 more potion count"
							+ "<br/>Second Gadget: Simon throws a fire spell that deals damage and decreases the REGEN of the enemy"
							+ "<br/><br/>PASSIVE (MAGICAL FROST): Simon's attacks have a %20 chance to make the enemy " + FROSTY
							);
					break;
				case "Susan":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: Susan kicks the enemy and deals " + atk + " damage"
							+ "<br/><br/>SUPER: Susan uses all her force to blow a kick that " + STUNS + " and deals " + sup + " damage ( " + HYPERCHARGE + " ) Susan kicks twice."
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Susan meditates and rests her legs. She heals a little"
							+ "<br/>Second Gadget: Susan blows 2 kicks back to back and deals some damage"
							+ "<br/><br/>PASSIVE (BELLOW THE WAIST): Susan deals more damage to the enemy if they have a negative status effect"
							);
							break; 
				case "Mark":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
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
							+ starterString
							+ "<br/><br/>ATTACK: Lisa brings the fire of justice from herself and deals " + atk + " damage"
							+ "<br/><br/>SUPER: In a fit of perseverence, Lisa almost doubles her ATK and REGEN. (" + HYPERCHARGE+") Lisa doubles her ATK and REGEN"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Lisa prays for a help from the heavens and gets super charge"
							+ "<br/>Second Gadget: Lisa makes a deal with the gods, with a portion of her HP gone, she gets some super charge"
							+ "<br/><br/>PASSIVE (FALLEN ANGEL): If Lisa's super is charged, She reduces any damage coming to her"
							);
							break; 	
				case "Jester":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
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
							+ starterString
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
							+ starterString
							+ "<br/><br/>ATTACK: Timmy fires his gun, dealing " + atk + " damage plus a portion of the enemy's current HP" 
							+ "<br/><br/>SUPER: Timmy's trusty Dragonic pet fires an enchanting flame, dealing " + sup +  " damage and instantly deleting "
							+ "a portion of the enemy's HP (" + HYPERCHARGE+ ") Timmy's pet attacks once again after the super"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Timmy's pet latches on the enemy and " + STUNS + " them"
							+ "<br/>Second Gadget: Timmy heals in proportion to the enemy's missing HP"
							+ "<br/><br/>PASSIVE (DRAGON MASTER): If Timmy has less then %20 of his HP, his pet starts attacking with him, dealing extra guaranteed damage"
							);
							break; 	
				case "Kasse":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: Kasse sends a cosmic butterfly to an enemy. It deals " + atk + " damage"
							+ "<br/><br/>SUPER: Kasse gets ready. After getting ready for 5 times, he executes the enemy in instant. ( " + HYPERCHARGE + " ) Kasse charges 25 super charge"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Kasse gets 10 super charge and makes the enemy " + WEAKENED 
							+ "<br/>Second Gadget: Kasse gets 15 super charge and makes himself " + WEAKENED
							+ "<br/><br/>PASSIVE (COSMIC CALL): For each 100 HP missing from him, Kasse's attacks damage and charge 1 more"
							);
							break; 
				case "John":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: John's shotgun has 5 bullets that deal " + atk + " damage! If he runs out of bullets he uses his"
									+ " small gun to deal 10 damage."
							+ "<br/><br/>SUPER: John starts raining all remaining bullets in his shotgun to the enemy! They deal more damage than normal"
							+ " ( "  + HYPERCHARGE +" ) John fires one more bullet."
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: John takes out a bullet from his gun and bites it to get some shield!"
							+ "<br/>Second Gadget: John reloads 1 bullet to his shotgun"
							+ "<br/><br/>PASSIVE (LAST BREATH): When John dies, he uses his normal attack one last time..."
							+ "<br><br>SPECIFIC: John reloads his shotgun and gets 5 bullets"
							);
							break;
				case "Light":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
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
							+ starterString
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
							+ starterString
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
							+ starterString
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
							+ starterString
							+ "<br/><br/>ATTACK: Betty throws some glitter(?) to the enemy and deals " + atk + " damage"
							+ "<br/><br/>SUPER: Betty creates a clone that mimics her enemy and uses their super ability! All by herself! With each use,"
							+ " Betty creates one more clone! They turn into dust that deal " + atk + " damage before they vanish! (" + HYPERCHARGE + ") The clones are hypercharged too!"
							+ "<br/><br/>GADGETS: <br/>First Gadget: Betty forces the enemy to attack theirselves! "
							+ "<br/>Second Gadget: Betty creates a stronger copy of the enemy's potion and uses it!"
							+ "<br/><br/>PASSIVE (MAGICAL ANNOYANCE): Betty's attacks have a %20 chance to make the enemy CONFUSED"
							);
							break;
				case "Nanny":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: Nanny charges her pulsaters and shoots them. Each has a 1/3 chance to attack and deals " + atk + " damage! But at least one always hits."
							+ "<br/><br/>SUPER: Nanny charges her laser and deals " + sup + " damage! (" + HYPERCHARGE + ") The laser deals %50 more damage"
							+ "<br/><br/>GADGETS: <br/>First Gadget: Nanny gets " + ENRAGED + "! Out of fear, the enemy becomes " + WEAKENED +  ". "
							+ "<br/>Second Gadget: Nanny overcharges and " + STUNS + " the enemy."
							+ "<br/><br/>PASSIVE (ENERGY CONVERSION): If Nanny gets " + STUNNED + ", she gets some shield."
							);
				 break;
				case "Hassan":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
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
							+ starterString
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
							+ starterString
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
							+ starterString
							+ "<br/><br/>ATTACK: Anvaa throws an ice piece at the enemies that deals " + atk + " damage. "
							+ "If the enemy is " + FROSTY + " it deals more damage and heals herself."
							+ "<br/><br/>SUPER: Anvaa creates a snow storm and deals " + sup + " damage to the enemy and "
							+ "changes their status to " + FROSTY + " If the enemy is already " + FROSTY + " deals more damage."
							+ "<br/><br/>GADGETS: <br/>First Gadget: If the enemy is " + FROSTY + " she " + STUNS + " them and deals damage. <br/>Second Gadget: "
							+ "Anvaa becomes " + FROSTY + " and heals some HP"
							+ "<br/><br/>PASSIVE (IMMORTALITY): If Anvaa dies, She gets reborned, she gets " + STUNNED + " but also gets some " + SHIELD
							+ " She can get reborn only once per main"
							);
					break;
				case "Vollie":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
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
							+ starterString
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
							+ starterString
							+ "<br/><br/>ATTACK: (CANNON) Clyde fires her cannon and deals " + atk + " damage. (SOLO) Clyde fires 3 fireworks to the enemy dealing " + (atk*1.5) + " damage"
							+ "<br/><br/>SUPER: (CANNON) Clyde shoots herself from her cannon, dealing " + sup + " damage and " + STUNS +" the enemy. She loses her cannon protection and becomes SOLO!"
								+ " (SOLO) Clyde hops back on her cannon, getting her sheld back."
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: (CANNON) Clyde converts her cannon's shield to HP. (SOLO) Clyde heatbutts the enemy with her helmet and " + STUNS + " them."
							+ "<br/>Second Gadget: (CANNON) Clyde charges some super charge. (SOLO) Clyde fully charges her super."
							+ "<br/><br/>PASSIVE (RECKLESS STUNTS): Hopping in and out of the Cannon makes Clyde " + ENRAGED
							);
							break;
				
				case "Amber":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: Amber has many weapons on her arsenal that she uses in a cycle 1-) Amber shoots her pistol one at a time, it has an ever decreasing chance to shoot 1 more weaker bullet "
							+ "2-) Amber throws a weakening net that stuns the enemy if already weakened. 3-) Amber brings out her shotgun to deal a massive damage. If the enemy is low on health, she shoots again for good measure! "
							+ "4-) Stolen from Finn, this weapon steals the enemy's HP and gives it to Amber!"
							+ "<br/><br/>SUPER: Amber overcharges her current weapon and uses them in a stronger manner!"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Amber uses the weapon on her hand but doesn't switch it."
							+ "<br/>Second Gadget: Amber changes her weapon to the next in cycle and uses it."
							+ "<br/><br/>PASSIVE (FAST HANDS): Each time Amber switches weapons she gets a little shield"
							);
							break; 
							
				case "Anton":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: Anton deals some damage to an enemy with a magical attack."
							+ "<br/><br/>SUPER: Anton's staff sends a big wave of magic."
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Anton heals or deals damage based on his form."
							+ "<br/>Second Gadget: Anton gets 2 more uses for his specific ability"
							+ "<br/><br/>PASSIVE (BALANCED): Anton has 2 forms: Balanced and Furious! Balanced form deals less damage but heals him more while Furious form deals a lot more damage!"
							+ "<br><br>SPECIFIC: Anton changes his form. Going from balanced to furious makes Anton " + ENRAGED + ". While going from Furious to Balanced heals him. (10 uses per match)"
							);
							break;
							
				case "Qirale":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: Qirale throws a chunk of whatever element she has been using. Air can recursively attack, Water makes the enemy "+WEAKENED+", "
									+ "Earth gets twice the super charge and Fire decreases the enemy's regen"
							+ "<br/><br/>SUPER: Qirale focuses and sends a big shockwave of the element she was holding. Water heals her, Fire burns the enemy, Earth gives her some shield, "
							+ "Air makes the enemy confused!"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Qirale takes on the next element"
							+ "<br/>Second Gadget: Qirale uses the element she's holding to help her in this fight!"
							+ "<br/><br/>PASSIVE (GRANDMAGE): Qirale can have 4 elemental modes to choose from! From less damage to most; Air, Water, Earth and Fire! Each"
							+ " with its own unique normal and super attacks!"
							+ "<br><br>SPECIFIC: Qirale takes on the next element. (10 uses per match)"
							);
							break;
				case "Olea":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: Olea shoots an arrow from his FABOLOUS bow and deals " + atk + " damage. If he is " + INTOXICATED + " he deals more "
							+ "damage and decreases the enemy's regeneration"
							+ "<br/><br/>SUPER: Olea makes himself and the enemy " + INTOXICATED + " for some turns! (" + HYPERCHARGE + ") 1 more turn is added to the super!"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Olea heals himself. If he is " +INTOXICATED+ " he heals more."
							+ "<br/>Second Gadget: Olea makes himself and the enemy " + INTOXICATED+ " for a few turns"
							+ "<br/><br/>PASSIVE (DRAMA QUEEN!): Olea is immune from taking damage from being "+INTOXICATED+""
							);
							break;
				case "Itan":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
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
							+ starterString
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
				case "Pine":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: Pine uses his nasty whip to deal " + atk + " damage"
							+ "<br/><br/>SUPER: Pine forces the enemy to attack themselves 3 times! Each use increases the attack count! After that,"
								+ " Pine brings their super and hypercharge back to its original state. (" + HYPERCHARGE + ") The enemy uses their attack one more"
									+ " time"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Pine trades his super charge with the enemy"
							+ "<br/>Second Gadget: Pine's next super also forces the enemy to use their super on themselves if they have enough charges!"
							+ "<br/><br/>PASSIVE (SADIST): Every time Pine sees the enemy hurting, he heals by 10, excluding spells and regeneration."
							);
							break; 	
				case "Rits":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: Rits throws his punches that hit like bear claws! It deals " + atk + " damage."
							+ "<br/><br/>SUPER: Rits becomes uncontrollable for some turns! He's immune to weak negatives effects, deals twice as much damage"
							+ " and gets hurt half! (" + HYPERCHARGE + ") The super lasts %50 more turns!"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Rits adds 3 more turns to his super"
							+ "<br/>Second Gadget: Rits becomes " + ENRAGED
							+ "<br/><br/>PASSIVE (PAPA BEAR): Rits have a chance to become " + ENRAGED + " each normal attack"
							);
							break; 		
				case "Gash":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: Gash poisons the enemy with some darts... It deals " + atk + " damage every turn for 10 turns, it can stack!"
							+ "<br/><br/>SUPER: Gash heals himself and deals damage to the enemy for " + sup + ". (" + HYPERCHARGE + ") Gash makes himself " + STRENGTHENED + " and the enemy " + WEAKENED
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: Gash increases existing poisons' duration by 3 turns"
							+ "<br/>Second Gadget: Gash's next attack sticks around for 20 turns"
							+ "<br/><br/>PASSIVE (POISON BOMB): When Gash dies, all poison darts explode and deal 20 damage to the enemy"
							);
							break;
				case "Jack":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: jack slaps the enemy and deals " + atk + " damage"
							+ "<br/><br/>SUPER: jack sharpenes his stingers. " + HYPERCHARGE + ": jack throws 2 stingers"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: jack throws a stinger"
							+ "<br/>Second Gadget: jack eats and weakenes his stingers to heal"
							+ "<br/><br/>PASSIVE (defense): when jack gets hurt he releases a stinger"
							);
							break;
				case "June":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: June swings her electrical bat to deal " + atk + " damage"
							+ "<br/><br/>SUPER: June rewinds time for her and enemy's HP for a couple of turns. Some amount they healed are then dealt as damage to the enemy! (" + HYPERCHARGE +") "
									+ " Deals twice as damage!"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: June's next swing deals 3 times more damage!"
							+ "<br/>Second Gadget: June rewinds time to return to the HP she had a turn ago!"
							+ "<br/><br/>PASSIVE (MOMENTUM): Every 3rd swing from her attacks deals extra damage"
							);
						break;
				
				case "Aboa":
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
							+ "<br/><br/>ATTACK: Aboa slashes his sword to deal " + atk + " damage. If he have a fairy, the fairy enhances his sword with its life to deal triple damage!"
							+ "<br/><br/>SUPER: All the fairies around Aboa heals him by " + sup + " ( " + HYPERCHARGE + " ) Heals are increased by %50!"
							+ "<br/><br/>GADGETS: "
							+ "<br/>First Gadget: A fairy sacrifices itself to charge Aboa's super a little"
							+ "<br/>Second Gadget: A fairy sacrifices itself to give Aboa some shield"
							+ "<br/><br/>PASSIVE (SACRIFICE): When Aboa dies, all the fairies around him sacrifice their life to heal him a little."
							+ "<br><br>SPECIFIC: Aboa clings his sword to summon some fairies. Each summoned fairy heals him a little"
							);
							break; 		
					
					/*
					description.setText("<html><p style=\"width:600px\">"
							+ starterString
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
					description.setText("<html>" + fighter.title.toUpperCase() +
							 			"<br/>HP: " + fighter.HP +
										"<br/>Attack DMG: " + fighter.AttackDamage +
										"<br/>Regeneration: " + fighter.regen +
										"<br/>Super DMG: " + fighter.SuperDamage + "<html>");
					break;
				case "Potions":
					description.setText("<html><p style=\"width:600px\">"
									  + "Potions are given to each fighter at the start of the match. They are not fighter specific so each potion is the same for every fighter."
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
							+ "Status effects indicate what the fighter can/will do."
							+ "<br/>Normal: Normal, nothing will happen."
							+ "<br/>Stunned: The fighter can't do anything, until the enemy breakes the "
							+ "<br/>stun by hitting them. (H-)" 
							+ "<br/>Weakened: The fighter will deal less damage (S-)" 
							+ "<br/>Strengthened: The fighter will deal more damage (S+)" 
							+ "<br/>Confused: The fighter will deal less damage but to theirself (S-)"
							+ "<br/>Guarded: The fighter won't get effected by anything from the enemy "
							+ "<br/>for a round. (H+)"
							+ "<br/>Enraged: The fighter will deal %100 more damage (S+)"
							+ "<br/>Frosty: The fighter can not regenerate (if the regen is positive) (S-)"
							+ "<br/>Scarred: The fighter loses %1 of their HP each turn (S-)"
							+ "<br/>Intoxicated: The fighter loses %5 of its maximum HP each turn (H-)"
							+ "<br>Poisoned: The fighter will heal only %50 efficiently! (H-)");
					break;
				case "Gears":
					description.setText("<html><p style=\"width:600px\">Gears are buffs available for every fighter! You just have to choose one:"
							+ "<br/>Health Gear: Increases the Fighter's HP"
							+ "<br/>Potion Gear: Increases the Fighter's potion count by one"
							+ "<br/>Gadget Gear: Increases the Fighter's gadget count by one"
							+ "<br/>Heal Gear: Increases the Fighter's all incoming heals"
							+ "<br/>Cooldown Gear: Decreases spell cooldown"
							+ "<br>Regen Gear: Increases Regeneration"
							+ "<br>Hypercharge Gear: Increases passive hypercharging");
					break;
				case "Spells":
					description.setText("<html><p style=\"width:600px\">Spells are abilities available for every fighter! You just have to choose: "
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
							+ "<br>Laser Rain: Deals a little damage, but after each use, it fires again!"
							);
					break;
				case "Abilities":
					description.setText("<html><p style=\"width:600px\">"
							+ "There are 3 main abilities each fighter has that's unique to them and their playstyle!"
							+ "<br/>Normal Attacks: Normally, weak attacks that fighter can use freely that costs nothing. It skips the turn to the enemy, "
							+ "or breaks any stuns they might have. It also charges the super the same as the damage it deals."
							+ "<br/><br/>Super Ability: Strong abilities that can be only used if the fighter has more than 100 super charge. It also consumes all the charge."
							+ "<br/><br/>Gadgets: Small abilities that can be used without the turn going back to the enemy. Varying fighters have varying amout of charges."
							);
					break;
				case "Hypercharge":
					description.setText("<html><p style=\"width:600px\">"
							+ "Charging your super ability, regenerating, using your super, or just existing in the first place, charges your " + HYPERCHARGE + "! If you have enough hypercharge you automatically "
							+ "activate it to hypercharge your fighter for 10 turns! A hypercharged fighter takes less damage, deals more damage and their super ability becomes much more powerful! Instead of changing the status, "
							+ "it is its own thing!"
							);
					break;
				case "Traits":
					description.setText("<html><p style=\"width:600px\">"
							+ "Traits are passive abilities that help the fighter in their own unique way, only limited number of fighters have them!"
							+ "<br>NORMAL: Normal trait is nothing!"
							+ "<br>MAGICAL: The fighter can not get soft negative status effects like " + WEAKENED
							+ "<br>TANK: The fighter will charge their super corelating with the damage they take"
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
		description.setFont(new Font("Calibri", Font.PLAIN, 23));
		
		descriptionPanel.add(description);
		con.add(descriptionPanel);
		
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
				
	}
	
}
