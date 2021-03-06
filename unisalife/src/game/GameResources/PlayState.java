/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameResources;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
/**
 * This class represent the state in which the player is effective playing the game
 * @author simon
 */
public class PlayState extends GameState{

    /**
     *
     */
    public PlayState(Game g){
        super(g);
    }
    
    /**
     *
     * @param g
     */
    @Override
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.white);
            g.fillRect(0, 0, Game.WIDTHSCREEN, Game.HEIGHTSCREEN2);
            g2d.translate(Game.camera.getX(), Game.camera.getY());
            Game.handler.render(g2d);
            g2d.translate(-Game.camera.getX(), -Game.camera.getY());
    }

    /**
     *
     */
    @Override
    public void tick(){
        Game.handler.tick();
        Game.camera.tick();
    }
    
    /**
     *
     * @param k
     */
    @Override
    public void performPressAction(int k){
        if(k==KeyEvent.VK_M)game.state=new NotGameState(game);
        if(k==KeyEvent.VK_RIGHT) Game.player.setVelX(game.PLAYERSPEED);
        if(k==KeyEvent.VK_LEFT) Game.player.setVelX(-game.PLAYERSPEED);
        if(k==KeyEvent.VK_DOWN) Game.player.setVelY(game.PLAYERSPEED);
        if(k==KeyEvent.VK_UP) Game.player.setVelY(-game.PLAYERSPEED);
        if(k==KeyEvent.VK_SPACE) Game.player.dialog(game.getActualMap().getList());
    }

    /**
     *
     * @param k
     */
    @Override
    public void performReleaseAction(int k){
        if(k==KeyEvent.VK_RIGHT) Game.player.setVelX(0);
        if(k==KeyEvent.VK_LEFT) Game.player.setVelX(0);
        if(k==KeyEvent.VK_DOWN) Game.player.setVelY(0);
        if(k==KeyEvent.VK_UP) Game.player.setVelY(0);
    }
    }
