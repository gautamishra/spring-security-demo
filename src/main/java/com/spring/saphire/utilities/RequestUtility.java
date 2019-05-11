package com.spring.saphire.utilities;

import javax.servlet.http.HttpServletRequest;

public class RequestUtility {

	public static String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
}
