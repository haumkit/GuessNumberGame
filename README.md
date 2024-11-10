Mô tả Game Tính Nhanh

Hệ thống:
- Hệ thống bao gồm một Server và nhiều client kết nối với nhau. Server sẽ quản lý các trận đấu và lưu trữ thông tin người chơi.
Đăng nhập:
- Người chơi cần đăng nhập vào tài khoản của mình từ một máy client. Sau khi đăng nhập thành công, giao diện sẽ hiển thị danh sách các người chơi đang trực tuyến cùng với thông tin: tên người chơi, tổng số điểm hiện có, và trạng thái (đang chơi hoặc đang online hoặc đang offline).
Bắt đầu trận đấu:
- Người chơi có thể gửi lời mời chơi cho một người chơi khác từ danh sách trực tuyến (không thuộc người chơi đang chơi). Khi người chơi khác chấp nhận, trận đấu sẽ bắt đầu.

Luật chơi:
- Trò chơi Tính Nhanh diễn ra như sau:
+	Mỗi người chơi sẽ có một số điểm riêng gọi là điểm ELO dùng để xếp hạng các người chơi, điểm ELO ban đầu là 1000.
+	Server sẽ ngẫu nhiên chọn một giá trị mục tiêu trong khoảng từ [0-100].
+	Người chơi chọn các số và phép tính cho sẵn sao cho kết quả của phép tính bằng với giá trị mục tiêu.
+	Nếu người chơi chọn độ khó cao hơn, họ sẽ phải thực hiện 2, 3 liên tiếp để đạt được giá trị mục tiêu.
+	Người chơi có một khoảng thời gian giới hạn để lựa chọn các phép tính, và có thể xem kết quả tạm thời của phép tính đã chọn.
+	Người chơi chiến thắng ván đấu và được 1 điểm, nếu thua thì trừ 1 điểm, hoà giữ nguyên.
+	Trong trường hợp cả hai người chơi đều đưa ra đáp án đúng, người nào đưa ra đáp án nhanh hơn sẽ giành phần thắng. Nếu cả hai người cùng đưa ra đáp án, cùng thời gian sẽ được tính là hoà.
+	Trong trường hợp hết thời gian, nếu hai người đều không đưa ra được đáp án hoặc cùng đưa đáp án sai thì hoà và không được cộng điểm.

Giao diện:
- Giao diện trò chơi hiển thị ô nhập liệu để người chơi nhập số và chọn phép tính. Sau khi gửi, kết quả sẽ hiển thị ngay lập tức cho biết kết quả tạm thời của phép tính và liệu người chơi có đạt được giá trị mục tiêu hay không.

Kết thúc trận đấu:
- Trận đấu kết thúc, Server sẽ thông báo người chiến thắng và cập nhật điểm số. 
Thoát khỏi trận đấu:
- Người chơi có thể thoát khỏi trận đấu bất cứ lúc nào, nhưng sẽ bị trừ 5 điểm nếu làm vậy trước khi trận đấu kết thúc.

Xếp hạng:
- Kết quả của các trận đấu sẽ được lưu trữ trên Server. Mỗi người chơi có thể xem bảng xếp hạng của toàn bộ hệ thống theo tổng số điểm ELO (giảm dần) và số trận thắng.
