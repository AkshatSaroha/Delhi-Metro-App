public class FareCalculator {
    // Base fare for minimum distance (up to 2 km)
    private static final int BASE_FARE = 10;
    
    // Additional fare per km after base distance
    private static final int FARE_PER_KM = 2;
    
    // Senior citizen discount (0.7 times normal fare)
    private static final double SENIOR_DISCOUNT = 0.7;
    
    // Student discount (0.8 times normal fare)
    private static final double STUDENT_DISCOUNT = 0.8;
    
    public static class FareDetails {
        double baseFare;
        double finalFare;
        double discount;
        String fareBreakdown;
        
        public FareDetails(double baseFare, double finalFare, double discount, String fareBreakdown) {
            this.baseFare = baseFare;
            this.finalFare = finalFare;
            this.discount = discount;
            this.fareBreakdown = fareBreakdown;
        }
    }
    
    public static FareDetails calculateFare(double distance, String passengerType) {
        StringBuilder breakdown = new StringBuilder();
        
        // Calculate base fare
        double baseFare = BASE_FARE;
        breakdown.append("Base fare (up to 2 km): ₹" + BASE_FARE + "\n");
        
        // Add distance-based fare for distances over 2 km
        if (distance > 2) {
            double additionalFare = (distance - 2) * FARE_PER_KM;
            baseFare += additionalFare;
            breakdown.append("Additional distance charge: ₹" + 
                           String.format("%.2f", additionalFare) + 
                           " (" + String.format("%.1f", distance-2) + " km × ₹" + 
                           FARE_PER_KM + ")\n");
        }
        
        // Apply discounts based on passenger type
        double finalFare = baseFare;
        double discount = 0;
        
        switch (passengerType.toLowerCase()) {
            case "senior":
                discount = baseFare * (1 - SENIOR_DISCOUNT);
                finalFare = baseFare * SENIOR_DISCOUNT;
                breakdown.append("Senior citizen discount (30%): -₹" + 
                               String.format("%.2f", discount) + "\n");
                break;
                
            case "student":
                discount = baseFare * (1 - STUDENT_DISCOUNT);
                finalFare = baseFare * STUDENT_DISCOUNT;
                breakdown.append("Student discount (20%): -₹" + 
                               String.format("%.2f", discount) + "\n");
                break;
        }
        
        breakdown.append("Final fare: ₹" + String.format("%.2f", finalFare));
        
        return new FareDetails(baseFare, finalFare, discount, breakdown.toString());
    }
}