package com.example.gles_project3;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class UnitObject {

	private FloatBuffer mVertexBuffer = null;
	private FloatBuffer mNormalBuffer = null;
	private ByteBuffer mIndexBuffer = null;
	
	private Color CLR;
	private float[] colors = new float[4*24];	
	
	static final float unit_size = 0.2f;
	static final float unit_height = 0.08f;
	
	static final float half_unit_size = unit_size/2;
	static final float half_unit_height = unit_height/2;
	
	private UnitCylinder unitcylinder;
	
	final float[] vertices = {
		
			// ����
			-half_unit_size, -half_unit_size, half_unit_height, //v0
			half_unit_size, -half_unit_size, half_unit_height,	//v1
			-half_unit_size, half_unit_size, half_unit_height, 	//v2
			half_unit_size, half_unit_size, half_unit_height, 	//v3
		
			// �ո�
			half_unit_size, -half_unit_size, half_unit_height, 	// ...
			half_unit_size, -half_unit_size, -half_unit_height, 
			half_unit_size, half_unit_size, half_unit_height, 
			half_unit_size, half_unit_size, -half_unit_height,
		
			// �ٴڸ�
			half_unit_size, -half_unit_size, -half_unit_height, 
			-half_unit_size, -half_unit_size, -half_unit_height, 
			half_unit_size, half_unit_size, -half_unit_height, 
			-half_unit_size, half_unit_size, -half_unit_height,
		
			// �޸�
			-half_unit_size, -half_unit_size, -half_unit_height, 
			-half_unit_size, -half_unit_size, half_unit_height, 
			-half_unit_size, half_unit_size, -half_unit_height, 
			-half_unit_size, half_unit_size, half_unit_height,
		
			// ���ʸ�
			-half_unit_size, -half_unit_size, -half_unit_height, 
			half_unit_size, -half_unit_size, -half_unit_height, 
			-half_unit_size, -half_unit_size, half_unit_height, 
			half_unit_size, -half_unit_size, half_unit_height,
		
			// �����ʸ�
			-half_unit_size, half_unit_size, half_unit_height, 
			half_unit_size, half_unit_size, half_unit_height,
			-half_unit_size, half_unit_size, -half_unit_height, 
			half_unit_size, half_unit_size, -half_unit_height, 
	};
 	
	private float normals[] = {
			
			// Front face
			0.0f, 0.0f, 1.0f, 						
			0.0f, 0.0f, 1.0f, 
			0.0f, 0.0f, 1.0f, 
			0.0f, 0.0f, 1.0f, 
			
			// Right face		
			1.0f, 0.0f, 0.0f, 
			1.0f, 0.0f, 0.0f, 
			1.0f, 0.0f, 0.0f, 
			1.0f, 0.0f, 0.0f, 

			// Back face
			0.0f, 0.0f, -1.0f, 
			0.0f, 0.0f, -1.0f, 
			0.0f, 0.0f, -1.0f, 
			0.0f, 0.0f, -1.0f, 

			// Left face
			-1.0f, 0.0f, 0.0f, 
			-1.0f, 0.0f, 0.0f, 
			-1.0f, 0.0f, 0.0f, 
			-1.0f, 0.0f, 0.0f, 

			// Bottom face
			0.0f, -1.0f, 0.0f, 
			0.0f, -1.0f, 0.0f, 
			0.0f, -1.0f, 0.0f, 
			0.0f, -1.0f, 0.0f, 

			// Up face
			0.0f, 1.0f, 0.0f, 
			0.0f, 1.0f, 0.0f, 
			0.0f, 1.0f, 0.0f, 
			0.0f, 1.0f, 0.0f, 
		};
		
	private byte indices[] = {
			// Faces definition
			0, 1, 3, 0, 3, 2, 		// Face front
			4, 5, 7, 4, 7, 6, 		// Face right
			8, 9, 11, 8, 11, 10, 	// ...
			12, 13, 15, 12, 15, 14, 
			16, 17, 19, 16, 19, 18, 
			20, 21, 23, 20, 23, 22, 
		};

	
	UnitObject(Color _CLR)
	{
		this.CLR = _CLR;
		for ( int i = 0 ; i < 24; i++){	
			colors[i*4] = CLR.r;	colors[i*4+1] = CLR.g;	colors[i*4+2] = CLR.b;	colors[i*4+3] = CLR.a;
		}
		
		mVertexBuffer = getFloatBufferFromFloatArray(vertices);
		mNormalBuffer = getFloatBufferFromFloatArray(normals);
		mIndexBuffer = getByteBufferFromByteArray(indices);
		
		unitcylinder = new UnitCylinder(this.CLR);
	}
	
	static float glass_ambient[] = {0.0f, 0.0f, 0.0f, 0.0f};
	static float glass_diffuse[] = {0.0f, 0.0f, 0.0f, 0.0f};
	static float glass_specular[] = {0.0f, 0.0f, 0.0f, 0.0f};
	static FloatBuffer glass_ambientbuffer = getFloatBufferFromFloatArray(glass_ambient);
    static FloatBuffer glass_diffusebuffer = getFloatBufferFromFloatArray(glass_diffuse);
    static FloatBuffer glass_specularbuffer = getFloatBufferFromFloatArray(glass_specular);
	
    static FloatBuffer getFloatBufferFromFloatArray(float array[]) {
		ByteBuffer tempBuffer = ByteBuffer.allocateDirect(array.length * 4);
		tempBuffer.order(ByteOrder.nativeOrder());
		FloatBuffer buffer = tempBuffer.asFloatBuffer();
		buffer.put(array);
		buffer.position(0);
		return buffer;
	}
    
    static void Material_Color(GL10 gl, Color _Color){
    	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_AMBIENT, glass_ambientbuffer);
    	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_DIFFUSE, glass_diffusebuffer);
    	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, glass_specularbuffer);
    	gl.glMaterialx(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, 128);
	    gl.glColor4f(_Color.r, _Color.g, _Color.b, _Color.a);
    }

	void draw(GL10 gl)
	{
		//Enable the vertex, texture and normal state
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

		//Set the face rotation
		gl.glFrontFace(GL10.GL_CW);

		//Point to our buffers
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, mNormalBuffer);
		Material_Color(gl, new Color(this.CLR) );

		//Draw the vertices as triangles, based on the Index Buffer information
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
		
		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);	
		
		gl.glPushMatrix();
		gl.glTranslatef(0.0f,0.0f,unit_height*5.0f/6.0f);
		unitcylinder.draw(gl);
		gl.glPopMatrix();
	}

	ByteBuffer getByteBufferFromByteArray( byte array[]) {
		ByteBuffer buffer = ByteBuffer.allocateDirect(array.length);
		buffer.put(array);
		buffer.position(0);
		return buffer;
	}


    
}


