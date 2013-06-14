/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.apache.commons.math3.linear.ArrayRealVector;

/**
 *
 * @author ngecheekeen
 */
public class UtilityMethods {
     
    /** 
     * Find the Euclidean distance between node(x1,y1) and node(x2,y2) in the lattice
     * 
     * @param           x1,y1,x2,y2 
     * @return          distance between 2 nodes' weight vector
     */
    public double euclideanDist(int x1, int x2, int y1, int y2)
    {
        return Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
    }
    
    /** 
     * Normalize the ArrayRealVector
     * 
     * @param           An ArrayRealVector
     * @return          Normalized ArrayRealVector
     */
    public ArrayRealVector normalization (ArrayRealVector v)
    {
        for (int i=0; i<v.getDimension(); i++)
        {
            v.setEntry(i, (v.getEntry(i)/v.getNorm()));
        }
        
        return v;
    }
}
