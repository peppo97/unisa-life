/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 * A block represents a point in the map that can't be passed from the player.
 *
 * @author simon
 */
public class Block extends GameObject{
    
    /**
     *constructor returns a new istance of block
     * @param x x position of the block
     * @param y y position of the block
     * @param i ObjectId of the block
     */
    public Block(float x,float y,ObjectId i){
        super(x,y,i);
        /*try {
        sprite = ImageIO.read(
				getClass().getResourceAsStream("/Sprites/gatto.png")
        );}
        catch (Exception e) {
            System.exit(1);
    }*/
    }
    
    /**
     * method tick is void because a block 
     * @param object
     */
    /*
    public void tick(LinkedList<GameObject> object){
        
    }
*/
    /**
     *
     * @param g
     */
    /*
    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect((int)x,(int)y, width, height);
        //g.drawImage(image, (int)x,(int) y, (int)width, (int)height, null);
    }*/
    
}
