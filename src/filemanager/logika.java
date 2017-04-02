package filemanager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;

public class logika {

    String st;
    File lista;
    Path path;

    public logika() {
    }

    public logika(String str) {
        this.st = str;
        this.lista = new File(st);

    }

    public void LIST() {

        try {

            if (lista.list().length > 0) {

                if (lista.exists() && lista.isDirectory()) {

                    String[] strings = lista.list();
                    for (int i = 0; i < strings.length; i++) {

                        System.out.println(strings[i]);
                    }

                } else if (!lista.exists()) {

                    System.out.println("Direktorijum ne postoji.");
                }
            } else {

                System.out.println("Vas direktorijum je prazan!");
            }

        } catch (Exception e) {

            System.out.println("Problem sa otvaranjem direktorijuma.");
        }
    }
    /*
    @Override
    public String toString(){
        
        return 
    }
*/
    public void INFO() {

        try {

            File[] listOfFiles = lista.listFiles();

            if (lista.list().length > 0) {

                System.out.println("Lista svih fajlova i foldera sa detaljnim informacijama: ");

                for (int f = 0; f < listOfFiles.length; f++) {

                    System.out.println("Ime fajla/foldera je: " + listOfFiles[f].getName());
                    System.out.println("Putanja do fajla/foldera je: " + listOfFiles[f].getAbsolutePath());
                    System.out.println("Velicina fajla/foldera je: " + listOfFiles[f].length() / 1024 + "B");

                    path = Paths.get(listOfFiles[f].toString());
                    BasicFileAttributes attr;
                    try {
                        attr = Files.readAttributes(path, BasicFileAttributes.class);
                        SimpleDateFormat df = new SimpleDateFormat("dd. MMMM yyyy. HH:mm:ss");
                        String dateCreated = df.format(attr.creationTime().toMillis());
                        System.out.println("Fajl je kreiran: " + dateCreated);
                    } catch (IOException e) {
                        System.out.println("Neka greska se pojavila." + e.getMessage());
                    }
                    
                    Instant instant = Instant.ofEpochMilli(listOfFiles[f].lastModified());
                    LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd. MMMM yyyy. HH:mm:ss");
                    System.out.println("Fajl/folder je poslednji put modifikovan: " + dateTime.format(formater));
                    System.out.println("--------------------------------------------");
                }
            } else {

                System.out.println("Vas direktorijum je prazan!");
            }

        } catch (Exception e) {

            System.out.println("Problema sa iscitavanjem informacija o direktorijumu ili fajlu.");
        }
    }

    public void CREATE_DIR() {

        try {

            if (lista.exists() && lista.isDirectory()) {

                System.out.println("Unesite naziv direktorijuma koji zelite da kreirate: ");
                Scanner s = new Scanner(System.in);
                String nazivDir = s.next();
                File creDirectory = new File(st + "\\" + nazivDir);

                if (!creDirectory.exists()) {

                    creDirectory.mkdir();
                    System.out.println("Direktorijum sa nazivom " + creDirectory.getName() + " je kreiran.");
                } else {

                    System.out.println("Direktorijum sa nazivom " + creDirectory.getName() + " vec postoji. Izaberite neki drugi naziv.");
                }
            } else if (!lista.exists()) {

                System.out.println("Direktorijum ne postoji.");
            }

        } catch (Exception e) {

            System.out.println("Problem sa kreiranjem novog foldera.");
        }
    }

    public void DELETE() {

        try {

            if (lista.exists() && lista.isDirectory()) {

                System.out.println("Unesite naziv direktorijuma/fajla koji zelite da obrisete: ");
                Scanner p = new Scanner(System.in);
                String nazDir = p.next();
                File remDirectory = new File(st + "\\" + nazDir);

                if (remDirectory.exists()) {

                    FileUtils.deleteQuietly(remDirectory);
                    System.out.println("Direktorijum/fajl sa nazivom " + remDirectory.getName() + " je obrisan.");
                } else {

                    System.out.println("Direktorijum/fajl sa nazivom " + remDirectory.getName() + " ne postoji.");
                }
            } else if (!lista.exists()) {

                System.out.println("Direktorijum ne postoji.");
            }

        } catch (Exception e) {

            System.out.println("Problem sa brisanjem foldera/fajla.");
        }
    }

    public void RENAME() {

        try {

            if (lista.exists() && lista.isDirectory()) {

                System.out.println("Unesite naziv direktorijuma/fajla koji zelite da preimenujete: ");
                Scanner s = new Scanner(System.in);
                String nazDir = s.next();
                File oldName = new File(st + "\\" + nazDir);

                if (!oldName.exists()) {

                    System.out.println("Direktorijum/fajl sa nazivom " + oldName.getName() + " ne postoji.");

                } else {

                    System.out.println("Unesite zeljeni naziv direktorijuma/fajla: ");
                    Scanner d = new Scanner(System.in);
                    String noviDir = d.next();
                    File newName = new File(st + "\\" + noviDir);

                    if (newName.exists()) {

                        System.out.println("Direktorijum/fajl sa nazivom " + newName.getName() + " vec postoji.");
                    } else {
                        if (oldName.renameTo(newName)) {

                            System.out.println("Direktorijum/fajl je promenjen u " + newName.getName());
                        } else {

                            System.out.println("Promena imena nije izvrsena.");
                        }
                    }
                }
            } else if (!lista.exists()) {

                System.out.println("Direktorijum/fajl ne postoji.");
            }

        } catch (Exception e) {

            System.out.println("Problem sa kreiranjem novog foldera.");
        }
    }

    public void COPY() {

        try {

            if (lista.exists() && lista.isDirectory()) {

                System.out.println("Unesite naziv direktorijuma/fajla koji zelite da kopirate: ");
                Scanner s = new Scanner(System.in);
                String nazDir = s.next();
                File oldName = new File(st + "\\" + nazDir);

                if (!oldName.exists()) {

                    System.out.println("Direktorijum/fajl sa nazivom " + oldName.getName() + " ne postoji.");

                } else {

                    System.out.println("Unesite zeljeni direktorijum gde zelite kopirati fajl/direktorijum u formatu \"C:\\ProgramFiles\": ");
                    Scanner d = new Scanner(System.in);
                    String noviDir = d.next();
                    File newName = new File(noviDir);

                    if (!newName.exists()) {

                        System.out.println("Direktorijum sa nazivom " + newName.getName() + " ne postoji.");
                    }
                    
                    if (oldName.isDirectory()) {
                        FileUtils.copyDirectory(oldName, newName);
                        System.out.println("Vas direktorijum " + oldName.getName() + " je uspesno iskopiran.");
                    } else if (oldName.isFile() && newName.isDirectory()){

                        FileUtils.copyFileToDirectory(oldName, newName);
                        System.out.println("Vas fajl " + oldName.getName() + " je uspesno iskopiran u direktorijum " + newName.getName());
                    } else {
                    
                        FileUtils.copyFile(oldName, newName);
                        System.out.println("Vas fajl " + oldName.getName() + " je uspesno iskopiran.");
                    }

                }
            } else if (!lista.exists()) {

                System.out.println("Direktorijum/fajl ne postoji.");
            }

        } catch (IOException e) {

            System.out.println("Problem sa kopiranjem foldera/fajla.");
        }
    }
    
    public void MOVE() {

        try {

            if (lista.exists() && lista.isDirectory()) {

                System.out.println("Unesite naziv direktorijuma/fajla koji zelite da premestite: ");
                Scanner s = new Scanner(System.in);
                String nazDir = s.next();
                File oldName = new File(st + "\\" + nazDir);

                if (!oldName.exists()) {

                    System.out.println("Direktorijum/fajl sa nazivom " + oldName.getName() + " ne postoji.");

                } else {

                    System.out.println("Unesite zeljeni direktorijum gde zelite premestiti fajl/direktorijum u formatu \"C:\\ProgramFiles\": ");
                    Scanner d = new Scanner(System.in);
                    String noviDir = d.next();
                    File newName = new File(noviDir);

                    if (!newName.exists()) {

                        System.out.println("Direktorijum sa nazivom " + newName.getName() + " ne postoji.");
                    }
                    
                    if (oldName.isDirectory()) {
                        FileUtils.moveDirectory(oldName, newName);
                        System.out.println("Vas direktorijum " + oldName.getName() + " je uspesno premesten.");
                    } else if (oldName.isFile() && newName.isDirectory()){

                        FileUtils.moveFileToDirectory(oldName, newName, true);
                        System.out.println("Vas fajl " + oldName.getName() + " je uspesno premesten u direktorijum " + newName.getName());
                    } else {
                    
                        FileUtils.moveFile(oldName, newName);
                        System.out.println("Vas fajl " + oldName.getName() + " je uspesno premesten.");
                    }

                }
            } else if (!lista.exists()) {

                System.out.println("Direktorijum/fajl ne postoji.");
            }

        } catch (IOException e) {

            System.out.println("Problem sa premestanjem foldera/fajla.");
        }
    }
    
    public void EXIT(){
    
        System.out.println("Hvala na koriscenju programa.");
        
    }

}
