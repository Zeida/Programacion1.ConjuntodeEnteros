import org.junit.*;
import static org.junit.Assert.*;
    /**@date: 09/10/2017
    *@authors:  Cristian Javier Rivero Martín
    *           Miguel Lincoln Capote Pratts
    *           Juan Manuel Benítez Rodríguez
    *           Zeida de los Reyes Rodríguez Mendoza
    *  Realización de las pruebas para la práctica 1, cada uno de los métodos se prueba en el test con el nombre del mismo. Disponemos, además,
    * de un conjunto de matrices de apoyo para la comprobación de la certeza de los método, definidos por "forMétodo".
    */
    
public class ConjuntoTest{
    private ConjuntoDeEnteros[] toTest2;
    private ConjuntoDeEnteros[] toTest;
    /** Vectores a partir de los cuales generamos los ConjuntoDeEntero que utilizaremos para las pruebas. Contiene elementos repetidos,
     * y algunos vectores tienen un tamaño mayor a 10, para comprobar que, efectivamente, toma solo los 10 primeros valores válidos.
     * Además, tenemos el vector vacío.
     */
    private int[][] matrix = {
        {},
        {1},
        {1,1,1,1,1,1,1,1,1,1,2},
        {1,2,3,4,5,6,7,8,9,0},
        {1,2,3,4,5,6,7,8,9},
        {1,1,1,1,1,1,1,1,1,1,2,3,4,5,6},
        {3,5,-1,0,-59,100,-30,-30,5,295},
        {11},  //union i == 7
        {7,88,9,60,55,34},
        {1,23,35,64,44,56,2,43,41},
        {7,88,9,60,55,34,-7,-8,-1,60,-2},
        {3,5,-1,0,-59,100,-30,295}
        //fin union
    };
    // Valores de los vectores anteriores que deberían ser introducidos en los ConjuntoDeEntero según la definición dada desde i=0 hasta i=7.
    private int[][] forTestElements = {
        {},
        {1},
        {1,2},
        {1,2,3,4,5,6,7,8,9,0},
        {1,2,3,4,5,6,7,8,9},
        {1,2,3,4,5,6},
        {3,5,-1,0,-59,100,-30,295},
        {},  //union i == 7
        {64,66},
        {1,23,35,64},
        {-1,88,55,60,9,7,-2,0},
        {12,2,100}
        // fin union
    };
    private int[][] forTestUnion = {
        {}, 
        {11},
        {7,88,9,60,55,34,64,66},                    //casos de union 
        {1,23,35,64,44,56,2,43,41},                 //casos de union 
        {7,88,9,60,55,34,-7,-8,-1,60,-2,0},
    };
    //Casos intersección
    private int[][] forTestInterseccion={
        {},
        {},
        {},
        {1,23,35,64},
        {-1,88,55,60,9,7,-2},
        {100},
        
    };
    //Casos diferencia
    private int[][] forTestDiferencia={
        {},
        {11},
        {7,88,9,60,55,34},
        {44,56,2,43,41},
        {34,-7,-8},
        {3,5,-1,0,-59,-30,295},
    };
    //Casos contenido
    private int[][] forTestContenido={
        {},
        {11},
        {7,9},
        {44,1,64,35},
        {34,-7,-8,1},
        {1},
    };
    private int[] vecAñade = {111,34,22,100};
    private int[] vecPertenece = {1,11,3,23,100};
    private String[] mensajesPruebas={
        "Conjuntos vacíos ",
        "Conjuntos de 1 elemento(mismo número) ",
        "Conjunto con elementos repetidos ",
        "Conjuntos de 10 elementos ",
        "Conjuntos de 9 elementos ",
        "Conjunto con más de 10 elementos, con repetición y mismo conjunto sin repetición ",
        "Conjuntos de elementos sin orden ",
        "Conjunto de 1 elemento y conjunto vacío ",
        "Conjunto de elementos aleatorios, sin repetición ",
        "Conjunto de elementos aleatorios, sin repetición ",
        "Conjunto de elementos aleatorios, sin repetición ",
        "Conjunto de elementos aleatorios, sin repetición ",
    };
    // Se inicializan los ConjuntoDeEnteros.
    @Before
    public void inicializa(){
        toTest = new ConjuntoDeEnteros[matrix.length];
        for(int i = 0; i < matrix.length; i++){
            toTest[i] = new ConjuntoDeEnteros(matrix[i].clone());
        }
        toTest2 = new ConjuntoDeEnteros[forTestElements.length];
        for(int i = 0; i < forTestElements.length; i++){
            toTest2[i] = new ConjuntoDeEnteros(forTestElements[i].clone());
        }
    }
    
    //Realizamos pruebas para el método Contenido()
    @Test
    public void testContenido(){
        int j = 0;
        for(int i = 0; i < 12 ; i++, j++){
            if(i == 1){
                i = 6;
                j--;
                continue;
            }
            ConjuntoDeEnteros contener = new ConjuntoDeEnteros(forTestContenido[j].clone());
            if(i >= 10){
                assertFalse(mensajesPruebas[i], toTest[i].contenido(contener));
                continue;
            }
            assertTrue(mensajesPruebas[i], toTest[i].contenido(contener));
        } 
    }
    
    //Realizamos pruebas para el método Diferencia()
    @Test
    public void testDiferencia(){
        int j = 0;
        for(int i = 0; i < 12; i++ , j++){
            if(i == 1){
                i = 6;
                j--;
                continue;
            }
            ConjuntoDeEnteros difiere= new ConjuntoDeEnteros(forTestDiferencia[j].clone());
            assertEquals(mensajesPruebas[i], difiere, toTest[i].diferencia(toTest2[i]));
        }
    }
    
    //Realizamos pruebas para el método Intersección()
    @Test
    public void testInterseccion(){
        int j = 0;
        for(int i = 0; i < 12; i++ , j++){
            if(i == 1){
                i = 6;
                j--;
                continue;
            }
            ConjuntoDeEnteros intersect= new ConjuntoDeEnteros(forTestInterseccion[j].clone());
            assertEquals(mensajesPruebas[i], intersect, toTest[i].intersección(toTest2[i]));
        }
    }
    
    //Realizamos pruebas para el método Unión()
    @Test
    public void testUnion(){
        int j = 0;
        for(int i = 0; i < 11; i++ , j++){
            if(i == 1){
                i = 6;
                j--;
                continue;
            }
            ConjuntoDeEnteros x = new ConjuntoDeEnteros(forTestUnion[j].clone());
            assertEquals(mensajesPruebas[i], x,toTest[i].unión(toTest2[i]));
        }
    }
    
    //Realizamos pruebas para el método Añade()
    @Test
    public void testAñade(){
        assertTrue("Error en añade: " + 0, toTest[0].añade(0));
        for(int i = 0; i < vecAñade.length; i++){
            if(i%2 !=0){
                assertFalse(mensajesPruebas[i], new ConjuntoDeEnteros(forTestUnion[i+1]).añade(vecAñade[i]));
            }else{
                assertTrue(mensajesPruebas[i] , new ConjuntoDeEnteros(forTestUnion[i+1]).añade(vecAñade[i]));
            }
        } 
    }

    //Realizamos pruebas para el método Pertenece()
    @Test
    public void testPertenece(){
        for(int i = 0; i < vecPertenece.length; i++){
            if(i%2 == 0 ){
                assertFalse(mensajesPruebas[i], new ConjuntoDeEnteros(forTestUnion[i]).pertenece(vecPertenece[i]));
            }else{
                assertTrue(mensajesPruebas[i], new ConjuntoDeEnteros(forTestUnion[i]).pertenece(vecPertenece[i]));
            }
        }  
    }
    
    //Realizamos pruebas para el método Vacío()
    @Test
    public void testVacío(){
        for(int i = 0; i < matrix.length; i++){
            if(toTest[i].elementos().length == 0){
                assertTrue(mensajesPruebas[i], toTest[i].estáVacío());
                continue;
            }
            assertFalse(mensajesPruebas[i], toTest[i].estáVacío());
        }
    }
    
    //Realizamos pruebas para el método Cardinal()
    @Test
    public void testCardinal(){
        for(int i = 0; i < 7; i++){
            assertTrue(mensajesPruebas[i], forTestElements[i].length == toTest[i].cardinal());
        }  
    }
    
    //Realizamos pruebas para el método Elementos()
    @Test
    public void testElementos(){
        for(int i = 0;i<7;i++){
            assertEquals(mensajesPruebas[i], toTest[i], new ConjuntoDeEnteros(toTest[i].elementos()));
        }
    }

    //Realizamos pruebas para el método Equals()
    @Test
    public void testEquals(){
        for(int i = 0; i < 7; i++){
            assertEquals(mensajesPruebas[i], toTest[i], new ConjuntoDeEnteros(forTestElements[i]));
        }  
    }
    
}

