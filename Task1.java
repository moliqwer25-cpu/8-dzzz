import java.util.Locale;

abstract class Beverage {
    public abstract String getDescription();
    public abstract double cost();
}

class Espresso extends Beverage {
    @Override
    public String getDescription() {
        return "Эспрессо";
    }

    @Override
    public double cost() {
        return 1.50;
    }
}

class Tea extends Beverage {
    @Override
    public String getDescription() {
        return "Чай";
    }

    @Override
    public double cost() {
        return 1.00;
    }
}

class Latte extends Beverage {
    @Override
    public String getDescription() {
        return "Латте";
    }

    @Override
    public double cost() {
        return 2.80;
    }
}

class Mocha extends Beverage {
    @Override
    public String getDescription() {
        return "Мокко";
    }

    @Override
    public double cost() {
        return 3.20;
    }
}

abstract class BeverageDecorator extends Beverage {
    protected Beverage beverage;

    public BeverageDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public abstract String getDescription();

    @Override
    public abstract double cost();
}

class Milk extends BeverageDecorator {
    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Молоко";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.50;
    }
}

class Sugar extends BeverageDecorator {
    public Sugar(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Сахар";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.20;
    }
}

class WhippedCream extends BeverageDecorator {
    public WhippedCream(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Взбитые сливки";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.75;
    }
}

class Chocolate extends BeverageDecorator {
    public Chocolate(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Шоколад";
    }

    @Override
    public double cost() {
        return beverage.cost() + 1.10;
    }
}

class Syrup extends BeverageDecorator {
    public Syrup(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Кленовый сироп";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.60;
    }
}

public class Task1 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        System.out.println("=== Система управления заказами в кафе ===");

        System.out.println("\nЗаказ 1:");
        Beverage order1 = new Espresso();
        order1 = new Milk(order1);
        order1 = new Sugar(order1);

        System.out.println("--- Итоговый заказ 1 ---");
        System.out.println("Описание: " + order1.getDescription());
        System.out.println("Общая стоимость: $" + String.format("%.2f", order1.cost()));

        System.out.println("\nЗаказ 2:");
        Beverage order2 = new Latte();
        order2 = new WhippedCream(order2);
        order2 = new Chocolate(order2);

        System.out.println("--- Итоговый заказ 2 ---");
        System.out.println("Описание: " + order2.getDescription());
        System.out.println("Общая стоимость: $" + String.format("%.2f", order2.cost()));

        System.out.println("\nЗаказ 3:");
        Beverage order3 = new Mocha();
        order3 = new Syrup(order3);
        order3 = new Milk(order3);

        System.out.println("--- Итоговый заказ 3 ---");
        System.out.println("Описание: " + order3.getDescription());
        System.out.println("Общая стоимость: $" + String.format("%.2f", order3.cost()));
    }
}