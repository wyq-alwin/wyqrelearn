package com.example.wyqrelearn


//class ListNode(var `val`: Int) {
//    var next: ListNode? = null
//
//    override fun toString(): String {
//        return "$`val`  ${next ?: ""}"
//    }
//}

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
//class TreeNode(var `val`: Int) {
//    var left: TreeNode? = null
//    var right: TreeNode? = null
//}
//
//fun TreeNode?.print() {
//    val sb = StringBuilder()
//    fun help(node: TreeNode?) {
//        if (node == null) {
//            sb.append(" null")
//            return
//        }
//        help(node.left)
//        sb.append(" " + node.`val`)
//        help(node.right)
//    }
//    help(this)
//    println(sb)
//}

//
//class Node(var `val`: Int) {
//    var left: Node? = null
//    var right: Node? = null
//    var next: Node? = null
//}
//class Node(var `val`: Int) {
//    var children: List<Node?> = listOf()
//}


//---------------------------------------------------------------------------------
//
//class Solution {
//    fun generateParenthesis(n: Int): List<String> {
//        val dp = Array(n-1){ mutableListOf<String>()}
//        dp[0].add("()")
//        for ( i in 1 .. n-1){
//            dp[i]
//        }
//    }
//}

class Salary(var base: Int) {
    override fun toString(): String {
        return base.toString()
    }

    operator fun plus(other: Salary): Salary {
        this.base += other.base
        return this
    }
}

operator fun Salary.minus(other: Salary): Salary {
    this.base -= other.base
    return this
}

fun main() {
    val s1 = Salary(1)
    val s2 = Salary(2)
    println(s1 + s2)
    println(s1 - s2)

    val map = mapOf(
        "java" to 1,
        "kotlin" to 2,
        "python" to 3
    ).withDefault { "?" }
}


