package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {                        //25/06/2018 10:30
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		String carModel = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.print("Retorno (dd/MM/yyyy): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.println("Entre com o preço por hora: ");
		double pricePerHour = sc.nextDouble();
		System.out.println("Entre com o preço pro dia: ");
		double pricePerDay = sc.nextDouble();  
		RentalService rentalService =  new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		
		rentalService.processInvoic(cr);
		
		System.out.println("fatura:");
		System.out.println("Pagamento basico: " + String.format("%.2f", cr.getInvoic().getBasicPayment()));
		System.out.println("Imposto: " + String.format("%.2f", cr.getInvoic().getTax()));
		System.out.println("Pagamento total: " + String.format("%.2f", cr.getInvoic().getTotalPayment()));
		
		sc.close();
	}

}
