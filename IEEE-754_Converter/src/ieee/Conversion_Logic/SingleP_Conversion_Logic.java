package ieee.Conversion_Logic;
import java.lang.Math;
public class SingleP_Conversion_Logic {

	// converts a floating point decimal number to a bianary one
	public String convertToSinglePrecision(String decimalNumber) {
		String min = "";
		boolean sign = checkForSign(decimalNumber);//checks for sign
		if (sign == true) {
			min = "1";//adds one if true
			decimalNumber = decimalNumber.substring(1);
		} else {
			min = "0";//0 if positive
		}
		if(!decimalNumber.contains(".")){
			decimalNumber = decimalNumber + ".0";
		}
		if(checkIfZero(decimalNumber) == true){
			String zero = toString("0", "00000000", "00000000000000000000000");
			//System.out.println(zero);
			return zero;
		}
		String[] splitNum = decimalNumber.split("\\.");//splits number at decimal
		int exponet = 0;
		String wholeNum = splitNum[0];
		String fraction = splitNum[1];
		fraction = "0." + fraction;
		int intWholeNum = convertToInt(wholeNum);
		String binWholeNum = Integer.toBinaryString(intWholeNum);
		exponet = binWholeNum.length() - 1;//exponent minus one 
		int biasNum = 127 + exponet; // number to store as 8 bit bianary expoent
		String frontMantissa = binWholeNum.substring(1);//front of mantissa starts at one
		String binFractionNum = convertFraction(fraction, exponet);//converts fraction to binFraction
		String MantissaNoPadding = frontMantissa + binFractionNum;
		String mantissa = addZerosToMantissa(MantissaNoPadding);//pads with zeros
		String exp = convertBiasToBin(biasNum);//converts bias to binary
		if (mantissa.length() > 23) {// if mantissa is larger than 23 chop of end
			mantissa = chopMantissa(mantissa);
		}
		String formattedNumber = toString(min, exp, mantissa);
		System.out.println(formattedNumber);
		return formattedNumber;
	}

////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public boolean checkIfZero(String num){
		int len = num.length();
		for(int i = 0; i < len; i++){
			if(num.charAt(i)=='0' || num.charAt(i) == '.'){
				continue;
			}else{
				return false;
			}
			
		}
		return true;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////	
	
	//Converts a 32 bit binary number to a decimal number
	public String convertFromSinglePrecision(String binNum) {
		if(binNum.length() < 32){
			binNum = addZerosToMantissa(binNum);
		}
		String rString = "";
		//gets numbers
		char sign = binNum.charAt(0);
		String exponent = binNum.substring(1, 9);
		String mantissa = binNum.substring(9, 32);
		
		if(checkIfZero(binNum) == true){
			String r = "0.0";
			System.out.print(r);
			return r;
		}
		//positive or neg. checker
		if (sign == '1') {
			rString = "-";
		}
		
		//if digit one is a zerp add a one
		if (mantissa.charAt(0) == '0') {
			mantissa = "1" + mantissa;
		}
		int decEx = convertBinExpoToDec(exponent) - 127;// take expoenent minus bias
		String wholeNum = convertToWholeNum(mantissa, decEx + 1);//converts to a whole num from bin num
		rString += wholeNum;
		mantissa = mantissa.substring(decEx+1);//gets mantissa
		String decimal = convertToDec(mantissa);//converts decimal
		decimal = decimal.substring(1);
		rString += decimal;
		//System.out.print(rString);
		return rString;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////	
	
	//converts decimal bin to whole num
	public String convertToDec(String man){
		int len = man.length();
		double tot = 0;
		for(int i = 0; i < len; i++){
			if(man.charAt(i)=='1'){
			i = i + 1;
			tot += Math.pow(2, -i);
			}	
		}
		String rString = String.valueOf(tot);
		return rString;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////	
	
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
	
	//converts mantissa to whole 
	public String convertToWholeNum(String mantissa, int exponent) {
		String wholeNum = mantissa.substring(0, exponent);
		int rInt = Integer.parseInt(wholeNum, 2);
		String rString = String.valueOf(rInt);
		return rString;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//converts bin expoenent to a decimal
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
	
	//chops end of a string
	public String chopMantissa(String mantissa) {
		String rString = "";
		for (int counter = 0; counter < 23; counter++) {
			rString = rString + mantissa.charAt(counter);
		}
		return rString;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//converts bias to a bin num
	public String convertBiasToBin(int bias) {
		String binExp = Integer.toBinaryString(bias);
		String rString = "";
		if (binExp.length() < 8) {//pads the number if too short
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
	
	//adds 0 to mantissa
	public String addZerosToMantissa(String man) {
		int len = man.length();
		for (int counter = len; counter < 32; counter++) {
			String r = man += "0";
			man = r;
			r = "";
		}
		return man;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//converts fractional part
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
				// case where string isnt bigger than 1
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
	
	//checks string for -
	public boolean checkForSign(String num) {
		if (num.charAt(0) == '-') {
			return true;
		} else {
			return false;
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//test code
	public static void main(String[] args) {
		String numbertoconvert = "-20.6252";
		numbertoconvert = "0";
		String binNumToConv = "11000001101001010000000001101001";
		binNumToConv = "00000000000000000000000000000";
		SingleP_Conversion_Logic l = new SingleP_Conversion_Logic();
		l.convertToSinglePrecision(numbertoconvert);
		l.convertFromSinglePrecision(binNumToConv);
	}
}
/*
 *fix infinity problem  
 */