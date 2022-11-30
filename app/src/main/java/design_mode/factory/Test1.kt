package design_mode.factory

import design_mode.factory.Factory.CHINESE_TYPE
import design_mode.factory.Factory.ENGLISH_TYPE

/**
 * 简单工厂实现
 * 主要围绕生产产品，也就时实例化对象来的
把实例化对象放到一个单独的类中实现，通过工厂提供对象让调用者和实现者之间解耦
有三个部分组成：产品接口、具体的产品子类实现、工厂。工厂根据调用者的要求（参数）去提供不同的产品类实现。
 *
 * @author zhangtuo
 * @date 2022/11/30
 */
object Test1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val enBook = Factory.createBook(ENGLISH_TYPE)
        enBook?.produce()
        val chBook = Factory.createBook(CHINESE_TYPE)
        chBook?.produce()
    }

}

/**
 * 产品接口
 */
interface Book {
    fun produce()
}

/**
 * 具体的产品实现
 */
class EnglishBook : Book {
    override fun produce() {
        println("英语书本")
    }
}

class ChineseBook : Book {
    override fun produce() {
        println("中文书本")
    }

}

object Factory {
    const val CHINESE_TYPE = 1
    const val ENGLISH_TYPE = 2
    fun createBook(type: Int): Book? {
        return if (type == CHINESE_TYPE) {
            ChineseBook()
        } else if (type == ENGLISH_TYPE) {
            EnglishBook()
        } else {
            null
        }
    }
}