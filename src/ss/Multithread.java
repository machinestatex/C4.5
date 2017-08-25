package ss;

import java.util.*;

import static ss.MainClass.createMapValues;
import static ss.MainClass.getResult;


public class Multithread extends Thread {
    public Multithread(){}

    public double getGain(List<String> sutun, List<String> sinif, int ayirici, double bilgi) throws InterruptedException {
        // We created a synchronized code block
        double gain=0;
        synchronized (this) {
            start();
            Map<String, Map<String, Integer>> map = new LinkedHashMap<>();
            for (int i = 0; i < sutun.size(); i++) {
                Map<String, Integer> nestedMap = new LinkedHashMap<>();
                int counter = 0;
                if (Integer.parseInt(sutun.get(i)) < ayirici) {
                    if (map.get("kucuk") != null) {
                        nestedMap = map.get("kucuk");
                        if (nestedMap.get(sinif.get(i)) != null) {
                            counter = nestedMap.get(sinif.get(i));
                        }
                    }
                    counter++;
                    nestedMap.put(sinif.get(i), counter);
                    map.put("kucuk", nestedMap);
                } else if (Integer.parseInt(sutun.get(i)) >= ayirici) {
                    if (map.get("buyuk") != null) {
                        nestedMap = map.get("buyuk");
                        if (nestedMap.get(sinif.get(i)) != null) {
                            counter = nestedMap.get(sinif.get(i));
                        }
                    }
                    counter++;
                    nestedMap.put(sinif.get(i), counter);
                    map.put("buyuk", nestedMap);
                }
            }
            double result = getResult(map, sutun.size());
            gain = bilgi - result;


        }
        return gain;
        //   this.sleep(150);

    }

    public String findRoot(Map<String,List<String>> sutunMap,double knowledge) throws InterruptedException {
        // We created a synchronized code block
        Map<Double,String> gains=new HashMap<>();
        synchronized (this) {
            start();

            for(Map.Entry<String,List<String>> entry: sutunMap.entrySet()){
                if(!entry.getKey().equals("index")){
                    double result=0;
                    Map<String, Map<String, Integer>> mapValues = new LinkedHashMap<>();
                    mapValues=createMapValues(entry.getValue(),sutunMap.get("index"));
                    result=getResult(mapValues,entry.getValue().size());
                    gains.put((knowledge-result),entry.getKey());
                }

            }
        }
        TreeMap<Double,String> sort=new TreeMap<Double,String>(gains);
        return gains.get(sort.lastKey());
        //   this.sleep(150);

    }

}
