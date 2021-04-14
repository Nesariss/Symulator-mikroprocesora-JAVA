import java.io.*;


public class Wczytywanie implements Serializable {
    Zadanie[] komendy;
    ObjectOutputStream z = null;
    ObjectInputStream o = null;


    Wczytywanie(Zadanie[] komendy){
        this.komendy=komendy;
    }

    Wczytywanie(){};



    public void zapis(){
        try{
            z=new ObjectOutputStream(new FileOutputStream("komendy.txt"));
            for(int i=0;komendy[i]!=null;i++){
                z.writeObject(komendy[i]);
                z.flush();
            }
            z.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void odczyt() throws IOException {
        int i=0;
        try {
            o = new ObjectInputStream(new FileInputStream("komendy.txt"));
            Main.okno.textarea.setText("");
            while (true){
                komendy[i]=(Zadanie)o.readObject();
                komendy[i].wypisz();
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e){
            System.out.println("Koniec pliku");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        o.close();


    }

    public void czyszczenie(){
        try{
            z=new ObjectOutputStream(new FileOutputStream("komendy.txt"));
            z.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

