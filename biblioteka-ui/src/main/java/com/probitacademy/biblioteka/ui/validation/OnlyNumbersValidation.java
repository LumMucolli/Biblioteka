package com.probitacademy.biblioteka.ui.validation;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import com.probitacademy.biblioteka.ui.util.UIHelper;
public class OnlyNumbersValidation extends InputVerifier{
@Override
public boolean verify(JComponent component) {
if ( component instanceof JTextField){
String content = ((JTextField) component).getText();
try{
Integer.parseInt(content);
return true;
}catch(Exception ex){
UIHelper.error("Vetem numra please!");
return false;
}
}
return true;
}
}