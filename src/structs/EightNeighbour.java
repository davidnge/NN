/*
 * This class generates the Matrix for 8-Neighbours
 */
package structs;

import org.apache.commons.math3.linear.ArrayRealVector;

/**
 *
 * @author ngecheekeen
 */
public class EightNeighbour {
    
    double [][] eightMatrix;
    
    public EightNeighbour(Lattice lattice)
    {
        eightMatrix = new double [(lattice.getSOMHEIGHT())][(lattice.getSOMWIDTH())];
        renderEightNeighbour(lattice);
    }
    
    /** 
     * Fill in values in the 2D array eightMatrix
     * 
     * @param           lattice
     * @void            Fill in values in the 2D array eightMatrix
     */
    public void renderEightNeighbour(Lattice latticeInput)
    {
        for (int i=0; i<latticeInput.getSOMWIDTH(); i++)
        {
            for (int j=0; j<latticeInput.getSOMHEIGHT(); j++)
            {
                //the operation needs to take into consideration 9 situations where the average is calculated differently
                
                //top left corner, calculate average from its neighbour (right+ right bottom + bottom)/3
                if(i-1<0 && j-1<0)
                eightMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j+1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j+1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j).getWeights()))/3;

                //bottom left corner, calculate average from its neighbour (top+ top right + right)/3
                else if(i+1>eightMatrix.length-1 && j-1<0)
                eightMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j+1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j+1).getWeights()))/3;

                //top right corner, calculate average from its neighbours (left+ left bottom + bottom)/3
                else if(i-1<0 && j+1>eightMatrix[0].length-1)
                eightMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j).getWeights()))/3;

                //bottom right corner, calculate average from its neighbours (top+ top left + left)/3
                else if(i+1>eightMatrix.length-1 && j+1>eightMatrix[0].length-1)
                eightMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j-1).getWeights()))/3;
 
                //left Border, calculate average from its neighbours (top+ top right + right + right bottom + bottom)/5 
                else if(j-1<0)
                eightMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j+1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j+1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j+1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j).getWeights()))/5;
                
                //bottom Border, calculate mean from its neighbours (left+ left top + top +top right  + right)/5    
                else if(i+1>eightMatrix.length-1)            
                eightMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j+1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j+1).getWeights()))/5;
     
                //right Border, calculate mean from its neighbours (top+ top left + left + leftbottom + bottom)/5
                else if(j+1>eightMatrix[0].length-1)
                eightMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j).getWeights()))/5;

                //top Border, calculate mean from its neighbours (left+ left bottom+ bottom + bottom right+ right)/3
                else if(i-1<0)               
                eightMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j+1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j+1).getWeights()))/5;

                //calculate average of all of its 8 immediate neighbours
                else if(i>0 && j>0 && i<eightMatrix.length-1 && j<eightMatrix[0].length-1)
                eightMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j+1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i, j+1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j+1).getWeights()))/8;

            }
        }
    }
    
    /** 
     * Get element of eightMatrix at coordinate (x,y)
     * 
     * @param           int x, int y
     * @return          the value in the eightMatrix[x,y]            
     */ 
    public double getEightMatrixElement(int x, int y)
    {
        return eightMatrix[x][y];
    }
    
}
