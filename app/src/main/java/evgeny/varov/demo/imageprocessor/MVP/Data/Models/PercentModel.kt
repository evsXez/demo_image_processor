package evgeny.varov.demo.imageprocessor.MVP.Data.Models

class PercentModel(val f: Float = 1f) {

    constructor(value: Float, total: Float) : this(value/total)

    val full: Boolean
        get() = f >= 1f
}