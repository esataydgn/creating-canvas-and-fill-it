package com.stm.challenge.service.impl;

import org.springframework.stereotype.Service;

import com.stm.challenge.model.DrawingTemplate;
import com.stm.challenge.service.CommandBase;
import com.stm.challenge.store.CommandStore;

@Service
public class DrawRectangleCommand implements CommandBase {

	private static final char DRAW_CHAR = 'x';
	private DrawingTemplate drawingTemplate;
	char[][] canvasArray;
	private CommandStore commandStore;

	public DrawRectangleCommand(DrawingTemplate drawingTemplate, CommandStore commandStore) {
		this.drawingTemplate = drawingTemplate;
		this.commandStore = commandStore;
	}

	public String draw() {

		canvasArray = drawingTemplate.getCanvasArray();
		String[] params = drawingTemplate.getParams();
		int x1Coordinate = Integer.parseInt(params[0]);
		int y1Coordinate = Integer.parseInt(params[1]);
		int x2Coordinate = Integer.parseInt(params[2]);
		int y2Coordinate = Integer.parseInt(params[3]);

		CommandBase.super.drawLine(x1Coordinate, y1Coordinate, x2Coordinate, y1Coordinate, DRAW_CHAR, canvasArray);
		CommandBase.super.drawLine(x1Coordinate, y1Coordinate, x1Coordinate, y2Coordinate, DRAW_CHAR, canvasArray);
		CommandBase.super.drawLine(x2Coordinate, y1Coordinate, x2Coordinate, y2Coordinate, DRAW_CHAR, canvasArray);
		CommandBase.super.drawLine(x1Coordinate, y2Coordinate, x2Coordinate, y2Coordinate, DRAW_CHAR, canvasArray);

		drawingTemplate.setCanvasArray(canvasArray);
		commandStore.getDrawingTemplates().add(drawingTemplate);
		return CommandBase.super.render(drawingTemplate);
	}

}
