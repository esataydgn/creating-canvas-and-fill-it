package com.stm.challenge.service.impl;

import org.springframework.stereotype.Service;

import com.stm.challenge.model.DrawingTemplate;
import com.stm.challenge.service.CommandBase;
import com.stm.challenge.store.CommandStore;

@Service
public class FillBucketCommand implements CommandBase {

	private DrawingTemplate drawingTemplate;
	char[][] canvasArray;
	private CommandStore commandStore;
	private int canvasHeight = 0;
	private int canvasWidth = 0;

	public FillBucketCommand(DrawingTemplate drawingTemplate, CommandStore commandStore) {
		this.drawingTemplate = drawingTemplate;
		this.commandStore = commandStore;
	}

	public String draw() {

		canvasArray = drawingTemplate.getCanvasArray();
		canvasHeight = drawingTemplate.getHeight();
		canvasWidth = drawingTemplate.getWidth();

		String[] params = drawingTemplate.getParams();
		int xCoordinate = Integer.parseInt(params[0]);
		int yCoordinate = Integer.parseInt(params[1]);
		char fillChar = params[2].charAt(0);
		bucketFill(xCoordinate, yCoordinate, fillChar);

		drawingTemplate.setCanvasArray(canvasArray);
		commandStore.getDrawingTemplates().add(drawingTemplate);
		return CommandBase.super.render(drawingTemplate);

	}

	public void bucketFill(int xCoordinate, int yCoordinate, char fillByChar) {
		if ((int) this.canvasArray[yCoordinate][xCoordinate] != 0) {
			return;
		}
		if (xCoordinate > 0 || xCoordinate < canvasHeight || yCoordinate > 0 || yCoordinate < canvasWidth) {
			if ((int) this.canvasArray[yCoordinate][xCoordinate] == 0)
				this.canvasArray[yCoordinate][xCoordinate] = fillByChar;
			bucketFill(xCoordinate + 1, yCoordinate, fillByChar);
			bucketFill(xCoordinate - 1, yCoordinate, fillByChar);
			bucketFill(xCoordinate, yCoordinate - 1, fillByChar);
			bucketFill(xCoordinate, yCoordinate + 1, fillByChar);
		}
	}

}
