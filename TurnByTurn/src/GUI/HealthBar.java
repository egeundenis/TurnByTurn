package GUI;

import java.awt.Color;
import java.awt.ComponentOrientation;

import javax.swing.*;

import Brawlers.Brawler;

public class HealthBar {
    public JProgressBar healthBar;
    public int maxHealth;

    public HealthBar(int maxeHealth, String player) {
        this.maxHealth = maxeHealth;
        healthBar = new JProgressBar(0, maxHealth);
        healthBar.setValue(maxHealth);
        healthBar.setStringPainted(true);
        healthBar.setForeground(Color.RED);
        healthBar.setBackground(Color.CYAN);
        healthBar.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        healthBar.setStringPainted(false);
        if (player == "P2")
        	 healthBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

    }

    public void updateHealth(Brawler A) {
        healthBar.setValue(A.HP);
    }
    
    public void updateShield(Brawler A) {
        healthBar.setValue(A.shield);
    }

    public JProgressBar getHealthBar() {
        return healthBar;
    }
}
