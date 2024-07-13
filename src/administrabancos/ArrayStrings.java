
package administrabancos;

import java.util.ArrayList;
import java.util.List;

public class ArrayStrings {

    // Lista para armazenar as strings
    private List<String> stringList;

    // Construtor
    public ArrayStrings() {
        this.stringList = new ArrayList<>();
    }

    // Método para adicionar strings à lista
    public void addStrings(String... strings) {
        for (String s : strings) {
            this.stringList.add(s);
        }
    }

    // Método para obter todas as strings armazenadas
    public List<String> getStrings() {
        return this.stringList;
    }

    
}
