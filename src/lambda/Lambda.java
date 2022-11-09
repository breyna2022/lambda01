
package lambda;

import dao.PersonaDao;
import dao.ProductoDao;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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
    static PersonaDao pdao = new PersonaDao();
    static ProductoDao proddao = new ProductoDao();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Persona p1 = new Persona(1, "Alex", LocalDate.of(1992, 1, 25));
        Persona p2 = new Persona(2, "Ana", LocalDate.of(1993, 3, 21));
        Persona p3 = new Persona(3, "Maria", LocalDate.of(1983, 7, 23));
        Persona p4 = new Persona(4, "Ruth", LocalDate.of(2018, 2, 15));
        pdao.agregar(p1);pdao.agregar(p2);pdao.agregar(p3);pdao.agregar(p4);
        Producto pr1 = new Producto(1, "Laptop", 3000.0);
        Producto pr2 = new Producto(2, "Teclado", 300.50);
        Producto pr3 = new Producto(3, "Pantalla", 1000.0);
        Producto pr4 = new Producto(4, "Mouse", 150.0);
        proddao.agregar(pr1);proddao.agregar(pr2);proddao.agregar(pr3);proddao.agregar(pr4);
        
       //Listar Persona y Producto
       List<Persona> lista1 = pdao.getLista();
       List<Producto> lista2 = proddao.getLista();
       lista1.forEach(System.out::println);
       lista2.forEach(System.out::println);
       
       //Filter (param: Predicate) Listar los mayores a 20 años
        pdao.printList(pdao.edadMayor());
       // 2-Map (param: Function)
        pdao.printList(pdao.map());
       // 3-Sorted (param: Comparator)
        pdao.printList(pdao.compareto());
       // 4-Match (param: Predicate)
        System.out.println(pdao.match());
        // 5-Limit/Skip
        pdao.printList(pdao.limit());        
        // 6-Collectors
        // GroupBy
        System.out.println(proddao.collectors_groupBy1());
        // Counting
        System.out.println(proddao.collectors_groupBy2());
        //System.out.println(collect2);
        System.out.println(proddao.collectors_groupBy3());
        //Agrupando por nombre producto y sumando
        System.out.println(proddao.suma_resumen());

        //Obteniendo suma y resumen
        System.out.println(proddao.reduce().get());
        
    }

}
