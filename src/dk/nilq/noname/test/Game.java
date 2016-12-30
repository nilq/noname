package dk.nilq.noname.test;

import org.lwjgl.opengl.Display;

import dk.nilq.noname.DisplayManager;
import dk.nilq.noname.Renderer;
import dk.nilq.noname.model.ModelLoader;
import dk.nilq.noname.model.RawModel;

public class Game {
	private static final String TITLE = "noname";
	
	public static void main(String[] args) {
		DisplayManager.createDisplay();

		Display.setTitle(TITLE);

		ModelLoader loader = new ModelLoader();
		Renderer renderer  = new Renderer();
		
		float[] vertices = {
			-0.5f,  0.5f,  0f,
			-0.5f, -0.5f,  0f, 
			0.5f,  -0.5f,  0f,
				
			0.5f, -0.5f,  0f,
			0.5f,  0.5f,  0f,
			-0.5f, 0.5f,  0f,
		};
		
		RawModel model = loader.loadToVAO(vertices);
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			renderer.render(model);
			
			DisplayManager.updateDisplay();
		}
		
		loader.cleanLists();
		
		DisplayManager.closeDisplay();
	}
}
