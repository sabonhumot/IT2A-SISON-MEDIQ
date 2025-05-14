/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import careq.*;
import config.connectDB;
import config.session;
import gfx.RoundedPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import gfx.RoundedTextField;
import java.awt.BorderLayout;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.BorderFactory;

/**
 *
 * @author Administrator
 */
public class adminDashB extends javax.swing.JFrame {

    Color mainColor = new Color(37, 171, 241);
    Color hoverColor = new Color(31, 128, 179);

    Color logoutColor = new Color(100, 188, 234);
    Color hoverlogoutColor = new Color(250, 249, 246);

    public adminDashB() {
        initComponents();
        loadOpenSans();
        loadItim();
        displayData();
        getTotalAcc();
        getPatientCount();
        getDoctorCount();
        getActiveAccCount();
        getPendingAccCount();
        emptyData();

    }

    public void emptyData() {

        if (usersTable.getModel().getRowCount() == 0) {

            jLabel3.setVisible(true);
            jLabel4.setVisible(true);

        } else {
            jLabel3.setVisible(false);
            jLabel4.setVisible(false);
        }

    }
    
    public void displayData() {
        try {
            connectDB con = new connectDB();
            ResultSet rs = con.getData("SELECT u_id, u_fname, u_lname, u_email, u_pnum, type, status FROM user");

            String[] column = {"ID", "First Name", "Last Name", "Email", "Phone Number", "Account Type", "Account Status"};

            DefaultTableModel editableModel = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
            rs.close();

            DefaultTableModel model = new DefaultTableModel(editableModel.getDataVector(), new Vector<>(Arrays.asList(column))) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Disable editing for all cells
                }
            };

            usersTable.setModel(model);

            // Header Renderer
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    JLabel label = new JLabel(value.toString());
                    label.setFont(new Font("OpenSans", Font.BOLD, 12));
                    label.setPreferredSize(new Dimension(label.getWidth(), 30));
                    label.setOpaque(true);
                    label.setBackground(new Color(250, 249, 246));
                    label.setForeground(Color.BLACK);
                    label.setHorizontalAlignment(SwingConstants.LEFT);
                    label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                    return label;
                }
            };

            // Apply custom header renderer
            for (int i = 0; i < usersTable.getColumnModel().getColumnCount(); i++) {
                usersTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            }

            // Cell Renderer
            DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    JLabel label = new JLabel(value != null ? value.toString() : "");
                    label.setFont(new Font("OpenSans", Font.PLAIN, 12));
                    label.setOpaque(true);
                    if (row % 2 == 0) {
                        label.setBackground(new Color(245, 245, 245));
                    } else {
                        label.setBackground(Color.WHITE);
                    }
                    label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                    label.setHorizontalAlignment(SwingConstants.LEFT);

                    if (isSelected) {
                        label.setBackground(new Color(220, 240, 255));
                        label.setForeground(Color.BLACK);
                    }

                    return label;
                }
            };

            // Apply renderer to all columns
            for (int i = 0; i < usersTable.getColumnModel().getColumnCount(); i++) {
                usersTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
            }

            // Status Column Renderer
            class StatusRenderer extends DefaultTableCellRenderer {

                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    JLabel label = new JLabel(value.toString());
                    label.setOpaque(true);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setFont(new Font("Open Sans", Font.PLAIN, 12));

                    switch (value.toString()) {
                        case "Active":
                            label.setBackground(new Color(144, 238, 144)); // Light Green
                            label.setForeground(Color.BLACK);
                            break;
                        case "Pending":
                            label.setBackground(new Color(255, 223, 128)); // Light Orange
                            label.setForeground(new Color(102, 51, 0));
                            break;
                        default:
                            label.setBackground(Color.WHITE);
                            label.setForeground(Color.BLACK);
                            break;
                    }

                    if (isSelected) {
                        label.setBackground(new Color(220, 240, 255));
                    }

                    return label;
                }
            }

            // Apply custom renderer to "Account Status" column (index 6)
            usersTable.getColumnModel().getColumn(6).setCellRenderer(new StatusRenderer());

        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }

    public void getTotalAcc() {

        connectDB con = new connectDB();

        try {

            ResultSet rs = con.getData("SELECT COUNT(*) FROM user");
            if (rs.next()) {
                int count = rs.getInt(1);
                TA.setText(String.valueOf(count));
            }

        } catch (SQLException ex) {
            System.out.println("" + ex);

        }

    }

    public void getPatientCount() {

        connectDB con = new connectDB();

        try {

            ResultSet rs = con.getData("SELECT COUNT(*) FROM user WHERE type = 'Patient'");
            if (rs.next()) {
                int count = rs.getInt(1);

                P.setText(String.valueOf(count));
            }

        } catch (SQLException ex) {
            System.out.println("" + ex);

        }

    }

    public void getDoctorCount() {

        connectDB con = new connectDB();

        try {

            ResultSet rs = con.getData("SELECT COUNT(*) FROM user WHERE type = 'Doctor'");
            if (rs.next()) {
                int count = rs.getInt(1);
                D.setText(String.valueOf(count));
            }

        } catch (SQLException ex) {
            System.out.println("" + ex);

        }

    }

    public void getActiveAccCount() {

        connectDB con = new connectDB();

        try {

            ResultSet rs = con.getData("SELECT COUNT(*) FROM user WHERE status = 'Active'");
            if (rs.next()) {
                int count = rs.getInt(1);
                AA.setText(String.valueOf(count));
            }

        } catch (SQLException ex) {
            System.out.println("" + ex);

        }

    }

    public void getPendingAccCount() {

        connectDB con = new connectDB();

        try {

            ResultSet rs = con.getData("SELECT COUNT(*) FROM user WHERE status = 'Pending'");
            if (rs.next()) {
                int count = rs.getInt(1);
                PA.setText(String.valueOf(count));

                if (count > 0) {
                    dot.setVisible(true);
                } else {
                    dot.setVisible(false);
                }

            }

        } catch (SQLException ex) {
            System.out.println("" + ex);

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainbg = new javax.swing.JPanel();
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
        logsPanel = new RoundedPanel(50);
        jLabel19 = new javax.swing.JLabel();
        profile1 = new javax.swing.JLabel();
        accMngrPanel = new RoundedPanel(50);
        jLabel17 = new javax.swing.JLabel();
        accM = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        dboard = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        dboardBG = new RoundedPanel(15);
        accOV = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        searchh = new RoundedTextField(35);
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new RoundedPanel(15);
        P = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        patient = new javax.swing.JLabel();
        jPanel5 = new RoundedPanel(15);
        jLabel13 = new javax.swing.JLabel();
        D = new javax.swing.JLabel();
        doctor = new javax.swing.JLabel();
        jPanel6 = new RoundedPanel(15);
        jLabel14 = new javax.swing.JLabel();
        actAcc = new javax.swing.JLabel();
        AA = new javax.swing.JLabel();
        jPanel7 = new RoundedPanel(15);
        PA = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        penAcc = new javax.swing.JLabel();
        dot = new javax.swing.JLabel();
        jPanel8 = new RoundedPanel(15);
        totalAcc = new javax.swing.JLabel();
        TA = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        mainbg.setBackground(new java.awt.Color(245, 245, 245));
        mainbg.setMinimumSize(new java.awt.Dimension(1017, 620));
        mainbg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilePanelMouseClicked(evt);
            }
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
        accMngrPanel.add(accM, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 150, 50));

        jPanel1.add(accMngrPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 190, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 70));

        mainbg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 700));

        dboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(250, 249, 246));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Admin Dashboard");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 300, -1));

        dboard.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 70));

        dboardBG.setBackground(new java.awt.Color(250, 249, 246));
        dboardBG.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        accOV.setText("Accounts Overview");
        dboardBG.add(accOV, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("No data available");
        dboardBG.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 120, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/no-data.png"))); // NOI18N
        dboardBG.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, -1, -1));

        usersTable.getTableHeader().setOpaque(false);
        usersTable.setBackground(new java.awt.Color(250, 249, 246));
        usersTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        usersTable.setDragEnabled(true);
        usersTable.setOpaque(false);
        usersTable.setRowHeight(35);
        usersTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        usersTable.setShowHorizontalLines(false);
        usersTable.setShowVerticalLines(false);
        scroll.setViewportView(usersTable);

        dboardBG.add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 830, 260));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clear.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        dboardBG.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 30, 30));

        searchh.setBackground(new java.awt.Color(240, 240, 240));
        searchh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchhActionPerformed(evt);
            }
        });
        searchh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchhKeyTyped(evt);
            }
        });
        dboardBG.add(searchh, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 190, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        dboardBG.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 20, 30));

        dboard.add(dboardBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 870, 330));

        jPanel4.setBackground(new java.awt.Color(250, 249, 246));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });
        jPanel4.setLayout(null);
        jPanel4.add(P);
        P.setBounds(20, 50, 30, 40);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/patient.png"))); // NOI18N
        jPanel4.add(jLabel11);
        jLabel11.setBounds(200, 40, 40, 40);

        patient.setText("Patients");
        patient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(patient);
        patient.setBounds(10, 10, 90, 14);

        dboard.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 270, 110));

        jPanel5.setBackground(new java.awt.Color(250, 249, 246));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });
        jPanel5.setLayout(null);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/doctor.png"))); // NOI18N
        jPanel5.add(jLabel13);
        jLabel13.setBounds(200, 40, 40, 40);
        jPanel5.add(D);
        D.setBounds(10, 50, 30, 40);

        doctor.setText("Doctors");
        jPanel5.add(doctor);
        doctor.setBounds(10, 20, 80, 14);

        dboard.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 270, 110));

        jPanel6.setBackground(new java.awt.Color(250, 249, 246));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });
        jPanel6.setLayout(null);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/activeUser.png"))); // NOI18N
        jPanel6.add(jLabel14);
        jLabel14.setBounds(200, 40, 40, 40);

        actAcc.setText("Active Accounts");
        jPanel6.add(actAcc);
        actAcc.setBounds(20, 20, 170, 14);
        jPanel6.add(AA);
        AA.setBounds(20, 50, 30, 30);

        dboard.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 270, 110));

        jPanel7.setBackground(new java.awt.Color(250, 249, 246));
        jPanel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel7MouseEntered(evt);
            }
        });
        jPanel7.setLayout(null);
        jPanel7.add(PA);
        PA.setBounds(20, 50, 30, 30);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pendingUser.png"))); // NOI18N
        jPanel7.add(jLabel12);
        jLabel12.setBounds(200, 40, 40, 50);

        penAcc.setText("Pending Accounts");
        jPanel7.add(penAcc);
        penAcc.setBounds(20, 10, 190, 30);

        dot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/red-dot.png"))); // NOI18N
        jPanel7.add(dot);
        dot.setBounds(230, 30, 20, 30);

        dboard.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 270, 110));

        jPanel8.setBackground(new java.awt.Color(250, 249, 246));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        totalAcc.setText("Total Accounts");
        jPanel8.add(totalAcc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        jPanel8.add(TA, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 30, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/accounts.png"))); // NOI18N
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 40, 40));

        dboard.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, 270, 110));

        mainbg.add(dboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 910, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainbg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainbg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardPanelMouseEntered

        dashboardPanel.setBackground(hoverColor);


    }//GEN-LAST:event_dashboardPanelMouseEntered

    private void dashboardPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardPanelMouseExited

        dashboardPanel.setBackground(mainColor);

    }//GEN-LAST:event_dashboardPanelMouseExited

    private void patientPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientPanelMouseEntered
        patientPanel.setBackground(hoverColor);
    }//GEN-LAST:event_patientPanelMouseEntered

    private void patientPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientPanelMouseExited
        patientPanel.setBackground(mainColor);
    }//GEN-LAST:event_patientPanelMouseExited

    private void doctorPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctorPanelMouseEntered
        doctorPanel.setBackground(hoverColor);
    }//GEN-LAST:event_doctorPanelMouseEntered

    private void doctorPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctorPanelMouseExited
        doctorPanel.setBackground(mainColor);
    }//GEN-LAST:event_doctorPanelMouseExited

    private void dashboardPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardPanelMouseClicked


    }//GEN-LAST:event_dashboardPanelMouseClicked

    private void logoutPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutPanelMouseEntered
        logoutPanel.setBackground(hoverlogoutColor);
    }//GEN-LAST:event_logoutPanelMouseEntered

    private void logoutPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutPanelMouseExited
        logoutPanel.setBackground(logoutColor);
    }//GEN-LAST:event_logoutPanelMouseExited

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

    private void searchhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchhActionPerformed

    private void searchhKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchhKeyTyped
        searchTable();
    }//GEN-LAST:event_searchhKeyTyped

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        searchh.setText("");
        usersTable.setRowSorter(null);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void patientPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientPanelMouseClicked

        patientMenu pMenu = new patientMenu();
        pMenu.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_patientPanelMouseClicked

    private void profilePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePanelMouseEntered

        profilePanel.setBackground(hoverColor);

    }//GEN-LAST:event_profilePanelMouseEntered

    private void profilePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePanelMouseExited

        profilePanel.setBackground(mainColor);

    }//GEN-LAST:event_profilePanelMouseExited

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel17MouseEntered

    private void accMngrPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMngrPanelMouseEntered

        accMngrPanel.setBackground(hoverColor);

    }//GEN-LAST:event_accMngrPanelMouseEntered

    private void accMngrPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMngrPanelMouseExited
        accMngrPanel.setBackground(mainColor);
    }//GEN-LAST:event_accMngrPanelMouseExited

    private void profilePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePanelMouseClicked

        profileMenu pm = new profileMenu();
        pm.setVisible(true);
        this.dispose();


    }//GEN-LAST:event_profilePanelMouseClicked

    private void doctorPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctorPanelMouseClicked

        doctorMenu dMenu = new doctorMenu();

        dMenu.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_doctorPanelMouseClicked

    private void accMngrPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accMngrPanelMouseClicked

        accountsMenu acctMenu = new accountsMenu();

        acctMenu.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_accMngrPanelMouseClicked

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

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked

        patientMenu pM = new patientMenu();
        pM.setVisible(true);
        this.dispose();


    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked

        doctorMenu dM = new doctorMenu();
        dM.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked

        accountsMenu accM = new accountsMenu();
        accM.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7MouseEntered

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked

        accountsMenu accM = new accountsMenu();
        accM.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jPanel7MouseClicked

    private void loadOpenSans() {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/font/OpenSans-VariableFont_wdth,wght.ttf");
            if (fontStream != null) {
                Font openSans = Font.createFont(Font.TRUETYPE_FONT, fontStream);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(openSans);
                // Now you can use the font:
                jLabel2.setFont(openSans.deriveFont(Font.BOLD, 12));
                jLabel5.setFont(openSans.deriveFont(Font.BOLD, 24));
                logout.setFont(openSans.deriveFont(Font.BOLD, 18));
                patient.setFont(openSans.deriveFont(Font.BOLD, 18));
                patient1.setFont(openSans.deriveFont(Font.BOLD, 12));
                doctor.setFont(openSans.deriveFont(Font.BOLD, 18));
                doctor1.setFont(openSans.deriveFont(Font.BOLD, 12));
                actAcc.setFont(openSans.deriveFont(Font.BOLD, 18));
                penAcc.setFont(openSans.deriveFont(Font.BOLD, 18));
                totalAcc.setFont(openSans.deriveFont(Font.BOLD, 18));
                accOV.setFont(openSans.deriveFont(Font.BOLD, 18));
                TA.setFont(openSans.deriveFont(Font.BOLD, 20));
                dboardBG.setFont(openSans.deriveFont(Font.BOLD, 18));
                D.setFont(openSans.deriveFont(Font.BOLD, 20));
                P.setFont(openSans.deriveFont(Font.BOLD, 20));
                AA.setFont(openSans.deriveFont(Font.BOLD, 20));
                PA.setFont(openSans.deriveFont(Font.BOLD, 20));
                usersTable.getTableHeader().setFont(openSans.deriveFont(Font.BOLD, 15));
                usersTable.getTableHeader().revalidate();
                usersTable.getTableHeader().repaint();
                usersTable.setFont(openSans.deriveFont(Font.PLAIN, 12));
                profile.setFont(openSans.deriveFont(Font.BOLD, 12));
                accM.setFont(openSans.deriveFont(Font.BOLD, 12));

            } else {
                System.err.println("Font file not found!");
            }
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    private void loadItim() {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/font/Itim-Regular.ttf");
            if (fontStream != null) {
                Font itim = Font.createFont(Font.TRUETYPE_FONT, fontStream);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(itim);

            } else {
                System.err.println("Font file not found!");
            }
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

    }

    private void searchTable() {
        DefaultTableModel model = (DefaultTableModel) usersTable.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        usersTable.setRowSorter(rowSorter);

        searchh.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }

            private void filter() {
                String searchText = searchh.getText().trim();
                if (searchText.isEmpty()) {
                    rowSorter.setRowFilter(null); // Show all rows if empty
                } else {
                    rowSorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
                        @Override
                        public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                            String firstName = entry.getStringValue(1).toLowerCase(); // First Name (Column 1)
                            String lastName = entry.getStringValue(2).toLowerCase();  // Last Name (Column 2)

                            return firstName.contains(searchText.toLowerCase()) || lastName.contains(searchText.toLowerCase());
                        }
                    });
                }
            }
        });
    }

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
            java.util.logging.Logger.getLogger(adminDashB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminDashB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminDashB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminDashB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminDashB().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AA;
    private javax.swing.JLabel D;
    private javax.swing.JLabel P;
    private javax.swing.JLabel PA;
    private javax.swing.JLabel TA;
    private javax.swing.JLabel accM;
    private javax.swing.JPanel accMngrPanel;
    private javax.swing.JLabel accOV;
    private javax.swing.JLabel actAcc;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JPanel dboard;
    private javax.swing.JPanel dboardBG;
    private javax.swing.JLabel doctor;
    private javax.swing.JLabel doctor1;
    private javax.swing.JPanel doctorPanel;
    private javax.swing.JLabel dot;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel logout;
    private javax.swing.JPanel logoutPanel;
    private javax.swing.JPanel logsPanel;
    private javax.swing.JPanel mainbg;
    private javax.swing.JLabel patient;
    private javax.swing.JLabel patient1;
    private javax.swing.JPanel patientPanel;
    private javax.swing.JLabel penAcc;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel profile1;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField searchh;
    private javax.swing.JLabel totalAcc;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables
}
