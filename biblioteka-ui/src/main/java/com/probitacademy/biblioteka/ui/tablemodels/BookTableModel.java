package com.probitacademy.biblioteka.ui.tablemodels;
import java.util.List;
import com.probitacademy.biblioteka.domain.Book;
public class BookTableModel extends BaseTableModel<Book> {
public BookTableModel() {
}
public BookTableModel(String[] columns, List<Book> elements) {
super(columns, elements);
}
@Override
public Object getValueAt(int rowIndex, int columnIndex) {
switch (columnIndex) {
case 0:
return elements.get(rowIndex).getId();
case 1:
return elements.get(rowIndex).getTitle();
case 2:
return elements.get(rowIndex).getIsbn();
case 3:
return elements.get(rowIndex).getPublishDate();
default:
break;
}
return null;
}
}