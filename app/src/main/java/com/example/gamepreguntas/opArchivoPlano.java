package com.example.gamepreguntas;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class opArchivoPlano {

    Context ctx;
    FileOutputStream fos;
    FileInputStream fis;
    public static ArrayList<Pregunta> ListPreguntas = new ArrayList<>();



    public opArchivoPlano(Context ctx) {
        this.ctx = ctx;
    }

    public void escribirTextFromData(String archivo, String newText) throws IOException {
        try {
            fos = ctx.openFileOutput(archivo, Context.MODE_APPEND);
            fos.write(newText.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("", e.getMessage());
        } catch (IOException ex){
            Log.e("", ex.getMessage());
        }
    }

    public String leerTextFromData(String archivo) {
        String lectura = "";
        OutputStreamWriter osw = null;
        try {
            fis = ctx.openFileInput(archivo);
            int i;
            char caracter ='a';
            do{
                i = fis.read();
                if(i != '\n') {
                    caracter = (char)i;
                    lectura += caracter;
                }
                if(caracter == '.'){
                    lectura += "\n";
                }
            } while (i > 0);
        } catch(Exception e) {
            Log.e("", e.getMessage());
        }
        return lectura;
    }

    public void crearUser(String archivo, String nombre, String correo, String pass) {
        try {
            fos = ctx.openFileOutput(archivo, Context.MODE_APPEND);
            String newTextLine = nombre + '\n' + correo + '\n' + pass ;
            fos.write(newTextLine.getBytes());
            fos.write('\n');
            fos.close();
            Toast.makeText(ctx,"Se ha creado correctamente su Usuario ",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("Error-> ", e.getMessage());
        }
    }

    public ArrayList<User> leerUsuarios(String archivo){
        ArrayList<User> lstUsers = new ArrayList<>();
        int i = 1;
        int cont = 0;
        String name="", correo = "", pass = "", lectura = "";
        char caracter;
        try {
            fis = ctx.openFileInput(archivo);
            while (i > 0) {
                i = fis.read();
                caracter = (char) i;
                lectura += caracter;
                if (i == '\n') {
                    switch (cont) {
                        case 0:
                            name = lectura.trim();
                            break;
                        case 1:
                            correo = lectura.trim();
                            break;
                        case 2:
                            pass = lectura.trim();
                            cont = -1;
                            lstUsers.add(new User(name, correo, pass));
                            break;
                    }
                    lectura = "";
                    cont ++;
                }
            }
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
        return lstUsers;
    }

    public ArrayList leerPreguntas(String archivo) {
        // crea el flujo para leer desde el archivo
        try{
            fis = ctx.openFileInput(archivo);
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
        File file = new File(archivo);
        ArrayList<Pregunta> lstPreg = new ArrayList<>();
        Scanner scanner;
        try {
            //se pasa el flujo al objeto scanner
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // el objeto scanner lee linea a linea desde el archivo
                String linea = scanner.nextLine();
                Scanner delimitar = new Scanner(linea);
                //se usa una expresión regular
                //que valida que antes o despues de una coma (,) exista cualquier cosa
                //parte la cadena recibida cada vez que encuentre una coma
                //delimitar.useDelimiter("\\s*,\\s*");
                delimitar.useDelimiter("[\\s]");
                String enun = delimitar.next();
                String res1 = delimitar.next();
                String res2 = delimitar.next();
                String res3 = delimitar.next();
                String res4 = delimitar.next();
                String resC = delimitar.next();
                int dif = Integer.parseInt(delimitar.next());
                lstPreg.add(new Pregunta(enun,res1,res2,res3,res4,resC,dif));
            }
            //se cierra el ojeto scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lstPreg;
    }

   public ArrayList<Pregunta> leerPregs(String archivo) {
       ArrayList<Pregunta> lstPreg = new ArrayList<>();

       String lectura = "", enun = "", res1 = "", res2 = "", res3 = "", res4 = "", resC = "";
       int dific = 0, cont = 0;

       try {
           int i;
           char caracter = 'a';
           fis = ctx.openFileInput(archivo);
           do{
               i = fis.read();
               caracter = (char) i;
               if (caracter == '\n') {
                   cont++;
                   switch (cont) {
                       case 1:
                           enun = lectura;
                           break;
                       case 2:
                           res1 = lectura;
                           break;
                       case 3:
                           res2 = lectura;
                           break;
                       case 4:
                           res3 = lectura;
                           break;
                       case 5:
                           res4 = lectura;
                           break;
                       case 6:
                           resC = lectura;
                           break;
                       case 7:
                           dific = Integer.parseInt(lectura.trim());
                           cont = 0;
                           lectura = "";
                           lstPreg.add(new Pregunta(enun, res1, res2, res3, res4, resC, dific));
                           break;
                   }
               }
           } while (i > 0);
       }catch (Exception e) {
           Log.e("", e.getMessage());
       }
       return lstPreg;
   }

    public ArrayList<Pregunta> LeerPreguntas(String archivo) {

        ListPreguntas.clear();
        String aux = "", pregunta = "", res1 = "", res2 = "", res3 = "", res4 = "", resC = "";
        int puntaje = 0, count = 0;

        try {
            int i;
            char caracter = 'a';
            fis = ctx.openFileInput(archivo);
            do {
                i = fis.read();
                caracter = (char) i;
                if (caracter == '\n') {
                    count = count + 1;
                    if (count == 1) {
                        pregunta = aux;
                    } else if (count == 2) {
                        res1 = aux;
                    } else if (count == 3) {
                        res2 = aux;
                    } else if (count == 4) {
                        res3 = aux;
                    } else if (count == 5) {
                        resC = aux;
                    } else if (count == 6) {
                        puntaje = Integer.parseInt(aux);
                    }
                    aux = "";

                } else if (caracter == ',') {
                    ListPreguntas.add(new Pregunta(pregunta, res1, res2, res3, res4, resC, puntaje));
                    aux = "";
                    pregunta = "";
                    res1 = "";
                    res2 = "";
                    res3 = "";
                    resC = "";
                    puntaje = 0;
                    count = 0;
                } else {
                    aux = aux + caracter;
                }
            } while (i > 0);
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
        return ListPreguntas;
    }


    public ArrayList<Pregunta> cargarPreguntas(String archivo){
        ArrayList<Pregunta> lstPreg = new ArrayList<>();

        int i = 1;
        int cont = 0;
        String lectura= "",enunc = "", res1 = "", res2 = "", res3 = "", res4 = "", resCorrec = "";
        int dific = 0;
        char caracter;

        try {
            fis = ctx.openFileInput(archivo);
            while (i > 0) {
                i = fis.read();
                caracter = (char) i;
                lectura += caracter;
                if (i == '\n') {
                    switch (cont) {
                        case 0:
                            enunc = lectura.substring(1);
                            break;
                        case 1:
                            res1 = lectura.trim();
                            break;
                        case 2:
                            res2 = lectura.trim();
                            break;
                        case 3:
                            res3 = lectura.trim();
                            break;
                        case 4:
                            res4 = lectura.trim();
                            break;
                        case 5:
                            resCorrec = lectura.trim();
                            break;
                        case 6:
                            dific = Integer.parseInt(lectura.trim());
                            cont = -1;
                            lstPreg.add(new Pregunta(enunc, res1, res2, res3, res4, resCorrec, dific));
                            break;
                    }
                    lectura = "";
                    cont ++;
                }
            }
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
        return lstPreg;
    }
}

