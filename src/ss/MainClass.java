package ss;

import java.io.*;
import java.util.*;


public class MainClass {
    MainClass(){}
    @SuppressWarnings("resource")
    public Map<String, String> main() throws FileNotFoundException, IOException {
        File file = new File("haberman.txt");

        List<String> etiket = new ArrayList<String>();

        List<String> birinci = new ArrayList<String>();

        List<String> ikinci = new ArrayList<String>();

        List<String> ucuncu = new ArrayList<String>();
        List<String> index = new ArrayList<String>();

        BufferedReader br = new BufferedReader(new FileReader("haberman.txt"));
        int boyut = 0;
        while (br.readLine() != null) {
            boyut++;
        }
        System.out.print("boyutumuzzzzz:");
        System.out.println(boyut);


        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        String satir = reader.readLine();

        ArrayList<String> x = new ArrayList<String>();


        int altboyut = 0;

        while (altboyut != boyut) {


            String[] kelime = null;
            kelime = satir.split(","); 


            for (int i = 0; i < kelime.length; i++) {
                x.add(kelime[i]);
            }
            altboyut++;
            satir = reader.readLine();
           

        }
      
        int nn = 0;
        for (int g = 0; g < 4; g++) {
            etiket = new ArrayList<>();
            for (int k = g; k < x.size(); k = k + 4) {
                etiket.add(nn, x.get(k));
                nn++;
            }
            nn = 0;
            if (g == 0) {
                for (int jj = 0; jj < etiket.size(); jj++)
                    birinci.add(jj, etiket.get(jj));
            }
            if (g == 1) {
                for (int jj = 0; jj < etiket.size(); jj++)
                    ikinci.add(jj, etiket.get(jj));
            }
            if (g == 2) {
                for (int jj = 0; jj < etiket.size(); jj++)
                    ucuncu.add(jj, etiket.get(jj));
            }
            if (g == 3) {
                for (int jj = 0; jj < etiket.size(); jj++)
                    index.add(jj, etiket.get(jj));
            }
        }
        Map<String, Integer> etiketMap = new LinkedHashMap<>();
        etiketMap = createMap(index);
        int etiketPayda = index.size();
        double deger = 0;
        double bilgi = 0;
        int say;
        for (Map.Entry<String, Integer> value : etiketMap.entrySet()) {
            deger = ((double) value.getValue() / etiketPayda);
            double logaritma = ((double) Math.log(deger)) / Math.log(2);
            double sonuc = (-1) * deger * logaritma;
            bilgi = bilgi + sonuc;
        }
        Map<String,List<String>> sutunMap=new HashMap<>();
        sutunMap.put("birinci",birinci);
        sutunMap.put("ikinci",ikinci);
        sutunMap.put("ucuncu",ucuncu);
        sutunMap.put("index",index);

        List<Integer> gain1list=new ArrayList<>();
        gain1list.add(50);
        gain1list.add(60);
        gain1list.add(70);
        List<Integer> gain2list=new ArrayList<>();
        gain2list.add(62);
        gain2list.add(63);
        gain2list.add(64);
        List<Integer> gain3list=new ArrayList<>();
        gain3list.add(5);
        gain3list.add(10);
        gain3list.add(19);
        Map<String,List<Integer>> compareMap=new HashMap<>();
        compareMap.put("birinci",gain1list);
        compareMap.put("ikinci",gain2list);
        compareMap.put("ucuncu",gain3list);
        String key="";
        Integer threshold=0;

        key=findRoot(sutunMap,bilgi);
        threshold=findThreshold(sutunMap.get(key),sutunMap.get("index"),compareMap.get(key),bilgi);

        Map<String,List<String>> newMap=createNewMapMin(key,threshold,sutunMap);
        String secondLeftKey= null;
        try {
            secondLeftKey = new Multithread().findRoot(newMap,bilgi);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer secondLeftThreshold=findThreshold(newMap.get(secondLeftKey),newMap.get("index"),compareMap.get(secondLeftKey),bilgi);
        Map<String,List<String>> newMap2=createNewMapMax(key,threshold,sutunMap);
        String secondRightKey= null;
        try {
            secondRightKey = new Multithread().findRoot(newMap2,bilgi);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer secondRightThreshold=findThreshold(newMap2.get(secondRightKey),newMap2.get("index"),compareMap.get(secondRightKey),bilgi);
        Map<String,List<String>> thirdLeft1Map=createNewMapMin(secondLeftKey,threshold,sutunMap);
        Map<String,List<String>> thirdLeft2Map=createNewMapMin(secondRightKey,threshold,sutunMap);
        Map<String,List<String>> thirdRight1Map=createNewMapMin(secondLeftKey,threshold,sutunMap);
        Map<String,List<String>> thirdRight2Map=createNewMapMin(secondRightKey,threshold,sutunMap);
        String thirdLeft1Key=null;
        try {
            thirdLeft1Key = new Multithread().findRoot(thirdLeft1Map,bilgi);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String thirdRight1Key=null;
        try {
            thirdRight1Key = new Multithread().findRoot(thirdRight1Map,bilgi);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String thirdLeft2Key=null;
        try {
            thirdLeft2Key = new Multithread().findRoot(thirdLeft2Map,bilgi);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String thirdRight2Key=null;
        try {
            thirdRight2Key = new Multithread().findRoot(thirdRight2Map,bilgi);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String,String> returnedMap=new HashMap<>();
        returnedMap.put("key",key);
        returnedMap.put("secondLeftKey",secondLeftKey);
        returnedMap.put("secondRightKey",secondRightKey);
        returnedMap.put("thirdLeft1Key",thirdLeft1Key);
        returnedMap.put("thirdRight1Key",thirdRight1Key);
        returnedMap.put("thirdLeft2Key",thirdLeft2Key);
        returnedMap.put("thirdRight2Key",thirdRight2Key);
        return returnedMap;

    }
    public static String findRoot(Map<String,List<String>> sutunMap,double knowledge){
        Map<Double,String> gains=new HashMap<>();
        for(Map.Entry<String,List<String>> entry: sutunMap.entrySet()){
            if(!entry.getKey().equals("index")){
                double result=0;
                Map<String, Map<String, Integer>> mapValues = new LinkedHashMap<>();
                mapValues=createMapValues(entry.getValue(),sutunMap.get("index"));
                result=getResult(mapValues,entry.getValue().size());
                gains.put((knowledge-result),entry.getKey());
            }

        }
        TreeMap<Double,String> sort=new TreeMap<Double,String>(gains);
        return gains.get(sort.lastKey());
    }

    public static Integer findThreshold(List<String> sutun,List<String> sinif,List<Integer> kas,double bilgi){
        Map<Double,Integer> gains=new HashMap<>();
        for(Integer ayirici:kas){
            double gain1=0;
            try {
                gain1 = new Multithread().getGain(sutun, sinif, ayirici, bilgi);
                gains.put(gain1,ayirici);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        TreeMap<Double,Integer> sort=new TreeMap<Double,Integer>(gains);
        return gains.get(sort.lastKey());
    }

    public static Map<String,List<String>> createNewMapMin(String key,Integer threshold,Map<String,List<String>> sutunMap) {
        Map<String,List<String>> newMap=new HashMap<>();
        for(Map.Entry<String,List<String>> news:sutunMap.entrySet()){
            List<String> newValues=new ArrayList<>();
            if(!news.getKey().equals(key) ){
                for(int i=0;i<sutunMap.get(key).size();i++){
                    if(Integer.parseInt(sutunMap.get(key).get(i))<threshold){
                        newValues.add(news.getValue().get(i));
                    }
                }
                newMap.put(news.getKey(),newValues);
            }
        }
        return newMap;
    }

    public static Map<String,List<String>> createNewMapMax(String key,Integer threshold,Map<String,List<String>> sutunMap){
        Map<String,List<String>> newMap=new HashMap<>();
        for(Map.Entry<String,List<String>> news:sutunMap.entrySet()){
            List<String> newValues=new ArrayList<>();
            if(!news.getKey().equals(key) ){
                for(int i=0;i<sutunMap.get(key).size();i++){
                    if(Integer.parseInt(sutunMap.get(key).get(i))>=threshold){
                        newValues.add(news.getValue().get(i));
                    }
                }
                newMap.put(news.getKey(),newValues);
            }
        }
        return newMap;

    }

    public static Map<String, Integer> createMap(List<String> list) {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String sutun1 : list) {
            int counter1 = 0;
            if (map.get(sutun1) != null) {
                counter1 = map.get(sutun1);
            }
            counter1++;
            map.put(sutun1, counter1);
        }
        return map;
    }

    public static Map<String, Map<String, Integer>> createMapValues(List<String> sutun, List<String> etiket) {
        //  Map<String,List<String>> etiketValues=new LinkedHashMap<>();
        Map<String, Map<String, Integer>> valuesNestedMap = new LinkedHashMap<>();
        for (int sutun1 = 0; sutun1 < sutun.size(); sutun1++) {
            // List<String> values=new ArrayList<>();
            Map<String, Integer> nestedMap = new LinkedHashMap<>();
            int counter = 0;
            if (valuesNestedMap.get(sutun.get(sutun1)) != null) {
                nestedMap = valuesNestedMap.get(sutun.get(sutun1));
                if (nestedMap.get(etiket.get(sutun1)) != null) {
                    counter = nestedMap.get(etiket.get(sutun1));
                }
                // values = etiketValues.get(sutun.get(sutun1));
            }
            counter++;
            nestedMap.put(etiket.get(sutun1), counter);
            valuesNestedMap.put(sutun.get(sutun1), nestedMap);
            // values.add(etiket.get(sutun1));
            // etiketValues.put(sutun.get(sutun1),values);
        }
        return valuesNestedMap;
    }

    public static double getResult(Map<String, Map<String, Integer>> sutunMap, Integer size) {
        double result = 0;
        for (Map.Entry<String, Map<String, Integer>> entry : sutunMap.entrySet()) {
            int total = 0;
            for (Map.Entry<String, Integer> nestedEntry : entry.getValue().entrySet()) {
                total = total + nestedEntry.getValue();
            }
            double nested_total = 0;
            for (Map.Entry<String, Integer> nested : entry.getValue().entrySet()) {
                nested_total = nested_total + (((double) nested.getValue() / total) * (Math.log((double) nested.getValue() / total) / Math.log(2)));
            }
            nested_total = -nested_total;
            result = result + (((double) total / size) * nested_total);
        }
        return result;

    }
}
