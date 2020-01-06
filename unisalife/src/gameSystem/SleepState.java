/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import game.Interfaces.Initializable;
import gameSystem.keySettings.interfaces.KeyCommand;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author 1997g
 */
public class SleepState extends GameState {
    
    private static SleepState instance;
    private BufferedImage img;
    
    public static SleepState getInstance() {
        if (instance == null) {
            instance = new SleepState();
        }
        return instance;
    }
    
    private SleepState() {
    }

    @Override
    public void init() throws Initializable.InitException {
        try {
            img = ImageIO.read(getClass().getResource("/Sprites/sleepingwindow.png"));
        } catch (IOException ex) {
            throw new Initializable.InitException("Can't find Sleeping image");
        }
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(img, 0, 0, null);
    }

    @Override
    public void handleInput(KeyCommand cmd) {
    }
    
}