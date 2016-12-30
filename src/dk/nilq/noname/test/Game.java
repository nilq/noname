package dk.nilq.noname.test;

import org.lwjgl.opengl.Display;

import dk.nilq.noname.DisplayManager;
import dk.nilq.noname.Renderer;
import dk.nilq.noname.model.ModelLoader;
import dk.nilq.noname.model.RawModel;
import dk.nilq.noname.shaders.StaticShader;

public class Game {
	private static final String TITLE = "noname";
	
	public static void main(String[] args) {
		DisplayManager.createDisplay();

		Display.setTitle(TITLE);

		ModelLoader loader = new ModelLoader();
		Renderer renderer  = new Renderer();
		
		StaticShader shader = new StaticShader();
		
		float[] vertices = {
			-0.5f,  0.5f,  0f,
			-0.5f, -0.5f,  0f, 
			0.5f,  -0.5f,  0f,
				
			0.5f, -0.5f,  0f,
			0.5f,  0.5f,  0f,
			-0.5f, 0.5f,  0f,
		};
		
		int[] indices = {
			0, 1, 3,
			3, 1, 2,
		};
				
		RawModel model = loader.loadToVAO(vertices, indices);
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			shader.start();
			
			renderer.render(model);
			
			shader.stop();
			
			DisplayManager.updateDisplay();
		}
		
		shader.clean();
		loader.clean();
		
		DisplayManager.closeDisplay();
	}
}
