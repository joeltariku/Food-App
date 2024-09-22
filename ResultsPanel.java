import javax.swing.*;

import api.Eaties;
import api.Place;

import java.awt.event.*;
import java.awt.*;

public class ResultsPanel extends ElementInListPanel {

    private final JButton saveRestButton = new JButton("<html>Save<br>Restaurant<html>");
    private Eaties eaties;
    private Place foodPlace;

    public ResultsPanel(SavedRestaurantsScreen otherScreen, FilterScreen mainScreen, int i) {
        super();

        try {
            eaties = mainScreen.eaties;
        } catch (Exception e) {

        }

        foodPlace = eaties.getPlaces().getPlaces()[i];

        saveRestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                otherScreen.addRestaurant(i);
                if (otherScreen.getRestIsSaved()) {
                    JOptionPane.showMessageDialog(null, "That restaurant has been previously added.");
                } else {
                    JOptionPane.showMessageDialog(null, "That restaurant has been added!");
                }
                saveRestButton.setEnabled(false);
            }
        });

        addElements();
    }

    @Override
    protected void addElements() {
        place.setText(String.format("<html><div style=\"width:%dpx;\">%s\n%s</div></html>", 90, "Place: ",
                foodPlace.getText()));
        address.setText(String.format("<html><div style=\"width:%dpx;\">%s\n%s</div></html>", 90, "Address:",
                foodPlace.getFormattedAddress()));
        add(new JLabel(String.format("<html><div style=\"width:%dpx;\">%s\n%.1f</div></html>", 90, "Overall Rating: ",
                foodPlace.getRating())));
        add(saveRestButton);
    }

}