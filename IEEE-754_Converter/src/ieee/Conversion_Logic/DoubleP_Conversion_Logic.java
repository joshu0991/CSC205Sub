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
		String fraction = splitNum[1];
		fraction = "0." + fraction;
		int intWholeNum = l.convertToInt(wholeNum);
		String binWholeNum = Integer.toBinaryString(intWholeNum);
		exponet = binWholeNum.length() - 1;
		//int biasNum = "some 64 bit bias " + exponet;
		
		return binNum;
	}

}
