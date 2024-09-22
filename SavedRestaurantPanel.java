import javax.swing.*;

import api.Eaties;
import api.Place;

import java.awt.event.*;
import java.awt.*;
import java.lang.IllegalArgumentException;

public class SavedRestaurantPanel extends ElementInListPanel {

    private static int totalPanels = 0;
    private int assignedIndex;
    private JPanel panelIn;
    private final JLabel notableFoodsLabel = new JLabel("Notable Foods:");
    private final JButton addFoodButton = new JButton("<html>Add<br>Food<html>");
    private final JButton removeButton = new JButton("Remove");
    private final JPanel foodPanel = new JPanel(new GridBagLayout());
    private final GridBagConstraints foodConstraints = new GridBagConstraints();
    private Eaties eaties;
    private Place foodPlace;

    public SavedRestaurantPanel(JPanel panel, FilterScreen mainScreen, int i) {
        super();

        try {
            eaties = mainScreen.eaties;
            if (eaties.getPlaces() != null) {
                foodPlace = eaties.getPlaces().getPlaces()[i];
            } else {
                foodPlace = null;
            }
        } catch (Exception e) {

        }

        panelIn = panel;

        assignedIndex = totalPanels;
        totalPanels++;

        setDefaultGridbagLayout();

        addFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                GridBagLayout theLayout = ((GridBagLayout) (foodPanel.getLayout()));
                SpecialTextField foodField = new SpecialTextField("Good Food");
                JButton noButton = new JButton("No");
                noButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent a) {
                        foodPanel.remove(foodField);
                        foodPanel.remove(noButton);
                        Screen.refreshFrameVisuals();
                    }
                });

                foodPanel.remove(foodPanel.getComponentCount() - 1);
                theLayout.setConstraints(foodField, foodConstraints);
                foodPanel.add(foodField);

                foodConstraints.gridwidth = GridBagConstraints.REMAINDER;

                theLayout.setConstraints(noButton, foodConstraints);
                foodPanel.add(noButton);
                theLayout.setConstraints(addFoodButton, foodConstraints);
                foodPanel.add(addFoodButton);

                setDefaultGridbagLayout();

                Screen.refreshFrameVisuals();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                GridLayout theLayout = ((GridLayout) (panelIn.getLayout()));
                panelIn.remove(assignedIndex);
                theLayout.setRows(theLayout.getRows() - 1);

                totalPanels--;
                updateIndicies(assignedIndex);

                Screen.refreshFrameVisuals();
            }
        });

        addElements();
    }

    public int getAssignedIndex() {
        return assignedIndex;
    }

    public void setAssignedIndex(int newIndex) throws IllegalArgumentException {
        if (newIndex < 0) {
            throw new IllegalArgumentException("newIndex must be greater than 0.");
        } else if (newIndex > panelIn.getComponentCount() - 2) {
            throw new IllegalArgumentException("newIndex must be less than " + (panelIn.getComponentCount() - 1)
                    + ", the index of the last element.");
        }
        assignedIndex = newIndex;
    }

    @Override
    protected void addElements() {
        if (foodPlace != null) {
            place.setText(String.format("<html><div style=\"width:%dpx;\">%s\n%s</div></html>", 90, "Place: ",
                    foodPlace.getText()));
            address.setText(String.format("<html><div style=\"width:%dpx;\">%s\n%s</div></html>", 90, "Address:",
                    foodPlace.getFormattedAddress()));
        } else {
            place.setText("Place: ");
            address.setText("Address: ");
        }

        ((GridBagLayout) (foodPanel.getLayout())).setConstraints(notableFoodsLabel, foodConstraints);
        ((GridBagLayout) (foodPanel.getLayout())).setConstraints(addFoodButton, foodConstraints);

        setDefaultGridbagLayout();
        foodPanel.add(notableFoodsLabel);
        foodPanel.add(addFoodButton);
        add(foodPanel);
        add(removeButton);
    }

    private void setDefaultGridbagLayout() {
        foodConstraints.weightx = 1.0;
        foodConstraints.weighty = 1.0;
        foodConstraints.gridwidth = 1;
        foodConstraints.fill = GridBagConstraints.BOTH;
        foodConstraints.anchor = GridBagConstraints.CENTER;
    }

    private void updateIndicies(int deletedIndex) {
        for (int i = deletedIndex; i < totalPanels; i++) {
            SavedRestaurantPanel elem = (SavedRestaurantPanel) (panelIn.getComponent(i));
            elem.setAssignedIndex(elem.getAssignedIndex() - 1);
        }
    }

    public String getFoodPlaceText() {
        return foodPlace.getText();
    }

}