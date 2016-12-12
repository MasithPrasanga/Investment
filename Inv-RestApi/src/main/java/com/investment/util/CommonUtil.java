package com.investment.util;

import java.util.UUID;

public class CommonUtil {

	public static String generateUserActivationKey() {
		UUID uniqueKey = UUID.randomUUID();
		return uniqueKey.toString();
	}
}
