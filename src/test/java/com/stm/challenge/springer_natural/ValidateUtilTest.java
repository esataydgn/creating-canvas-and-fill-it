package com.stm.challenge.springer_natural;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.stm.challenge.model.DrawingTemplate;
import com.stm.challenge.service.CommandBase;
import com.stm.challenge.service.impl.CreateCanvasCommand;
import com.stm.challenge.service.impl.ExecutorService;
import com.stm.challenge.store.CommandStore;
import com.stm.challenge.util.ValidatorUtil;

class ValidateUtilTest {

	private static CommandStore commandStore;

	@BeforeAll
	public static void setUpClass() {
		commandStore = new CommandStore();
	}

	@Test
	@DisplayName("check the canvas parameters are valid?")
	void testValidateCanvasParameter() {
		String[] parameters = "C 20 4".split(" ");
		parameters = Arrays.copyOfRange(parameters, 1, parameters.length);
		assertTrue(ValidatorUtil.isCanvasParamsValid(parameters));
		assertTrue(ValidatorUtil.isParamSizeValid(2, parameters));
		assertTrue(ValidatorUtil.isParamsInstanceOfInteger(parameters));
	}

	@Test
	@DisplayName("check the line parameters are valid?")
	void testValidateLineParameter() {
		createCanvas();
		String[] parameters = "L 1 2 6 2".split(" ");
		parameters = Arrays.copyOfRange(parameters, 1, parameters.length);
		assertTrue(ValidatorUtil.isDrawLineParamsValide(commandStore, parameters));
		assertTrue(ValidatorUtil.isParamSizeValid(4, parameters));
		assertTrue(ValidatorUtil.isParamsInstanceOfInteger(parameters));
	}

	@Test
	@DisplayName("check the rectangle parameters are valid?")
	void testValidateRectangleParameter() {
		createCanvas();
		String[] parameters = "R 16 1 20 3".split(" ");
		parameters = Arrays.copyOfRange(parameters, 1, parameters.length);
		assertTrue(ValidatorUtil.isDrawRectangleParamsValid(commandStore, parameters));
		assertTrue(ValidatorUtil.isParamSizeValid(4, parameters));
		assertTrue(ValidatorUtil.isParamsInstanceOfInteger(parameters));
	}

	@Test
	@DisplayName("check the fill the blanks parameters are valid?")
	void testValidateFillBlankParameter() {
		createCanvas();
		String[] parameters = "B 10 3 o".split(" ");
		parameters = Arrays.copyOfRange(parameters, 1, parameters.length);
		assertTrue(ValidatorUtil.isFillBucketParamsValid(commandStore, parameters));
		assertTrue(ValidatorUtil.isParamSizeValid(3, parameters));
		assertTrue(ValidatorUtil.isFillBucketParamsAsExpected(parameters));
	}

	@Test
	@DisplayName("check the created canvas is exist")
	void isCanvasExist() {
		createCanvas();
		assertTrue(ValidatorUtil.isCanvasExist(commandStore));
	}

	@Test
	@DisplayName("check the command char is empty?")
	void testIsCommandCharEmpty() {
		String[] parameters = "B 10 3 o".split(" ");
		String commandChar = parameters[0].trim();
		assertTrue(ValidatorUtil.isNotEmptyChar(commandChar));
	}

	private CommandBase createCanvas() {
		DrawingTemplate drawingTemplate = new DrawingTemplate(20, 4);
		CommandBase command = new CreateCanvasCommand(drawingTemplate, commandStore);

		ExecutorService executor = new ExecutorService(command);
		executor.executor();
		return command;

	}
}
