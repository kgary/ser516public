package org.scrinch.nbmodule;

import org.scrinch.model.ScrinchEnvToolkit;

/**
 *
 * @author christian
 */
public class ScrinchPreferencesMultiviewDescription extends ScrinchMultiviewDescription {

    public ScrinchPreferencesMultiviewDescription(ScrinchEnvToolkit toolkit){
        super(toolkit);
    }

    public String getDisplayName() {
        return "Preferences";
    }

    public ScrinchTopComponent createScrinchElement() {
        return new ScrinchPreferencesTopComponent();
    }

}