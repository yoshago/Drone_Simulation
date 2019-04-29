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
import Algorithms.Algo2;
import Algorithms.NavigationAlgorithm;
import GUI.ManualControlGUI;
import Objects.Coordinate;
import Objects.Quadcopter;
import Objects.RoomMap;

public class main {

	public static void main(String[] args) {
		int lidarDistance = 3;
		int rightLidarAngle = 45;
		int leftLidarAngle = -45;
		int resolution = 40;
		Coordinate start = new Coordinate(80,80);
		File pngFile = new File("p11.png");
		RoomMap backgroundMap = new RoomMap(pngFile, resolution);
		Quadcopter quad = new Quadcopter(backgroundMap, lidarDistance, start, rightLidarAngle, leftLidarAngle,resolution);
		
		
		JFrame testFrame = new JFrame();
		testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final ManualControlGUI comp = new ManualControlGUI(quad);
		NavigationAlgorithm algo = new Algo2(comp);
		
		quad.setAlgo(algo);
		comp.setPreferredSize(new Dimension(backgroundMap.getWidth(), backgroundMap.getHeight()+40));
		comp.addKeyListener(comp);
		comp.setFocusable(true);
		testFrame.getContentPane().add(comp, BorderLayout.CENTER);
		testFrame.pack();
		testFrame.setVisible(true);
		
	}
}
