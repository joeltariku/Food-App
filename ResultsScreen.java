import javax.swing.*;

import api.Eaties;

import java.awt.event.*;
import java.awt.*;

public class ResultsScreen extends SubScreen {

    public ResultsScreen(FilterScreen mainScreen) {
        super(mainScreen);

        headerLabel = new JLabel("Your Results:", SwingConstants.CENTER);
        headerLabel.setFont(new Font("ComicSansMS", Font.PLAIN, 45));

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                if (mainScreen.eaties != null) {
                    mainScreen.eaties.resetFields("");
                }
                scrollPanel.removeAll();
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

        if (mainScreen.eaties.getPlaces().getPlaces() == null) {
            scrollPanel.add(new JLabel("No Results.", SwingConstants.CENTER));
        } else {
            for (int i = 0; i < mainScreen.eaties.getPlaces().getPlaces().length; i++) {
                scrollPanel.add(new ResultsPanel(mainScreen.getSavedRestaurants(), mainScreen, i));
            }
        }
        setUpComponent(scrollList);
        scrollList.setPreferredSize(new Dimension(500, 500));
        scrollList.setMinimumSize(new Dimension(500, 500));
    }

}