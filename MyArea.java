
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintStream;
import javax.swing.*;;
import java.util.Scanner;
import java.awt.event.*;


public class MyArea implements KeyListener{
    Gui gui;
    Sampletxt sam;
    Timer tim;
    Object last;
    String stringy = "";
    String fullstr = "";
    int charcount = 0;
    int wscurr = 0;
    int wsprev = 0;
    int trigger = 0;
    String str = "";//String in textbox
    int wordcount = 0;
    int charcountfinal = 0;
    int minute = 0;
    int second = 60;
    int checktime = 0;
    int i=0,j=0,disable=0; // word per min and char per min
    public MyArea(Gui guii) throws Exception{
        last = null;
        this.gui = guii;
        // timer = new MyTimer(gui,this);
        str = gui.txtarea.getText();
        // this.gui = gui;
        this.gui.text.addKeyListener(this);
        this.sam = new Sampletxt(gui,str,last,this);
        sam.doHighlight();
        //----------------------------------------------------------------------
        ActionListener actlis = new ActionListener(){
          public void actionPerformed(ActionEvent ev){

                gui.texttime.setText(minute+":"+--second);
                int n = calculateWPM();
                int m = calculateCPM();
                int timerepeat = 60 - second;
                if(timerepeat != 60 && timerepeat > 5 && timerepeat%3 == 0){
                        i = (n*60)/timerepeat;
                        j = (m*60)/timerepeat;
                        gui.textwpm.setText(""+i);
                        gui.textcpm.setText(""+j);
                      }
                else if(timerepeat == 60){
                      n = m/5;
                      gui.text.setEditable(false);
                      gui.text.getCaret().setVisible(false);
                      tim.setRepeats(false);
                      tim.stop();
                      second = 0;
                      // gui.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                      // m = n*5;
                      gui.textwpm.setText(""+n);
                      gui.textcpm.setText(""+m);
                      disable = 1;
                }

          }
        };
        //-------------------------------------------------------------------------
       tim = new Timer(1000,actlis);

    }


    public void keyPressed(KeyEvent e) {
      try{
          if(checktime == 0){
            // timer.start();
            tim.start();
          }
          checktime = 1;

        }catch(Exception ex){}
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
      int dis = disableApp();
      if(disable != 1){
      try{
        if (keyEvent.getKeyCode() == 8) {
            // StringBuilder stringBuilder = new StringBuilder(this.fullstr);
            // int n = stringBuilder.length();
            // stringBuilder.setCharAt(n - 1, '\u0000');
            // stringBuilder.setLength(n - 1);
            // this.fullstr = stringBuilder.toString();
            // n = this.fullstr.length();
            StringBuilder sbb = new StringBuilder(fullstr);
            String sample = gui.text.getText();
            if(sample.equals(" ")){
              sbb.setLength(sbb.length() - 1);
              fullstr = sbb.toString();
            }
            this.wscurr = this.whitespace(this.fullstr);
            int sublen = 0;
            if (this.wscurr <= this.wsprev && wscurr >0 && wsprev>0) {
                Scanner sc = new Scanner(fullstr);
                  String string = "";
                while(sc.hasNext()){
                   string = sc.next();
                   sublen = string.length();
                }
                // System.out.println("\n\n" + string);
                this.gui.text.setText(string);
                StringBuilder sb = new StringBuilder(fullstr);
                int fulllen = sb.length();
                sb.setLength(fulllen - sublen);
                fullstr = sb.toString();
                this.wsprev--;
                this.triggerSetOne();
                // System.out.println("Wscurr is :"+ wscurr +" :: wsprev is :"+wsprev);
            }else{
              this.triggerSetZero();
          }
            sam.toggle = 1;
            sam.doHighlight();
        } else if (keyEvent.getKeyCode() == 32) {
            this.stringy = this.gui.text.getText();
            if(stringy.equals(" ")){
                System.out.println("Stringy is " +stringy);
                gui.text.setText("");
            }else{
              System.out.println("Stringy is in else is" +stringy);
            this.gui.text.setText("");
            StringBuilder stringBuilder = new StringBuilder(this.fullstr);
            stringBuilder.append("" +stringy);
            this.fullstr = stringBuilder.toString();
            // System.out.println("Word is :" + this.fullstr);
            this.wsprev = this.wscurr;
            this.wscurr = this.whitespace(this.fullstr);
            this.triggerSetZero();
            sam.toggle = 0;
            sam.doHighlight();
            calculateWPM();
          }
        }// else {
            // this.stringy = this.gui.text.getText();
            // int n = this.gui.text.getCaretPosition();
            // char c = stringy.charAt(n-1);
            // this.fullstr += Character.toString(c);
            // char[] arrc = this.stringy.toCharArray();
            // this.fullstr = this.fullstr + Character.toString(arrc[n - 1]);
      //  }
      }catch(Exception ex){}
      }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    public int whitespace(String string) {
        int n = 0;
        for(int i=0;i<string.length();i++){
          if(Character.isWhitespace(string.charAt(i))){
            n++;
          }
        }
        return n;
    }



    public void triggerSetZero() {
        this.trigger = 0;
    }

    public void triggerSetOne() {
        this.trigger = 1;
    }

    public int triggerNow() {
        return this.trigger;
    }

    public int  calculateWPM(){
          wordcount = 0;
          Scanner sc1 = new Scanner(fullstr);
          Scanner sc2 = new Scanner(sam.x);
          while(sc1.hasNext() && sc2.hasNext()){
            String str1 = sc1.next();
            String str2 = sc2.next();
            if(str1.equals(str2)){
              wordcount = wordcount + 1;
            }
          }
            // System.out.println("Word count is: " + wordcount);
            return wordcount;
    }

    public int  calculateCPM(){
          charcountfinal = 0;
          Scanner sc1 = new Scanner(fullstr);
          Scanner sc2 = new Scanner(sam.x);
          while(sc1.hasNext() && sc2.hasNext()){
            String str1 = sc1.next();
            String str2 = sc2.next();
            if(str1.equals(str2)){
              charcountfinal += str1.length();
            }
          }
            // System.out.println("char count is: " + wordcount);
            return charcountfinal;
    }

    public int disableApp(){
      return disable;
    }

}
