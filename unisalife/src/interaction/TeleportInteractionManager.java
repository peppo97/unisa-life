/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import game.GameObjects.Player;
import game.GameObjects.Teleport;
import game.Interfaces.Interactable;
import gameSystem.GameStateManager;
import gameSystem.LoadingState;
import gameSystem.PlayState;
import gameSystem.map.MapManager;

/**
 *
 * @author simon
 */
public class TeleportInteractionManager implements InteractionManager {

    @Override
    public void execute(Interactable obj) {
        Teleport t=(Teleport)obj;
        System.out.println("sono arrivato");
        GameStateManager.getInstance().setState(LoadingState.getInstance());
        try{
            MapManager.getInstance().setMap(t.getMapDestination());
            Player.getIstance().setX(t.getPositionDestination().getX());
            Player.getIstance().setY(t.getPositionDestination().getY());
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                }
                System.out.println("CAZZZOOOO");
                GameStateManager.getInstance().setState(PlayState.getInstance());
            }).start();

        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
}
