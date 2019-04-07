package Test;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Algorithms.Algo1;
import Algorithms.NavigationAlgorithm;
import GUI.ManualControlGUI;
import Objects.Coordinate;
import Objects.Quadcopter;
import Objects.RoomMap;

public class main {

	public static void main(String[] args) {
		int lidarDistance = 3;
		Coordinate start = new Coordinate(60,60);
		File pngFile = new File("2.png");
		RoomMap backgroundMap = new RoomMap(pngFile);
		Quadcopter quad = new Quadcopter(backgroundMap, lidarDistance, start);
		
		
		JFrame testFrame = new JFrame();
		testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final ManualControlGUI comp = new ManualControlGUI(quad);
		NavigationAlgorithm algo = new Algo1(comp);
		quad.setAlgo(algo);
		comp.setPreferredSize(new Dimension(backgroundMap.getWidth(), backgroundMap.getHeight()+40));
		comp.addKeyListener(comp);
		comp.setFocusable(true);
		testFrame.getContentPane().add(comp, BorderLayout.CENTER);
		testFrame.pack();
		testFrame.setVisible(true);
		
	}
}
