import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class Cliente {
 
    public static void main(String[] args) {
 
        String id = "David";
        // 
        if (args.length!=0){
            id=args[0];
        }
        //puerto del servidor
        final int PUERTO_SERVIDOR = 5000;
        //buffer donde se almacenara los mensajes
        byte[] bufferOut = new byte[1024];
        byte[] bufferIn = new byte[1024];
 
        try {
            //TODAS LAS SIGUIENTES IP VALEN
            //InetAddress direccionServidor = InetAddress.getByName("localhost");
            InetAddress direccionServidor = InetAddress.getByName("255.255.255.255");
            //InetAddress direccionServidor = InetAddress.getByName("192.168.1.255");

            //Creo el socket de UDP
            DatagramSocket socketUDP = new DatagramSocket();
 
            String mensaje = "Hola santi desde "+id+"!";
 
            //Convierto el mensaje a bytes
            bufferOut = mensaje.getBytes("UTF-8");
 
            //Creo un datagrama
            DatagramPacket pregunta = new DatagramPacket(bufferOut, bufferOut.length, direccionServidor, PUERTO_SERVIDOR);
 
            //Lo envio con send
            System.out.println(mensaje);//"Envio el datagrama");
            socketUDP.send(pregunta);
 
            //Preparo la respuesta
            DatagramPacket peticion = new DatagramPacket(bufferIn, bufferIn.length);
 
            //Recibo la respuesta
            socketUDP.receive(peticion);
            System.out.println("Recibo la peticion");
 
            //Cojo los datos y lo muestro
            mensaje = new String(peticion.getData());
            System.out.println(mensaje);
 
            //Cierro el socket
            socketUDP.close();
 
        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
 
}