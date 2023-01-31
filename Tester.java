import java.util.Random;

public class Tester {
    public static void main(String[] array) {
        Random random = new Random();
        Rebaser rebaser = new Rebaser();
        int testCount = 50;

        boolean[] testResults;
        String[][] testDetails;
        // [ [ expected result, actual result ] ... ]
        
        String toConvert;
        String actual;
        String expected;
        int toBase;
        int fromBase;
        int digits;
        double result;
        
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("  convertToBase10 tests    ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        testResults = new boolean[testCount];
        testDetails = new String[testCount][2];
        toBase = 10;        
        for (int testNumber = 0; testNumber < testCount; ++testNumber) {
            fromBase = random.nextInt(2, 17);
            digits = (int)Math.ceil(Math.log10((double)random.nextInt(1, 10000)));
            
            toConvert = "";
            for (int i = 0; i < digits; i++) {
                toConvert += Character.toString(randomDigit(fromBase));
            }
            rebaser.setValue(toConvert);
            
            expected = Integer.toString(Integer.parseInt(toConvert, fromBase));
            actual = rebaser.convertToBase10(fromBase);

            testDetails[testNumber][0] = expected;
            testDetails[testNumber][1] = actual;
            testResults[testNumber] = actual.equals(expected);
        }

        result = 0.0;
        System.out.println("Failed tests:");
        
        // list failed test numbers
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            if (!testResults[testNumber]) {
                System.out.print(testNumber + ", ");
            } else {
                result += 1.0;
            }
        }
        System.out.println("\n" + result / 50.0 * 100.0 + "% success rate\n");

        // Show results of failed tests
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            if (!testResults[testNumber]) {
                System.out.println(testNumber + ") Expected \"" + testDetails[testNumber][0] + "\", got \"" + testDetails[testNumber][1] + "\"");
            }
        }        
        
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("  convertToBaseN tests");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~\n");


        testResults = new boolean[testCount];
        testDetails = new String[testCount][2];
        fromBase = 10;
        
        for (int testNumber = 0; testNumber < testCount; ++testNumber) {
            toBase = random.nextInt(2, 17);
            toConvert = Integer.toString(random.nextInt(1000000));

            rebaser.setValue(toConvert);
            expected = Integer.toString(Integer.parseInt(toConvert, 10), toBase);
            actual = rebaser.convertToBaseN(toBase);
            
            testDetails[testNumber][0] = expected;
            testDetails[testNumber][1] = actual;
            testResults[testNumber] = actual.equals(expected);
        }

        result = 0.0;
        System.out.println("Failed tests:");
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            if (!testResults[testNumber]) {
                System.out.print(testNumber + ", ");
            } else {
                result += 1.0;
            }
        }
        System.out.println("\n" + result / 50.0 * 100.0 + "% success rate\n");
        // Show results of failed tests
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            if (!testResults[testNumber]) {
                System.out.println(testNumber + ") Expected \"" + testDetails[testNumber][0] + "\", got \"" + testDetails[testNumber][1] + "\"");
            }
        }
    }
    
    public static char randomDigit(int bound) {
        return (new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' })[new Random().nextInt(bound)];
    }
}
