package view;

import javax.swing.*;
import java.util.ArrayList;

public class CheckEmptyResult {
    public static void checkResultIsEmpty(ArrayList arrayList, JButton button) {
        if (arrayList.isEmpty()) {
            if (button != null) {
                button.setEnabled(false);
            }
            JOptionPane.showMessageDialog(null, "Aucun résultat", "Informations", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
