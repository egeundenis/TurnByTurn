package Fighters;

import main.Build;
import main.SoundManager;
import main.Spell;
import main.Status;
import main.StatusManager;
import main.Trait;

public abstract class Fighter {
	
	public static boolean isCallAllowed = false;
	
	public Build defaultBuild = new Build();
	public static Fighter[] fighters = {
			new Todd(new Build()),
			new Susan(new Build()),
			new Mark(new Build()),		
			new Lisa(new Build()),		
			new Raven(new Build()),    
			new Jester(new Build()), 
			new Finn(new Build()),        
			new Timmy(new Build()),    
			new Kasse(new Build()),   
			new John(new Build()),
			new Missy(new Build()),
			new Zachy(new Build()),
			new Simon(new Build()),
			new Felix(new Build()),
			new Imelda(new Build()),
			new Betty(new Build()),
			new Light(new Build()),
			new Hassan(new Build()),
			new Nanny(new Build()),
			new Ignace(new Build()),
			new Gusty(new Build()),
			new Anvaa(new Build()),
			new Vollie(new Build()),
			new Giran(new Build()),
			new Clyde(new Build()),
			new Amber(new Build()),
			new Anton(new Build()),
			new Qirale(new Build()),
			new Olea(new Build()),
			new Itan(new Build()),
			new Louis(new Build()),
			new Pine(new Build()),
			new Rits(new Build()),
			new Gash(new Build()),
			new Jack(new Build()),
			new June(new Build()),
			new Aboa(new Build())
	};

	public String name;
    public int HP;
    public int AttackDamage;
    public int AttackCharge;
    public int SuperCharge;
    public int SuperDamage;
    public int regen;
    public int gadgetCount;
    public int potionCount;
    public int shield;
    public String title;
    public Status stat;
    public  Build build;
    public Spell spell;  
    public Fighter enemy;
    public boolean isNormalModified = false;   
    public String briefExpl;  
    public int HyperCharge;
    public int hyperchargeTurn = -1;
    public boolean isHypercharged = false;    
    public int passiveHypercharge = 2;
    public int hak = -1;   
    public String gamemode;
    public Trait trait = Trait.Normal;
    public double tankTraitRatio;
    public boolean canThisFighterChooseTarget = false;
    
    public Fighter(Build build) {}

    public double per() {
        StatusManager stma = new StatusManager();
        return stma.damagePercentage(this);
    }

    public abstract void attackAbility(Fighter enemy);

    public abstract void superAbility(Fighter enemy);

    public abstract void gadgetAbility(Fighter enemy);
    
    public boolean fighterSpecificActivity(Fighter enemy) {return false;}

    public abstract Fighter newInstance();

    public abstract void reset();
    
    /* MAYBE
    
    public void dealDamage(Fighter enemy, int amount) {
    	
    }
    
    public void takeDamage(Fighter inflicter, int amount) {
    	
    }
    
    public void heal(int amount) {
    	
    }
    
    */
    
    //changeHP Different Fighters: Anvaa Zachy Imelda Vollie Itan Louis Rits Jack Todd
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
            this.HP += (int) (x * (this.build.gearChoise.equals("HEAL GEAR") ? 1.2 : 1) * (this.stat == Status.Poisoned ? 0.5 : 1));
            if (this.HP > newInstance().HP)
                this.HP = newInstance().HP;
        } else {
            if (this.stat == Status.Guarded)
                this.stat = Status.Normal;
            else {
            	this.HP += (int) (x * (isHypercharged ? 0.8 : 1));
            	if(this.trait == Trait.Tank)
            		this.changeCHARGE(x*tankTraitRatio);
            }
                
        }
    }   

    public void regenerate() {
        this.HP += this.regen;
        int maxhp = this.newInstance().HP;
        if (this.regen > 0)
            changeHYPERCHARGE(this.regen / 4);
        if (this.HP > maxhp)
            this.HP = maxhp;
    }

    public void changeATK(int x) {
        this.AttackDamage += x;
    }

    public void changeSUPATK(int x) {
        this.SuperDamage += x;
    }

    
    //different for Louis, Rits
    public void changeCHARGE(double x) {

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

    public void changeHYPERCHARGE(double x) {
    	
    	if(this.hyperchargeTurn > -1) {
    		return;	
    	}else {
    		
    		this.HyperCharge += x;
        if (this.HyperCharge > 250)
            this.HyperCharge = 250;
        if (this.HyperCharge < 0)
            this.HyperCharge = 0;
        
        if(this.HyperCharge == 250) {
        	SoundManager.hypercharge.play();
        	this.hyperchargeTurn = 10;
        	this.HyperCharge = 0;
        }
        
    	}
        
    }

    public void passiveHyperCharge() {

        changeHYPERCHARGE(this.passiveHypercharge);
        if(this.build.gearChoise.equals("HYPERCHARGE GEAR"))
        	changeHYPERCHARGE(1);

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
    
    //Different change status fighters: Vollie Imelda Nanny Rits
    public void changeSTATUS(Status x) {
    	
        if (Status.isNegative(x) && this.trait == Trait.Magical)
        	return;
        else
        	this.stat = x;
        
    }

    public void changeSHIELD(int x) {
        this.shield += x;
        if (shield > 400)
            shield = 400;
    }
    
    abstract public String getExplanation();
    
    public void eachTurnChecks(Fighter enemy) {
    	//nothin
    }
    
    public static Fighter GenerateFighter(String str) {
    	switch(str) {
    	case "Amber": return new Amber(new Build());
    	case "Anton": return new Anton(new Build());
    	case "Betty": return new Betty(new Build());
    	case "Clyde": return new Clyde(new Build());
    	case "Raven": return new Raven(new Build());
    	case "Felix": return new Felix(new Build());
    	case "Finn": return new Finn(new Build());
    	case "Giran": return new Giran(new Build());
    	case "Gusty": return new Gusty(new Build());
    	case "Hassan": return new Hassan(new Build());
    	case "Ignace": return new Ignace(new Build());
    	case "Imelda": return new Imelda(new Build());
    	case "Jester": return new Jester(new Build());
    	case "John": return new John(new Build());
    	case "Kasse": return new Kasse(new Build());
    	case "Light": return new Light(new Build());
    	case "Lisa": return new Lisa(new Build());
    	case "Mark": return new Mark(new Build());
    	case "Missy": return new Missy(new Build());
    	case "Nanny": return new Nanny(new Build());
    	case "Simon": return new Simon(new Build());
    	case "Susan": return new Susan(new Build());
    	case "Timmy": return new Timmy(new Build());
    	case "Todd": return new Todd(new Build());
    	case "Vollie": return new Vollie(new Build());
    	case "Zachy": return new Zachy(new Build());
    	case "Qirale": return new Qirale(new Build());
    	case "Olea": return new Olea(new Build());
    	case "Itan": return new Itan(new Build());
    	case "Louis": return new Louis(new Build());
    	case "Pine": return new Pine(new Build());
    	case "Rits": return new Rits(new Build());
    	case "Gash": return new Gash(new Build());
    	case "Jack": return new Jack(new Build());
    	case "June": return new June(new Build());
    	case "Aboa": return new Aboa(new Build());
    	
    	default: return new Todd(new Build());
    	
    	}
    }
    
    public static void fighterCall(String braw) {

    	if(isCallAllowed)    		
		switch(braw) {
		
    	case "lisa": SoundManager.lisa_call.play(); break;
    	case "mark": SoundManager.mark_call.play(); break;
    	case "susan": SoundManager.susan_call.play(); break;
    	case "todd": SoundManager.todd_call.play(); break;

    	
    	default: break;
		}
	}



}