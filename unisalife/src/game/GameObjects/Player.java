/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.GameResources.Animation;
import game.GameResources.Game;
import game.GameResources.Handler;
import game.GameResources.NotGameState;
import game.GameResources.PlayState;
import game.Interfaces.Tickable;
import game.Interfaces.Interactable;
import game.Interfaces.Renderable;
import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author simon
 */
public class Player extends GameObject implements Tickable, Renderable {
    
    private float velX = 0;
    protected float velY = 0;
    protected Animation upWalk;
    protected Animation downWalk;
    protected Animation leftWalk;
    protected Animation rightWalk;
    protected BufferedImage facingDownImage;
    protected BufferedImage facingLeftImage;
    protected BufferedImage facingRightImage;
    protected BufferedImage facingUpImage;
    protected FaceState face;
    private static Player uniqueIstance = null;
    private boolean nextMove = true;
    private Position p;
    
    /*public Player(float x,float y,SubjectEnum i){
     super(x,y,i);
     }*/
    private Player(Position p) {
        super(p);
        face = new DownFaceState(this);
        
        /*try {
         image = ImageIO.read(
         getClass().getResourceAsStream("/Sprites/gatto.png")
         );}
         catch (Exception e) {
         System.exit(1);
         }
         upWalk=u;
         downWalk=d;
         leftWalk=l;
         rightWalk=r;
         facingDownImage=Game.texturePlayer[0];
         facingLeftImage=Game.texturePlayer[3];
         facingRightImage=Game.texturePlayer[6];
         facingUpImage=Game.texturePlayer[9];
    
         */
    }
    
    public void changeFaceSet(BufferedImage down, BufferedImage left, BufferedImage right, BufferedImage up) {
        facingLeftImage = left;
        facingRightImage = right;
        facingUpImage = up;
        facingDownImage = down;
    }
    
    public void changeAnimationSet(Animation down, Animation left, Animation right, Animation up) {
        upWalk = up;
        downWalk = down;
        leftWalk = left;
        rightWalk = right;
    }

    /**
     * @param g
     * @return
     *
     */
    public static Player getIstance() {
        if (uniqueIstance == null) {
            return new Player(new Position(50,50));
        }
        return uniqueIstance;
    }
    
    public void setX(int x){
        this.p.setX(x);
    }
    
    public void setY(int y){
        this.p.setY(y);
    }
    
    /**
     *
     * @param a
     */
    /**
     *
     * @return
     */
    
    public int getX(){
        return p.getX();
    }
    
    public int getY(){
        return p.getY();
    }
    
    public float getVelX() {
        return velX;
    }

    /**
     *
     * @return
     */
    public float getVelY() {
        return velY;
    }

    /**
     *
     * @param velX
     */
    public void setVelX(float velX) {
        this.velX = velX;
    }

    /**
     *
     * @param velY
     */
    public void setVelY(float velY) {
        this.velY = velY;
    }


    
     
    @Override
    public void tick(/*LinkedList<GameObject> objects*/) {
        int x=p.getX();
        int y=p.getY();
        nextMove = true;
        if (velX > 0) {
            face = new RightFaceState(this);
        } else if (velX < 0) {
            face = new LeftFaceState(this);
        } else if (velY > 0) {
            face = new DownFaceState(this);
        } else if (velY < 0) {
            face = new UpFaceState(this);
        }
        collisions(Game.getGame().getActualMap().getObjectManager());
        if (x + velX > 0 && x + velX < Game.getGame().getWidthMap() - Game.DIMENSIONSPRITE && nextMove == true) {
            x += velX;
        }
        if (y + velY > 0 && y + velY < Game.getGame().getHeightMap() - Game.DIMENSIONSPRITE && nextMove == true) {
            y += velY;
        }
        //collisions(game.getActualMap().getList());
        downWalk.runAnimation();
        leftWalk.runAnimation();
        rightWalk.runAnimation();
        upWalk.runAnimation();
    }

    /**
     *
     * @param ObjectsManager
     */
    public void collisions(ObjectManager objMan) {
        GameObject g = objMan.get(face.nextStep());
            if (g!=null)
            {   
                
                if(g instanceof Teleport){
                    Teleport t = (Teleport)g;
                /*
                 System.out.print("hello");
                 System.out.print("tile"+t.tilePath);
                 System.out.print(t.mapPath);
                 Game.tileMap.loadTiles(t.tilePath);
                 */
                    Game.getGame().updateActualMap(t.getMapDest());
                    Game.getGame().setWidthMap(Game.getGame().getActualMap().getTileMap().getWidth());
                    Game.getGame().setHeightMap(Game.getGame().getActualMap().getTileMap().getHeight());
                    Game.getGame().setHandler(new Handler());
                    p.setX(t.getDestination().getX());
                    p.setY(t.getDestination().getY());
                }
                
                nextMove=false;
                
                
            }
            //if (face.nextStep().intersects(g.getBounds())) {
                
            /*
             if (getBottomBounds().intersects(g.getBounds()))//&&g.getId()!=SubjectEnum.Teleport)
             {
                
             y = g.getY() - height-1;
             break;
             }

             if (getTopBounds().intersects(g.getBounds())) {//&&g.getId()!=SubjectEnum.Tel){
                
             y = g.getY() + g.height+1;
             break;
             }
             if (getLeftBounds().intersects(g.getBounds())) {//&&g.getId()!=SubjectEnum.Block){
             nextMove=false;
             x = g.getX() + g.width +1;
             break;
             }
             if (getRightBounds().intersects(g.getBounds())) {//&&g.getId()!=SubjectEnum.Block){
             nextMove=false;
             x = g.getX() - width - 1;
             break;
             }
             */
        }
        
    

    //dialog deve lavorare solo con oggetti interactable (item e persone per adesso)
    public void dialog(ObjectManager o) {
        GameObject g = o.get(face.nextStep());
        if (g==null || !(g instanceof Interactable))
        {
                this.setVelX(0);
                this.setVelY(0);
                Game.getGame().setState(new NotGameState(Game.getGame()));
                ((Interactable) g).interact();
                Game.getGame().setState(new PlayState(Game.getGame()));
                
        }    
    }

    /**
     *
     * @param g
     */
    @Override
    public void render(Graphics g) {
        if (velX > 0) {
            rightWalk.drawAnimation(g,p.getX(), p.getY(), width, height);
            return;
        }
        
        if (velX < 0) {
            leftWalk.drawAnimation(g, p.getX(), p.getY(), width, height);
            return;
        }
        if (velY > 0) {
            downWalk.drawAnimation(g, p.getX(), p.getY(), width, height);
            return;
        }
        
        if (velY < 0) {
            upWalk.drawAnimation(g, p.getX(), p.getY(), width, height);
            return;
        }
        face.drawFace(g);
    }
    
    private Position visualViewOfPlayer() {
        return face.visualViewOfPlayer();
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
}
