package com.example.demo

interface ClothType {
    var typeName: String
    var properties: ArrayList<Pair<String, String>>
    var sizes: ArrayList<Size>
    var marksList: MarksForWear
}

class TypeTshirt(
        override var typeName: String = "Мужская футболка",
        override var properties: ArrayList<Pair<String, String>>,
        override var sizes: ArrayList<Size>,
        override var marksList: MarksForWear = MarksForTshirt()
) : ClothType {

}