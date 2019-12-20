/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import game.GameObjects.Player;
import game.Interfaces.Initializable;
import gameSystem.keySettings.interfaces.KeyCommand;
import gameSystem.map.MapManager;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author simon
 */
public class MapState extends GameState{
    private static MapState instance;
    private BufferedImage img;
    
    public static MapState getInstance() throws Initializable.InitException {
        if (instance == null) {
            instance = new MapState();
        }
        return instance;
    }
    
    private MapState() throws Initializable.InitException {
        try {
            img = ImageIO.read(getClass().getResource("/Sprites/Map.jpg"));
        } catch (IOException ex) {
            throw new Initializable.InitException("Can't find Map image");
        }
    }
    
    
    
    @Override
    public void init() {
        
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Game.WIDTHSCREEN, Game.HEIGHTSCREEN2);
        g.drawImage(img, Game.WIDTHSCREEN/4, Game.HEIGHTSCREEN2/4, null);
        g.setColor(Color.red);
        int xPlayer = Player.getIstance().getPosition().getX();
        int yPlayer = Player.getIstance().getPosition().getY();
        int heightMap = MapManager.getInstance().getMap().getHeightMap();
        int widthMap = MapManager.getInstance().getMap().getWidthMap();
        int xPlayerInMap = (int)Math.ceil(xPlayer*img.getWidth()/widthMap)+Game.WIDTHSCREEN/4;
        int yPlayerInMap = (int)Math.ceil(yPlayer*img.getHeight()/heightMap)+Game.HEIGHTSCREEN2/4;
        g.setStroke(new BasicStroke(2));
        g.drawLine(xPlayerInMap - 4 , yPlayerInMap, xPlayerInMap + 4, yPlayerInMap);
        g.drawLine(xPlayerInMap, yPlayerInMap-4, xPlayerInMap , yPlayerInMap+4);
    }

    @Override
    public void handleInput(KeyCommand cmd) {
        
        cmd.visitMapState(instance);
    }
    
}