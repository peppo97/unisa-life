/**
 * Sprint #1
 * User Story: (13) As a player I would like more languages
 * Task: (7) Structure of the language package
 * Team Members: Alfonso De Masi, Giuseppe De Simone
*/
package language;

import language.exceptions.TextFinderException;
import language.exceptions.StringNotFoundException;
import java.util.List;

/**
 * Interface for a generic class which aim is to find a text to be shown in the game for a given object
 * @author alfon
 */
public interface TextFinder {
    
    /**
     * Method to perform a query and get the messages for that object
     * @param obj and object that implements Information interface
     * @return a list of strings
     * @throws StringNotFoundException 
     */
    List<String> getString(Information obj) throws TextFinderException;

}
