package main;

public enum Status {
	
	Normal,
	Stunned,
	Weakened,
	Strengthened,
	Confused,
	Guarded,
	Enraged,
	Frosty,
	Scarred,
	Intoxicated,
	Poisoned;
	
	public static boolean isNegative(Status x) {
		if(x == Weakened || x == Confused || x == Scarred || x == Frosty)
			return true;
		else
			return false;
	}
	
	public static boolean isPositive(Status x) {
		if(x == Strengthened || x == Enraged || x == Guarded)
			return true;
		else
			return false;
	}
	
	public static boolean isHard(Status x) {
		if(x == Stunned || x == Guarded || x == Intoxicated || x == Poisoned)
			return true;
		else
			return false;
	}
	
	
}


