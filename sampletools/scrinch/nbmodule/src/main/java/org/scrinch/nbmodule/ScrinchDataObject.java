/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrinch.nbmodule;

import java.io.OutputStream;
import java.util.List;
import org.openide.cookies.SaveCookie;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.CookieSet;
import org.openide.nodes.Node;
import org.openide.nodes.Children;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.scrinch.model.ScrinchEnvToolkit;

public class ScrinchDataObject extends MultiDataObject implements SaveCookie{

    private ScrinchEnvToolkit data;
    
    private class ScrinchChildFactory extends ChildFactory<String> {

        public ScrinchChildFactory() {
        }

        @Override
        protected boolean createKeys(List list) {
            list.add("Preferences");
            list.add("Projects");
            list.add("Sprints");
            list.add("Search");
            list.add("Members");
            list.add("Targets");
            return true;
        }

        @Override
        protected Node createNodeForKey(String key) {
            Node childNode = new AbstractNode(Children.LEAF);
            childNode.setDisplayName(key);
            return childNode;
        }
    }

    public ScrinchDataObject(final FileObject primaryFile, MultiFileLoader loader) throws DataObjectExistsException {
        super(primaryFile, loader);
        CookieSet cookies = getCookieSet();
        cookies.add((Node.Cookie) new ScrinchOpenSupport(getPrimaryEntry()));
    }
    
    public ScrinchEnvToolkit getData(){
        return this.data;
    }
    
    public ScrinchEnvToolkit open(){
        if( this.data==null ){
            this.data = load();
            this.data.addListener(new ScrinchEnvToolkit.Listener() {

                public void preferencesChanged() {
                    dataChanged();
                }

                public void dataLoaded() {
                }

                public void dataChanged() {
                    if( !isModified() ){
                        System.out.println("Scrinch data has changed");
                        //Exceptions.printStackTrace(new Exception());
                        CookieSet cookies = getCookieSet();
                        cookies.add(ScrinchDataObject.this);
                        setModified(true);
                    }
                }
            });
        }
        return this.data;
    }

    public void save() {
        FileObject primaryFile = getPrimaryFile();
        try {
            OutputStream out = primaryFile.getOutputStream();
            try {
                data.save(out);
            } finally {
                out.close();
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private ScrinchEnvToolkit load() {
        FileObject primaryFile = getPrimaryFile();
        try {
            ScrinchEnvToolkit.initDefaultLogger();
            ScrinchEnvToolkit toolkit = new ScrinchEnvToolkit();
            toolkit.open(primaryFile.asBytes());
            return toolkit;
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
            throw new RuntimeException("Unable to open file: " + primaryFile.getPath());
        }
    }

    @Override
    protected Node createNodeDelegate() {
        return new DataNode(this, Children.create(new ScrinchChildFactory(), true), getLookup());
    }

    @Override
    public Lookup getLookup() {
        return getCookieSet().getLookup();
    }
}
