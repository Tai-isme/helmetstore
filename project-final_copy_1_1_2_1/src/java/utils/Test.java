package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Test {

    public static void main(String[] args) {
//        System.out.println("select * \n" +
//"from sanpham\n" +
//"where kieumau = 'fullface' AND mausac = 'Pink' AND kichco = 'S' And giaban between 200000 and 1000000");
//        String kieumau = "";
//        String mausac = "Pink";
//        String kichco = "S";
//        String giaban = "200000 1000000";
//        int vitrigiua = giaban.indexOf(" ");
//        System.out.println(vitrigiua);
//        String from = giaban.substring(0, 6);
//        String to = giaban.substring(7);
//        System.out.println("from "+from);
//        System.out.println("to "+to);
//        
//        
//        String url = " select * \n"
//                + "from sanpham \n";
//        if(kieumau.length()>0 || mausac.length()>0 || kichco.length()>0 || giaban.length()>0 ){
//            url = url + "where ";
//        }
//        
//        if(kieumau.length()>0){
//            url = url +" kieumau = fullface ";
//        }
//        
//        if(mausac.length()>0){
//            url = url +" AND mausac = 'Pink' ";
//        }
//        
//        if(kichco.length()>0){
//            url = url +" AND kichco = 'S' ";
//        }
//        
//        if(giaban.length()>0){
//            url = url +" AND giaban between " + from + " and " + to  ;
//        }
//        
//        System.out.println("vị trí where   is: " +url.indexOf("where ")); 
//        
//        System.out.println("url = "+url);
//        System.out.println("url = "+url.indexOf("AND"));
//        

        String sql = "select * \n"
                + "from sanpham\n"
                + "where (kieumau = ? or is null) AND mausac = ? or is null) AND (kickco = ? or is null) AND (giaban between ? and ? ) or is null) ";
        
        
        
        
    }
}
