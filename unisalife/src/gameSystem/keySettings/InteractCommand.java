/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import game.GameObjects.GameObject;
import game.GameObjects.Position;
import game.Interfaces.Interactable;
import gameSystem.GameStateManager;
import gameSystem.LoadingState;
import gameSystem.PauseState;
import gameSystem.PlayState;
import gameSystem.map.MapManager;

/**
 *
 * @author Giuseppe De Simone
 */
public class InteractCommand extends KeyCommand implements ActionCommand{

    @Override
    public void visitPlayState(PlayState playState) {
        Position p = player.getFace().nextStep();
        GameObject obj =  MapManager.getInstance().getMap().getGameObject(p);
        if(obj instanceof Interactable){
            GameStateManager.getInstance().setState(PauseState.getInstance());
            player.setVelX(0);
            player.setVelY(0);
            ((Interactable) obj).interact();
            GameStateManager.getInstance().setState(PlayState.getInstance());
        }
    }

    @Override
    public void visitPauseState(PauseState pauseState) {
    }

    @Override
    public void visitiLoadingState(LoadingState loadState) {

    }

}
