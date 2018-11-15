package conto_corrente;

import java.util.*;

public class SortedList<T extends Comparable<T>>{

    private ArrayList<T> internalList;

    public SortedList(){

        this.internalList = new ArrayList<>();

    }

    public boolean add(T element){

        boolean res  = this.internalList.add(element);
        Collections.sort(this.internalList);
        return res;
    }

    public T get(int index){

        return this.internalList.get(index);

    }

    public String toString(){

        return this.internalList.toString();

    }




}
