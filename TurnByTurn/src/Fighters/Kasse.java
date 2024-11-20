package Fighters;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Kasse extends Fighter {

    static SoundEffect kasse_normal = new SoundEffect("res/audio/kasse_attack.wav");
    static SoundEffect kasse_super = new SoundEffect("res/audio/kasse_super.wav");
    static SoundEffect kasse_ready = new SoundEffect("res/audio/kasse_ready.wav");
    
    public int superCount = 0;

    public Kasse(Build build) {
        super(build);
        this.build = build;
        this.name = "Kasse";
        this.HP = 500;
        this.AttackDamage = 25;
        this.AttackCharge = 15;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 99999;
        this.regen = 5;
        this.passiveHypercharge = 6;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Ender";
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
    	
        kasse_normal.play();
        int missing = this.newInstance().HP - this.HP;
        int dmg = missing / 100;
        dmg += this.AttackDamage;
        enemy.changeHP(-dmg*statper);
        this.changeCHARGE(this.AttackCharge + (missing / 100) );
        
    }

    @Override
	public
    void superAbility(Fighter enemy) {
    	
    	if(this.superCount == 4) {
    		kasse_super.play();
    		this.superCount++;
    		enemy.HP = -this.SuperDamage;
    	} else {
    		
    		if(this.isHypercharged){
    			this.changeCHARGE(25);
    		}
    		
    		this.superCount++;
    		for(int i = 0; i < this.superCount; i++) {
    			kasse_ready.play();
    			try {Thread.sleep(250);} catch (InterruptedException e) {}
    		}
    			
    	}
    	
        
    }
    
    @Override
	public
	void gadgetAbility(Fighter enemy) {
		if (this.build.gadgetChoise == "FIRST") {
			this.changeCHARGE(10);
			enemy.changeSTATUS(Status.Weakened);
		}
		else {
			this.changeCHARGE(15);
			this.changeSTATUS(Status.Weakened);
		}
    }

    public Fighter newInstance() {
        return new Kasse(build);
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
        this.superCount = 0;
    }
    
    @Override
   	public String getExplanation() {
   		StringBuilder sb = new StringBuilder();
   		sb.append("Kasse is here..? The end is here. No need to fight now. It will be slow. But he"
   				+ " is inevitible. Unless you're fast enough."); //here
   		return sb.toString();
   	}
}