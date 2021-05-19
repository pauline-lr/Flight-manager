package view.panels.menuBarPanels.listingFlightsPanels;

import controller.ApplicationController;
import view.tables.ListAllFlightsTable;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;


public class ListAllFlightsPanel extends JPanel {
    public ListAllFlightsPanel(MenuWindow menuWindow, ApplicationController controller) {
        ListAllFlightsTable listAllFlightsTable = new ListAllFlightsTable(controller);
        JTable table = new JTable(listAllFlightsTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}