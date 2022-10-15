package com.probitacademy.biblioteka;
import javax.swing.SwingUtilities;
import com.probitacademy.biblioteka.ui.LoginFrame;
public class App {
public static void main(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
@Override
public void run() {
	new LoginFrame();
}
});
}
}