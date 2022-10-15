package com.probitacademy.biblioteka.ui.table;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.TableModel;
public class CustomizedTable extends JTable {
public CustomizedTable(TableModel model) {
super(model);
setRowHeight(30);
setFont(new Font("Tahoma", Font.PLAIN, 16));
getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
}
}