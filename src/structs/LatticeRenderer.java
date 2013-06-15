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
     * Render the following Matrix: UMatrix, 4-Neighbours & 8-Neighbours on JPanel
     * 
     * @param           int x, int y
     * @void            render UMatrix, 4-Neighbours or 8-Neighbours Lattice           
     */ 
    public void renderMatrix(double [][] matrix)
    {
                float cellWidth = (float)getWidth() / (float)matrix.length;
		float cellHeight = (float)getHeight() / (float)matrix[0].length;
		
		int imgW = img.getWidth();
		int imgH = img.getHeight();
		Graphics2D g2 = img.createGraphics();
		g2.setBackground(Color.DARK_GRAY);
		g2.clearRect(0,0,imgW,imgH);
		for (int x=0; x<matrix.length; x++) {
			for (int y=0; y<matrix[0].length; y++) {
                            FindRange(matrix);
                            double range = HIGHESTNODE-SMALLESTNODE;
                            
                            Color gray1 = new Color(60,60,60);
                            Color gray2 = new Color(80,80,80);
                            Color gray3 = new Color(100,100,100);
                            Color gray4 = new Color(120,120,120);
                            Color gray5 = new Color(140,140,140);
                            Color gray6 = new Color(160,160,160);
                            Color gray7 = new Color(180,180,180);
                            Color gray8 = new Color(200,200,200);
                            Color gray9 = new Color(225,225,225);
                            Color gray10 = new Color(255,255,255);
                            
                            
                            if(matrix[x][y]<=0.1*range)
                                g2.setColor(Color.darkGray);
                            else if(matrix[x][y]>0.1*range && matrix[x][y]<0.2*range)
                                g2.setColor(gray4);
                            else if(matrix[x][y]>=0.2*range && matrix[x][y]<0.3*range)
                                g2.setColor(gray5);
                            else if(matrix[x][y]>=0.3*range && matrix[x][y]<=0.4*range)
                                g2.setColor(gray6);
                            else if(matrix[x][y]>=0.4*range && matrix[x][y]<=0.5*range)
                                g2.setColor(gray7);
                            else if(matrix[x][y]>=0.5*range && matrix[x][y]<=0.6*range)
                                g2.setColor(gray8);
                            else if(matrix[x][y]>=0.6*range && matrix[x][y]<=0.7*range)
                                g2.setColor(gray9);
                            else if (matrix[x][y]> 0.7*range)
                                g2.setColor(gray10);
                            
                            /**
                            if(matrix[x][y]<=0.2*range)
                                g2.setColor(Color.darkGray);
                            else if(matrix[x][y]>0.2*range && matrix[x][y]<0.4*range)
                                g2.setColor(s);
                            else if(matrix[x][y]>=0.4*range && matrix[x][y]<0.6*range)
                                g2.setColor(Color.MAGENTA);
                            else if(matrix[x][y]>=0.6*range && matrix[x][y]<=0.8*range)
                                g2.setColor(Color.ORANGE);
                            else if (matrix[x][y]> 0.8*range)
                                g2.setColor(Color.red);
                                **/
                            g2.fillRect((int)(x*cellWidth),(int)(y*cellHeight),(int)cellWidth+1, (int)cellHeight+1);
			}
		}
		g2.dispose();
		repaint();
                
    }
    
    /** 
     * Render the Winner Node plot on JPanel
     * Initial color for winner node (255,255,255). 
     * For each repeating winner node, the value is decremented starting from b,
     * then g, then r in the palette (r,g,b).
     * Total possible color to color the winner nodes: 16,581,375 (255x255x255)
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
    public void FindRange(double [][] umat)
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
