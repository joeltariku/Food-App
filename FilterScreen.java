import javax.swing.*;

import api.Eaties;

import java.awt.event.*;
import java.awt.*;

public class FilterScreen extends Screen {

    private final JLabel craveLabel = new JLabel("What food do you want?", SwingConstants.CENTER);
    private final JLabel whereLabel = new JLabel("Where are you", SwingConstants.CENTER);
    private final JLabel serviesLabel = new JLabel("Additional Services:", SwingConstants.CENTER);
    private final SpecialTextField craveField = new SpecialTextField("Food");
    private final SpecialTextField whereField = new SpecialTextField("Near Me");

    private final JButton resultsButton = new JButton("Give \'em to me!");
    private final JButton savedButton = new JButton("<html>Saved<br>Places</html>");
    private SavedRestaurantsScreen savedRestaurants = new SavedRestaurantsScreen(this);
    private ResultsScreen results = new ResultsScreen(this);

    protected Eaties eaties;

    public FilterScreen() {
        super();

        refreshFrameVisuals();
        cp = new Container();
        layout = new GridBagLayout();
        c = new GridBagConstraints();
        cp.setLayout(layout);

        // refreshFrameVisuals();
        craveLabel.setFont(new Font("ComicSansMS", Font.PLAIN, 30));
        whereLabel.setFont(new Font("ComicSansMS", Font.PLAIN, 30));
        serviesLabel.setFont(new Font("ComicSansMS", Font.PLAIN, 30));

        craveField.startTextField();
        whereField.startTextField();

        try {
            eaties = new Eaties();
        } catch (Exception e) {
        }

        resultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                eaties.resetFields(craveField.getText() + "near" + whereField.getText());
                try {
                    eaties.run();
                } catch (Exception e) {

                }
                moveToScreen(results);
            }
        });

        savedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                moveToScreen(savedRestaurants);
            }
        });

        organizeCompoments();
    }

    protected void organizeCompoments() {
        frame.setContentPane(cp);

        setDefaultGridbagLayout();

        allowMultiplePerRow(true);
        setUpComponent(craveLabel);
        allowMultiplePerRow(false);
        setUpComponent(savedButton);
        setUpComponent(craveField);

        setUpComponent(whereLabel);
        setUpComponent(whereField);

        allowMultiplePerRow(true);

        allowMultiplePerRow(false);

        setUpComponent(resultsButton);

        frame.setVisible(true); // set sizes and locations of components AFTER THIS LINE

        craveLabel.setSize(craveLabel.getMinimumSize());
        savedButton.setSize(55, 40);

        craveLabel.setLocation((frame.getWidth() / 2) - (craveLabel.getWidth() / 2), craveLabel.getY());
        savedButton.setLocation(frame.getWidth() - savedButton.getWidth() - 5, savedButton.getY());

        // refreshFrameVisuals();
    }

    public SavedRestaurantsScreen getSavedRestaurants() {
        return savedRestaurants;
    }

    public ResultsScreen getResults() {
        return results;
    }

    private void moveToScreen(SubScreen newScreen) {
        cp.removeAll();
        newScreen.organizeCompoments();
        refreshFrameVisuals();
    }

    public static void main(String[] args) {
        FilterScreen runner = new FilterScreen();
    }
}
