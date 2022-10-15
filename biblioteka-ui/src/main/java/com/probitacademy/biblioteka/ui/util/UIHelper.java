package com.probitacademy.biblioteka.ui.util;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
public class UIHelper {
public static ImageIcon infoIcon;
public static ImageIcon errorIcon;
public static ImageIcon confirmIcon;
public static ImageIcon warningIcon;
public static ImageIcon deleteIcon;
public static ImageIcon saveIcon;
public static ImageIcon abortIcon;
public static ImageIcon newIcon;
public static ImageIcon exitIcon;
public static ImageIcon okIcon;
public static ImageIcon wrenchIcon;
public static ImageIcon aboutIcon;
public static ImageIcon addIcon;
public static ImageIcon searchIcon;
public static ImageIcon findIcon;
public static ImageIcon authorsIcon;
public static ImageIcon notesIcon;
public static ImageIcon repairIcon;
public static Icon bossIcon;
static {
newIcon = new ImageIcon(UIHelper.class.getResource("/icons/Create.png"));
saveIcon = new ImageIcon(UIHelper.class.getResource("/icons/Save.png"));
deleteIcon = new ImageIcon(UIHelper.class.getResource("/icons/Delete.png"));
abortIcon = new ImageIcon(UIHelper.class.getResource("/icons/Abort.png"));
exitIcon = new ImageIcon(UIHelper.class.getResource("/icons/Exit.png"));
okIcon = new ImageIcon(UIHelper.class.getResource("/icons/OK.png"));
wrenchIcon = new ImageIcon(UIHelper.class.getResource("/icons/Wrench.png"));
aboutIcon = new ImageIcon(UIHelper.class.getResource("/icons/About.png"));
addIcon = new ImageIcon(UIHelper.class.getResource("/icons/Add.png"));
searchIcon = new ImageIcon(UIHelper.class.getResource("/icons/Search.png"));
findIcon = new ImageIcon(UIHelper.class.getResource("/icons/Find.png"));
authorsIcon = new ImageIcon(UIHelper.class.getResource("/icons/Authors.png"));
notesIcon = new ImageIcon(UIHelper.class.getResource("/icons/Notes.png"));
repairIcon = new ImageIcon(UIHelper.class.getResource("/icons/Repair.png"));
infoIcon = new ImageIcon(UIHelper.class.getResource("/icons/Info.png"));
errorIcon = new ImageIcon(UIHelper.class.getResource("/icons/Error.png"));
confirmIcon = new ImageIcon(UIHelper.class.getResource("/icons/Question.png"));
warningIcon = new ImageIcon(UIHelper.class.getResource("/icons/Warning.png"));
}
public static void info(String message) {
JOptionPane.showMessageDialog(null, message, "Informate",
JOptionPane.INFORMATION_MESSAGE, infoIcon);
}
public static void warning(String message) {
JOptionPane.showMessageDialog(null, message, "Kujdes",
JOptionPane.WARNING_MESSAGE, warningIcon);
}
public static void error(String message) {
JOptionPane.showMessageDialog(null, message, "Gabim",
JOptionPane.ERROR_MESSAGE, errorIcon);
}
public static int confirm(String message) {
return JOptionPane.showConfirmDialog(null, "A jeni te sigurt per veprimin?", "Konfirmo",
JOptionPane.YES_NO_OPTION,
JOptionPane.QUESTION_MESSAGE, confirmIcon);
}
}