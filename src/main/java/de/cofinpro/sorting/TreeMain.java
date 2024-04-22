package de.cofinpro.sorting;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

@Slf4j
public class TreeMain {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree.Node<>(
                new Tree.Node<>(
                        new Tree.Node<>(new Tree.Nil<>(), 1, new Tree.Nil<>()),
                        2,
                        new Tree.Node<>(new Tree.Nil<>(), 3, new Tree.Nil<>())),
                4,
                new Tree.Node<>(
                        new Tree.Node<>(new Tree.Nil<>(), 5, new Tree.Nil<>()),
                        6,
                        new Tree.Node<>(new Tree.Nil<>(), 7, new Tree.Nil<>())));

        log.info("{}", tree.contains(5)); // true
        log.info("{}", tree.contains(8)); // false

        tree.inorderTraversal(n -> log.info("{}", n)); // 1 2 3 4 5 6 7
        tree.breadthFirstTraversal(n -> log.info("{}", n)); // 4 2 6 1 3 5 7
    }
}


sealed interface Tree<T> permits Tree.Nil, Tree.Node {

    boolean contains(T target);

    void inorderTraversal(Consumer<T> consumer);

    void breadthFirstTraversal(Consumer<T> consumer);

    // implement breadth-first traver
    record Nil<T>() implements Tree<T> {
        @Override
        public boolean contains(T target) {
            return false;
        }

        @Override
        public void inorderTraversal(Consumer<T> consumer) {
            // do nothing
        }

        @Override
        public void breadthFirstTraversal(Consumer<T> consumer) {
            // do nothing
        }
    }


    record Node<T>(Tree<T> left, T val, Tree<T> right) implements Tree<T> {
        @Override
        public boolean contains(T target) {
            return target.equals(val)
                   || left.contains(target)
                   || right.contains(target);
        }

        @Override
        public void inorderTraversal(Consumer<T> consumer) {
            left.inorderTraversal(consumer);
            consumer.accept(val);
            right.inorderTraversal(consumer);
        }

        @Override
        public void breadthFirstTraversal(Consumer<T> consumer) {
            Queue<Tree<T>> queue = new LinkedList<>();
            queue.add(this);
            while (!queue.isEmpty()) {
                switch (queue.poll()) {
                    case Nil<T> ignored -> { // do nothing
                    }
                    case Node<T> n -> {
                        consumer.accept(n.val);
                        queue.add(n.left);
                        queue.add(n.right);
                    }
                }
            }
        }
    }
}
