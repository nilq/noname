package dk.nilq.noname.model;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class ModelLoader {

	private List<Integer> vaoList = new ArrayList<Integer>();
	private List<Integer> vboList = new ArrayList<Integer>();
	
	public RawModel loadToVAO(float[] positions) {
		int vaoID = createVAO();
		
		storeAttributeList(0, positions);
		unbindVAO();
		
		return new RawModel(vaoID, positions.length / 3);
	}
	
	private int createVAO() {
		int vaoID = GL30.glGenVertexArrays();

		vaoList.add(vaoID);
		
		GL30.glBindVertexArray(vaoID);
		
		return vaoID;
	}
	
	private void storeAttributeList(int attributeNumber, float[] data) {
		int vboID = GL15.glGenBuffers();

		vboList.add(vboID);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		
		FloatBuffer buffer = storeFloatBuffer(data);
		
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private FloatBuffer storeFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}
	
	private void unbindVAO() {
		GL30.glBindVertexArray(0);
	}
	
	public void cleanLists() {
		for(int vao : vaoList) {
			GL30.glDeleteVertexArrays(vao);
		}
		
		for (int vbo : vboList) {
			GL15.glDeleteBuffers(vbo);
		}
	}
}
