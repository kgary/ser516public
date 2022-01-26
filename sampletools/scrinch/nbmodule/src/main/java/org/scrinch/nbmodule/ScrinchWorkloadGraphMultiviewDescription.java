package org.scrinch.nbmodule;

import org.scrinch.model.ScrinchEnvToolkit;

/**
 *
 * @author christian
 */
public class ScrinchWorkloadGraphMultiviewDescription extends ScrinchMultiviewDescription {

    public ScrinchWorkloadGraphMultiviewDescription(ScrinchEnvToolkit toolkit){
        super(toolkit);;
    }

    public String getDisplayName() {
        return "Workload";
    }

    public ScrinchTopComponent createScrinchElement() {
        return new ScrinchWorkloadTopComponent();
    }

}