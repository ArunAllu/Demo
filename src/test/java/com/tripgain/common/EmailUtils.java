package com.tripgain.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailUtils {

    private static final String FROM_EMAIL = "arun.kumar@tripgain.com";  // your email
    private static final String PASSWORD = "nshlltgljgzbqpyn";                  // your app password
    private static final long ZIP_THRESHOLD_BYTES = 5 * 1024 * 1024;       // 5 MB

    public static void sendReportByEmail(String reportPath, String[] bccEmails) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));

            // ✅ Set BCC recipients
            InternetAddress[] bccAddresses = Arrays.stream(bccEmails)
                    .map(email -> {
                        try {
                            return new InternetAddress(email);
                        } catch (AddressException e) {
                            throw new RuntimeException("Invalid email: " + email, e);
                        }
                    }).toArray(InternetAddress[]::new);
            message.setRecipients(Message.RecipientType.BCC, bccAddresses);

            message.setSubject("TripGain Automation Test Report");

            // Email body
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Hi,\n\nPlease find attached the TripGain test execution report.\n\nRegards,\nQA Team");

            // Check size and zip if needed
            File reportFile = new File(reportPath);
            File fileToAttach;

            if (reportFile.length() > ZIP_THRESHOLD_BYTES) {
                System.out.println("📦 Report is large (" + reportFile.length() + " bytes), zipping...");
                fileToAttach = zipFile(reportFile);
            } else {
                fileToAttach = reportFile;
            }

            // Attachment part
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(fileToAttach);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("✅ Report emailed successfully to BCC: " + String.join(", ", bccEmails));

        } catch (Exception e) {
            System.err.println("❌ Failed to send email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ✅ Helper to ZIP a file
    private static File zipFile(File inputFile) throws IOException {
        String zipFilePath = inputFile.getParent() + File.separator + inputFile.getName() + ".zip";
        File zipFile = new File(zipFilePath);

        try (
                FileOutputStream fos = new FileOutputStream(zipFile);
                ZipOutputStream zos = new ZipOutputStream(fos);
                FileInputStream fis = new FileInputStream(inputFile)
        ) {
            ZipEntry zipEntry = new ZipEntry(inputFile.getName());
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[4096];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
        }

        return zipFile;
    }


}
