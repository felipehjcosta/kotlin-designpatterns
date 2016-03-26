package com.felipecosta.designpatterns.behavioral

interface TreeNode {
    val leftNode: TreeNode?
    val rightNode: TreeNode?
    val value: Int
    fun accept(visitor: TreeVisitor)
}

interface TreeVisitor {
    fun visit(treeNode: TreeNode)
}

class TreeNodeImpl(value: Int,
                   leftTreeNode: TreeNode? = null,
                   rightTreeNode: TreeNode? = null) : TreeNode {

    private var internalValue = value;
    private var leftTreeNode: TreeNode? = leftTreeNode
    private var rightTreeNode: TreeNode? = rightTreeNode

    override val value: Int
        get() = internalValue

    override val leftNode: TreeNode?
        get() = leftTreeNode
    override val rightNode: TreeNode?
        get() = rightTreeNode

    override fun accept(visitor: TreeVisitor) {
        visitor.visit(this)
    }
}

class PreOrderTreeVisitor() : TreeVisitor {

    override fun visit(treeNode: TreeNode) {
        val leftNode = treeNode.leftNode
        leftNode?.accept(this)

        System.out.println("TreeNode Value: ${treeNode.value}")

        val rightNode = treeNode.rightNode
        rightNode?.accept(this)
    }
}

class PostOrderTreeVisitor() : TreeVisitor {

    override fun visit(treeNode: TreeNode) {
        val rightNode = treeNode.rightNode
        rightNode?.accept(this)

        System.out.println("TreeNode Value: ${treeNode.value}")

        val leftNode = treeNode.leftNode
        leftNode?.accept(this)
    }
}

fun main(args: Array<String>) {
    val leftTreeNode = TreeNodeImpl(10)
    val rightTreeNode = TreeNodeImpl(20)
    val rootTreeNode = TreeNodeImpl(30, leftTreeNode, rightTreeNode)

    PreOrderTreeVisitor().visit(rootTreeNode)
    System.out.println("")
    PostOrderTreeVisitor().visit(rootTreeNode)
}
