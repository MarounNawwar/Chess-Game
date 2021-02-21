package Position;

public class LocationFactory {
	
	private static final Integer colNumb =8;
	
	public static Location build(Location current, Integer columnOffset, Integer rowOffset) {

		if (current.getColumn() + columnOffset >= colNumb	||	current.getColumn() + columnOffset < 0) {
			
			return new Location(current.getRow()+rowOffset, current.getColumn());
		
		}else{
		
			return new Location(current.getRow()+rowOffset, current.getColumn() + columnOffset);
		
		}
	
	}

}
