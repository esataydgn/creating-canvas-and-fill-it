package com.stm.challenge.service;

import org.springframework.stereotype.Service;

import com.stm.challenge.model.DrawingTemplate;

@Service
public interface CommandBase {

	String draw();

	default public void drawLine(int x1Coordinate, int y1Coordinate, int x2Coordinate, int y2Coordinate, char drawChar,
			char[][] canvasArray) {
		for (int i = y1Coordinate; i <= y2Coordinate; i++) {
			for (int j = x1Coordinate; j <= x2Coordinate; j++) {
				canvasArray[i][j] = drawChar;
			}
		}
	}

	default public String render(DrawingTemplate template) {
		char[][] canvasArray = template.getCanvasArray();
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < template.getHeight() + 2; i++) {
			for (int j = 0; j < template.getWidth() + 2; j++) {
				strBuilder.append(canvasArray[i][j] == '\u0000' ? ' ' : canvasArray[i][j]);
			}
			strBuilder.append("\n");
		}
		return strBuilder.toString().trim();
	}
}
