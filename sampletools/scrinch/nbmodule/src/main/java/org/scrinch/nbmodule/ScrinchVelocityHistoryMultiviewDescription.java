package org.scrinch.nbmodule;

import org.scrinch.model.ScrinchEnvToolkit;

/**
 *
 * @author christian
 */
public class ScrinchVelocityHistoryMultiviewDescription extends ScrinchMultiviewDescription {

    public ScrinchVelocityHistoryMultiviewDescription(ScrinchEnvToolkit toolkit){
        super(toolkit);;
    }

    public String getDisplayName() {
        return "Velocity";
    }

    public ScrinchTopComponent createScrinchElement() {
        return new ScrinchVelocityHistoryTopComponent();
    }

}