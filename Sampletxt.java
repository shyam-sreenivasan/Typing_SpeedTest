
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;


public class Sampletxt {
    Gui gui;
    MyArea area;
    HashMap<Integer, String> hm;
    String str = "";
    Scanner sc = null;
    Object last = null;
    int flag = 0;
    int checkstart = 0;
    int p0 = 0;
    int p1 = 0;
    int scrollvalue = 0;
    int linecurr = 0;
    int lineprev = 0;
    public static int count = 0;
    int wordPosCurr = 1;
    int wordPosPrev = 0;
    String x = ""; //doneString
    int toggle = 0;
    int prev = 0;
    String klm = "";


    public Sampletxt(Gui gui, String string, Object object,MyArea area) {
        this.gui = gui;
        this.str = null;
        this.str = this.gui.txtarea.getText();
        this.last = object;
        this.area = area;
        // this.gui.text.addKeyListener(this);
        this.hm = new HashMap();
    }

    public void doHighlight() {
        try {
            String string;
            if (this.last != null) {
                this.gui.txtarea.getHighlighter().removeHighlight(this.last);
            }
            Highlighter highlighter = this.gui.txtarea.getHighlighter();
            DefaultHighlighter.DefaultHighlightPainter defaultHighlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.pink);
            // int n = this.area.triggerNow();
            if (this.toggle == 0 && area.trigger == 0) {
                string = this.findWord(this.str, this.wordPosCurr);
                StringBuilder stringBuilder = new StringBuilder(this.x);
                stringBuilder.append(" " + string);
                this.x = stringBuilder.toString();
                // System.out.println("Current word position is:" + this.wordPosCurr);
                // System.out.println("Current word is:" + this.x);
                this.p0 = this.findIndex(this.str, this.wordPosCurr, string);
                this.hm.put(this.p0, string);
                this.p1 = this.p0 + string.length();
                this.wordPosPrev = this.wordPosCurr++;
                TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>(this.hm);
                Set<Map.Entry<Integer, String>> set = treeMap.entrySet();
                for (Map.Entry<Integer, String> entry : set) {
                }
            } else if (this.toggle == 1 && area.trigger == 1) {
                // this.area.triggerSetZero();
                // System.out.println("Trigger is" + this.area.trigger);
                String temp = "";
                StringBuilder sb = new StringBuilder(x);
                Scanner sc  = new Scanner(x);
                while(sc.hasNext()){
                  temp = sc.next();
                }
                int len = temp.length();
                sb.setLength(sb.length() - len -1);
                x = sb.toString();
                string = "";
                int n2 = 0;
                TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>(this.hm);
                Set<Map.Entry<Integer, String>> set = treeMap.entrySet();
                Iterator<Map.Entry<Integer, String>> iterator = set.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer, String> entry = iterator.next();
                    int n3 = entry.getKey();
                    String string2 = entry.getValue();
                    if (n3 == this.p0) {
                        iterator.remove();
                        this.p0 = n2;
                        this.klm = string;
                        this.p1 = this.p0 + this.klm.length();
                        break;
                    }
                    n2 = n3;
                    string = string2;
                }
                --this.wordPosCurr;
                --this.wordPosPrev;
            }
            this.gui.txtarea.setCaretPosition(this.p1);
            this.last = highlighter.addHighlight(this.p0, this.p1, defaultHighlightPainter);
            int n4 = this.gui.txtarea.getCaretPosition();
            this.linecurr = this.gui.txtarea.getLineOfOffset(n4);
            if (this.linecurr > this.lineprev) {
                this.scrollvalue+=16;
                this.gui.scroll.getVerticalScrollBar().setValue(this.scrollvalue);
                this.lineprev = this.linecurr;
            }
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
        }
    }

    public String findWord(String string, int n) {
        Scanner scanner = new Scanner(string);
        String string2 = "";
        for (int i = 0; i < n; ++i) {
            string2 = scanner.next();
        }
        return string2;
    }

    public int findIndex(String string, int n, String string2) {
        int n2 = 0;
        String string3 = "";
        int n3 = 0;
        n2 = this.p0;
        Scanner scanner = new Scanner(string);
        for (int i = 0; i < n; ++i) {
            string3 = scanner.next();
            if (!string3.equals(string2)) continue;
            while (n2 < this.p1) {
                n2 = this.str.indexOf(string3, n3);
                ++n3;
            }
        }
        return n2;
    }
}
