/**
* Converts positive integers between different number bases
*
* The class stores a number, which then can be accessed with the getValue, convertToBase10, and convertToBaseN methods
*/

public class Rebaser {

    static final char[] digits = {
        '0', '1', '2', '3',
        '4', '5', '6', '7',
        '8', '9', 'A', 'B',
        'C', 'D', 'E', 'F'
    };
    
    private String v;
    
    /**
    * Initializes a Rebaser with the value "0"
    */
    public Rebaser(){
        v = "0";
    }

    /***
    * Initializes a Rebaser with a string representation of a number (initializes with "0" if value is invalid)
    * @param value Init value
    ***/
    public Rebaser(String value) {
        if (Rebaser.isValidNumber(value, 16)) {
            this.v = value.toUpperCase();
        } else {
            this.v = "0";
        }
    }

    /*** 
    * Sets the stored value to "value" if "value" is a valid string representation of an integer
    * @param value String representation of an integer of any base 2-16
    **/
    public void setValue(String value) {
        if (isValidNumber(value, 16)) {
            this.v = value.toUpperCase();
        } else {
            this.v = "0";
        }
    }

    /**
    * Returns the stored value
    * @return Stored value
    */
    public String getValue() {
        return this.v;
    }

    /**
    * Returns the stored number converted from base 10 to a different base
    * @param toBase The base to convert to
    * @return The converted number
    */
    public String convertToBaseN(int toBase) {
        int num = Integer.parseInt(this.v);

        if (toBase < 2 || toBase > 16 ) {
            return  "-1";
        }
        if ( num < 0 ) {
            return  "-1";
        }

        int tmp = num;
        int n;
        String str = "";

        while (true) {
            n = tmp % toBase;
            tmp = tmp / toBase;

            if ( tmp <= 0 ) {
                str += digits[n];
                break;
            } else {
                str += digits[n];
            }
        }
        
        String str1 = "";
        int len = str.length();
        for (int i = 0; i < len; i++) {
            str1 += str.charAt(len-i-1);
        }
        return str1;
    }

    /**
    * Converts the stored value from a specified base into base 10
    * @param fromBase The base of the stored string
    * @return Stored value converted to base 10
    */
    public String convertToBase10(int fromBase) {
        if ( fromBase < 2 || fromBase > 16 ) {
            return "-1";
        }
        if (!isValidNumber(v, fromBase)) {
            return "-1";
        }
        
        int result = 0;
        int len = this.v.length();
        int digitValue;
        int placeValue;
        int place = 0;
        
        for (int digit = len - 1; digit >= 0; digit--) {            
            digitValue = digitToBaseN(this.v.charAt(digit));            
            placeValue = (int)Math.pow(fromBase, place);
            
            result += digitValue * placeValue;
            place++;
        }        
        return Integer.toString(result);
    }

    /*** private methods ***/

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
        if (number.isEmpty() || number == null) {
            return false;
        }
        // guard statement: base is valid
        if (2 > base || 16 < base) {
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
