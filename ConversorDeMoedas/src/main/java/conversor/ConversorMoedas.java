package conversor;

import conversor.api.HttpClientMoeda;
import conversor.util.Conversor;

import java.util.Scanner;

public class ConversorMoedas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String apiKey = "3cca41c7e7b6701aa9b35a15";

        while (true) {
            System.out.println("\nConversor de Moedas:");
            System.out.println("1. Converter Moeda");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 2) {
                System.out.println("Encerrando o programa.");
                break;
            }

            System.out.print("Digite a moeda base (ex: USD): ");
            String base = scanner.nextLine().toUpperCase();

            System.out.print("Digite a moeda de destino (ex: BRL): ");
            String destino = scanner.nextLine().toUpperCase();

            System.out.print("Digite o valor a converter: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            try {
                double taxa = HttpClientMoeda.obterTaxa(apiKey, base, destino);
                double resultado = Conversor.converter(valor, taxa);
                System.out.printf("%.2f %s = %.2f %s%n", valor, base, resultado, destino);
            } catch (Exception e) {
                System.out.println("Erro na conversão: " + e.getMessage());
            }
        }
    }
}