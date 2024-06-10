package main;

public class Spell {

	public int currentCooldown = 0;
	public int cooldown;
	public int effectTurn;
	public boolean doesSkip = true;
	public String name;

	public Spell(String name){
		this.name = name;
		
		switch (name) {
		case "Static Shock":
			cooldown = 25;
			effectTurn = -1;
			break;
		case "Redemption":
			cooldown = 15;
			effectTurn = -1;
			break;
		case "Heart of Steel":
			cooldown = 20;
			effectTurn = -1;
			doesSkip = false;
			break;
		case "Glacial Gale":
			cooldown = 20;
			effectTurn = -1;
			break;
		case "Electric Storm":
			cooldown = 15;
			effectTurn = -1;
			break;
		case "Guardian":
			cooldown = 20;
			effectTurn = -1;
			break;
		case "Prixie":
			cooldown = 10;
			effectTurn = -1;
			doesSkip = false;
			break;
		case "Last Strike":
			cooldown = 30;
			effectTurn = -1;
			break;
		case "Demonic Cuteness":
			cooldown = 20;
			effectTurn = 0;
			doesSkip = false;
			break;
		case "Mirror":
			cooldown = 35;
			effectTurn = -1;
			doesSkip = false;
			break;
		case "Meditation Medication":
			cooldown = 25;
			effectTurn = 0;
		}
		
	}

	
}
