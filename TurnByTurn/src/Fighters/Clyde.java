package Fighters;

import javax.swing.ImageIcon;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Clyde extends Fighter {

	public boolean isCannon = true;
	SoundEffect clyde_attack1 = new SoundEffect("res/audio/clyde_attack1.wav");
	SoundEffect clyde_attack2 = new SoundEffect("res/audio/clyde_attack2.wav");
	SoundEffect clyde_super1 = new SoundEffect("res/audio/clyde_super1.wav");
	SoundEffect clyde_super2 = new SoundEffect("res/audio/clyde_super2.wav");
	
	public ImageIcon clyde_cannon = new ImageIcon("res/images/clyde_cannon.png");
	public ImageIcon clyde_solo = new ImageIcon("res/images/clyde_solo.png");

    public Clyde(Build build) {
        super(build);
        this.build = build;
        this.name = "Clyde";
        this.HP = 400;
        this.AttackDamage = 20;
        this.AttackCharge = 20;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 50;
        this.regen = 5;
        this.gadgetCount = 1;
        this.potionCount = 1;
        this.shield = 150;
        this.stat = Status.Normal;
        this.title = "the Stunter";
        this.spell = new Spell(build.spellChoise);

        if ("HP GEAR".equals(build.gearChoise))
            this.HP *= 1.1;
        if ("POTION GEAR".equals(build.gearChoise))
            this.potionCount += 1;
        if ("GADGET GEAR".equals(build.gearChoise))
            this.gadgetCount += 1;
        if("REGEN GEAR".equals(build.gearChoise))
        	this.regen += 3;
    }

    @Override
	public
    void attackAbility(Fighter enemy) {
    	
    	double statper = per();
    	
    	if(isCannon) {		
    		//Cannon Form
    		clyde_attack1.play();
    		enemy.changeHP(-AttackDamage*statper);
    		this.changeCHARGE(this.AttackCharge);
    	}
    	else {
    		//Solo Form
    		clyde_attack2.play();
    		enemy.changeHP(-AttackDamage*statper*1.5);
            this.changeCHARGE(this.AttackCharge*1.5);
    	}
    	
    }

    @Override
	public
    void superAbility(Fighter enemy) {
        
    	if(isCannon) {
    		//Cannon Form
    		clyde_super1.play();
    		enemy.changeHP(-SuperDamage);
    		this.changeSHIELD(-this.shield);
    		enemy.changeSTATUS(Status.Stunned);
    		isCannon = false;
    		this.changeSTATUS(Status.Enraged);
    	}
    	else {
    		//Solo Form
    		clyde_super2.play();
    		this.changeSHIELD(150);
    		isCannon = true;
    		this.changeSTATUS(Status.Enraged);
    	}
    		
    	
    }
    
    @Override
	public
	void gadgetAbility(Fighter enemy) {

			if (this.build.gadgetChoise == "FIRST") {
				//first gadget
				if(isCannon) {
					this.changeHP(this.shield/2);
					this.changeSHIELD(-this.shield);
				}
				else {
					enemy.changeSTATUS(Status.Stunned);
				}
				
			}
			else //2nd gadget
				if(isCannon) {
					this.changeCHARGE(50);
				}
				else {
					this.changeCHARGE(100);
				}
		
	}

    public Fighter newInstance() {
        return new Clyde(build);
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
        isCannon = true;
        this.shield = 150;
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
		 sb.append("Bonnie and Clyde are goin' for a ride! Clyde is quite strong even if she's on her"
		 		+ " trusty cannon or when she shoots herself down on the enemies!"); //here
		return sb.toString();
	}

}
