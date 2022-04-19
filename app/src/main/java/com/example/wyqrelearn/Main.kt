package com.example.wyqrelearn

import java.util.*


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
    Solution().sortColors(intArrayOf(2,0,2,1,1,0))
}

class Solution {
    fun sortColors(nums: IntArray): Unit {
        var left = 0
        var right = nums.lastIndex
        var cur = 0
        while (cur <= right) {
            when (nums[cur]) {
                0 -> {
                    swap(nums, left, cur)
                    left++
                    cur++
                }
                1 -> {
                    cur++
                }
                2 -> {
                    swap(nums,cur,right)
                    right--
                }
            }
        }
//        println(nums.contentToString())
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        if (i == j){
            return
        }
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }
}