package com.stm.challenge.service.impl;

import java.util.Arrays;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.stm.challenge.model.DrawingTemplate;
import com.stm.challenge.service.CommandBase;
import com.stm.challenge.store.CommandStore;
import com.stm.challenge.util.ValidatorUtil;

@Service
public class CommandService {
	private static Scanner scanner;
	private CommandStore commandStore;

	public CommandService(CommandStore commandStore) {
		this.commandStore = commandStore;
	}

	public void start() {
		scanner = new Scanner(System.in);
		System.out.println("Enter a command: ");
		while (true) {
			executeCommands(scanner.nextLine());
			System.out.println("Enter a command: ");
		}
	}

	public void executeCommands(String command) {
		String[] params = command.trim().split(" ");
		CommandBase commandType = createCommandDetails(params);
		if (ValidatorUtil.isCommandNotEmpty(commandType)) {
			ExecutorService executor = new ExecutorService(commandType);
			System.out.println(executor.executor());
		}
	}

	public CommandBase createCommandDetails(String[] params) {
		DrawingTemplate drawingTemplate;
		CommandBase command = null;

		if (ValidatorUtil.isNotEmptyChar(params[0].trim())) {
		char commandChar = findFirstCharOfCommand(params);
		params = Arrays.copyOfRange(params, 1, params.length);

			switch (commandChar) {
			case 'C':
				if (!ValidatorUtil.isCanvasParamsValid(params)) {
					break;
				}
				drawingTemplate = new DrawingTemplate(Integer.parseInt(params[0]), Integer.parseInt(params[1]));
				command = new CreateCanvasCommand(drawingTemplate, commandStore);
				break;
			case 'L':
				if (!ValidatorUtil.isDrawLineParamsValide(commandStore, params)) {
					break;
				}
				drawingTemplate = findLastTemplate();
				drawingTemplate.setParams(params);
				command = new DrawLineCommand(drawingTemplate, commandStore);
				break;
			case 'R':
				if (!ValidatorUtil.isDrawRectangleParamsValid(commandStore, params)) {
					break;
				}
				drawingTemplate = findLastTemplate();
				drawingTemplate.setParams(params);
				command = new DrawRectangleCommand(drawingTemplate, commandStore);
				break;
			case 'B':
				if (!ValidatorUtil.isFillBucketParamsValid(commandStore, params)) {
					break;
				}
				drawingTemplate = findLastTemplate();
				drawingTemplate.setParams(params);
				command = new FillBucketCommand(drawingTemplate, commandStore);
				break;
			case 'Q':
				System.out.println("Quit...");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid command type...");
			}
		}

		return command;
	}

	private char findFirstCharOfCommand(String[] params) {
		return params[0].toUpperCase().charAt(0);
	}

	private DrawingTemplate findLastTemplate() {
		return commandStore.getDrawingTemplates().getLast();
	}
}
