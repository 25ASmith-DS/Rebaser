
public class Rebaser {

    private String v;
    
    public Rebaser(){
        v = "0";
    }

    /***
    * Initializes a Rebaser with a string representation of a number.
    * If an invalid string is used (Above base 16) then the initialized value = "0".
    * @param value
    *: init value
    ***/
    public Rebaser(String value) {
        if (Rebaser.isValidNumber(value, 16)) {
            this.v = value;
        } else {
            this.v = "0";
        }
    }

    public void setValue(String value) {
        if (Rebaser.isValidNumber(value, 16)) {
            this.v = value;
        } else {
            this.v = "0";
        }
    }

    public String getValue() {
        return this.v;   
    }

    public String convertToBaseN(int toBase) {
        // TODO: make this
        return "TODO";
    }

    public String convertToBase10(int fromBase) {
        int result = 0;
        int digits = this.v.length();
        for (int digit = digits - 1, placeValue = 0; digit >= 0; digit--, placeValue++) {
            result += digitToBaseN(this.v.charAt(digit)) * (int)Math.pow(fromBase, placeValue);
        }
        return Integer.toString(result);
        
    }


    /*** private methods &#11088 ***/

    // convert digit to integer value
    private static int digitToBaseN(char digit) {
        switch (digit) {
            case '0': return 0;
            case '1': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            case 'A': return 10;
            case 'B': return 11;
            case 'C': return 12;
            case 'D': return 13;
            case 'E': return 14;
            case 'F': return 15;
            default: return -1;
        }
    }

    private static boolean isValidDigit(char digit, int base) {
        int value = Rebaser.digitToBaseN(digit);
        // value is between 1 and F, and does not exceed the max value for a base
        // example, the value of 'A' is 10, so anything exceeding '9' is not valid in base 10
        return value != -1 && value < base;
    }

    private static boolean isValidNumber(String number, int base) {
        // guard statement: string isn't empty
        if (number.isEmpty()) {
            return false;
        }

        // guard statement: digits are valid
        for( int i = 0 ; i < number.length(); i++ ) {
            if (!isValidDigit(number.charAt(i), base)) {
                return false;
            }
        }
        
        // happy path: digits are valid and the string isn't empty
        return true;
    }
}
