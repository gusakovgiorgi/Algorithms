package com.example.algorithms.closest_pair;

import com.example.algorithms.sorting.mergesort.MergeSortAlgorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;

public class ClosestPairAlgorithm {

    private static Comparator<Point> xComparator = (o1, o2) -> {
        int xDifference = o1.getX() - o2.getX();
        if (xDifference != 0) {
            return xDifference;
        } else {
            return o1.getY() - o2.getY();
        }
    };

    public static Point[] findClosetPairNaive(Point... points) {
        points = removeDuplicates(points);

        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("points are null or there is less than 2 points");
        }

        double midDistance = Double.MAX_VALUE;
        Point[] closestPoints = new Point[2];
        for (int i = 0; i < points.length; i++) {
            Point firstPoint = points[i];
            for (int j = i + 1; j < points.length; j++) {
                Point secondPoint = points[j];
                double distance = Math.sqrt(Math.pow((firstPoint.getX() - secondPoint.getX()), 2) +
                        Math.pow((firstPoint.getY() - secondPoint.getY()), 2));
                if (distance <= midDistance) {
                    midDistance = distance;
                    closestPoints[0] = firstPoint;
                    closestPoints[1] = secondPoint;
                }
            }
        }
        return closestPoints;
    }

    public static Point[] findClosetPairQuick(Point... points) {
        points = removeDuplicates(points);

        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("points are null or there is less than 2 points");
        }

        Point[] Px = MergeSortAlgorithm.sort(points, xComparator);
        Point[] Py = MergeSortAlgorithm.sort(points, (o1, o2) -> {
            int yDifference = o1.getY() - o2.getY();
            if (yDifference != 0) {
                return yDifference;
            } else {
                return o1.getX() - o2.getX();
            }
        });

        return closestPair(Px, Py);
    }

    static Point[] closestPair(Point[] Px, Point[] Py) {
        if (Px.length <= 4) {
            return findClosetPairNaive(Px);
        }

        int middleIndex = Px.length / 2 - 1;
        Point[] Lx, Ly, Rx, Ry;
        Point middlePoint = Px[middleIndex];

        Lx = Arrays.copyOfRange(Px, 0, middleIndex + 1);
        Ly = new Point[middleIndex + 1];
        Rx = Arrays.copyOfRange(Px, middleIndex + 1, Px.length);
        Ry = new Point[Px.length - (middleIndex + 1)];

        int RyIndex = 0, LyIndex = 0;
        for (int i = 0; i < Px.length; i++) {
            Point yPoint = Py[i];
            if (xComparator.compare(yPoint, middlePoint) <= 0) {
                Ly[LyIndex++] = yPoint;
            } else {
                Ry[RyIndex++] = yPoint;
            }
        }

        Point[] firstPair = closestPair(Lx, Ly);
        Point[] secondPair = closestPair(Rx, Ry);
        double firstPairDistance = getEuclideanDistance(firstPair[0], firstPair[1]);
        double secondPairDistance = getEuclideanDistance(secondPair[0], secondPair[1]);
        double delta = Math.min(firstPairDistance,
                secondPairDistance);
        Point[] splitPair = closestSplitPair(Px, Py, delta);
        if (splitPair != null) {
            return splitPair;
        } else if (firstPairDistance < secondPairDistance) {
            return firstPair;
        } else {
            return secondPair;
        }

    }

    private static Point[] closestSplitPair(Point[] Px, Point[] Py, double delta) {
        // Index of left part of array which is from 0 to middle-1
        int middleElementIndex = Px.length / 2 - 1;
        Point xBar = Px[middleElementIndex];
        Point[] Sy = getPointsSortedByY(Py, xBar.getX(), delta);
        double bestDelta = delta;
        Point[] bestPair = null;
        for (int i = 0; i < Sy.length; i++) {
            Point first = Sy[i];
            // Maybe here min can be less than 7 (maeby 3)
            int min = Math.min(7, Sy.length - i - 1);
            for (int j = 1; j <= min; j++) {
                Point second = Sy[i + j];
                double newDelta = getEuclideanDistance(first, second);
                if (newDelta < bestDelta) {
                    bestPair = new Point[]{first, second};
                    bestDelta = newDelta;
                }
            }
        }
        return bestPair;
    }

    private static Point[] getPointsSortedByY(Point[] Py, double xMiddle, double range) {
        return Arrays.stream(Py).filter(point -> (point.getX() > xMiddle - range) && (point.getX() < xMiddle + range))
                .toArray(Point[]::new);
    }

    public static double getEuclideanDistance(Point first, Point second) {
        return Math.sqrt(Math.pow((first.getX() - second.getX()), 2) +
                Math.pow((first.getY() - second.getY()), 2));
    }


    private static Point[] removeDuplicates(Point[] points) {
        Set<Point> setPoints = new LinkedHashSet<>(Arrays.asList(points));
        Point[] removedDuplicatePoints = setPoints.toArray(new Point[setPoints.size()]);
        return removedDuplicatePoints;
    }


}
