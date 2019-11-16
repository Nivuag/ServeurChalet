/*
	ServeurChalet
	
	Implémente un Serveur qui dois:
		--Recevoirs des commandes d'un Client 
		-- Les Validers
		-- Les redirigers au Controleur Chalet
		-- Répondre aux demandes du client
		-- Creer un thread pour le client


*/
import java.net.ServerSocket;
import java.net.Socket;


public class ServeurChalet{
	
	public static final int PORT = 1000;
	
	private ServerSocket socServeur;
	private Socket socClient;
	
	private BufferedReader reader;
	private PrintWriter writer;
	
	
	
	///--- Start ---
	///   Demarage des Services du Serveur et Attente d'une connexion au client 
	///
	public void Start(){
		
		try{
		socServeur = new ServerSocket(PORT);
		System.out.println("Serveur Online. En attente d'une connexion");
		
		// Attente de la connexion d'un client
		socClient = socServeur.accept();
		System.out.println("Client Connecte");
		
		// Obtention des flux d'entrer et de sorties 
		reader = new BufferedReader(
				new InputStreamReader(socClient.getInputStream()));
		writer = new PrintWriter(
				new OutputStreamWriter(socClient.getOutputStream()));
		}
		catch (IOException ioe)
		{
			System.out.println("Connexion a echouer ");
		}
		
	
	}
	
	
	/// --- Servire ---
	/// Attent une reponse ecrite du client
	///
	private void Servir()
	{
		boolean fini = false;
		String ligne = null;
		
		while(!fini)
		{
			ligne = reader.readLine();
			
			if(ligne != null || !ligne.equals("")
					// Si la ligne n'est pas vide ou null	
						// On envois la ligne a une methode qui s'occupera de la gestion des donners recus
							// retournera false si la connexion est terminer
			   fini = Traiter_Donners(ligne.split(" "));
																											
			else{
				// Si la ligne est null, alors la connexion a eter couper
				fini = true;
			}			
			
			
		}
		
		
	}
	
	/// --- Traiter_Donners ---
	/// commandes: String[] -> Les commandes et ses parametres
	/// Resumer -> Recois une commande et reagis a celle-ci 
	/// Retourne -> Boolean representant si la connexion est terminer (false si OUI)
	private boolean Traiter_Donners(String[] commandes)
	{
		
		
		
	}
	
	
	
	
}