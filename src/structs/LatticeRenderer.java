/*
 * This class draws the Lattice onto the JPanel
 * 
 */
package structs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author ngecheekeen
 */
public class LatticeRenderer extends JPanel {
    
    BufferedImage img;
    double HIGHESTNODE;
    double SMALLESTNODE;
    //double [][] Umatrix;
    //private int jpg;

    @Override
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);       
        g.drawImage(img, 0, 0, this);
    }
    
    /** 
     * to F]Get or create a BufferedImage during initialization
     * 
     * @param           none
     * @void            BufferedImage         
     */ 
    public BufferedImage getImage() 
    {
        if (img == null)
            img = (BufferedImage)createImage(getWidth(), getHeight());
	return img;
    }

    /** 
     * Render the following Matrix: UMatrix, 4-Neighbours & 8-Neighbours
     * 
     * @param           int x, int y
     * @void            render UMatrix, 4-Neighbours or 8-Neighbours Lattice           
     */ 
    public void renderMatrix(double [][] umatrix)
    {
                float cellWidth = (float)getWidth() / (float)umatrix.length;
		float cellHeight = (float)getHeight() / (float)umatrix[0].length;
		
		int imgW = img.getWidth();
		int imgH = img.getHeight();
		Graphics2D g2 = img.createGraphics();
		g2.setBackground(Color.DARK_GRAY);
		g2.clearRect(0,0,imgW,imgH);
		for (int x=0; x<umatrix.length; x++) {
			for (int y=0; y<umatrix[0].length; y++) {
                            FindLargestandSmallestNode(umatrix);
                            double range = HIGHESTNODE-SMALLESTNODE;
                            if(umatrix[x][y]<=0.2*range)
                                g2.setColor(Color.WHITE);
                            else if(umatrix[x][y]>0.2*range && umatrix[x][y]<0.4*range)
                                g2.setColor(Color.cyan);
                            else if(umatrix[x][y]>=0.4*range && umatrix[x][y]<0.6*range)
                                g2.setColor(Color.MAGENTA);
                            else if(umatrix[x][y]>=0.6*range && umatrix[x][y]<=0.8*range)
                                g2.setColor(Color.ORANGE);
                            else if (umatrix[x][y]> 0.8*range)
                                g2.setColor(Color.red);

                            g2.fillRect((int)(x*cellWidth),(int)(y*cellHeight),(int)cellWidth+1, (int)cellHeight+1);
			}
		}
		g2.dispose();
		repaint();
                
    }
    
    /** 
     * Get element of fourMatrix at coordinate (x,y)
     * 
     * @param           2D array
     * @void            render Winner Node Lattice         
     */ 
    public void renderWinner(int [][] xMatrix)
    {
        float cellWidth = (float)getWidth() / (float)xMatrix.length;
        float cellHeight = (float)getHeight() / (float)xMatrix[0].length;
         
        int imgW = img.getWidth();
        int imgH = img.getHeight();
        Graphics2D g2 = img.createGraphics();
        g2.setBackground(Color.DARK_GRAY);
	g2.clearRect(0,0,imgW,imgH);
        
        for (int x=0; x<xMatrix.length; x++) 
        {
            for (int y=0; y<xMatrix[0].length; y++) 
            {
                if (xMatrix[x][y]==0)
                {
                    g2.setColor(Color.DARK_GRAY);
                }
                else
                {    
                     int r=255;
                     int g=255;
                     int b=255;
                     
                     int temp1 = xMatrix[x][y]/255;
                     int temp2 = xMatrix[x][y]%255;
                     int temp3 = xMatrix[x][y]%65025;
                     
                     if (temp1 >=1 && temp1<255)
                     {
                         g = g-temp1;
                     }
                     
                     else if (temp1<1)
                     {
                         b = b - xMatrix[x][y];
                     }
                     
                     else if (temp1 >= 255 && temp1<65025)
                     {
                         b = b-temp2;
                         g = g-temp1;
                     }
                     
                     else if (temp1 >=65025)
                     {
                         b = b-temp2;
                         g = g - (temp3/255);
                         r = r- temp3;
                     }
                     /**
                     b = b - xMatrix[x][y];
                     if(b<=0)
                     {
                         b = 0;
                         g = g - (xMatrix[x][y]-255);
                         
                         if (g<=0)
                         {
                             g = 0;
                             r = r - (xMatrix[x][y] - 255 - 255);
                         }             
                     }**/
        
                    Color rgb = new Color (r,g,b);
                    g2.setColor(rgb);    
                }
                g2.fillRect((int)(x*cellWidth),(int)(y*cellHeight),(int)cellWidth+1, (int)cellHeight+1);
               
            }
        }
        g2.dispose();
        repaint();
                        
    }
    
    
    
    /** 
     * find the range of the highest and lowest value in the Matrix
     * 
     * @param           2D Array
     * @void            set HIGHESTNODE and SMALLESTNODE values           
     */ 
    public void FindLargestandSmallestNode(double [][] umat)
    {
         HIGHESTNODE = umat[0][0];
         SMALLESTNODE = umat[0][0];
        
        for (int i = 0; i<umat.length; i++)
        {
            for (int j = 0; j<umat[0].length; j++)
            {
                if (umat[i][j]>HIGHESTNODE)
                {
                    HIGHESTNODE = umat[i][j];
                }
                
                if (umat[i][j]<SMALLESTNODE)
                {
                    SMALLESTNODE = umat[i][j];                     
                }
                
            }
  
        }
     
    }   
}
