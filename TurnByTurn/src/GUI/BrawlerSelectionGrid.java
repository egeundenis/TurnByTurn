package GUI;

import javax.swing.*;

import Brawlers.Brawler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

@SuppressWarnings("serial")
public class BrawlerSelectionGrid extends JPanel {

    private ButtonGroup buttonGroup; // Declare ButtonGroup as an instance variable
    private JLabel welcomeLabel; // Reference to the welcome label to be updated
    private JLabel explLabel;
    public boolean randomlyChosen = false;

    public BrawlerSelectionGrid(JLabel welcomeLabel, JLabel explLabel) {
        this.welcomeLabel = welcomeLabel; // Set the reference to the welcome label
        this.explLabel = explLabel;

        // Set up the panel with a grid layout
        setLayout(new GridLayout(6, 10)); // Example: 6x6 grid layout
        setBackground(Color.LIGHT_GRAY);

        // Create radio buttons for brawlers and add to the panel
        String[] brawlers = {
                "Todd", "Susan", "Mark", "Lisa", "Raven", "Jester",
                "Finn", "Timmy", "Kasse", "John", "Missy", "Zach",
                "Simon", "Felix", "Imelda", "Betty", "Light", "Hassan",
                "Nanni", "Ignace", "Gusty", "Anvaa", "Vollie", "Giran",
                "Clyde", "Amber", "Anton", "Qirale", "Olea", "Itan", "Louis", "Pine"
        };

        buttonGroup = new ButtonGroup(); // Initialize the ButtonGroup

        for (String brawler : brawlers) {
            JRadioButton radioButton = new JRadioButton(brawler);
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

    public String getSelectedBrawler() {
        // Retrieve the selected radio button's text
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText(); // Return the selected radio button's text
            }
        }
        return null; // Return null if no button is selected
    }
    
    public void changeLabels(String brawler) {
    	
    	welcomeLabel.setText(brawler.toUpperCase());
        welcomeLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
        StringBuilder sb = new StringBuilder();
        sb.append("<html><p style=\"width:110px\">");
    	sb.append(Brawler.GenerateBrawler(brawler).getExplanation()); //here
   	    sb.append("<html>");
        explLabel.setText(sb.toString());
    }
}