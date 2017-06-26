/* 
 *  Author:  Anderson Hiroshi de Siqueira 
 *  N USP:   9313197
 *  Subject: OOP - SCC0504 
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  
 */

package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GraphicUtils {
	private static boolean mute = false;
	
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
	
	public static void changeMuteStatus() {
		mute = !mute;
	}
	
	public static boolean isMute() {
		return mute;
	}
	
	public static void playSound(String song) {
		// Adapted from stack overflow: https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
	    String path;
		
	    // Select sound
		switch(song) {
			case "flip":
				path = "../sounds/f4ngy-card-flip.wav";
				break;
			case "pop":
				path = "../sounds/greenvwbeetle_pop1.wav";
				break;
			case "chips100" : 
				path = "../sounds/piggimon-casino-chips-01.wav";
				break;
			case "chips250" : 
				path = "../sounds/piggimon-casino-chips-03.wav";
				break;
			case "chips500" : 
				path = "../sounds/piggimon-casino-chips-02.wav";
				break;
			default:
				path = "none"; 
	    }
		
		// Play sound
		if(path != "none" && mute == false) {
			try {	
				// Load an audio input stream from a file 
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
		        
				// Allocate new clip
				Clip clip = AudioSystem.getClip();
		        
				// Load sound into clip and play
				clip.open(audioStream); 
		        clip.start();		        
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
		}
	}
}
