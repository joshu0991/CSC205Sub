package ieee.Conversion_Logic;

public class DoubleP_Conversion_Logic {
	
	//instance of single p to pull redundant methods
	SingleP_Conversion_Logic l = new SingleP_Conversion_Logic();
	
	//converts double to a ieee 754 floating point bin num
	public String convertToDoubleP(String decNum){
		String binNum = "";
		boolean sign = l.checkForSign(decNum);//checks for sign
		
		if(sign == true){
			binNum += "1";
			
		}else{
			binNum += "0";
		}
		
		String[] splitNum = decNum.split("\\.");//splits string by dec point 
		int exponet = 0;
		String wholeNum = splitNum[0];
		wholeNum = wholeNum.substring(1);
		String fraction = splitNum[1];
		fraction = "0." + fraction;//need a 0 at front of decimal number
		
		int intWholeNum = convertToInt(wholeNum);//converts number to integer
		String binWholeNum = Integer.toBinaryString(intWholeNum);
		exponet = binWholeNum.length() - 1;//exponent is equal to length -1
		int biasNum = 1023 + exponet;//bias for 64 bit is +1023
		String exp = convertBiasToBin(biasNum);
		
		String frontMantissa = binWholeNum.substring(1);//front part of mantissa

		String binFractionNum = convertFraction(fraction, exponet);//converts fraction based on size of exponent
		String mantissa = frontMantissa + binFractionNum;//both parts of mantissa
		String formattedNum = l.toString(binNum, exp, mantissa);//to a formated string
		System.out.println(formattedNum);
		return formattedNum;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//converts from a double percicision bin num to decimal
	public String convertFromDoubleP(String binNum){
		String rString = "";
		//chops up num accordingly
		char sign = binNum.charAt(0);
		String exponent = binNum.substring(1, 12);
		String mantissa = binNum.substring(12, 64);

		//checks for negativitiy
		if (sign == '1') {
			rString = "-";
		}

		//if first number is a zero add a 1
		if (mantissa.charAt(0) == '0') {
			mantissa = "1" + mantissa;
		}
		int decEx = l.convertBinExpoToDec(exponent) - 1023;//subtracts bias 
		String wholeNum = l.convertToWholeNum(mantissa, decEx + 1);//converts whole num part
		rString += wholeNum;
		mantissa = mantissa.substring(decEx+1);//whole num out of decimal
		String decimal = convertToDec(mantissa);//converts decimal
		decimal = decimal.substring(1);//creates decimal string
		rString += decimal;
		System.out.println(rString);
		return rString;
		
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//converts bin to fraction
	public String convertToDec(String man){
		int len = man.length();
		double tot = 0;
		for(int i = 0; i < len; i++){
			if(man.charAt(i)=='1'){//if number is a 1 multiply by two to that power and adds it to total
			i = i + 1;
			tot += Math.pow(2, -i);//2^-i
			}	
		}
		String rString = String.valueOf(tot);
		return rString;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//converts fraction bin num to base ten number
	public String convertFraction(String fraction, int exp) {
		double fFrac = l.convertToDouble(fraction);//converts num to a double
		String rString = "";
		double runningNum = fFrac * 2;//multiples by two to start
		int counter = 1;
		boolean cont = true;//init cont to be true
		while (counter <= 52 - exp && cont == true) {

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
		//round last num up
		if (runningNum > 0.5) {
			int term = rString.length();
			rString = rString.substring(0, term - 1);
			rString += "1";// round up last digit if last number is greater than
							// .5
		}
		return rString;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////	
		
	//converts the bias to a bin number
	public String convertBiasToBin(int bias) {
		String binExp = Integer.toBinaryString(bias);
		String rString = "";
		if (binExp.length() < 11) {
			for (int counter = binExp.length(); counter < 11; counter++) {//less then 11 bits add 0's
				rString += "0" + binExp;
				binExp = rString;
				rString = "";
			}
		}
		return binExp;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//converts a number to a an int
	public int convertToInt(String num) {
		int intNum;
		intNum = Integer.parseInt(num);
		return intNum;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//test code
	public static void main(String[] args) {
		String numbertoconvert = "-20.6252";
		String binNumToConv = "1100000000110100101000000000110100011011011100010111010110001110";
		DoubleP_Conversion_Logic l = new DoubleP_Conversion_Logic();
		l.convertToDoubleP(numbertoconvert);
		l.convertFromDoubleP(binNumToConv);
		
	}
}
