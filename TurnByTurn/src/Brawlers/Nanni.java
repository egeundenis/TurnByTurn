package Brawlers;

import java.util.Random;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Nanni extends Brawler {

    static SoundEffect nanni_normal = new SoundEffect("res/audio/nanni_normal.wav");
    static SoundEffect nanni_super = new SoundEffect("res/audio/nanni_super.wav");

    public Nanni(Build build) {
        super(build);
        this.build = build;
        this.name = "Nanni";
        this.HP = 550;
        this.AttackDamage = 20;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 100;
        this.regen = 0;
        this.gadgetCount = 1;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Angry";
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
    void attackAbility(Brawler enemy) {
    	
    	double statper = per();
    	
        nanni_normal.play();
        Random ran = new Random();
        int ret = ran.nextInt(1, 4);
        int damage = this.AttackDamage * ret;
        enemy.changeHP(-damage*statper);
        this.changeCHARGE(damage*statper);
    }

    @Override
	public
    void superAbility(Brawler enemy) {
        nanni_super.play();
        enemy.changeHP(-this.SuperDamage);
    }
    
    @Override
	public
	void gadgetAbility(Brawler enemy) {
		
		if (this.build.gadgetChoise == "FIRST") {
			this.changeSTATUS(Status.Enraged);
			enemy.changeSTATUS(Status.Weakened);
		}else 
			enemy.changeSTATUS(Status.Stunned);
		
	}

    public Brawler newInstance() {
        return new Nanni(build);
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
        if (x == Status.Stunned)
            changeSHIELD(75);
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
   		sb.append("SECURITY ALERT! Are you breaking in her house! Oh, you're getting cooked and exploded "
   				+ "in your face!"); //here
   		return sb.toString();
   	}
	
}