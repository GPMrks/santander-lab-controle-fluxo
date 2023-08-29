import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessoSeletivo {
    public static void main(String[] args) {

        List<String> candidatos = new ArrayList<>();

        System.out.println("---------- SELEÇÃO DE CANDIDATOS ----------\n");
        Map<String, Double> selecaoDeCandidatos = selecaoDeCandidatos(candidatos);

        System.out.println("\n---------- IMPRIMIR LISTA GERAL ----------\n");
        imprimirListagemGeral(candidatos);

        System.out.println("\n---------- ANÁLISE DE CANDIDATOS ----------\n");
        List<String> aprovados = analisarCandidatos(selecaoDeCandidatos);

        System.out.println("\n---------- ENTRANDO EM CONTATO ----------\n");
        entrandoEmContato(aprovados);

    }

    static Map<String, Double> selecaoDeCandidatos(List<String> selecaoCandidatos) {
        String[] candidatos = {"FELIPE", "MARCIA", "JULIA", "PAULO", "AUGUSTO", "MONICA", "FABRICIO", "MIRELA", "DANIELA", "JORGE"};
        Map<String, Double> candidatosESalarios = new HashMap<>();

        selecaoCandidatos.addAll(Arrays.asList(candidatos));

        int candidatosSelecionados = 0;
        int candidatoAtual = 0;
        double salarioBase = 2000.0;

        while (candidatosSelecionados < 5 && candidatoAtual < candidatos.length) {
            String candidato = candidatos[candidatoAtual];
            double salarioPretendido = valorPretendido();

            System.out.printf("O candidato %s solicitou o seguinte valor: R$%.2f\n", candidato , salarioPretendido);

            candidatosESalarios.put(candidato, salarioPretendido);

            if (salarioBase >= salarioPretendido) {
                System.out.println("O candidato " + candidato + " foi selecionado para a vaga");
                candidatosSelecionados++;
            }

            candidatoAtual++;
        }

        return candidatosESalarios;
    }

    static List<String> analisarCandidatos(Map<String, Double> candidatosESalarios) {
        double salarioBase = 2000.0;
        List<String> candidatosAprovados = new ArrayList<>();

        for (Map.Entry<String, Double> entry : candidatosESalarios.entrySet()) {

            String candidato = entry.getKey();
            Double salarioPretendido = entry.getValue();

            if (salarioBase > salarioPretendido) {
                System.out.println("LIGAR PARA O CANDIDATO " + candidato);
                candidatosAprovados.add(candidato);
            } else if (salarioBase == salarioPretendido) {
                System.out.println("LIGAR PARA O CANDIDATO " + candidato + " COM A CONTRA PROPOSTA");
                candidatosAprovados.add(candidato);
            } else {
                System.out.println("AGUARDANDO O RESULTADO DOS DEMAIS CANDIDATOS");
            }
        }

        return candidatosAprovados;
    }

    static void imprimirListagemGeral(List<String> candidatos) {

        for (int i = 0; i < candidatos.size(); i++) {
            System.out.println("O candidato de nº" + (i+1) + " é o " + candidatos.get(i));
        }
    }

    static void entrandoEmContato(List<String> aprovados) {

        for (String candidato : aprovados) {
            int tentativasRealizadas = 1;
            boolean continuarTentando = true;
            boolean atendeu = false;

            do {
                atendeu = atender();
                continuarTentando = !atendeu;

                if (continuarTentando) {
                    tentativasRealizadas++;
                } else {
                    System.out.println("CONTATO REALIZADO COM SUCESSO");
                }
            } while (continuarTentando && tentativasRealizadas < 3);

            if (atendeu) {
                System.out.println("CONSEGUIMOS CONTATO COM " + candidato + " NA " + tentativasRealizadas + " TENTATIVA\n");
            } else {
                System.out.println("NÃO CONSEGUIMOS CONTATO COM " + candidato + ". NÚMERO MÁXIMO DE " + tentativasRealizadas + " TENTATIVAS REALIZADAS\n");
            }
        }
    }

    static boolean atender() {
        return new Random().nextInt(3) == 1;
    }

    static double valorPretendido() {
        return ThreadLocalRandom.current().nextDouble(1800, 2200);
    }
}