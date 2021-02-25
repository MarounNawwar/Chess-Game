package Position;

import java.util.Objects;

public class Location {
	
	private final Integer row;
	private final Integer column;
	
	//This is the constructor
	public Location(Integer row,Integer column) {
		this.row = row;
		this.column = column;
	}

	//HashCode and equal will be used to compare 2 locations with each others
	//Each Object has a unique HashCode,used to facilitate the comparison
	@Override
	public int hashCode() {
		return Objects.hash(row, column);
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return Objects.equals(column, other.column) && row == other.row;
	}

	
	//Getters
	public Integer getRow() {
		return row;
	}

	public Integer getColumn() {
		return column;
	}

	@Override
	public String toString() {
		return "Location [row=" + row + ", column=" + column + "]";
	}
	

}