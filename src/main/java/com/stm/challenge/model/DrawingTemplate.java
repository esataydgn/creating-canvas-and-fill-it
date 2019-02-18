package com.stm.challenge.model;

public class DrawingTemplate {
	private int height;
	private int width;
	private String[] params;

	private char[][] canvasArray;

	public DrawingTemplate(int width, int height) {
		this.height = height;
		this.setWidth(width);
		setCanvasArray(new char[width][height]);

	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public char[][] getCanvasArray() {
		return canvasArray;
	}

	public void setCanvasArray(char[][] canvasArray) {
		this.canvasArray = canvasArray;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

}
