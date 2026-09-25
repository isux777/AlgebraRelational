import java.util.ArrayList;

public class Relation {
    public ArrayList<String> header = new ArrayList<String>();
    public ArrayList<Row> rows = new ArrayList<Row>();

    public Relation(String csvFile){
        CSVLoader loader = new CSVLoader(csvFile);
        Relation temp = loader.loadCSVinRelation();
        this.header = temp.header;
        this.rows =temp.rows;
    }

    public Relation Selection(Relation input, String key, String value){
        Relation res = new Relation();
        res.header = new ArrayList<String>(input.header);

        int pos = -1;
        for (int i = 0; i < input.header.size(); i++) {
            if (input.header.get(i).equals(key)) {
                pos = i;
            }
        }
        if(pos != -1){
            for (int i = 0; i < input.rows.size(); i++) {
                Row r =input.rows.get(i);
                if(r.values.get(pos).equals(value)){
                    res.rows.get(i);
                }
            }
        }
        return res;
    }

    public Relation Projection(Relation input, ArrayList<String>keys){
        Relation res = new Relation();
        res.header = keys;

        ArrayList<Integer> pos = new ArrayList<Integer>();
        for (int i = 0; i < keys.size(); i++) {
            for(int j = 0; j < input.header.size(); j++){
                if(keys.get(i).equals(input.header.get(j))){
                    pos.add(j);
                }
            }
        }
        for (int i = 0; i < input.rows.size(); i++) {
            Row vecchia = input.rows.get(i);
            Row nuova = new Row();
            for(int j = 0; j < pos.size(); j++){
                nuova.values.add(vecchia.values.get(pos.get(j)));
            }
            res.rows.add(nuova);
        }
        return res;
    }

    public Relation Union(Relation one, Relation two) {
        Relation res = new Relation();
        if (one.header.equals(two.header)) {
            res.header = new ArrayList<String>(one.header);

            for (int i = 0; i < one.rows.size(); i++) {
                res.rows.add(one.rows.get(i));
            }
            for (int i = 0; i < two.rows.size(); i++) {
                Row r2 = two.rows.get(i);
                boolean ce = false;
                for (int j = 0; j < res.rows.size(); j++) {
                    if (res.rows.get(j).values.equals(r2.values)) {
                        ce = true;
                    }
                }
                if (!ce) {
                    res.rows.add(r2);
                }
            }
        }
        return res;
    }

    public Relation Difference(Relation one, Relation two){
        Relation res = new Relation();
        if(one.header.equals(two.header)){
            res.header = new ArrayList<String>(one.header);
            for(int i = 0; i < one.rows.size(); i++){
                Row r1 =one.rows.get(i);
                boolean ce = false;

                for (int j = 0; j < two.rows.size(); j++) {
                    if (r1.values.equals(two.rows.get(j).values)){
                        ce = true;
                    }
                }
                if(!ce){
                    res.rows.add(r1);
                }
            }
        }
        return res;
    }
}


 
