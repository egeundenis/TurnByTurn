package Brawlers;

import main.Build;
import main.SoundEffect;
import main.Spell;
import main.Status;

public class Raven extends Brawler {

    static SoundEffect crow_normal = new SoundEffect("res/audio/crow_normal.wav");
    static SoundEffect crow_super = new SoundEffect("res/audio/crow_super.wav");

    public Raven(Build build) {
        super(build);
        this.build = build;
        this.name = "Raven";
        this.HP = 450;
        this.AttackDamage = 15;
        this.SuperCharge = 0;
        this.HyperCharge = 0;
        this.SuperDamage = 40;
        this.regen = 3;
        this.gadgetCount = 1;
        this.potionCount = 1;
        this.shield = 0;
        this.stat = Status.Normal;
        this.title = "the Poisoner";
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
    	
    	double statper = per();
    	
        crow_normal.play();
        enemy.changeHP(-this.AttackDamage*statper);
        this.changeCHARGE(this.AttackDamage*statper);
        enemy.changeREGEN(-2);
    }

    @Override
	public
    void superAbility(Brawler enemy) {
        crow_super.play();
        try {Thread.sleep(300);} catch (InterruptedException e) {}
        
        for (int i = 0; i < (4 + (isHypercharged ? 1 : 0)); i++) {
        	 crow_normal.play();
        	 enemy.changeHP(-this.AttackDamage);
             enemy.changeREGEN(-2);
            try {Thread.sleep(130);} catch (InterruptedException e) {}
            
        }
        
    }
    
    @Override
	public
	void gadgetAbility(Brawler enemy) {
    	
		if (this.build.gadgetChoise == "FIRST") 
			enemy.changeREGEN(-8);	
		else
			enemy.changeREGEN( (int) (enemy.regen * 0.5) );	
			
    }

    public Brawler newInstance() {
        return new Raven(build);
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

	@Override
	public String getExplanation() {
		StringBuilder sb = new StringBuilder();
		 sb.append("This annoying crow named Raven throws poisioned dagger at his enemies. And when he's"
		 		+ " ready, he leaps up! AND! throws even more daggers!"); //here
		return sb.toString();
	}
}