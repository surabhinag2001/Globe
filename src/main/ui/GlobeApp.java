package ui;


import exceptions.*;
import model.AllCountries;
import model.VisitedCountry;
import model.VisitedList;
import model.WishList;
import persistence.JsonVisitedListReader;
import persistence.JsonVisitedListWriter;
import persistence.JsonWishListReader;
import persistence.JsonWishListWriter;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.lang.*;


//represents the Globe application
public class GlobeApp {
    private static final String JSON_STORE_WISHLIST = "./data/wishlist.json";
    private static final String JSON_STORE_VISITED_LIST = "./data/visitedlist.json";
    private WishList wishCountries;
    private VisitedList visitedCountries;
    private final Scanner input = new Scanner(System.in);
    private final JsonWishListWriter jsonWishListWriter;
    private final JsonWishListReader jsonWishListReader;
    private final JsonVisitedListWriter jsonVisitedListWriter;
    private final JsonVisitedListReader jsonVisitedListReader;

    private JButton bt1; //all button
    private JButton bt2; //wishlist button
    private JButton bt3; //visited button
    private JPanel tbPanel1; //all panel
    private JPanel tbPanel2; //wishlist panel
    private JPanel tbPanel3; //visited panel
    private JTable table3; //visit table
    private DefaultTableModel tableModel3; //visit table model
    private JTable table2; //wishlist table
    private DefaultTableModel tableModel2; //wishlist table model
    private JTable table1; //all table
    private DefaultTableModel tableModel1; //all table model
    private JTable table4; //filtered table
    private DefaultTableModel tableModel4; //filtered table model
    private JScrollPane sp1;
    private JScrollPane sp2;
    private JScrollPane sp3;
    private JScrollPane sp4;
    private JFrame frame;
    private JPanel mid;
    private JPanel mainLayout;
    private JPanel topbar;
    private JPanel opts;
    private JTextField textField;
    private ImageIcon crossIc;
    private JLabel cross;
    private List<VisitedCountry> filterList;
    private JLabel filter;
    private ImageIcon filterIc;
    private JLabel sub;
    private ImageIcon subIc;
    private JLabel add;
    private ImageIcon addIc;
    private GlobeApp gb;
    private String query;


    //EFFECTS: runs the globe application
    public GlobeApp() {
        jsonWishListWriter = new JsonWishListWriter(JSON_STORE_WISHLIST);
        jsonWishListReader = new JsonWishListReader(JSON_STORE_WISHLIST);
        jsonVisitedListWriter = new JsonVisitedListWriter(JSON_STORE_VISITED_LIST);
        jsonVisitedListReader = new JsonVisitedListReader(JSON_STORE_VISITED_LIST);
//        runGlobe();
        setUI();
    }

    //EFFECTS: sets the UI for the entire application
    public void setUI() {
        gb = this;
        setParentFrameUI();

        mainLayout = new JPanel();
        mainLayout.setLayout(new BoxLayout(mainLayout, BoxLayout.Y_AXIS));
        mainLayout.setBackground(null);

        setTopbar();

        mainLayout.add(topbar);

        setMidPanel();

        setOptionsPanel();

        mid.add(opts);
        mid.add(Box.createHorizontalStrut(13));

        createtbPanel3();
        mid.add(tbPanel3);


        mainLayout.add(mid);
        mainLayout.add(Box.createVerticalStrut(25));


        frame.add(mainLayout, BorderLayout.NORTH);
        frame.setTitle("Globe");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.requestFocusInWindow();

    }

    //EFFECTS: sets UI for parent frame
    private void setParentFrameUI() {
        frame = new JFrame(("World"));
        frame.getContentPane().setBackground(new Color(248, 248, 251));
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
    }

    //EFFECTS: adds buttons on the left of the screen and sets their UI
    private void setOptionsPanel() {
        opts = new JPanel();
        opts.setBackground(null);
        opts.setLayout(new BoxLayout(opts, BoxLayout.Y_AXIS));

        bt1 = new JButton("World List");
        bt2 = new JButton("Wishlist");
        bt3 = new JButton("Visits List");
        setButton(bt1);
        setButton(bt2);
        setButton(bt3);
        bt3.setForeground(new Color(247, 37, 133));
        performBt1Clicked();
        performBt2Clicked();
        performBt3Clicked();

        opts.add(Box.createRigidArea(new Dimension(0, 50)));
        opts.add(bt1);
        opts.add(bt2);
        opts.add(bt3);
        opts.add(Box.createRigidArea(new Dimension(0, 80)));
    }

    //EFFECTS: sets UI if the mid panel
    private void setMidPanel() {
        mid = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mid.setBackground(null);
        mid.add(Box.createHorizontalStrut(10));
    }

    //MODIFIES: this
    //EFFECTS: changes color of bt3 to pink and removes panel other than tbPanel3 and shows tbPanel3
    private void performBt3Clicked() {
        bt3.addActionListener(e -> {
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
        });
    }

    //EFFECTS: returns integer value depending on what panel
    private int detectPanel() {
        if (mid.getComponent(3).getName().equals("1")) {
            return 1;
        } else if (mid.getComponent(3).getName().equals("2")) {
            return 2;
        } else if (mid.getComponent(3).getName().equals("3")) {
            return 3;
        } else {
            return 3;
        }
    }

    //MODIFIES: this
    //EFFECTS: changes color of bt2 to pink and removes panel other than tbPanel2 and shows tbPanel2
    private void performBt2Clicked() {
        bt2.addActionListener(e -> {
            bt2.setForeground(new Color(247, 37, 133));
            bt1.setForeground(Color.BLACK);
            bt3.setForeground(Color.BLACK);

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
        });
    }

    //MODIFIES: this
    //EFFECTS: changes color of bt1 to pink and removes panel other than tbPanel1 and shows tbPanel1
    private void performBt1Clicked() {
        bt1.addActionListener(e -> {
            bt1.setForeground(new Color(247, 37, 133));
            bt2.setForeground(Color.BLACK);
            bt3.setForeground(Color.BLACK);
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
        });
    }

    //EFFECTS: sets UI of the buttons
    private void setButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Nunito", Font.PLAIN, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    //EFFECTS: sets UI of the top bar
    private void setTopbar() {
        topbar = new JPanel();
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

        setSearchBar();
        topbar.add(textField);
    }

    //EFFECTS: sets the UI of the search bar and implements functionality related typing in it
    private void setSearchBar() {
        textField = new JTextFieldHintUI("Search");
        textField.setBorder(null);
        textField.setMargin(new Insets(5, 50, 5, 10));
        textField.setPreferredSize(new Dimension(500, 30));
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                query = textField.getText().toUpperCase();
                if (detectPanel() == 1) {
                    searchCountry(query);
                } else if (detectPanel() == 2) {
                    searchWish(query);
                } else if (detectPanel() == 3) {
                    searchVisit(query);
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: sets the UI and layout of the panel displaying visits table
    public void createtbPanel3() {
        setTbPanel3();

        JPanel tbOptions = new JPanel();
        tbOptions.setBackground(null);
        tbOptions.add(Box.createHorizontalStrut(5));

        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(248, 248, 251));
        tbPanel3.add(emptyPanel);
        tbPanel3.add(Box.createVerticalStrut(5));

        JLabel visitList = new JLabel();
        visitList.setText("Visited Places");
        visitList.setFont(new Font("Nunito", Font.BOLD, 24));
        visitList.setForeground(new Color(247, 37, 133));
        tbOptions.add(visitList);

        setTbPanel3Buttons(tbOptions);

        tbOptions.add(Box.createHorizontalStrut(160));
        tbPanel3.add(tbOptions);
        tbPanel3.add(Box.createVerticalStrut(5));
        //add table
        createVisitTable();
        tbPanel3.add(sp3);

        addVisitsMouseListener();
        subVisitsMouseListener();
        filterVisitsMouseListener(tbOptions);
        crossMouseListener(tbOptions);
    }

    private void crossMouseListener(JPanel tbOptions) {
        cross.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setCrossIcon("images/crossD.png");
                tbOptions.revalidate();
                tbOptions.repaint();

                tbPanel3.remove(tbPanel3.getComponent(4));
                createVisitTable();
                tbPanel3.add(sp3);
                tbPanel3.revalidate();
                tbPanel3.repaint();
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: changes the cross icon from grey to pink by changing the source of the image
    private void filterVisitsMouseListener(JPanel tbOptions) {
        filter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setCrossIcon("images/crossE.png");
                tbOptions.revalidate();
                tbOptions.repaint();
                FilterListDialog fd = new FilterListDialog(frame, gb);
                fd.display();
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: deletes the selected visit otherwise plays an error sound
    private void subVisitsMouseListener() {
        sub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = getSeletedRowFromTable('v');
                if (index != -1) {
                    DeleteVisitedListDialogBox db = new DeleteVisitedListDialogBox(frame, gb);
                    db.display();
                } else {
                    playSound();
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: adds a visit to the visited list
    private void addVisitsMouseListener() {
        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AddToVisitsDialogBox av = new AddToVisitsDialogBox(frame, gb);
                av.display();
            }
        });
    }

    //EFFECTS: adds buttons to the top of visited list panel
    private void setTbPanel3Buttons(JPanel tbOptions) {
        setTbPanel2Buttons(tbOptions);

        filter = new JLabel();
        setFilterIcon();
        tbOptions.add(Box.createHorizontalStrut(1));
        tbOptions.add(filter);

        cross = new JLabel();
        setCrossIcon("images/crossD.png");
        tbOptions.add(Box.createHorizontalStrut(1));
        tbOptions.add(cross);
    }

    //EFFECTS: the layout for UI of tbPanel3
    private void setTbPanel3() {
        tbPanel3 = new JPanel();
        tbPanel3.setName("3");
        tbPanel3.setBackground(Color.WHITE);
        tbPanel3.setLayout(new BoxLayout(tbPanel3, BoxLayout.Y_AXIS));
    }

    //MODIFIES: addIc
    //EFFECTS: resized the add icon
    private void setAddIcon() {
        addIc = new ImageIcon("images/addicon.png");
        Image image = addIc.getImage(); // transform it
        Image newimg = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        addIc = new ImageIcon(newimg);
        add.setIcon(addIc);
    }

    //MODIFIES: addIc
    //EFFECTS: resized the sub icon
    private void setSubIcon() {
        subIc = new ImageIcon("images/removeicon.png");
        Image remimage = subIc.getImage(); // transform it
        Image newimgRem = remimage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        subIc = new ImageIcon(newimgRem);
        sub.setIcon(subIc);
    }

    //MODIFIES: addIc
    //EFFECTS: resized the filter icon
    private void setFilterIcon() {
        filterIc = new ImageIcon("images/filtericon.png");
        Image filimg = filterIc.getImage(); // transform it
        Image newfilimg = filimg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        filterIc = new ImageIcon(newfilimg);
        filter.setIcon(filterIc);
    }

    //MODIFIES: addIc
    //EFFECTS: resized the cross icon
    public void setCrossIcon(String source) {
        crossIc = new ImageIcon(source);
        Image crossimg = crossIc.getImage(); // transform it
        Image newcrossimg = crossimg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        crossIc = new ImageIcon(newcrossimg);
        cross.setIcon(crossIc);
    }

    //getter method
    public JPanel getTbPanel3() {
        return tbPanel3;
    }

    //getter method
    public JScrollPane getSp4() {
        return sp4;
    }

    //getter method
    public JScrollPane getSp3() {
        return sp3;
    }

    //EFFECTS: searches query in table3
    private void searchVisit(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(tableModel3);
        table3.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }

    //EFFECTS: searches query in table2
    private void searchWish(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(tableModel2);
        table2.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }

    //EFFECTS: searches query in table1
    private void searchCountry(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(tableModel1);
        table1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }

    //MODIFIES: this
    //EFFECTS: sets the UI and layout of the panel displaying visits
    public void createtbpanel2() {
        setTbPanel2();

        JPanel tbOptions = new JPanel();
        tbOptions.setBackground(null);
        tbOptions.add(Box.createHorizontalStrut(5));


        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(248, 248, 251));
        tbPanel2.add(emptyPanel);
        tbPanel2.add(Box.createVerticalStrut(5));

        JLabel wishList = new JLabel();
        wishList.setText("Wishlist");
        wishList.setFont(new Font("Nunito", Font.BOLD, 24));
        wishList.setForeground(new Color(247, 37, 133));
        tbOptions.add(wishList);

        setTbPanel2Buttons(tbOptions);

        tbOptions.add(Box.createHorizontalStrut(305));

        tbPanel2.add(tbOptions);
        //add table
        createWishlistTable();
        tbPanel2.add(sp2);
        addWishlistMouseListener();
        subWishlistMouseListener();
    }

    //MODIFIES: this
    //EFFECTS: deletes the selected country otherwise plays an error sound
    private void subWishlistMouseListener() {
        sub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = getSeletedRowFromTable('w');
                if (index != -1) {
                    DeleteWishListDialogBox db = new DeleteWishListDialogBox(frame, gb);
                    db.display();
                } else {
                    playSound();
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: adds a country to the wishlist
    private void addWishlistMouseListener() {
        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AddToWishlistDialogBox wl = new AddToWishlistDialogBox(frame, gb);
                wl.display();
            }
        });
    }

    //EFFECTS: adds buttons to the top of wishlist panel
    private void setTbPanel2Buttons(JPanel tbOptions) {
        add = new JLabel();
        setAddIcon();
        tbOptions.add(Box.createHorizontalStrut(10));
        tbOptions.add(add);

        sub = new JLabel();
        setSubIcon();
        tbOptions.add(Box.createHorizontalStrut(1));
        tbOptions.add(sub);
    }

    //EFFECTS: the layout for UI of tbPanel2
    private void setTbPanel2() {
        tbPanel2 = new JPanel();
        tbPanel2.setName("2");
        tbPanel2.setBackground(Color.WHITE);
        tbPanel2.setLayout(new BoxLayout(tbPanel2, BoxLayout.Y_AXIS));
    }

    //MODIFIES: this
    //EFFECTS: sets the UI of the panel displaying all countries
    public void createtbpanel1() {
        setTbPanel1();

        JPanel tbOptions = new JPanel();
        tbOptions.setBackground(null);
        tbOptions.add(Box.createHorizontalStrut(5));

        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(248, 248, 251));

        tbPanel1.add(emptyPanel);
        tbPanel1.add(Box.createVerticalStrut(5));

        JLabel allCountries = new JLabel();
        allCountries.setText("All Countries");
        allCountries.setFont(new Font("Nunito", Font.BOLD, 24));
        allCountries.setForeground(new Color(247, 37, 133));
        tbOptions.add(allCountries);

        tbOptions.add(Box.createHorizontalStrut(323));
        tbPanel1.add(tbOptions);

        tbPanel1.add(Box.createVerticalStrut(5));

        createWorldTable();
        tbPanel1.add(sp1);
    }

    //EFFECTS: the layout for UI of tbPanel1
    private void setTbPanel1() {
        tbPanel1 = new JPanel();
        tbPanel1.setName("1");
        tbPanel1.setBackground(Color.WHITE);
        tbPanel1.setLayout(new BoxLayout(tbPanel1, BoxLayout.Y_AXIS));
    }

    //EFFECTS: creates a JTable displaying visited countries
    private void createVisitTable() {
        loadVisitedList();
        String[] column = {"Country", "Date", "Notes"};
        String[][] data = new String[visitedCountries.getMyVisitedList().size()][3];
        for (int i = 0; i < visitedCountries.getMyVisitedList().size(); i++) {
            data[i][0] = visitedCountries.getMyVisitedList().get(i).getCountryName();
            data[i][1] = visitedCountries.getMyVisitedList().get(i).getDateVisited().toString();
            data[i][2] = visitedCountries.getMyVisitedList().get(i).getNotesCountry();
        }
        tableModel3 = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table3 = new JTable(tableModel3);
        setTableDisplay(table3);

        sp3 = new JScrollPane(table3);
        sp3.setName("sp3");
        sp3.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 10, Color.white));
        sp3.setBackground(new Color(248, 248, 251));
    }

    //EFFECTS: creates a JTable displaying visits between min and max dates if min<max else
    //throws MaxDateBeforeMinDateException, NullFieldsException is thrown when any field is empty
    public void createFilterTable(String minDate, String maxDate) throws MaxDateBeforeMinDateException,
            NullFieldsException {
        checkFilterDateFields(minDate, maxDate);
        filterVisits(minDate, maxDate);
        System.out.println(filterList.size());
        String[] column = {"Country", "Date", "Notes"};
        String[][] data = new String[filterList.size()][3];
        for (int i = 0; i < filterList.size(); i++) {
            data[i][0] = filterList.get(i).getCountryName();
            data[i][1] = filterList.get(i).getDateVisited().toString();
            data[i][2] = filterList.get(i).getNotesCountry();
        }
        tableModel4 = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table4 = new JTable(tableModel4);
        setTableDisplay(table4);

        sp4 = new JScrollPane(table4);
        sp4.setName("sp4");
        sp4.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 10, Color.white));
        sp4.setBackground(new Color(248, 248, 251));
    }

    //EFFECTS: throws NullFieldsException exception if minDate or maxDate have not been entered by the user
    private void checkFilterDateFields(String minDate, String maxDate) throws NullFieldsException {
        System.out.println(minDate);
        if (minDate.equals("yyyy-mm-dd") || maxDate.equals("yyyy-mm-dd")) {
            throw new NullFieldsException();
        }
    }

    //EFFECTS: converts string dates to locale dates and initialises filterList with the countries between min
    //and max, throws MaxDateBeforeMinDateException if min date is after max date
    private void filterVisits(String minDate, String maxDate) throws MaxDateBeforeMinDateException {
        int y1 = Integer.parseInt(minDate.substring(0, 4));
        int m1 = Integer.parseInt(minDate.substring(5, 7));
        int d1 = Integer.parseInt(minDate.substring(8));
        LocalDate date1 = inputDate(y1, m1, d1);

        int y2 = Integer.parseInt(maxDate.substring(0, 4));
        int m2 = Integer.parseInt(maxDate.substring(5, 7));
        int d2 = Integer.parseInt(maxDate.substring(8));
        LocalDate date2 = inputDate(y2, m2, d2);

        filterList = visitedCountries.filterDateWise(date1, date2);
    }

    //EFFECTS: plays error sound throws exception if needed
    private void playSoundWithErrors() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File f = new File("./sounds/error.wav");
        AudioInputStream audio = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
    }

    //EFFECTS: plays error sound
    public void playSound() {
        try {
            playSoundWithErrors();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: creates a JTable displaying the wishlist
    public void createWishlistTable() {
        loadWishList();
        String[] column = {"Country", "Notes"};
        String[][] data = new String[wishCountries.getMyWishList().size()][3];
        for (int i = 0; i < wishCountries.getMyWishList().size(); i++) {
            data[i][0] = wishCountries.getMyWishList().get(i).getCountryName();
            data[i][1] = wishCountries.getMyWishList().get(i).getNotesCountry();
        }
        tableModel2 = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table2 = new JTable(tableModel2);
        setTableDisplay(table2);

        sp2 = new JScrollPane(table2);
        sp2.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 10, Color.white));
        sp2.setBackground(new Color(248, 248, 251));

    }

    //EFFECTS: creates a JTable displaying all countries
    private void createWorldTable() {
        String[] column = {"Country"};
        AllCountries obj = new AllCountries();
        String[][] data = new String[obj.getAllCountries().size()][1];
        for (int i = 0; i < obj.getAllCountries().size(); i++) {
            data[i][0] = obj.getAllCountries().get(i);
        }
        tableModel1 = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1 = new JTable(tableModel1);
        setTableDisplay(table1);

        sp1 = new JScrollPane(table1);
        sp1.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 10, Color.white));
        sp1.setBackground(new Color(248, 248, 251));
    }

    //EFFECTS: sets the layout and UI of table
    public void setTableDisplay(JTable table) {
        table.setBounds(30, 40, 200, 300);
        table.setFont(new Font("Nunito", Font.PLAIN, 12));
        table.setForeground(new Color(106, 102, 102));
        table.setRowHeight(35);
        table.setBorder(BorderFactory.createEmptyBorder());
        table.setSelectionBackground(new Color(248, 248, 251));
        table.setSelectionForeground(new Color(106, 102, 102));
        table.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        table.setFocusable(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setCellAlignment(table);
    }

    //EFFECTS: sets the layout and UI of each cell of table
    private void setCellAlignment(JTable table) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    //MODIFIES: this
    //EFFECTS: deletes the selected row from visit list or wishlist depending on c
    public void deleteSelectedRow(char c) {
        int index;
        if (c == 'v') {
            index = table3.getSelectedRow();
            if (index != -1) {
                tableModel3.removeRow(table3.getSelectedRow());
                loadVisitedList();
                visitedCountries.removeVisitByIndex(index);
                saveVisitedList();
            }
        }
        if (c == 'w') {
            index = table2.getSelectedRow();
            if (index != -1) {
                tableModel2.removeRow(table2.getSelectedRow());
                loadWishList();
                wishCountries.removeCountryByIndex(index);
                saveWishList();
            }
        }
    }

    //EFFECTS: returns the index of the selected row from either visited list or wishlist depending on c
    public int getSeletedRowFromTable(char c) {
        if (c == 'v') {
            return table3.getSelectedRow();
        } else if (c == 'w') {
            return table2.getSelectedRow();
        } else {
            return -1;
        }
    }

    //EFFECTS: throws NullFieldsException if user has not entered data in an of the fields while adding a country to
    //the wishlist
    private void checkAddWishlistField(String name, String notes) throws NullFieldsException {
        if (name.equals("Enter country") || notes.equals("Enter notes")) {
            throw new NullFieldsException();
        }
    }

    //MODIFIES: this
    //EFFECT : adds a country to the wishlist,saves and loads wishlist
    public void addToWishlist(String name, String notes) throws CountryAlreadyPresentException,
            InvalidCountryException, NullFieldsException {
        checkAddWishlistField(name, notes);
        wishCountries.addCountry(name, notes);
        saveWishList();
        loadWishList();
    }

    //MODIFIES: this
    //EFFECT : adds a country to the wishlist
    private void addToWishlist() {
        System.out.println("add to wishlist accessed");
        System.out.print("Enter country name:");
        input.nextLine();
        String country = (input.nextLine().trim()).toUpperCase();
        System.out.print("Enter notes: ");
        String notes = input.nextLine().trim();
        try {
            wishCountries.addCountry(country, notes);
            System.out.println(country + " added to the wishlist with notes : " + notes);
        } catch (InvalidCountryException e) {
            System.out.println("Country entered does not exist");
        } catch (CountryAlreadyPresentException e) {
            System.out.println(country + " is already present in the wishlist");
        }
    }

    //EFFECTS: throws NullFieldsException if user has not entered data in an of the fields while adding a country to
    //the visited list
    private void checkAddVisitField(String name, String date, String notes) throws NullFieldsException {
        if (name.equals("Enter country") || date.equals("yyyy-mm-dd") || notes.equals("Enter notes")) {
            throw new NullFieldsException();
        }
    }


    //REQUIRES : date is entered in yyyy-mm-dd format, is valid and is not in future
    //MODIFIES: this
    //EFFECT : adds a country to the visited list
    public void addToVisitedList(String name, String dateString, String notes) throws CountryAlreadyPresentException,
            InvalidCountryException, FutureDateException, NullFieldsException {
        checkAddVisitField(name, dateString, name);
        int y = Integer.parseInt(dateString.substring(0, 4));
        int m = Integer.parseInt(dateString.substring(5, 7));
        int d = Integer.parseInt(dateString.substring(8));
        LocalDate date = inputDate(y, m, d);
        visitedCountries.addCountry(name, notes, date);
        saveVisitedList();
        loadVisitedList();
    }


    //REQUIRES : date is entered in yyyy-mm-dd format, is valid and is not in future
    //MODIFIES: this
    //EFFECT : adds a country to the visited list
    private void addToVisitedList() {
        System.out.println("add to visited list accessed");
        System.out.print("Enter country name:");
        input.nextLine();
        String country = (input.nextLine().trim()).toUpperCase();
        System.out.print("Enter notes: ");
        String notes = input.nextLine().trim();
        System.out.println("Enter date visited");
        LocalDate date = inputDate();
        try {
            visitedCountries.addCountry(country, notes, date);
            System.out.println(country + " added to the visited with notes : " + notes + " date :" + date);
        } catch (InvalidCountryException e) {
            System.out.println("Country entered is invalid");
        } catch (FutureDateException e) {
            System.out.println("Date of visit cannot be in future");
        } catch (CountryAlreadyPresentException e) {
            System.out.println(country + " is already present in the list of visits");
        }
    }

    //getter method
    public DefaultTableModel getTableModel2() {
        return tableModel2;
    }

    //getter method
    public DefaultTableModel getTableModel3() {
        return tableModel3;
    }

    //EFFECT: function to input dates
    public LocalDate inputDate(int y, int m, int d) {
        return LocalDate.of(y, m, d);
    }

    //EFFECT: function to input dates
    private LocalDate inputDate() {
        System.out.print("Year: ");
        int y = input.nextInt();
        System.out.print("Month: ");
        int m = input.nextInt();
        System.out.print("Day: ");
        int d = input.nextInt();
        return LocalDate.of(y, m, d);
    }

    //most of the fucntions beyond this point are for console based UI

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGlobe() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if ("q".equals(command)) {
                keepGoing = false;
            } else if ("a".equals(command)) {
                displayAllCountries();
            } else if ("w".equals(command)) {
                handleWishlist();
            } else if ("v".equals(command)) {
                handleVisitedList();
            } else {
                System.out.println("Invalid selection");
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user input for visited list
    private void handleVisitedList() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayVisitedMenu();
            command = input.next();
            loadVisitedList();

            if (command.equalsIgnoreCase("q")) {
                keepGoing = false;
            } else if (command.equalsIgnoreCase("a")) {
                handleAddToVisited();
            } else if (command.equalsIgnoreCase("r")) {
                handleRemoveFromVisited();
            } else if (command.equalsIgnoreCase("v")) {
                viewVisitedList();
            } else if (command.equalsIgnoreCase("f")) {
                filterWithException();
            } else {
                System.out.println("Invalid selection");
                keepGoing = false;
            }
        }
    }

    //EFFECT: executes addToVisitedList(); handling the DateTimeException
    private void handleAddToVisited() {
        try {
            addToVisitedList();
            saveVisitedList();
        } catch (DateTimeException e) {
            System.out.println("Invalid date");
        }
    }

    //EFFECT: executes removeFromVisitedList(); handling the DateTimeException
    private void handleRemoveFromVisited() {
        try {
            removeFromVisitedList();
            saveVisitedList();
        } catch (DateTimeException e) {
            System.out.println("Invalid date");
        }
    }

    //EFFECT: executes filter(); handling the DateTimeException
    private void filterWithException() {
        try {
            filter();
        } catch (DateTimeException e) {
            System.out.println("Invalid date");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the visited list from file
    private void loadVisitedList() {
        try {
            visitedCountries = jsonVisitedListReader.read();
            System.out.println("Loaded Visited list from " + JSON_STORE_VISITED_LIST);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_VISITED_LIST);
        } catch (InvalidCountryException e) {
            System.out.println("Country entered is invalid");
        } catch (FutureDateException e) {
            System.out.println("Date entered cannot be in future");
        } catch (CountryAlreadyPresentException e) {
            System.out.println("Same visit cannot be added more than once");
        }
    }

    // EFFECTS: saves the visited list to file
    private void saveVisitedList() {
        try {
            jsonVisitedListWriter.open();
            jsonVisitedListWriter.write(visitedCountries);
            jsonVisitedListWriter.close();
            System.out.println("Saved Visited list to " + JSON_STORE_VISITED_LIST);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_VISITED_LIST);
        }
    }


    //REQUIRES: dates are given in yyyy-mm-dd format and are valid
    //EFFECT: displays countries that lie between specified dates
    private void filter() {
        System.out.println("Enter lower limit date");
        LocalDate minDate = inputDate();

        System.out.println("Enter upper limit date");
        LocalDate maxDate = inputDate();
        int size = 0;
        try {
            size = visitedCountries.filterDateWise(minDate, maxDate).size();
            System.out.println("Countries visited between " + minDate + "and" + maxDate + " :");
        } catch (MaxDateBeforeMinDateException e) {
            System.out.println("Minimum date " + minDate + " lies after maximum date " + maxDate);
        }
        for (int j = 0; j < size; j++) {
            try {
                System.out.println(visitedCountries.filterDateWise(minDate, maxDate).get(j).getCountryName());
                System.out.println(visitedCountries.filterDateWise(minDate, maxDate).get(j).getDateVisited());
            } catch (MaxDateBeforeMinDateException e) {
                System.out.println("Minimum date " + minDate + " lies after maximum date " + maxDate);
            }
            System.out.println();
        }
    }


    //EFFECTS : displays list of all visited countries
    private void viewVisitedList() {
        for (int i = 0; i < visitedCountries.getMyVisitedList().size(); i++) {
            System.out.println(visitedCountries.getMyVisitedList().get(i).getCountryName());
            System.out.println(visitedCountries.getMyVisitedList().get(i).getDateVisited());
            System.out.println(visitedCountries.getMyVisitedList().get(i).getNotesCountry());
            System.out.println();
        }
    }


    //MODIFIES: this
    //EFFECT : removes country from the visited list
    private void removeFromVisitedList() {
        System.out.println("remove from visited list accessed");
        System.out.print("Enter country name:");
        input.nextLine();
        String country = (input.nextLine().trim()).toUpperCase();
        System.out.println("Enter date visited");
        LocalDate date = inputDate();
        try {
            visitedCountries.removeCountry(country, date);
            System.out.println(country + " visited on " + date + " removed from the visited list");
        } catch (CountryNotPresentInListException e) {
            System.out.println(country + " visited on " + date + " is not present in the list of visits");
        }
    }


    // MODIFIES: this
    // EFFECTS: processes user input for wish list
    private void handleWishlist() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayWishlistMenu();
            command = input.next();
            loadWishList();

            if (command.equalsIgnoreCase("q")) {
                keepGoing = false;
            } else if (command.equalsIgnoreCase("a")) {
                addToWishlist();
                saveWishList();
            } else if (command.equalsIgnoreCase("r")) {
                removeFromWishList();
                saveWishList();
            } else if (command.equalsIgnoreCase("v")) {
                viewWishList();
            } else {
                System.out.println("Invalid selection");
                keepGoing = false;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the wishlist from file
    private void loadWishList() {
        try {
            wishCountries = jsonWishListReader.read();
            System.out.println("Loaded WishList from " + JSON_STORE_WISHLIST);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_WISHLIST);
        } catch (InvalidCountryException e) {
            System.out.println("Country entered is invalid");
        } catch (CountryAlreadyPresentException e) {
            System.out.println("Country entered is already present in the wishlist");
        }
    }

    // EFFECTS: saves the wishlist to file
    private void saveWishList() {
        try {
            jsonWishListWriter.open();
            jsonWishListWriter.write(wishCountries);
            jsonWishListWriter.close();
            System.out.println("Saved WishList to " + JSON_STORE_WISHLIST);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_WISHLIST);
        }
    }

    //EFFECTS: displays wishlist
    private void viewWishList() {
        for (int i = 0; i < wishCountries.getMyWishList().size(); i++) {
            System.out.println(wishCountries.getMyWishList().get(i).getCountryName());
            System.out.println();
        }
    }


    //REQUIRES : country being removes is present in the wishlist
    //MODIFIES: this
    //EFFECT : removes country from the wishlist
    private void removeFromWishList() {
        System.out.println("remove from wishlist accessed");
        System.out.print("Enter country name:");
        input.nextLine();
        String country = (input.nextLine().trim()).toUpperCase();
        try {
            wishCountries.removeCountry(country);
            System.out.println(country + " removed from the wishlist");
        } catch (CountryNotPresentInListException e) {
            System.out.println(country + " is not present in the wishlist");
        }
    }


    //MODIFIES: this
    //EFFECTS: initializes wishlist and visited lists
    public void init() {
        wishCountries = new WishList();
        visitedCountries = new VisitedList();
    }


    // EFFECTS: displays wishlist menu of options to user
    private void displayWishlistMenu() {
        System.out.println("wishlist menu displayed");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add country to wishlist");
        System.out.println("\tr -> Remove country from a wishlist ");
        System.out.println("\tv -> See names all countries in wishlist");
        System.out.println("\tq -> Quit Wishlist Menu");
    }

    // EFFECTS: displays visited menu of options to user
    private void displayVisitedMenu() {
        System.out.println("visited menu displayed");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add country to visited menu");
        System.out.println("\tr -> Remove a country from the visited menu");
        System.out.println("\tv -> See names and date of visit of all countries visited");
        System.out.println("\tf -> See names and date of visit of countries visited within specified dates");
        System.out.println("\tq -> Quit visited Menu");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> View all countries of the world");
        System.out.println("\tw -> View wishlist menu");
        System.out.println("\tv -> View visited menu");
        System.out.println("\tq -> Quit main menu");
    }

    // EFFECTS: prints all countries of the world
    private void displayAllCountries() {
        AllCountries obj = new AllCountries();
        for (String country : obj.getAllCountries()) {
            System.out.println(country);
        }
    }

}
