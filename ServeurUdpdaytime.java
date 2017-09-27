import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.GregorianCalendar;

public class ServeurUdpdaytime {

	private int port;
	private int nbClients;

	public ServeurUdpdaytime(int port, int nbClients) {
		this.port = port;
		this.nbClients = nbClients;
	}

	public void lancer() {
		byte[] buf = new byte[512];

		try {

			DatagramSocket socket = new DatagramSocket(null);
			socket.bind(new InetSocketAddress(port));

			DatagramPacket packetReceive = new DatagramPacket(buf, buf.length);

			String chaineDate;

			System.out.println("Serveur daytime lancé sur le port " + port);

			for (int i = 1; i <= nbClients; i++) {

				socket.receive(packetReceive);

				chaineDate = new GregorianCalendar().getTime().toString();

				DatagramPacket packetSend = new DatagramPacket(
						chaineDate.getBytes(), chaineDate.getBytes().length,
						packetReceive.getAddress(), packetReceive.getPort());

				socket.send(packetSend);

			}

			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		new ServeurUdpdaytime(Integer.parseInt(args[0]),
				Integer.parseInt(args[1])).lancer();
	}

}