package com.imcode.rls.orders.model;

import com.imcode.rls.roomsource.model.RoomSource;
import com.imcode.rls.userinfo.model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomNews {
    private int roomNewID;
    private RoomSource roomNO;
    private UserInfo userID;
    private String newTitle;
    private String newDescription;
    private String newTime;
    private String newHTML;
    private NewTypes newTypeID;
    private int newSeeCount;
}
