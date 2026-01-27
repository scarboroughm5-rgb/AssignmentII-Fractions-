

public class CSCI271_Assignment2_MatthewScarborough {
        private long Numerator; 
        private long Denominator;

        public CSCI271_Assignment2_MatthewScarborough(long Num, long Den){
            // handles special cases
            
            // case 1: both numerator and denominator are 0
            if (Num == 0 && Den == 0){
                System.out.println("Undefined");

                // case 2: denominator is 0 and numerator is negative
            } else if(Den == 0 && Num < 0){
                System.out.println("-Infinity");
            }
            // case 3: denominator is o and numerator is postive
             else if (Den == 0 && Num > 0) {
                System.out.println("+Infinity");
            } else {
                this.Numerator = Num;
                this.Denominator = Den;
            }

            normalize();
        }

        CSCI271_Assignment2_MatthewScarborough(long Num){
            this.Numerator = Num;
            this.Denominator = 1;
            
        }

        public long getNumerator(){
            return this.Numerator;
        }

        public long getDenominator(){
            return this.Denominator;
        }

        public String toString(){
            return this.Numerator + "/" + this.Denominator;
        }


    }