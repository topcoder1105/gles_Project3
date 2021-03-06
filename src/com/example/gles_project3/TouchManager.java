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
	
	Dot u, v; // world coordinate
	Dot l;
	public TouchManager() {
		renderer = null;
		u = new Dot(0, 1, 0);
		v = new Dot(1, 0, 0);
		l = new Dot(0, 0, 1);
	}
	
	// touch manage
	private boolean isFirstSingleTouch = true;
	private boolean isFirstDoubleTouch = true;
	private boolean isEnded = true;
	
	private double prevSingleX, prevSingleY;
	private double prevDoubleX, prevDoubleY, prevDistance = -1;
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()) {
		case MotionEvent.ACTION_UP:
			isEnded = true;
			if(!isFirstSingleTouch && isFirstDoubleTouch)
				onSingleTouchUp(event.getX(), event.getY());
		case MotionEvent.ACTION_DOWN:
			isFirstSingleTouch = false;
			isFirstDoubleTouch = true;
			isEnded = false;
			prevDistance = -1;
			prevSingleX = event.getX();
			prevSingleY = event.getY();
			onSingleTouchDown(event.getX(), event.getY());
			break;
		case MotionEvent.ACTION_MOVE:
			if (event.getPointerCount() == 1 && !isFirstSingleTouch) {
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
				
				if (!isEnded)
					onSingleTouchDown(currentSingleX, currentSingleY);
				
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
				
				if(!isFirstSingleTouch)
					onSingleTouchCancel(event.getX(), event.getY());
				isFirstDoubleTouch = false;
				isFirstSingleTouch = true;
			}
			break;
		}
		return true;
	}
	
	
	// touch gesture manage
	void onSingleTouchMoved(double dx, double dy) {
//		renderer.settX((float)(renderer.gettX() + dx / 1000f * -renderer.gettZ()));
//		renderer.settY((float)(renderer.gettY() - dy / 1000f * -renderer.gettZ()));

	}
	
	void onSingleTouchDown(double dx, double dy) {
		onInputTouchHandle(dx, dy, 0);
	}
	
	void onSingleTouchUp(double dx, double dy) {
		onInputTouchHandle(dx, dy, 1);
	}
	
	void onSingleTouchCancel(double dx, double dy) {
		onInputTouchHandle(dx, dy, 2);
	}
	
	void onInputTouchHandle(double dx, double dy, int mode) {
		if (onTouchInterface != null) {
			Dot rotation = convertUVToRotation();
			
			double px = dx * renderer.gettZ() / renderer.getWidth() - renderer.gettZ() / 2 + renderer.gettX();
			double py = (renderer.getHeight() - dy) * renderer.gettZ() / renderer.getHeight() - renderer.gettZ() / 2 + renderer.gettY();

			Dot start_point = new Dot(-px, -py, 0);
			start_point.rotate_with_degree(rotation.z, 0, 0, 1).rotate_with_degree(-rotation.y, 0, 1, 0).rotate_with_degree(rotation.x, 1, 0, 0);
			Dot direct_vector = new Dot(0, 0, 1);
			direct_vector.rotate_with_degree(rotation.z, 0, 0, 1).rotate_with_degree(-rotation.y, 0, 1, 0).rotate_with_degree(rotation.x, 1, 0, 0);
			
			switch (mode) {
			case 0: // down
				onTouchInterface.onInputTouchDown(start_point, direct_vector);
				break;
			case 1: // up
				onTouchInterface.onInputTouchUp(start_point, direct_vector);
				break;
			case 2: // cancel
				onTouchInterface.onInputTouchCancel(start_point, direct_vector);
				break;

			default:
				break;
			}
		}
	}
	
	
	void onDoubleTouchMoved(double dx, double dy) {
		double drx = dy / 10f;
		double dry = dx / 10f;
		
		u.rotate(MathUtil.degreeToRadian(drx), 1, 0, 0);
		u.rotate(MathUtil.degreeToRadian(dry), 0, 1, 0);
		
		v.rotate(MathUtil.degreeToRadian(drx), 1, 0, 0);
		v.rotate(MathUtil.degreeToRadian(dry), 0, 1, 0);
		
		// z=0 angle
		Dot rotation = convertUVToRotation();
		
		renderer.setrX((float) -rotation.x);
		renderer.setrY((float) rotation.y);
		renderer.setrZ((float) -rotation.z);
		
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
	public Dot convertUVToRotation() {
		// z=0 angle
		Dot nn = new Dot(u.x, u.y, 0);
		double rz = nn.angle(new Dot(0, 1, 0));
		if(nn.x < 0) rz = 360 - rz;
		Dot nu = new Dot(u.x, u.y, u.z).rotate(MathUtil.degreeToRadian(rz), 0, 0, 1);
		double rx = nu.angle(new Dot(0, 1, 0));
		if(nu.z < 0) rx = 360 - rx;
		Dot nv = new Dot(v.x, v.y, v.z).rotate(MathUtil.degreeToRadian(rz), 0, 0, 1).rotate(MathUtil.degreeToRadian(rx), 1, 0, 0);
		double ry = nv.angle(new Dot(1, 0, 0));
		if(nv.z < 0) ry = 360 - ry;
		
		return new Dot(rx, ry, rz);
	}
	
	// renderer getter and setter
	private GLRenderer renderer = null;
	
	public void setRenderer(GLRenderer renderer) {
		this.renderer = renderer;
	}
	
	// onTouchInterface getter and setter
	private OnTouchInterface onTouchInterface = null;

	public void setOnTouchInterface(OnTouchInterface onTouchInterface) {
		this.onTouchInterface = onTouchInterface;
	}
}
