package put.io.testing.mocks;
import put.io.students.fancylibrary.database.IFancyDatabase;
import java.util.Collections;
import java.util.List;

public class MyDatabase implements IFancyDatabase {

    @Override
    public void connect() {

    }


    public List<Object> queryAll(){
        return Collections.emptyList();
    }


    @Override
    public <T> void persist(T t) {

    }

    @Override
    public void close() {

    }
}
