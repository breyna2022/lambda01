
package lambda;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import model.Persona;
import model.Producto;

/**
 *
 * @author admin
 */
public class Lambda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Persona p1 = new Persona(1, "Alex", LocalDate.of(1992, 1, 25));
        Persona p2 = new Persona(2, "Ana", LocalDate.of(1993, 3, 21));
        Persona p3 = new Persona(3, "Maria", LocalDate.of(1983, 7, 23));
        Persona p4 = new Persona(4, "Ruth", LocalDate.of(2018, 2, 15));
        Persona p5 = new Persona(5, "Carlos", LocalDate.of(2011, 7, 4));

        Producto pr1 = new Producto(1, "Laptop", 3000.0);
        Producto pr2 = new Producto(2, "Teclado", 300.50);
        Producto pr3 = new Producto(3, "Pantalla", 1000.0);
        Producto pr4 = new Producto(4, "Mouse", 150.0);

        List<Persona> personas = Arrays.asList(p1, p2, p3, p4, p5);
        List<Producto> productos = Arrays.asList(pr1, pr2, pr3, pr4);
        
       // personas.forEach(System.out::println);
       // productos.forEach(System.out::println);
       
       //Filter (param: Predicate) Listar los mayores a 20 años
        List<Persona> filterList1 = personas.stream()
                .filter(p->Lambda.getAge(p.getFechaNac())>=20)
                .collect(Collectors.toList());
        //Lambda.printList(filterList1);
          // 2-Map (param: Function)
        Function<String, String> coderFunction = name -> "Coder " + name;
        List<String> filteredList2 = personas.stream()
                                        //.filter(p -> App.getAge(p.getBirthDate()) >= 18)
                                        //.map(p -> App.getAge(p.getBirthDate()))
                                        //.map(p -> "Coder " + p.getName())
                                        //.map(p-> p.getName())
                                        .map(Persona::getNombre)
                                        .map(coderFunction)
                                        .collect(Collectors.toList());
        //App.printList(filteredList2);      

        // 3-Sorted (param: Comparator)
        Comparator<Persona> byNameAsc = (Persona o1, Persona o2) -> o1.getNombre().compareTo(o2.getNombre());
        Comparator<Persona> byNameDesc = (Persona o1, Persona o2) -> o2.getNombre().compareTo(o1.getNombre());
        Comparator<Persona> byBirthDate = (Persona o1, Persona o2) -> o1.getFechaNac().compareTo(o2.getFechaNac());

        List<Persona> filteredList3 = personas.stream()
                                        .sorted(byBirthDate)
                                        .collect(Collectors.toList());
        //App.printList(filteredList3);          
        
        // 4-Match (param: Predicate)
        Predicate<Persona> startsWithPredicate = person -> person.getNombre().startsWith("J");
        // anyMatch : No evalua todo el stream, termina en la coincidencia
        boolean rpta1 = personas.stream()
                                .anyMatch(startsWithPredicate);        
        // allMatch : Evalua todo el stream bajo la condicion
        boolean rpta2 = personas.stream()
                                .allMatch(startsWithPredicate);        
        
        // noneMatch : Evalua todo el stream bajo la condicion
        boolean rpta3 = personas.stream()
                                .noneMatch(startsWithPredicate);                
        
        // 5-Limit/Skip
        int pageNumber = 1;
        int pageSize = 2;
        List<Persona> filteredList4 = personas.stream()
                                        .skip(pageNumber * pageSize)
                                        .limit(pageSize)
                                        .collect(Collectors.toList());
        //App.printList(filteredList4);
        
        // 6-Collectors
        // GroupBy
        Map<String, List<Producto>> collect1 = productos.stream()
                                                .filter(p -> p.getPrecio()> 20)
                                                .collect(Collectors.groupingBy(Producto::getNombre));
        //System.out.println(collect1);
        // Counting
        Map<String, Long> collect2 = productos.stream()
                                            .collect(Collectors.groupingBy(
                                                Producto::getNombre, Collectors.counting()
                                                )
                                            );
        //System.out.println(collect2);
        //Agrupando por nombre producto y sumando
        Map<String, Double> collect3 = productos.stream()
                                            .collect(Collectors.groupingBy(
                                                Producto::getNombre, 
                                                Collectors.summingDouble(Producto::getPrecio)
                                                )
                                            );
        //System.out.println(collect3);
        //Obteniendo suma y resumen
        DoubleSummaryStatistics statistics = productos.stream()
                                                .collect(Collectors.summarizingDouble(Producto::getPrecio));
        //System.out.println(statistics);
        
        //7-reduce        
        Optional<Double> sum = productos.stream()
                                    .map(Producto::getPrecio)
                                    .reduce(Double::sum);
                                    //.reduce((a,b) -> a+b)
        System.out.println(sum.get());
        

        
    }
    public static int getAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public static void printList(List<?> list){
        list.forEach(System.out::println);
    }
}
