package src.ru.croc.tasks.task16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Taxi {
    private String identificator;
    private String fullName;
    private Double latitude;
    private Double longitude;
    private String comfortClass;
    private String[] specialRequests;

    private static ArrayList<Taxi> allTaxi = new ArrayList<>();


    @Override
    public String toString() {
        return "Taxi{" +
                "identificator='" + identificator + '\'' +
                ", fullName='" + fullName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", comfortClass='" + comfortClass + '\'' +
                ", specialRequests=" + Arrays.toString(specialRequests) +
                '}';
    }

    public Taxi(String fullName, Double latitude, Double longitude, String comfortClass, String[] specialRequests) {
        this.fullName = fullName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.comfortClass = comfortClass;
        this.specialRequests = specialRequests;
        this.updateIdentificator();
        allTaxi.add(this);
    }

    private void updateIdentificator() {
        this.identificator = "T-" + this.fullName.replace(" ", "") + "-"
                + String.valueOf((this.fullName + Arrays.toString(specialRequests)).hashCode());
    }

    public static void findNearestForThisPerson(double latitude, double longitude, String comfortClass, ArrayList<String> specialRequests) {
        ArrayList<Taxi> sortedTaxi = new ArrayList<>(allTaxi);
        sortedTaxi.sort(((o1, o2) -> Double.compare(o1.calculateDistanceTo(latitude, longitude), o2.calculateDistanceTo(latitude, longitude))));

        for (Taxi taxi : sortedTaxi) {
            if (taxi.comfortClass.equals(comfortClass)) {
                if (specialRequests.size() == 1 && specialRequests.get(0).isEmpty()) {
                    System.out.println(taxi.identificator);
                    return;
                } else {
                    int count = 0;
                    for (String a : specialRequests) {
                        for (String b : taxi.specialRequests) {
                            if (a.equals(b)) {
                                count++;
                                break;
                            }
                        }
                    }
                    if (count == specialRequests.size()) {
                        System.out.println(taxi.identificator);
                        return;
                    }
                }
            }
        }
    }

    private double calculateDistanceTo(double latitude, double longitude) {
        return Math.sqrt(Math.pow((this.latitude - latitude), 2) + Math.pow((this.longitude - longitude), 2));
    }
}
