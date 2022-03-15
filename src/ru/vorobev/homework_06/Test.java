package ru.vorobev.homework_06;

public class Test {
    public static void main(String[] args) {
        
        int count = 20;
        int notBalanced = 0;
    
        for (int i = 0; i < count; i++) {
            My4LevelTree<Integer> tree = new My4LevelTree<>();
    
            for (int j = 0; j < 25; j++) {
                tree.add((int)(Math.random() * 50) - 25);
            }
    
            System.out.println(".".repeat(64));
            System.out.println("Tree #" + i);
            tree.display();
            if (!tree.isBalanced(tree.getRoot())) {
                notBalanced++;
            }
        }
        int notBalancedPercents = 100 * notBalanced / count;
        System.out.println("Not balanced trees: " + notBalancedPercents + "%");
    }
}
