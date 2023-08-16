package Position;

public class LocationFactory {

    private static final Integer colNumb = 7;
    private static final Integer rowNumb = 7;


    private LocationFactory() {}

    public static Location build(Location current, Integer columnOffset, Integer rowOffset) {

        int newCol = current.getColumn() + columnOffset;
        int newRow = current.getRow() + rowOffset;

        if (!between(newCol,0,colNumb) || !between(newRow,0,rowNumb))
            return null;
        else
            return new Location(newRow, newCol);
    }

    //Function that returns true if the number is like low <= x <= high
    private static boolean between(int x,int low,int high) {
        if(x >= low && x <= high)
            return true;
        return false;
    }

}