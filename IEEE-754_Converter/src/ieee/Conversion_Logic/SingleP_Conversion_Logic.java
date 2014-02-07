package ieee.Conversion_Logic;

public class SingleP_Conversion_Logic {

	// converts a floating point decimal number to a bianary one
	public String convertToSinglePrecision(String decimalNumber) {
		String min = "";
		boolean sign = checkForSign(decimalNumber);
		if (sign == true) {
			min = "1";
			decimalNumber = decimalNumber.substring(1);
		} else {
			min = "0";
		}
		String[] splitNum = decimalNumber.split("\\.");
		int exponet = 0;
		String wholeNum = splitNum[0];
		String fraction = splitNum[1];
		fraction = "0." + fraction;
		int intWholeNum = convertToInt(wholeNum);
		String binWholeNum = Integer.toBinaryString(intWholeNum);
		exponet = binWholeNum.length() - 1;
		int biasNum = 127 + exponet; // number to store as 8 bit bianary expoent
		String frontMantissa = binWholeNum.substring(1);
		String binFractionNum = convertFraction(fraction, exponet);
		String MantissaNoPadding = frontMantissa + binFractionNum;
		String mantissa = addZerosToMantissa(MantissaNoPadding);
		String exp = convertBiasToBin(biasNum);
		if (mantissa.length() > 23) {
			mantissa = chopMantissa(mantissa);
		}
		String formattedNumber = toString(min, exp, mantissa);
		System.out.println(formattedNumber);
		return formattedNumber;
	}

/////////////////////////////////////////////////////////////////////////////////////////////	
	
	//Converts a 32 bit binary number to a decimal number
	public String convertFromSinglePrecision(String binNum) {
		String rString = "";
		char sign = binNum.charAt(0);
		String exponent = binNum.substring(1, 9);
		String mantissa = binNum.substring(9, 32);

		if (sign == '1') {
			rString = "-";
		}

		if (mantissa.charAt(0) == '0') {
			mantissa = "1" + mantissa;
		}
		
		int decEx = convertBinExpoToDec(exponent) - 127;// take expoenent minus
														// bias
		String wholeNum = convertToWholeNum(mantissa, decEx + 1);
		rString += wholeNum;
		System.out.print("rString " + rString);
		return exponent;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////	
	
	//pads the mantissa for binanry number
	public String padBinNumMantissa(String binNum){
		int len = binNum.length();
		String rString = "";
		for(int i = len; i <=23; i++){
			rString = binNum + "0";
		}
		System.out.print("bin Num " + rString);
		return rString;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public String convertToWholeNum(String mantissa, int exponent) {
		String wholeNum = mantissa.substring(0, exponent);
		int rInt = Integer.parseInt(wholeNum, 2);
		String rString = String.valueOf(rInt);
		return rString;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public int convertBinExpoToDec(String binEx) {
		int rInt = Integer.parseInt(binEx, 2);
		return rInt;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public String toString(String sign, String exponet, String mantissa) {
		String rString = sign + " | " + exponet + " | " + mantissa;
		return rString;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public String chopMantissa(String mantissa) {
		String rString = "";
		for (int counter = 0; counter < 23; counter++) {
			rString = rString + mantissa.charAt(counter);
		}
		return rString;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public String convertBiasToBin(int bias) {
		String binExp = Integer.toBinaryString(bias);
		String rString = "";
		if (binExp.length() < 8) {
			for (int counter = binExp.length(); counter < 8; counter++) {
				rString += "0" + binExp;
				binExp = rString;
				rString = "";
			}
		}
		return binExp;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public int convertToInt(String num) {
		int intNum;
		intNum = Integer.parseInt(num);
		return intNum;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public String toString(int intNum) {
		String rNum = String.valueOf(intNum);
		return rNum;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public double convertToDouble(String fraction) {
		double fFraction = Double.parseDouble(fraction);
		return fFraction;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public String addZerosToMantissa(String man) {
		int len = man.length();
		for (int counter = len; counter < 23; counter++) {
			man += "0";
		}
		return man;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public String convertFraction(String fraction, int exp) {
		double fFrac = convertToDouble(fraction);
		String rString = "";
		double runningNum = fFrac * 2;
		int counter = 1;
		boolean cont = true;
		while (counter <= 23 - exp && cont == true) {

			// if the string is larger than 1 adds 1 to rval and minuses 1
			if (runningNum >= 1) {
				runningNum = runningNum - 1;
				rString = rString + "1";
				runningNum = runningNum * 2;
				// case where string isnt bigger than 1000
			} else if (runningNum < 1 && runningNum > 0) {
				rString = rString + "0";
				runningNum = runningNum * 2;
				// if is equal to zero means stop and fill in zeros to end
			} else if (runningNum == 0) {
				cont = false;

			}
			counter++;
		}
		if (runningNum > 0.5) {
			int term = rString.length();
			rString = rString.substring(0, term - 1);
			rString += "1";// round up last digit if last number is greater than
							// .5
		}
		return rString;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public boolean checkForSign(String num) {
		if (num.charAt(0) == '-') {
			return true;
		} else {
			return false;
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public static void main(String[] args) {
		String numbertoconvert = "-20.625256";
		String binNumToConv = "11000001101001010000000010000110";
		SingleP_Conversion_Logic l = new SingleP_Conversion_Logic();
		l.convertToSinglePrecision(numbertoconvert);
		l.convertFromSinglePrecision(binNumToConv);
	}

}
/*
 * to do list
 * check for NaNs
 * check rubrinc for other special cases
 */
 