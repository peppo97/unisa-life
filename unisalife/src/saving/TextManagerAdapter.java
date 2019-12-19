/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saving;

import java.util.List;
import java.util.Set;
import language.Information;
import language.TextManager;
import language.exceptions.TextFinderException;

/**
 *
 * @author Giuseppe De Simone
 */
class TextManagerAdapter extends TextManager {

    private static final TextManagerAdapter instance = new TextManagerAdapter();

    public static TextManagerAdapter getTextManagerAdpter() {
        return instance;
    }

    /**
     * Not implemented
     */
    @Override
    public Set<String> getAvailableLanguages() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Not implemented
     */
    public List<String> getString(Information obj) throws TextFinderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
