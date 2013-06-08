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
	private double prevDoubleX, prevDoubleY;
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()) {
		case MotionEvent.ACTION_UP:			
			isFirstSingleTouch = true;
			isFirstDoubleTouch = true;
			break;
		case MotionEvent.ACTION_DOWN:
			isFirstSingleTouch = false;
			prevSingleX = event.getX();
			prevSingleY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			if (event.getPointerCount() == 1) {
				isFirstSingleTouch = false;
				
				// if not double touched
				if (isFirstDoubleTouch) {
					
				}
			} else if (event.getPointerCount() == 2) {
				// median value of two touches
				double currentDoubleX = (event.getX(0) + event.getX(1)) / 2;
				double currentDoubleY = (event.getY(0) + event.getY(1)) / 2;
				
				if(!isFirstDoubleTouch) {
					
				}
				prevDoubleX = currentDoubleX;
				prevDoubleY = currentDoubleY;
				
				isFirstDoubleTouch = false;
				isFirstSingleTouch = true;
				
				
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
