package com.stm.challenge;

import com.stm.challenge.service.impl.CommandService;
import com.stm.challenge.store.CommandStore;

public class App {

	public static void main(String[] args) {

		System.out.println("*********************************************");
        System.out.println("*                                        	*");
        System.out.println("*                  CMD                   	*");
        System.out.println("*                                        	*");
        System.out.println("*   0) C w h          to create a convas 	*");
        System.out.println("*   1) L x1 y1 x2 y2  to draw a line     	*");
        System.out.println("*   2) R x1 y1 x2 y2  to draw rectangle 	*");
        System.out.println("*   3) B x1 y1 character  to refill         *");
        System.out.println("*                                        	*");
        System.out.println("*********************************************");
        System.out.println("*   4) Q              to Exit            	*");
        System.out.println("*********************************************");
		
		CommandService commandService = new CommandService(new CommandStore());
		commandService.start();
	}
}
