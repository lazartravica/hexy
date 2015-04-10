package core;
import rafgfxlib.Util;

import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public abstract class Tile {
    private int tileWidth;
    private int tileHeight;

    public BufferedImage image = null;
    public int positionX = 0;
    public int positionY = 0;
    
    public int centerX, centerY;
    public int size; 
   
    public Polygon hex;
    
    protected Graphics2D g;

    public Tile(String fileName, int coordX, int coordY, int tileWidth, int tileHeight, Graphics2D g) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        image = Util.loadImage(fileName);

        positionX = (coordX * tileWidth) + (coordY * tileWidth / 2);
        positionY = coordY * (int)Math.round(tileHeight * 0.75);

        centerX = positionX+tileWidth/2; 
        centerY =positionY+tileHeight/2;
        size =(int)(tileWidth/Math.sqrt(3));
        hex = new Polygon();
        
        
        this.g = g;
        
        
        
       
        
    } 
    
    public boolean containsPoint(Point a){
    	return hex.contains(a);
    }
    
    
   
    
    
    
    
}
