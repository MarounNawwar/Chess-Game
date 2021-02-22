package Position;

public class LocationFactory {
	
	private static final Integer colNumb = 8;
	private static final Integer rowNumb = 8;
	
	public static Location build(Location current, Integer columnOffset, Integer rowOffset) {

		if (current.getColumn() + columnOffset >= colNumb	||	current.getColumn() + columnOffset < 0) {
		
			return new Location(current.getRow()+rowOffset, current.getColumn());
		
		}else if(current.getRow()+rowOffset >= rowNumb || current.getRow() + rowOffset < 0){
			
			return new Location(current.getRow(),current.getColumn()+columnOffset);
		
		}else{
		
			return new Location(current.getRow()+rowOffset, current.getColumn() + columnOffset);
		
		}
	
	}

}
