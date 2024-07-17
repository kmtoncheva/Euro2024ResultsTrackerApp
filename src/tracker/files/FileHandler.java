package tracker.files;
import java.util.AbstractMap;

public interface FileHandler<T,K> {
        AbstractMap<T,K> uploadFromFile();
}
