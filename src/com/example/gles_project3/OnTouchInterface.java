package com.example.gles_project3;

public interface OnTouchInterface {
	public void onInputTouchDown(Dot startPoint, Dot directVector);
	public void onInputTouchUp(Dot startPoint, Dot directVector);
	public void onInputTouchCancel(Dot startPoint, Dot directVector);
	public void onDeleteTouchDown(Dot startPoint, Dot directVector);
	public void onDeleteTouchUp(Dot startPoint, Dot directVector);
	public void onDeleteTouchCancel(Dot startPoint, Dot directVector);
}
