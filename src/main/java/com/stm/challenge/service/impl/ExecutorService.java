package com.stm.challenge.service.impl;

import org.springframework.stereotype.Service;

import com.stm.challenge.service.CommandBase;

@Service
public class ExecutorService {

	private CommandBase commandBase;

	public ExecutorService(CommandBase commandBase) {
		this.commandBase = commandBase;
	}
	
	public String executor() {
		return commandBase.draw();
	}
	
}
