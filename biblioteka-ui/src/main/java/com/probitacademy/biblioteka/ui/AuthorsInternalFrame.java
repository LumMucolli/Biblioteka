package com.probitacademy.biblioteka.ui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import com.probitacademy.biblioteka.dao.AuthorDao;
import com.probitacademy.biblioteka.domain.Author;
import com.probitacademy.biblioteka.ui.table.CustomizedTable;
import com.probitacademy.biblioteka.ui.tablemodels.AuthorTableModel;
import com.probitacademy.biblioteka.ui.util.UIHelper;
public class AuthorsInternalFrame extends BaseInternalFrame<Author>
implements ActionListener {
private JTextField txtSearch;
private CustomizedTable table;
private JButton btnNewAuthor;
private JButton btnEditAuthor;
private JButton btnDeleteAuthor;
private JButton btnSearch;
private AuthorTableModel model;
private List<Author> elements;
private ArrayList<Author> filteredElements = new ArrayList<>();
public AuthorsInternalFrame(String title) {
super(title, UIHelper.authorsIcon, new AuthorDao());
new GetAuthors().execute();
}
private void filter(String filterText) {
for (Author author : elements) {
if
(author.getName().toLowerCase().contains(filterText.toLowerCase())
||
author.getLastName().toLowerCase().contains(filterText.toLowerCase())) {
if (!filteredElements.contains(author))
filteredElements.add(author);
} else {
if (filteredElements.contains(author))
filteredElements.remove(author);
}
}
}
private class GetAuthors extends SwingWorker<Void, Void> {
@Override
protected Void doInBackground() throws Exception {
try {
elements = baseDao.getAll();
} catch (Exception e) {
UIHelper.error("Problem me bazen e shenimeve!");
}
return null;
}
@Override
protected void done() {
model.setData(elements);
updateFooterMessage("Finished loading data.");
}
}
@Override
public void actionPerformed(ActionEvent evt) {
if (evt.getSource() == btnNewAuthor) {
Author author = new Author();
AuthorNewEditFrame frm = new AuthorNewEditFrame(author,
(AuthorDao) baseDao);
getParent().add(frm);
try {
frm.setSelected(true);
frm.addInternalFrameListener(new
AuthorFrameListener());
} catch (java.beans.PropertyVetoException e) {
}
} else if (evt.getSource() == btnEditAuthor) {
int selectedRow = table.getSelectedRow();
if (selectedRow != -1) {
Author author = model.get(selectedRow);
model.remove(author);
AuthorNewEditFrame frm = new
AuthorNewEditFrame(author, (AuthorDao) baseDao);
getParent().add(frm);
try {
frm.setSelected(true);
frm.addInternalFrameListener(new
AuthorFrameListener());
} catch (java.beans.PropertyVetoException e) {
}
}
} else if (evt.getSource() == btnDeleteAuthor) {
int selectedRow = table.getSelectedRow();
if (selectedRow != -1) {
int confirm = UIHelper.confirm("A jeni te sigurt??");
if (confirm == JOptionPane.YES_OPTION) {
Author author = model.get(selectedRow);
try {
baseDao.delete(author);
model.remove(author);
} catch (Exception ex) {
ex.printStackTrace();
}
}
}
} else if (evt.getSource() == btnSearch) {
}
}
class AuthorFrameListener implements InternalFrameListener {
@Override
public void internalFrameActivated(InternalFrameEvent arg0) {
}
@Override
public void internalFrameClosed(InternalFrameEvent arg0) {
}
@Override
public void internalFrameClosing(InternalFrameEvent evt) {
AuthorNewEditFrame frm = (AuthorNewEditFrame)
evt.getInternalFrame();
Author author = frm.getAuthor();
if (author != null && author.getId() != 0)
model.add(author);
}
@Override
public void internalFrameDeactivated(InternalFrameEvent arg0) {
}
@Override
public void internalFrameDeiconified(InternalFrameEvent arg0) {
}
@Override
public void internalFrameIconified(InternalFrameEvent arg0) {
}
@Override
public void internalFrameOpened(InternalFrameEvent arg0) {
}
}
@Override
public void doCustomLayout() {
JPanel pnlContent = new JPanel();
getContentPane().add(pnlContent, BorderLayout.CENTER);
pnlContent.setLayout(new BorderLayout(0, 0));
JPanel pnlButtons = new JPanel();
pnlContent.add(pnlButtons, BorderLayout.NORTH);
pnlButtons.setLayout(new BorderLayout());
JPanel pnlLeftButtons = new JPanel(new
FlowLayout(FlowLayout.LEFT));
pnlButtons.add(pnlLeftButtons, BorderLayout.WEST);
btnNewAuthor = new JButton("New");
btnNewAuthor.addActionListener(this);
pnlLeftButtons.add(btnNewAuthor);
btnEditAuthor = new JButton("Edit");
btnEditAuthor.addActionListener(this);
pnlLeftButtons.add(btnEditAuthor);
btnDeleteAuthor = new JButton("Delete");
btnDeleteAuthor.addActionListener(this);
pnlLeftButtons.add(btnDeleteAuthor);
JPanel pnlRightButtons = new JPanel(new
FlowLayout(FlowLayout.RIGHT));
pnlButtons.add(pnlRightButtons, BorderLayout.EAST);
txtSearch = new JTextField();
txtSearch.setFont(normalFont);
txtSearch.setColumns(20);
txtSearch.getDocument().addDocumentListener(new
DocumentListener() {
@Override
public void removeUpdate(DocumentEvent e) {
// kur fshihet nje shkronje
String filterText = txtSearch.getText();
if (filterText.equals("")) {
model.setData(elements);
} else {
filter(filterText);
model.setData(filteredElements);
}
}
@Override
public void insertUpdate(DocumentEvent e) {
// kur shtohet nje shkronje
String filterText = txtSearch.getText();
filter(filterText);
model.setData(filteredElements);
}
@Override
public void changedUpdate(DocumentEvent e) {
}
});
pnlRightButtons.add(txtSearch);
btnSearch = new JButton("");
btnSearch.setIcon(UIHelper.findIcon);
pnlRightButtons.add(btnSearch);
JPanel pnlTable = new JPanel();
pnlTable.setLayout(new BorderLayout(0, 0));
String[] columns = { "ID", "Emri", "Mbiemri", "Shteti" };
model = new AuthorTableModel(columns, elements);
table = new CustomizedTable(model);
table.setFont(new Font("Tahoma", Font.PLAIN, 18));
table.setRowHeight(30);
JScrollPane scrollPane = new JScrollPane(table);
pnlTable.add(scrollPane, BorderLayout.CENTER);
pnlContent.add(pnlTable, BorderLayout.CENTER);
getContentPane().add(pnlContent, BorderLayout.CENTER);
updateFooterMessage("Loading data...");
}
}
