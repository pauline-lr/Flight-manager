package view.panels.menuBarPanels.searchingPanels;

import controller.ApplicationController;
import exception.DataBaseConnectionException;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFlightsByPilotPanel extends JPanel {
    public SearchFlightsByPilotPanel(MenuWindow menuWindow, ApplicationController controller) throws SQLException, DataBaseConnectionException {
        SearchFlightsByPilotForm pilotForm = new SearchFlightsByPilotForm(controller);
        this.setLayout(new BorderLayout());
        this.add(pilotForm, BorderLayout.PAGE_START);
      /*  this.add(new ButtonsPanel(menuWindow, "SearchPilot", this, pilotForm.getPilotId(),
                "Rechercher", controller), BorderLayout.PAGE_END);*/

    }

    public static class SearchFlightsByPilotForm extends JPanel {
        private ApplicationController controller;
        private JLabel pilotLabel;
        private JComboBox pilotComboBox;
        private Font font = new Font(null, Font.BOLD, 13);

        public SearchFlightsByPilotForm(ApplicationController controller) throws SQLException, DataBaseConnectionException {
            this.controller = controller;
            this.setLayout(new GridLayout(14, 2, 5, 5));

            createFlightsByPilotForm();
        }

        public void createFlightsByPilotForm() throws SQLException, DataBaseConnectionException {
            pilotLabel = new JLabel("    Choisissez le pilote");
            pilotLabel.setFont(font);
            pilotLabel.setHorizontalAlignment(SwingConstants.LEFT);
            add(pilotLabel);

            pilotComboBox = new JComboBox(controller.getAllPilotsForComboBox());
            this.add(pilotComboBox);
        }

        public String getPilotId() {
            String pilotText = (String) pilotComboBox.getSelectedItem();
            return getId(pilotText);
        }

        public String getId(String text) {
            Pattern pattern = Pattern.compile("^\\w+(?=\\s-\\s)");
            Matcher matcher = pattern.matcher(text);
            String id = null;
            if (matcher.find()) {
                id = matcher.group();
            }
            return id;
        }
    }
}
