package com.example.gles_project3;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.EditText;

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
		menu.add(Menu.NONE, 1, Menu.NONE, "색깔");
		menu.add(Menu.NONE, 2, Menu.NONE, "x");
		menu.add(Menu.NONE, 3, Menu.NONE, "y");
		menu.add(Menu.NONE, 4, Menu.NONE, "z");
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stubLattice.Undo();
		switch (item.getItemId()) {
		case 0:
			Lattice.Undo();
			break;
		case 1:
			new ColorPickerDialog(this, 
					new ColorPickerDialog.OnColorChangedListener() {
						
						@Override
						public void colorChanged(String key, int color) {
							// TODO Auto-generated method stub
							Object.drawingColor = Color.intToColor(color);
						}
					}, 
					"KEY", 
					Color.colorToInt(Object.drawingColor), 
					Color.colorToInt(Object.drawingColor)).show();
			break;
		case 2:
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("크기를 선택하세요.");

			// Set up the input
			final EditText input = new EditText(this);
			// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
			input.setInputType(InputType.TYPE_CLASS_NUMBER);
			input.setText(Integer.toString(Math.round(Object.drawingSize.x)));
			builder.setView(input);

			// Set up the buttons
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			    	Object.drawingSize.x = Integer.parseInt(input.getText().toString());
			    }
			});
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			        dialog.cancel();
			    }
			});

			builder.show();
		}
			break;
		case 3:
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("크기를 선택하세요.");

			// Set up the input
			final EditText input = new EditText(this);
			// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
			input.setInputType(InputType.TYPE_CLASS_NUMBER);
			input.setText(Integer.toString(Math.round(Object.drawingSize.y)));
			builder.setView(input);

			// Set up the buttons
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			    	Object.drawingSize.y = Integer.parseInt(input.getText().toString());
			    }
			});
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			        dialog.cancel();
			    }
			});

			builder.show();
		}
			break;
		case 4:
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("크기를 선택하세요.");

			// Set up the input
			final EditText input = new EditText(this);
			// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
			input.setInputType(InputType.TYPE_CLASS_NUMBER);
			input.setText(Integer.toString(Math.round(Object.drawingSize.z)));
			builder.setView(input);

			// Set up the buttons
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			    	Object.drawingSize.z = Integer.parseInt(input.getText().toString());
			    }
			});
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			        dialog.cancel();
			    }
			});

			builder.show();
		}
			break;
		
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return TouchManager.getInstance().onTouchEvent(event);
	}

}
