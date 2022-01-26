/*
Scrinch is a stand-alone Swing application that helps managing your
projects based on Agile principles.
Copyright (C) 2007  Julien Piaser, Jerome Layat, Peter Fluekiger,
Christian Lebaudy, Jean-Marc Borer

Scrinch is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Scrinch is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.scrinch.pdf;

import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.scrinch.Main;
import org.scrinch.model.ScrinchEnvToolkit;

public abstract class PDFExport {

    public abstract void draw(PdfContentByte cb, Graphics2D g, Rectangle2D r)throws Exception;
    
    public static void exportToPDF(String fileName, Component parent, PDFExport exporter){
        try{
            String lastExportDir = Main.getUserProperty(Main.LAST_EXPORT_DIR);
            JFileChooser fileChooser = new JFileChooser(lastExportDir);
            File defaultFile = new File(lastExportDir
                                        + File.separator 
                                        + fileName);
            fileChooser.setSelectedFile(defaultFile);        
            if(JFileChooser.APPROVE_OPTION==fileChooser.showSaveDialog(parent)){
                File fileToWrite = fileChooser.getSelectedFile().getCanonicalFile();
                Main.setUserProperty(Main.LAST_EXPORT_DIR, fileToWrite.getParent());
                if( fileToWrite.exists() ){
                    int r = JOptionPane.showConfirmDialog(parent, "File "+fileToWrite+" already exists, overwrite ?",
                        "Overwrite confirmation", JOptionPane.YES_NO_OPTION);
                    if( r==JOptionPane.NO_OPTION ){
                        return;
                    }
                }
                
                Rectangle pageRect = PageSize.A4.rotate();
                int margin = 10;
                com.lowagie.text.Document iTextDocument =
                    new com.lowagie.text.Document(pageRect, margin, margin, margin, margin);
                PdfWriter writer = PdfWriter.getInstance(iTextDocument,
                                                         new FileOutputStream(fileToWrite));
                iTextDocument.open();
                PdfContentByte cb = writer.getDirectContent();
                int width =  (int) (pageRect.getWidth() - (2.0f*margin));
                int height = (int) (pageRect.getHeight() - (2.0f*margin));

                PdfTemplate tp = cb.createTemplate(width, height);
                Graphics2D g2 = tp.createGraphics(width, height, new DefaultFontMapper());
                g2.setColor(new Color(240, 240, 240));
                g2.fillRect(0, 0, width, height);
                g2.setColor(Color.BLACK);
                g2.drawRect(0, 0, width, height);

                Rectangle2D rectangle = new Rectangle2D.Double(0, 0, width, height);
                exporter.draw(cb, g2, rectangle);

                g2.dispose();
                cb.addTemplate(tp, margin, margin);

                iTextDocument.close();

                org.scrinch.ostools.Runtime.pdfOpen(fileToWrite.getPath());
            }
        }catch(Exception e){
            String message = "Error exporting pdf file "+fileName;
            ScrinchEnvToolkit.logger.log(Level.WARNING, message, e);
            JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}
