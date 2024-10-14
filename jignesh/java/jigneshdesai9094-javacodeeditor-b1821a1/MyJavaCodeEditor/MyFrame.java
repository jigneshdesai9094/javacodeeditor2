import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.print.attribute.AttributeSet;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.*;

public class MyFrame extends JFrame implements KeyListener, ActionListener {
    JMenuBar jMenuBar;
    JMenu jMenu, jMenu2;
    JScrollPane scrollPane;
    JTextPane jTextArea;
    FileReader fr;
    BufferedReader br;
    String line;
    String keyword[];
    StyledDocument doc;
    SimpleAttributeSet sas;
    String Filename, directory;
    String font;
    int font_size, font_style;

    MyFrame() {
        // Menu Bar

        jMenuBar = new JMenuBar();
        jMenu = new JMenu("File");
        jMenu2 = new JMenu("Format");
        JMenu jMenu6 = new JMenu("Theme");
        JMenu jMenu7 = new JMenu("Edit");
        JMenu jMenu8 = new JMenu("Run");
        JMenuItem jMenuItem22 = new JMenuItem("CMD");
        JMenuItem jMenuItem17 = new JMenuItem("Dark");
        JMenuItem jMenuItem18 = new JMenuItem("Light");
        JMenuItem jMenuItem19 = new JMenuItem("Cut");
        JMenuItem jMenuItem20 = new JMenuItem("Copy");
        JMenuItem jMenuItem21 = new JMenuItem("Paste");

        jMenuItem17.addActionListener(this);
        jMenuItem18.addActionListener(this);
        jMenuItem19.addActionListener(this);

        jMenuItem20.addActionListener(this);
        jMenuItem21.addActionListener(this);
        jMenuItem22.addActionListener(this);
        jMenu8.add((jMenuItem22));
        jMenu6.add(jMenuItem17);
        jMenu6.add(jMenuItem18);
        jMenu7.add(jMenuItem19);
        jMenu7.add(jMenuItem20);
        jMenu7.add(jMenuItem21);

        JMenuItem jMenuItem = new JMenuItem("New");
        JMenuItem jMenuItem2 = new JMenuItem("Open");
        JMenuItem jMenuItem3 = new JMenuItem("Save");
        JMenuItem jMenuItem4 = new JMenuItem("Save As");
        JMenu jMenu3 = new JMenu("Font");
        JMenu jMenu4 = new JMenu("Font Size");
        JMenu jMenu5 = new JMenu("Style");
        JMenuItem jMenuItem5 = new JMenuItem("Arial");
        JMenuItem jMenuItem6 = new JMenuItem("Verdana");
        JMenuItem jMenuItem7 = new JMenuItem("Garamond");
        JMenuItem jMenuItem8 = new JMenuItem("Calibri");

        JMenuItem jMenuItem9 = new JMenuItem("10");
        JMenuItem jMenuItem10 = new JMenuItem("12");
        JMenuItem jMenuItem11 = new JMenuItem("15");
        JMenuItem jMenuItem12 = new JMenuItem("18");
        JMenuItem jMenuItem13 = new JMenuItem("20");

        JMenuItem jMenuItem14 = new JMenuItem("PLAIN");
        JMenuItem jMenuItem15 = new JMenuItem("BOLD");
        JMenuItem jMenuItem16 = new JMenuItem("ITALIC");
        jMenu5.add(jMenuItem14);
        jMenu5.add(jMenuItem15);
        jMenu5.add(jMenuItem16);

        jMenu3.add(jMenuItem5);
        jMenu3.add(jMenuItem6);
        jMenu3.add(jMenuItem7);
        jMenu3.add(jMenuItem8);
        jMenu4.add(jMenuItem9);
        jMenu4.add(jMenuItem10);
        jMenu4.add(jMenuItem11);
        jMenu4.add(jMenuItem12);
        jMenu4.add(jMenuItem13);
        jMenu2.add(jMenu5);
        jMenu2.add(jMenu3);
        jMenu2.add(jMenu4);

        jMenuItem.addActionListener(this);
        jMenuItem2.addActionListener(this);
        jMenuItem3.addActionListener(this);
        jMenuItem4.addActionListener(this);

        jMenuItem5.addActionListener(this);
        jMenuItem6.addActionListener(this);
        jMenuItem7.addActionListener(this);
        jMenuItem8.addActionListener(this);
        jMenuItem9.addActionListener(this);
        jMenuItem10.addActionListener(this);
        jMenuItem11.addActionListener(this);
        jMenuItem12.addActionListener(this);
        jMenuItem13.addActionListener(this);
        jMenuItem14.addActionListener(this);
        jMenuItem15.addActionListener(this);
        jMenuItem16.addActionListener(this);

        jMenu.add(jMenuItem);
        jMenu.add(jMenuItem2);
        jMenu.add(jMenuItem3);
        jMenu.add(jMenuItem4);
        jMenuBar.add(jMenu);
        jMenuBar.add(jMenu7);
        jMenuBar.add(jMenu2);
        jMenuBar.add(jMenu6);
        jMenuBar.add(jMenu8);
        
        this.setJMenuBar(jMenuBar);

        // Textarea
        font = "Arial";
        font_size = 15;
        font_style = Font.PLAIN;
        jTextArea = new JTextPane();
        this.add(jTextArea, BorderLayout.CENTER);
        jTextArea.setFont(new Font(font, font_style, font_size));
        scrollPane = new JScrollPane(jTextArea);
        this.add(scrollPane);
        jTextArea.addKeyListener(this);

        // FileReading
        try {
            fr = new FileReader("./keywords.txt");
            br = new BufferedReader(fr);
            line = br.readLine();
            String l = br.readLine();
            while (l != null) {
                l = br.readLine();
                if (l != null)
                    line = line + l;
            }
            keyword = line.split(",");
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        doc = jTextArea.getStyledDocument();
        sas = new SimpleAttributeSet();
        StyleConstants.setForeground(sas, Color.BLUE);

        Filename = "None";
        directory = "None";

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    void autoClose(String ch) {
        try {
            int pos;
            pos = jTextArea.getCaretPosition();
            doc.insertString(pos, ch, null);

            jTextArea.setCaretPosition(pos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void changeColor() {
        try {
            int pos = jTextArea.getCaretPosition();
            for (String a : keyword) {
                int beg = pos - (a.length());
                if (beg >= 0) {

                    String s = jTextArea.getText(beg, a.length());
                    if ((s.trim()).equals(a))
                        doc.setCharacterAttributes(beg, a.length(), sas, true);
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        int pos = jTextArea.getCaretPosition();
        if (key == '{') {
            try {
                doc.insertString(pos, "\n", null);
            } catch (Exception err) {
                err.printStackTrace();
            }
            autoClose("}");
            jTextArea.setCaretPosition(pos);
        } else if (key == '[')
            autoClose("]");
        else if (key == '(')
            autoClose(")");
        else if (key == '"')
            autoClose("\"");
        changeColor();

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    void saveAs() {
        FileDialog fd = new FileDialog(this, "Save", FileDialog.SAVE);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            Filename = fd.getFile();
            setTitle(Filename);
            directory = fd.getDirectory();
            try {
                PrintWriter pw = new PrintWriter(directory + Filename);
                pw.print(jTextArea.getText());
                pw.flush();
                pw.close();
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
    }

    void save() {
        if (Filename.equals("None")) {
            saveAs();
        } else {
            try {
                PrintWriter pw = new PrintWriter(directory + Filename);
                pw.print(jTextArea.getText());
                pw.flush();
                pw.close();
            } catch (Exception err) {
                err.printStackTrace();
            }
        }

    }

    void open() {
        FileDialog fd = new FileDialog(this, "Open", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            Filename = fd.getFile();
            setTitle(Filename);
            directory = fd.getDirectory();

            try {
                br = new BufferedReader(new FileReader(directory + Filename));
                String s = br.readLine();
                String content = "";
                while (s != null) {
                    content = content + s + "\n";
                    s = br.readLine();
                }
                br.close();
                jTextArea.setText(content);
                int cursor_pos = jTextArea.getCaretPosition();
                int i = 0;
                while (i <= cursor_pos) {
                    changeColor();
                    jTextArea.setCaretPosition(i);
                    i++;
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
    }

    void new_file() {
        jTextArea.setText("");
        setTitle("None");
        Filename = "None";
        directory = "None";
    }

    void run() {
        try {
            if (directory.equals("None")) {
                saveAs();
            } else {
              // Get the command processor path
                String comSpec = System.getenv("ComSpec");
                System.out.println(comSpec);
                // Create a new process builder
                ProcessBuilder builder = new ProcessBuilder(comSpec,"/c","start");


                builder.directory(new File(directory));

                System.out.println(directory);
                Process process = builder.start();
                int exitCode = process.waitFor();
                                
                System.out.println("Process exited with code: " + exitCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
           
           String click = e.getActionCommand();
           
          // JOptionPane.showMessageDialog(null,click);
           if(click.equals("New"))
              new_file();
           else if(click.equals("Open"))
               open();
           else if(click.equals("Save"))
               save();
           else if(click.equals("Save As"))
             saveAs();
           else if(click.equals("Arial"))
           {
              font=click;
              jTextArea.setFont(new Font(font,font_style,font_size));
           }
           else if(click.equals("Verdana"))
           {
              font=click;
              jTextArea.setFont(new Font(font,font_style,font_size));
           }
           else if(click.equals("Garamond"))
            {
               font=click;
               jTextArea.setFont(new Font(font,font_style,font_size));
            }
           else if(click.equals("Calibri"))
           {
              font=click;
              jTextArea.setFont(new Font(font,font_style,font_size));
           }
           else if(click.equals("10"))
           {
              font_size=10;
              jTextArea.setFont(new Font(font,font_style,font_size));
           }
           else if(click.equals("12"))
           {
              font_size=12;
              jTextArea.setFont(new Font(font,font_style,font_size));
           }
           else if(click.equals("15"))
           {
              font_size=15;
              jTextArea.setFont(new Font(font,font_style,font_size));
           }
           else if(click.equals("18"))
           {
              font_size=18;
              jTextArea.setFont(new Font(font,font_style,font_size));
           }
           else if(click.equals("20"))
           {
              font_size=20;
              jTextArea.setFont(new Font(font,font_style,font_size));
           }
           else if(click.equals("BOLD"))
           {
              font_style=Font.BOLD;

              jTextArea.setFont(new Font(font,font_style,font_size));
           }
           else if(click.equals("ITALIC"))
           {
              font_style=Font.ITALIC;
              jTextArea.setFont(new Font(font,font_style,font_size));
           }
           else if(click.equals("PLAIN"))
           {
            font_style=Font.PLAIN;
            jTextArea.setFont(new Font(font,font_style,font_size));
           }
           else if(click.equals("Dark"))
           {
            jTextArea.setBackground(Color.GRAY);
            jTextArea.setForeground(Color.WHITE);
           }
           else if(click.equals("Light"))
           {
            jTextArea.setBackground(Color.WHITE);
            jTextArea.setForeground(Color.BLACK);
           }
           else if(click.equals("Cut"))
            jTextArea.cut();
           else if(click.equals("Copy"))
            jTextArea.copy();
           else if(click.equals("Paste"))
            jTextArea.paste();
           else if(click.equals("CMD"))
            run();
    }
}
