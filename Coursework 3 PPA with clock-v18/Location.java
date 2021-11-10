/**
 * Represent a location in a rectangular grid.
 * 
 * @author David J. Barnes and Michael Kölling, Lubna Taraki and Kelvin Eforhare 
 * @version 2021.03.01
 */
public class Location
{
    // Row and column positions.
    private int row;
    private int col; 
    
    // Variable to hold size of water 
    private static int waterArea;
    // Tag to show if water or not
    private boolean isWaterArea;
    
    /**
     * Represent a row and column. Locations that fall within water size 
     * have isWaterArea set as true
     * @param row The row.
     * @param col The column.
     */
    public Location(int row, int col)
    {
        this.row = row;
        this.col = col;  
        
        if(col >= waterArea){ 
            isWaterArea = true;
        }
    }  
    
    /**
     * Allows the water size to be inputted outside class
     */
    public static void setWaterArea(int waterAreaSize) 
    { 
        waterArea = waterAreaSize;
    }
    
    /**
     * @return true if location is part of water area
     */
    public boolean checkIsWaterArea() 
    { 
        return isWaterArea;
    }
    
    /**
     * Implement content equality.
     */
    public boolean equals(Object obj)
    {
        if(obj instanceof Location) {
            Location other = (Location) obj;
            return row == other.getRow() && col == other.getCol();
        }
        else {
            return false;
        }
    }
    
    /**
     * Return a string of the form row,column
     * @return A string representation of the location.
     */
    public String toString()
    {
        return row + "," + col;
    }
    
    /**
     * Use the top 16 bits for the row value and the bottom for
     * the column. Except for very big grids, this should give a
     * unique hash code for each (row, col) pair.
     * @return A hashcode for the location.
     */
    public int hashCode()
    {
        return (row << 16) + col;
    }
    
    /**
     * @return The row.
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * @return The column.
     */
    public int getCol()
    {
        return col;
    }
}
