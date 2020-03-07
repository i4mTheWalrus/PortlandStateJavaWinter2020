package edu.pdx.cs410J.race3;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AirlineSaveState implements Serializable {
    static public List<Airline> airlineList = new ArrayList<>();
    static AirlineSaveState instance = null;

    public static AirlineSaveState getInstance(){
        if( instance == null )
            instance = new AirlineSaveState();
        return instance;
    }

    public static void saveData(AirlineSaveState instance){
        ObjectOutput out;
        try {
            File outFile = new File(Environment.getExternalStorageDirectory(), "airlines.data");
            out = new ObjectOutputStream(new FileOutputStream(outFile));
            out.writeObject(instance);
            out.close();
        } catch (Exception e) {e.printStackTrace();}
    }

    public static AirlineSaveState loadData(){
        ObjectInput in;
        AirlineSaveState ss=null;
        try {
            in = new ObjectInputStream(new FileInputStream("airlines.data"));
            ss=(AirlineSaveState) in.readObject();
            in.close();
        } catch (Exception e) {e.printStackTrace();}
        return ss;
    }
}
