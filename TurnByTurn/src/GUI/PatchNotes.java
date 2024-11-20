package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class PatchNotes {

	public PatchNotes() {}

	public static void patchNotes(String patch) {
		
		JFrame window = new JFrame("Turn by Turn - " + patch);
        window.setSize(850, 800);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.getContentPane().setBackground(Color.darkGray);
        window.setLayout(null);
        window.setIconImage(new ImageIcon("res/images/turnbyturn.png").getImage());
        Container con = window.getContentPane();

        JLabel title = new JLabel(patch, SwingConstants.CENTER);
        title.setBounds(0, 0, 830, 45);
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        con.add(title);

        JPanel notesPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 7, 10));
        notesPanel.setBounds(24, 40, 790, 710);
        notesPanel.setBackground(Color.LIGHT_GRAY);
        notesPanel.setBorder(BorderFactory.createLineBorder(Color.black, 6));

        JLabel notes = new JLabel("");
        notes.setForeground(Color.black);
        notes.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
        
        StringBuilder patchNotesContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/GUI/patchnotes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                patchNotesContent.append(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading patchnotes.txt: " + e.getMessage());
        }

        notes.setText(patchNotesContent.toString());

        notesPanel.add(notes);

        // Wrap notesPanel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(notesPanel);
        scrollPane.setBounds(24, 40, 790, 710);
        scrollPane.setBackground(Color.darkGray);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        con.add(scrollPane);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    
	
}
	
}
