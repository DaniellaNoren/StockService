package daniella.iths.se.librarystorageservice.resources;

import java.util.List;

public class ListOfObject<T> {

    private List<T> listOfObjects;

    public ListOfObject(){

    }

    public List<T> getList() {
        return listOfObjects;
    }

        public void setBookList(List<T> listOfObject) {
        this.listOfObjects = listOfObject;
    }
}
