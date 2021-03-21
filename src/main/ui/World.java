package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class World extends JFrame {

    private JButton bt1;
    private JButton bt2;
    private JButton bt3;
    private JPanel tbPanel1;
    private JPanel tbPanel2;
    private JPanel tbPanel3;
    private JTable table1;


    /**
     * public world() {
     * button1.addActionListener(new ActionListener() {
     *
     * @Override public void actionPerformed(ActionEvent e) {
     * JOptionPane.showMessageDialog(null,"Hello world");
     * }
     * });
     * }
     **/
    public void setUI() {

        JFrame frame = new JFrame(("World"));
        frame.getContentPane().setBackground(new Color(229, 229, 229));
        frame.setLayout(new BorderLayout());

        JPanel mainLayout = new JPanel();
        mainLayout.setLayout(new BoxLayout(mainLayout, BoxLayout.Y_AXIS));

        JPanel topbar = new JPanel();
        topbar.setBackground(Color.WHITE);
        topbar.add(Box.createHorizontalStrut(10));

        JLabel logo = new JLabel();
        logo.setText("Globe");
        ImageIcon ic = new ImageIcon("images/globeicon.png");
        Image glbimage = ic.getImage(); // transform it
        Image newimgGlb = glbimage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ic = new ImageIcon(newimgGlb);
        logo.setFont(new Font("Nunito", Font.BOLD, 26));
        logo.setIcon(ic);
        logo.setIconTextGap(8);

        topbar.add(logo);

        topbar.add(Box.createHorizontalStrut(10));

        JLabel searchIco = new JLabel();
        ImageIcon ic2 = new ImageIcon("images/searchico.png");
        searchIco.setIcon(ic2);
        topbar.add(searchIco);

        topbar.add(Box.createHorizontalStrut(10));

        JTextField searchVisit = new JTextFieldHintUI("Search visit");
        searchVisit.setBorder(null);
        searchVisit.setMargin(new Insets(5, 50, 5, 10));
        searchVisit.setPreferredSize(new Dimension(500, 30));
        topbar.add(searchVisit);

        mainLayout.add(topbar);

        JPanel mid = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mid.add(Box.createHorizontalStrut(10));
        JPanel opts = new JPanel();
        opts.setLayout(new BoxLayout(opts, BoxLayout.Y_AXIS));
        bt1 = new JButton("World");
        bt2 = new JButton("Wishlist");
        bt3 = new JButton("Visits List");

        bt1.setBorderPainted(false);
        bt1.setContentAreaFilled(false);
        bt1.setFocusPainted(false);
        bt1.setFont(new Font("Nunito", Font.PLAIN, 14));
        bt1.setAlignmentX(Component.CENTER_ALIGNMENT);

        bt2.setAlignmentX(Component.CENTER_ALIGNMENT);
        bt2.setBorderPainted(false);
        bt2.setContentAreaFilled(false);
        bt2.setFocusPainted(false);
        bt2.setFont(new Font("Nunito", Font.PLAIN, 14));

        bt3.setAlignmentX(Component.CENTER_ALIGNMENT);
        bt3.setBorderPainted(false);
        bt3.setContentAreaFilled(false);
        bt3.setFocusPainted(false);
        bt3.setForeground(new Color(247, 37, 133));
        bt3.setFont(new Font("Nunito", Font.PLAIN, 14));

        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bt1.setForeground(new Color(247, 37, 133));
                bt2.setForeground(Color.BLACK);
                bt3.setForeground(Color.BLACK);
                createtbpanel1();
                if (mid.getComponent(3).getName().equals("2")) {
                    mid.remove(tbPanel2);
                    createtbpanel1();
                    mid.add(tbPanel1);


                }

                if (mid.getComponent(3).getName().equals("3")) {
                    mid.remove(tbPanel3);
                    createtbpanel1();
                    mid.add(tbPanel1);

                }
                mid.revalidate();
                mid.repaint();
            }
        });
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bt2.setForeground(new Color(247, 37, 133));
                bt1.setForeground(Color.BLACK);
                bt3.setForeground(Color.BLACK);
                createtbpanel2();
                if (mid.getComponent(3).getName().equals("1")) {
                    mid.remove(tbPanel1);
                    createtbpanel2();
                    mid.add(tbPanel2);
                }

                if (mid.getComponent(3).getName().equals("3")) {
                    mid.remove(tbPanel3);
                    createtbpanel2();
                    mid.add(tbPanel2);
                }


                mid.revalidate();
                mid.repaint();
            }
        });
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bt3.setForeground(new Color(247, 37, 133));
                bt2.setForeground(Color.BLACK);
                bt1.setForeground(Color.BLACK);

                if (mid.getComponent(3).getName().equals("2")) {
                    mid.remove(tbPanel2);
                    createtbPanel3();
                    mid.add(tbPanel3);
                }

                if (mid.getComponent(3).getName().equals("1")) {
                    mid.remove(tbPanel1);
                    createtbPanel3();
                    mid.add(tbPanel3);
                }


                mid.revalidate();
                mid.repaint();
            }
        });

        opts.add(Box.createRigidArea(new Dimension(0, 50)));
        opts.add(bt1);
        opts.add(bt2);
        opts.add(bt3);
        opts.add(Box.createRigidArea(new Dimension(0, 80)));

        mid.add(opts);
        mid.add(Box.createHorizontalStrut(13));

        createtbPanel3();
        mid.add(tbPanel3);

        //add table
        createVisitTable();
        mainLayout.add(mid);


        frame.add(mainLayout, BorderLayout.NORTH);


        frame.setTitle("Globe");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.requestFocusInWindow();

    }


    public void createtbPanel3() {
        tbPanel3 = new JPanel();
        tbPanel3.setName("3");
        tbPanel3.setBackground(Color.WHITE);
        tbPanel3.setLayout(new BoxLayout(tbPanel3, BoxLayout.Y_AXIS));
        JPanel tbOptions = new JPanel();
        tbOptions.setBackground(null);
        tbOptions.add(Box.createHorizontalStrut(5));

        JLabel visitList = new JLabel();
        visitList.setText("Visited Places");
        visitList.setFont(new Font("Nunito", Font.BOLD, 24));
        visitList.setForeground(new Color(247, 37, 133));
        tbOptions.add(visitList);

        JLabel add = new JLabel();
        ImageIcon addIc = new ImageIcon("images/addicon.png");
        Image image = addIc.getImage(); // transform it
        Image newimg = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        addIc = new ImageIcon(newimg);
        add.setIcon(addIc);
        tbOptions.add(Box.createHorizontalStrut(10));
        tbOptions.add(add);

        JLabel sub = new JLabel();
        ImageIcon subIc = new ImageIcon("images/removeicon.png");
        Image remimage = subIc.getImage(); // transform it
        Image newimgRem = remimage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        subIc = new ImageIcon(newimgRem);
        sub.setIcon(subIc);
        tbOptions.add(Box.createHorizontalStrut(1));
        tbOptions.add(sub);

        JLabel filter = new JLabel();
        ImageIcon filterIc = new ImageIcon("images/filtericon.png");
        Image filimg = filterIc.getImage(); // transform it
        Image newfilimg = filimg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        filterIc = new ImageIcon(newfilimg);
        filter.setIcon(filterIc);
        tbOptions.add(Box.createHorizontalStrut(1));
        tbOptions.add(filter);
        tbOptions.add(Box.createHorizontalStrut(233));
        tbPanel3.add(tbOptions);
        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null, "Add visit");
            }
        });
        sub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null, "Remove visit");
            }
        });
        filter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null, "Filter visit");
            }
        });


    }

    public void createtbpanel2() {
        tbPanel2 = new JPanel();
        tbPanel2.setName("2");


    }

    public void createtbpanel1() {
        tbPanel1 = new JPanel();
        tbPanel1.setName("1");

    }


    private void createVisitTable() {
        table1 = new JTable();
        String column[] = {"Country", "Date", "Notes"};
    }


    public static void main(String[] args) {

        World inst = new World();
        inst.setUI();


    }


}
