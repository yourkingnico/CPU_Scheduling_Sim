package edu.bsu.cs376;

import java.util.*;

public class SJFLayout extends FCFSLayout{

    @Override
    public void simulate(){
        Map<String, Integer> hm = new HashMap<>();
        String job;
        String output = "";
        for(int i = 0; i < times.size(); i++){
            job = "Job "+ (i+1) ;
            hm.put(job, Integer.valueOf(times.get(i).getText()));
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(hm.size());
        entries.addAll(hm.entrySet());
        Collections.sort(entries, (entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()));
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String , Integer> entry : entries)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String,Integer> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            output += "  " + key + ", " + value + ", ";
        }
        showOutput(output);
    }
}