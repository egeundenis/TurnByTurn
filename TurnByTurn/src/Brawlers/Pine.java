package Brawlers;

import java.util.Random;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Pine extends Brawler {

    SoundEffect pine_attack = new SoundEffect("res/audio/pine_attack.wav");
    SoundEffect pine_super = new SoundEffect("res/audio/pine_super.wav");
    SoundEffect pine_super2 = new SoundEffect("res/audio/pine_super2.wav");
    SoundEffect pine_super3 = new SoundEffect("res/audio/pine_super3.wav");
    SoundEffect pine_super4 = new SoundEffect("res/audio/pine_super4.wav");
    public int stacks = 3;
    boolean isSuper = false;

    public Pine(Build build) {
        super(build);
        this.build = build;
        this.name = "Pine";
        this.HP = 500;
        this.AttackDamage = 30;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 0;
        this.regen = 5;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Controller";
        this.spell = new Spell(build.spellChoise);

        if (build.gearChoise.equals("HP GEAR"))
            this.HP *= 1.1;
        if (build.gearChoise.equals("POTION GEAR"))
            this.potionCount += 1;
        if (build.gearChoise.equals("GADGET GEAR"))
            this.gadgetCount += 1;
        if("REGEN GEAR".equals(build.gearChoise))
        	this.regen += 3;
    }

    @Override
	public
    void attackAbility(Brawler enemy) {
    	
    	pine_attack.play();
    	
    	double statper = per();
        enemy.changeHP(-this.AttackDamage*statper);
        this.changeCHARGE(this.AttackDamage*statper);
        this.changeHP(10);
       
    }

    @Override
	public
    void superAbility(Brawler enemy) {
    	
    	switch(new Random().nextInt(0,4)) {
	    	case 0:
	    		pine_super.play();
	    		break;
	    	case 1:
	    		pine_super4.play();
	    		break;
	    	case 2:
	    		pine_super2.play();
	    		break;
	    	case 3:
	    		pine_super3.play();
	    		break;
    	}
    	try {Thread.sleep(800);} catch (InterruptedException e) {}
    	
    	int startingCharge = enemy.SuperCharge;
    	int startingHyper = enemy.HyperCharge;
    	boolean ishyper = enemy.isHypercharged;
    	
    	
    	for(int i = 0; i < this.stacks + (this.isHypercharged ? 1 : 0); i++) {
    		enemy.attackAbility(enemy);
    		enemy.SuperCharge = startingCharge;
    		enemy.HyperCharge = startingHyper;
    		enemy.isHypercharged = ishyper;
    		if(!ishyper) enemy.hyperchargeTurn = -1;
    		this.changeHP(10);
        	try {Thread.sleep( Math.max(650 - (this.stacks*40), 0)  );} catch (InterruptedException e) {}
    	}
    	
    	if(isSuper) {
    		if(enemy.SuperCharge > 99) {
    			enemy.superAbility(enemy);
    			enemy.changeCHARGE(-100);
    		}
    		isSuper = !isSuper;
    	}
    	
    	
    	this.stacks++;
    		    	
    }
    
    @Override
	public
	void gadgetAbility(Brawler enemy) {
		
		if (this.build.gadgetChoise == "FIRST") {
			int temp = this.SuperCharge;
			this.SuperCharge = enemy.SuperCharge;
			enemy.SuperCharge = temp;
		}
		else {
			isSuper = true;
		}
		
	}

    public Brawler newInstance() {
        return new Pine(build);
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
        stacks = 3;
        isSuper = false;
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

    public void changeSHIELD(int x) {
        this.shield += x;
        if (shield > 400)
            shield = 400;
    }

	@Override
	public String getExplanation() {
		StringBuilder sb = new StringBuilder();
		 sb.append("Pine is an annoying little brat that loves to pull all the strings in everyone's life! He decides when and whom his idiot"
		 		+ " enemy attack! Get on his good side will ya?"); //here
		return sb.toString();
	}
	
}