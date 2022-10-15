package com.probitacademy.biblioteka.ui.tablemodels;
import java.util.List;
import com.probitacademy.biblioteka.domain.Category;
public class CategoryTableModel extends BaseTableModel<Category> {
public CategoryTableModel(String[] columns, List<Category> elements) {
super(columns, elements);
}
@Override
public Object getValueAt(int rowIndex, int columnIndex) {
Category category = elements.get(rowIndex);
if (columnIndex == 0) {
return category.getId();
} else {
return category.getName();
}
}
}