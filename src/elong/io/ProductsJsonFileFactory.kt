package elong.io

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import elong.models.Product
import java.io.FileReader
import java.io.FileWriter

class ProductsJsonFileFactory {
    public fun SaveFiles(products:MutableList<Product>,path:String):Boolean{
        try {
            var fileWriter = FileWriter(path)
            Gson().toJson(products,fileWriter)
            fileWriter.close()
            return true
        }catch(e:Exception){
            e.printStackTrace()
        }
        return false
    }
    public fun ReadFile(path:String):MutableList<Product>{
        var products:MutableList<Product> = mutableListOf()
        try {
            var fileReader = FileReader(path)
            products=Gson().fromJson(fileReader,
                object:TypeToken<MutableList<Product>>(){}.type)
            fileReader.close()
        }catch (e:Exception){e.printStackTrace()}
        return products
    }
}