package com.intvw;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Merge 2 streams of objects into a new stream
 */
public class StreamSort {

    public class StreamObj implements Comparable{
        Date date;
        String val;

        public int compareTo(Object other) {
            return this.date.compareTo(((StreamObj)other).date);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public static void main(String[] args) throws IOException {

        List<StreamObj> list1 = new ArrayList<>();
        List<StreamObj> list2 = new ArrayList<>();

        List<StreamObj> outList = new ArrayList<>();

        Iterator<StreamObj> itr1 = list1.stream().iterator();
        Iterator<StreamObj> itr2 = list2.stream().iterator();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StreamObj left=null , right = null;
        while(itr1.hasNext() && itr2.hasNext()){
            left = itr1.next();
            right = itr2.next();

            if(left.compareTo(right) > 0){
                writer.write(left.toString());
            }
        }

        while(itr1.hasNext()){

        }
    }
}
