# CoffeAfternoon

Yêu cầu cài đặt các service:

#Java 17

#Mysql:
user: blog, pass: blog
database: coffeafternoon

#RabbitMQ:
Service running
http://localhost:15672 is running


a, Xác định yêu cầu (Được triển khai với microservice với các module chính sau, sẽ cập nhật ):

1. Module quản lý người dùng ( hội viên đăng ký vé tháng hoặc khách mua vé lẻ hàng ngày, mua vé lẻ có thể không cần quản lý ).
  
3. Module quản lý phòng ngủ.
   
5. Module cho thuê phòng, gồm các hoạt động như cho thuê, thêm voucher, …. Khi cho thuê sẽ update các phòng và cộng tiền vào module báo cáo.
   
7. Module báo cáo doanh thu, báo cáo lượng người đăng ký, lập biểu đồ.
   
9. Module quản lý user.
    
11. Module quản lý vé ngày, vé tháng. 
