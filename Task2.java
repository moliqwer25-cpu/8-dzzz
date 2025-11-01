import java.util.Locale;

interface IPaymentProcessor {
    void processPayment(double amount);
}

class PayPalPaymentProcessor implements IPaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("PayPal процессор обрабатывает платеж. Сумма: $" + String.format("%.2f", amount));
    }
}

class StripePaymentService {
    public void makeTransaction(double totalAmount) {
        System.out.println("Stripe: Проводится транзакция. Общая сумма: $" + String.format("%.2f", totalAmount));
    }
}

class StripePaymentAdapter implements IPaymentProcessor {
    private StripePaymentService stripeService;

    public StripePaymentAdapter(StripePaymentService stripeService) {
        this.stripeService = stripeService;
    }

    @Override
    public void processPayment(double amount) {
        System.out.print("Stripe Адаптер обрабатывает платеж -> ");
        stripeService.makeTransaction(amount);
    }
}

class AnotherPaymentGateway {
    public boolean authorizeAndPay(double value) {
        System.out.print("AnotherGateway: Проводится авторизация и оплата. Сумма: $" + String.format("%.2f", value));
        return true;
    }
}

class AnotherPaymentAdapter implements IPaymentProcessor {
    private AnotherPaymentGateway gateway;

    public AnotherPaymentAdapter(AnotherPaymentGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void processPayment(double amount) {
        System.out.print("AnotherPayment Адаптер обрабатывает платеж -> ");
        gateway.authorizeAndPay(amount);
        System.out.println(" (Успешно завершено.)");
    }
}

public class Task2 {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        IPaymentProcessor payPalProcessor = new PayPalPaymentProcessor();

        StripePaymentService stripeService = new StripePaymentService();
        IPaymentProcessor stripeAdapter = new StripePaymentAdapter(stripeService);

        AnotherPaymentGateway anotherGateway = new AnotherPaymentGateway();
        IPaymentProcessor anotherAdapter = new AnotherPaymentAdapter(anotherGateway);

        System.out.println("\n--- Платеж 1 (Через PayPal) ---");
        payPalProcessor.processPayment(50.00);

        System.out.println("\n--- Платеж 2 (Через Stripe Adapter) ---");
        stripeAdapter.processPayment(75.50);

        System.out.println("\n--- Платеж 3 (Через Another Adapter) ---");
        anotherAdapter.processPayment(100.99);

        System.out.println("\nВсе платежи обработаны через единый интерфейс IPaymentProcessor.");
    }
}