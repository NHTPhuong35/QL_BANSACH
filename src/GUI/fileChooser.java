/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileView;

public class fileChooser extends JFileChooser {

    private static final int ICON_SIZE = 60;
    private static final Image LOADING_IMAGE = new BufferedImage(ICON_SIZE, ICON_SIZE, BufferedImage.TYPE_INT_ARGB);
    private final Pattern imageFIlePattern = Pattern.compile(".+?\\.(png|jpe?g|gif|tiff?)$", Pattern.CASE_INSENSITIVE);
    private final Map imagseCache = new WeakHashMap();

    public fileChooser() {
        super();
    }

    public fileChooser(String src) {
        super(src);
    }
    
    {
        setFileView(new View());
    }

    private class View extends FileView {

        private final ExecutorService executor = Executors.newCachedThreadPool();

        public Icon getIcon(File file) {
            if (!imageFIlePattern.matcher(file.getName()).matches()) {
                return null;
            }
            synchronized (imagseCache) {
                ImageIcon icon = (ImageIcon) imagseCache.get(file);
                if (icon == null) {
                    icon = new ImageIcon(LOADING_IMAGE);
                    imagseCache.put(file, icon);
                    executor.submit(new ThumbnailIconLoader(icon, file));
                }
                return icon;
            }
        }
    }

    private class ThumbnailIconLoader implements Runnable {

        private final ImageIcon icon;
        private final File file;

        public ThumbnailIconLoader(ImageIcon icon, File file) {
            this.icon = icon;
            this.file = file;
        }

        @Override
        public void run() {
            ImageIcon newIcon = new ImageIcon(file.getAbsolutePath());
            Image img = newIcon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
            icon.setImage(img);

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    repaint();
                }
            });
        }

    }
}

