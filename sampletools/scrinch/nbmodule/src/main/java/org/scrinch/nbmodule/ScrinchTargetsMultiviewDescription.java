package org.scrinch.nbmodule;

import org.netbeans.core.spi.multiview.MultiViewElement;
import org.scrinch.model.ScrinchEnvToolkit;

/**
 *
 * @author christian
 */
public class ScrinchTargetsMultiviewDescription extends ScrinchMultiviewDescription {

    public ScrinchTargetsMultiviewDescription(ScrinchEnvToolkit toolkit){
        super(toolkit);
    }

    public String getDisplayName() {
        return "Targets";
    }

    @Override
    public ScrinchTopComponent createScrinchElement() {
        return new ScrinchTargetsTopComponent();
    }

}