package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Domain.entities.Contract;
import Domain.entities.Installment;
import Service.ContractService;

public class exercise {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in).useLocale(Locale.US);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("Entre os dados do contrato");
		System.out.println("Numero: ");
		int contractNumber = sc.nextInt();
		System.out.println("Data (dd/MM/yyyy)");
		String dateString = sc.next();

		LocalDate ld = LocalDate.parse(dateString, dtf);
		System.out.println("Valor do contrato:");
		Double totalValue = sc.nextDouble();

		System.out.println("Entre com o numero de parcelas: ");
		int installments = sc.nextInt();

		Contract contract = new Contract(contractNumber, ld, totalValue, new ArrayList<Installment>());

		ContractService contractService = new ContractService();

		contractService.processContract(contract, installments);

	}

}
