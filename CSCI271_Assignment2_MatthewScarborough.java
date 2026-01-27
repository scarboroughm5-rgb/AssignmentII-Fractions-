

public class CSCI271_Assignment2_MatthewScarborough {
        private long Numerator; 
        private long Denominator;

        public CSCI271_Assignment2_MatthewScarborough(long Num, long Den){
            // handles special cases
            
            // case 1: both numerator and denominator are 0
            if(Num == 0 && Den == 0){
                this.Numerator = 0;
                this.Denominator = 0;
                return;
            }

            // case 2: denominator is 0
            if (Den == 0){
                // store signs for positive and negative infinity
                this.Numerator = (Num > 0) ? 1 : -1;
                this.Denominator = 0;
                return;
            }

            // normal case 
            this.Numerator = Num;
            this.Denominator = Den;
            normalize();
        }
        // constructor for whole numbers
        public CSCI271_Assignment2_MatthewScarborough(long Num){
            this.Numerator = Num;
            this.Denominator = 1;
        }

    private static long gcd(long a, long b){

        a = Math.abs(a);
        b = Math.abs(b);
        
        if (a == 0 && b == 0){
            return 1;
        }

        if (a == 0){
            return b;
        }

        if(b == 0){
            return a;
        }
        while (b != 0){
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    private void normalize(){

        if(this.Denominator == 0){
            return;
        }

        if(this.Denominator < 0){
            this.Numerator = -this.Numerator;
            this.Denominator = -this.Denominator;
        }

        long divisor = gcd(this.Numerator, this.Denominator);
        if(divisor != 0){
            this.Numerator /= divisor;
            this.Denominator /= divisor;
        }

        if(this.Numerator == 0 && this.Denominator != 0){
            this.Denominator = 1;
        }
    }

    public boolean isNan(){
        return (this.Numerator == 0 && this.Denominator == 0);
    }

    public boolean isInfinite(){
        return (this.Denominator == 0 && this.Numerator != 0);
    }

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

    public CSCI271_Assignment2_MatthewScarborough negate(){
        if(this.isNan()) return new CSCI271_Assignment2_MatthewScarborough(0, 0);
        if(this.isInfinite()) return new CSCI271_Assignment2_MatthewScarborough(-this.Numerator, 0);
        return new CSCI271_Assignment2_MatthewScarborough(-this.Numerator, this.Denominator);
    }

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
        
        if(exponent < 0){
            exponent = -exponent;
            long newNum = (long) Math.pow(this.Denominator, exponent);
            long newDen = (long) Math.pow(this.Numerator, exponent);
            return new CSCI271_Assignment2_MatthewScarborough(newNum, newDen);
        }

        long newNum = (long) Math.pow(this.Numerator, exponent);
        long newDen = (long) Math.pow(this.Denominator, exponent);
        return new CSCI271_Assignment2_MatthewScarborough(newNum, newDen);
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