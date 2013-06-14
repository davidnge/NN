/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structs;

import java.util.ArrayList;
import java.util.StringTokenizer;
import org.apache.commons.math3.linear.ArrayRealVector;

/**
 *
 * @author ngecheekeen
 */
public class Node {

    //Vector WEIGHT_VECTOR = null;
    ArrayRealVector WEIGHT_VECTOR;

    public Node(int Dimension)
    {
        WEIGHT_VECTOR = new ArrayRealVector(Dimension);
        for(int i = 0 ; i < WEIGHT_VECTOR.getDimension() ; i++)
        {
           
            WEIGHT_VECTOR.setEntry(i, Math.random());
        }  
    }
 
    public ArrayRealVector getWeights()
    {
        return WEIGHT_VECTOR;
    }
    
    public void setWeights(ArrayRealVector v)
    {
        this.WEIGHT_VECTOR = v;
    }
    

    
}
