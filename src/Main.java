import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18),
                new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12),
                new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2),
                new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14),
                new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5),
                new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19),
                new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31),
                new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8),
                new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24),
                new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2),
                new BigDecimal("2799.93"), "Gerente"));

        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        System.out.println("FUNCIONARIOS: ");
        funcionarios.forEach(System.out::println);

        funcionarios.forEach(funcionario ->
                funcionario.setSalario(funcionario.getSalario().multiply(new BigDecimal("1.1"))));

        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();

        funcionarios.forEach(funcionario -> {
            funcionariosPorFuncao.computeIfAbsent(funcionario.getFuncao(), k -> new ArrayList<>()).add(funcionario);
        });

        System.out.println("\nFuncionários por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(System.out::println);
        });

        System.out.println("\nFuncionarios que fazem aniversário em outubro e dezembro:");
        funcionarios.stream()
                .filter(funcionario ->
                        funcionario.getDataNascimento().getMonthValue() == 10 ||
                        funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);

        System.out.println("\nFuncionario com a maior idade:");
        Funcionario maisVelho = funcionarios.stream()
                        .min((f1,f2) -> f1.getDataNascimento().compareTo(f2.getDataNascimento()))
                        .orElse(null);

        if(maisVelho != null) {
            long idade = maisVelho.getDataNascimento().until(LocalDate.now()).getYears();
            System.out.println("Nome: " + maisVelho.getNome() + ", Idade: " + idade);
        }

        System.out.println("\nFuncionarios ordenados por ordem alfabética:");
        funcionarios.stream()
                .sorted((f1, f2) -> f1.getNome().compareTo(f2.getNome()))
                .forEach(System.out::println);

        System.out.println("\nTotal dos salários dos funcionários:");
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(totalSalarios);

        System.out.println("\nQuantidade de salários mínimos de cada funcionário:");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(funcionario -> {
            BigDecimal salarioMinimos = funcionario.getSalario()
                    .divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + ": " + salarioMinimos);
        });

    }
}