/*
 * This class generates the Matrix for 4-Neighbours
 */
package structs;

import org.apache.commons.math3.linear.ArrayRealVector;

/**
 *
 * @author ngecheekeen
 */
public class FourNeighbour {
   
    double [][] fourMatrix;
    ArrayRealVector tempSOM  [][];
    
    public FourNeighbour(Lattice lattice)
    {
        tempSOM  = new ArrayRealVector [lattice.getSOMHEIGHT()][lattice.getSOMWIDTH()];
        fourMatrix = new double [(lattice.getSOMHEIGHT())][(lattice.SOMWIDTH)];
        renderFourNeighbour(lattice);
    }

    /** 
     * Fill in values in the 2D array fourMatrix
     * 
     * @param           lattice
     * @void            Fill in values in the 2D array fourMatrix
     */
    public void renderFourNeighbour(Lattice latticeInput)
    {
        for (int i=0; i<latticeInput.getSOMWIDTH(); i++)
        {
            for (int j=0; j<latticeInput.getSOMHEIGHT(); j++)
            {
                //the operation needs to take into consideration 9 situations where the average is calculated differently
                
                //top left corner, calculate average from its neighbour (right+ bottom)/3
                if(i-1<0 && j-1<0)
                fourMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j+1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j).getWeights()))/2;

                //bottom left corner, calculate average from its neighbour (top + right)/2
                else if(i+1>fourMatrix.length-1 && j-1<0)
                fourMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j+1).getWeights()))/2;

                //top right corner, calculate average from its neighbours (left+ bottom)/2
                else if(i-1<0 && j+1>fourMatrix[0].length-1)
                fourMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j).getWeights()))/2;

                //bottom right corner, calculate average from its neighbours (top + left)/2
                else if(i+1>fourMatrix.length-1 && j+1>fourMatrix[0].length-1)
                fourMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j-1).getWeights()))/2;
 
                //left Border, calculate average from its neighbours (top+ right + bottom)/3 
                else if(j-1<0)
                fourMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j+1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j).getWeights()))/3;
                
                //bottom Border, calculate mean from its neighbours (left+ top  + right)/3    
                else if(i+1>fourMatrix.length-1)            
                fourMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j+1).getWeights()))/3;
     
                //right Border, calculate mean from its neighbours (top+ left + bottom)/3
                else if(j+1>fourMatrix[0].length-1)
                fourMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j).getWeights()))/3;

                //top Border, calculate mean from its neighbours (left+ bottom + right)/3
                else if(i-1<0)               
                fourMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j+1).getWeights()))/3;

                //calculate average of all of its 4 immediate neighbours
                else if(i-1>0 && j-1>0 && i+1<fourMatrix.length-1 && j+1<fourMatrix[0].length-1)
                fourMatrix[i][j] = (latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i-1,j).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i,j-1).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i+1,j).getWeights()) + latticeInput.getNode(i, j).getWeights().getDistance(latticeInput.getNode(i, j+1).getWeights()))/4;

            }
        }
    }
    
   /** 
     * Get element of fourMatrix at coordinate (x,y)
     * 
     * @param           int x, int y
     * @return          the value in the fourMatrix[x,y]            
     */ 
    public double getFourMatrixElement(int x, int y)
    {
        return fourMatrix[x][y];
    }
    
}
