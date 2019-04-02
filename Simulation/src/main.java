import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
		int lidarDistance = 3;
		Coordinate start = new Coordinate(60,60);
		File pngFile = new File("2.png");
		RoomMap backgroundMap = new RoomMap(pngFile);
		JFrame testFrame = new JFrame();
		testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final GUI comp = new GUI();
		comp.setPreferredSize(new Dimension(backgroundMap.getWidth(), backgroundMap.getHeight()));
		testFrame.getContentPane().add(comp, BorderLayout.CENTER);
		testFrame.pack();
		testFrame.setVisible(true);
		Quadcopter quad = new Quadcopter(backgroundMap, lidarDistance, start, comp);
		quad.manualStart();
		System.out.println(quad.getAngle());
	}
}
