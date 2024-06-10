package main;

import Brawlers.Brawler;

public class StatusManager {

	public StatusManager() {}
	
	public double damagePercentage(Brawler brawler) {
		
		double ret = 0;
		
		switch(brawler.stat) {
		case Weakened:
			brawler.changeSTATUS(Status.Normal);
			ret = 0.50;
			break;
		case Confused:
			ret = 1.0;
			break;
		case Enraged:
			brawler.changeSTATUS(Status.Normal);
			ret = 1.5;
			break;
		case Frosty:
			ret = 1.0;
			break;
		case Guarded:
			ret = 1.0;
			break;
		case Normal:
			ret = 1.0;
			break;
		case Scarred:
			ret = 1.0;
			break;
		case Strengthened:
			brawler.changeSTATUS(Status.Normal);
			ret = 2;
			break;
		case Stunned:
			ret = 1.0;
			break;
		case Intoxicated:
			ret = 1.0;
			break;
		
		default:
			ret = 1.0;
			break;
		}
		
		if(brawler.isHypercharged) {
			ret += 0.2;
		}
		
		return ret;
		
	}

	
	
}
