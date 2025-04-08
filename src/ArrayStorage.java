import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        size = 0;
    }

    void save(Resume resume) {
        this.storage[this.size++] = resume;
    }

    Resume get(String uuid) {
        for (int i = 0; i < this.size(); i++) {
            if (Objects.equals(this.storage[i].uuid, uuid)) {
                return this.storage[i];
            }
        }

        return null;
    }

    void delete(String uuid) {
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] all = new Resume[this.size()];
        if (this.size() >= 0) {
            System.arraycopy(this.storage, 0, all, 0, this.size());
        }
        return all;
    }

    int size() {
        return this.size;
    }
}
