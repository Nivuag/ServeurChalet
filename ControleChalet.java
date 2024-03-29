import org.iot.raspberry.grovepi.GrovePi;
import org.iot.raspberry.grovepi.devices.GroveLed;
import org.iot.raspberry.grovepi.pi4j.GrovePi4J;
import org.iot.raspberry.grovepi.devices.GroveLightSensor;
import org.iot.raspberry.grovepi.devices.GroveTemperatureAndHumiditySensor;
import java.io.IOException;
import java.lang.Thread;
import java.util.Scanner;
class ControleChalet 
{
	public static void main(String args[])
	{
		
			double maxTemp = 25;
			double minTemp = 15;
			double éclairage = 250;
			
			Chalet Ch1 = new Chalet(maxTemp, minTemp, éclairage);
			// Tester les lumières 
		/*try{
			Ch1.Chauffage(true);
			Ch1.Climatisation(true);
		}catch(IOException e){}
		*/
			
			Runnable A = () -> {
				// Faire ca mais en Serveur
				
					
					try
					{
						
					String Input = "";
					
					
					while(!Input.equals("q"))
					{
					 System.out.print("Commande: ");
					 Input = System.console().readLine();
					 if(Input.equals(""))
					 {
						 System.out.print(Ch1.to_string());
					 }
					 else{
						 String[] TabInput = Input.split(" "); 
						 
						 switch(TabInput[0]){
							case "t":
							
								int Max = 0;
								int Min = 0;
								if(TabInput.length == 3)
								{
									if(tryParseInt(TabInput[1]) && tryParseInt(TabInput[2]))
									{
										Min  = Integer.parseInt(TabInput[1]);
										Max = Integer.parseInt(TabInput[2]);
										
										if(!Ch1.setMargeTemp(Min,Max))
										{
											System.out.println("Marge Invalide");
										}
										
									}
									else if (TabInput[1].equals("banane"))
									{
										System.out.println("You Monkey");
									}
									else
									{
										System.out.println("Valeurs Invalides ");
									}
								}
								else
								{
								System.out.println("Nombre Paramètres Invalide ");
										}
							break;
							case "l":
								int Luminosit = 0;
								if(TabInput.length == 2)
								{
									if(tryParseInt(TabInput[1]))
									{
										Luminosit  = Integer.parseInt(TabInput[1]);
										
										
										if(!Ch1.setLuminosité(Luminosit))
										{
											System.out.println("Marge Invalide");
										}
										
									}
									else if (TabInput[1].equals("banane"))
									{
										System.out.println("You Monkey");
									}
									else
									{
										System.out.println("Valeurs Invalides ");
									}
								}
								else
								{
								System.out.println("Nombre Paramètres Invalide ");
										}
							
							break;
							case "q":
							break;
							default:
							System.out.println("Commande Invalide");
						 
						 }
						 
						 
						 }
					 
					}
				}
				catch(IOException xxx){}
				catch(InterruptedException xx){}
					
				};
				
				
			Thread thC = new Thread(A);
			
			thC.start();
			
			while(thC.isAlive())
			{
				Ch1.Update();
				
			}
	
		
	}
	
	
	public static boolean tryParseInt(String Valeur)
	{
		try{
			Integer.parseInt(Valeur);
			return true;
		} catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	
	
	
}
//lorsquon java : java ControleChalet 2> /dev
