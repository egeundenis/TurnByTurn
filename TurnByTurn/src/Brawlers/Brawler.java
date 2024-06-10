package Brawlers;

import main.Build;
import main.SoundManager;
import main.Spell;
import main.Status;
import main.StatusManager;

public abstract class Brawler {
	
	public Build defaultBuild = new Build();
	public static Brawler[] brawlers = {
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
			new Zach(new Build()),
			new Simon(new Build()),
			new Felix(new Build()),
			new Imelda(new Build()),
			new Betty(new Build()),
			new Light(new Build()),
			new Hassan(new Build()),
			new Nanni(new Build()),
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
			new Louis(new Build())
	};

	public String name;
    public int HP;
    public int AttackDamage;
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
    
    public String briefExpl;
    
    public int HyperCharge;
    public int hyperchargeTurn = -1;
    public boolean isHypercharged = false;
    
    public int hak = -1;
    
    public String gamemode;
    
    public Brawler(Build build) {}

    public double per() {
        StatusManager stma = new StatusManager();
        return stma.damagePercentage(this);
    }

    public abstract void attackAbility(Brawler enemy);

    public abstract void superAbility(Brawler enemy);

    public abstract void gadgetAbility(Brawler enemy);
    
    public int brawlerSpecificActivity(Brawler enemy) {return 999;}

    public abstract Brawler newInstance();

    public abstract void reset();
 
    //changeHP Different Brawlers: Anvaa Zach Imelda Vollie Itan Louis
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
            this.HP += (int) (x * (this.build.gearChoise.equals("HEAL GEAR") ? 1.2 : 1));
            if (this.HP > newInstance().HP)
                this.HP = newInstance().HP;
        } else {
            if (this.stat == Status.Guarded)
                this.stat = Status.Normal;
            else
                this.HP += (int) (x * (isHypercharged ? 0.8 : 1));
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

    public abstract void changeATK(int x);

    public abstract void changeSUPATK(int x);

    public void changeCHARGE(double x) {

        if (this.SuperCharge - x < 100 && this.SuperCharge + x > 99)
            new SoundManager().charged.play();

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
        	this.hyperchargeTurn = 15;
        	this.HyperCharge = 0;
        }
        
    	}
        
    }

    public void passiveHyperCharge() {

        switch (this.name) {

            case "Kasse":
                changeHYPERCHARGE(8);
                break;
            case "Crow":
                changeHYPERCHARGE(5);
                break;
            case "John":
                changeHYPERCHARGE(5);
                break;
            default:
                changeHYPERCHARGE(2);
                break;

        }
        
        if(this.build.gearChoise.equals("HYPERCHARGE GEAR"))
        	changeHYPERCHARGE(1);

    }

    public abstract void changeREGEN(int x);

    public abstract void changeGADCNT(int x);

    public abstract void changePOTCNT(int x);
    
    //Different change status brawlers: Simon Betty Vollie Imelda Nanni  
    public void changeSTATUS(Status x) {
        this.stat = x;
    }

    public abstract void changeSHIELD(int x);
    
    abstract public String getExplanation();
    
    public static Brawler GenerateBrawler(String str) {
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
    	case "Nanni": return new Nanni(new Build());
    	case "Simon": return new Simon(new Build());
    	case "Susan": return new Susan(new Build());
    	case "Timmy": return new Timmy(new Build());
    	case "Todd": return new Todd(new Build());
    	case "Vollie": return new Vollie(new Build());
    	case "Zach": return new Zach(new Build());
    	case "Qirale": return new Qirale(new Build());
    	case "Olea": return new Olea(new Build());
    	case "Itan": return new Itan(new Build());
    	case "Louis": return new Louis(new Build());
    	
    	default: return new Todd(new Build());
    	
    	}
    }
    
    public static void brawlerCall(String braw) {

		switch(braw) {
    	case "lisa": SoundManager.lisa_call.play(); break;
    	case "mark": SoundManager.mark_call.play(); break;
    	case "susan": SoundManager.susan_call.play(); break;
    	case "todd": SoundManager.todd_call.play(); break;

    	
    	default: break;
		}
	}



}