package view.panels.menuBarPanels.listingPanels;

import controller.ApplicationController;
import view.table.FlightTable;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;


public class AllFlightsPanel extends JPanel {
    public AllFlightsPanel(MenuWindow menuWindow, ApplicationController controller) {
        FlightTable flightTable = new FlightTable(controller);
        JTable table = new JTable(flightTable);
        this.add(BorderLayout.CENTER, new JScrollPane(table));
    }
}