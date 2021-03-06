package by.serhel.shapestask.creator;

import by.serhel.shapestask.entity.Shape;

public abstract class ShapeCreator {
    private static int id = 0;

    public abstract Shape createShape(double[] array);

    public int generateId() {
        id++;
        return id;
    }
}
