package commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author DanierHe
 * @description
 * @date 2019-12-20-12-20 14:52
 */
public class DateUtils {
    private Logger logger = LoggerFactory.getLogger(DateUtils.class);
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    public static SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
    public static SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
    public static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
    * @Author: DanierHe
    * @Date: 2019-12-20 下午 02:56
    * @Description: 获取当前的年份
    * @Param: []
    * @return: java.lang.String
    */
    public static String getYear(){
        return sdf.format(new Date());
    }


    /**
    * @Author: DanierHe
    * @Date: 2019-12-20 下午 02:57
    * @Description: 获取月份
    * @Param: []
    * @return: java.lang.String
    */
    public static String getMonth(){
        return sdf1.format(new Date());
    }


    /**
    * @Author: DanierHe
    * @Date: 2019-12-20 下午 02:56
    * @Description: 获取当前月
    * @Param: []
    * @return: java.lang.String
    */
    public static String getDay(){
        return sdf2.format(new Date());
    }


    /**
    * @Author: DanierHe
    * @Date: 2019-12-20 下午 02:58
    * @Description: 获取 yyyy-MM-dd 格式的日期
    * @Param: []
    * @return: java.lang.String
    */
    public static String getYmd(){
        return sdf3.format(new Date());
    }

    /**
    * @Author: DanierHe
    * @Date: 2019-12-20 下午 02:59
    * @Description: 获取 yyyy-MM-dd HH:mm:ss 格式的时间
    * @Param: []
    * @return: java.lang.String
    */
    public static String getYmdhms(){
        return sdf4.format(new Date());
    }
}
