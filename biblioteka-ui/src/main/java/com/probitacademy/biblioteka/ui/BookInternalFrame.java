package com.probitacademy.biblioteka.ui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import com.probitacademy.biblioteka.dao.BookDao;
import com.probitacademy.biblioteka.dao.CategoryDao;
import com.probitacademy.biblioteka.domain.Book;
import com.probitacademy.biblioteka.domain.Category;
import com.probitacademy.biblioteka.ui.table.CustomizedTable;
import com.probitacademy.biblioteka.ui.tablemodels.BookTableModel;
import com.probitacademy.biblioteka.ui.util.UIHelper;
public class BookInternalFrame extends BaseInternalFrame<Book> {
private BookTableModel model;
private CustomizedTable tblBooks;
private JScrollPane scrPaneBooks;
private ArrayList<Book> elements;
private JTextField txtID;
private JTextField txtTitle;
private JTextField txtISBN;
private JTextField txtPublishDate;
private JComboBox<Category> cboCategories;
private JButton btnNewBook;
private JButton btnDeleteBook;
private JButton btnAbortEditBook;
private JButton btnSaveBook;
private Book SELECTED_BOOK = new Book();
private GridBagConstraints labelConstraints;
private GridBagConstraints fieldConstraints;
private GridBagConstraints buttonConstraints;
public BookInternalFrame(String title) {
super(title, UIHelper.notesIcon, new BookDao());
new GetBooksTask().execute();
}
@Override
public void doCustomLayout() {
JPanel pnlContent = new JPanel();
pnlContent.setLayout(new BorderLayout(5, 5));
createConstraints();
JPanel editPanel = createEditPanel();
JPanel tablePanel = createTablePanel();
pnlContent.add(editPanel, BorderLayout.NORTH);
Box.createHorizontalStrut(10);
pnlContent.add(tablePanel, BorderLayout.CENTER);
updateFooterMessage("Loading data...");
getContentPane().add(pnlContent, BorderLayout.CENTER);
}
private void createConstraints() {
labelConstraints = new GridBagConstraints();
labelConstraints.fill = GridBagConstraints.HORIZONTAL;
labelConstraints.anchor = GridBagConstraints.NORTHWEST;
labelConstraints.weightx = 0;
labelConstraints.gridwidth = 1;
labelConstraints.insets = new Insets(2, 2, 2, 2);
fieldConstraints = (GridBagConstraints)
labelConstraints.clone();
fieldConstraints.weightx = 1;
fieldConstraints.gridwidth = GridBagConstraints.REMAINDER;
buttonConstraints = (GridBagConstraints)
labelConstraints.clone();
buttonConstraints.fill = GridBagConstraints.NONE;
}
public JPanel createTablePanel() {
JPanel pnlTable = new JPanel();
pnlTable.setBorder(BorderFactory.createTitledBorder("Lista e librave"));
pnlTable.setLayout(new BorderLayout(5, 5));
String[] columns = { "ID", "Title", "ISBN", "Publish Date" };
model = new BookTableModel(columns, elements);
tblBooks = new CustomizedTable(model);
tblBooks.addMouseListener(new RowClickListener());
scrPaneBooks = new JScrollPane();
scrPaneBooks.setViewportView(tblBooks);
pnlTable.add(scrPaneBooks, BorderLayout.CENTER);
return pnlTable;
}
public JPanel createEditPanel() {
// GridBagLayout huazuar nga
//http://javatechniques.com/blog/gridbaglayout-example-a-simple-form-layout/
JPanel panel = new JPanel(new GridBagLayout());
panel.setBorder(BorderFactory.createTitledBorder("Libri"));
addLabel(new JLabel("ID"), panel);
txtID = new JTextField(20);
txtID.setEditable(false);
addField(txtID, panel);
addLabel(new JLabel("Kategoria"), panel);
cboCategories = new JComboBox<Category>();
ArrayList<Category> categories;
try {
categories = new CategoryDao().getAll();
for (Category category : categories) {
cboCategories.addItem(category);
}
} catch (Exception e) {
e.printStackTrace();
}
addField(cboCategories, panel);
addLabel(new JLabel("Title"), panel);
txtTitle = new JTextField(20);
addField(txtTitle, panel);
addLabel(new JLabel("ISBN"), panel);
txtISBN = new JTextField(20);
addField(txtISBN, panel);
addLabel(new JLabel("Publish date"), panel);
txtPublishDate = new JTextField(20);
addField(txtPublishDate, panel);
btnNewBook = new JButton();
btnNewBook.setAction(new NewAction());
btnNewBook.setToolTipText("I ri");
addButton(btnNewBook, panel);
btnDeleteBook = new JButton();
btnDeleteBook.setAction(new DeleteAction());
btnDeleteBook.setToolTipText("Fshije");
addButton(btnDeleteBook, panel);
btnAbortEditBook = new JButton();
btnAbortEditBook.setAction(new AbortEditAction());
btnAbortEditBook.setToolTipText("Anulo ndryshimin");
addButton(btnAbortEditBook, panel);
btnSaveBook = new JButton();
btnSaveBook.setAction(new SaveAction());
btnSaveBook.setToolTipText("Ruaj");
addButton(btnSaveBook, panel);
return panel;
}
public void addField(Component c, Container panel) {
GridBagLayout layout = (GridBagLayout) panel.getLayout();
layout.setConstraints(c, fieldConstraints);
panel.add(c);
}
public void addLabel(Component c, Container panel) {
GridBagLayout layout = (GridBagLayout) panel.getLayout();
layout.setConstraints(c, labelConstraints);
panel.add(c);
}
public void addButton(Component c, Container panel) {
GridBagLayout layout = (GridBagLayout) panel.getLayout();
layout.setConstraints(c, buttonConstraints);
panel.add(c);
}
private class GetBooksTask extends SwingWorker<Void, Void> {
private List<Book> list;
@Override
protected Void doInBackground() throws Exception {
list = baseDao.getAll();
return null;
}
@Override
protected void done() {
model.setData(list);
updateFooterMessage("Finished.");
}
}
private void fillEditPanel() {
txtID.setText("" + SELECTED_BOOK.getId());
txtTitle.setText(SELECTED_BOOK.getTitle());
txtISBN.setText(SELECTED_BOOK.getIsbn());
if (SELECTED_BOOK.getPublishDate() != null) {
String data = new
SimpleDateFormat("dd/MM/yyyy").format(SELECTED_BOOK.getPublishDate());
txtPublishDate.setText(data);
}else {
txtPublishDate.setText("");
}
try {
Category category = new
CategoryDao().get(SELECTED_BOOK.getCategoryId());
cboCategories.setSelectedItem(category);
} catch (Exception e) {
e.printStackTrace();
}
}
private class NewAction extends AbstractAction {
private NewAction() {
super(null, UIHelper.newIcon);
}
@Override
public void actionPerformed(ActionEvent arg0) {
SELECTED_BOOK = new Book();
fillEditPanel();
}
}
private class AbortEditAction extends AbstractAction {
public AbortEditAction() {
super(null, UIHelper.abortIcon);
}
@Override
public void actionPerformed(ActionEvent arg0) {
tblBooks.clearSelection();
SELECTED_BOOK = new Book();
fillEditPanel();
}
}
private class SaveAction extends AbstractAction {
public SaveAction() {
super(null, UIHelper.saveIcon);
}
@Override
public void actionPerformed(ActionEvent ae) {
if (validate()) {
SELECTED_BOOK.setTitle(txtTitle.getText());
Category c = (Category)
cboCategories.getSelectedItem();
SELECTED_BOOK.setCategoryId(c.getId());
SELECTED_BOOK.setIsbn(txtISBN.getText());
SELECTED_BOOK.setPublishDate(new
java.util.Date());
try {
int affected = -1;
if (SELECTED_BOOK.getId() == 0)
affected =
baseDao.save(SELECTED_BOOK);
else
affected =
baseDao.update(SELECTED_BOOK);
if (affected > 0)
model.add(SELECTED_BOOK);
else
UIHelper.error("Te dhenat nuk jane ruajtur!");
} catch (Exception e) {
e.printStackTrace();
}
}
}
private boolean validate() {
return true;
}
}
private class DeleteAction extends AbstractAction {
public DeleteAction() {
super(null, UIHelper.deleteIcon);
}
@Override
public void actionPerformed(ActionEvent arg0) {
int selectedRowIndex = tblBooks.getSelectedRow();
if (selectedRowIndex != -1) {
SELECTED_BOOK = model.get(selectedRowIndex);
int result = UIHelper.confirm("A jeni te sigurt per veprimin?");
if (result == JOptionPane.YES_OPTION) {
try {
int affected =
baseDao.delete(SELECTED_BOOK);
if (affected > 0) {
model.remove(SELECTED_BOOK);
SELECTED_BOOK = new
Book();
fillEditPanel();
} else {
UIHelper.error("Rekordi nuk eshte fshire!!");
}
} catch (Exception e) {
e.printStackTrace();
}
}
}
}
}
public class RowClickListener implements MouseListener {
@Override
public void mouseClicked(MouseEvent e) {
int row = tblBooks.getSelectedRow();
SELECTED_BOOK = model.get(row);
fillEditPanel();
}
@Override
public void mouseEntered(MouseEvent e) {
}
@Override
public void mouseExited(MouseEvent e) {
}
@Override
public void mousePressed(MouseEvent e) {
}
@Override
public void mouseReleased(MouseEvent e) {
	
}
}
}
