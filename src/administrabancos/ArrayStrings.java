
package administrabancos;

import java.util.ArrayList;
import java.util.List;

public class ArrayStrings {

    // Lista para armazenar as strings
    protected List<String> stringList;
    protected List<String> insercaoList;

    
    // Construtor
    public ArrayStrings() {
        this.stringList = new ArrayList<>();
        this.insercaoList = new ArrayList<>();
    }

    // Método para adicionar strings à lista
    public void addStrings(String... strings) {
        for(String s : strings) {
            stringList.add(s);
        }
    }
    
    public void addStringsInsercao(String... strings){
        for(String s : strings){
            insercaoList.add(s);
        }
    }

    // Método para obter todas as strings armazenadas
    public List<String> getStrings() {
        return this.stringList;
    }
    
    public List<String> getStringsInsercao() {
        return this.insercaoList;
    }
}
