package main;

import java.util.Random;

public class Build {
	
	public static String[][] abilityNames = {
			{"Hammah!", "Heavy Swing" , "Rich Flavoured", "Heavy Attack"}, 					//Todd
			{"Strong Legs", "Stunning Kick", "Meditate", "Double Kick"},  					//Susan 
			{"Big Claws", "A Kiss to the Neck", "Autocannibalism", "Nasty Bite"},			//Mark
			{"Rock Throw", "Guardian Angel", "Gift from Heavens", "When Heavens Fall"},		//Lisa
			{"Dipped Dagger", "Quadagger", "Nausea", "Double Trouble"},						//Crow
			{"Jingle Bells", "What Will It Be!?", "Pick a Card", "Charged Cards"},			//Jester
			{"Mugged", "Stolen Dreams", "Health Exchange", "Charge Trade"},					//Finn
			{"Piercing Bullet", "Halving Burns", "Scaly Slam", "Courage"},				    //Timmy
			{"The Beginning", "The End", "Fast Forward", "Slow Motion"},					//Kasse
			{"POW! or pew", "Love Salvo", "Bite The Bullet", "Spare Bullet"},				//John
			{"Health Nut", "Anti-inflammatory", "Vitamin Gummies", "Healthy Hit"},			//Missy
			{"Slimy", "Regroup!", "Sticky Investment", "Last Resort"},						//Zach
			{"Magical Projectile", "aand... Iceball.", "Restocked", "Fire Spell"},			//Simon
			{"Sword Swing", "Counter Strike", "En guarde!", "Taunt"},						//Felix	
			{"Destined Punch", "Adrenaline", "Blood Rush", "No Pain, No Gain"},				//Imelda
			{"Broken Glass", "Green Reflection", "Passage Granted", "Reflective Glass"}, 	//Betty
			{"Hard Punch", "Deadly Notebook", "Waste away", "A Run to Death"},				//Light
			{"Cruel Attack", "Grim Look", "Starvation", "Full-Course Meal"},				//Hassan
			{"Light Beams", "Peeped Beam", "Eyes Everywhere!", "Shocking"},					//Nanni
			{"Heartburn", "Flame within", "Flamed", "Burning Bridges"},						//Ignace
			{"My Balloon!", "Spooooky", "Phasmablast", "Tough Attack"},						//Gusty
			{"Ice Projectile", "The Storm", "Freezing!", "Self-Loathe"},					//Anvaa
			{"Bloody Claws", "Big Nibble", "Scarring Strike", "Smashed"},					//Vollie
			{"Annoying Poke", "Multiplying Fun!" , "Yummy Poultry", "Quick Attacks"},	    //Giran
			{"Cannon Attack!", "Wooohooo!", "Shield Conversion", "Charging Port"},			//Clyde
			{"Rapidfire", "Fiercefire", "Favorite Weapon!", "Skipped Symmetry"},			//Amber
			{"Selfless Attack", "Breeze of Heart", "Anger", "Calm"},						//Anton
			{"Elemental Strike", "Queen of All!", "Change of Plans", "A Blessing"},			//Qirale
			{"The Archer", "Vigilante Shit", "Delicate", "Midnight Rain"},					//Olea
			{"Quake", "Summon: Beurc", "Paw Quake", "Purring Heal"},		 				//Itan
			{"Grace Attack", "Won't Feel It!", "Self-Harm", "Eye For An Eye"}, 				//Louis
			{"Forced Bow", "Submit to Power", "Charge Swap", "Super Control"}, 				//Pine
			{"Clawlike", "Uncontrollable", "Unstable", "Anger"},							//Ritz
			{"Everlasting Scar", "Up and Down", "Once Again", "Everlasting"}, 				//Gash
			{"slap", "harsher defense", "voluntary", "yummy prick"}					 	//Jack
	};
	 
	public static String[] Potions = {
			"Red",
			"Yellow",
			"Dark Purple",
			"Dark Blue", 
			"Dark Red", 
			"Green",
			"Gray",
			"Dark Gray",
			"Brown",
			"Light Purple"
	};
	
	public static String[] PotionsName = {
			"Healing",
			"Charging",
			"Regenerating",
			"Weakening", 
			"Strengthening", 
			"Confusing",
			"Cleansing",
			"Shielding",
			"Harming",
			"Hypercharging"
	};
	
	public static String[] Gadgets = {
			"FIRST",
			"SECOND"
	};
	
	public static String[] Gears = {
			"HP GEAR",
			"POTION GEAR",
			"GADGET GEAR",
			"HEAL GEAR",
			"COOLDOWN GEAR",
			"REGEN GEAR",
			"HYPERCHARGE GEAR"
	};
	
	public static String[] Spells = {
			"Static Shock",
			"Redemption",
			"Heart of Steel",
			"Glacial Gale",
			"Electric Storm",
			"Guardian",
			"Prixie",
			"Last Strike",
			"Demonic Cuteness",
			"Mirror",
			"Meditation Medication"

	};
	
	
	public String gadgetChoise = "FIRST";
	public String potionChoise = "Red";
	public String gearChoise = "POTION GEAR";
	public String spellChoise = "Static Shock";

	public Build(String gadgetChoise, String potionChoise, String gearChoise, String spellChoise) {
		this.gadgetChoise = gadgetChoise;
		this.potionChoise = potionChoise;
		this.gearChoise = gearChoise;
		this.spellChoise = spellChoise;
 	}
	
	public Build() {}
	
	public Build(Random ran) {
		
		this.gadgetChoise = Gadgets[ran.nextInt(0, Gadgets.length)];
		this.potionChoise = Potions[ran.nextInt(0, Potions.length)];
		this.gearChoise = Gears[ran.nextInt(0, Gears.length)];
		this.spellChoise = Spells[ran.nextInt(0, Spells.length)];
		
	}

}
