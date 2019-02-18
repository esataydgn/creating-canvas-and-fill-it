package com.stm.challenge.springer_natural;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.stm.challenge.App;
import com.stm.challenge.model.DrawingTemplate;
import com.stm.challenge.service.CommandBase;
import com.stm.challenge.service.impl.CommandService;
import com.stm.challenge.service.impl.CreateCanvasCommand;
import com.stm.challenge.service.impl.DrawLineCommand;
import com.stm.challenge.service.impl.DrawRectangleCommand;
import com.stm.challenge.service.impl.ExecutorService;
import com.stm.challenge.service.impl.FillBucketCommand;
import com.stm.challenge.store.CommandStore;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	private static CommandStore commandStore;
	private static CommandService commandService;
	
	@BeforeAll
	public static void setUpClass() {
		commandStore = new CommandStore();
		commandService = new CommandService(commandStore);
	}

	@Test
	@DisplayName("should create a canvas as C 20 4")
	void testShouldCreateCanvas() {
		CommandBase command = createCanvas();
		
		DrawingTemplate createdTemplate = commandStore.getDrawingTemplates().getLast();
		assertEquals(command.render(createdTemplate),  
				"----------------------\n" +
		        "|                    |\n" +
		        "|                    |\n" +
		        "|                    |\n" +
		        "|                    |\n" +
		        "----------------------");
	}
	

	@Test
	@DisplayName("should create a line with the L 1 2 6 2 parameters")
	void testShouldDrawLine() {
		createCanvas();
		DrawingTemplate drawingTemplate = commandStore.getDrawingTemplates().getLast();
		String[] params = "L 1 2 6 2".split(" ");
		drawingTemplate.setParams(Arrays.copyOfRange(params, 1, params.length));
		CommandBase command = new DrawLineCommand(drawingTemplate, commandStore);
		ExecutorService executor = new ExecutorService(command);
		executor.executor();
		
		DrawingTemplate createdTemplate = commandStore.getDrawingTemplates().getLast();
		assertEquals(command.render(createdTemplate), 
				"----------------------\n" + 
				"|                    |\n" + 
				"|xxxxxx              |\n" + 
				"|                    |\n" + 
				"|                    |\n" + 
				"----------------------");
		
	}
	
	@Test
	@DisplayName("should create a rectangle with the R 16 1 20 3 parameters")
	void testShouldDrawRectangle() {
		createCanvas();
		DrawingTemplate drawingTemplate = commandStore.getDrawingTemplates().getLast();
		String[] params = "R 16 1 20 3".split(" ");
		drawingTemplate.setParams(Arrays.copyOfRange(params, 1, params.length));
		
		CommandBase command = new DrawRectangleCommand(drawingTemplate, commandStore);
		ExecutorService executor = new ExecutorService(command);
		executor.executor();
		
		DrawingTemplate createdTemplate = commandStore.getDrawingTemplates().getLast();
		assertEquals(command.render(createdTemplate),
				"----------------------\n" + 
				"|               xxxxx|\n" + 
				"|               x   x|\n" + 
				"|               xxxxx|\n" + 
				"|                    |\n" + 
				"----------------------"); 
	}
	
	@Test
	@DisplayName("should fill blank with the B 10 3 o parameters")
	void testShouldFillBucketWith_o() {
		createCanvas();
		DrawingTemplate drawingTemplate = commandStore.getDrawingTemplates().getLast();
		String[] params = "B 10 3 o".split(" ");
		drawingTemplate.setParams(Arrays.copyOfRange(params, 1, params.length));
		
		CommandBase command = new FillBucketCommand(drawingTemplate, commandStore);
		ExecutorService executor = new ExecutorService(command);
		executor.executor();
		
		DrawingTemplate createdTemplate = commandStore.getDrawingTemplates().getLast();
		assertEquals(command.render(createdTemplate),
				"----------------------\n" +
				"|oooooooooooooooooooo|\n" + 
				"|oooooooooooooooooooo|\n" + 
				"|oooooooooooooooooooo|\n" + 
				"|oooooooooooooooooooo|\n" + 
				"----------------------");   
	}
	

	private CommandBase createCanvas() {
		DrawingTemplate drawingTemplate = new DrawingTemplate(20, 4);
		CommandBase command = new CreateCanvasCommand(drawingTemplate, commandStore);
		
		ExecutorService executor = new ExecutorService(command);
		executor.executor();
		return command;
		
	}
}
