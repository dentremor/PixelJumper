package de.se2projekt.main;

import java.util.*;
import java.util.Map.Entry;

public class LevelHighscore {

    public static void main(String[] args) {

        HashMap<String, Integer> levelScores = new HashMap<>();
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<String> list = new ArrayList<>();

        levelScores.put("Name1", 100);
        levelScores.put("Name2", 222);
        levelScores.put("Name3", 133);
        levelScores.put("Name4", 123);
        levelScores.put("Name5", 111);


        ArrayList<Integer> scores = new ArrayList<>(levelScores.values());

        Iterator<Integer> pre = scores.iterator();
        for (Map.Entry s : levelScores.entrySet()) {
            System.out.println(s.getKey() + " : " + pre.next());
        }
        System.out.println();

        Collections.sort(scores);
        Collections.reverse(scores);
        Iterator<Integer> scoreIt = scores.iterator();
        for (Map.Entry s : levelScores.entrySet()) {
            System.out.println(s.getKey() + " : " + scoreIt.next());
        }
    }
}

