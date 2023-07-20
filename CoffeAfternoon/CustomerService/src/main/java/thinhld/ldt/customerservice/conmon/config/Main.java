package thinhld.ldt.customerservice.conmon.config;

import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Lấy thời gian hiện tại
        Date currentTime = new Date();

        // Tạo một đối tượng Calendar và thiết lập thời gian là currentTime
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);

        // Cộng thêm 1 tháng vào Calendar
        calendar.add(Calendar.MONTH, 1);

        // Lấy thời gian sau khi đã cộng thêm 1 tháng
        Date newDate = calendar.getTime();

        // In ra giá trị của newDate
        System.out.println("Ngày sau khi cộng thêm 1 tháng: " + newDate);

        // So sánh newDate với thời gian hiện tại
        if (newDate.after(currentTime)) {
            System.out.println("newDate sau thời gian hiện tại.");
        } else if (newDate.before(currentTime)) {
            System.out.println("newDate trước thời gian hiện tại.");
        } else {
            System.out.println("newDate bằng thời gian hiện tại.");
        }
    }
}