package Brawlers;

import javax.swing.ImageIcon;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class John extends Brawler {

    static SoundEffect john_normal = new SoundEffect("res/audio/john_attack.wav");
    static SoundEffect john_reload = new SoundEffect("res/audio/john_reload.wav");
    static SoundEffect john_strong = new SoundEffect("res/audio/john_strong.wav");
    static SoundEffect john_super = new SoundEffect("res/audio/john_super.wav");  
    public ImageIcon john_specific = new ImageIcon("res/images/john_specific.png");
    
    
    public int bulletCount = 5;

    public John(Build build) {
        super(build);
        this.build = build;
        this.name = "John";
        this.HP = 375;
        this.AttackDamage = 40;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 60;
        this.regen = 0;
        this.gadgetCount = 2;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Glasscannon";
        this.spell = new Spell(build.spellChoise);
        this.hak = 1;

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
    	
    	if(this.bulletCount > 0) {
    		john_strong.play();
    		this.bulletCount--;
            enemy.changeHP(-this.AttackDamage*statper);
            this.changeCHARGE(this.AttackDamage*statper);
    	} else {
    		john_normal.play();
            enemy.changeHP(-this.AttackDamage/4*statper);
            this.changeCHARGE(this.AttackDamage/4*statper);
    	}
         
    }
    
    private void reload() {
    	
    	john_reload.play();
    	bulletCount = 5;
    	try{Thread.sleep(1500);}catch(Exception e) {}
    }
    
    @Override
    public boolean brawlerSpecificActivity(Brawler enemy) {
    	reload();	
    	return true;
   }

    @Override
	public void superAbility(Brawler enemy) {

    	for(int i = 0; i < this.bulletCount + (this.isHypercharged ? 1 : 0); i++) {
    		john_super.play();
    		enemy.changeHP(-this.SuperDamage);
    		try{Thread.sleep(300);}catch(Exception e) {}
    	}
    	
    	this.bulletCount = 0;
    	
    }
    
	@Override
	public
	void gadgetAbility(Brawler enemy) {

		if (this.build.gadgetChoise == "FIRST") {
			
			if(bulletCount > 0) {
				bulletCount--;
				this.changeSHIELD(50);
			}
				
		}
		else {
			bulletCount++;
		}
		
	}

    public Brawler newInstance() {
        return new John(build);
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

    public void changeSHIELD(int x) {
        this.shield += x;
        if (shield > 400)
            shield = 400;
    }
    
    @Override
   	public String getExplanation() {
   		StringBuilder sb = new StringBuilder();
   		sb.append("How elegant. This skinny male may look like an easy bite. But he will blow "
   				+ "your mind using his big shotgun and if he can't; his little gun come in handy!... WAIT THAT CAME OUT WRONG"); //here
   		return sb.toString();
   	}


}