import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Change {
    private int a = 0, b = 0;// 脚标默认初始值
    private String sInsert = "";// 插入的字符串默认
    private boolean direction = Util.POSITIVE;// 处理读取方向默认正向
    private boolean isIncludeSubitem = Util.EXCLUDE_SUBITEM;// 是否包含子项，默认不包含，仅当前
    private List<File> fileList;

    /**
     *
     * @param dirPath
     * @param type
     * @param direction
     * @param isIncludeSubitem
     * @param index1
     * @param index2
     * @param sInsert
     */
    public void changeName(String dirPath, int type, boolean direction, boolean isIncludeSubitem, String index1,
                           String index2, String sInsert) {
        try {
            File file = new File(dirPath);// 创建file对象
            if (file.isDirectory()) { // 判断文件夹是否存在
                this.sInsert = sInsert;// 插入的字符串
                this.direction = direction;// 处理读取方向
                this.isIncludeSubitem=isIncludeSubitem;
                File[] files = file.listFiles();// 获取file目录下所有文件对象
                fileList = new ArrayList<>();

                addFileToFileList(files);

                if (type == Util.TYPE_ONE) {
                    // 留数字
                    doChange(type, a, b);
                } else if (type == Util.TYPE_TWO || type == Util.TYPE_THREE) {
                    // 段截取和段截去
                    if (isNumber(index1) && isNumber(index2)) {
                        a = Integer.parseInt(index1);
                        b = Integer.parseInt(index2);
                        if (a == 0 || b == 0) {
                            showPrompt("截取位置出错(是否含0？)！", "警告！", 2);
                        } else if (a > b) {
                            showPrompt("截取位置出错(是否始值大于末值？)！", "警告！", 2);
                        } else {
                            doChange(type, a, b);
                        }
                    } else {
                        showPrompt("输入的位置的值不是整数！", "警告！", 2);
                    }
                } else if (type == Util.TYPE_FOUR) {
                    // 段插入
                    if (!isNumber(index1)) {
                        showPrompt("输入的位置的值不是整数！", "警告！", 2);
                    } else if (Integer.parseInt(index1) < 0) {
                        showPrompt("输入的位置的值不能小于0！", "警告！", 2);
                    } else {
                        a = Integer.parseInt(index1);
                        if (sInsert.equals("请输入字符串")) {
                            showPrompt("请输入要插入的字符串！", "提示！", 2);
                        } else {
                            doChange( type, a, b);
                        }
                    }
                } else if (type == Util.TYPE_FIVE) {
                    // 改后缀
                    if (sInsert.equals("请输入字符串")) {
                        showPrompt("请输入新后缀名！", "提示！", 2);
                    } else {
                        doChange( type, a, b);
                    }
                }else if (type == Util.TYPE_SIX){
                    if (sInsert.equals("请输入字符串")) {
                        showPrompt("请输入新后缀名！", "提示！", 2);
                    } else {
                        doChange( type, a, b);
                    }
                }
            } else {
                showPrompt("无该文件夹！", "错误！", 0);
            }
        } catch (Exception e) {
            showPrompt("异常(检查是否路径错误)！", "错误！", 0);
        }
    }

    /**
     * 添加文件类型文件到文件list
     * @param files file类型对象数组
     */
    private void addFileToFileList(File[] files) {
        if (files != null) {
            for (File f : files) {
                if (isIncludeSubitem == Util.EXCLUDE_SUBITEM) {
                    if (f.isFile()) {
                        // 忽略文件夹,只留文件类型的file对象
                        fileList.add(f);
                    }
                } else {
                    if (f.isFile()) {
                        // 忽略文件夹,只留文件类型的file对象
                        fileList.add(f);
                    } else {
                        //使用递归遍历所有子文件夹
                        addFileToFileList(f.listFiles());
                    }
                }
            }
        }
    }

    /**
     * 批量修改成功提示
     */
    public void showPrompt(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void doChange( int type, int a, int b) {
        System.out.println(fileList.size());
        for (File file : fileList) {
            int m = 1;
            String sFileName = file.getName();// 获取文件名称字符串
            // fileFromsName = fileFromsName.replace("\\s", "");// 去所有空格
            String[] sArrFileName = sFileName.split("\\.");// 以“.”截取字符串数组
            String sNewFileNameNoSuffix = "";// 没有后缀的新文件名
            String sNewFileName = "";// 新文件名

            for (int i = 0; i < sArrFileName.length - 1; i++) {
                sNewFileNameNoSuffix += sArrFileName[i] + ".";
            }
            sNewFileNameNoSuffix = sNewFileNameNoSuffix.substring(0, sNewFileNameNoSuffix.length() - 1);

            if (type == Util.TYPE_ONE) {// 只留数字
                char[] names = sNewFileNameNoSuffix.toCharArray();// 将字符串转成字符数组
                for (char name : names) {
                    if (name >= 48 && name <= 57)// 判断是否为数字
                        sNewFileName += name;
                }
            }
            if (type == Util.TYPE_TWO) {// 截取字符串
                if (b > sNewFileNameNoSuffix.length()) {
                    b = sNewFileNameNoSuffix.length();
                }
                if (direction == Util.POSITIVE) {
                    sNewFileName = sNewFileNameNoSuffix.substring(a - 1, b);
                } else {
                    int length = sNewFileNameNoSuffix.length();
                    sNewFileName = sNewFileNameNoSuffix.substring(length - b, length - a + 1);
                }
            }
            if (type == Util.TYPE_THREE) {// 截去字符串
                if (b > sNewFileNameNoSuffix.length()) {
                    b = sNewFileNameNoSuffix.length();
                }
                if (direction == Util.POSITIVE) {
                    String sTemp = sNewFileNameNoSuffix;
                    sNewFileName = sTemp.substring(0, a - 1) + sTemp.substring(b, sTemp.length());
                } else {
                    String sTemp = sNewFileNameNoSuffix;
                    int length = sNewFileNameNoSuffix.length();
                    sNewFileName = sTemp.substring(0, length - b) + sTemp.substring(length - a + 1, length);
                }
            }

            if (type == Util.TYPE_FOUR) {// 段插入
                if (a > sNewFileNameNoSuffix.length()) {
                    a = sNewFileNameNoSuffix.length();
                }
                if (direction == Util.POSITIVE) {
                    sNewFileName = sNewFileNameNoSuffix.substring(0, a) + sInsert + sNewFileNameNoSuffix.substring(a);
                } else {
                    int length = sNewFileNameNoSuffix.length();
                    sNewFileName = sNewFileNameNoSuffix.substring(0, length - a) + sInsert
                            + sNewFileNameNoSuffix.substring(length - a, length);
                }
            }

            if (type == Util.TYPE_FIVE) {// 该后缀
                sArrFileName[sArrFileName.length - 1] = sInsert;
                sNewFileName = sNewFileNameNoSuffix;
            }

            if (type == Util.TYPE_SIX) {// 重命名

                    sNewFileName = sInsert + m ;
                    m++;
            }

            sNewFileName += "." + sArrFileName[sArrFileName.length - 1];// 给新文件名添加后缀

            File newFile = new File(file.getParentFile().getAbsolutePath() + "\\" + sNewFileName);
            if (file.exists() && !newFile.exists()) {
                file.renameTo(newFile);
            } else {
                showPrompt("输出文件同名，无法再进行！", "警告！", 2);
                break;
            }
        }
        showPrompt("批量修改成功！", "恭喜你！", 1);
    }
}
