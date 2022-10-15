package com.probitacademy.biblioteka.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.probitacademy.biblioteka.dao.Dao;
public abstract class BaseInternalFrame<T> extends JInternalFrame {
protected Dao<T> baseDao;
private JLabel lblFooterLabel = new JLabel(" ");
protected Font titleFont = new Font("Arial", Font.BOLD, 20);
protected Font normalFont = new Font("Arial", Font.BOLD, 18);
protected Font footerFont = new Font("Arial", Font.BOLD, 15);
public BaseInternalFrame(String title, ImageIcon icon, Dao<T> baseDao)
{
super(title, true, true, true, true);
this.baseDao = baseDao;
getContentPane().setLayout(new BorderLayout(0, 0));
doHeaderLayout(icon);// 1
doCustomLayout();// 2-impl. me vone
doFooterLayout();// 3
pack();
setVisible(true);
}
public void updateFooterMessage(String message) {
lblFooterLabel.setText(message);
// largon tekstin e labeles pas 3 sekondave
Timer t = new Timer(3000, new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
lblFooterLabel.setText(" ");
}
});
t.setRepeats(false);
t.start();
}
public void doFooterLayout() {
JPanel pnlFooter = new JPanel(new FlowLayout(FlowLayout.LEFT));
pnlFooter.setBackground(Color.LIGHT_GRAY);
lblFooterLabel.setFont(footerFont);
pnlFooter.add(lblFooterLabel);
getContentPane().add(pnlFooter, BorderLayout.SOUTH);
}
public void doHeaderLayout(ImageIcon icon) {
JPanel pnlHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
pnlHeader.setBackground(SystemColor.activeCaption);
if (icon != null) {
JLabel lblIcon = new JLabel(icon);
pnlHeader.add(lblIcon);
}
JLabel title = new JLabel(getTitle());
title.setFont(titleFont);
pnlHeader.add(title);
getContentPane().add(pnlHeader, BorderLayout.NORTH);
}
public abstract void doCustomLayout();
}