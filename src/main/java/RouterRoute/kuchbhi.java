package RouterRoute;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class kuchbhi {
  public static void main(String[] args) {
    HashMap<Integer,String> hashMap = new HashMap<>();

    hashMap.put(1,"Pooja");
    hashMap.put(2,"Pooja1");
    hashMap.put(3,"Pooja2");
    hashMap.put(4,"Pooja3");
    hashMap.put(5,"Pooja4");


    Iterator<Integer> keyset = hashMap.keySet().iterator();

    while (keyset.hasNext()){
      System.out.println("index " + keyset.next());
      hashMap.put(10, "smit");
    }

    System.out.println(hashMap);

  }
}
