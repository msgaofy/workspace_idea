package com.cbx.ykt.tlv;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TLVUtils {
    /**
     * 将16进制字符串转换为TLV对象列表
     * @param hexString
     * @return
     */
    public static List<TLVMessage> builderTlvList(String hexString) {
        List<TLVMessage> tlvs = new ArrayList<TLVMessage>();

        int position = 0;
        while (position != StringUtils.length(hexString)) {
            String _hexTag = getTag(hexString, position);
            position += _hexTag.length();

            VLPosition l_position = getLengthAndPosition(hexString, position);
            int _vl = l_position.get_vl();

            position = l_position.get_vp();

            String _value = StringUtils.substring(hexString, position, position + _vl * 2);

            position = position + _value.length();

            tlvs.add(new TLVMessage(_hexTag, _vl, _value));
        }
        return tlvs;
    }

    /**
     * 将16进制字符串转换为TLV对象MAP
     * @param hexString
     * @return
     */
    public static Map<String, TLVMessage> builderTlvMap(String hexString) {

        Map<String, TLVMessage> tlvs = new HashMap<String, TLVMessage>();

        int position = 0;
        while (position != hexString.length()) {
            String _hexTag = getTag(hexString, position);
            position += _hexTag.length();

            VLPosition l_position = getLengthAndPosition(hexString, position);
            int _vl = l_position.get_vl();

            position = l_position.get_vp();

            String _value = StringUtils.substring(hexString, position, position + _vl * 2);

            position = position + _value.length();
            tlvs.put(_hexTag, new TLVMessage(_hexTag, _vl, _value));
        }
        return tlvs;
    }

    /**
     * 返回最后的Value的长度
     *
     * @param hexString
     * @param position
     * @return
     */
    private static VLPosition getLengthAndPosition(String hexString, int position) {
        String firstByteString = hexString.substring(position, position + 2);
        int i = Integer.parseInt(firstByteString, 16);
        String hexLength = "";

        if (((i >>> 7) & 1) == 0) {
            hexLength = hexString.substring(position, position + 2);
            position = position + 2;
        } else {
            // 当最左侧的bit位为1的时候，取得后7bit的值，
            int _L_Len = i & 127;
            position = position + 2;
            hexLength = hexString.substring(position, position + _L_Len * 2);
            // position表示第一个字节，后面的表示有多少个字节来表示后面的Value值
            position = position + _L_Len * 2;
        }
        return new VLPosition(Integer.parseInt(hexLength, 16), position);

    }

    /**
     * 取得子域Tag标签
     *
     * @param hexString
     * @param position
     * @return
     */
    private static String getTag(String hexString, int position) {
        String firstByte = StringUtils.substring(hexString, position, position + 2);
        int i = Integer.parseInt(firstByte, 16);//将16进制的字符串转换成10进制
        if ((i & 0x1f) == 0x1f) {//10进制自动转成2进制按位与运算
            return hexString.substring(position, position + 4);

        } else {
            return hexString.substring(position, position + 2);
        }
    }

}
