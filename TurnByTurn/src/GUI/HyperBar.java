package GUI;

import java.awt.Color;
import java.awt.ComponentOrientation;

import javax.swing.*;

import Brawlers.Brawler;

public class HyperBar {
	
    private JProgressBar hyperBar;
    private int requiredCharge;

    public HyperBar(Brawler A, String player) {
    	
        this.requiredCharge = 250;
        hyperBar = new JProgressBar(0, requiredCharge);
        hyperBar.setValue(0);
        hyperBar.setStringPainted(true);
        hyperBar.setForeground(Color.magenta);
        hyperBar.setBackground(Color.cyan);
        hyperBar.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        hyperBar.setString("");
        
        hyperBar.setStringPainted(true);
        if (player == "P2")
        	 hyperBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

    }

    public void updateCharge(Brawler A) {

        hyperBar.setValue(A.HyperCharge);
    }

    public JProgressBar getChargeBar() {
        return hyperBar;
    }
}
