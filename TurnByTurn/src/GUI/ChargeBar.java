package GUI;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;

import javax.swing.*;

import Brawlers.Brawler;

public class ChargeBar {
	
    private JProgressBar chargeBar;
    private int requiredCharge;

    public ChargeBar(Brawler A, String player) {
        this.requiredCharge = 200;
        chargeBar = new JProgressBar(0, requiredCharge);
        chargeBar.setValue(0);
        chargeBar.setStringPainted(true);
        chargeBar.setForeground(Color.yellow);
        chargeBar.setBackground(Color.cyan);
        chargeBar.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        chargeBar.setString("l");
        chargeBar.setFont(new Font("Arial",Font.BOLD,15));

        chargeBar.setStringPainted(true);
        if (player == "P2")
        	 chargeBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

    }

    public void updateCharge(Brawler A) {

        chargeBar.setValue(A.SuperCharge);
    }

    public JProgressBar getChargeBar() {
        return chargeBar;
    }
}
