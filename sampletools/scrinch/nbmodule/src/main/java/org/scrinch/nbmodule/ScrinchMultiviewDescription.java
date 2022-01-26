/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.scrinch.nbmodule;

import java.awt.Image;
import java.io.Serializable;
import org.netbeans.core.spi.multiview.MultiViewDescription;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.openide.util.HelpCtx;
import org.openide.util.ImageUtilities;
import org.openide.windows.TopComponent;
import org.scrinch.model.ScrinchEnvToolkit;

/**
 *
 * @author christian
 */
public abstract class ScrinchMultiviewDescription implements MultiViewDescription, Serializable {

    protected final transient ScrinchEnvToolkit toolkit;
    
    protected ScrinchMultiviewDescription(ScrinchEnvToolkit toolkit){
        this.toolkit = toolkit;
    }

    public final int getPersistenceType() {
        return TopComponent.PERSISTENCE_NEVER;
    }

    public final Image getIcon() {
        return ImageUtilities.loadImage("/org/scrinch/nbmodule/icon16x16.png");
    }

    public final HelpCtx getHelpCtx() {
        return null;
    }

    public final String preferredID() {
       return getClass().getSimpleName();
    }

    public abstract ScrinchTopComponent createScrinchElement();

    public final MultiViewElement createElement() {
        ScrinchTopComponent projects = createScrinchElement();
        projects.loadContent(toolkit);
        return projects;
    }

}
