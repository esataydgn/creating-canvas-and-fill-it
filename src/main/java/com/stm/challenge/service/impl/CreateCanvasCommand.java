package com.stm.challenge.service.impl;

import org.springframework.stereotype.Service;

import com.stm.challenge.model.DrawingTemplate;
import com.stm.challenge.service.CommandBase;
import com.stm.challenge.store.CommandStore;

@Service
public class CreateCanvasCommand implements CommandBase {

	private static final char HORIZONTAL_BORDER_CHAR = '-';
	private static final char VERTICAL_BORDER_CHAR = '|';
	private DrawingTemplate drawingTemplate;
	char[][] canvasArray;

	private CommandStore commandStore;

	public CreateCanvasCommand(DrawingTemplate drawingTemplate, CommandStore commandStore) {
		this.drawingTemplate = drawingTemplate;
		this.commandStore = commandStore;
	}

	public String draw() {

		int width = drawingTemplate.getWidth() + 2;
		int height = drawingTemplate.getHeight() + 2;

		canvasArray = new char[height][width];

		CommandBase.super.drawLine(0, 0, width - 1, 0, HORIZONTAL_BORDER_CHAR, canvasArray);
		CommandBase.super.drawLine(0, height - 1, width - 1, height - 1, HORIZONTAL_BORDER_CHAR, canvasArray);
		CommandBase.super.drawLine(0, 1, 0, height - 2, VERTICAL_BORDER_CHAR, canvasArray);
		CommandBase.super.drawLine(width - 1, 1, width - 1, height - 2, VERTICAL_BORDER_CHAR, canvasArray);

		drawingTemplate.setCanvasArray(canvasArray);
		commandStore.getDrawingTemplates().add(drawingTemplate);
		return CommandBase.super.render(drawingTemplate);
	}

}
