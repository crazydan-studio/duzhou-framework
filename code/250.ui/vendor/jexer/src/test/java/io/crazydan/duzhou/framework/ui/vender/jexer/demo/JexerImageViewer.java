/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的应用生产平台
 * Copyright (C) 2025 Crazydan Studio <https://studio.crazydan.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.
 * If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html#license-text>.
 */

package io.crazydan.duzhou.framework.ui.vender.jexer.demo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import io.crazydan.duzhou.framework.ui.vendor.jexer.JexerApp;
import jexer.TAction;
import jexer.TApplication;
import jexer.TDesktop;
import jexer.TDirectoryList;
import jexer.TImage;
import jexer.backend.SwingTerminal;
import jexer.bits.CellAttributes;
import jexer.bits.GraphicsChars;
import jexer.event.TKeypressEvent;
import jexer.event.TResizeEvent;
import jexer.menu.TMenu;
import jexer.ttree.TDirectoryTreeItem;
import jexer.ttree.TTreeItem;
import jexer.ttree.TTreeViewWidget;

import static jexer.TKeypress.kbDown;
import static jexer.TKeypress.kbEnd;
import static jexer.TKeypress.kbEnter;
import static jexer.TKeypress.kbHome;
import static jexer.TKeypress.kbPgDn;
import static jexer.TKeypress.kbPgUp;
import static jexer.TKeypress.kbUp;

/**
 * Implements a simple image thumbnail file viewer.  Much of this code was
 * stripped down from TFileOpenBox.
 * <br/><br/>
 * source from https://gitlab.com/AutumnMeowMeow/jexer/blob/master/examples/JexerImageViewer.java
 */
public class JexerImageViewer extends JexerApp {

    /**
     * Main entry point.
     */
    public static void main(String[] args) throws Exception {
        JexerImageViewer app = new JexerImageViewer();
        (new Thread(app)).start();
    }

    /**
     * Public constructor chooses the ECMA-48 / Xterm backend.
     */
    public JexerImageViewer() throws Exception {
        super();

        // The stock tool menu has items for redrawing the screen, opening
        // images, and (when using the Swing backend) setting the font.
        addToolMenu();

        // We will have one menu containing a mix of new and stock commands
        TMenu fileMenu = addMenu("&File");

        // Stock commands: a new shell, exit program.
        fileMenu.addDefaultItem(TMenu.MID_SHELL);
        fileMenu.addSeparator();
        fileMenu.addDefaultItem(TMenu.MID_EXIT);

//        setHideMenuBar(true);

        // Filter the files list to support image suffixes only.
        List<String> filters = new ArrayList<String>();
        filters.add("^.*\\.[Jj][Pp][Gg]$");
        filters.add("^.*\\.[Jj][Pp][Ee][Gg]$");
        filters.add("^.*\\.[Pp][Nn][Gg]$");
        filters.add("^.*\\.[Gg][Ii][Ff]$");
        filters.add("^.*\\.[Bb][Mm][Pp]$");
        setDesktop(new ImageViewerDesktop(this, ".", filters));
    }

    /**
     * The desktop contains a tree view on the left, list of files on the top
     * right, and image view on the bottom right.
     */
    static class ImageViewerDesktop extends TDesktop {

        /**
         * The left-side tree view pane.
         */
        private final TTreeViewWidget treeView;
        /**
         * The top-right-side directory list pane.
         */
        private final TDirectoryList directoryList;
        /**
         * The data behind treeView.
         */
        private final TDirectoryTreeItem treeViewRoot;
        /**
         * The bottom-right-side image pane.
         */
        private TImage imageWidget;

        /**
         * Public constructor.
         *
         * @param application
         *         the TApplication that manages this window
         * @param path
         *         path of selected file
         * @param filters
         *         a list of strings that files must match to be displayed
         * @throws IOException
         *         of a java.io operation throws
         */
        public ImageViewerDesktop(final TApplication application, final String path, final List<String> filters)
                throws IOException {

            super(application);
            setActive(true);

            // Add directory treeView
            this.treeView = addTreeViewWidget(0, 0, getWidth() / 2, getHeight(), new TAction() {
                public void DO() {
                    TTreeItem item = ImageViewerDesktop.this.treeView.getSelected();
                    File selectedDir = ((TDirectoryTreeItem) item).getFile();
                    try {
                        ImageViewerDesktop.this.directoryList.setPath(selectedDir.getCanonicalPath());
                        if (!ImageViewerDesktop.this.directoryList.getList().isEmpty()) {
                            setThumbnail(ImageViewerDesktop.this.directoryList.getPath());
                        } else {
                            if (ImageViewerDesktop.this.imageWidget != null) {
                                getChildren().remove(ImageViewerDesktop.this.imageWidget);
                            }
                            ImageViewerDesktop.this.imageWidget = null;
                        }
                        activate(ImageViewerDesktop.this.treeView);
                    } catch (IOException e) {
                        // If the backend is Swing, we can emit the stack
                        // trace to stderr.  Otherwise, just squash it.
                        if (getScreen() instanceof SwingTerminal) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            this.treeViewRoot = new TDirectoryTreeItem(this.treeView, path, true);

            // Add directory files list
            this.directoryList = addDirectoryList(path, getWidth() / 2 + 1, 0, getWidth() / 2 - 1, getHeight() / 2,

                                                  new TAction() {
                                                      public void DO() {
                                                          setThumbnail(ImageViewerDesktop.this.directoryList.getPath());
                                                      }
                                                  }, new TAction() {

                        public void DO() {
                            setThumbnail(ImageViewerDesktop.this.directoryList.getPath());
                        }
                    }, filters);

            if (!this.directoryList.getList().isEmpty()) {
                activate(this.directoryList);
                setThumbnail(this.directoryList.getPath());
            } else {
                activate(this.treeView);
            }
        }

        /**
         * Handle window/screen resize events.
         *
         * @param event
         *         resize event
         */
        @Override
        public void onResize(final TResizeEvent event) {

            // Resize the tree and list
            this.treeView.setY(1);
            this.treeView.setWidth(getWidth() / 2);
            this.treeView.setHeight(getHeight() - 1);
            this.treeView.onResize(new TResizeEvent(event.getBackend(),
                                                    TResizeEvent.Type.WIDGET,
                                                    this.treeView.getWidth(),
                                                    this.treeView.getHeight()));
            this.treeView.getTreeView()
                         .onResize(new TResizeEvent(event.getBackend(),
                                                    TResizeEvent.Type.WIDGET,
                                                    this.treeView.getWidth() - 1,
                                                    this.treeView.getHeight() - 1));
            this.directoryList.setX(getWidth() / 2 + 1);
            this.directoryList.setY(1);
            this.directoryList.setWidth(getWidth() / 2 - 1);
            this.directoryList.setHeight(getHeight() / 2 - 1);
            this.directoryList.onResize(new TResizeEvent(event.getBackend(),
                                                         TResizeEvent.Type.WIDGET,
                                                         this.directoryList.getWidth(),
                                                         this.directoryList.getHeight()));

            // Recreate the image
            if (this.imageWidget != null) {
                getChildren().remove(this.imageWidget);
            }
            this.imageWidget = null;
            if (!this.directoryList.getList().isEmpty()) {
                activate(this.directoryList);
                setThumbnail(this.directoryList.getPath());
            } else {
                activate(this.treeView);
            }
        }

        /**
         * Handle keystrokes.
         *
         * @param keypress
         *         keystroke event
         */
        @Override
        public void onKeypress(final TKeypressEvent keypress) {

            if (this.treeView.isActive() || this.directoryList.isActive()) {
                if ((keypress.equals(kbEnter))
                    || (keypress.equals(kbUp))
                    || (keypress.equals(kbDown))
                    || (keypress.equals(kbPgUp))
                    || (keypress.equals(kbPgDn))
                    || (keypress.equals(kbHome))
                    || (keypress.equals(kbEnd))) {
                    // Tree view will be changing, update the directory list.
                    super.onKeypress(keypress);

                    // This is the same action as treeView's enter.
                    TTreeItem item = this.treeView.getSelected();
                    File selectedDir = ((TDirectoryTreeItem) item).getFile();
                    try {
                        if (this.treeView.isActive()) {
                            this.directoryList.setPath(selectedDir.getCanonicalPath());
                        }
                        if (!this.directoryList.getList().isEmpty()) {
                            activate(this.directoryList);
                            setThumbnail(this.directoryList.getPath());
                        } else {
                            if (this.imageWidget != null) {
                                getChildren().remove(this.imageWidget);
                            }
                            this.imageWidget = null;
                            activate(this.treeView);
                        }
                    } catch (IOException e) {
                        // If the backend is Swing, we can emit the stack trace
                        // to stderr.  Otherwise, just squash it.
                        if (getScreen() instanceof SwingTerminal) {
                            e.printStackTrace();
                        }
                    }
                    return;
                }
            }

            // Pass to my parent
            super.onKeypress(keypress);
        }

        /**
         * Draw me on screen.
         */
        @Override
        public void draw() {
            CellAttributes background = getTheme().getColor("tdesktop.background");
            putAll(' ', background);

            vLineXY(getWidth() / 2, 0, getHeight(), GraphicsChars.WINDOW_SIDE, getBackground());

            hLineXY(getWidth() / 2, getHeight() / 2, (getWidth() + 1) / 2, GraphicsChars.WINDOW_TOP, getBackground());

            putCharXY(getWidth() / 2, getHeight() / 2, GraphicsChars.WINDOW_LEFT_TEE, getBackground());
        }

        /**
         * Set the image thumbnail.
         *
         * @param file
         *         the image file
         */
        private void setThumbnail(final File file) {
            if (file == null) {
                return;
            }
            if (!file.exists() || !file.isFile()) {
                return;
            }

            BufferedImage image = null;
            try {
                image = ImageIO.read(file);
            } catch (IOException e) {
                // If the backend is Swing, we can emit the stack trace to
                // stderr.  Otherwise, just squash it.
                if (getScreen() instanceof SwingTerminal) {
                    e.printStackTrace();
                }
                return;
            }

            if (this.imageWidget != null) {
                getChildren().remove(this.imageWidget);
            }
            int width = getWidth() / 2 - 1;
            int height = getHeight() / 2 - 1;

            this.imageWidget = new TImage(this,
                                          getWidth() - width,
                                          getHeight() - height,
                                          width,
                                          height,
                                          image,
                                          0,
                                          0,
                                          null);

            // Resize the image to fit within the pane.
            this.imageWidget.setScaleType(TImage.Scale.SCALE);

            this.imageWidget.setActive(false);
            activate(this.directoryList);
        }

    }

}
