package com.example.gles_project3;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;

public class MainActivity extends Activity {

	private GLSurfaceView view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new GLSurfaceView(this);
		setContentView(view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(Menu.NONE, 0, Menu.NONE, "Undo").setIcon(android.R.drawable.ic_menu_revert);
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return TouchManager.getInstance().onTouchEvent(event);
	}

}
