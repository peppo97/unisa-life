/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import game.Interfaces.Initializable;
import hud.HudUpdater;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import saving.Saveable;
import saving.exceptions.LoadingException;
import unisagui.GameFrame;
import unisagui.GuiManager;

/**
 *
 * @author mariodesio
 */
public class StatusManager implements Saveable, Initializable {
    private int initialEnergy,initialHunger,initialStress,initialMoney;
    private final GameFrame gameframe = GameFrame.getInstance();
    private HudUpdater updater;
    private static StatusManager instance;
    private static Thread up;
    private StatusManager() {
    }
    
    public static StatusManager getInstance() {
        if (instance == null) {
            instance = new StatusManager();
        }
        return instance;
    }
    
    public synchronized void updateEnergy(int increment) {
        int newValue = Status.getEnergyLevel() + increment;
        Status.setEnergyLevel(newValue);
        GuiManager.getInstance().updateEnergyBar(newValue);
    }
    
    public synchronized void updateHunger(int increment) {
        int newValue = Status.getHungerLevel() + increment;
        Status.setHungerLevel(newValue);
        GuiManager.getInstance().updateHungerBar(newValue);
    }
    
    public synchronized void updateStress(int increment) {
        int newValue = Status.getStressLevel() + increment;
        Status.setStressLevel(newValue);
        GuiManager.getInstance().updateStressBar(newValue);
    }
    
    public synchronized void updateMoney(int increment) {
        int newValue = Status.getMoney() + increment;
        Status.setMoney(newValue);
        GuiManager.getInstance().updateMoney(newValue);
    }
    
    public synchronized void setEnergy(int newValue) {
        Status.setEnergyLevel(newValue);
        GuiManager.getInstance().updateEnergyBar(newValue);
    }
    
    public synchronized void setHunger(int newValue) {
        Status.setHungerLevel(newValue);
        GuiManager.getInstance().updateHungerBar(newValue);
    }
    
    public synchronized void setStress(int newValue) {
        Status.setStressLevel(newValue);
        GuiManager.getInstance().updateStressBar(newValue);
    }
    
    private synchronized void setMoney(int newValue) {
        Status.setMoney(newValue);
        GuiManager.getInstance().updateMoney(newValue);
    }
    
    public static synchronized void stopBar(){
        if(up!=null){
            up.interrupt();
        }
    }
    
    /**
     *
     * @return a Serializable useful to save the status of the character
     */
    @Override
    public Serializable save() {
        List<Integer> stat = new ArrayList<>();
        stat.add(Status.getEnergyLevel());
        stat.add(Status.getHungerLevel());
        stat.add(Status.getStressLevel());
        stat.add(Status.getMoney());
        return (Serializable) stat;
    }

    /**
     *
     * @param obj is a Serializable. Downcast is necessary before load the obj
     */
    @Override
    public void load(Serializable obj) throws LoadingException {
        List<Integer> stat = (ArrayList<Integer>) obj;
        int energyLevel = stat.get(0);
        int hungerLevel = stat.get(1);
        int stressLevel = stat.get(2);
        int money = stat.get(3);
        setEnergy(energyLevel);
        setHunger(hungerLevel);
        setMoney(money);
        setStress(stressLevel);
        this.updater = new HudUpdater();
        up = new Thread(updater);
        up.start();
    }
    
    @Override
    public void init() {
        this.updater = new HudUpdater();
        Thread up = new Thread(updater);
        initialEnergy = Status.getEnergyLevel();
        initialHunger = Status.getHungerLevel();
        initialStress = Status.getStressLevel();
        initialMoney = Status.getMoney();
        up.start();
        setEnergy(initialEnergy);
        setHunger(initialHunger);
        setMoney(initialMoney);
        setStress(initialStress);
    }
}
