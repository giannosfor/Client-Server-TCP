package tcpclient;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class TCPClient extends javax.swing.JFrame {

    private Socket socket;
    private BufferedReader inputReader;
    private PrintWriter outputWriter;

    public TCPClient() {
        initComponents();
    }

    private void connectToServer() {

        String serverIPAddress = iptextfield.getText();
        int serverPort = Integer.parseInt(porttextfield.getText());
        try {
            socket = new Socket(InetAddress.getByName(serverIPAddress), serverPort);

        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputWriter = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread() {

            @Override
            public void run() {
                receiveData();
            }
        }.start();
    }

    private void receiveData() {

        try {
            while (true) {
                String receivedMessage = inputReader.readLine();
                messagearea.append(receivedMessage + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendData(String messageToSend) {

        outputWriter.println(messageToSend);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        iptextfield = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        porttextfield = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        usernametextfield = new javax.swing.JTextField();
        signintoggle = new javax.swing.JToggleButton();
        signouttoggle = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        messagearea = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        sendmessagetextfield = new javax.swing.JTextField();
        usersendtextfield = new javax.swing.JTextField();
        sendbutton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        usersbutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Server Data"));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setText("IP Address  ");
        jPanel5.add(jLabel1);

        iptextfield.setText("127.0.0.1");
        jPanel5.add(iptextfield);

        jLabel2.setText("    Port  ");
        jPanel5.add(jLabel2);

        porttextfield.setText("15000");
        jPanel5.add(porttextfield);

        getContentPane().add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 255)), "Client"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Username"));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));
        jPanel2.add(jPanel6);

        usernametextfield.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernametextfieldFocusGained(evt);
            }
        });
        jPanel2.add(usernametextfield);

        buttonGroup1.add(signintoggle);
        signintoggle.setText("Sign in");
        signintoggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signintoggleActionPerformed(evt);
            }
        });
        jPanel2.add(signintoggle);

        buttonGroup1.add(signouttoggle);
        signouttoggle.setSelected(true);
        signouttoggle.setText("Sign out");
        signouttoggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signouttoggleActionPerformed(evt);
            }
        });
        jPanel2.add(signouttoggle);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Messages"));
        jPanel3.setLayout(new java.awt.BorderLayout());

        messagearea.setColumns(20);
        messagearea.setEditable(false);
        messagearea.setLineWrap(true);
        messagearea.setRows(5);
        messagearea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(messagearea);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Send Message"));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        sendmessagetextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendmessagetextfieldActionPerformed(evt);
            }
        });
        jPanel4.add(sendmessagetextfield);

        usersendtextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersendtextfieldActionPerformed(evt);
            }
        });
        jPanel4.add(usersendtextfield);

        sendbutton.setText("Send");
        sendbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendbuttonActionPerformed(evt);
            }
        });
        jPanel4.add(sendbutton);

        jSeparator2.setForeground(new java.awt.Color(240, 240, 240));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator2);

        jSeparator1.setForeground(new java.awt.Color(153, 0, 255));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator1);

        usersbutton.setText("Users");
        usersbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersbuttonActionPerformed(evt);
            }
        });
        jPanel4.add(usersbutton);

        jPanel1.add(jPanel4, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void signintoggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signintoggleActionPerformed
    String username = usernametextfield.getText();  //ΙΣΩΣ ΠΡΕΠΕΙ ΝΑ ΕΛΕΝΞΩ ΝΑ ΠΑΡΕΙ ΕΝΑ argument
    if (username.trim().isEmpty()) {
        this.usernametextfield.setBackground(Color.red);
        this.signouttoggle.setSelected(true);
    } else {
        connectToServer();            
        sendData("Signin " + username);
    }

}//GEN-LAST:event_signintoggleActionPerformed

private void signouttoggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signouttoggleActionPerformed
    sendData("Signout");
}//GEN-LAST:event_signouttoggleActionPerformed

private void sendbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendbuttonActionPerformed

    if (this.signintoggle.isSelected()) {
        String senduser = usersendtextfield.getText().trim();
        String message;
        String sendmessage = sendmessagetextfield.getText();
        if (senduser.isEmpty()) {
            message = "Message " + sendmessage;
        } else {
            message = "MessageTo " + senduser + " " + sendmessage;
        }
        sendData(message);
        sendmessagetextfield.setText("");
        usersendtextfield.setText("");
        messagearea.append(sendmessage + "\n");
    } else {
        JOptionPane.showMessageDialog(this,
                "You must signin first",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_sendbuttonActionPerformed

private void usersbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersbuttonActionPerformed
    if (this.signintoggle.isSelected()) {
        sendData("Users");
    } else {
        JOptionPane.showMessageDialog(this,
                "You must signin first",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_usersbuttonActionPerformed

//
private void sendmessagetextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendmessagetextfieldActionPerformed
    //AKYRH ΜΕΘΟΔΟΣ
}//GEN-LAST:event_sendmessagetextfieldActionPerformed

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    if (socket != null) {
        try {
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}//GEN-LAST:event_formWindowClosing

    private void usernametextfieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernametextfieldFocusGained
        this.usernametextfield.setBackground(Color.white);
    }//GEN-LAST:event_usernametextfieldFocusGained

    private void usersendtextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersendtextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usersendtextfieldActionPerformed

    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TCPClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TCPClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TCPClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TCPClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                TCPClient client = new TCPClient();
                client.setSize(600, 700);
                client.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField iptextfield;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea messagearea;
    private javax.swing.JTextField porttextfield;
    private javax.swing.JButton sendbutton;
    private javax.swing.JTextField sendmessagetextfield;
    private javax.swing.JToggleButton signintoggle;
    private javax.swing.JToggleButton signouttoggle;
    private javax.swing.JTextField usernametextfield;
    private javax.swing.JButton usersbutton;
    private javax.swing.JTextField usersendtextfield;
    // End of variables declaration//GEN-END:variables
}
