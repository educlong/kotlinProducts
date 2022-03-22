package elong.test

import elong.ui.ProductsUI
import javax.swing.JFrame

fun main(args: Array<String>) {
    var gui:JFrame= JFrame("Chương trình quản lý sản phẩm")
    var productsUI=ProductsUI()
    gui.contentPane=productsUI.pnMain
    productsUI.createMenu(gui)
    gui.defaultCloseOperation=JFrame.EXIT_ON_CLOSE
    gui.setSize(550,500)
    gui.setLocationRelativeTo(null)
    gui.isVisible=true
}