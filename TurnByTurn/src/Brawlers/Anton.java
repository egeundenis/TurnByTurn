package Brawlers;

import javax.swing.ImageIcon;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Anton extends Brawler {

	public int passive = 0;
    // 0 is balanced, 1 is furious
    SoundEffect anton_attack_0 = new SoundEffect("res/audio/anton_attack_balanced.wav");
    SoundEffect anton_attack_1 = new SoundEffect("res/audio/anton_attack_angry.wav");
    SoundEffect anton_super_0 = new SoundEffect("res/audio/anton_super_balanced.wav");
    SoundEffect anton_super_1 = new SoundEffect("res/audio/anton_super_angry.wav");
    
    public ImageIcon anton_angry = new ImageIcon("res/images/anton_angry.png");
    public ImageIcon anton_calm = new ImageIcon("res/images/anton_calm.png");

    public Anton(Build build) {
        super(build);
        this.build = build;
        this.name = "Anton";
        this.HP = 450;
        this.AttackDamage = 20;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 75;
        this.regen = 3;
        this.gadgetCount = 10;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Balanced?";
        this.spell = new Spell(build.spellChoise);

        if (build.gearChoise == "HP GEAR")
            this.HP *= 1.1;
        if (build.gearChoise == "POTION GEAR")
            this.potionCount += 1;
        if (build.gearChoise == "GADGET GEAR")
            this.gadgetCount += 1;
        if("REGEN GEAR".equals(build.gearChoise))
        	this.regen += 3;
        
        if(this.build.gadgetChoise.equals("SECOND")) {
        	passive = 1;
        }
    }

    @Override
	public
    void attackAbility(Brawler enemy) {
    	
    	double statper = per();
    	
        if(passive == 0) {
        	anton_attack_0.play();
        	enemy.changeHP(-this.AttackDamage * statper);
        	this.changeCHARGE(this.AttackDamage * statper);
        	this.changeHP(this.AttackDamage * statper / 2);
        	
        }
        
        if(passive == 1) {
        	anton_attack_1.play();
        	enemy.changeHP(-(this.AttackDamage+20) * statper);
        	this.changeCHARGE((this.AttackDamage+20) * statper);
        }
        
    }

    @Override
	public
    void superAbility(Brawler enemy) {
    	
    	if(passive == 0) {
        	enemy.changeHP(-this.SuperDamage);
        	this.changeHP(this.SuperDamage);
        	anton_super_0.play();
        }
        
        if(passive == 1) {
        	enemy.changeHP(-(this.SuperDamage+25));
        	anton_super_1.play();
        }
        
    }
    
    @Override
	public
    void gadgetAbility(Brawler enemy) {
    	
		if (this.build.gadgetChoise == "FIRST") {
			
			passive = 1;
			this.build.gadgetChoise = "SECOND";
			this.changeSTATUS(Status.Enraged);
			
			} else {
			
				passive = 0;
				this.build.gadgetChoise = "FIRST";
				this.changeHP(30);
				
			} 
		
    }
    
    public Brawler newInstance() {
        return new Anton(build);
    }

    public void reset() {
        this.HP = this.newInstance().HP;
        this.regen = this.newInstance().regen;
        this.SuperCharge = this.newInstance().SuperCharge;
        this.gadgetCount = this.newInstance().gadgetCount;
        this.AttackDamage = this.newInstance().AttackDamage;
        this.SuperDamage = this.newInstance().SuperDamage;
        this.HyperCharge = this.newInstance().HyperCharge;
        this.stat = this.newInstance().stat;
        this.potionCount = this.newInstance().potionCount;
        passive = 0;
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
            this.HP += (int) (x * (this.build.gearChoise == "HEAL GEAR" ? 1.2 : 1));
            if (this.HP > newInstance().HP)
                this.HP = newInstance().HP;
        } else {
            if (this.stat == Status.Guarded)
                this.stat = Status.Normal;
            else
                this.HP += (int) (x * (isHypercharged ? 0.8 : 1));
        }
        
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
		 sb.append("Anton is a versitile and a reliable brawler. He can deal lots of damage or heal himself!"
		 		+ " All you have to do is to choose his mood! He's quite polar...");    
		return sb.toString();
	}
}