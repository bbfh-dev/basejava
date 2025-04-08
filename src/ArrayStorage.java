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
        return null;
    }

    void delete(String uuid) {
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {
        return this.size;
    }
}
