package cd18;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ChenDeng
 * 2018-10-11 17:29
 */
public class Each
{
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("Each");
        names.add("vv");
        names.add("gg");
        names.add("dd");
        names.add("fewafaw");
        names.add("er");
        names.add("ss");
        names.add("sh");

        for(Iterator<String> it = names.iterator();it.hasNext();){
            String name = it.next();
            if(name.length()>2){
                names.remove(name);
            }
        }
    }
}
