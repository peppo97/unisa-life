/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.HashMap;
import saving.SaveManager;
import saving.Saveable;
import saving.exceptions.LoadingException;
import saving.exceptions.SavingException;

/**
 * This class declares the methods to set and get keyboard keys used to move the
 * avatar and performs game's actions like to interact with game's objects or to
 * enter in pause modality
 *
 * @author Giuseppe De Simone
 */
public class SettingsManager implements Saveable {

    private enum Commands {
        MOVE_UP,
        MOVE_DOWN,
        MOVE_LEFT,
        MOVE_RIGHT,
        INTERACT,
        PAUSE,
        MAP,
        INVENTORY,
        SAVE
    }

    private HashMap<Commands, Integer> register = new HashMap<>(9);
    private static final SettingsManager instance = new SettingsManager();

    private boolean checkInput(int code) {
        final int A = KeyEvent.VK_A;
        final int Z = KeyEvent.VK_Z;
        final int SPACE = KeyEvent.VK_SPACE;
        final int ZERO = KeyEvent.VK_0;
        final int NINE = KeyEvent.VK_9;
        final int DOWN = KeyEvent.VK_DOWN;
        final int LEFT = KeyEvent.VK_LEFT;

        if (code >= A && code <= Z || code >= ZERO && code <= NINE || code == SPACE || code >= LEFT || code <= DOWN) {
            return true;
        }
        return false;
    }

    private boolean isRegistered(int key) {
        if (register.containsValue(key)) {
            return true;
        }
        return false;
    }

    private boolean setKey(Commands cmd, int button) {
        if (!checkInput(button) || isRegistered(button)) {
            return false;
        }
        register.put(cmd, button);
        try {
            SaveManager.getSaveManager().saveKeys();
        } catch (SavingException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private SettingsManager() {
        register.put(Commands.MOVE_UP, KeyEvent.VK_W);
        register.put(Commands.MOVE_DOWN, KeyEvent.VK_S);
        register.put(Commands.MOVE_LEFT, KeyEvent.VK_A);
        register.put(Commands.MOVE_RIGHT, KeyEvent.VK_D);
        register.put(Commands.PAUSE, KeyEvent.VK_P);
        register.put(Commands.INTERACT, KeyEvent.VK_SPACE);
        register.put(Commands.MAP, KeyEvent.VK_M);
        register.put(Commands.INVENTORY, KeyEvent.VK_I);
        register.put(Commands.SAVE, KeyEvent.VK_S);
    }

    /**
     *
     * @return an int containing the value of keyboard key pressed
     */
    public int getInventoryButton() {
        return register.get(Commands.INVENTORY);
    }

    /**
     *
     * @return an int containing the value of keyboard key pressed
     */
    public int getMapButton() {
        return register.get(Commands.MAP);
    }

    /**
     * set keyboard's key when the player want to visualize the map
     *
     * @param mapButton
     * @return false if key is already setted or it is a no valid value
     * otherwise true
     */
    public boolean setMapButton(int mapButton) {
        return setKey(Commands.MAP, mapButton);
    }

    /**
     *
     * @return an int containing the value of keyboard key pressed
     */
    public int getSaveButton() {
        return register.get(Commands.SAVE);
    }

    /**
     *
     * @return the instance of this class
     */
    public static SettingsManager getSettingsManager() {
        return instance;
    }

    /**
     *
     * @return an int containing the value of keyboard key related to this
     * commad
     */
    public int getMoveUp() {
        return register.get(Commands.MOVE_UP);
    }

    /**
     * set keyboard's key related to move up command
     *
     * @param moveUp
     * @return false if key is already setted or it is a no valid value
     * otherwise true
     */
    public boolean setMoveUp(int moveUp) {
        return setKey(Commands.MOVE_UP, moveUp);
    }

    /**
     *
     * @return an int containing the value of keyboard key related to this
     * commad
     */
    public int getMoveDown() {
        return register.get(Commands.MOVE_DOWN);
    }

    /**
     * set keyboard's key related to move down command
     *
     * @param moveDown
     * @return false if key is already setted or it is a no valid value
     * otherwise true
     */
    public boolean setMoveDown(int moveDown) {
        return setKey(Commands.MOVE_DOWN, moveDown);
    }

    /**
     *
     * @return an int containing the value of keyboard key related to this
     * commad
     */
    public int getMoveLeft() {
        return register.get(Commands.MOVE_LEFT);
    }

    /**
     * set keyboard's key related to move left command
     *
     * @param moveLeft
     * @return false if key is already setted or it is a no valid value
     * otherwise true
     */
    public boolean setMoveLeft(int moveLeft) {
        return setKey(Commands.MOVE_LEFT, moveLeft);
    }

    /**
     *
     * @return an int containing the value of keyboard key related to this
     * commad
     */
    public int getMoveRight() {
        return register.get(Commands.MOVE_RIGHT);
    }

    /**
     * set keyboard's key related to move right command
     *
     * @param moveRight
     * @return false if key is already setted or it is a no valid value
     * otherwise true
     */
    public boolean setMoveRight(int moveRight) {
        return setKey(Commands.MOVE_RIGHT, moveRight);
    }

    /**
     *
     * @return an int containing the value of keyboard key related to this
     * commad
     */
    public int getInteractButton() {
        return register.get(Commands.INTERACT);
    }

    /**
     * set keyboard's key related to interact command command
     *
     * @param interactButton
     * @return false if key is already setted or it is a no valid value
     * otherwise true
     */
    public boolean setInteractButton(int interactButton) {
        return setKey(Commands.INTERACT, interactButton);
    }

    /**
     *
     * @return an int containing the value of keyboard key related to this
     * commad
     */
    public int getPauseButton() {
        return register.get(Commands.PAUSE);
    }

    /**
     * set keyboard's key related to pause command command
     *
     * @param pauseButton
     * @return false if key is already setted or it is a no valid value
     * otherwise true
     */
    public boolean setPauseButton(int pauseButton) {
        return setKey(Commands.PAUSE, pauseButton);
    }

    @Override
    public Serializable save() {
        return this.register;
    }

    @Override
    public void load(Serializable obj) throws LoadingException {
        this.register = (HashMap<Commands, Integer>) obj;
    }
}
