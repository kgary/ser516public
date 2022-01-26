package org.scrinch.nbmodule;

import org.scrinch.model.ScrinchEnvToolkit;

/**
 *
 * @author christian
 */
public class ScrinchGlobalReportMultiviewDescription extends ScrinchMultiviewDescription {

    public ScrinchGlobalReportMultiviewDescription(ScrinchEnvToolkit toolkit){
        super(toolkit);
    }

    public String getDisplayName() {
        return "Global Report";
    }

    public ScrinchTopComponent createScrinchElement() {
        return new ScrinchGlobalReportTopComponent();
    }

}