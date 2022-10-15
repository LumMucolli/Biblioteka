package com.probitacademy.biblioteka.ui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.probitacademy.biblioteka.dao.AuthorDao;
import com.probitacademy.biblioteka.domain.Author;
import com.probitacademy.biblioteka.ui.util.UIHelper;
public class AuthorNewEditFrame extends JInternalFrame {
private Author author;
private AuthorDao dao;
private JTextField txtID;
private JTextField txtName;
private JTextField txtLastname;
private JTextField txtCountry;
public AuthorNewEditFrame(Author author, AuthorDao dao) {
super("", true, true, true, true);
this.author = author;
this.dao = dao;
getContentPane().setLayout(new BorderLayout(0, 0));
JPanel pnlHeader = new JPanel(new FlowLayout(FlowLayout.LEFT,
10, 10));
pnlHeader.setBackground(SystemColor.activeCaption);
getContentPane().add(pnlHeader, BorderLayout.NORTH);
JLabel lblTitle = new JLabel("Title");
lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
pnlHeader.add(lblTitle);
JPanel pnlContent = new JPanel();
getContentPane().add(pnlContent, BorderLayout.CENTER);
GridBagLayout gbl_pnlContent = new GridBagLayout();
gbl_pnlContent.columnWidths = new int[] { 0, 0, 0 };
gbl_pnlContent.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
gbl_pnlContent.columnWeights = new double[] { 0.0, 1.0,
Double.MIN_VALUE };
gbl_pnlContent.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
0.0, Double.MIN_VALUE };
pnlContent.setLayout(gbl_pnlContent);
JLabel lblId = new JLabel("ID");
GridBagConstraints gbc_lblId = new GridBagConstraints();
gbc_lblId.insets = new Insets(5, 10, 5, 5);
gbc_lblId.anchor = GridBagConstraints.WEST;
gbc_lblId.gridx = 0;
gbc_lblId.gridy = 0;
pnlContent.add(lblId, gbc_lblId);
txtID = new JTextField();
txtID.setEnabled(false);
GridBagConstraints gbc_txtID = new GridBagConstraints();
gbc_txtID.anchor = GridBagConstraints.WEST;
gbc_txtID.insets = new Insets(5, 5, 5, 10);
gbc_txtID.gridx = 1;
gbc_txtID.gridy = 0;
pnlContent.add(txtID, gbc_txtID);
txtID.setColumns(10);
JLabel lblName = new JLabel("Name");
GridBagConstraints gbc_lblName = new GridBagConstraints();
gbc_lblName.anchor = GridBagConstraints.WEST;
gbc_lblName.insets = new Insets(5, 10, 5, 5);
gbc_lblName.gridx = 0;
gbc_lblName.gridy = 1;
pnlContent.add(lblName, gbc_lblName);
txtName = new JTextField();
GridBagConstraints gbc_txtName = new GridBagConstraints();
gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
gbc_txtName.insets = new Insets(5, 5, 5, 10);
gbc_txtName.gridx = 1;
gbc_txtName.gridy = 1;
pnlContent.add(txtName, gbc_txtName);
txtName.setColumns(10);
JLabel lblLastname = new JLabel("Lastname");
GridBagConstraints gbc_lblLastname = new GridBagConstraints();
gbc_lblLastname.anchor = GridBagConstraints.WEST;
gbc_lblLastname.insets = new Insets(5, 10, 5, 5);
gbc_lblLastname.gridx = 0;
gbc_lblLastname.gridy = 2;
pnlContent.add(lblLastname, gbc_lblLastname);
txtLastname = new JTextField();
GridBagConstraints gbc_txtLastname = new GridBagConstraints();
gbc_txtLastname.insets = new Insets(5, 5, 5, 10);
gbc_txtLastname.fill = GridBagConstraints.HORIZONTAL;
gbc_txtLastname.gridx = 1;
gbc_txtLastname.gridy = 2;
pnlContent.add(txtLastname, gbc_txtLastname);
txtLastname.setColumns(10);
JLabel lblCountry = new JLabel("Country");
GridBagConstraints gbc_lblCountry = new GridBagConstraints();
gbc_lblCountry.anchor = GridBagConstraints.WEST;
gbc_lblCountry.insets = new Insets(5, 10, 5, 5);
gbc_lblCountry.gridx = 0;
gbc_lblCountry.gridy = 3;
pnlContent.add(lblCountry, gbc_lblCountry);
txtCountry = new JTextField();
GridBagConstraints gbc_txtCountry = new GridBagConstraints();
gbc_txtCountry.anchor = GridBagConstraints.WEST;
gbc_txtCountry.insets = new Insets(5, 5, 5, 10);
gbc_txtCountry.gridx = 1;
gbc_txtCountry.gridy = 3;
pnlContent.add(txtCountry, gbc_txtCountry);
txtCountry.setColumns(10);
JPanel panel = new JPanel();
GridBagConstraints gbc_panel = new GridBagConstraints();
gbc_panel.anchor = GridBagConstraints.EAST;
gbc_panel.insets = new Insets(0, 0, 5, 10);
gbc_panel.fill = GridBagConstraints.VERTICAL;
gbc_panel.gridx = 1;
gbc_panel.gridy = 4;
pnlContent.add(panel, gbc_panel);
panel.setLayout(new GridLayout(0, 2, 0, 0));
JButton btnCancel = new JButton("Cancel");
btnCancel.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
try {
setClosed(true);
} catch (PropertyVetoException e1) {
e1.printStackTrace();
}
}
});
panel.add(btnCancel);
JButton btnSave = new JButton("Save");
btnSave.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent evt) {
if (authorValid()) {
updateAuthor();
try {
dao.save(author);
setClosed(true);
} catch (Exception ex) {
ex.printStackTrace();
}
} else {
UIHelper.error("Ploteso te dhenat!");
}
}
});
panel.add(btnSave);
fillFields();
pack();
setVisible(true);
}
public void fillFields() {
if (author != null && author.getId() != 0) {
txtID.setText("" + author.getId());
txtName.setText(author.getName());
txtLastname.setText(author.getLastName());
txtCountry.setText(author.getCountry());
}
}
public void updateAuthor() {
if (author != null) {
author.setName(txtName.getText());
author.setLastName(txtLastname.getText());
author.setCountry(txtCountry.getText());
}
}
public Author getAuthor() {
return author;
}
private boolean authorValid() {
return Objects.nonNull(author) &&
Objects.nonNull(author.getName()) && Objects.nonNull(author.getLastName());
}
}