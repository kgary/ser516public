package org.scrinch.nbmodule;

import org.scrinch.model.ScrinchEnvToolkit;

/**
 *
 * @author christian
 */
public class ScrinchProjectsMultiviewDescription extends ScrinchMultiviewDescription {

    public ScrinchProjectsMultiviewDescription(ScrinchEnvToolkit toolkit){
        super(toolkit);
    }

    public String getDisplayName() {
        return "Projects";
    }

    public ScrinchTopComponent createScrinchElement() {
        return new ScrinchProjectsTopComponent();
    }

}