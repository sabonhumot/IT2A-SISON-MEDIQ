/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class reports {

    // Method to test compilation only
    public static void testCompilation() {
        try {
            System.out.println("Testing compilation of diagnosis_report.jrxml...");
            JasperReport report = JasperCompileManager.compileReport("src/diagnosis_report/diagnosis_report.jrxml");
            System.out.println("Compilation successful!");
        } catch (JRException e) {
            System.err.println("Compilation failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Overloaded method that accepts Integer directly
    public static void showDiagnosisReport(Connection conn, Integer patientId) throws JRException {
        try {
            System.out.println("Compiling report...");
            JasperReport report = JasperCompileManager.compileReport("src/diagnosis_report/diagnosis_report.jrxml");

            Map<String, Object> params = new java.util.HashMap<>();
            params.put("LoggedInPatient", patientId);

            System.out.println("Filling report with patient ID: " + patientId);
            JasperPrint filledReport = JasperFillManager.fillReport(report, params, conn);

            System.out.println("Showing report...");
            JasperViewer.viewReport(filledReport, false);

        } catch (JRException e) {
            System.err.println("JasperReports Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public static void showDiagnosisReport(Connection conn, String patientId) throws JRException {
        try {
            System.out.println("Compiling report...");

            System.setProperty("jasper.reports.compile.keep.java.file", "true");
            System.setProperty("jasper.reports.compile.class.debug", "true");
            System.setProperty("jasper.reports.compile.class.path", System.getProperty("java.class.path"));
            System.setProperty("jasper.reports.compile.temp", "C:/DEBUG");
            JasperReport report = JasperCompileManager.compileReport("src/diagnosis_report/test6.jrxml");

            Map<String, Object> params = new java.util.HashMap<>();
            // Convert String to Integer to match JRXML parameter type
            params.put("LoggedInPatient", Integer.valueOf(patientId));
            params.put("imagePath1", "src/img/mediq_white-removebg-preview.png");
            params.put("imagePath2", "src/img/output-onlinepngtools.png");
            

            System.out.println("Filling report with patient ID: " + patientId);
            JasperPrint filledReport = JasperFillManager.fillReport(report, params, conn);

            System.out.println("Showing report...");
            JasperViewer.viewReport(filledReport, false);

        } catch (NumberFormatException e) {
            System.err.println("Invalid patient ID format. Expected integer: " + patientId);
            throw new JRException("Invalid patient ID format", e);
        } catch (JRException e) {
            System.err.println("JasperReports Error: " + e.getMessage());
            e.printStackTrace();

            // Print the cause if available
            if (e.getCause() != null) {
                System.err.println("Root cause: " + e.getCause().getMessage());
                e.getCause().printStackTrace();
            }
            throw e;
        }
    }
}
