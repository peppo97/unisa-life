/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameResources;
import gameSystem.Game;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
/**
 * Animation represents an animation in the game. It consists in a sequence of 
 * images to be rendered in succession.
 *
 * @author simon
 */
public class Animation {
    
    private final int frames;
    private int index=0;
    private int count=0;
    private final BufferedImage[] images;
    private BufferedImage currentImage;
    
    /**
     * Constructor takes a sequence of BufferedImage objects.
     *
     * 
     * @param args sequence of BufferedImages
     */
    public Animation(BufferedImage... args){
        
        images=new BufferedImage[args.length];
        frames=args.length;        
        System.arraycopy(args, 0, images, 0, frames);
    }
    
    /**
     * this method is responsible for the change of frame to be displayed
     */
    public void runAnimation(){
        index++;
        if(index>Game.ANIMATIONSPEED){
            index=0;
            nextFrame();
        }
    }
    
    /**
     * this method updates the frame of the animation
     */
    public void nextFrame(){
        for(int i=0;i<frames;i++){
            if(count==i)
                currentImage=images[i];
        }
        count++;
        if(count>frames)
            count=0;
    }
        
    /**
     *this method renders the right frame of the sequence.
     * @param g Graphics object where the frame will be displayed
     * @param x x position of the frame
     * @param y y position of the frame
     */
    public void drawAnimation(Graphics g,int x,int y){
            g.drawImage(currentImage, x,y, null);
        }
        
    /*
     * this method renders the right frame of the sequence with
     * dimensions scaleX(width) and scaleY(height).
     * @param g Graphics object where the frame will be displayed
     * @param x x position of the frame
     * @param y y position of the frame
     * @param scaleX width dimension of the frame
     * @param scaleY height dimension of the frame
     */
    public void drawAnimation(Graphics g,int x,int y,int scaleX,int scaleY){
            g.drawImage(currentImage, x, y, null);
        }
     
        
        
    }
    
    
