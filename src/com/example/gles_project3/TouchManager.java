package com.example.gles_project3;

import android.util.Log;
import android.view.MotionEvent;

public class TouchManager {
	static TouchManager _instance = null;
	static TouchManager getInstance()
	{
		if (_instance == null)
			_instance = new TouchManager();
		return _instance;
	}
	
	Dot u, v; // world coordinate
	public TouchManager() {
		renderer = null;
		u = new Dot(0, 1, 0);
		v = new Dot(1, 0, 0);
	}
	
	// touch manage
	private boolean isFirstSingleTouch = true;
	private boolean isFirstDoubleTouch = true;
	
	private double prevSingleX, prevSingleY;
	private double prevDoubleX, prevDoubleY, prevDistance = -1;
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()) {
		case MotionEvent.ACTION_UP:			
			isFirstSingleTouch = false;
			isFirstDoubleTouch = false;
			prevDistance = -1;
			break;
		case MotionEvent.ACTION_DOWN:
			isFirstSingleTouch = true;
			isFirstDoubleTouch = true;
			prevDistance = -1;
			prevSingleX = event.getX();
			prevSingleY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			if (event.getPointerCount() == 1) {
				// one touch
				double currentSingleX = event.getX();
				double currentSingleY = event.getY();
				
				if(!isFirstSingleTouch) {
					double diffX = currentSingleX - prevSingleX;
					double diffY = currentSingleY - prevSingleY;
					
					onSingleTouchMoved(diffX, diffY);
				}
				
				// update prev position
				prevSingleX = currentSingleX;
				prevSingleY = currentSingleY;
				
				isFirstSingleTouch = false;
				isFirstDoubleTouch = true;
				
			} else if (event.getPointerCount() == 2) {
				// median value of two touches
				double currentDoubleX = (event.getX(0) + event.getX(1)) / 2;
				double currentDoubleY = (event.getY(0) + event.getY(1)) / 2;
				double currentDistance = (Math.pow(event.getX(0) - event.getX(1), 2) + Math.pow(event.getY(0) - event.getY(1), 2));
				currentDistance = Math.sqrt(currentDistance);
				
				if(!isFirstDoubleTouch) {
					double diffX = currentDoubleX - prevDoubleX;
					double diffY = currentDoubleY - prevDoubleY;
					onDoubleTouchMoved(diffX, diffY);
					
					double distance = currentDistance - prevDistance;
					onDoubleTouchZoomed(distance);
				}
				prevDoubleX = currentDoubleX;
				prevDoubleY = currentDoubleY;
//				if(prevDistance < 0) prevDistance = currentDistance;
				prevDistance = currentDistance;
				
				isFirstDoubleTouch = false;
				isFirstSingleTouch = true;
			}
			break;
		}
		return true;
	}
	
	
	// touch gesture manage
	void onSingleTouchMoved(double dx, double dy) {
		renderer.settX((float)(renderer.gettX() + dx / 1000f * -renderer.gettZ()));
		renderer.settY((float)(renderer.gettY() - dy / 1000f * -renderer.gettZ()));
	}
	
	void onDoubleTouchMoved(double dx, double dy) {
		double drx = dy / 10f;
		double dry = dx / 10f;
		
		u.rotate(u.degreeToRadian(drx), 1, 0, 0);
		u.rotate(u.degreeToRadian(dry), 0, 1, 0);
		
		v.rotate(v.degreeToRadian(drx), 1, 0, 0);
		v.rotate(v.degreeToRadian(dry), 0, 1, 0);
		
		// z=0 angle
		convertUVToRotation();
		
//		Log.e("TEST", rx + " " + ry + " " + rz);
//		Log.e("TEST", "u : " + u.x + " " + u.y + " " + u.z);
//		Log.e("TEST", "v : " + v.x + " " + v.y + " " + v.z);
//		renderer.setrX((float)(renderer.getrX() + dy / 10f));
//		renderer.setrY((float)(renderer.getrY() + dx / 10f));
	}
	
	void onDoubleTouchZoomed(double distance) {
		renderer.settZ((float) (renderer.gettZ() + distance / 100f));
		
		//boundary
		if(renderer.gettZ() > -1f)renderer.settZ(-1f);
		if(renderer.gettZ() < -20)renderer.settZ(-20);

	}
	
	
	// convert uv to rotation
	public void convertUVToRotation() {
		// z=0 angle
		Dot nn = new Dot(u.x, u.y, 0);
		double rz = nn.angle(new Dot(0, 1, 0));
		if(nn.x < 0) rz = 360 - rz;
		Dot nu = new Dot(u.x, u.y, u.z).rotate(u.degreeToRadian(rz), 0, 0, 1);
		double rx = nu.angle(new Dot(0, 1, 0));
		if(nu.z < 0) rx = 360 - rx;
		Dot nv = new Dot(v.x, v.y, v.z).rotate(u.degreeToRadian(rz), 0, 0, 1).rotate(u.degreeToRadian(rx), 1, 0, 0);
		double ry = nv.angle(new Dot(1, 0, 0));
		if(nv.z < 0) ry = 360 - ry;
		
		renderer.setrX((float) -rx);
		renderer.setrY((float) ry);
		renderer.setrZ((float) -rz);
	}
	
	
	// renderer getter and setter
	private GLRenderer renderer;
	
	public GLRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(GLRenderer renderer) {
		this.renderer = renderer;
	}
}
