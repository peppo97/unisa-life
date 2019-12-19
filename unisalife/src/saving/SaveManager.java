/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saving;

import character.Status;
import saving.exceptions.*;
import exam.booklet.BookletSingleton;
import game.GameObjects.GameInventorySingleton;
import game.GameObjects.Player;
import gameSystem.GameManager;
import gameSystem.map.MapManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import quests.quest.QuestsSingleton;

/**
 *
 * @author Giuseppe De Simone
 * @author Simone Serritiello
 */
public class SaveManager {

    private static SaveManager instance = new SaveManager();
    private List<Saveable> saveableComponents = new ArrayList<>();
    private Map<String, Serializable> savingItems = new HashMap<>();
    private final String PATH = "../save/save.game"; // path per la cartella di salvataggio

    public synchronized static SaveManager getSaveManager() {
        return instance;
    }

    private SaveManager() { // da completare quando abbiamo tutte le classi da salvare
        saveableComponents.add(TextManagerAdapter.getTextManagerAdpter());
        saveableComponents.add(BookletSingleton.getInstance());
        saveableComponents.add(Player.getIstance());
        saveableComponents.add(new Status());
        saveableComponents.add(GameInventorySingleton.getInstance());
        saveableComponents.add(QuestsSingleton.getInstance());
    }

    public boolean isSaveSomething() {
        File f = new File(PATH);
        if (!f.exists() || f.getTotalSpace() == 0) {
            return false;
        }
        return true;
    }

    public void save() throws SavingException {
        for (Saveable sav : saveableComponents) {
            Serializable itemToSave = sav.save();
            savingItems.put(sav.getClass().getName(), itemToSave);
        }
        try (FileOutputStream fileout = new FileOutputStream(new File(PATH));
                ObjectOutputStream s = new ObjectOutputStream(fileout);) {
            s.writeObject(savingItems);

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new SavingException();
        }
    }

    public void load() throws LoadingException {
        try (FileInputStream filein = new FileInputStream(new File(PATH));
                ObjectInputStream s = new ObjectInputStream(filein);) {
            Object obj = s.readObject();
            savingItems = (Map) obj;
            for (Saveable sav : saveableComponents) {
                Serializable item = savingItems.get(sav.getClass().getName());
                sav.load(item);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new LoadingException();
        }
    }
}
