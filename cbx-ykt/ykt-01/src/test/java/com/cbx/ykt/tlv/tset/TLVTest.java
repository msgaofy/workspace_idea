package com.cbx.ykt.tlv.tset;

import com.cbx.ykt.tlv.TLVMessage;
import com.cbx.ykt.tlv.TLVUtils;

import java.util.List;
import java.util.Map;

public class TLVTest {

    public static void main(String[] args) {
        String data = "950500000008004F08A0000003330101019F36022935500A50424F432044454249549F3704CC8DEE7A82027C009F34030000009F101307020103A00000010A010000017400116766E99F3303E0E1C0";
        List<TLVMessage> tlvMessageList =  TLVUtils.builderTlvList(data);
        Map<String,TLVMessage> tlvMessageMap = TLVUtils.builderTlvMap(data);
        System.out.println("list==="+tlvMessageList);
        System.out.println("map==="+tlvMessageMap);
    }
}
