package main;

public enum Trait {
	Normal,
	Magical,
	Tank;
	
	public String toString() {

		switch (this) {
		
		case Normal:
			return "NORMAL";
		case Magical:
			return "MAGICAL";
		case Tank:
			return "TANK";
			}
		
	return "NORMAL";
	}
	
}
