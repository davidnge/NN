/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import structs.Vector;

/**
 *
 * @author ngecheekeen
 */
public class UtilityMethods {
    
    public UtilityMethods()
    {
        
    }
    
    public double norm(Vector v)
    {
        double temp[] = v.getVector();
        double normValue = 0.0;
        
        
        for(int i = 0; i < temp.length; i++)
        {
            normValue += Math.pow(temp[i],2);
        }
        
        return Math.sqrt(normValue);
    }
    
    public Vector normalization(Vector v)
    {
        double normValue = norm(v);
        double temp[] = v.getVector();
        
        for(int i=0; i < temp.length; i++)
        {
            temp[i] = temp[i]/normValue;
            v.ELEMENTS[i] = temp[i];
        }
        
        return v;
    }
    
    public double multiply(Vector v, Vector u)
    {
        double cummulativeValue = 0.0;
        
        if(v.DIMENSIONS == u.DIMENSIONS)
        {
            for(int i = 0; i < v.DIMENSIONS; i++)
            {
               cummulativeValue += v.ELEMENTS[i]*u.ELEMENTS[i];
            }
        }
        return cummulativeValue;
    }
    
}
