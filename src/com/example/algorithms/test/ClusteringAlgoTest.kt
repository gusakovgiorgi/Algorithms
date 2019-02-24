package com.example.algorithms.test

import com.example.datastructure.UnionFind
import org.junit.Test
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException

class ClusteringAlgoTest {

    @Test
    fun calculateMinimumSpacing() {
/*        val str = "0000"
        System.out.println("initial str = $str")
        for (i in 0..3) {
            val strBuilder = StringBuilder(str)
            strBuilder[i] = if (strBuilder[i] == '0') '1' else '0'
            System.out.println(strBuilder)
        }

        for (i in 0..3) {
            val firstBitBuilder = StringBuilder(str)
            firstBitBuilder[i] = if (firstBitBuilder[i] == '0') '1' else '0'
            for (j in i..3) {
                if (i == j) continue
                val strBuilder = StringBuilder(firstBitBuilder)
                strBuilder[j] = if (strBuilder[j] == '0') '1' else '0'
                System.out.println(strBuilder)
            }
        }*/
        val fileName = "/home/qvark/Desktop/clustering_big.txt"
        val points = mutableMapOf<Point, Point>()
        val unionFind = UnionFind(200000)
        try {
            val reader = BufferedReader(FileReader(fileName))
            var line: String?
//            val numerOfNodes = reader.readLine().toInt()
            reader.readLine()
            line = reader.readLine()
            var nodeIndex = 0
            while (line != null) {
                val binaryRepresentation = line.replace(" ", "")
                val point = Point(binaryRepresentation, nodeIndex++)
                val existedPoint = points.put(point, point)
                if (existedPoint != null) {
                    unionFind.union(existedPoint.nodeIndex, point.nodeIndex)
                }
                line = reader.readLine()
            }
            System.out.println(points.size)
            val iterator = points.keys.iterator()
            while (iterator.hasNext()) {

                fun unionAllWithOneBit(point: Point) {
                    for (i in 0 until point.bits.length) {
                        val strBuilder = StringBuilder(point.bits)
                        strBuilder[i] = if (strBuilder[i] == '0') '1' else '0'
                        val existedPoint = points.get(Point(strBuilder.toString(), point.nodeIndex))
                        if (existedPoint != null) {
                            unionFind.unionIfNeed(existedPoint.nodeIndex, point.nodeIndex)
                        }
                    }
                }

                fun unionAllWithTwoBits(point: Point) {
                    for (i in 0 until point.bits.length) {
                        val firstBitBuilder = StringBuilder(point.bits)
                        firstBitBuilder[i] = if (firstBitBuilder[i] == '0') '1' else '0'
                        for (j in i until point.bits.length) {
                            if (i == j) continue
                            val strBuilder = StringBuilder(firstBitBuilder)
                            strBuilder[j] = if (strBuilder[j] == '0') '1' else '0'
                            val existedPoint = points.get(Point(strBuilder.toString(), point.nodeIndex))
                            if (existedPoint != null) {
                                unionFind.unionIfNeed(existedPoint.nodeIndex, point.nodeIndex)
                            }
                        }
                    }
                }

                val point = iterator.next();
                unionAllWithOneBit(point)
                unionAllWithTwoBits(point)
            }

            println("Number of clusters = ${unionFind.calculateClusters()}")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    class Point(val bits: String, val nodeIndex: Int) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Point

            if (bits != other.bits) return false

            return true
        }

        override fun hashCode(): Int {
            return bits.hashCode()
        }
    }
}