package com.company.fruitShop;
class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}
class InvalidcalculateException extends Exception {
    public InvalidcalculateException(String message) {
        super(message);
    }
}

public class FruitShop {
    private static final double APPLE_PRICE = 8.0;
    private static final double STRAWBERRY_PRICE = 13.0;
    private static final double MANGO_PRICE = 20.0;
    private static final double STRAWBERRY_DISCOUNT = 0.8;
    private static final double DISCOUNT_THRESHOLD = 100.0;
    private static final double DISCOUNT_AMOUNT = 10.0;

    public static void checkInput(int... weights) throws InvalidInputException {
        for (int weight : weights) {
            if (weight < 0) {
                throw new InvalidInputException("Fruit weight cannot be negative.");
            }
        }
    }

    public static double calculateTotalPriceForA(int appleWeight, int strawberryWeight) throws InvalidInputException,InvalidcalculateException {
        checkInput(appleWeight,strawberryWeight);
        double totalPrice = appleWeight * APPLE_PRICE + strawberryWeight * STRAWBERRY_PRICE;
        if (totalPrice-appleWeight * APPLE_PRICE!=strawberryWeight * STRAWBERRY_PRICE){
            throw new InvalidcalculateException("计算结果错误，请检查溢出情况");
        }
        return totalPrice;
    }
    public static double calculateTotalPriceForB(int appleWeight, int strawberryWeight, int mangoWeight) throws InvalidInputException,InvalidcalculateException {
        checkInput(appleWeight,strawberryWeight,mangoWeight);
        double totalPrice = appleWeight * APPLE_PRICE + strawberryWeight * STRAWBERRY_PRICE + mangoWeight * MANGO_PRICE;
        if (totalPrice-appleWeight * APPLE_PRICE - strawberryWeight * STRAWBERRY_PRICE !=mangoWeight * MANGO_PRICE){
            throw new InvalidcalculateException("计算结果错误，请检查溢出情况");
        }
        return totalPrice;
    }

    public static double calculateTotalPriceForC(int appleWeight, int strawberryWeight, int mangoWeight) throws InvalidInputException,InvalidcalculateException {
        checkInput(appleWeight,strawberryWeight,mangoWeight);
        double strawberryPrice = STRAWBERRY_PRICE * STRAWBERRY_DISCOUNT;
        double totalPrice = appleWeight * APPLE_PRICE + strawberryWeight * strawberryPrice + mangoWeight * MANGO_PRICE;
        //验证结果
        if (totalPrice-appleWeight * APPLE_PRICE - strawberryWeight * strawberryPrice !=mangoWeight * MANGO_PRICE){
            throw new InvalidcalculateException("计算结果错误，请检查溢出情况");
        }
        return totalPrice;
    }

    public static double calculateTotalPriceForD(int appleWeight, int strawberryWeight, int mangoWeight) throws InvalidInputException,InvalidcalculateException {
        checkInput(appleWeight,strawberryWeight,mangoWeight);
        double totalPrice = appleWeight * APPLE_PRICE + strawberryWeight * STRAWBERRY_PRICE + mangoWeight * MANGO_PRICE;
        if (totalPrice >= DISCOUNT_THRESHOLD) {
            totalPrice -= DISCOUNT_AMOUNT;
        }
        //验证结果
        if(totalPrice + DISCOUNT_AMOUNT >= DISCOUNT_THRESHOLD){
            if (totalPrice+DISCOUNT_AMOUNT  -appleWeight * APPLE_PRICE - strawberryWeight * STRAWBERRY_PRICE !=mangoWeight * MANGO_PRICE){
                throw new InvalidcalculateException("计算结果错误，请检查溢出情况");
            }
        }else{
            if (totalPrice -appleWeight * APPLE_PRICE - strawberryWeight * STRAWBERRY_PRICE !=mangoWeight * MANGO_PRICE){
                throw new InvalidcalculateException("计算结果错误，请检查溢出情况");
            }
        }
        return totalPrice;
    }


    public static void main(String[] args) {
        try {
            // 顾客A购买情况
            int appleWeightA = 5;
            int strawberryWeightA = 3;
            double totalPriceA = calculateTotalPriceForA(appleWeightA, strawberryWeightA);
            System.out.println("Total price for customer A: " + totalPriceA);

            // 顾客B购买情况
            int appleWeightB = 2;
            int strawberryWeightB = 4;
            int mangoWeightB = 1;
            double totalPriceB = calculateTotalPriceForB(appleWeightB, strawberryWeightB, mangoWeightB);
            System.out.println("Total price for customer B: " + totalPriceB);

            // 顾客C购买情况
            int appleWeightC = 3;
            int strawberryWeightC = 2;
            int mangoWeightC = 1;
            double totalPriceC = calculateTotalPriceForC(appleWeightC, strawberryWeightC, mangoWeightC);
            System.out.println("Total price for customer C: " + totalPriceC);

            // 顾客D购买情况
            int appleWeightD = 5;
            int strawberryWeightD = 6;
            int mangoWeightD = 3;
            double totalPriceD = calculateTotalPriceForD(appleWeightD, strawberryWeightD, mangoWeightD);
            System.out.println("Total price for customer D: " + totalPriceD);
        } catch (InvalidInputException | InvalidcalculateException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
