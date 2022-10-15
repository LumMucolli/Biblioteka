package com.probitacademy.biblioteka.ui.tablemodels;
import java.util.List;
import com.probitacademy.biblioteka.domain.User;
public class UserTableModel extends BaseTableModel<User> {
public UserTableModel(String[] columns, List<User> elements) {
super(columns, elements);
}
@Override
public Object getValueAt(int row, int col) {
switch (col) {
case 0:
return elements.get(row).getId();
case 1:
return elements.get(row).getUsername();
case 2:
return elements.get(row).getPassword();
case 3:
return elements.get(row).getLastLogin();
default:
break;
}
return null;
}
}
