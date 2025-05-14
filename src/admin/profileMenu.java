/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import careq.logIn;
import config.session;
import gfx.RoundedPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import patient.changePassForm;
import config.connectDB;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import patient.patientProfileMenu;

/**
 *
 * @author Administrator
 */
public class profileMenu extends javax.swing.JFrame {

    Color mainColor = new Color(37, 171, 241);
    Color hoverColor = new Color(31, 128, 179);

    Color logoutColor = new Color(100, 188, 234);
    Color hoverlogoutColor = new Color(250, 249, 246);

    public profileMenu() {
        initComponents();
        accountInformation();
        loadOpenSans();
    }

    private void accountInformation() {

        try {
            session sess = session.getInstance();
            connectDB conn = new connectDB();

            String fullName = sess.getFirstName() + " " + sess.getLastName();

            name.setText(fullName);

            email.setText("" + sess.getEmail());

            contact.setText("" + sess.getContact());

            password.setText("•••••••••••");

            String query = "SELECT u_pfp FROM user WHERE u_id = ?";
            PreparedStatement pstmt = conn.getConnection().prepareStatement(query);
            pstmt.setString(1, sess.getU_id());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String pfpPath = rs.getString("u_pfp");

                if (pfpPath == null || pfpPath.trim().isEmpty()) {
                    
                    String blankPfpPath = "src/img/blankpfp.jpg";
                    
                    pfp.setIcon(ResizeImage(blankPfpPath, null, pfp));
                } else {
                    pfp.setIcon(ResizeImage(pfpPath, null, pfp));
                    oldpath = pfpPath;
                    path = pfpPath;
                    destination = pfpPath;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(patientProfileMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainbg = new javax.swing.JPanel();
        dboard = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        dboardBG = new RoundedPanel(15);
        contact = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        password = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pfp = new javax.swing.JLabel();
        addpfp = new gfx.RoundGradientButton ("Refresh", new Color(25, 175, 219), new Color(21, 162, 203), 30);
        removepfp = new gfx.RoundGradientButton ("Delete", new Color(220, 53, 69), new Color(255, 0, 0), 30);
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel1 = new RoundedPanel(5);
        doctorPanel = new RoundedPanel(50);
        jLabel9 = new javax.swing.JLabel();
        doctor1 = new javax.swing.JLabel();
        patientPanel = new RoundedPanel(50);
        jLabel15 = new javax.swing.JLabel();
        patient1 = new javax.swing.JLabel();
        dashboardPanel = new RoundedPanel(50);
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        logoutPanel = new RoundedPanel(25);
        jLabel6 = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        profilePanel = new RoundedPanel(50);
        jLabel16 = new javax.swing.JLabel();
        profile = new javax.swing.JLabel();
        accMngrPanel = new RoundedPanel(50);
        jLabel17 = new javax.swing.JLabel();
        accM = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        logsPanel = new RoundedPanel(50);
        jLabel19 = new javax.swing.JLabel();
        profile1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainbg.setBackground(new java.awt.Color(245, 245, 245));
        mainbg.setMinimumSize(new java.awt.Dimension(1017, 620));
        mainbg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(250, 249, 246));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Admin Profile");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 300, -1));

        dboard.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 70));

        dboardBG.setBackground(new java.awt.Color(250, 249, 246));
        dboardBG.setLayout(null);

        contact.setText("contact");
        dboardBG.add(contact);
        contact.setBounds(50, 480, 280, 30);

        name.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setText("name");
        dboardBG.add(name);
        name.setBounds(139, 245, 536, 30);

        email.setText("email");
        dboardBG.add(email);
        email.setBounds(50, 410, 290, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 255));
        jLabel10.setText("Edit");
        dboardBG.add(jLabel10);
        jLabel10.setBounds(810, 420, 40, 15);

        password.setText("password");
        dboardBG.add(password);
        password.setBounds(50, 560, 200, 14);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 255));
        jLabel11.setText("Edit");
        dboardBG.add(jLabel11);
        jLabel11.setBounds(810, 490, 40, 15);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 255));
        jLabel12.setText("Edit");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        dboardBG.add(jLabel12);
        jLabel12.setBounds(810, 560, 40, 15);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Personal Information");
        dboardBG.add(jLabel13);
        jLabel13.setBounds(30, 340, 230, 22);
        dboardBG.add(pfp);
        pfp.setBounds(315, 29, 195, 210);

        addpfp.setForeground(new java.awt.Color(255, 255, 255));
        addpfp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        addpfp.setText("Add Profile Picture");
        addpfp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        addpfp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addpfpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addpfpMouseExited(evt);
            }
        });
        addpfp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addpfpActionPerformed(evt);
            }
        });
        dboardBG.add(addpfp);
        addpfp.setBounds(260, 290, 170, 30);

        removepfp.setForeground(new java.awt.Color(255, 255, 255));
        removepfp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-delete-15.png"))); // NOI18N
        removepfp.setText("Remove");
        removepfp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        removepfp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removepfpMouseClicked(evt);
            }
        });
        removepfp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removepfpActionPerformed(evt);
            }
        });
        dboardBG.add(removepfp);
        removepfp.setBounds(440, 290, 115, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/email.png"))); // NOI18N
        dboardBG.add(jLabel3);
        jLabel3.setBounds(30, 380, 16, 30);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/phone.png"))); // NOI18N
        dboardBG.add(jLabel4);
        jLabel4.setBounds(30, 440, 16, 40);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pass.png"))); // NOI18N
        dboardBG.add(jLabel7);
        jLabel7.setBounds(30, 530, 16, 16);

        jLabel14.setText("Email");
        dboardBG.add(jLabel14);
        jLabel14.setBounds(50, 380, 120, 30);

        jLabel18.setText("Phone Number");
        dboardBG.add(jLabel18);
        jLabel18.setBounds(50, 450, 190, 20);

        jLabel20.setText("Password");
        dboardBG.add(jLabel20);
        jLabel20.setBounds(50, 530, 200, 14);

        dboard.add(dboardBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 870, 590));

        mainbg.add(dboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 910, 700));

        jPanel1.setBackground(new java.awt.Color(37, 171, 241));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        doctorPanel.setBackground(new java.awt.Color(37, 171, 241));
        doctorPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        doctorPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                doctorPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                doctorPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                doctorPanelMouseExited(evt);
            }
        });
        doctorPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/doctor_white_filled.png"))); // NOI18N
        doctorPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 50, 50));

        doctor1.setForeground(new java.awt.Color(250, 249, 246));
        doctor1.setText("Doctors");
        doctorPanel.add(doctor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 100, 50));

        jPanel1.add(doctorPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 190, 50));

        patientPanel.setBackground(new java.awt.Color(37, 171, 241));
        patientPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        patientPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                patientPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                patientPanelMouseExited(evt);
            }
        });
        patientPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/patient_white_filled.png"))); // NOI18N
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        patientPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 50, 50));

        patient1.setForeground(new java.awt.Color(250, 249, 246));
        patient1.setText("Patients");
        patient1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        patientPanel.add(patient1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 110, 50));

        jPanel1.add(patientPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 190, 50));

        dashboardPanel.setBackground(new java.awt.Color(37, 171, 241));
        dashboardPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dashboardPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dashboardPanelMouseExited(evt);
            }
        });
        dashboardPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dashboard_filled.png"))); // NOI18N
        dashboardPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 40, 50));

        jLabel2.setForeground(new java.awt.Color(250, 249, 246));
        jLabel2.setText("Dashboard");
        dashboardPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 110, 30));

        jPanel1.add(dashboardPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 190, 50));

        logoutPanel.setBackground(new java.awt.Color(100, 188, 234));
        logoutPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutPanelMouseExited(evt);
            }
        });
        logoutPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/log-out.png"))); // NOI18N
        logoutPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 30, 50));

        logout.setBackground(new java.awt.Color(73, 138, 172));
        logout.setForeground(new java.awt.Color(73, 138, 172));
        logout.setText("Logout");
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutPanel.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 30));

        jPanel1.add(logoutPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 170, 70));

        profilePanel.setBackground(new java.awt.Color(37, 171, 241));
        profilePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profilePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                profilePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                profilePanelMouseExited(evt);
            }
        });
        profilePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/profile-white.png"))); // NOI18N
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
        });
        profilePanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 50, 50));

        profile.setForeground(new java.awt.Color(250, 249, 246));
        profile.setText("Profile");
        profilePanel.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 100, 50));

        jPanel1.add(profilePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 190, -1));

        accMngrPanel.setBackground(new java.awt.Color(37, 171, 241));
        accMngrPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        accMngrPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accMngrPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                accMngrPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                accMngrPanelMouseExited(evt);
            }
        });
        accMngrPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/accounts-white.png"))); // NOI18N
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel17MouseEntered(evt);
            }
        });
        accMngrPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 50, 50));

        accM.setForeground(new java.awt.Color(250, 249, 246));
        accM.setText("Accounts \nManager");
        accM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        accMngrPanel.add(accM, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 160, 50));

        jPanel1.add(accMngrPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 190, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        logsPanel.setBackground(new java.awt.Color(37, 171, 241));
        logsPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logsPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logsPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logsPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logsPanelMouseExited(evt);
            }
        });
        logsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logs_icon.png"))); // NOI18N
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel19MouseEntered(evt);
            }
        });
        logsPanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 50, 50));

        profile1.setForeground(new java.awt.Color(250, 249, 246));
        profile1.setText("Logs");
        logsPanel.add(profile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 100, 50));

        jPanel1.add(logsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 190, -1));

        mainbg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 700));

        getContentPane().add(mainbg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void doctorPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctorPanelMouseEntered
        doctorPanel.setBackground(hoverColor);
    }//GEN-LAST:event_doctorPanelMouseEntered

    private void doctorPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctorPanelMouseExited
        doctorPanel.setBackground(mainColor);
    }//GEN-LAST:event_doctorPanelMouseExited

    private void patientPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientPanelMouseClicked

        patientMenu pMenu = new patientMenu();
        pMenu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_patientPanelMouseClicked

    private void patientPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientPanelMouseEntered
        patientPanel.setBackground(hoverColor);
    }//GEN-LAST:event_patientPanelMouseEntered

    private void patientPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientPanelMouseExited
        patientPanel.setBackground(mainColor);
    }//GEN-LAST:event_patientPanelMouseExited

    private void dashboardPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardPanelMouseClicked

        adminDashB adb = new adminDashB();
        adb.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_dashboardPanelMouseClicked

    private void dashboardPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardPanelMouseEntered

        dashboardPanel.setBackground(hoverColor);

    }//GEN-LAST:event_dashboardPanelMouseEntered

    private void dashboardPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardPanelMouseExited

        dashboardPanel.setBackground(mainColor);
    }//GEN-LAST:event_dashboardPanelMouseExited

    private void logoutPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutPanelMouseClicked

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            session userSession = session.getInstance();

            userSession.setU_id(null);
            userSession.setFirstName(null);
            userSession.setLastName(null);
            userSession.setEmail(null);
            userSession.setUsername(null);
            userSession.setAcc_type(null);
            userSession.setAcc_status(null);

            logIn lg = new logIn();
            lg.setVisible(true);
            this.dispose();

        }
    }//GEN-LAST:event_logoutPanelMouseClicked

    private void logoutPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutPanelMouseEntered
        logoutPanel.setBackground(hoverlogoutColor);
    }//GEN-LAST:event_logoutPanelMouseEntered

    private void logoutPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutPanelMouseExited
        logoutPanel.setBackground(logoutColor);
    }//GEN-LAST:event_logoutPanelMouseExited

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel16MouseEntered

    private void profilePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePanelMouseEntered

        profilePanel.setBackground(hoverColor);
    }//GEN-LAST:event_profilePanelMouseEntered

    private void profilePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePanelMouseExited

        profilePanel.setBackground(mainColor);
    }//GEN-LAST:event_profilePanelMouseExited

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel17MouseEntered

    private void accMngrPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMngrPanelMouseEntered

        accMngrPanel.setBackground(hoverColor);
    }//GEN-LAST:event_accMngrPanelMouseEntered

    private void accMngrPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMngrPanelMouseExited
        accMngrPanel.setBackground(mainColor);
    }//GEN-LAST:event_accMngrPanelMouseExited

    private void accMngrPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMngrPanelMouseClicked

        accountsMenu acctM = new accountsMenu();

        acctM.setVisible(true);
        this.dispose();


    }//GEN-LAST:event_accMngrPanelMouseClicked

    private void doctorPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctorPanelMouseClicked

        doctorMenu docM = new doctorMenu();

        docM.setVisible(true);
        this.dispose();


    }//GEN-LAST:event_doctorPanelMouseClicked

    private void jLabel19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel19MouseEntered

    private void logsPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsPanelMouseClicked
        logs lo = new logs();
        lo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logsPanelMouseClicked

    private void logsPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsPanelMouseEntered
        
        logsPanel.setBackground(hoverColor);
        
    }//GEN-LAST:event_logsPanelMouseEntered

    private void logsPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsPanelMouseExited
        
        logsPanel.setBackground(mainColor);
        
    }//GEN-LAST:event_logsPanelMouseExited

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked

        changePassForm cpForm = new changePassForm();

        cpForm.setVisible(true);

    }//GEN-LAST:event_jLabel12MouseClicked

    private void addpfpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addpfpMouseEntered

    }//GEN-LAST:event_addpfpMouseEntered

    private void addpfpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addpfpMouseExited

    }//GEN-LAST:event_addpfpMouseExited

    public String destination = "";
    File selectedFile;
    public String oldpath = "";
    public String path = "";

    public int FileExistenceChecker(String path) {
        File file = new File(path);
        String fileName = file.getName();

        Path filePath = Paths.get("src/user_img", fileName);
        boolean fileExists = Files.exists(filePath);

        if (fileExists) {
            return 1;
        } else {
            return 0;
        }

    }


    private void addpfpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addpfpActionPerformed

        LocalDate actionDate = LocalDate.now();
        LocalTime atime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String actionTime = atime.format(formatter);
        
        connectDB con = new connectDB();
        session sess = session.getInstance();

        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                selectedFile = fileChooser.getSelectedFile();
                destination = "src/user_img/" + selectedFile.getName();
                path = selectedFile.getAbsolutePath();

                if (FileExistenceChecker(path) == 1) {
                    JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                    destination = "";
                    path = "";
                } else {
                    pfp.setIcon(ResizeImage(path, null, pfp));
                    addpfp.setEnabled(false);
                    removepfp.setEnabled(true);
                    JOptionPane.showMessageDialog(this, "Profile Photo Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    con.updateData("UPDATE user SET u_pfp = '" + destination + "' WHERE u_id = '" + sess.getU_id() + "'");
                    Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    
                    con.insertData("INSERT INTO logs (u_id, action, action_date, action_time)"
                        + "VALUES ('"+sess.getU_id()+"', 'Updated profile photo', '"+actionDate+"', '"+actionTime+"')");
                    
                }
            } catch (Exception ex) {
                System.out.println("File Error!");
            }
        }

    }//GEN-LAST:event_addpfpActionPerformed

    public ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
        try {
            BufferedImage originalImage;
            if (ImagePath != null) {
                originalImage = ImageIO.read(new File(ImagePath));
            } else {
                originalImage = ImageIO.read(new ByteArrayInputStream(pic));
            }

            int diameter = Math.min(label.getWidth(), label.getHeight());
            BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2 = masked.createGraphics();
            g2.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, diameter, diameter));
            g2.drawImage(originalImage, 0, 0, diameter, diameter, null);
            g2.dispose();

            return new ImageIcon(masked);
        } catch (IOException e) {
            System.out.println("Image Load Error: " + e.getMessage());
            return null;
        }
    }

    public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            // Read the image file
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);

            // Get the original width and height of the image
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();

            // Calculate the new height based on the desired width and the aspect ratio
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);

            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }

        return -1;
    }


    private void removepfpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removepfpMouseClicked

    }//GEN-LAST:event_removepfpMouseClicked

    private void removepfpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removepfpActionPerformed

        LocalDate actionDate = LocalDate.now();
        LocalTime atime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String actionTime = atime.format(formatter);
        
        connectDB con = new connectDB();
        session sess = session.getInstance();

        removepfp.setEnabled(false);
        addpfp.setEnabled(true);

        String blankPfpPath = "src/img/blankpfp.jpg";

        pfp.setIcon(ResizeImage(blankPfpPath, null, pfp));
        destination = "";
        path = "";
        con.updateData("UPDATE user SET u_pfp = '" + destination + "' WHERE u_id = '" + sess.getU_id() + "'");
        
        con.insertData("INSERT INTO logs (u_id, action, action_date, action_time)"
                        + "VALUES ('"+sess.getU_id()+"', 'Removed profile photo', '"+actionDate+"', '"+actionTime+"')");

    }//GEN-LAST:event_removepfpActionPerformed

    private void loadOpenSans() {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/font/OpenSans-VariableFont_wdth,wght.ttf");
            if (fontStream != null) {
                Font openSans = Font.createFont(Font.TRUETYPE_FONT, fontStream);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(openSans);

                jLabel5.setFont(openSans.deriveFont(Font.BOLD, 24));
                name.setFont(openSans.deriveFont(Font.BOLD, 18));
                email.setFont(openSans.deriveFont(Font.BOLD, 18));
                contact.setFont(openSans.deriveFont(Font.BOLD, 18));
                jLabel2.setFont(openSans.deriveFont(Font.BOLD, 12));
                patient1.setFont(openSans.deriveFont(Font.BOLD, 12));
                doctor1.setFont(openSans.deriveFont(Font.BOLD, 12));
                accM.setFont(openSans.deriveFont(Font.BOLD, 12));
                profile.setFont(openSans.deriveFont(Font.BOLD, 12));
                logout.setFont(openSans.deriveFont(Font.BOLD, 18));

            } else {
                System.err.println("Font file not found!");
            }
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

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
            java.util.logging.Logger.getLogger(profileMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(profileMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(profileMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(profileMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new profileMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accM;
    private javax.swing.JPanel accMngrPanel;
    private javax.swing.JButton addpfp;
    private javax.swing.JLabel contact;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JPanel dboard;
    private javax.swing.JPanel dboardBG;
    private javax.swing.JLabel doctor1;
    private javax.swing.JPanel doctorPanel;
    private javax.swing.JLabel email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel logout;
    private javax.swing.JPanel logoutPanel;
    private javax.swing.JPanel logsPanel;
    private javax.swing.JPanel mainbg;
    private javax.swing.JLabel name;
    private javax.swing.JLabel password;
    private javax.swing.JLabel patient1;
    private javax.swing.JPanel patientPanel;
    private javax.swing.JLabel pfp;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel profile1;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JButton removepfp;
    // End of variables declaration//GEN-END:variables
}
