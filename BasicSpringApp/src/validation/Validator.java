package validation;

import java.util.ArrayList;

public class Validator {
	public static boolean isValid(String toValidate) {
		if (!toValidate.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isObjectValid(ArrayList<String> toValidate) {
		for (int i = 0; i < toValidate.size(); i++) {
			if (toValidate.get(i).isEmpty()) {
				return false;
			}
		}
		return true;
	}
}
