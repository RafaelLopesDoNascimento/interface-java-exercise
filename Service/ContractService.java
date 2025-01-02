package Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Domain.entities.Contract;
import Domain.entities.Installment;

public class ContractService {
	public ContractService() {

	}

	public void processContract(Contract contract, Integer months) {
		System.out.println("Contract service method");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		PaypalService payPalService = new PaypalService();
		System.out.println("Parcelas:");
		LocalDate dateSupport = contract.getDate();

		for (int i = 1; i <= months; i++) {
			dateSupport = dateSupport.plusMonths(1);
//			contract.setDate(contract.getDate().plusMonths(1));
			Double interestParam = payPalService.interest(contract.getTotalValue(), i);
			Double toAddpaymentFeeParam = payPalService.paymentFee(contract.getTotalValue() / months + interestParam);
			Double paymentFeeParam = contract.getTotalValue() / months + interestParam;

			System.out.print(dateSupport.format(dtf));
			System.out.printf(" - %.2f%n", toAddpaymentFeeParam + paymentFeeParam);
			Installment installmentParam = new Installment(contract.getDate().plusMonths(1), (toAddpaymentFeeParam + paymentFeeParam));
			contract.addInstallment(installmentParam);
		}
	}
}
