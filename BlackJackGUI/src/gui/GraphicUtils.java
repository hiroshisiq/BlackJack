package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GraphicUtils {
	
	public static ImageIcon getScaledImage(ImageIcon srcImg, int w, int h){
		// Adapted from stack overflow
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg.getImage(), 0, 0, w, h, null);
	    g2.dispose();

	    return new ImageIcon(resizedImg);
	}
	
	public static String verticalHtml(String text) {
		// from: https://stackoverflow.com/questions/14777926/java-how-to-make-a-jlabel-with-vertical-text
	    String ans = "<html>";
	    String[] letters = text.split("");
	    for (String letter : letters) {
	        ans += letter + "<br>";
	    }
	    ans += "</html>";
	    return ans;
	}
	
	public static void setGreenBackground(Container cont) {
		cont.setBackground(new Color (0,110,0));
	}
	
	public static void changeBrightness(BufferedImage image, boolean darker, int times) {
		// Get each pixel of the image
		for(int y = 0; y < image.getHeight(); y++) {
		    for(int x = 0; x < image.getWidth(); x++) {
		    	// Get RGB
		    	int color = image.getRGB(x, y);
		    	
		    	// Check if it's not a transparent pixel 
		    	if( (color & 0xff000000) < 0x00000000 ) {
		    		// Change color 
		    		for(int i = 0; i < times; i++) {
		    			// Get red, green and blue bytes
		    			int red   = color & 0x00ff0000;
		    			int green = color & 0x0000ff00;
		    			int blue  = color & 0x000000ff;
		    			
		    			// Change brightness (increase or decrease RGB value)
		    			if(darker) {
		    				if(red   != 0x00000000) red   -= 0x00010000;
		    				if(green != 0x00000000) green -= 0x00000100;
		    				if(blue  != 0x00000000) blue  -= 0x00000001;
		    			} else {
			    			if(red   != 0x00ff0000) red   += 0x00010000;
			    			if(green != 0x0000ff00) green += 0x00000100;
			    			if(blue  != 0x000000ff) blue  += 0x00000001;
		    			}
		    			
		    			// Update color
		    			color = (0xff000000 | red | green | blue);			    			
		    		}
		    	}
		    	
		    	// Set color
		        image.setRGB(x, y, color);
		    }
		}
	}
	
	public static void loadImage(JLabel button, String path, int w, int h, int timesDark) {
		// Load image
		BufferedImage img = null;
		try {
			// Adjust size and brightness
			img = ImageIO.read(new File(path));
			changeBrightness(img, (timesDark > 0)? true : false, (timesDark > 0)?timesDark:-timesDark);
			ImageIcon iImg = GraphicUtils.getScaledImage(new ImageIcon(img), w, h);
			
			// Set image to button
			button.setIcon(iImg);
		} catch (IOException e) {
			e.printStackTrace();					
		}
	}
}
