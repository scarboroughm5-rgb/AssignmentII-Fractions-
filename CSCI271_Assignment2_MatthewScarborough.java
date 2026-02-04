/* Assignment 2 for csci 271-001 Spring 2026

Auther: Matthew Scarboroughm
OS:  Window 11 Home edition 
Compiler: javac 25.0.1
Date: January  22, 2025

Purpose
The purpose of this program is to calculate the final numerical grade based on the grading scheme that was published in the course syllbus. 

I declare and confirm the following:

-I have not discussed this program code with anyone other than my instructor or teaching assistant assigned to these course. 
-I have not used prgramming code obtained from someone else, or any unautherised sources, including the internet, either modified or unmodified. 
-If any source code or documentation used in my program was obtained from other sources, like a text book, or course notes, i have clearly indicated that with a  proper citation in the comments of my program. 
-I have not designed this program in such a way to defeat or interfere with the normal operations of the supplied grading code. 

Matthew Scarborough
*/
public class CSCI271_Assignment2_MatthewScarborough {
        private long Numerator; 
        private long Denominator;

        /************************Constructor (Num, Den)***********************
         * Description: This function initializes a Fraction object with a numerator and denominator
         *
         * Parameters: Num (numerator), Den (denominator)
         *
         * pre: None
         *
         * post: Fraction object is initialized with normalized values
         *
         * Returns: Fraction object
         *
         * Called by: main
         * Calls: normalize
         ***********************************************************************/
        public CSCI271_Assignment2_MatthewScarborough(long Num, long Den){
            
            // case 1: both numerator and denominator are 0
            if(Num == 0 && Den == 0){
                this.Numerator = 0; // sets the Numerator variable to 0 
                this.Denominator = 0; // sets the denominator to 0 
                return;
            }

            // case 2: denominator is 0
            if (Den == 0){
                // store signs for positive and negative infinity
                this.Numerator = (Num > 0) ? 1 : -1; 
                this.Denominator = 0; // sets denominator to 0 
                return;
            }

            // normal case 
            this.Numerator = Num; // sets numerator to num 
            this.Denominator = Den; // sets denominator to den 
            normalize();
        }
        /************************Constructor (Num)***********************
         * Description: This function initializes a Fraction object with a whole number
         *
         * Parameters: Num (whole number)
         *
         * pre: None
         *
         * post: Fraction object is initialized with denominator of 1
         *
         * Returns: Fraction object
         *
         * Called by: main
         * Calls: None
         ***********************************************************************/
        public CSCI271_Assignment2_MatthewScarborough(long Num){
            this.Numerator = Num;
            this.Denominator = 1;
        }

    /************************GCD***********************
     * Description: This function calculates the greatest common divisor of two numbers
     *
     * Parameters: a, b (two long values)
     *
     * pre: None
     *
     * post: GCD is calculated using Euclidean algorithm
     *
     * Returns: The greatest common divisor
     *
     * Called by: normalize
     * Calls: None
     ***********************************************************************/
    private static long gcd(long a, long b){

        a = Math.abs(a); // absolute value of a
        b = Math.abs(b); // absolute value of b 
        
        if (a == 0 && b == 0){
            return 1; // return 0
        }

        if (a == 0){
            return b; // returns the denominator 
        }

        if(b == 0){
            return a; // returns the numerator 
        }
        while (b != 0){
            long temp = b; // temp value to store b
            b = a % b; // sets b to the modulos of a 
            a = temp; // a equals to temp 
        }
        return a; // returns a 
    }
    
    /************************Normalize***********************
     * Description: This function normalizes a fraction by reducing it to lowest terms and ensuring positive denominator
     *
     * Parameters: None
     *
     * pre: Fraction object must be initialized
     *
     * post: Fraction is in normalized form with positive denominator and no common factors
     *
     * Returns: None
     *
     * Called by: Constructor
     * Calls: gcd
     ***********************************************************************/
    private void normalize(){

        // if the denominator is 0 then it returns 
        if(this.Denominator == 0){
            return;
        }
        // if the denominator is less this zero, this will see if it numerator and denominator need to be negative
        if(this.Denominator < 0){
            this.Numerator = -this.Numerator;
            this.Denominator = -this.Denominator;
        }

        long divisor = gcd(this.Numerator, this.Denominator);
        // if the divisro is not zero the Numerator and denominator will continue to be divided
        if(divisor != 0){
            this.Numerator /= divisor;
            this.Denominator /= divisor;
        }
        // if numerator is equals to 0 and denominator does not denominator equals 1
        if(this.Numerator == 0 && this.Denominator != 0){
            this.Denominator = 1;
        }
    }

    /************************isNan***********************
     * Description: This function checks if the fraction represents NaN (0/0)
     *
     * Parameters: Nonen
     *
     * pre: Fraction object must be initialized
     *
     * post: Boolean value is returned
     *
     * Returns: true if fraction is NaN, false otherwise
     *
     * Called by: All arithmetic methods
     * Calls: None
     ***********************************************************************/
    public boolean isNan(){
        return (this.Numerator == 0 && this.Denominator == 0);
    }

    /************************isInfinite***********************
     * Description: This function checks if the fraction represents infinity
     *
     * Parameters: None
     *
     * pre: Fraction object must be initialized
     *
     * post: Boolean value is returned
     *
     * Returns: true if fraction is infinity, false otherwise
     *
     * Called by: All arithmetic methods
     * Calls: None
     ***********************************************************************/
    public boolean isInfinite(){
        return (this.Denominator == 0 && this.Numerator != 0);
    }

    /************************toString***********************
     * Description: This function converts the fraction to its string representation
     *
     * Parameters: None
     *
     * pre: Fraction object must be initialized
     *
     * post: String representation is created
     *
     * Returns: String representation of the fraction
     *
     * Called by: main, testing
     * Calls: isNan, isInfinite
     ***********************************************************************/
    @Override
    public String toString(){
        // check for NaN
        if(isNan()){
            return "NaN";
        }
        // check if denominator is 0
        if(isInfinite()){
            return (this.Numerator > 0) ? "Infinity" : "-Infinity";
        }
            //check if denominator is 1
        if(this.Denominator == 1){
            return Long.toString(this.Numerator);
        }

        // normal fraction
        return this.Numerator + "/" + this.Denominator;
    } 

    /************************add***********************
     * Description: This function adds two fractions together
     *
     * Parameters: other (another Fraction object)
     *
     * pre: Both fraction objects must be initialized
     *
     * post: The sum of two fractions is calculated
     *
     * Returns: A new Fraction object representing the sum
     *
     * Called by: main
     * Calls: isNan, isInfinite
     ***********************************************************************/
    public CSCI271_Assignment2_MatthewScarborough add(CSCI271_Assignment2_MatthewScarborough other){
        if(this.isNan() || other.isNan()) return new CSCI271_Assignment2_MatthewScarborough(0,0);
        if(this.isInfinite() && other.isInfinite()){
            if((this.Numerator > 0 && other.Numerator > 0) || (this.Numerator < 0 && other.Numerator < 0)){
                return new CSCI271_Assignment2_MatthewScarborough(this.Numerator, 0);
            }
            return new CSCI271_Assignment2_MatthewScarborough(0,0);
        }
        if (this.isInfinite()) return new CSCI271_Assignment2_MatthewScarborough(this.Numerator, 0);
        if (other.isInfinite()) return new CSCI271_Assignment2_MatthewScarborough(other.Numerator, 0);
        
        long newNum = this.Numerator * other.Denominator + other.Numerator * this.Denominator;
        long newDen = this.Denominator * other.Denominator;
        return new CSCI271_Assignment2_MatthewScarborough(newNum, newDen);

    }

    /************************subtract***********************
     * Description: This function subtracts one fraction from another
     *
     * Parameters: other (another Fraction object)
     *
     * pre: Both fraction objects must be initialized
     *
     * post: The difference of two fractions is calculated
     *
     * Returns: A new Fraction object representing the difference
     *
     * Called by: main
     * Calls: isNan, isInfinite
     ***********************************************************************/
    public CSCI271_Assignment2_MatthewScarborough subtract(CSCI271_Assignment2_MatthewScarborough other){
        if(this.isNan() || other.isNan()) return new CSCI271_Assignment2_MatthewScarborough(0,0);
        if(this.isInfinite() && other.isInfinite()){
            if((this.Numerator > 0 && other.Numerator < 0) || (this.Numerator < 0 && other.Numerator > 0)){
                return new CSCI271_Assignment2_MatthewScarborough(this.Numerator, 0);
            }
            return new CSCI271_Assignment2_MatthewScarborough(0,0);
        }
        if (this.isInfinite()) return new CSCI271_Assignment2_MatthewScarborough(this.Numerator, 0);
        if (other.isInfinite()) return new CSCI271_Assignment2_MatthewScarborough(other.Numerator, 0);

        long newNum = this.Numerator * other.Denominator - other.Numerator * this.Denominator;
        long newDen = this.Denominator * other.Denominator;
        return new CSCI271_Assignment2_MatthewScarborough(newNum, newDen);
    }

    /************************multiply***********************
     * Description: This function multiplies two fractions together
     *
     * Parameters: other (another Fraction object)
     *
     * pre: Both fraction objects must be initialized
     *
     * post: The product of two fractions is calculated
     *
     * Returns: A new Fraction object representing the product
     *
     * Called by: main
     * Calls: isNan, isInfinite
     ***********************************************************************/
    public CSCI271_Assignment2_MatthewScarborough multiply(CSCI271_Assignment2_MatthewScarborough other){
        if(this.isNan() || other.isNan()) return new CSCI271_Assignment2_MatthewScarborough(0,0);
        if(this.isInfinite()){
            if(other.Numerator == 0) return new CSCI271_Assignment2_MatthewScarborough(0,0);
            return new CSCI271_Assignment2_MatthewScarborough((this.Numerator > 0 && other.Numerator > 0) || (this.Numerator < 0 && other.Numerator < 0) ? 1 : -1, 0);
        }
        if(other.isInfinite()){
            if(this.Numerator == 0) return new CSCI271_Assignment2_MatthewScarborough(0,0);
            return new CSCI271_Assignment2_MatthewScarborough((this.Numerator > 0 && other.Numerator > 0) || (this.Numerator < 0 && other.Numerator < 0) ? 1 : -1, 0);
        }

        long newNum = this.Numerator * other.Numerator;
        long newDen = this.Denominator * other.Denominator;
        return new CSCI271_Assignment2_MatthewScarborough(newNum, newDen);
    }

    /************************divide***********************
     * Description: This function divides one fraction by another
     *
     * Parameters: other (another Fraction object)
     *
     * pre: Both fraction objects must be initialized and divisor must not be zero
     *
     * post: The quotient of two fractions is calculated
     *
     * Returns: A new Fraction object representing the quotient
     *
     * Called by: main
     * Calls: isNan, isInfinite
     ***********************************************************************/
    public CSCI271_Assignment2_MatthewScarborough divide(CSCI271_Assignment2_MatthewScarborough other){
        if(this.isNan() || other.isNan() || (other.Numerator == 0 && other.Denominator != 0)) return new CSCI271_Assignment2_MatthewScarborough(0,0);
        if(other.isInfinite()){
            return new CSCI271_Assignment2_MatthewScarborough(0,1 );
           }
        
        if (other.Numerator == 0 && other.Denominator != 0){
            return new CSCI271_Assignment2_MatthewScarborough((this.Numerator > 0 && other.Denominator > 0) || (this.Numerator < 0 && other.Denominator < 0) ? 1 : -1, 0);

        }
        if (this.isInfinite()){
            return new CSCI271_Assignment2_MatthewScarborough((this.Numerator > 0 && other.Numerator > 0) || (this.Numerator < 0 && other.Numerator < 0) ? 1 : -1, 0);
        }
        long newNum = this.Numerator * other.Denominator;
        long newDen = this.Denominator * other.Numerator;
        return new CSCI271_Assignment2_MatthewScarborough(newNum, newDen);
    }

    /************************negate***********************
     * Description: This function negates the fraction (multiplies by -1)
     *
     * Parameters: None
     *
     * pre: Fraction object must be initialized
     *
     * post: The negated fraction is calculated
     *
     * Returns: A new Fraction object with opposite sign
     *
     * Called by: main
     * Calls: isNan, isInfinite
     ***********************************************************************/
    public CSCI271_Assignment2_MatthewScarborough negate(){
        if(this.isNan()) return new CSCI271_Assignment2_MatthewScarborough(0, 0);
        if(this.isInfinite()) return new CSCI271_Assignment2_MatthewScarborough(-this.Numerator, 0);
        return new CSCI271_Assignment2_MatthewScarborough(-this.Numerator, this.Denominator);
    }

    /************************pow***********************
     * Description: This function raises a fraction to a given integer power
     *
     * Parameters: exponent (an integer)
     *
     * pre: Fraction object must be initialized
     *
     * post: The fraction raised to the power is calculated
     *
     * Returns: A new Fraction object representing the result
     *
     * Called by: main
     * Calls: isNan, isInfinite
     ***********************************************************************/
    public CSCI271_Assignment2_MatthewScarborough pow(int exponent){
        if(this.isNan()) return new CSCI271_Assignment2_MatthewScarborough(0,0);
        if(this.isInfinite()){
            if(exponent > 0 ) return new CSCI271_Assignment2_MatthewScarborough(this.Numerator, 0);
            if(exponent < 0) return new CSCI271_Assignment2_MatthewScarborough(0,1);
            return new CSCI271_Assignment2_MatthewScarborough(1,1);
        }

        if(exponent == 0){
            if (this.Numerator == 0) return new CSCI271_Assignment2_MatthewScarborough(0, 0);
            return new CSCI271_Assignment2_MatthewScarborough(1,1);
        }
        long Newnum = this.Numerator;
        long Newden = this.Denominator;

        for (int i = 1; i < Math.abs(exponent); i ++){
            Newnum *= this.Numerator;
            Newden *= this.Denominator;
        }
        if (exponent < 0){
            long temp = Newnum;
            Newnum = Newden;
            Newden = temp;
        }
        return new CSCI271_Assignment2_MatthewScarborough(Newnum, Newden);
    }

    static public void main(String[] args){

        System.out.println("Test 1: Construcutor and toString()");

        CSCI271_Assignment2_MatthewScarborough F1 = new CSCI271_Assignment2_MatthewScarborough(6, -24);
        testing("6/-24", F1.toString(), "-1/4");

        CSCI271_Assignment2_MatthewScarborough F2 = new CSCI271_Assignment2_MatthewScarborough(0, 8);
        testing("0/8", F2.toString(), "0");

        CSCI271_Assignment2_MatthewScarborough F3 = new CSCI271_Assignment2_MatthewScarborough(23, 0);
        testing("23/0", F3.toString(), "Infinity");

        CSCI271_Assignment2_MatthewScarborough F4 = new CSCI271_Assignment2_MatthewScarborough(-15, 0);
        testing("-15/0", F4.toString(), "-Infinity"); 

        CSCI271_Assignment2_MatthewScarborough F5 = new CSCI271_Assignment2_MatthewScarborough(0, 0);
        testing("0/0", F5.toString(), "NaN");

        CSCI271_Assignment2_MatthewScarborough F6 = new CSCI271_Assignment2_MatthewScarborough(42);
        testing("42", F6.toString(), "42");

        CSCI271_Assignment2_MatthewScarborough F7 = new CSCI271_Assignment2_MatthewScarborough(-8, -32);
        testing("-8/-32", F7.toString(), "1/4");

        CSCI271_Assignment2_MatthewScarborough F8 = new CSCI271_Assignment2_MatthewScarborough(8, -6);
        testing("8/-6", F8.toString(), "-4/3");

        System.out.println("\n2 test 2 arithmetic operations");

        CSCI271_Assignment2_MatthewScarborough a = new CSCI271_Assignment2_MatthewScarborough(1, 3);
        CSCI271_Assignment2_MatthewScarborough b = new CSCI271_Assignment2_MatthewScarborough(2, 5);
        testing("1/3 + 2/5", a.add(b).toString(), "11/15");
        testing("1/3 - 2/5", a.subtract(b).toString(), "-1/15");
        testing("1/3 * 2/5", a.multiply(b).toString(), "2/15");
        testing("1/3 / 2/5", a.divide(b).toString(), "5/6");

        CSCI271_Assignment2_MatthewScarborough c = new CSCI271_Assignment2_MatthewScarborough(2, 3);
        testing("negate(2/3)", c.negate().toString(), "-2/3");
        testing("(2/3)^2", c.pow(2).toString(), "4/9");
        testing("(2/3)^-1", c.pow(-1).toString(), "3/2");
        testing("(2/3)^0", c.pow(0).toString(), "1");

        System.out.println("\n the complex Example from assignment");
        CSCI271_Assignment2_MatthewScarborough sixteen = new CSCI271_Assignment2_MatthewScarborough(16);
        CSCI271_Assignment2_MatthewScarborough threeFifths = new CSCI271_Assignment2_MatthewScarborough(3, 5);
        CSCI271_Assignment2_MatthewScarborough seven = new CSCI271_Assignment2_MatthewScarborough(7);
        CSCI271_Assignment2_MatthewScarborough sixSevenths = new CSCI271_Assignment2_MatthewScarborough(6, 7);

        CSCI271_Assignment2_MatthewScarborough denominator = threeFifths.add(seven);
        CSCI271_Assignment2_MatthewScarborough divison = sixteen.divide(denominator);
        CSCI271_Assignment2_MatthewScarborough result = divison.multiply(sixSevenths);
        testing("16/(3/5 +7) * 6/7", result.toString(), "240/133");

        System.out.println("\n test 2 part 2: more intesne testing with arithmetic operations");
        CSCI271_Assignment2_MatthewScarborough F9 = new CSCI271_Assignment2_MatthewScarborough(4, 9);
        CSCI271_Assignment2_MatthewScarborough F10 = new CSCI271_Assignment2_MatthewScarborough(2, 3);
        testing("4/9 + 2/3",  F9.add(F10).toString(), "14/9");
        testing("4/9 - 1/3", F9.subtract(F10).toString(), "-2/9");
        testing("4/9 * 2/3", F9.multiply(F10).toString(), "8/27");
        testing("4/9 / 2/3", F9.divide(F10).toString(), "2/3");
        CSCI271_Assignment2_MatthewScarborough F11 = new CSCI271_Assignment2_MatthewScarborough(4, 5);
        testing("pow(4/5, 30)", F11.pow(3).toString(), "64/125");

        System.out.println("\n Test 3: Operations with Infinity and NaN");

        CSCI271_Assignment2_MatthewScarborough inf = new CSCI271_Assignment2_MatthewScarborough(1, 0);
        CSCI271_Assignment2_MatthewScarborough negInf = new CSCI271_Assignment2_MatthewScarborough(-1, 0);
        CSCI271_Assignment2_MatthewScarborough nan = new CSCI271_Assignment2_MatthewScarborough(0, 0);
        CSCI271_Assignment2_MatthewScarborough zero = new CSCI271_Assignment2_MatthewScarborough(0, 1);
        CSCI271_Assignment2_MatthewScarborough one = new CSCI271_Assignment2_MatthewScarborough(1, 1);

        testing("Infinity + Infinity", inf.add(inf).toString(), "Infinity");
        testing("Infinity - Infinity", inf.subtract(inf).toString(), "NaN");
        testing("Infinity * 0", inf.multiply(zero).toString(), "NaN");
        testing("Infinity / 1", inf.divide(one).toString(), "Infinity");
        testing("NaN + 1", nan.add(one).toString(), "NaN");
        testing("NaN - Infinity", nan.subtract(inf).toString(), "NaN");
        testing("NaN * 0", nan.multiply(zero).toString(), "NaN");
        testing("NaN / -Infinity", nan.divide(negInf).toString(), "NaN");
        testing("Infinity ^ 2", inf.pow(2).toString(), "Infinity");
        testing("Infinity ^ -1", inf.pow(-1).toString(), "0");
        testing("0 ^ 0", zero.pow(0).toString(), "NaN");
        testing("negate(Infinity)", inf.negate().toString(), "-Infinity");
        testing("negate(NaN)", nan.negate().toString(), "NaN");

        System.out.println("All tests completed.");
    }

    private static void testing(String description, String actual, String expected){
        boolean passed = actual.equals(expected);
        System.out.println(description);
        System.out.println(" Expected: " + expected);
        System.out.println("   Actual: " + actual);
        System.out.println("   Result: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
}