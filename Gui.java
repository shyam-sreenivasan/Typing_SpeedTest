import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.text.*;
import java.util.Scanner;

public class Gui{
  long sec = 60;
  long min = 0;
  JFrame frame = null;
  JPanel panel = null;
  JTextArea txtarea = null;
  JTextField texttime = null;
  JTextField text = null;
  JTextField textcpm = null;
  JTextField textwpm = null;
  JScrollPane scroll = null;
  String sentence =  "";
  public Gui() throws Exception{
      String str = "";
       frame = new JFrame("Test your speed");
       panel =  new JPanel(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      JLabel label = new JLabel("Sample text");
      c.gridx = 0;
      c.gridy = 0;
      panel.add(label,c);
       txtarea = new JTextArea(5,14);
      txtarea.setEditable(false);
      txtarea.setLineWrap(true);
      txtarea.setWrapStyleWord(true);
      // txtarea.setText(string);
      scroll = new JScrollPane(txtarea);
      c.gridx = 0;
      c.gridy = 5;
      panel.add(scroll,c);
      JLabel label1 = new JLabel("Type here");
      c.gridx = 0;
      c.gridy = 15;
      panel.add(label1,c);
       text = new JTextField(12);
      c.gridx = 0;
      c.gridy = 20;
      panel.add(text,c);
      JLabel labelcpm = new JLabel("CPM");
      c.gridx = 25;
      c.gridy = 0;
      panel.add(labelcpm,c);
       textcpm = new JTextField(3);
      c.gridx = 30;
      c.gridy =0;
      panel.add(textcpm,c);
      JLabel labelwpm = new JLabel("WPM");
      c.gridx = 25;
      c.gridy = 5;
      panel.add(labelwpm,c);
       textwpm = new JTextField(3);
      c.gridx = 30;
      c.gridy =5;
      panel.add(textwpm,c);
      JLabel labeltime = new JLabel("Time");
      c.gridx = 25;
      c.gridy = 10;
      panel.add(labeltime,c);
       texttime = new JTextField(5);
      c.gridx = 30;
      c.gridy = 10;
      panel.add(texttime,c);
      text.grabFocus();
      textcpm.setText("0");
      textwpm.setText("0");
      texttime.setText("1:00");
      textcpm.setEditable(false);
      textwpm.setEditable(false);
      texttime.setEditable(false);
      text.setHorizontalAlignment(JTextField.CENTER);
      scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      frame.getContentPane().add(panel,new BorderLayout().CENTER);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      frame.setResizable(false);
      frame.setVisible(true);
      frame.setSize(400,200);

      panel.setBackground(Color.gray);
      // text.addKeyListener(this);
      Font font = new Font("Verdana",Font.PLAIN,13);
      // font.setSize(14);
      str = getString(60);
      txtarea.setText(str);
      txtarea.setFont(font);
      txtarea.setBounds(2, 0, 700, 600);
    }



public String getString(int n) throws Exception{
  // File f = new File("test");
  int i=0;
  String f = "testing file now  hey shyam here  how are your  question is clear  its okay dear  happen that i think  problem cause king infinite deteriorate  checking my life";
  f += " instead random experience ";
  f+=  "power print person caution speed ";
  f+="as the travel terrace alphabet ";
  f += "ball date dread respect ";
  f += "responsible sat has ";
  f +="next face joke tiger ";
  f+="cloud river music master ";
  f += "noon never awesome drink water ";
  String st = "";
  String str = "";
  int len = 0;
  int sum = 16;

  while(i<n){

      double rand = Math.random();
      rand = rand*60;
      rand = Math.round(rand);
      Scanner sc = new Scanner(f);
      double j=0;
      // System.out.println(rand);
      while(j<rand){
          if(sc.hasNext()){
            st = sc.next();
            j++;
          }else{
            st = "";
            break;
          }

      }
      if(st.length() <= sum){
          sum -=st.length();
          str += st;
          if(sum <= 0){
            str += "\n";
            sum = 16;
          }else{
            str += " ";
          }
          i++;
      }

  }
  // System.out.println(str);

            return str;
          }
        }
