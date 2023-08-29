import java.util.Scanner;

public class Contador {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o primeiro parâmetro: ");
        int primeiroParametro = scanner.nextInt();

        System.out.print("Digite o segundo parâmetro: ");
        int segundoParametro = scanner.nextInt();

        try {
            contar(primeiroParametro, segundoParametro);
        }catch (ParametrosInvalidosException e) {
            System.out.println(e.getMessage());
        }
    }

    static void contar(int primeiroParametro, int segundoParametro) throws ParametrosInvalidosException {

        int conte = 0;

        if (primeiroParametro > segundoParametro) {
            throw new ParametrosInvalidosException("Segundo parâmetro não pode ser menor que o primeiro!");
        } else {
            conte = segundoParametro - primeiroParametro;
        }

        for (int i = 0; i < conte; i++) {
            System.out.println("Imprimindo o número " + (i+1));
        }
    }
}
