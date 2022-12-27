package workshop;


public class Fizzbuzz {
    public String fizzbuzzOrNumber(int number){

        if(isModulusZero(number, 15)){
            return "FizzBuzz";
        } else if (isModulusZero(number, 3)) {
            return "Fizz";
        } else if (isModulusZero(number, 5)) {
            return "Buzz";
        }
        else
            return String.valueOf(number);
    }
    
    public boolean isModulusZero(int number, int divisor ){
        if(number%divisor == 0)
            return true;
        else return false;
    }
}


