//不同操作参数设计
public class Util {
    /**
     * 0代表留数，
     */
    public final static int TYPE_ONE = 0;

    /**
     * 1代表截取，
     */
    public final static int TYPE_TWO = 1;

    /**
     * 2代表截去，
     */
    public final static int TYPE_THREE = 2;

    /**
     * 3代表段插入
     */
    public final static int TYPE_FOUR = 3;

    /**
     * 4代表改后缀
     */
    public final static int TYPE_FIVE = 4;

    /**
     * 5代表重命名
     */
    public final static int TYPE_SIX = 5;

    /**
     * 正向读取 direction
     */
    public final static boolean POSITIVE = true;

    /**
     * 逆向读取
     */
    public final static boolean REVERSE = false;

    /**
     * 含子项
     */
    public final static boolean INCLUDE_SUBITEM = true;

    /**
     * 当前项
     */
    public final static boolean EXCLUDE_SUBITEM = false;

    /**
     * 帮助文档
     */
    private static String space = "               ";
    public final static String HELP = "留数字: 把文件名中的非数字字符移除只留下数字\n" + space +
            "正反向:'我12A3a4%5.txt'>>>'12345.txt'\n"
            + "段截取: 把文件名截取而留下截取部分,始位末位都包括该位\n" + space +
            "正向:'12345.txt'>>>从'1'到'3'>>>'123.txt'\n" + space
            + "反向:'12345.txt'>>>从'1'到'3'>>>'345.txt'\n" +
            "段抠去: 把文件名截取而移除截取部分,始位末位都包括该位\n" + space
            + "正向:'12345.txt'>>>从'1'到'3'>>>'45.txt'\n" + space +
            "反向:'12345.txt'>>>从'1'到'3'>>>'12.txt'\n"
            + "段插入: 把自定义的字符串插入指定位置后\n" + space +
            "正向:'12345.txt'>>>插'abc'到'3'>>>'123abc45.txt'\n" + space
            + "反向:'12345.txt'>>>插'abc'到'3'>>>'12abc345.txt'\n" +
            "改后缀: 将文件名后缀修改\n" + space
            + "正反向:'12345.txt'>>>'12345.java'\n" +
            "    正向: 读取文件名从左到右\n" + "    反向: 读取文件名从右到左\n"
            +"当前项: 只执行处理当前文件夹下的文件类型文件\n"
            +"含子项: 处理当前文件夹下及所有子文件夹的文件类型文件";
}
