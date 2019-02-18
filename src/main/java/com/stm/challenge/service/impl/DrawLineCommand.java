package com.stm.challenge.service.impl;

import org.springframework.stereotype.Service;

import com.stm.challenge.model.DrawingTemplate;
import com.stm.challenge.service.CommandBase;
import com.stm.challenge.store.CommandStore;

@Service
public class DrawLineCommand implements CommandBase {
	private static final char DRAW_CHAR = 'x';
	private DrawingTemplate drawingTemplate;
	char[][] canvasArray;
	private CommandStore commandStore;

	public DrawLineCommand(DrawingTemplate drawingTemplate, CommandStore commandStore) {
		this.drawingTemplate = drawingTemplate;
		this.commandStore = commandStore;
	}

	public String draw() {

		canvasArray = drawingTemplate.getCanvasArray();
		String[] params = drawingTemplate.getParams();
		CommandBase.super.drawLine(Integer.parseInt(params[0]), Integer.parseInt(params[1]),
				Integer.parseInt(params[2]), Integer.parseInt(params[3]), DRAW_CHAR, canvasArray);

		drawingTemplate.setCanvasArray(canvasArray);
		commandStore.getDrawingTemplates().add(drawingTemplate);
		return CommandBase.super.render(drawingTemplate);
	}
}
