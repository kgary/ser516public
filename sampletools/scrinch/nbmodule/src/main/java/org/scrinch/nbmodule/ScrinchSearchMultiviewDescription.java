package org.scrinch.nbmodule;

import org.netbeans.core.spi.multiview.MultiViewElement;
import org.scrinch.model.ScrinchEnvToolkit;

/**
 *
 * @author christian
 */
public class ScrinchSearchMultiviewDescription extends ScrinchMultiviewDescription {

    public ScrinchSearchMultiviewDescription(ScrinchEnvToolkit toolkit){
        super(toolkit);
    }

    public String getDisplayName() {
        return "Search";
    }

    public ScrinchTopComponent createScrinchElement() {
        return new ScrinchSearchTopComponent();
    }

}