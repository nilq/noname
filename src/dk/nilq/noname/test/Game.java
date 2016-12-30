package dk.nilq.noname.test;

import org.lwjgl.opengl.Display;

import dk.nilq.noname.DisplayManager;

public class Game {
	private static final String TITLE = "noname";
	
	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Display.setTitle(TITLE);
		
		while(!Display.isCloseRequested()) {
			DisplayManager.updateDisplay();
		}
		
		DisplayManager.closeDisplay();
	}
}
