package view.panels.menuBarPanels.listingPanels;

import controller.ApplicationController;
import view.table.ListAllFlightsTable;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;


public class ListAllFlightsPanel extends JPanel {
    public ListAllFlightsPanel(MenuWindow menuWindow, ApplicationController controller) {
        ListAllFlightsTable listAllFlightsTable = new ListAllFlightsTable(controller);
        JTable table = new JTable(listAllFlightsTable);
        this.add(BorderLayout.CENTER, new JScrollPane(table));
    }
}