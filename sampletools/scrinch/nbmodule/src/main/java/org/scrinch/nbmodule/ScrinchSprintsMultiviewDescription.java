package org.scrinch.nbmodule;

import org.scrinch.model.ScrinchEnvToolkit;

/**
 *
 * @author christian
 */
public class ScrinchSprintsMultiviewDescription extends ScrinchMultiviewDescription {

    public ScrinchSprintsMultiviewDescription(ScrinchEnvToolkit toolkit){
        super(toolkit);
    }

    public String getDisplayName() {
        return "Sprints";
    }

    public ScrinchTopComponent createScrinchElement() {
        return new ScrinchSprintsTopComponent();
    }

}