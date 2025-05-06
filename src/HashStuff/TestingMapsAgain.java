package HashStuff;
import java.util.ArrayList;
import java.util.HashMap;
//hash maps unordered
//tree maps alphabetize
//price is runtime
public class TestingMapsAgain {
    public static void main(String[] args){
        HashMap<String, Integer> hm = new HashMap<>();

        hm.put("Tom", 1);
        hm.put("Brandon", 2);
        hm.put("Lisa", 3);
        hm.put("Brandon", 2);
        hm.put("Jerry", 3);

        HashMap<String, ArrayList<String>> shm = new HashMap();

        System.out.println(hm.size());
        System.out.println("What is Lisa?" +hm.get("Lisa"));

        for(String singleValue: hm.keySet()){
            System.out.print((singleValue));
        }
    }
}
