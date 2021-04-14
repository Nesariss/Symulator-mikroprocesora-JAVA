



import java.awt.*;
import java.io.Serializable;

import java.util.Date;

import java.util.Stack;

public class Zadanie implements Serializable {



        int[] rejestr_y = new int[16];
        static int decimalY;
        int decimalwej1;
        int decimalwej2;
        int SP;
        String operacja;
        String pokonwersjiH="";
        String pokonwersjiL="";
        String zapisanyRozkaz;
        String s1;
        String s2;

       // List<String> Stos= new ArrayList<String>();

    Stack<String> stos= new Stack<String>();



        Zadanie(String s1, String s2, String operacja,String zapisanyRozkaz){
            this.zapisanyRozkaz=zapisanyRozkaz;
            this.operacja=operacja;
            this.s1 = s1;
            this.s2=s2;
            wypisz();

        }


        public void push(){
            String rejestrpomocniczy="";

            if(s1.equals("A")) {
               rejestrpomocniczy=Oknogl.tekst[0][0].getText()+Oknogl.tekst[0][1].getText();
                stos.push(rejestrpomocniczy);

            }
            else if(s1.equals("B")){
                rejestrpomocniczy=Oknogl.tekst[1][0].getText()+Oknogl.tekst[1][1].getText();
                stos.push(rejestrpomocniczy);
            }
            else if(s1.equals("C")){
                rejestrpomocniczy=Oknogl.tekst[3][0].getText()+Oknogl.tekst[3][1].getText();
                stos.push(rejestrpomocniczy);
            }
            else if(s1.equals("D")){
                rejestrpomocniczy=Oknogl.tekst[4][0].getText()+Oknogl.tekst[4][1].getText();
                stos.push(rejestrpomocniczy);
            }

        }

        public void pop () throws ArrayIndexOutOfBoundsException{
            Oknogl.textarea2.setText(stos.pop());
        //Oknogl.textarea2.setText(Stos.get(0));


        }

        public void wykonaj_operacje(){
            if(s1.equals("A")) {
                decimalwej1 = Main.okno.decimalA;

            }
            else if(s1.equals("B")){
                decimalwej1=Main.okno.decimalB;
            }
            else if(s1.equals("C")){
                decimalwej1=Main.okno.decimalC;
            }
            else if(s1.equals("D")){
                decimalwej1=Main.okno.decimalD;
            }

            if(s2.equals("A"))
                decimalwej2=Main.okno.decimalA;
            else if(s2.equals("B")){
                decimalwej2=Main.okno.decimalB;
            }
            else if(s2.equals("C")){
                decimalwej2=Main.okno.decimalC;
            }
            else if(s2.equals("D")){
                decimalwej2=Main.okno.decimalD;
            }

            if(operacja.equals("MOV")){
                decimalwej2 = decimalwej1;
                System.out.println(decimalwej2);
                zamiana_na_binarny(decimalwej2);
                konwersja();
                przypisanie();


            }
            if(operacja.equals("ADD")){
                decimalY = decimalwej1 + decimalwej2;
                System.out.println(decimalY);
                zamiana_na_binarny(decimalY);
                konwersja();
                przypisanie();

            }
            if(operacja.equals("SUB")){
                decimalY = decimalwej1 - decimalwej2;
                System.out.println(decimalY);
                zamiana_na_binarny(decimalY);
                konwersja();
                przypisanie();
            }

            if(operacja.equals("PUSH")){
                push();
                Oknogl.textarea2.setText("Zapisano na stos"+ stos);
            }
            if(operacja.equals("POP")){
                push();
                pop();
            }
            if(operacja.equals("10H01H")){
                Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
                Oknogl.background.setCursor(cursor);
            }
            else {
                Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
                Oknogl.background.setCursor(cursor);
            }
            if(operacja.equals("13H08H")){
                Oknogl.textarea2.setText(Oknogl.DiskInfo());
            }

            if(operacja.equals("12H")){
                long total = Runtime.getRuntime().maxMemory();
                long used  = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                Oknogl.textarea2.setText("Maksymalna dostępna pamięć " + total/Oknogl.dataSize+"MB" +"\n" +"Używana pamięć RAM:" + used/Oknogl.dataSize+"MB" );
            }
            if(operacja.equals("10H02H")){
                INT10H02HsetCursor_position();
            }
            if(operacja.equals("10H13H")){
                INT10H13H_wypisz_String();
            }
            if(operacja.equals("13H00H")){
                INT13H00H_zresetuj_pamiec();
            }
            if(operacja.equals("1AH00H")){
                INT1AH00H_zczytaj_czas();
            }

        }

        public void zamiana_na_binarny(int a){
            int j=0;
            for(int i=15; i>=0;i--){
                if((a&0b1 << i) != 0b0)
                    rejestr_y[j] = 1;
                else
                    rejestr_y[j] = 0;
                j++;
            }
        }


        public void konwersja(){
            for (int i=0; i<8;i++)
            pokonwersjiH= pokonwersjiH+ rejestr_y[i];
            for (int i=8; i<16;i++)
                pokonwersjiL= pokonwersjiL+ rejestr_y[i];

        }

        public void przypisanie(){
            switch (zapisanyRozkaz) {
                case "A":
                    Oknogl.tekst[0][0].setText(pokonwersjiH);
                    Oknogl.tekst[0][1].setText(pokonwersjiL);
                    Oknogl.Wpisz1.doClick();
                    break;
                case "B":
                    Oknogl.tekst[1][0].setText(pokonwersjiH);
                    Oknogl.tekst[1][1].setText(pokonwersjiL);
                    Oknogl.Wpisz3.doClick();
                    break;
                case "C":
                    Oknogl.tekst[3][0].setText(pokonwersjiH);
                    Oknogl.tekst[3][1].setText(pokonwersjiL);
                    Oknogl.Wpisz2.doClick();
                    break;
                case "D":
                    Oknogl.tekst[4][0].setText(pokonwersjiH);
                    Oknogl.tekst[4][1].setText(pokonwersjiL);
                    Oknogl.Wpisz4.doClick();
                    break;
            }
        }

    public void wypisz(){
        Main.okno.textarea.setText(Main.okno.textarea.getText()+"Komenda "+": Rejestr "+s1+"      "+operacja+"      Rejestr "+s2+"       na Rejestr "+zapisanyRozkaz+ "\n");

    }

    public void INT10H02HsetCursor_position(){
        int positionX = decimalwej1;
        int positionY = decimalwej2;
        try {
            Robot r = new Robot();
            r.mouseMove(positionX,positionY);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void INT10H13H_wypisz_String(){
        char znak = (char) decimalwej1;
        Oknogl.textarea2.setText(String.valueOf(znak));
    }


    public void INT13H00H_zresetuj_pamiec(){
        for(int i=0;Main.okno.komendy[i]!=null;i++)
            Main.okno.komendy[i]=null;
        Main.okno.textarea.setText("");
        Wczytywanie w = new Wczytywanie();
        w.czyszczenie();
        Oknogl.nr_komendy_wykonanie =0;
        Oknogl.nr_komendy_zapis=0;
    }
    public void INT1AH00H_zczytaj_czas(){
        Date d = new Date();
        Oknogl.textarea2.setText(String.valueOf(d));
    }

    }

