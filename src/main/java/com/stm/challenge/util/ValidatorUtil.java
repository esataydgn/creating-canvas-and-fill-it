package com.stm.challenge.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.stm.challenge.service.CommandBase;
import com.stm.challenge.store.CommandStore;

public class ValidatorUtil {
	
	protected static final int CREATE = 2;
	protected static final int LINE = 4;
	protected static final int RECTANGLE = 4;
	protected static final int FILL_BUCKET = 3;

	public static boolean isCanvasParamsValid(String[] params) {
		if (!isParamSizeValid(CREATE, params) || !isParamsInstanceOfInteger(params)) {
			return false;
		}
		return true;
	}

	public static boolean isDrawLineParamsValide(CommandStore commandStore, String[] params) {
		if (!isParamSizeValid(LINE, params) || !isCanvasExist(commandStore) || !isParamsInstanceOfInteger(params)) {
			return false;
		}
		return true;
	}

	public static boolean isDrawRectangleParamsValid(CommandStore commandStore, String[] params) {
		if (!isParamSizeValid(RECTANGLE, params) || !isCanvasExist(commandStore)
				|| !isParamsInstanceOfInteger(params)) {
			return false;
		}
		return true;
	}

	public static boolean isFillBucketParamsValid(CommandStore commandStore, String[] params) {
		if (!isParamSizeValid(FILL_BUCKET, params) || !isCanvasExist(commandStore)
				|| !isFillBucketParamsAsExpected(params)) {
			return false;
		}
		return true;
	}

	public static boolean isCanvasExist(CommandStore commandStore) {
		if (commandStore == null || commandStore.getDrawingTemplates() == null
				|| commandStore.getDrawingTemplates().size() == 0) {
			System.out.println("Canvas is not created yet!");
			return false;
		}
		return true;
	}

	public static boolean isParamSizeValid(int expectedParameterSize, String[] params) {
		if (params.length != expectedParameterSize) {
			System.err.println("Invalid parameter size!");
			return false;
		}
		return true;
	}

	public static boolean isCommandNotEmpty(CommandBase commandType) {
		if (null == commandType) {
			return false;
		}
		return true;
	}

	public static boolean isNotEmptyChar(String cmd) {
		if (StringUtils.isBlank(cmd)) {
			System.err.println("Invalid command type...");
			return false;
		}
		return true;
	}

	public static boolean isFillBucketParamsAsExpected(String[] params) {
		int count = 0;
		for (String parameter : params) {
			count++;
			if (count == 3) {
				if ("".equals(parameter)) {
					System.err.println("invalid parameters for filling the bucket.");
					return false;
				}
			} else if (!NumberUtils.isCreatable(parameter)) {
				System.err.println("invalid parameters to fill the bucket.");
				return false;
			}
		}
		return true;
	}

	public static boolean isParamsInstanceOfInteger(String[] params) {
		for (String paramater : params) {
			if (!NumberUtils.isCreatable(paramater)) {
				System.err.println("invalid parameters to draw a line!");
				return false;
			}
		}
		return true;
	}
}
