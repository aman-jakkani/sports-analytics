package edu.sportanalytics.database;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

public class DatabaseProperties extends JFrame
{

    private static final Logger log = Logger.getLogger(DatabaseProperties.class.getName());

    private DBAccess connection;

    public DatabaseProperties(DBAccess connection)
    {
        this.connection = connection;
        buildDialog();
        this.pack();
        this.setVisible(true);
        log.info("Created database properties dialog.");
    }

    private void buildDialog()
    {
        this.getContentPane().setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5,2));

        //server url
        JLabel serverURLL = new JLabel("ServerURL:", JLabel.TRAILING);
        formPanel.add(serverURLL);
        final JTextField serverURLTF = new JTextField();
        serverURLL.setLabelFor(serverURLTF);
        formPanel.add(serverURLTF);

        //portnumber
        JLabel portL = new JLabel("Port:", JLabel.TRAILING);
        formPanel.add(portL);
        //interger only
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(9999);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        final JFormattedTextField portTF = new JFormattedTextField(formatter);
        portL.setLabelFor(portTF);
        formPanel.add(portTF);

        //SID
        JLabel sidL = new JLabel("SID:", JLabel.TRAILING);
        formPanel.add(sidL);
        final JTextField sidTF = new JTextField();
        sidL.setLabelFor(sidTF);
        formPanel.add(sidTF);


        //username
        JLabel usernameL = new JLabel("Username:", JLabel.TRAILING);
        formPanel.add(usernameL);
        final JTextField usernameTF = new JTextField();
        usernameL.setLabelFor(usernameTF);
        formPanel.add(usernameTF);

        //password
        JLabel passwordL = new JLabel("Password:", JLabel.TRAILING);
        formPanel.add(passwordL);
        final JPasswordField passwordTF = new JPasswordField();
        passwordL.setLabelFor(passwordTF);
        formPanel.add(passwordTF);

        this.getContentPane().add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1,2));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connection.setDBParams(
                        serverURLTF.getText(),
                        Integer.valueOf(portTF.getText()).toString(),
                        sidTF.getText(),
                        usernameTF.getText(),
                        passwordTF.getPassword());
                log.info("Database properties changed.");
                exit();
            }
        });
        buttonPanel.add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                log.info("Database properties dialog canceled.");
                exit();
            }
        });
        buttonPanel.add(cancelButton);

        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        //init
        serverURLTF.setText(connection.getServerURL());
        portTF.setText(connection.getPort());
        sidTF.setText(connection.getSid());
        usernameTF.setText(connection.getUsername());

    }

    private void exit()
    {
        dispose();
    }
}
