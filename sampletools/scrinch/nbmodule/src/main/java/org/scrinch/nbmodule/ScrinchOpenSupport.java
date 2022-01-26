package org.scrinch.nbmodule;

import org.netbeans.core.spi.multiview.MultiViewDescription;
import org.netbeans.core.spi.multiview.MultiViewFactory;
import org.openide.cookies.CloseCookie;
import org.openide.cookies.OpenCookie;
import org.openide.loaders.OpenSupport;
import org.openide.windows.CloneableTopComponent;
import org.scrinch.model.ScrinchEnvToolkit;

/**
 *
 * @author christian
 */
public class ScrinchOpenSupport extends OpenSupport implements OpenCookie, CloseCookie {
    
    public ScrinchOpenSupport(ScrinchDataObject.Entry entry) {
        super(entry);
    }

    protected CloneableTopComponent createCloneableTopComponent() {
        System.out.println("creating top component");
        ScrinchDataObject dataObject = (ScrinchDataObject) entry.getDataObject();
        ScrinchEnvToolkit toolkit = dataObject.getData();
        // Create an array of multiview descriptors:
        MultiViewDescription[] viewDescriptions = {
            new ScrinchPreferencesMultiviewDescription(toolkit),
            new ScrinchProjectsMultiviewDescription(toolkit),
            new ScrinchSprintsMultiviewDescription(toolkit),
            new ScrinchSearchMultiviewDescription(toolkit),
            new ScrinchMembersMultiviewDescription(toolkit),
            new ScrinchTargetsMultiviewDescription(toolkit),
            new ScrinchGlobalReportMultiviewDescription(toolkit),
            new ScrinchVelocityHistoryMultiviewDescription(toolkit),
            new ScrinchWorkloadGraphMultiviewDescription(toolkit)
        };

        // Create the multiview window:
        CloneableTopComponent tc =
                MultiViewFactory.createCloneableMultiView(viewDescriptions,
                viewDescriptions[2], null);
        tc.setDisplayName(entry.getDataObject().getName());
        return tc;
    }

    @Override
    public void open() {
        ScrinchDataObject dataObject = (ScrinchDataObject) entry.getDataObject();
        ScrinchEnvToolkit toolkit = dataObject.open();
        toolkit.setUseItemSetScrollPane(false);
        System.out.println("Opening the file: " + entry.getFile().getPath());
        super.open();
    }

    @Override
    public boolean close() {
        System.out.println("Closing the file: " + entry.getFile().getPath());
        return super.close();
    }
    

}
