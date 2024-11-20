package Fighters;

import java.util.Random;

import main.Build;
import main.SoundEffect;
import main.SoundManager;
import main.Spell;
import main.Status;

public class Louis extends Fighter {

    SoundEffect louis_normal = new SoundEffect("res/audio/louis_normal.wav");
    SoundEffect louis_super = new SoundEffect("res/audio/louis_super.wav");
    public SoundEffect louis_superburst = new SoundEffect("res/audio/louis_superburst.wav");
    
    public int passiveTurns = -1;
    public int decreasedHP = 0;

    public Louis(Build build) {
        super(build);
        this.build = build;
        this.name = "Louis";
        this.HP = 700;
        this.AttackDamage = 15;
        this.AttackCharge = 15;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 0;
        this.regen = 5;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Reflector";
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

    Random ran = new Random();
    
    public void eachTurnChecks(Fighter enemy) {
    	
    	if(this.passiveTurns == 0) {					
			enemy.changeHP(this.decreasedHP);
			this.changeHP(-this.decreasedHP/2);
			this.decreasedHP = 0;
			this.louis_superburst.play();
			try {Thread.sleep(300);}catch(Exception e){}
		}			
		if(this.passiveTurns >= 0)
			this.passiveTurns--;	
    }

    @Override
	public
    void attackAbility(Fighter enemy) {
    	
    	double statper = per();
    	louis_normal.play();
        enemy.changeHP(-this.AttackDamage*statper);
        this.changeCHARGE(this.AttackCharge);
        
        if(new Random().nextInt(0,10) == 4) {
        	try { Thread.sleep(500);} catch (Exception e) {}
        	louis_normal.play();
        	this.changeHP(-this.AttackDamage*statper);
        	this.changeCHARGE(this.AttackCharge);
        }
        
        if(this.passiveTurns > 0)
	        if(new Random().nextInt(0,3) == 1) {
	        	try { Thread.sleep(500);} catch (Exception e) {}
	        	louis_normal.play();
	        	this.changeHP(-this.AttackDamage*statper);
	        	this.changeCHARGE(this.AttackCharge);
	        }
        
    }

    @Override
	public
    void superAbility(Fighter enemy) {
        
        passiveTurns = 10;
        louis_super.play();

    }
    
    @Override
	public
	void gadgetAbility(Fighter enemy) {

		if (this.build.gadgetChoise == "FIRST") {
			this.changeHP(-50);
			this.changeSTATUS(Status.Enraged);
		} else {
			this.changeHP(-30);
			enemy.changeHP(-30);
		}
		
	}

    public Fighter newInstance() {
        return new Louis(build);
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
        this.shield += 0;
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
    
    public void changeHP(double x) {

        if (x < 0 && shield != 0) {
            shield += x;
            x = 0;
        }

        if (shield < 0) {
            x = shield;
            shield = 0;
        }

        if (x > 0) {
            this.HP += (int) (x * (this.build.gearChoise.equals("HEAL GEAR") ? 1.2 : 1)* (this.stat == Status.Poisoned ? 0.5 : 1));
            if (this.HP > newInstance().HP)
                this.HP = newInstance().HP;
        } else {
            if (this.stat == Status.Guarded)
                this.stat = Status.Normal;
            else {
            	if(this.passiveTurns > 0)
            		decreasedHP += (int) (x * (isHypercharged ? 0.8 : 1));
            	else
            		this.HP += (int) (x * (isHypercharged ? 0.8 : 1));
            	 
            }
               
        }
    }
    
    public void changeCHARGE(double x) {
    	
    	if(this.passiveTurns > 0 && x > 0) {
    		decreasedHP += x;
    		return;
    	}

    	if (this.SuperCharge - x < 100 && this.SuperCharge + x > 99 && this.SuperCharge < 100)
            SoundManager.charged.play();

        this.SuperCharge += x;
        if (this.SuperCharge > 200)
            this.SuperCharge = 200;
        if (this.SuperCharge < 0)
            this.SuperCharge = 0;

        if (x > 0)
            changeHYPERCHARGE(x / 3);

    }

    public void changeGADCNT(int x) {
        this.gadgetCount += x;
    }

    public void changePOTCNT(int x) {
        this.potionCount += x;
    }

    public void changeSHIELD(int x) {
        this.shield += x;
        if (shield > 400) shield = 400;
    }

    @Override
   	public String getExplanation() {
   		StringBuilder sb = new StringBuilder();
   		sb.append("Louis is a futuristic and electrical fighter that relies too much on his electricity! But he can always rely on "
   				+ "it to protect him from the dangers!"); //here
   		return sb.toString();
   	}
	
}