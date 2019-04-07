package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GuiTest {
	public static void main(String[] args) {
	    JFrame testFrame = new JFrame();
	    testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    final GUI comp = new GUI();
	    comp.setPreferredSize(new Dimension(320, 200));
	    testFrame.getContentPane().add(comp, BorderLayout.CENTER);
	    Color red = Color.red;
	    comp.addPoint(60, 60, 4, red);
//	    JPanel buttonsPanel = new JPanel();
//	    JButton newLineButton = new JButton("New Line");
//	    JButton clearButton = new JButton("Clear");
//	    buttonsPanel.add(newLineButton);
//	    buttonsPanel.add(clearButton);
//	    testFrame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
//	    newLineButton.addActionListener(new ActionListener() {
//
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
//	            int x1 = (int) (Math.random()*320);
//	            int x2 = (int) (Math.random()*320);
//	            int y1 = (int) (Math.random()*200);
//	            int y2 = (int) (Math.random()*200);
//	            Color randomColor = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
//	            comp.addLine(x1, y1, x2, y2, randomColor);
//	        }
//	    });
//	    clearButton.addActionListener(new ActionListener() {
//
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
//	            comp.clearLines();
//	        }
//	    });
	    testFrame.pack();
	    testFrame.setVisible(true);
	}
}
