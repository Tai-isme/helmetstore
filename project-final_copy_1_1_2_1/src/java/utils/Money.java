
package utils;

import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Money {
    
    
    public static String getMoney(Double soTien) {
        
 // Thiết lập các ký tự định dạng: dấu phân cách nhóm là dấu chấm, dấu thập phân là dấu phẩy (nếu cần)
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');  // Nếu có số thập phân, nhưng ở đây không cần
        
        // Định dạng số: "#,###" có nghĩa là có nhóm hàng, không hiển thị số thập phân
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        
        return formatter.format(soTien) + " VNĐ";
        
    }
    
    public static void main(String[] args) {
//        System.out.println(Money.getMoney(1000000.0));
        
        double number = 1000000.0;
        Money.getMoney(number);
        System.out.println(Money.getMoney(number));
        
    }
    
    
}