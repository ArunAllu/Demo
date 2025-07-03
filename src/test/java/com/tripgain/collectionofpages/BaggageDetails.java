package com.tripgain.collectionofpages;

public class BaggageDetails {

    String cabinBaggage;
    String checkinBaggage;

    public BaggageDetails(String cabinBaggage, String checkinBaggage) {
        this.cabinBaggage = cabinBaggage;
        this.checkinBaggage = checkinBaggage;
    }

    @Override
    public String toString() {
        return "Cabin: " + cabinBaggage + ", Check-in: " + checkinBaggage;
    }

    //Method to Store and get Baggage Details.
    public static BaggageDetails getBaggageDetailsManual(String flightName, String fareName) {

        switch (flightName) {
            case "Indigo":
                if (fareName.equals("Saver")) {
                    return new BaggageDetails("7 kg", "15 kg");
                } else if (fareName.equals("SME")) {
                    return new BaggageDetails("7 kg", "15 kg");
                } else if (fareName.equals("Flexi")) {
                    return new BaggageDetails("7 kg", "15 kg");
                }
                else if (fareName.equals("Super6E")) {
                    return new BaggageDetails("7 kg", "20 kg");
                }
                else if (fareName.equals("Tactical")) {
                    return new BaggageDetails("7 kg", "15 kg");
                }
                else if (fareName.equals("Stretch")) {
                    return new BaggageDetails("12 kg", "30 kg");
                }
                else if (fareName.equals("StretchPlus")) {
                    return new BaggageDetails("12 kg", "40 kg");
                }else {
                    System.out.println("Invalid fare type for Indego.");
                }
                break;

            case "Akasa Air":
                if (fareName.equals("Corporate")) {
                    return new BaggageDetails("7 kg", "15 kg");
                } else if (fareName.equals("Coupon")) {
                    return new BaggageDetails("7 kg", "15 kg");
                } else if (fareName.equals("SpecialFare")) {
                    return new BaggageDetails("7 kg", "15 kg");
                }
                else if (fareName.equals("Saver")) {
                    return new BaggageDetails("7 kg", "15 kg");
                }
                else if (fareName.equals("CorporateSelect")) {
                    return new BaggageDetails("7 kg", "15 kg");
                }
                else if (fareName.equals("SME.CrpCon")) {
                    return new BaggageDetails("7 kg", "15 kg");
                }
                else if (fareName.equals("Flexi")) {
                    return new BaggageDetails("7 kg", "15 kg");
                }
                else {
                    System.out.println("Invalid fare type for Akasha.");
                }
                break;

            default:
                System.out.println("Flight not recognized.");
                break;
        }
        return null;
    }
}
