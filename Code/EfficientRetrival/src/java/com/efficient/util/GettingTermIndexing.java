/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efficient.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Datapoint
 */
public class GettingTermIndexing {
 public static Map getDocumentIndexes(String data){
    Map map = new TreeMap();
        String[] arr = data.split(" ");
        for(String s1 : arr){
          if(map.get(s1)== null){
              map.put(s1, ""+1);
          }else{
           String oval = (String) map.get(s1);
           int ioval = Integer.parseInt(oval);
           ioval++;
           String s2 = ""+ioval;
           map.put(s1, s2);
          }
        }
        return map;
    }
    public static void main(String[] args) {
        String s = "to to be can not contain to to be again";
        Map m = new HashMap();
        String[] arr = s.split(" ");
        for(String s1 : arr){
          if(m.get(s1)== null){
              m.put(s1, ""+1);
          }else{
           String oval = (String) m.get(s1);
           int ioval = Integer.parseInt(oval);
           ioval++;
           String s2 = ""+ioval;
           m.put(s1, s2);
          }
        }
        System.out.println(m);
    }   
}
