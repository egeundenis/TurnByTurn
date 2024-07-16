 package Brawlers;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Betty extends Brawler {
	
	SoundEffect betty_attack = new SoundEffect("res/audio/betty_attack.wav");
	SoundEffect betty_passive = new SoundEffect("res/audio/betty_passive.wav");
	SoundEffect betty_super = new SoundEffect("res/audio/betty_super.wav");
	public int cloneCount = 1;

    public Betty(Build build) {
        super(build);
        this.build = build;
        this.name = "Betty";
        this.HP = 600;
        this.AttackDamage = 25;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 0;
        this.regen = 5;
        this.gadgetCount = 3;
        this.potionCount = 2;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Magical Mime";
        this.spell = new Spell(build.spellChoise);

        if (build.gearChoise == "HP GEAR")
            this.HP *= 1.1;
        if (build.gearChoise == "POTION GEAR")
            this.potionCount += 1;
        if (build.gearChoise == "GADGET GEAR")
            this.gadgetCount += 1;
        if("REGEN GEAR".equals(build.gearChoise))
        	this.regen += 3;
    }

    @Override
	public
    void attackAbility(Brawler enemy) {
    	
    	double statper = per(); 
    	
        betty_attack.play();
        enemy.changeHP(-this.AttackDamage * statper );
        this.changeCHARGE(this.AttackDamage * statper );
    }

    @Override
	public
    void superAbility(Brawler enemy) {
        betty_super.play();
        try {Thread.sleep(1000);} catch (InterruptedException e) {}
        if(enemy.name == "Betty") {
        	betty_super.play();
        	return;
        }
         
        for(int i = 0; i < this.cloneCount; i++) {   	
        	Brawler temp = enemy.newInstance();
        	temp.enemy = enemy;
            temp.isHypercharged = this.isHypercharged;
            temp.changeCHARGE(100);
            temp.superAbility(enemy);           
            try {Thread.sleep(700);} catch (InterruptedException e) {}
            enemy.changeHP(-this.AttackDamage);
            betty_passive.play();
            try {Thread.sleep(500);} catch (InterruptedException e) {}
            
        }
        
        this.cloneCount++;

    }
    
    @Override
	public
	void gadgetAbility(Brawler enemy) {
		
		if (this.build.gadgetChoise == "FIRST") {
			int prev = enemy.SuperCharge;
			enemy.attackAbility(enemy);
			int now = enemy.SuperCharge;
			enemy.changeCHARGE(-(now-prev));
		}
		else {
			switch(enemy.build.potionChoise) {
			case "Red":
				this.changeHP(60);
				break;
			case "Yellow":
				this.changeCHARGE(20);
				break;
			case "Dark Purple":
				this.changeREGEN(5);
				break;
			case "Dark Blue":
				enemy.changeSTATUS(Status.Weakened);
				break;
			case "Dark Red":
				this.changeSTATUS(Status.Strengthened);
				break;
			case "Green":
				enemy.changeSTATUS(Status.Confused);
				break;
			case "Gray":
				this.changeSTATUS(Status.Normal);
				this.changeHP(30);
				break;
			case "Dark Gray":
				this.changeSHIELD(60);
				break;
			case "Brown":
				enemy.changeHP(-50);
				break;
			case "Light Purple":
				this.changeHYPERCHARGE(+50);
				break;
			}
		}
		  	
	}

    public Brawler newInstance() {
        return new Betty(build);
    }

    public void reset() {
        this.HP = this.newInstance().HP;
        this.regen = this.newInstance().regen;
        this.SuperCharge = this.newInstance().SuperCharge;
        this.HyperCharge = this.newInstance().HyperCharge;
        this.gadgetCount = this.newInstance().gadgetCount;
        this.AttackDamage = this.newInstance().AttackDamage;
        this.SuperDamage = this.newInstance().SuperDamage;
        this.stat = this.newInstance().stat;
        this.potionCount = this.newInstance().potionCount;
    }

    public void changeATK(int x) {
        this.AttackDamage += x;
    }

    public void changeSUPATK(int x) {
        this.SuperDamage += x;
    }

    public void changeREGEN(int x) {
        this.regen += x;
    }

    public void changeGADCNT(int x) {
        this.gadgetCount += x;
    }

    public void changePOTCNT(int x) {
        this.potionCount += x;
    }

    public void changeSTATUS(Status x) {
        Status stat = Status.Normal;
        if (!stat.isNegative(x))
            this.stat = x;
    }

    public void changeSHIELD(int x) {
        this.shield += x;
        if (shield > 400)
            shield = 400;
    }

	@Override
	public String getExplanation() {
		StringBuilder sb = new StringBuilder();
		 sb.append("Betty is an unoriginal brawler... She just throws magic sand from her pockets and "
		 		+ "mimic her enemy's super! She should've became a mime instead!"); //here
		return sb.toString();
	}

	
}