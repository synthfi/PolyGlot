/*
 * Copyright (c) 2015, draque
 * All rights reserved.
 *
 * Licensed under: Creative Commons Attribution-NonCommercial 4.0 International Public License
 *  See LICENSE.TXT included with this code to read the full license agreement.

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package PolyGlot;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * This form displays and allows editing of a chapter/section style grammar
 * text for Conlanging authors to use.
 * @author draque
 */
public class ScrGrammarGuide extends PFrame {
    private final DictCore core;
    private final String searchText; // TODO: Move to constants class
    private final String defName;// TODO: move to constants class
    private SoundRecorder soundRecorder;
    private boolean isUpdating;
    // TODO: allow import of audio wav files (how to autodetect audio format?)
    /**
     * Creates new form scrGrammarGuide
     * @param _core Dictionary core
     */
    public ScrGrammarGuide(DictCore _core) {
        // TODO: clean up this instantiation so all the object setup is in a called method
        this.isUpdating = false;
        searchText = "Search...";
        defName = "Name...";
        core = _core;
        
        initComponents();
        treChapList.setCellRenderer(new PGTreeCellRenderer());
        
        treChapList.requestFocus();
        
        txtSearch.setText(searchText);
        txtSearch.setForeground(Color.gray);
        txtName.setText(defName);
        txtName.setForeground(Color.gray);
        soundRecorder = new SoundRecorder();
        soundRecorder.setTimer(txtTimer);
        soundRecorder.setSlider(sldSoundPosition);
        txtSection.setStyledDocument(new PGDocument(core.getPropertiesManager().getFontCon()));
        treChapList.setRootVisible(false);
        txtTimer.setText("00:00:00");
        
        try {
            txtTimer.setFont(new IOHandler().getLcdFont().deriveFont(0, 18f));
        } catch (FontFormatException e) {
            InfoBox.error("Font Error", "Unable to load LCD font due to: " + e.getMessage(), this);
        } catch (IOException e) {
            InfoBox.error("Font Error", "Unable to load LCD font due to: " + e.getMessage(), this);
        }
        
        cmbFonts.addItem("NatLang Font");
        cmbFonts.addItem(core.getPropertiesManager().getFontCon().getName());
        
        setupListeners();
        setupKeyStrokes();
        populateSections();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        cmbFonts = new javax.swing.JComboBox();
        txtFontSize = new javax.swing.JTextField();
        cmbFontColor = new javax.swing.JComboBox();
        btnApply = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSection = new javax.swing.JTextPane();
        jPanel4 = new javax.swing.JPanel();
        sldSoundPosition = new javax.swing.JSlider();
        jToolBar2 = new javax.swing.JToolBar();
        jLabel2 = new javax.swing.JLabel();
        btnPlayPauseAudio = new javax.swing.JButton();
        btnRecordAudio = new javax.swing.JButton();
        txtTimer = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        treChapList = new javax.swing.JTree();
        txtSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnAddSection = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAddChapter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jSplitPane1.setDividerLocation(170);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        txtName.setToolTipText("Name of chapter/section");
        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNameFocusLost(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        cmbFonts.setToolTipText("Text font");
        cmbFonts.setMaximumSize(new java.awt.Dimension(120, 32767));
        jToolBar1.add(cmbFonts);

        txtFontSize.setText("12");
        txtFontSize.setToolTipText("Font Size");
        txtFontSize.setMaximumSize(new java.awt.Dimension(40, 20));
        jToolBar1.add(txtFontSize);

        cmbFontColor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "black", "red", "blue", "green", "gray", "yellow" }));
        cmbFontColor.setToolTipText("Font Color");
        cmbFontColor.setMaximumSize(new java.awt.Dimension(96, 20));
        jToolBar1.add(cmbFontColor);

        btnApply.setText("Apply");
        btnApply.setToolTipText("Switches currently selected text to current font style");
        btnApply.setFocusable(false);
        btnApply.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnApply.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });
        jToolBar1.add(btnApply);

        txtSection.setBorder(null);
        jScrollPane2.setViewportView(txtSection);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
        );

        jPanel4.setMaximumSize(new java.awt.Dimension(32767, 76));
        jPanel4.setMinimumSize(new java.awt.Dimension(100, 76));

        sldSoundPosition.setToolTipText("");
        sldSoundPosition.setValue(0);
        sldSoundPosition.setMinimumSize(new java.awt.Dimension(10, 29));

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        jLabel2.setText("Spoken Example  ");
        jToolBar2.add(jLabel2);

        btnPlayPauseAudio.setText("Play");
        btnPlayPauseAudio.setToolTipText("Plays spoken example of grammar, if it exists.");
        btnPlayPauseAudio.setFocusable(false);
        btnPlayPauseAudio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPlayPauseAudio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPlayPauseAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayPauseAudioActionPerformed(evt);
            }
        });
        jToolBar2.add(btnPlayPauseAudio);

        btnRecordAudio.setText("Record");
        btnRecordAudio.setToolTipText("Records spoken example of grammar (erases any current example)");
        btnRecordAudio.setFocusable(false);
        btnRecordAudio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRecordAudio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRecordAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecordAudioActionPerformed(evt);
            }
        });
        jToolBar2.add(btnRecordAudio);

        txtTimer.setEditable(false);
        txtTimer.setBackground(new java.awt.Color(0, 0, 0));
        txtTimer.setForeground(new java.awt.Color(51, 255, 51));
        txtTimer.setText("00:00:00");
        txtTimer.setToolTipText("Recording/play time");
        txtTimer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sldSoundPosition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(289, 289, 289)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sldSoundPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(350, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane1.setRightComponent(jPanel2);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("JTree");
        treChapList.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        treChapList.setToolTipText("Chapter Guide");
        jScrollPane1.setViewportView(treChapList);

        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });

        jLabel1.setText("Sections");

        btnAddSection.setText("+");
        btnAddSection.setToolTipText("Add a new section to a chapter");
        btnAddSection.setMaximumSize(new java.awt.Dimension(40, 29));
        btnAddSection.setMinimumSize(new java.awt.Dimension(40, 29));
        btnAddSection.setPreferredSize(new java.awt.Dimension(40, 29));
        btnAddSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSectionActionPerformed(evt);
            }
        });

        btnDelete.setToolTipText("Delete current chapter/node");
        btnDelete.setLabel("-");
        btnDelete.setMaximumSize(new java.awt.Dimension(40, 29));
        btnDelete.setMinimumSize(new java.awt.Dimension(40, 29));
        btnDelete.setOpaque(true);
        btnDelete.setPreferredSize(new java.awt.Dimension(40, 29));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAddChapter.setText("+Chap");
        btnAddChapter.setToolTipText("Create a new chapter");
        btnAddChapter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddChapterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtSearch)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnAddSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddChapter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddChapter)))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained
        txtSearch.setForeground(Color.black);
        if (txtSearch.getText().equals(searchText)) {
            txtSearch.setText("");
        }
    }//GEN-LAST:event_txtSearchFocusGained

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
        if (txtSearch.getText().equals("")) {
            txtSearch.setText(searchText);
            txtSearch.setForeground(Color.gray);
        }
    }//GEN-LAST:event_txtSearchFocusLost

    private void btnRecordAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecordAudioActionPerformed
        recordAudio();
    }//GEN-LAST:event_btnRecordAudioActionPerformed

    private void btnPlayPauseAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayPauseAudioActionPerformed
        playPauseAudio();
    }//GEN-LAST:event_btnPlayPauseAudioActionPerformed

    private void txtNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFocusGained
        if (txtName.getText().equals(defName)
                && !isUpdating) {
            txtName.setForeground(Color.black);
            txtName.setText("");
        }
    }//GEN-LAST:event_txtNameFocusGained

    private void txtNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFocusLost
        if (txtName.getText().isEmpty()
                && !isUpdating) {
            isUpdating = true;
            txtName.setForeground(Color.gray);
            txtName.setText(defName);
            isUpdating = false;
        }
    }//GEN-LAST:event_txtNameFocusLost

    private void btnAddChapterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddChapterActionPerformed
        addChapter();
    }//GEN-LAST:event_btnAddChapterActionPerformed

    private void btnAddSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSectionActionPerformed
        addSection();
    }//GEN-LAST:event_btnAddSectionActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteNode();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        setFont();
    }//GEN-LAST:event_btnApplyActionPerformed

    /**
     * Sets input font/font of selected text
     */
    private void setFont() {
        Font natFont = this.getFont();
        Font conFont = core.getPropertiesManager().getFontCon();
        SimpleAttributeSet aset = new SimpleAttributeSet();
        
        // natlang font is always 0, conlang font always 1
        if (cmbFonts.getSelectedIndex() == 0) {
            StyleConstants.setFontFamily(aset, natFont.getFamily());
        } else {
            StyleConstants.setFontFamily(aset, conFont.getFamily());
        }

        StyleConstants.setForeground(aset, 
                FormattedTextHelper.textToColor((String)cmbFontColor.getSelectedItem()));
        
        StyleConstants.setFontSize(aset, Integer.parseInt(txtFontSize.getText()));
        
        txtSection.setCharacterAttributes(aset, true);

        txtSection.requestFocus();
    }
    
    /**
     * sets up object listeners
     */
    private void setupListeners() {
        txtName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!isUpdating) {
                    updateName();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!isUpdating) {
                    updateName();
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!isUpdating) {
                    updateName();
                }
            }
        });
    }
    
    /**
     * Updates name from UI element of currently selected chapter or section
     */
    private void updateName() {
        boolean localUpdating = isUpdating;
        isUpdating = true;
        
        Object selection = treChapList.getLastSelectedPathComponent();
        
        if (selection instanceof GrammarSectionNode) {
            ((GrammarSectionNode)selection).setName(txtName.getText());
        } else if (selection instanceof GrammarChapNode) {
            ((GrammarChapNode)selection).setName(txtName.getText());
        }
        treChapList.repaint();
        isUpdating = localUpdating;
    }
    
    /**
     * Stops all recording and play streams
     */
    // TODO: make sure this is called before populateProperties when tree value changes
    private void closeAllPlayRecord() {
        try {
            if (soundRecorder.isPlaying()) {
                soundRecorder.playPause();
            }
            if (soundRecorder.isRecording()) {
                soundRecorder.endRecording();
            }
        } catch (LineUnavailableException e) {
            // on exception, inform user and replace sound recorder
            soundRecorder = new SoundRecorder();
            InfoBox.error("Recorder Error", "Unable to end audio stream: " + e.getLocalizedMessage(), this);
            //e.printStackTrace();
        } catch (IOException e) {
            // on exception, inform user and replace sound recorder
            soundRecorder = new SoundRecorder();
            InfoBox.error("Recorder Error", "Unable to end audio stream: " + e.getLocalizedMessage(), this);
            //e.printStackTrace();
        }
    }
    
    /**
     * Populates properties of chapter/section and sets appropriate controls
     */
    private void populateProperties() {
        if (isUpdating) {
            return;
        }
        
        isUpdating = true;
        
        Object selection = treChapList.getLastSelectedPathComponent();
        if (selection instanceof GrammarChapNode) {
            GrammarChapNode chapNode = (GrammarChapNode)selection;
            txtName.setText(chapNode.getName());
            txtSection.setText("");
            txtSection.setEnabled(false);
            btnApply.setEnabled(false);
            txtFontSize.setEnabled(false);
            cmbFontColor.setEnabled(false);
            cmbFonts.setEnabled(false);
            btnPlayPauseAudio.setEnabled(false);
            btnRecordAudio.setEnabled(false);
            sldSoundPosition.setValue(0);
            sldSoundPosition.setEnabled(false);
        } if (selection instanceof GrammarSectionNode) {
            GrammarSectionNode secNode = (GrammarSectionNode)selection;
            txtName.setText(secNode.getName());
            txtSection.setEnabled(true);
            btnApply.setEnabled(true);
            txtFontSize.setEnabled(true);
            cmbFontColor.setEnabled(true);
            cmbFonts.setEnabled(true);
            btnPlayPauseAudio.setEnabled(true);
            btnRecordAudio.setEnabled(true);
            sldSoundPosition.setValue(0);
            sldSoundPosition.setEnabled(true);
            try {
                FormattedTextHelper.restoreFromString(secNode.getSectionText(), 
                        txtSection);
            } catch (BadLocationException e) {
                InfoBox.error("Section Load Error", "Unable to load section text: "
                        + e.getLocalizedMessage(), this);
                //e.printStackTrace();
            }
        }
        
        isUpdating = false;
    }
    
    private void deleteNode() {
        // TODO: add confirmation
        Object selection = treChapList.getLastSelectedPathComponent();
        DefaultTreeModel model = (DefaultTreeModel)treChapList.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
            
        if (selection == null) {
            return;
        }
        
        if (selection instanceof GrammarSectionNode) {
            GrammarSectionNode curNode = (GrammarSectionNode)selection;
            GrammarChapNode parent = (GrammarChapNode)curNode.getParent();
            parent.remove(curNode);
            treChapList.expandPath(new TreePath(model.getPathToRoot(parent)));
            treChapList.setSelectionPath(new TreePath(model.getPathToRoot(parent)));
        } else if (selection instanceof GrammarChapNode){
            root.remove((GrammarChapNode)selection);
            core.getGrammarManager().removeChapter((GrammarChapNode)selection);
        }
        
        model.reload(root);
    }
    
    private void addChapter() {
        DefaultTreeModel model = (DefaultTreeModel)treChapList.getModel();
        Object selection = treChapList.getLastSelectedPathComponent();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
        GrammarChapNode newNode = new GrammarChapNode();
        newNode.setName("NEW CHAPTER");
        
        if (selection instanceof GrammarSectionNode) {
            GrammarChapNode parent = (GrammarChapNode)((GrammarSectionNode)selection).getParent();
            int index = root.getIndex(parent);
            model.insertNodeInto(newNode, root, index + 1);
            core.getGrammarManager().addChapterAtIndex(newNode, index + 1);
        } else if (selection instanceof GrammarChapNode) {
            int index = root.getIndex((GrammarChapNode)selection);
            model.insertNodeInto(newNode, root, index + 1);
            core.getGrammarManager().addChapterAtIndex(newNode, index + 1);
        } else {           
            root.add(newNode);
            core.getGrammarManager().addChapter(newNode);
        }
        
        model.reload();
        treChapList.setSelectionPath(new TreePath(model.getPathToRoot(newNode)));
    }
    
    private void addSection() {
        DefaultTreeModel model = (DefaultTreeModel)treChapList.getModel();
        Object selection = treChapList.getLastSelectedPathComponent();
        
        if (selection instanceof GrammarSectionNode) {
            GrammarChapNode parent = (GrammarChapNode)((GrammarSectionNode)selection).getParent();
            int index = parent.getIndex((GrammarSectionNode)selection);
            GrammarSectionNode newNode = core.getGrammarManager().getNewSection();
            newNode.setName("NEW SECTION");
            model.insertNodeInto(newNode, parent, index + 1);
            parent.addSectionAtIndex(newNode, index + 1);
            model.reload();
            treChapList.setSelectionPath(new TreePath(model.getPathToRoot(newNode)));
        } else if (selection instanceof GrammarChapNode) {
            GrammarChapNode parent = (GrammarChapNode)selection;
            GrammarSectionNode newNode = core.getGrammarManager().getNewSection();
            newNode.setName("NEW SECTION");
            parent.add(newNode);
            model.reload();
            treChapList.setSelectionPath(new TreePath(model.getPathToRoot(newNode)));
        } else {
            InfoBox.warning("Section Creation", "Select a chapter in which to create a section.", this);
        }
    }
    
    private void playPauseAudio() {
        try {
            soundRecorder.playPause();
        } catch (LineUnavailableException e) {
            InfoBox.error("Play Error", "Unable to play due to: " + e.getLocalizedMessage(), this);
            //e.printStackTrace();
        } catch (IOException e) {
            InfoBox.error("Play Error", "Unable to play due to: " + e.getLocalizedMessage(), this);
            //e.printStackTrace();
        }
        // TODO: make buttons animate to proper state
    }
    
    private void recordAudio() {
        try {
            if (soundRecorder.isRecording()) {
                soundRecorder.endRecording();
            } else {
                if (soundRecorder.getSound() != null) { // confirm overwrite of existing data
                    if (InfoBox.yesNoCancel("Overwrite Confirmation", 
                            "Discard existing audio recording?", this) != JOptionPane.YES_OPTION) {
                        return;
                    }
                }
                
                soundRecorder.beginRecording();
            }
        } catch (Exception e) {
            InfoBox.error("Recording Error", "Unable to record due to: " + e.getLocalizedMessage(), this);
            //e.printStackTrace();
        }
        // TODO: make buttons animate to proper state
    }
    // TODO: delete main method
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ScrGrammarGuide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScrGrammarGuide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScrGrammarGuide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScrGrammarGuide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScrGrammarGuide(new DictCore()).setVisible(true);
            }
        });
    }
    
    public static ScrGrammarGuide run(DictCore _core) {
        final ScrGrammarGuide s = new ScrGrammarGuide(_core);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                s.setVisible(true);
            }
        });

        return s;
    }
    
    private void populateSections() {
        // TODO: Set proper icons
        List<GrammarChapNode> chapters = core.getGrammarManager().getChapters();
        Iterator<GrammarChapNode> chapIt = chapters.iterator();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root Node");
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        treChapList.setModel(treeModel);
        
        while (chapIt.hasNext()) {
            GrammarChapNode curChap = chapIt.next();
            rootNode.add(curChap);
            
            List<GrammarSectionNode> sections = curChap.getSections();
            Iterator<GrammarSectionNode> secIt = sections.iterator();
            
            while (secIt.hasNext()) {
                GrammarSectionNode curSec = secIt.next();
                curChap.add(curSec);
            }
        }
        
        treeModel.reload(rootNode);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddChapter;
    private javax.swing.JButton btnAddSection;
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPlayPauseAudio;
    private javax.swing.JButton btnRecordAudio;
    private javax.swing.JComboBox cmbFontColor;
    private javax.swing.JComboBox cmbFonts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JSlider sldSoundPosition;
    private javax.swing.JTree treChapList;
    private javax.swing.JTextField txtFontSize;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextPane txtSection;
    private javax.swing.JTextField txtTimer;
    // End of variables declaration//GEN-END:variables
}
