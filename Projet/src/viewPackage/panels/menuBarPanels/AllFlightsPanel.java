package viewPackage.panels.menuBarPanels;

import viewPackage.windows.MenuWindow;

import javax.swing.*;

/*
De même, l’affichage dans une JTable du résultat d’une recherche (select)
nécessite la création d’une sous-classe d’AbstractTableModel. Cette sous-classe
est liée au composant swing JTable. Cette sous-classe d’AbstractTableModel fait
donc bien partie de la couche View et non pas DataAccess : une ArrayList
d’objets est créée par la couche DataAccess et remontée à la couche View qui se
charge de créer un objet d’une sous-classe d’AbstractTableModel pour l’affichage
dans une JTable.
 */

public class AllFlightsPanel extends JPanel {
    public AllFlightsPanel(MenuWindow menuWindow){

    }
}
