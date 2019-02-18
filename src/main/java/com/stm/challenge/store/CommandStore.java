package com.stm.challenge.store;

import java.util.LinkedList;

import org.springframework.stereotype.Component;

import com.stm.challenge.model.DrawingTemplate;

@Component
public class CommandStore {

	private LinkedList<DrawingTemplate> drawingTemplates = new LinkedList<DrawingTemplate>();

	public LinkedList<DrawingTemplate> getDrawingTemplates() {
		return drawingTemplates;
	}

	public void setDrawingTemplates(LinkedList<DrawingTemplate> drawingTemplates) {
		this.drawingTemplates = drawingTemplates;
	}
}
