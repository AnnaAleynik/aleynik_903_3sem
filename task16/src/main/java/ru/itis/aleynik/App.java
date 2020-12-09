package ru.itis.aleynik;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class App {

    private final Dimension statusDim = new Dimension(100, 20);
    private final Dimension functionalDim = new Dimension(300, 100);
    protected JFrame frame;
    protected Container pane;
    protected JMenuBar toolBar;
    protected JLabel status;
    protected JPanel functional;
    protected JPanel central;
    protected String[] buttonsMeaning = {
            "Add new square",
            "Rotate the square",
            "Add form"
    };
    protected boolean isPressed = false;
    protected boolean isRotatePressed = false;
    protected boolean isFormPressed = false;
    protected int angle = 0;

    protected JPanel form;
    private JLabel nameLabel;
    private JTextField nameField;
    private JPanel productFormPanel;
    private JLabel categoryLabel;
    private JTextField categoryField;
    private JButton saveButton;


    public static void main(String[] args) {
        App app = new App();
        app.initGUI();
    }

    public void initGUI() {
        frame = new JFrame("Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        addToolBar(pane);

        addFunctional(pane);

        addStatus(pane);

        addCentral(pane);

        frame.setBounds(20, 20, 800, 500);
        frame.setVisible(true);
    }

    private void addCentral(Container pane) {
        central = new Central();
        form = setForm();
        pane.add(central);
    }

    private void addToolBar(Container pane) {
        toolBar = new JMenuBar();

        JMenu file = new JMenu("File");
        toolBar.add(file);
        JMenuItem i1 = new JMenuItem("New");
        file.add(i1);
        JMenuItem i2 = new JMenuItem("Exit");
        file.add(i2);
        i2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenu settings = new JMenu("Settings");
        toolBar.add(settings);

        JMenu about = new JMenu("About");
        about.addMenuListener(new AboutListener());
//        about.addActionListener(l);

        toolBar.add(about);

        pane.add(toolBar, BorderLayout.NORTH);
    }

    private void addFunctional(Container pane) {
        functional = new JPanel();
        functional.setBackground(Color.PINK);
        setSizes(functional, functionalDim);
        functional.setLayout(new BoxLayout(functional, BoxLayout.Y_AXIS));

        JButton b1 = new JButton("add");
        JButton b2 = new JButton("rotate");
        JButton b3 = new JButton("form");

        b1.addMouseListener(new ButtonListener(buttonsMeaning[0]));
        b2.addMouseListener(new ButtonListener(buttonsMeaning[1]));
        b3.addMouseListener(new ButtonListener(buttonsMeaning[2]));

        b1.addActionListener(e -> {
            isPressed = !isPressed;
            isFormPressed = false;
            central.repaint();
        });

        b2.addActionListener(e -> {
            isRotatePressed = !isRotatePressed;
            System.out.println(isRotatePressed);
            isFormPressed = false;

//            central.repaint();
            Timer timer = new Timer(5, ev -> {
                if (angle + 1 > 90) {
                    angle = 0;
                }
                angle += 1;
                central.repaint();
                central.revalidate();
            });
            if (isRotatePressed) {
                timer.start();
            } else {
                timer.stop();
                central.repaint();
                central.revalidate();
            }

        });

        b3.addActionListener(e -> {
            isPressed = false;
            isRotatePressed = false;
            isFormPressed = !isFormPressed;
            System.out.println(isFormPressed);
            if (isFormPressed) {
                central.add(form);
            } else {
                central.remove(form);
            }
            central.repaint();
            central.revalidate();
        });

        functional.add(b1);
        functional.add(b2);
        functional.add(b3);

        pane.add(functional, BorderLayout.EAST);
    }

    private void addStatus(Container pane) {
        JPanel statusBar = new JPanel();
        statusBar.setBackground(Color.gray);
//        setSizes(statusBar, statusDim);
        statusBar.setLayout(new FlowLayout());

        status = new JLabel("Status");
        statusBar.add(status);
        pane.add(statusBar, BorderLayout.SOUTH);
    }

    private void setSizes(JPanel panel, Dimension dim) {
        panel.setMinimumSize(dim);
        panel.setPreferredSize(dim);
        panel.setMaximumSize(dim);
    }

    private JPanel setForm() {
        productFormPanel = new JPanel(new GridBagLayout());

        nameLabel = new JLabel("Name", JLabel.LEFT);
        productFormPanel.add(nameLabel, this.newLabelConstraints());

        nameField = new JTextField();
        nameLabel.setLabelFor(nameField);
        productFormPanel.add(nameField, this.newTextFieldConstraints());

        categoryLabel = new JLabel("Category", JLabel.LEFT);
        productFormPanel.add(categoryLabel, this.newLabelConstraints());

        categoryField = new JTextField();
        categoryLabel.setLabelFor(categoryField);
        productFormPanel.add(categoryField, this.newTextFieldConstraints());

        // Add a spacer to push all the form rows to the top of the window.
        GridBagConstraints spacer = new GridBagConstraints();
        spacer.fill = GridBagConstraints.BOTH;
        spacer.gridwidth = GridBagConstraints.REMAINDER;
        spacer.weighty = 1.0;
        productFormPanel.add(new JPanel(), spacer);

        saveButton = new JButton("Save");
//        saveButton.addActionListener((ActionEvent e) -> {
//            JOptionPane.showMessageDialog(mainFrame, "Data has been saved.");
//        });
        productFormPanel.add(saveButton, this.newTextFieldConstraints());

        productFormPanel.setBorder(new TitledBorder("Form"));
        productFormPanel.setBounds(50, 50, 300, 300);
        return productFormPanel;
    }

    private GridBagConstraints newConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        // a little breathing room
        c.insets = new Insets(2, 2, 2, 2);
        return c;
    }

    /**
     * Constructs constraints for labels.
     * @return Constraints for labels.
     */
    private GridBagConstraints newLabelConstraints() {
        GridBagConstraints c = this.newConstraints();
        // right-align labels
        c.anchor = GridBagConstraints.BASELINE_TRAILING;
        // do not grow labels
        c.weightx = 0.0;
        return c;
    }

    /**
     * Constructs constraints for text fields.
     * @return Constraints for text fields.
     */
    private GridBagConstraints newTextFieldConstraints() {
        GridBagConstraints c = this.newConstraints();
        c.anchor = GridBagConstraints.BASELINE;
        // grow text fields horizontally
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        // text fields end a row
        c.gridwidth = GridBagConstraints.REMAINDER;
        return c;
    }

    private class AboutListener implements MenuListener {
        JWindow w;

        @Override
        public void menuSelected(MenuEvent e) {
            System.out.println("menuSelected");
            w = new JWindow();
            JPanel p = new JPanel();
            JLabel label = new JLabel("Hello!");
            p.add(label);
            p.setBorder(BorderFactory.createLineBorder(Color.black));
            w.add(p);
            p.setBackground(Color.lightGray);

            // setsize of window
            w.setSize(200, 100);

            // set visibility of window
            w.setVisible(true);

            // set location of window
            w.setLocation(100, 100);
        }

        @Override
        public void menuDeselected(MenuEvent e) {
            System.out.println("menuDeselected");
            w.setVisible(false);
        }

        @Override
        public void menuCanceled(MenuEvent e) {
            System.out.println("menuCanceled");
        }
    }

    private class ButtonListener implements MouseListener {

        String text;

        public ButtonListener(String text) {
            super();
            this.text = text;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        //        зажали кнопку
        @Override
        public void mousePressed(MouseEvent e) {

        }

        //        Отпускаем кнопку
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            status.setText(text);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            status.setText("Status");
        }
    }


    private class Central extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (isPressed) {
                System.out.println("s");
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLUE);
                if (isRotatePressed) {
                    System.out.println("r" + angle);
//                    g2.rotate(Math.toRadians(45), this.getWidth()/2 - 20, this.getHeight()/2 - 20);
                    g2.rotate(Math.toRadians(angle), this.getWidth()/2, this.getHeight()/2);
                }
                System.out.println("a");
                g2.fillRect(this.getWidth()/2 - 20, this.getHeight()/2 - 20, 40, 40);
            }
//            if (isFormPressed) {
//                central.add(form);
//            }
        }
/*
        private void rotate() {
            double rotationRequired = Math.toRadians (45);
            double locationX = image.getWidth() / 2;
            double locationY = image.getHeight() / 2;
            AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        }

 */
    }
}
