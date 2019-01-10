import java.text.NumberFormat;

/**
 * FormatedPrinting.java
 */

/**
 * @author {Mugdha Phadke}
 * This class creates the formated string for printing
 */
public class FormatedPrinting {

	/*format the object to respective form
	 * @param Object and integer flag 1 for currency and 2 for percent
	 * depending on the yype of object set the decimal as 0 or 2 for integer and double resp
	 */
	public static String formatObject(Object o, int flag) {

		String s = null;
		switch (flag) {

		case 1:
			s = CurrencyFormat(o);
			break;
		case 2:
			s = PercentFormat(o);
			break;
		}
		return s;
	}

	//return the currency format
	static String CurrencyFormat(Object o) {
		NumberFormat currency = null;
		if (o instanceof Double) {
			currency = NumberFormat.getCurrencyInstance();
			currency.setMaximumFractionDigits(2);

		} else if (o instanceof Integer) {
			currency = NumberFormat.getCurrencyInstance();
			currency.setMaximumFractionDigits(0);
		}
		return currency.format(o);
	}

	//return the percent format
	static String PercentFormat(Object o) {
		NumberFormat percent = null;
		if (o instanceof Double) {
			percent = NumberFormat.getPercentInstance();
			percent.setMinimumFractionDigits(2);

		} else if (o instanceof Integer) {
			percent = NumberFormat.getPercentInstance();
			percent.setMinimumFractionDigits(0);

		}
		return percent.format(o);
	}

}
