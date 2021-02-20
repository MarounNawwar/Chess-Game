package Position;



public class LocationFactory {
	//I used to have File[] file,so I replaced it with Integer[] row because in class Factory
	
	  private static final Integer colNumb =8;
	  public static Location build(Location current, Integer columnOffset, Integer rowOffset) {
		  //I changed from >= to > because I have from 1 to 8 in my board not 0 to 7 
		  //and <1 not <0 because I start from 1 not 0
		  //Offset is the difference between 2 number example: 21,28 offset is 5
	    if (current.getColumn() + columnOffset > colNumb|| current.getColumn() + columnOffset < 1) {
	      return new Location(current.getRow()+rowOffset, current.getColumn());
	    }
	    return new Location(current.getRow()+rowOffset, current.getColumn() + columnOffset);
	    
	  }
	}
