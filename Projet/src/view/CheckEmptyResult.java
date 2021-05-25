package view;

import javax.swing.*;
import java.util.ArrayList;

public class CheckEmptyResult {
    public static void checkResultIsEmpty(ArrayList arrayList) {
        if (arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun résultat", "Informations", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
