/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structs;

/**
 *
 * @author ngecheekeen
 */
public class Vector {
    
    public int DIMENSIONS = 0;
    public double ELEMENTS[] = null;
    
    public Vector(int rows)
    {
        DIMENSIONS = rows;
        ELEMENTS = new double[rows];
    }
    
    public Vector(double[] rows)
    {
        ELEMENTS = rows;
        DIMENSIONS = ELEMENTS.length;
    }
    
    public double[] getVector()
    {
        return this.ELEMENTS;
    }
    
    public int getSize()
    {
        return ELEMENTS.length;
    }
    
    public void initializeWeights()
    {
        for(int i = 0 ; i < ELEMENTS.length ; i++)
        {
           
            ELEMENTS[i] = Math.random();
        }
    }
  
}
