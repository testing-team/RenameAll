
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
//主界面
@SuppressWarnings("serial")
public class Show extends JFrame implements ActionListener, FocusListener {
        private JMenuBar jmr;// 菜单栏
        private JMenu jmFile, jmDeal, jmHelp;// 菜单项
        private JMenuItem jmiOpen, jmiExit, jmiClear, jmiRun, jmiHelp;// 菜单子按键项
        private ButtonGroup btngrp;// 单选按键组
        private JRadioButton jrb1, jrb2, jrb3, jrb4, jrb5,jrb6;// 定义单选框组件
        private JButton jbOpen, jbClear, jbRun;// 按键
        private JComboBox<String> jcbDirection,jcbSubitem;// 下拉选项框
        private String[] sDirection = { "正向", "反向" };// 下拉框所有项
        private String[] sSubitem = { "当前项", "含子项" };// 下拉框所有项
        private JTextField jtfPath, jtfIndex1, jtfIndex2, jtfString;// 定义文本框组件
        private Box vertical;// 布局
        private JPanel jp1, jp2, jp3;// 面板
        private int type = Util.TYPE_ONE;// 文件处理方式，默认第一种
        private boolean direction = Util.POSITIVE;// 文件处理读取方向，默认正向
        private boolean isIncludeSubitem = Util.EXCLUDE_SUBITEM;// 是否包含子项，默认不包含，仅当前
        private String str = "";// 需要插入的字符串

        public Show() {
            creatObject();// 创建对象
            addListener();// 添加监听
            setLayout();// 设置布局

            this.setJMenuBar(jmr);
            this.add(vertical);// BorderLayout.NORTH
            this.setTitle("批量修改文件名");
            this.setSize(480, 190);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        /**
         * 创建对象
         */
        private void creatObject() {
            vertical = Box.createVerticalBox();
            jp1 = new JPanel();
            jp2 = new JPanel();
            jp3 = new JPanel();

            jmr = new JMenuBar();
            jmiOpen = new JMenuItem("打开");
            jmiExit = new JMenuItem("退出");
            jmiClear = new JMenuItem("清除");
            jmiRun = new JMenuItem("执行");
            jmiHelp = new JMenuItem("帮助");
            jmFile = new JMenu("文件");
            jmFile.add(jmiOpen);
            jmFile.add(jmiExit);
            jmDeal = new JMenu("操作");
            jmDeal.add(jmiClear);
            jmDeal.add(jmiRun);
            jmHelp = new JMenu("帮助");
            jmHelp.add(jmiHelp);
            jmr.add(jmFile);
            jmr.add(jmDeal);
            jmr.add(jmHelp);

            btngrp = new ButtonGroup();
            jrb1 = new JRadioButton("留数字   ", true);
            jrb2 = new JRadioButton("段截取   ");
            jrb3 = new JRadioButton("段抠去   ");
            jrb4 = new JRadioButton("段插入   ");
            jrb5 = new JRadioButton("重命名");
            jrb6 = new JRadioButton("改后缀");
            btngrp.add(jrb1);
            btngrp.add(jrb2);
            btngrp.add(jrb3);
            btngrp.add(jrb4);
            btngrp.add(jrb5);
            btngrp.add(jrb6);

            jcbDirection = new JComboBox<String>(sDirection);
            jcbDirection.setPreferredSize(new Dimension(55, 21));
            jcbSubitem = new JComboBox<String>(sSubitem);
            jcbSubitem.setPreferredSize(new Dimension(67, 21));

            jtfPath = new JTextField("请输入具体路径", 28);
            jtfIndex1 = new JTextField(2);
            jtfIndex1.setEditable(false);
            jtfIndex2 = new JTextField(2);
            jtfIndex2.setEditable(false);
            jtfString = new JTextField(6);
            jtfString.setEditable(false);

            jbOpen = new JButton("打开");
            jbOpen.setMargin(new Insets(1, 5, 1, 1));
            jbOpen.setPreferredSize(new Dimension(50, 22));
            jbClear = new JButton("清除");
            jbClear.setMargin(new Insets(1, 1, 1, 1));
            jbClear.setPreferredSize(new Dimension(46, 22));
            jbRun = new JButton("执行");
            jbRun.setMargin(new Insets(1, 1, 1, 1));
            jbRun.setPreferredSize(new Dimension(46, 22));

        }

        /**
         * 添加监听
         */
        private void addListener() {
            // 菜单按键添加动作监听
            jmiOpen.addActionListener(this);
            jmiExit.addActionListener(this);
            jmiClear.addActionListener(this);
            jmiRun.addActionListener(this);
            jmiHelp.addActionListener(this);
            // 单选框添加动作监听
            jrb1.addActionListener(this);
            jrb2.addActionListener(this);
            jrb3.addActionListener(this);
            jrb4.addActionListener(this);
            jrb5.addActionListener(this);
            jrb6.addActionListener(this);
            // 文本框添加焦点监听
            jtfPath.addFocusListener(this);
            jtfIndex1.addFocusListener(this);
            jtfIndex2.addFocusListener(this);
            jtfString.addFocusListener(this);
            // 普通按键添加动作监听
            jbOpen.addActionListener(this);
            jbClear.addActionListener(this);
            jbRun.addActionListener(this);

        }

        /**
         * 设置布局
         */
        private void setLayout() {
            jp1.add(jrb1);
            jp1.add(jrb2);
            jp1.add(jrb3);
            jp1.add(jrb4);
            jp1.add(jrb5);
            jp1.add(jrb6);
            jp2.add(jtfPath);
            jp2.add(jbOpen);
            jp3.add(jcbDirection);
            jp3.add(jcbSubitem);
            jp3.add(jtfIndex1);
            jp3.add(jtfIndex2);
            jp3.add(jtfString);
            jp3.add(jbClear);
            jp3.add(jbRun);

            vertical.add(jp1);
            vertical.add(jp2);
            vertical.add(jp3);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            listenJRadioButtonEvent(e);// 监听单选框部分
            listenJButtonEvent(e);// 监听按键部分
        }

        private void listenJButtonEvent(ActionEvent e) {
            if (e.getSource() == jbOpen || e.getSource() == jmiOpen) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                File dir = new File(jtfPath.getText());
                if (dir.isDirectory()) {
                    jfc.setCurrentDirectory(dir);
                }
                jfc.showDialog(new JLabel(), "选择");
                File file = jfc.getSelectedFile();
                if (file != null) {
                    jtfPath.setText(file.getAbsolutePath());
                }
            } else if (e.getSource() == jmiExit) {
                System.exit(0);
            } else if (e.getSource() == jmiHelp) {
                JOptionPane.showMessageDialog(null, Util.HELP, "帮助", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == jbClear || e.getSource() == jmiClear) {
                jtfPath.setText("请输入具体路径");
                if (jtfIndex1.isEditable() == true)
                    jtfIndex1.setText("从");
                if (jtfIndex2.isEditable() == true)
                    jtfIndex2.setText("到");
                if (jtfString.isEditable() == true)
                    jtfString.setText("输入字符串");
            } else if (e.getSource() == jbRun || e.getSource() == jmiRun) {
                if (type == 3 || type == 4 || type == 5){
                    str = jtfString.getText();
                }
                if (jcbDirection.getSelectedItem().equals("正向")) {
                    direction = Util.POSITIVE;
                } else {
                    direction = Util.REVERSE;
                }
                if (jcbSubitem.getSelectedItem().equals("当前项")) {
                    isIncludeSubitem=Util.EXCLUDE_SUBITEM;
                } else {
                    isIncludeSubitem=Util.INCLUDE_SUBITEM;
                }
                new Change().changeName(jtfPath.getText(), type, direction,isIncludeSubitem, jtfIndex1.getText(), jtfIndex2.getText(), str);
            }
        }

        private void listenJRadioButtonEvent(ActionEvent e) {
            if (e.getSource() == jrb1) {
                setStatus("", "", "", false, false, false);
                type = Util.TYPE_ONE;
            } else if (e.getSource() == jrb2) {
                setStatus("从", "到", "", true, true, false);
                type = Util.TYPE_TWO;
            } else if (e.getSource() == jrb3) {
                setStatus("从", "到", "", true, true, false);
                type = Util.TYPE_THREE;
            } else if (e.getSource() == jrb4) {
                setStatus("从", "", "输入字符串", true, false, true);
                type = Util.TYPE_FOUR;
            } else if (e.getSource() == jrb5) {
                setStatus("", "", "输入字符串", false, false, true);
                type = Util.TYPE_SIX;
            }else if (e.getSource() == jrb6) {
                setStatus("", "", "输入字符串", false, false, true);
                type = Util.TYPE_FIVE;
            }
        }

        /**
         * 设置各个文本框的状态
         */
        private void setStatus(String s1, String s2, String s3, boolean b1, boolean b2, boolean b3) {
            jtfIndex1.setText(s1);
            jtfIndex2.setText(s2);
            jtfString.setText(s3);
            jtfIndex1.setEditable(b1);
            jtfIndex2.setEditable(b2);
            jtfString.setEditable(b3);
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (e.getSource() == jtfPath) {
                if (jtfPath.getText().trim().equals("请输入具体路径"))
                    jtfPath.setText("");
                else
                    jtfPath.selectAll();
            } else if (e.getSource() == jtfIndex1) {
                if (jtfIndex1.getText().trim().equals("从"))
                    jtfIndex1.setText("");
                else
                    jtfIndex1.selectAll();
            } else if (e.getSource() == jtfIndex2) {
                if (jtfIndex2.getText().trim().equals("到"))
                    jtfIndex2.setText("");
                else
                    jtfIndex2.selectAll();
            } else {
                if (jtfString.getText().equals("输入字符串"))
                    jtfString.setText("");
                else
                    jtfString.selectAll();
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (e.getSource() == jtfPath) {
                if (jtfPath.getText().trim().length() == 0)
                    jtfPath.setText("请输入具体路径");
            } else if (e.getSource() == jtfIndex1) {
                if (jtfIndex1.getText().trim().length() == 0 && jtfIndex1.isEditable() == true)
                    jtfIndex1.setText("从");
            } else if (e.getSource() == jtfIndex2) {
                if (jtfIndex2.getText().trim().length() == 0 && jtfIndex2.isEditable() == true)
                    jtfIndex2.setText("到");
            } else {
                if (jtfString.getText().trim().length() == 0 && jtfString.isEditable() == true)
                    jtfString.setText("输入字符串");
            }
        }
}
