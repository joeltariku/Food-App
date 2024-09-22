import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class SavedRestaurantsScreen extends SubScreen {

    private final JButton addPlaceButton = new JButton("");
    private boolean restIsSaved;

    public SavedRestaurantsScreen(FilterScreen mainScreen) {
        super(mainScreen);

        headerLabel = new JLabel("Saved Restaurants", SwingConstants.CENTER);
        headerLabel.setFont(new Font("ComicSansMS", Font.PLAIN, 45));

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                moveToMainScreen();
            }
        });
    }

    protected void organizeCompoments() {
        frame.setContentPane(cp);

        setDefaultGridbagLayout();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0.0;

        allowMultiplePerRow(true);
        setUpComponent(exitButton);
        allowMultiplePerRow(false);
        setUpComponent(headerLabel);

        scrollPanel.add(addPlaceButton);
        setUpComponent(scrollList);
        scrollList.setPreferredSize(new Dimension(500, 500));
        scrollList.setMinimumSize(new Dimension(500, 500));
    }

    public void addRestaurant(int i) {
        GridLayout theLayout = ((GridLayout) (scrollPanel.getLayout()));
        theLayout.setRows(theLayout.getRows() + 1);
        if (scrollPanel.getComponentCount() > 0) {
            scrollPanel.remove(scrollPanel.getComponentCount() - 1);
        }

        restIsSaved = false;
        scrollPanel.add(new SavedRestaurantPanel(scrollPanel, mainScreen, i));
        for (Component c : scrollPanel.getComponents()) {
            if (c instanceof SavedRestaurantPanel) {
                SavedRestaurantPanel s = (SavedRestaurantPanel) c;
                if (s.getFoodPlaceText().equals(mainScreen.eaties.getPlaces().getPlaces()[i].getText())) {
                    restIsSaved = true;
                }
            }
        }
        if (restIsSaved == false) {
            scrollPanel.add(new SavedRestaurantPanel(scrollPanel, mainScreen, i));
        }

        scrollPanel.add(addPlaceButton);
        refreshFrameVisuals();
    }

    public boolean getRestIsSaved() {
        return restIsSaved;
    }
}