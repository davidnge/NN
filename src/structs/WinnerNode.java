/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structs;

/**
 *
 * @author ngecheekeen
 */
public class WinnerNode {
    
    int winnerMatrix [][];
    
    public WinnerNode(Lattice latticeInput)
    {
        winnerMatrix = new int [latticeInput.getSOMHEIGHT()][latticeInput.getSOMWIDTH()];
        initializeWinnerMatrix();
    }
    

   
    /**Initialize all cells the value 0 as default value, to paint first winner the 
     * color 000,000,000, and ascending value (000 000 001, 000 000 002) etc
     * for subsequent winners  
     * 
     * Fill in values in the 2D array fourMatrix
     * 
     * @param           none
     * @void            Initialize all values in winnerMatrix to 0
     */
    public void initializeWinnerMatrix ()
    {
        for (int i=0; i<winnerMatrix.length; i++)
        {
            for (int j=0; j<winnerMatrix[0].length; j++)
            {
                winnerMatrix[i][j] = 0;
            }
        }
    }
    
    /** 
     * Fill in values in the 2D array fourMatrix
     * 
     * @param           lattice
     * @void            Fill in values in the 2D array fourMatrix
     */
    public void registerWinner(int x, int y)
    {
        winnerMatrix[x][y] +=1;
    }
    
    /** 
     * Get value of winnerMatrix at coordinate (x,y)
     * 
     * @param           int x, int y
     * @return          winnerMatrix[x][y]            
     */
    public int getValue(int x, int y)
    {
        return winnerMatrix[x][y];
    }
    
    /** 
     * Get width of winnerMatrix
     * 
     * @param           none
     * @return          height of winnerMatrix          
     */
    public int getW()
    {
        return winnerMatrix.length;
    }
    
    /** 
     * Get height of winnerMatrix
     * 
     * @param           none
     * @return          height of winnerMatrix            
     */
    public int getH()
    {
        return winnerMatrix[0].length;
    }
}
