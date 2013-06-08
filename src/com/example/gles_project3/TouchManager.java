package com.example.gles_project3;

import android.view.MotionEvent;

public class TouchManager {
	static TouchManager _instance = null;
	static TouchManager getInstance()
	{
		if (_instance == null)
			_instance = new TouchManager();
		return _instance;
	}
	
	public TouchManager() {
		renderer = null;
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
			prevSingleX = event.getX();
			prevSingleY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			if (event.getPointerCount() == 1 && isFirstSingleTouch) {
				// one touch
				double currentSingleX = event.getX();
				double currentSingleY = event.getY();
				
				double diffX = currentSingleX - prevSingleX;
				double diffY = currentSingleY - prevSingleY;
				
				renderer.settX((float)(renderer.gettX() + diffX / 1000f));
				renderer.settY((float)(renderer.gettY() - diffY / 1000f));
				
				// update prev position
				prevSingleX = currentSingleX;
				prevSingleY = currentSingleY;
				
			} else if (event.getPointerCount() == 2) {
				// median value of two touches
				double currentDoubleX = (event.getX(0) + event.getX(1)) / 2;
				double currentDoubleY = (event.getY(0) + event.getY(1)) / 2;
				double currentDistance = (Math.pow(event.getX(0) - event.getX(1), 2) + Math.pow(event.getY(0) - event.getY(1), 2));
				currentDistance = Math.sqrt(currentDistance);
				
				if(!isFirstDoubleTouch) {
					
				}
				prevDoubleX = currentDoubleX;
				prevDoubleY = currentDoubleY;
				if(prevDistance < 0) prevDistance = currentDistance;
				
				isFirstDoubleTouch = false;
			}
			break;
		}
		return true;
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
