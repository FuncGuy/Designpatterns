/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package memento.solution1;

public class HIVLab {
    public static void main(String... args) {
        LabRat mickey = new LabRat();
        Memento memento;
        while (true) {
            memento = mickey.createMemento();
            mickey.feedDrugs();
            if (!mickey.isAlive()) {
                mickey.setMemento(memento);
            }
            memento = mickey.createMemento();
            mickey.blastWithRadar();
            if (!mickey.isAlive()) {
                mickey.setMemento(memento);
            }
        }
    }
}
