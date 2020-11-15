package pt.joja;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Greed {

    public static void main(String[] args) {
        Map<String, Set<String>> radioStations = new HashMap<>();
        Set<String> coverAreas = new HashSet<>();
        radioStations.put("K1", coverAreas);
        coverAreas.add("北京");
        coverAreas.add("上海");
        coverAreas.add("天津");
        coverAreas = new HashSet<>();
        radioStations.put("K2", coverAreas);
        coverAreas.add("广州");
        coverAreas.add("北京");
        coverAreas.add("深圳");
        coverAreas = new HashSet<>();
        radioStations.put("K3", coverAreas);
        coverAreas.add("成都");
        coverAreas.add("上海");
        coverAreas.add("杭州");
        coverAreas = new HashSet<>();
        radioStations.put("K4", coverAreas);
        coverAreas.add("上海");
        coverAreas.add("天津");
        coverAreas = new HashSet<>();
        radioStations.put("K5", coverAreas);
        coverAreas.add("杭州");
        coverAreas.add("大连");

        Set<String> targetAreas = new HashSet<>();
        targetAreas.add("北京");
        targetAreas.add("上海");
        targetAreas.add("天津");
        targetAreas.add("广州");
        targetAreas.add("深圳");
        targetAreas.add("成都");
        targetAreas.add("杭州");
        targetAreas.add("大连");

        greed(targetAreas, radioStations);
    }

    public static void greed(Set<String> targetAreas, Map<String, Set<String>> radioStations) {
        if (targetAreas.isEmpty()) {
            System.out.println("Selection over.");
            return;
        }

        Set<String> coveredAreas = new HashSet<>();
        String currStation = "";
        for (String station : radioStations.keySet()) {
            int i = 0;
            for (String coverdArea : radioStations.get(station)) {
                if (targetAreas.contains(coverdArea)) {
                    i++;
                }
            }
            if (i > coveredAreas.size()) {
                currStation = station;
                coveredAreas = radioStations.get(station);
            }
        }

        if (currStation == "") {
            System.out.println("No solution.");
            return;
        }

        System.out.println("Chosen: " + currStation + " - " + coveredAreas);
        targetAreas.removeAll(coveredAreas);
        radioStations.remove(currStation);
        greed(targetAreas, radioStations);
    }
}
