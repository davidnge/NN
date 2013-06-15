/*
 * This class generates the U-Matrix for visualization
 */
package structs;

import org.apache.commons.math3.linear.ArrayRealVector;
import utils.UtilityMethods;

/**
 *
 * @author ngecheekeen
 */
public class UMatrix {
    
    static ArrayRealVector tempSOM  [][];
    static double UMatrix [][];
    
    /** 
     * Fill in values in the 2D array UMatrix
     * 
     * @param           lattice
     * @void            Fill in values in the 2D array UMatrix
     */
    public static void renderUMatrix(Lattice lattice)
    {
         //the tempSOM 2D array is the basis to derive other values for the UMatrix
         tempSOM  = new ArrayRealVector [lattice.getSOMHEIGHT()*2-1][lattice.getSOMWIDTH()*2-1];
         UMatrix = new double [(lattice.getSOMHEIGHT()*2)-1][(lattice.getSOMWIDTH()*2)-1];
        
        // The original vectors must first stored in position that is the same 
        // as where the mean in the UMatrix will be calculated
        for (int i=0; i<lattice.getSOMHEIGHT(); i++)
        {
            for (int j=0; j<lattice.getSOMWIDTH(); j++)
            {
                tempSOM[i*2][j*2] = lattice.getNode(i, j).getWeights();

            }
        }
       
        //Fill every distance value that takes horizontal weight vectors
        for (int i=0; i<UMatrix.length; i+=2)
        {
            for (int j =1; j<UMatrix[0].length; j+=2)
            {          
                UMatrix[i][j] = tempSOM[i][j-1].getDistance(tempSOM[i][j+1]);
        
            }   
        }
        
        //Fill every distance value that takes vertical weight vectors
        for (int i=1; i<UMatrix.length; i+=2)
        {
            for (int j = 0; j<UMatrix[0].length; j +=2)
            {
                UMatrix[i][j] = tempSOM[i-1][j].getDistance(tempSOM[i+1][j]);
            }
        }
        
        //Fill every distance that takes diagonal weight vectors
        for (int i =1; i<UMatrix.length; i+=2)
        {
            for (int j =1; j<UMatrix[0].length; j+=2)
            {
                UMatrix[i][j] = (tempSOM[i-1][j-1].getDistance(tempSOM[i+1][j+1]) + tempSOM[i+1][j-1].getDistance(tempSOM[i-1][j+1]))/(2*Math.sqrt(2));
            }
        }
        
        //Fill the cells that calculate the mean of its immediate neighbours
        for (int i =0; i<UMatrix.length; i+=2)
        {
            for (int j = 0; j<UMatrix[0].length; j+=2)
            {
                //the operation needs to take into consideration 9 situations where the mean is calculated differently
           
                //top left corner, calculate mean from its (right+ right bottom + bottom) neighbours
                if(i-1<0 && j-1<0)
                UMatrix[i][j] = (UMatrix[i][j+1] + UMatrix[i+1][j+1] + UMatrix[i+1][j])/3;

                //bottom left corner, calculate mean from its (top+ top right + right) neighbours
                else if(i+1>UMatrix.length-1 && j-1<0)
                UMatrix[i][j] = (UMatrix[i-1][j] + UMatrix[i-1][j+1] + UMatrix[i][j+1])/3;
                
                //top right corner, calculate mean from its (left+ left bottom + bottom) neighbours
                else if(i-1<0 && j+1>UMatrix[0].length-1)
                UMatrix[i][j] = (UMatrix[i][j-1] + UMatrix[i+1][j-1] + UMatrix[i+1][j])/3;
                
                //bottom right corner, calculate mean from its (top+ top left + left) neighbours
                else if(i+1>UMatrix.length-1 && j+1>UMatrix[0].length-1)
                UMatrix[i][j] = (UMatrix[i-1][j] + UMatrix[i-1][j-1] + UMatrix[i][j-1])/3;
                 
                //left Border, calculate mean from its (top+ topright+ right +right bottom + bottom) neighbours   
                else if(j-1<0)
                UMatrix[i][j] = (UMatrix[i-1][j] + UMatrix[i-1][j+1] + UMatrix[i][j+1] + UMatrix[i+1][j+1] + UMatrix[i+1][j])/5;
                
                //bottom Border, calculate mean from its (left+left top+ top+ top right + right) neighbours 
                else if(i+1>UMatrix.length-1)            
                UMatrix[i][j] = (UMatrix[i][j-1] + UMatrix[i-1][j-1] + UMatrix[i-1][j] + UMatrix[i-1][j+1] + UMatrix[i][j+1])/5;
                                         
                //right Border, calculate mean from its (top + top left + left + left bottom + bottom) neighbours   
                else if(j+1>UMatrix[0].length-1)
                UMatrix[i][j] = (UMatrix[i-1][j] + UMatrix[i-1][j-1] + UMatrix[i][j-1] + UMatrix[i+1][j-1] + UMatrix[i+1][j])/5;
                
                //top Border, calculate mean from its (left+ left bottom + bottom + bottom right + right) neighbours
                else if(i-1<0)               
                UMatrix[i][j] = (UMatrix[i][j-1] + UMatrix[i+1][j-1] + UMatrix[i+1][j] + UMatrix[i+1][j+1] + UMatrix[i][j+1])/5;
              
                //calculate mean from all its 8 neighbours 
                else //if(i>0 && j>0 && i<UMatrix.length-1 && j<UMatrix[0].length-1)
                UMatrix[i][j] = (UMatrix[i-1][j] + UMatrix[i-1][j-1] + UMatrix[i][j-1] + UMatrix[i+1][j-1] + UMatrix[i+1][j] + UMatrix[i+1][j+1] + UMatrix[i][j+1] + UMatrix[i-1][j+1])/8;
                
            }
        }
 
    }
    
    
   /** 
     * Get the Height of the UMatrix
     * 
     * @param           none
     * @return          UMatrix Height
     */
    public int getHeight()
    {
        return UMatrix.length;
    }
    
    /** 
     * Get the Width of the UMatrix
     * 
     * @param           none
     * @return          UMatrix Width
     */
    public int getWidth()
    {
        return UMatrix[0].length;
    }
    
    /** 
     * Get element of UMatrix at coordinate (x,y)
     * 
     * @param           int x, int y
     * @return          the value in the UMatrix[x,y]            
     */ 
    public double getUMatrixElement(int x, int y)
    {
        return UMatrix[x][y];
    }
    
    //To initially test the UMatrix class
    public static void main(String[] args) {
        
        //testing
        double [][] test2Darray = new double [3][3];
        double [] testVector = {5.0,3.0, 15.0};
        ArrayRealVector t = new ArrayRealVector(testVector);
        
        Lattice testLattice = new Lattice(3,3,3);
        
        for(int i =0; i<testLattice.getSOMHEIGHT(); i++)
        {
            for (int j=0; j<testLattice.getSOMWIDTH(); j++)
            {
                testLattice.getNode(i, j).setWeights(t);
                //System.out.println("asd"+ testSOM.getNode(i, j).getWeights().toString());
            }
        }
          
        renderUMatrix (testLattice);
        
        for (int i =0; i<UMatrix.length; i++)
        {
            for (int j =0; j<UMatrix[0].length; j++)
            {
                System.out.print(UMatrix[i][j] + "||"); 
                
            }
            System.out.print("\n");
        }
    }
    
}
