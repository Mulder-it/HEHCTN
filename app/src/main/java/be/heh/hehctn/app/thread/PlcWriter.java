package be.heh.hehctn.app.thread;

import be.heh.hehctn.simanticS7.S7;
import be.heh.hehctn.simanticS7.S7Client;

public class PlcWriter implements Runnable {

    private S7Client s7Client;
    static byte[] data;
    String sIp;
    int iRack;
    int iSlot;

    public PlcWriter(byte[] data,String sIp,int iRack,int iSlot){
        this.data = data;
        this.sIp = sIp;
        this.iRack = iRack;
        this.iSlot = iSlot;
    }
    @Override
    public void run() {
        int isConnected = 0;


        Plc plc = new Plc(sIp,iRack,iSlot);

        PlcConnection plcConnection = new PlcConnection(plc);
        isConnected = plcConnection.open();

        s7Client = plcConnection.getS7Client();

        while (isConnected == 0){
            s7Client.WriteArea(S7.S7AreaDB,5,0,34,data);
            try {
                Thread.sleep(100);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setWrite(int db, byte value){
        data[db] = value;
    }

    public void setWrite(int db, int value){
        S7.SetWordAt(data,db,value);
    }
}
