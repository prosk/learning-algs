package com.mycompany.yandex;

import java.util.Objects;

public class Point implements Comparable<Point> {
    int coord;
    int openCloseType;

    public Point(int coord, int openCloseType) {
        this.coord = coord;
        this.openCloseType = openCloseType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return coord == point.coord && openCloseType == point.openCloseType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coord, openCloseType);
    }

    @Override
    public int compareTo(Point anotherPoint) {
        int res = Integer.compare(this.coord, anotherPoint.coord);
        return res == 0 ? Integer.compare(this.openCloseType, anotherPoint.openCloseType) : res;
    }

}
