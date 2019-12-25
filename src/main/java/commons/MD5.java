package commons;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  加密密码
 *
 * @author DanierHe
 * @date 2019-08-22-08-22 10:10
 */
public class MD5 {

    /****
     *  md5加密用户密码，
     * @param plainText 明文，不传默认123456
     * @return 加密后的密文
     */
    public static String encryption(String plainText){
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            if(StringUtils.isEmpty(plainText)){
                plainText = "123456";
            }
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuffer stringBuffer = new StringBuffer("");
            for (int offset = 0;offset<b.length;offset++){
                i = b[offset];
                if(i<0){
                    i+=256;
                }
                if(i<16){
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            re_md5 = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }


    public static void main(String[] args) {
        String str = "-111.000";
        Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
        Matcher s = pattern.matcher(str);
        System.out.println(s.matches());
    }

}
