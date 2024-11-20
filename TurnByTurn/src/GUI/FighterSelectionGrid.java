package GUI;

import javax.swing.*;

import Fighters.Fighter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

@SuppressWarnings("serial")
public class FighterSelectionGrid extends JPanel {

    private ButtonGroup buttonGroup; // Declare ButtonGroup as an instance variable
    private JLabel welcomeLabel; // Reference to the welcome label to be updated
    private JLabel explLabel;
    public boolean randomlyChosen = false;

    public FighterSelectionGrid(JLabel welcomeLabel, JLabel explLabel) {
        this.welcomeLabel = welcomeLabel; // Set the reference to the welcome label
        this.explLabel = explLabel;

        // Set up the panel with a grid layout
        setLayout(new GridLayout(7, 10)); // Example: 6x6 grid layout
        setBackground(Color.LIGHT_GRAY);

        // Create radio buttons for fighters and add to the panel
        String[] fighters = {
                "Todd", "Susan", "Mark", "Lisa", "Raven", "Jester",
                "Finn", "Timmy", "Kasse", "John", "Missy", "Zachy",
                "Simon", "Felix", "Imelda", "Betty", "Light", "Hassan",
                "Nanny", "Ignace", "Gusty", "Anvaa", "Vollie", "Giran",
                "Clyde", "Amber", "Anton", "Qirale", "Olea", "Itan", 
                "Louis", "Pine", "Rits", "Gash", "Jack", "June", "Aboa"
        };

        buttonGroup = new ButtonGroup(); // Initialize the ButtonGroup

        for (String fighter : fighters) {
            JRadioButton radioButton = new JRadioButton(fighter);
            radioButton.setBackground(Color.LIGHT_GRAY);
            radioButton.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
            buttonGroup.add(radioButton);
            add(radioButton);

            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JRadioButton selectedButton = (JRadioButton) e.getSource();
                    if (selectedButton.isSelected()) {
                    	randomlyChosen = false;
                        changeLabels(selectedButton.getText());
                    }
                }
            });
        }
    }

    public String getSelectedFighter() {
        // Retrieve the selected radio button's text
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText(); // Return the selected radio button's text
            }
        }
        return null; // Return null if no button is selected
    }
    
    public void changeLabels(String fighter) {
    	
    	welcomeLabel.setText(fighter.toUpperCase());
        welcomeLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
        StringBuilder sb = new StringBuilder();
        sb.append("<html><p style=\"width:110px\">");
    	sb.append(Fighter.GenerateFighter(fighter).getExplanation()); //here
   	    sb.append("<html>");
        explLabel.setText(sb.toString());
    }
}