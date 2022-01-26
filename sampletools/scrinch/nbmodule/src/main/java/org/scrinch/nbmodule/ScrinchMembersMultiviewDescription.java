package org.scrinch.nbmodule;

import org.scrinch.model.ScrinchEnvToolkit;

/**
 *
 * @author christian
 */
public class ScrinchMembersMultiviewDescription extends ScrinchMultiviewDescription {

    public ScrinchMembersMultiviewDescription(ScrinchEnvToolkit toolkit){
        super(toolkit);;
    }

    public String getDisplayName() {
        return "Members";
    }

    public ScrinchTopComponent createScrinchElement() {
        return new ScrinchMembersTopComponent();
    }

}