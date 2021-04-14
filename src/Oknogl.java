import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class Oknogl extends JFrame implements ActionListener{
    public static JLabel background;
    static JButton Wpisz1, Wpisz2, Wpisz3, Wpisz4,wykonaj_krok,wykonaj_program,zapisz_program,wczytaj_program;
    JButton wRozkaz= new JButton("Wykonaj rozkaz");
    JButton zRozkaz= new JButton("Zapisz rozkaz");

    Zadanie [] komendy = new Zadanie[30];
    static int nr_komendy_zapis = 0;
    static int nr_komendy_wykonanie=0;
  public static int dataSize = 1024 * 1024;

    static JTextField tekst[][]= new JTextField[5][3];
    public JLabel etykiety[][]= new JLabel[4][13];
    public JRadioButton rb[][]= new JRadioButton[2][81];
  static  JTextArea textarea,textarea2;

    JComboBox cb,cb1,cb2,cb3;
    int zmienna1,zmienna2,decimalA,decimalB,decimalC,decimalD;
    String sx="x";


    Oknogl(){


        setSize(1444,878);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon img= new ImageIcon("images/tlo.jpg");
        background= new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,1544,966);
        add(background);
        background.setFocusable(true);
        background.requestFocus();


        background.addKeyListener(new KeyAdapter(){
        public void keyPressed(KeyEvent ke){

          if(sx.equals("09H"))
            textarea2.setText("Key value in ASCII is :" +ke.getKeyCode());
        }
    });

       Radiobuttons();
        etykiety();
        Przyciski();
        comboboxes();

        Wpisz1.doClick();
        Wpisz2.doClick();
        Wpisz3.doClick();
        Wpisz4.doClick();



        setVisible(true);
    }



    public static String DiskInfo() {

        File[] roots = File.listRoots();
        StringBuilder sb = new StringBuilder();
        textarea2.setFont(new Font("Calibri",Font.PLAIN,12));

        for (File root : roots) {
            sb.append("File system root: ");
            sb.append(root.getAbsolutePath());
            sb.append("\n");
            sb.append("Total space (MB): ");
            sb.append(root.getTotalSpace()/dataSize);
            sb.append("\n");
            sb.append("Free space (MB): ");
            sb.append(root.getFreeSpace()/dataSize);
            sb.append("\n");

        }
        return sb.toString();
    }



    private void comboboxes(){

        String[] funkcja = { "MOV", "ADD", "SUB", "PUSH", "POP","09H","10H01H","10H02H","10H13H","12H","13H00H","13H08H","1AH00H" };
        cb=new JComboBox(funkcja);
        cb.setBounds(273,730,70,30);
        background.add(cb);

        String[] funkcja2 = { "A", "B", "C","D" };
        cb1=new JComboBox(funkcja2);
        cb1.setBounds(363,730,50,30);
        background.add(cb1);


        cb2=new JComboBox(funkcja2);
        cb2.setBounds(473,730,50,30);
        background.add(cb2);

        cb3=new JComboBox(funkcja2);
        cb3.setBounds(543,730,50,30);
        background.add(cb3);

        etykiety[3][11]= new JLabel("--->");
        etykiety[3][11].setBounds(429,730,60,40);
        background.add(etykiety[3][11]);


        cb.addActionListener(this);
        cb1.addActionListener(this);
        cb2.addActionListener(this);
        cb3.addActionListener(this);

    }

    private void etykiety(){
        int dxp=0;
        int dxp1=0;


        for(int i=0;i<6;i++) {
            etykiety[2][i] = new JLabel("15-bit");
            etykiety[3][i] = new JLabel("0-bit");
            background.add(etykiety[2][i]);
            background.add(etykiety[3][i]);
        }
        dxp=0;
        for(int i=0;i<4;i++){

            etykiety[0][i]=new JLabel("0");
            etykiety[0][i].setBounds(42+dxp,56,10,10);
            background.add(etykiety[0][i]);
            etykiety[1][i]=new JLabel("1");
            etykiety[1][i].setBounds(42+dxp,32,10,10);
            background.add(etykiety[1][i]);


            if(i==1)
                dxp=dxp-300;
            dxp=dxp+330;
            dxp1=dxp1+270;
        }

        dxp=0;
        for(int i=0;i<4;i++){
            etykiety[1][i]=new JLabel("1");
            etykiety[1][i].setBounds(42+dxp,286,10,10);
            background.add(etykiety[1][i]);
            etykiety[0][i]=new JLabel("0");
            etykiety[0][i].setBounds(42+dxp,306,10,10);
            background.add(etykiety[0][i]);


            if(i==1)
                dxp=dxp-300;
            dxp=dxp+330;
        }
        dxp=0;
        dxp1=0;
        int dy=0;

        tekst[0][0]=new JTextField("00000000");
        background.add(tekst[0][0]);
        tekst[0][1]=new JTextField("00000000");
        background.add(tekst[0][1]);
        tekst[1][0]=new JTextField("00000000");
        background.add(tekst[1][0]);
        tekst[1][1]=new JTextField("00000000");
        background.add(tekst[1][1]);
        tekst[2][0]=new JTextField("00000000");
        background.add(tekst[2][0]);
        tekst[2][1]=new JTextField("00000000");
        background.add(tekst[2][1]);
        tekst[3][0]=new JTextField("00000000");
        background.add(tekst[3][0]);
        tekst[3][1]=new JTextField("00000000");
        background.add(tekst[3][1]);
        tekst[4][0]=new JTextField("00000000");
        background.add(tekst[4][0]);
        tekst[4][1]=new JTextField("00000000");
        background.add(tekst[4][1]);



        textarea=new JTextArea("");
        textarea2=new JTextArea("");
        textarea.setEnabled(false);
        textarea2.setEnabled(false);
        textarea.setBounds(800,10,600,650);
        textarea.setFont(new Font("Calibri",Font.BOLD,18));
        background.add(textarea);

        textarea2.setBounds(490,500,290,155);
        textarea2.setFont(new Font("Calibri",Font.BOLD,18));
        background.add(textarea2);
        textarea2.setBackground(Color.BLACK);
        textarea2.setForeground(Color.WHITE);


        for(int i=0;i<2;i++){



            etykiety[1][i]=new JLabel("1");
            etykiety[1][i].setBounds(42+dxp,540,10,10);
            background.add(etykiety[1][i]);
            etykiety[0][i]=new JLabel("0");
            etykiety[0][i].setBounds(42+dxp,560,10,10);
            background.add(etykiety[0][i]);


            etykiety[2][i].setBounds(47+dxp1,74,40,10);
            etykiety[3][i].setBounds(350+dxp1,74,40,10);


            dxp=dxp+330;
            dxp1=dxp+33;

        }

        for(int i=0;i<3;i++){
            tekst[i][0].setBounds(144,130+dy,100,40);
            tekst[i][1].setBounds(244,130+dy,100,40);
            dy=dy+270;


        }


        dy=0;
        for(int i=3;i<5;i++){
            tekst[i][0].setBounds(444,130+dy,100,40);
            tekst[i][1].setBounds(544,130+dy,100,40);
            dy=dy+270;

        }



        etykiety[2][2].setBounds(47,324,40,10);
        etykiety[3][2].setBounds(717,324,40,10);
        etykiety[2][3].setBounds(407,324,40,10);
        etykiety[3][3].setBounds(350,324,40,10);
        etykiety[2][4].setBounds(47,584,40,10);
        etykiety[3][4].setBounds(350,584,40,10);

        etykiety[0][11]= new JLabel("Argument dla trybu natychmiastowego");
        etykiety[0][11].setBounds(80,500,290,30);
        background.add(etykiety[0][11]);

        etykiety[0][12]= new JLabel("A");
        etykiety[0][12].setBounds(10,20,20,20);
        background.add(etykiety[0][12]);

        etykiety[1][12]= new JLabel("B");
        etykiety[1][12].setBounds(10,262,20,20);
        background.add(etykiety[1][12]);

        etykiety[2][12]= new JLabel("C");
        etykiety[2][12].setBounds(755,20,20,20);
        background.add(etykiety[2][12]);

        etykiety[3][12]= new JLabel("D");
        etykiety[3][12].setBounds(755,262,20,20);
        background.add(etykiety[3][12]);
    }
    private void Radiobuttons(){


        int dx=0;
        for(int i=0;i<32;i++){

            rb[0][i]=new JRadioButton();
            rb[0][i].setBounds(50+dx,50,20,20);
            background.add(rb[0][i]);
            rb[1][i]=new JRadioButton();
            rb[1][i].setBounds(50+dx,30,20,20);
            background.add(rb[1][i]);
            rb[0][i].setEnabled(false);
            rb[1][i].setEnabled(false);
            dx=dx+20;
            if(i==15)
                dx=dx+40;
        }
        dx=0;
        for(int i=32;i<64;i++){

            rb[0][i]=new JRadioButton();
            rb[0][i].setBounds(50+dx,300,20,20);
            background.add(rb[0][i]);
            rb[1][i]=new JRadioButton();
            rb[1][i].setBounds(50+dx,280,20,20);
            background.add(rb[1][i]);
            rb[0][i].setEnabled(false);
            rb[1][i].setEnabled(false);

            dx=dx+20;
            if(i==47)
                dx=dx+40;
        }
        dx=0;
        for(int i=64;i<80;i++){

            rb[0][i]=new JRadioButton();
            rb[0][i].setBounds(50+dx,550,20,20);
            background.add(rb[0][i]);

            rb[1][i]=new JRadioButton();
            rb[1][i].setBounds(50+dx,530,20,20);
            background.add(rb[1][i]);
            rb[0][i].setEnabled(false);
            rb[1][i].setEnabled(false);
            dx=dx+20;
            if(i==47)
                dx=dx+40;
        }


    }


    private void Przyciski(){

        Wpisz1 = new JButton("Wpisz");
        Wpisz2 = new JButton("Wpisz");
        Wpisz3 = new JButton("Wpisz");
        Wpisz4 = new JButton("Wpisz");

        wykonaj_krok = new JButton("Wykonaj krok");
        wykonaj_program = new JButton("Wykonaj program");
        zapisz_program = new JButton("Zapisz program");
        wczytaj_program = new JButton("wczytaj program");

        wykonaj_krok.setBounds(838,710,130,30);
        wykonaj_program.setBounds(838,750,150,30);
        zapisz_program.setBounds(999,730,130,30);
        wczytaj_program.setBounds(1150,730,130,30);

        wRozkaz.setBounds(600,680,130,40);
        wRozkaz.addActionListener(this);

        zRozkaz.setBounds(600,730,130,40);
        zRozkaz.addActionListener(this);

        Wpisz1.setBounds(250,98,70,30);
        Wpisz2.setBounds(590,98,70,30);
        Wpisz3.setBounds(250,358,70,30);
        Wpisz4.setBounds(605,358,70,30);
        background.add(Wpisz1);
        background.add(Wpisz2);
        background.add(Wpisz3);
        background.add(Wpisz4);
        background.add(wRozkaz);
        background.add(zRozkaz);
        background.add(wykonaj_krok);
        background.add(wczytaj_program);
        background.add(wykonaj_program);
        background.add(zapisz_program);

        Wpisz1.addActionListener(this);
        Wpisz2.addActionListener(this);
        Wpisz3.addActionListener(this);
        Wpisz4.addActionListener(this);
        wykonaj_krok.addActionListener(this);
        wykonaj_program.addActionListener(this);
        zapisz_program.addActionListener(this);
        wczytaj_program.addActionListener(this);





        Wpisz1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tekst[0][0].getText().length() != 8 || tekst[0][1].getText().length() != 8) {
                    JOptionPane.showMessageDialog(null, "Niepoprawna ilość bitów!");

                } else {
                    int[] rejestr_a = new int[22];
                    char[] rejestr_char_aH = tekst[0][0].getText().toCharArray();
                    char[] rejestr_char_aL = tekst[0][1].getText().toCharArray();
                    for (int i = 0; i < 16; i++) {
                        if (i < 7)
                            rejestr_a[i] = Character.getNumericValue(rejestr_char_aH[i]);
                        if (i > 7)
                            rejestr_a[i] = Character.getNumericValue(rejestr_char_aL[i - 8]);

                        if (rejestr_a[i] == 1) {
                            rb[1][i].setSelected(true);
                            rb[0][i].setSelected(false);
                        } else {
                            rb[1][i].setSelected(false);
                            rb[0][i].setSelected(true);
                        }

                    }

                    decimalA = Integer.parseInt(tekst[0][0].getText() + tekst[0][1].getText(), 2);
                    System.out.println(decimalA);

                }
            }
        });


        Wpisz2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tekst[3][0].getText().length() != 8 || tekst[3][1].getText().length() != 8) {
                    JOptionPane.showMessageDialog(null, "Niepoprawna ilość bitów!");

                } else {
                    int[] rejestr_c = new int[22];
                    char[] rejestr_char_cH = tekst[3][0].getText().toCharArray();
                    char[] rejestr_char_cL = tekst[3][1].getText().toCharArray();
                    for (int i = 0; i < 16; i++) {
                        if (i < 7)
                            rejestr_c[i] = Character.getNumericValue(rejestr_char_cH[i]);
                        if (i > 7)
                            rejestr_c[i] = Character.getNumericValue(rejestr_char_cL[i - 8]);

                        if (rejestr_c[i] == 1) {
                            rb[1][i + 16].setSelected(true);
                            rb[0][i + 16].setSelected(false);
                        } else {
                            rb[1][i + 16].setSelected(false);
                            rb[0][i + 16].setSelected(true);
                        }


                    }


                    decimalC = Integer.parseInt(tekst[3][0].getText() + tekst[3][1].getText(), 2);
                    System.out.println(decimalC);
                    System.out.println("wpisz 2");
                }
            }
        });

        Wpisz3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tekst[1][0].getText().length() != 8 || tekst[1][1].getText().length() != 8) {
                    JOptionPane.showMessageDialog(null, "Niepoprawna ilość bitów!");

                } else {
                    int[] rejestr_b = new int[22];
                    char[] rejestr_char_bH = tekst[1][0].getText().toCharArray();
                    char[] rejestr_char_bL = tekst[1][1].getText().toCharArray();
                    for (int i = 0; i < 16; i++) {
                        if (i < 7)
                            rejestr_b[i] = Character.getNumericValue(rejestr_char_bH[i]);
                        if (i > 7)
                            rejestr_b[i] = Character.getNumericValue(rejestr_char_bL[i - 8]);

                        if (rejestr_b[i] == 1) {
                            rb[1][i + 32].setSelected(true);
                            rb[0][i + 32].setSelected(false);
                        } else {
                            rb[1][i + 32].setSelected(false);
                            rb[0][i + 32].setSelected(true);
                        }


                    }

                    decimalB = Integer.parseInt(tekst[1][0].getText() + tekst[1][1].getText(), 2);
                    System.out.println(decimalB);
                    System.out.println("wpisz 3");

                }
            }
        });


        Wpisz4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tekst[4][0].getText().length() != 8 || tekst[4][1].getText().length() != 8) {
                    JOptionPane.showMessageDialog(null,"Niepoprawna ilość bitów!");

                } else {
                    int[] rejestr_d = new int[22];
                    char[] rejestr_char_dH = tekst[4][0].getText().toCharArray();
                    char[] rejestr_char_dL = tekst[4][1].getText().toCharArray();
                    for (int i = 0; i < 16; i++) {
                        if (i < 7)
                            rejestr_d[i] = Character.getNumericValue(rejestr_char_dH[i]);
                        if (i > 7)
                            rejestr_d[i] = Character.getNumericValue(rejestr_char_dL[i - 8]);

                        if (rejestr_d[i] == 1) {
                            rb[1][i + 48].setSelected(true);
                            rb[0][i + 48].setSelected(false);
                        } else {
                            rb[1][i + 48].setSelected(false);
                            rb[0][i + 48].setSelected(true);
                        }


                    }

                    decimalD = Integer.parseInt(tekst[4][0].getText() + tekst[4][1].getText(), 2);
                    System.out.println(decimalD);

                }
            }
        });


    }


    @Override
    public void actionPerformed (ActionEvent e) {
        Object zrodlo = e.getSource();


 
         if(zrodlo==wRozkaz) {

             String s = (String) cb.getSelectedItem();
            String s2 = (String) cb2.getSelectedItem();
            String s1 = (String) cb1.getSelectedItem();
            String s3 = (String) cb3.getSelectedItem();

            sx=s;
             System.out.println(sx);

            switch (s1) {
                case "A":
                    zmienna1 = decimalA;
                    break;
                case "B":
                    zmienna1 = decimalB;
                    break;
                case "C":
                    zmienna1 = decimalC;
                    break;

                case "D":
                    zmienna1 = decimalD;
                    break;
            }

            switch (s2) {
                case "A":
                    zmienna2 = decimalA;
                    break;
                case "B":
                    zmienna2 = decimalB;
                    break;
                case "C":
                    zmienna2 = decimalC;
                    break;
                case "D":
                    zmienna2 = decimalD;
                    break;
            }


            Zadanie z = new Zadanie(s1, s2, s, s3);
            z.wykonaj_operacje();
           // z.pop();

            tekst[2][0].setText(z.pokonwersjiH);
            tekst[2][1].setText(z.pokonwersjiL);
try {
    int[] rejestr_natychmiastowy = new int[22];
    char[] rejestr_char_nH = tekst[2][0].getText().toCharArray();
    char[] rejestr_char_nL = tekst[2][1].getText().toCharArray();
    for (int i = 0; i < 16; i++) {
        if (i < 7)
            rejestr_natychmiastowy[i] = Character.getNumericValue(rejestr_char_nH[i]);
        if (i > 7)
            rejestr_natychmiastowy[i] = Character.getNumericValue(rejestr_char_nL[i - 8]);

        if (rejestr_natychmiastowy[i] == 1) {
            rb[1][i + 64].setSelected(true);
            rb[0][i + 64].setSelected(false);
        } else {
            rb[1][i + 64].setSelected(false);
            rb[0][i + 64].setSelected(true);
        }


    }
}
catch (ArrayIndexOutOfBoundsException exe){

}
             background.requestFocus(true);
        }
        else if(zrodlo==zRozkaz){
            String s= (String) cb.getSelectedItem();
            String s2= (String) cb2.getSelectedItem();
            String s1= (String) cb1.getSelectedItem();
            String s3= (String) cb3.getSelectedItem();

            switch (s1) {
                case "A":
                    zmienna1 = decimalA;
                    break;
                case "B":
                    zmienna1 = decimalB;
                    break;
                case "C":
                    zmienna1 = decimalC;
                    break;

                case "D":
                    zmienna1 = decimalD;
                    break;
            }

            switch (s2) {
                case "A":
                    zmienna2 = decimalA;
                    break;
                case "B":
                    zmienna2 = decimalB;
                    break;
                case "C":
                    zmienna2 = decimalC;
                    break;
                case "D":
                    zmienna2 = decimalD;
                    break;
            }

            komendy[nr_komendy_zapis]=new Zadanie(s1,s2,s,s3);
            nr_komendy_zapis++;


             background.requestFocus(true);

        }

        else if(zrodlo==zapisz_program){
            Wczytywanie w= new Wczytywanie(komendy);
            w.zapis();
             background.requestFocus(true);
        }

        else if(zrodlo==wczytaj_program){
            Wczytywanie w= new Wczytywanie(komendy);
            try {
                w.odczyt();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
             background.requestFocus(true);
        }

        else if(zrodlo==wykonaj_krok){
            komendy[nr_komendy_wykonanie].wykonaj_operacje();
            nr_komendy_wykonanie++;
             background.setFocusable(true);
         }

        else if (zrodlo==wykonaj_program){
            while(nr_komendy_wykonanie<komendy.length){
                komendy[nr_komendy_wykonanie].wykonaj_operacje();
                nr_komendy_wykonanie++;
            }
             background.requestFocus(true);
         }

    }



}
