package ieee.Conversion_Logic;

public class DoubleP_Conversion_Logic {
	
	public String convertToDoubleP(String decNum){
		String binNum = "";
		SingleP_Conversion_Logic l = new SingleP_Conversion_Logic();
		boolean sign = l.checkForSign(decNum);
		
		if(sign == true){
			binNum += '1';
			
		}else{
			binNum += '0';
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
		
		return binNum;
	}

	
	
	public String convertFraction(String frac, int exp){
		String fracString = ""; // fraction for 64 bits = 64 - 12 = 52 bits
		
		
		return fracString;
	}
	
	
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
