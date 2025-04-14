package me.bbfh.webapp.storage;

import me.bbfh.webapp.exception.ResumeAlreadyExistsException;
import me.bbfh.webapp.exception.ResumeNotFoundException;
import me.bbfh.webapp.model.Resume;

import java.util.Objects;

public class ListStorage extends AbstractStorage {
    private static class Node {
        Resume resume;
        Node prev;
        Node next;

        Node(Node prev, Resume resume, Node next) {
            this.prev = prev;
            this.resume = resume;
            this.next = next;
        }
    }

    Node first;
    Node last;

    private Node find(String uuid) {
        if (this.first == null) {
            return null;
        }

        Node pivot = this.first;
        do {
            if (Objects.equals(pivot.resume.getUUID(), uuid)) {
                return pivot;
            }
            pivot = pivot.next;
        } while (pivot != null);

        return null;
    }

    @Override
    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public void update(Resume resume) throws ResumeNotFoundException {
        Node node = this.find(resume.getUUID());
        if (node == null) {
            throw new ResumeNotFoundException(resume.getUUID());
        }
        node.resume = resume;
    }

    @Override
    public void save(Resume resume) throws ResumeAlreadyExistsException {
        this.size++;

        if (this.first == null && this.last == null) {
            Node node = new Node(null, resume, null);
            this.first = node;
            this.last = node;
            return;
        }

        if (this.find(resume.getUUID()) != null) {
            throw new ResumeAlreadyExistsException(resume);
        }

        Node node = new Node(this.last, resume, null);
        this.last.next = node;
        this.last = node;
    }

    @Override
    public Resume get(String uuid) {
        Node node = this.find(uuid);
        if (node == null) {
            return null;
        }
        return node.resume;
    }

    @Override
    public void delete(String uuid) throws ResumeNotFoundException {
        Node node = find(uuid);
        if (node == null) {
            throw new ResumeNotFoundException(uuid);
        }
        node.prev.next = node.next;
    }

    @Override
    public Resume[] getAll() {
        if (this.first == null) {
            return new Resume[0];
        }

        Resume[] all = new Resume[this.size];
        int i = 0;
        Node pivot = this.first;
        do {
            all[i] = pivot.resume;
            pivot = pivot.next;
            i++;
        } while (pivot != null);

        return all;
    }
}
