package Service;

import java.time.format.DateTimeFormatter;

import Domain.entities.Contract;

public class ContractService {
	public ContractService() {

	}

	public void processContract(Contract contract, Integer months) {
		System.out.println("Contract service method");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		PaypalService payPalService = new PaypalService();
		System.out.println("Parcelas:");

		for (int i = 1; i <= months; i++) {
			contract.setDate(contract.getDate().plusMonths(1));
			Double interestParam = payPalService.interest(contract.getTotalValue(), i);
			Double toAddpaymentFeeParam = payPalService.paymentFee(contract.getTotalValue() / months + interestParam);
			Double paymentFeeParam = contract.getTotalValue() / months + interestParam;

			System.out.print(contract.getDate().format(dtf));
			System.out.printf(" - %.2f%n", toAddpaymentFeeParam + paymentFeeParam);
		}
	}
}
