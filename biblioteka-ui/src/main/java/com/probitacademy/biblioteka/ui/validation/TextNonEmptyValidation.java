package com.probitacademy.biblioteka.ui.validation;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import com.probitacademy.biblioteka.ui.util.UIHelper;
public class TextNonEmptyValidation extends InputVerifier {
@Override
public boolean verify(JComponent component) {
if (component instanceof JTextField) {
if (((JTextField) component).getText().length() > 0) {
return true;
} else {
UIHelper.error("Fusha eshte obligative");
return false;
}
}
return true;
}
}