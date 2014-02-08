package ieee.Conversion_Logic;

public class DoubleP_Conversion_Logic {
	
	SingleP_Conversion_Logic l = new SingleP_Conversion_Logic();
	
	public String convertToDoubleP(String decNum){
		String binNum = "";
		boolean sign = l.checkForSign(decNum);
		
		if(sign == true){
			binNum += "1";
			
		}else{
			binNum += "0";
		}
		
		String[] splitNum = decNum.split("\\.");
		int exponet = 0;
		String wholeNum = splitNum[0];
		wholeNum = wholeNum.substring(1);
		String fraction = splitNum[1];
		fraction = "0." + fraction;
		int intWholeNum = convertToInt(wholeNum);
		String binWholeNum = Integer.toBinaryString(intWholeNum);
		exponet = binWholeNum.length() - 1;
		int biasNum = 1023 + exponet;
		String exp = convertBiasToBin(biasNum);
		
		String frontMantissa = binWholeNum.substring(1);

		String binFractionNum = convertFraction(fraction, exponet);
		String mantissa = frontMantissa + binFractionNum;
		String formattedNum = l.toString(binNum, exp, mantissa);
		System.out.println(formattedNum);
		return formattedNum;
	}

	
	public String convertFraction(String fraction, int exp) {
		double fFrac = l.convertToDouble(fraction);
		String rString = "";
		double runningNum = fFrac * 2;
		int counter = 1;
		boolean cont = true;
		while (counter <= 52 - exp && cont == true) {

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

////////////////////////////////////////////////////////////////////////////////////////////////////	
		
	public String convertBiasToBin(int bias) {
		String binExp = Integer.toBinaryString(bias);
		String rString = "";
		if (binExp.length() < 11) {
			for (int counter = binExp.length(); counter < 11; counter++) {
				rString += "0" + binExp;
				binExp = rString;
				rString = "";
			}
		}
		return binExp;
	}
	
	
	public int convertToInt(String num) {
		int intNum;
		intNum = Integer.parseInt(num);
		return intNum;
	}
	
	public static void main(String[] args) {
		String numbertoconvert = "-20.6252";
		//String binNumToConv = "11000001101001010000000001101001";
		DoubleP_Conversion_Logic l = new DoubleP_Conversion_Logic();
		l.convertToDoubleP(numbertoconvert);
		
	}
}
