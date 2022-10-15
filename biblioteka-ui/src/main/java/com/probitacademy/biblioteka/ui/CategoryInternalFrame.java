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
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import com.probitacademy.biblioteka.dao.CategoryDao;
import com.probitacademy.biblioteka.domain.Category;
import com.probitacademy.biblioteka.ui.table.CustomizedTable;
import com.probitacademy.biblioteka.ui.tablemodels.CategoryTableModel;
import com.probitacademy.biblioteka.ui.util.UIHelper;
public class CategoryInternalFrame extends BaseInternalFrame<Category> {
private CategoryTableModel model;
private CustomizedTable tblCategories;
private JScrollPane scrPaneCategories;
private ArrayList<Category> elements;
private JTextField txtID;
private JTextField txtName;
private JButton btnNewCategory;
private JButton btnDeleteCategory;
private JButton btnAbortEditCategory;
private JButton btnSaveCategory;
private Category SELECTED_CATEGORY = new Category();
private GridBagConstraints labelConstraints;
private GridBagConstraints fieldConstraints;
private GridBagConstraints buttonConstraints;
public CategoryInternalFrame(String title) {
super(title, UIHelper.repairIcon, new CategoryDao());
new GetCategoriesTask().execute();
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
String[] columns = { "ID", "Name" };
model = new CategoryTableModel(columns, elements);
tblCategories = new CustomizedTable(model);
tblCategories.addMouseListener(new RowClickListener());
scrPaneCategories = new JScrollPane();
scrPaneCategories.setViewportView(tblCategories);
pnlTable.add(scrPaneCategories, BorderLayout.CENTER);
return pnlTable;
}
public JPanel createEditPanel() {
// GridBagLayout huazuar nga
//http://javatechniques.com/blog/gridbaglayout-example-a-simple-form-layout/
JPanel panel = new JPanel(new GridBagLayout());
panel.setBorder(BorderFactory.createTitledBorder("Kategoria"));
addLabel(new JLabel("ID"), panel);
txtID = new JTextField(20);
txtID.setEditable(false);
addField(txtID, panel);
addLabel(new JLabel("Emri"), panel);
txtName = new JTextField(20);
addField(txtName, panel);
btnNewCategory = new JButton();
btnNewCategory.setAction(new NewAction());
btnNewCategory.setToolTipText("I ri");
addButton(btnNewCategory, panel);
btnDeleteCategory = new JButton();
btnDeleteCategory.setAction(new DeleteAction());
btnDeleteCategory.setToolTipText("Fshije");
addButton(btnDeleteCategory, panel);
btnAbortEditCategory = new JButton();
btnAbortEditCategory.setAction(new AbortEditAction());
btnAbortEditCategory.setToolTipText("Anulo ndryshimin");
addButton(btnAbortEditCategory, panel);
btnSaveCategory = new JButton();
btnSaveCategory.setAction(new SaveAction());
btnSaveCategory.setToolTipText("Ruaj");
addButton(btnSaveCategory, panel);
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
private class GetCategoriesTask extends SwingWorker<Void, Void> {
private List<Category> list;
@Override
protected Void doInBackground() throws Exception {
list = baseDao.getAll();
return null;
}
@Override
protected void done() {
model.setData(list);
updateFooterMessage("Finished loading data.");
}
}
private void fillEditPanel() {
if (SELECTED_CATEGORY != null) {
txtID.setText("" + SELECTED_CATEGORY.getId());
txtName.setText(SELECTED_CATEGORY.getName());
} else {
txtID.setText("");
txtName.setText("");
}
}
private class NewAction extends AbstractAction {
private NewAction() {
super(null, UIHelper.newIcon);
}
@Override
public void actionPerformed(ActionEvent arg0) {
SELECTED_CATEGORY = new Category();
fillEditPanel();
}
}
private class AbortEditAction extends AbstractAction {
public AbortEditAction() {
super(null, UIHelper.abortIcon);
}
@Override
public void actionPerformed(ActionEvent arg0) {
tblCategories.clearSelection();
SELECTED_CATEGORY = new Category();
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
try {
int affected = -1;
SELECTED_CATEGORY.setName(txtName.getText());
affected =
baseDao.save(SELECTED_CATEGORY);
if (affected > 0) {
model.remove(SELECTED_CATEGORY);
model.add(SELECTED_CATEGORY);
} else {
UIHelper.error("Te dhenat nuk jane ruajtur!");
}
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
int selectedRowIndex = tblCategories.getSelectedRow();
if (selectedRowIndex != -1) {
SELECTED_CATEGORY = model.get(selectedRowIndex);
int result = UIHelper.confirm("A jeni te sigurt per veprimin");
if (result == JOptionPane.YES_OPTION) {
try {
int affected =
baseDao.delete(SELECTED_CATEGORY);
if (affected > 0) {
model.remove(SELECTED_CATEGORY);
SELECTED_CATEGORY = null;
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
int row = tblCategories.getSelectedRow();
SELECTED_CATEGORY = model.get(row);
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
