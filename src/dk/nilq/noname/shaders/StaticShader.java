package dk.nilq.noname.shaders;

public class StaticShader extends ShaderProgram {

	private static final String VERTEX_FILE   = "src/dk/nilq/noname/shaders/vertex_shader.glsl";
	private static final String FRAGMENT_FILE = "src/dk/nilq/noname/shaders/fragment_shader.glsl";

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttributes(0, "position");
	}
}
