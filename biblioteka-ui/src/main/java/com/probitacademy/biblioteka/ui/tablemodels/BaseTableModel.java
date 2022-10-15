package com.probitacademy.biblioteka.ui.tablemodels;
import java.util.List;
import javax.swing.table.AbstractTableModel;
public abstract class BaseTableModel<E> extends AbstractTableModel {
protected String [] columns;
protected List<E> elements;
public BaseTableModel() {
}
public BaseTableModel(String [] columns, List<E> elements) {
this.columns = columns;
this.elements = elements;
}
@Override
public String getColumnName(int column) {
return columns[column];
}
@Override
public int getColumnCount() {
if (columns != null)
return columns.length;
return 0;
}
@Override
public int getRowCount() {
if (elements != null)
return elements.size();
return 0;
}
public void setData(List<E> elements) {
this.elements = elements;
fireTableDataChanged();//kjo metode eshte e AbstractTableModel
}
public E get(int index) {
if (index >= 0)
return elements.get(index);
return null;
}
public void remove(E element) {
elements.remove(element);
fireTableDataChanged();
}
public void add(E element) {
elements.add(element);
fireTableDataChanged();
}
}