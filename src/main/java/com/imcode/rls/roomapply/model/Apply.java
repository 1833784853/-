package com.imcode.rls.roomapply.model;

import com.imcode.rls.roomsource.model.RoomSource;
import com.imcode.rls.userinfo.model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apply {
        private int applyID;  //申请房间每条记录的id
        private UserInfo userID;  //用户id
        private RoomSource roomNO;  //房源信息的编号（外键）
        private int applyStatus;//0：表示该房没有被申请 1：正在申请看房 2：表示该房被申请 3：表示该房被同意申请4：表示该放被拒绝申请 5：表示该房可以被申请退房 6：正在申请退房 7：表示该房被同意申请退房 8：表示该房被拒绝申请退房
        private int roomListID; //房源编号
}
