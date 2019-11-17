/****************************************************************
 * Šioje klasėje tiriame įvarių Javos paketų naudingas klases
 * Rekomenduojame plačiau patyrinėti metodiką su Enumemeration,
 * klasės Calendar metodus, jų ryšį su LT lokale.
 * operacijas su failais pakete java.nio
 * tinklinį programavimą su paketo java.net klasėmis
 * 
 * ... Taigi čia galime pasireikšti visi: tiek kolegos, tiek studentai 
 *
 * @author visi kurie pateiks medžiagą
 **************************************************************************/
package demos.console;

import extendsFX.BaseConsole;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javafx.stage.Stage;
import java.awt.Desktop;
import java.net.*;

public class Demo4_Various extends BaseConsole{
    final static File file =  new File("C:\\Users\\Antanas\\Desktop\\labasJava\\Lab1a_IntroductionFX\\build\\Dokumentas.txt");
    private void openUrl()
    {
            try {
                Desktop d = Desktop.getDesktop();
                d.browse(new URI("https://docs.oracle.com/javase/7/docs/api/java/net/package-summary.html"));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

    }
    private void read()
    {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine())
            {
                String values = scanner.nextLine();
                ta2.appendText(values + nL);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void calendar()
    {
        Calendar cl = Calendar.getInstance();
        printLn(cl.getTime()+  ""); //Paima kompiuteryje esamą laiką
        Calendar c = Calendar.getInstance(Locale.KOREA);
        printLn(Calendar.getInstance(Locale.KOREA).getTime() + ""); //-?
    }
    private void iterator()
    {
        /*IteratorClass<String> itt = new IteratorClass<String>("sgs", "sgsg");
        Iterator<String> it = itt.iterator();
        for (String ot: itt)
        {
            ta2.appendText(ot + "");
        }*/
        IteratorClass<String>[] iter = new IteratorClass[2]; //Rimtesnis Iteravimas
        iter[0] = new IteratorClass<String>("fs", "sf");
        iter[1] = new IteratorClass<String>("fsd", "sfd");
        for (int i = 0; i < iter.length; i++)
        {
            for (String temp : iter[i])
            {
                ta2.appendText(temp + "" + nL);
            }
        }
    }
    private void infoAboutEnviroment() {
        ta1.appendText("*** Informacija apie aplinkos savybes:" + nL);
        Properties p = System.getProperties();
        Enumeration keys = p.keys();
        while (keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            String value = (String)p.get(key);
            ta1.appendText(key + ": " + value + nL);
        }
    }
    @Override
    public void createControls() {
        super.createControls();    // sukuriame bazinius mygtukus
        addButton("info", e -> infoAboutEnviroment());
        addButton("iterate", e -> iterator());
        addButton("calendar", e -> calendar());
        addButton("file", e -> read());
        addButton("openUrl", e -> openUrl());
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Įvairūs demonstraciniai metodai (KTU IF)");
        super.start(stage);  
    }
    public static void main(String[] args) {
        launch();
    }
}
class IteratorClass<T> implements Iterable<T>
{
    private T name;

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public T getSurname() {
        return surname;
    }

    public void setSurname(T surname) {
        this.surname = surname;
    }

    public IteratorClass(T name, T surname) {
        this.name = name;
        this.surname = surname;
    }

    private T surname;
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int count = 0;
            private int count2 = 0;
            @Override
            public boolean hasNext() {
                return count >= count2;
            }
            @Override
            public T next() {
                count2++;
                return name;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Program stopped working!");
            }
        };
    }
}
